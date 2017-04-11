package main.java.com.nnit.pvc.authentication;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.com.bean.PrincipalWrap;
import main.java.com.dao.contact_merchant_operator;
import main.java.com.dao.contact_merchant_user;
import main.java.com.dao.data_operator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.session.Session;

import main.java.com.dao.data_user;

public class UserAuthenticationSFDB implements UserAuthenticationInterface {

	private SimpleAuthenticationInfo authenticationInfo;
	private UsernamePasswordToken token;
	private Long userId;
	
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
				operatorParam.put("operator_id",user.getLong("id"));
				merchant_operator = contact_merchant_operator.dao.searchFirst(operatorParam);
				if (merchant_operator==null){
					return false;
				}
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("login_type","operator");
				session.setAttribute("operator_name", user.getStr("operator_name"));
				session.setAttribute("operator_account", user.getStr("operator_account"));
				session.setAttribute("operator_id", user.getLong("id"));
				session.setAttribute("merchant_id", merchant_operator.getLong("merchant_id"));
				session.setAttribute("merchant_name", merchant_operator.getStr("merchant_name"));
                authenticationInfo =  new SimpleAuthenticationInfo(new PrincipalWrap(user.getStr("operator_name"),user),
                        user.getStr("operator_password"), realmName);
                userId = user.getLong("id");
                return true;
            }
        }
        data_user dataUser = new data_user();
        paramMap = new HashMap<String, Object>();
        paramMap.put("user_account", token.getUsername());
        dataUser =  data_user.dao.searchFirst(paramMap);
		if (dataUser!=null) {
            if (password.equals(dataUser.getStr("user_password"))) {
                contact_merchant_user contact_merchant_user = new contact_merchant_user();
                HashMap<String, Object> userParam = new HashMap<String, Object>();
                userParam.put("user_id", dataUser.getLong("id"));
                contact_merchant_user = contact_merchant_user.dao.searchFirst(userParam);
                if (contact_merchant_user==null){
                    return false;
                }
                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("login_type","user");
                session.setAttribute("user_account", dataUser.getStr("user_account"));
                session.setAttribute("user_id", dataUser.getLong("id"));
                session.setAttribute("user_name", dataUser.getStr("user_name"));
                session.setAttribute("merchant_id", contact_merchant_user.getLong("merchant_id"));
                session.setAttribute("merchant_name", contact_merchant_user.getStr("merchant_name"));
                authenticationInfo =  new SimpleAuthenticationInfo(new PrincipalWrap(dataUser.getStr("user_name"),dataUser),
                        dataUser.getStr("user_password"), realmName);
                userId = dataUser.getLong("id");
                return true;
            } else {
                return false;
            }
        }
		return false;
	}

	public String getUserName() {
		if (token==null) {
			return null;
		} else {
			return token.getUsername();
		}
		// TODO Auto-generated method stub
	}

	public String getPassWord() {
		if (token==null) {
			return null;
		} else {
			return String.valueOf(token.getPassword());
		}
	}

	public SimpleAuthorizationInfo getAuthorizationByUser(PrincipalWrap principalWrap) {

        if (true) {
        	new Permission() {
				
				public boolean implies(Permission arg0) {
					// TODO Auto-generated method stub
					return false;
				}
			};
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            ArrayList<String> perms = new ArrayList<String>();
            perms.add("login");
            Object user = principalWrap.getModel();
            if (user instanceof data_operator){
                perms.add("data_operator");
                Long operatorId = ((data_operator) user).getLong("id");
                Integer is_sysadmin = ((data_operator) user).getInt("is_sysadmin");
                contact_merchant_operator cmo = new contact_merchant_operator();
                HashMap<String,Object> param = new HashMap<String, Object>();
                param.put("issuper","1");
                param.put("operator_id",operatorId);
                cmo = contact_merchant_operator.dao.searchFirst(param);
                if (cmo!=null) {
                    perms.add("super_opeartor");
                }
                if (is_sysadmin>0){
                    perms.add("is_sysadmin");
                }
            } else if (user instanceof data_user){
                perms.add("data_user");
            }

            info.addStringPermissions(perms);
//            info.addStringPermission("users/testRedirect");
            // info.addStringPermissions( role.getPermissions()
            // );//如果你添加了对权限的表，打开此注释，添加角色具有的权限
            return info;
        } else {
            return null;
        }
	}

	public SimpleAuthenticationInfo getAuthenticationInfo() {
		// TODO Auto-generated method stub
		return authenticationInfo;
	}

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
