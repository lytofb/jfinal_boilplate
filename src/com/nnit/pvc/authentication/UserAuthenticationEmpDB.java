package com.nnit.pvc.authentication;

import java.util.ArrayList;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;

import com.dao.Roles;
import com.dao.Users;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserAuthenticationEmpDB implements UserAuthenticationInterface {

	private SimpleAuthenticationInfo authenticationInfo;
	private UsernamePasswordToken token;
	private Integer userId;
	
	@Override
	public Boolean validateUser(UsernamePasswordToken token,String realmName) {
		this.token = token;
        String password = String.valueOf(token.getPassword());
        // 调用操作数据库的方法查询user信息
        Record user = Db.use("UInfo").findFirst("select * from USER_INFO where USER_I_NAME = ?"
        		, token.getUsername());
        if (user != null) {
            if (password.equals(user.getStr("USER_I_PW"))) {
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("username", user.getStr("USER_I_NAME"));
                authenticationInfo =  new SimpleAuthenticationInfo(user.getInt("USER_I_ID"),
                        user.getStr("USER_I_PW"), realmName);
                userId = user.getInt("USER_I_ID");
                return true;
            } else {
                return false;
            }
        } else {
        	return false;
        }
	}

	@Override
	public String getUserName() {
		if (token==null) {
			return null;
		} else {
			return token.getUsername();
		}
		// TODO Auto-generated method stub
	}

	@Override
	public String getPassWord() {
		if (token==null) {
			return null;
		} else {
			return String.valueOf(token.getPassword());
		}
	}

	@Override
	public SimpleAuthorizationInfo getAuthorizationById(String userId) {
		Record user = Db.use("UInfo").findById("USER_INFO", "USER_I_ID", Integer.valueOf(userId));
        if (user != null) {
        	new Permission() {
				
				@Override
				public boolean implies(Permission arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			};
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Record role = Db.use("UInfo").findById("roles",user.getInt("USER_I_ROLE"));
            info.addRole(role.getStr("rolename"));
            ArrayList<String> perms = new ArrayList<String>();
            perms.add("testRedirect");perms.add("test");
            info.addStringPermissions(perms);
//            info.addStringPermission("users/testRedirect");
            // info.addStringPermissions( role.getPermissions()
            // );//如果你添加了对权限的表，打开此注释，添加角色具有的权限
            return info;
        } else {
            return null;
        }
	}

	@Override
	public SimpleAuthenticationInfo getAuthenticationInfo() {
		// TODO Auto-generated method stub
		return authenticationInfo;
	}

	@Override
	public Boolean setUserId() {
		// TODO Auto-generated method stub
		if (userId==null) {
			return false;
		} else {
			SecurityUtils.getSubject().getSession().setAttribute("userId", userId);
			return true;
		}
	}

}
