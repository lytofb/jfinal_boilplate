package main.java.com.controller;

import com.jfinal.ext2.core.ControllerExt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by root on 2017/4/6.
 */
public class LiveShowController extends ControllerExt {
    @Override
    public void onExceptionError(Exception e) {

    }

    public void index(){
        Subject currentUser  = SecurityUtils.getSubject();
        String userAccount = (String) currentUser.getSession().getAttribute("operator_account");
        setAttr("publishUrl", getPublishUrl(userAccount));
        render("live.html");
    }

    private String getPublishUrl(String userid){
        return "rtmp://pda107c6e.live.126.net/live/30f52dbd21ee4359bb537bf8aae53922?wsSecret=6f155a0f257fb4f29807b1500e729a28&wsTime=1491442263";
    }
}
