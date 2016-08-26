package com.nnit.pvc.authentication;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;

import com.dao.data_user;

public class UserAuthenticationSFDB implements UserAuthenticationInterface {

	private SimpleAuthenticationInfo authenticationInfo;
	private UsernamePasswordToken token;
	private Integer userId;
	
	@Override
	public Boolean validateUser(UsernamePasswordToken token,String realmName) {
		this.token = token;
        String password = String.valueOf(token.getPassword());
        // 调用操作数据库的方法查询user信息
        data_user user = new data_user();
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("user_password", password);
        paramMap.put("user_account", token.getUsername());
        user = user.searchFirst(paramMap);
        if (user != null) {
            if (password.equals(user.getStr("password"))) {
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("username", user.getStr("user_name"));
                authenticationInfo =  new SimpleAuthenticationInfo(user.getInt("id"),
                        user.getStr("password"), realmName);
                userId = user.getInt("id");
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
        /*Users user = Users.dao.findById(userId);*/
		String sql = "select * from user_role_rel where user_info_id=? and deleteflag=1";
      
        if (true) {
        	new Permission() {
				
				@Override
				public boolean implies(Permission arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			};
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
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
