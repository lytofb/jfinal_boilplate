package main.java.com.controller;

import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.kit.FileKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import main.java.com.bean.Createliveuser;
import main.java.com.bean.Createtest;
import main.java.com.bean.PrincipalWrap;
import main.java.com.dao.ModelEx;
import main.java.com.dao.contact_merchant_user;
import main.java.com.dao.data_operator;
import main.java.com.dao.data_user;
import main.java.com.toolkit.RequestKit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import javax.security.auth.Subject;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 2017/4/6.
 */
@RequiresPermissions("login")
public class LiveVipController extends ControllerExt {
    Session session = SecurityUtils.getSubject().getSession();
    Long merchant_id = (Long) session.getAttribute("merchant_id");
    String merchant_name = (String) session.getAttribute("merchant_name");
    Long operator_id = (Long) session.getAttribute("operator_id");
    String operator_name = (String) session.getAttribute("operator_name");

    @Override
    public void onExceptionError(Exception e) {

    }

    public void toCreateLiveVip(){
        render("livevipcreate.html");
    }

    public void toCreateTest(){
        data_user dataUser = getCurrentUser();
        Long userId = dataUser.getLong("id");
        List<Record> recordList = Db.find(
                String.format("select * from z%s_exercise_group where user_id = ?",
                        merchant_id.toString()),
                userId
        );
//        ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String, Object>>();
//        for (Record r :recordList){
//            HashMap<String,Object> map  = new HashMap<String, Object>();
//            map.put("id",r.getLong("id"));
//            map.put("group_name",r.getStr("group_name"));
//            result.add(map);
//        }
        setAttr("recordlist",recordList);
        render("livetestcreate.html");
    }

    public void createLiveVip(){
        Createliveuser createliveuser = getBean(Createliveuser.class);
        //record liveuser
        recordLiveuser(createliveuser);
    }

    public void createTest(){
        Createtest createtest = getBean(Createtest.class);
        Long groupid = Long.valueOf(createtest.getGroup());
        data_user dataUser = getCurrentUser();
        Long user_id = dataUser.getLong("id");
        String user_name = dataUser.getStr("user_name");

        //find exerciseGroup if null insert group
        Record exerciseGroup =
                Db.findById(String.format("z%s_exercise_group", merchant_id.toString()), groupid);
        if (exerciseGroup==null){
            String newGroup = createtest.getNewgroup();
            String groupTableName = String.format("z%s_exercise_group",merchant_id.toString());
            Record record = new Record();
            record.set("group_name",newGroup);
            record.set("user_id",user_id);
            record.set("user_name",user_name);
            Db.save(groupTableName,record);
            groupid = record.getLong("id");
            exerciseGroup = record;
        }
        //record exerciseTable
        String group_name = exerciseGroup.getStr("group_name");
        String exerciseTpl = "insert into z%s_exercise (name,html,answer,group_id,group_name,user_id,user_name)" +
                " values (?,?,?,?,?,?,?)";
        Db.update(String.format(exerciseTpl,merchant_id.toString()),
                createtest.getName(),
                createtest.getHtml(),
                createtest.getAnswer(),
                groupid.toString(),
                group_name,
                user_id.toString(),
                user_name
        );

        renderJson(createtest);
    }

    private PrincipalWrap getCurrentPrincipalWrap(){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        PrincipalWrap user = (PrincipalWrap) principals.iterator().next();
        return user;
    }

    private data_user getCurrentUser(){
        PrincipalWrap user = getCurrentPrincipalWrap();
        ModelEx model = (ModelEx) user.getModel();
        if (model instanceof data_user){
            return (data_user) model;
        } else {
            return null;
        }
    }

    private PathClass getUserFilePath(String filename){
        String uploadbasepath = PropKit.use("config.properties").get("uploadbasepath");
        String webserverbasepath = PropKit.use("config.properties").get("webserverbasepath");
        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<String> uploadpathArray = new ArrayList<String>();
        ArrayList<String> webserverpathArray = new ArrayList<String>();
        PrincipalWrap user = getCurrentPrincipalWrap();
        ModelEx model = (ModelEx) user.getModel();
        String user_account = model.getStr("user_account");
        String type = "user";
        if (model instanceof data_operator){
            type = "operator";
            user_account = model.getStr("operator_account");
        }
        tokens.add(merchant_id.toString());
        tokens.add(type);
        tokens.add(user_account);
        tokens.add(filename);
        uploadpathArray.add(uploadbasepath);
        uploadpathArray.addAll(tokens);
        webserverpathArray.add(webserverbasepath);
        webserverpathArray.addAll(tokens);
        String userpath = StringUtils.join(uploadpathArray.toArray(),File.separator);
        String webserverpath = StringUtils.join(webserverpathArray.toArray(),"/");
        return new PathClass(userpath,webserverpath);
    }

    public class PathClass{
        PathClass(String uploadpath,String webserverpath){
            setUploadpath(uploadpath);
            setWebserverpath(webserverpath);
        }
        private String uploadpath;
        private String webserverpath;

        public String getUploadpath() {
            return uploadpath;
        }

        public void setUploadpath(String uploadpath) {
            this.uploadpath = uploadpath;
        }

        public String getWebserverpath() {
            return webserverpath;
        }

        public void setWebserverpath(String webserverpath) {
            this.webserverpath = webserverpath;
        }
    }

    public void uploadimg() throws IOException {
        UploadFile file = getFile("upload");
        File uploadedFile = file.getFile();
        String filename = RequestKit.generateuuid().replaceAll("-","").substring(0,14);
        PathClass path = getUserFilePath(filename);
        File newFile = new File(path.getUploadpath());
        FileUtils.forceMkdirParent(newFile);
        uploadedFile.renameTo(newFile);
        renderJson(path);
    }

    public void mockCreateliveuser(){
        Createliveuser cl = new Createliveuser();
        cl.setTel_num(13700088213L);
        cl.setUser_account("aaa");
        cl.setUser_name("aaaname");
        cl.setUser_password("aaapwd");
        recordLiveuser(cl);
        renderJson(cl);
    }

    private Boolean recordLiveuser(final Createliveuser createliveuser){
        return Db.tx(new IAtom() {
            public boolean run() throws SQLException {
                data_user user = new data_user();
                data_user userRecord = user.searchFirst("user_account", createliveuser.getUser_account());
                if (userRecord!=null){
                    return false;
                }
                user.put("user_name",createliveuser.getUser_name());
                user.put("user_account",createliveuser.getUser_account());
                user.put("user_password",createliveuser.getUser_password());
                user.put("tel_num",createliveuser.getTel_num());
                user.save();

                Long userid = user.getLong("id");
                contact_merchant_user cmu = new contact_merchant_user();
                HashMap<String,Object> param = new HashMap<String, Object>();
                param.put("user_account",createliveuser.getUser_account());
                param.put("merchant_id",merchant_id);
                contact_merchant_user cmuRecord = cmu.searchFirst(param);
                if (cmuRecord!=null){
                    return false;
                }
                cmu.put("user_id",userid);
                cmu.put("user_account",createliveuser.getUser_account());
                cmu.put("user_name",createliveuser.getUser_name());
                cmu.put("merchant_id",merchant_id);
                cmu.put("merchant_name",merchant_name);
                cmu.put("tel_num",createliveuser.getTel_num());
                cmu.put("updatetime",new Date());
                cmu.save();
                return true;
            }
        });
    }
}
