package main.java.com.controller;

import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import main.java.com.bean.Createliveuser;
import main.java.com.dao.contact_merchant_user;
import main.java.com.dao.data_user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

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
        render("livetestcreate.html");
    }

    public void createLiveVip(){
        Createliveuser createliveuser = getBean(Createliveuser.class);
        //record liveuser
        recordLiveuser(createliveuser);
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
