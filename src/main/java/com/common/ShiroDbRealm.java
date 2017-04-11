package main.java.com.common;

import main.java.com.bean.PrincipalWrap;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import main.java.com.nnit.pvc.authentication.PVCUserNamePasswordToken;
import main.java.com.nnit.pvc.authentication.UserAuthenticationInterface;
import main.java.com.nnit.pvc.authentication.UserAuthenticationSFDB;

public class ShiroDbRealm extends AuthorizingRealm {
	
	private UserAuthenticationInterface uai;

	/**
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
    	PVCUserNamePasswordToken token = (PVCUserNamePasswordToken) authcToken;
        String dbtarget = token.getDbtarget();
        
        /**
         * hard coded for distribute
         */
//        String interfacename = getInterfaceNameByDbTarget(dbtarget);
        String interfacename = "UserAuthenticationSFDB";
        Class clazz = null;
        try {
			clazz = Class.forName("main.java.com.nnit.pvc.authentication."+interfacename);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        UserAuthenticationInterface uai = new UserAuthenticationSFDB();
        try {
			uai = (UserAuthenticationInterface) clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (uai.validateUser(token,getName())) {
        	if (!uai.setUserId()) {
				return null;
			}
        	this.uai = uai;
			return uai.getAuthenticationInfo();
		} else {
			return null;
		}
//        String password = String.valueOf(token.getPassword());
//        // 调用操作数据库的方法查询user信息
//        Users user = Users.dao.findFirst("select * from USER_INFO where USER_I_NAME = ?"
//        		, token.getUsername());
//        if (user != null) {
//            if (password.equals(user.getStr("USER_I_PW"))) {
//                Session session = SecurityUtils.getSubject().getSession();
//                session.setAttribute("username", user.getStr("USER_I_NAME"));
//                return new SimpleAuthenticationInfo(user.getInt("USER_I_ID"),
//                        user.getStr("USER_I_PW"), getName());
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
    }
 
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
    	if (uai==null) {
			return null;
		}
        PrincipalWrap user = (PrincipalWrap) principals.fromRealm(getName()).iterator().next();
    	return uai.getAuthorizationByUser(user);
    }
    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }
 
    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
    
    private String getInterfaceNameByDbTarget(String username) {
    	String distributeDbName = PropKit.use("main/java/com/config.properties").get("distributeDbName");
    	String distributeTableName = PropKit.use("main/java/com/config.properties").get("distributeTableName");
    	Record r= null;
    	if (CacheKit.get("distributeDbName", distributeDbName)==null) {
    		r = Db.use(distributeDbName).
        			findFirst("select interface_name from "+distributeTableName+" where user_name = ? and deleteflag = 1",username);
    		CacheKit.put("distributeDbName", distributeDbName, r);
		}
    	r = CacheKit.get("distributeDbName", distributeDbName);
		if (r==null) {
			return null;
		} else {
			return r.getStr("interface_name");
		}
	} 

}
