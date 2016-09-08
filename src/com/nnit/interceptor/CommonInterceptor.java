package com.nnit.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	}

	@Override
	public void intercept(Invocation ai) {
		String actionKey = ai.getActionKey();
		String[] keys = actionKey.split("/");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			if (!keys[i].equals("")){
				sb.append(keys[i]);
				sb.append(".");
			}
		}
		if (sb.length()==0){
			sb.append(".");
		}
		String sbresult = sb.substring(0,sb.length()-1);
//		ai.getController().setAttr("placeholder", titleProp.get(ai.getMethodName(),"洗洗车"));
		Map<String,String[]> paramMap = ai.getController().getParaMap();
		Set<String> urlparamkeys = paramMap.keySet();
		StringBuilder urlparamsb = new StringBuilder();
		for(String key:urlparamkeys){
			urlparamsb.append(key).append("=").append(paramMap.get(key)[0]);
			urlparamsb.append("&");
		}
		if(urlparamsb.length()==0){
			urlparamsb.append("&");
		}
		String urlParas = urlparamsb.substring(0,urlparamsb.length()-1);
		ai.getController().setAttr("placeholder", titleProp.get(sbresult,"仪表盘"));
		ai.getController().setAttr("actionkey", sbresult);
		Long userId = (Long) SecurityUtils.getSubject().getSession().getAttribute("userId");
		ai.getController().setAttr("userid", userId);
		ai.getController().setAttr("_urlParas",urlParas);
//		ai.getController().setAttr("bundle",I18N.getResourceBundleModel(ai.getController().getRequest().getLocale()));
		ai.invoke();
	}

}
