package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.nnit.pvc.authentication.PVCUserNamePasswordToken;
import com.toolkit.Md5Kit;

/**
 * CommonController
 */
public class CommonController extends Controller {
	
	protected final Log logger = Log.getLog(getClass());
	
	public void index() {
		render("tables.html");
	}
	public void tables() {
		render("tables.html");
	}
	public void incomehistory() {
		render("incomehistory.html");
	}


	public void login() {
		Subject currentUser  = SecurityUtils.getSubject();
		String password=null;
		try {
			password = getPara("password");
//			password = Md5Kit.getMd5(getPara("password"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UsernamePasswordToken token = new UsernamePasswordToken(getPara("username"), password);
		PVCUserNamePasswordToken pvctoken = new PVCUserNamePasswordToken(token, "saleForce");
		token.setRememberMe(true);
		try {
			currentUser.login(pvctoken);
			Long userid = (Long) currentUser.getSession().getAttribute("userId");
			System.out.println(userid);
			redirect("/");
		} catch (Exception e) {
			Log.getLog(CommonController.class).info("auth error", e);
			// TODO: handle exception
		}
	}
	
	public void logout() {
		Subject currentUser  = SecurityUtils.getSubject();
		currentUser.logout();
		redirect("/");
	}
	
	@RequiresRoles("super")
	public void test() {
		renderText("test");
	}
	
	@RequiresRoles("super")
	@RequiresPermissions("testRedirect")
	public void testRedirect() {
		renderText("testRedirect");
	}
	
}
