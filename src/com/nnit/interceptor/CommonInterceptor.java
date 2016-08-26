package com.nnit.interceptor;

import java.util.HashMap;

import org.apache.shiro.SecurityUtils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

public class CommonInterceptor implements Interceptor {
	
	private HashMap<String, String> titleMap = new HashMap<String, String>();
	private Prop titleProp = PropKit.use("propTitleConfig.txt");
	
	public CommonInterceptor() {
		// TODO Auto-generated constructor stub
		titleMap.put("main_page", "零售视图");
		titleMap.put("map_page", "零售视图");
		titleMap.put("dataFullList", "零售列表");
		titleMap.put("cwtp", "零售环比");
		titleMap.put("formattedForm", "零售概况");
		titleMap.put("energyForm", "能源列表");
		titleMap.put("userForm", "用户列表");
		titleMap.put("roleForm", "角色列表");
		titleMap.put("getUserList", "用户列表");
		titleMap.put("getRoleList", "角色列表");
		titleMap.put("getUserList", "权限列表");
		titleMap.put("getUserRel", "角色用户关联");
		titleMap.put("editUserRel", "更改角色关联");
		titleMap.put("getTemplateList", "模板列表");
		titleMap.put("amdFile", "空白");
	 //	titleMap.put("addUser", "");
	}

	@Override
	public void intercept(Invocation ai) {
		// TODO Auto-generated method stub
		ai.getController().setAttr("mvalue", titleProp.get(ai.getMethodName()));
		Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
		ai.getController().setAttr("userid", userId);
//		ai.getController().setAttr("bundle",I18N.getResourceBundleModel(ai.getController().getRequest().getLocale()));
		ai.invoke();
	}

}
