package com.nnit.pvc.authentication;

import java.util.ArrayList;
import java.util.HashMap;

import com.dao.contact_merchant_operator;
import com.dao.data_operator;
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
	private Long userId;
	
	@Override
	public Boolean validateUser(UsernamePasswordToken token,String realmName) {
		this.token = token;
        String password = String.valueOf(token.getPassword());
        // 调用操作数据库的方法查询user信息
        data_operator user = new data_operator();
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("user_password", password);
        paramMap.put("operator_account", token.getUsername());
        user = user.searchFirst(paramMap);
        if (user != null) {
            if (password.equals(user.getStr("operator_password"))) {
				contact_merchant_operator merchant_operator = new contact_merchant_operator();
				HashMap<String, Object> operatorParam = new HashMap<String, Object>();
				operatorParam.put("operator_id",merchant_operator.getLong("id"));
				merchant_operator = contact_merchant_operator.dao.searchFirst(operatorParam);
				if (merchant_operator==null){
					return false;
				}
                Session session = SecurityUtils.getSubject().getSession();
				session.setAttribute("operator_name", user.getStr("operator_name"));
				session.setAttribute("operator_account", user.getStr("operator_account"));
				session.setAttribute("operator_id", user.getLong("id"));
				session.setAttribute("operator_name", user.getStr("operator_name"));
				session.setAttribute("merchant_id", merchant_operator.getLong("merchant_id"));
				session.setAttribute("merchant_name", merchant_operator.getStr("merchant_name"));
                authenticationInfo =  new SimpleAuthenticationInfo(user.getLong("id"),
                        user.getStr("operator_password"), realmName);
                userId = user.getLong("id");
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
            perms.add("login");
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
