package main.java.com.nnit.pvc.authentication;

import main.java.com.bean.PrincipalWrap;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

public interface UserAuthenticationInterface {
	
	public Boolean validateUser(UsernamePasswordToken token,String realmName);
	public String getUserName();
	public String getPassWord();
	public SimpleAuthorizationInfo getAuthorizationByUser(PrincipalWrap principalWrap);
	public SimpleAuthenticationInfo getAuthenticationInfo();
	public Boolean setUserId();
}
