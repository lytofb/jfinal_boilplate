package main.java.com.controller;

import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import main.java.com.core.LiveChannel;
import main.java.com.core.LiveChannelManager;
import main.java.com.core.LiveChannelWrap;
import main.java.com.dao.contact_merchant_user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static main.java.com.toolkit.NnitSqlKit.buildSql;
import static main.java.com.toolkit.NnitSqlKit.turnArrayToList;

/**
 * Created by root on 2017/4/6.
 */
public class LiveShowController extends ControllerExt {
    Session session = SecurityUtils.getSubject().getSession();
    Long merchant_id = (Long) session.getAttribute("merchant_id");
    String user_account = (String) session.getAttribute("user_account");
    String merchant_name = (String) session.getAttribute("merchant_name");
    Long operator_id = (Long) session.getAttribute("operator_id");
    String operator_name = (String) session.getAttribute("operator_name");

    @Override
    public void onExceptionError(Exception e) {

    }

    public void index(){
        Subject currentUser  = SecurityUtils.getSubject();
        setAttr("publishUrl", getPublishUrl(user_account));
//        try {
//            openLiveshowChannel("kk","hello");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        render("live.html");
    }

    public void listliveuser(){
        Integer pagenum = getParaToInt(0,1);
        keepPara();
        StringBuilder sqlExpect =
                new StringBuilder(" from contact_merchant_user where enabled=1 ");
        String tailorderby = " order by id desc";
        String vipname = getPara("vipname");
//        String vipcardid = getPara("vipcardid");
//        String oldvipcardid = getPara("oldcardid");
        ArrayList<Object> listpara = new ArrayList();
        listpara.add(merchant_id);
        listpara.add(vipname);
//        listpara.add(vipcardid);
//        listpara.add(oldvipcardid);
        String dynamicSql = buildSql(sqlExpect.toString(),listpara,tailorderby,"merchant_id","user_name");
        Page<Record> page = Db.paginate(pagenum, 20, "select *", dynamicSql,turnArrayToList(listpara));
        setAttr("page",page);
        render("livevipinfo.html");
    }

    public void openChannel() throws IOException {
        String user_account = getPara("user_account");
        String channelName = user_account+"_liveshow";
        openLiveshowChannel(channelName,user_account);
        renderNull();
    }

    private void openLiveshowChannel(String channelname,String account) throws IOException {
        Subject currentUser  = SecurityUtils.getSubject();
        Long merchant_id = (Long) currentUser.getSession().getAttribute("merchant_id");
        LiveChannelWrap liveChannelwrap = LiveChannelManager.createChannel(channelname,account);
        recordLiveChannel(liveChannelwrap,merchant_id);
    }

    private void recordLiveChannel(LiveChannelWrap liveChannelWrap,Long merchant_id) {
        String liveshowTable = String.format("z%s_liveshow",merchant_id.toString());
        String insertLiveshowSqlTpl = String.format(
                "insert into %s " +
                        "(user_account,cid,chname,pushUrl,pullHttpUrl,pullHLSUrl,pullRTMPUrl,createtime) " +
                        "values (?,?,?,?,?,?,?,?)",
                liveshowTable
        );
        LiveChannel liveChannel = liveChannelWrap.getLiveChannel();
        Date now = new Date();
        Db.update(insertLiveshowSqlTpl,
                liveChannelWrap.getUserAccount(),
                liveChannel.getCid(),
                liveChannel.getName(),
                liveChannel.getPushurl(),
                liveChannel.getHttpPullUrl(),
                liveChannel.getHlsPullUrl(),
                liveChannel.getRtmpPullUrl(),
                now);
        //search contact_merchant_user and update channel_status
        HashMap<String,Object> cmuPara = new HashMap<String, Object>();
        contact_merchant_user contact_merchant_user = new contact_merchant_user();
        cmuPara.put("user_account",liveChannelWrap.getUserAccount());
        cmuPara.put("merchant_id",merchant_id);
//        cmuPara.put("channel_status","open");
        contact_merchant_user = contact_merchant_user.dao.searchFirst(cmuPara);
        contact_merchant_user.put("channel_status","open");
        contact_merchant_user.update();
    }

    private String getPublishUrl(String userAccount){
        String sqltpl = String.format("select * from z%s_liveshow where enabled = 1 and user_account = ?",
                merchant_id.toString());
        Record liveshow = Db.findFirst(sqltpl, userAccount);
        if (liveshow==null) {
            throw new RuntimeException();
        } else {
            return liveshow.getStr("pushUrl");
        }
    }
}
