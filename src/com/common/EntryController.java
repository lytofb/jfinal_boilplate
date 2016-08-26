package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.ehcache.CacheKit;
import com.nnit.interceptor.TitleInterceptor;
import com.toolkit.HttpKitExt;
public class EntryController extends Controller {
	public static Logger logger = Logger.getLogger(EntryController.class);
//	private String base_path = "http://172.28.217.44:8080/spring/";
//	private String base_path = "http://113.207.68.9:38080/spring/";
//	private String base_path = "http://172.28.217.74:8080/COMMON_SERVICE/";
//	private String base_path = "http://172.28.217.66:8080/service_0710/";
	private String base_path = PropKit.get("baseUrl");

	@RequiresRoles("super")
	@Before(TitleInterceptor.class)
	public void main_page() {
		keepPara("schema_seq");
		keepPara("template_id");
		keepPara("schema_id");
	}
	
	@Before(TitleInterceptor.class)
	public void testTree() {
	}
	
	@Before(TitleInterceptor.class)
	public void testnewTree() {
	}
	@Before(TitleInterceptor.class)
	public void amdFile() {
		
	}
	
	@Before(TitleInterceptor.class)
	public void energyForm() {
		keepPara("schema_seq");
		keepPara("template_id");
	}
	
	@Before(TitleInterceptor.class)
	public void cwtp() {
		keepPara("schema_seq");
	}
	
	@Before(TitleInterceptor.class)
	public void formattedForm() {
		keepPara("schema_seq");
	}
	
	@Before(TitleInterceptor.class)
	public void basic_table() {
	}
	
	@Before(TitleInterceptor.class)
	public void blog_details() {
		
	}
	
	@Before(TitleInterceptor.class)
	public void dataFullList() {
	}

	public void clearCache() {
		postAndCacheInterface(base_path,"clearCache","clearCache");
	}
	
	public void initdatasource() {
		postAndCacheInterface(base_path,"initdatasource","initdatasource");
	}
	
	public void getDataForDraw() {
		postAndCacheInterface(base_path,"getDataForDraw","getDataForDraw");
	}
	
	public void getCwtpData() {
		postAndCacheInterface(base_path,"getDataForIncrease","getDataForIncrease");
	}
	
	public void getSchema() {
		postAndCacheInterface(base_path,"getUserSchema","getUserSchema");
	}
	
	public void getMemberRoot() {
		postAndCacheInterface(base_path,"getMemberRoot","getMemberRoot");
	}
	
	public void getMemberMeta() {
		postAndCacheInterface(base_path,"getMemberMetaData","getMemberMetaData");
	}
	
	public void getGroupDataDetail() {
		postAndCacheInterface(base_path,"getGroupDataDetail","getGroupDataDetail");
	}
	
	public void getTemplateRecord() {
		postAndCacheInterface(base_path,"getTemplateRecord","getTemplateRecord");
	}
	
	@RequiresPermissions("testRedirect")
	public void getChartData() {
		postAndCacheInterface(base_path,"getData","chartData");
	}
	
	public void getTemplate(){
		String result = postToInterface(base_path, "getTemplate");
		renderText(result);
	}
	
	public void saveTemplate() {
		String result = postToInterface(base_path, "saveTemplate");
		renderText(result);
	}
	
	public void getTemplateData() {
		String result = postToInterface(base_path, "getTemplateData");
		renderText(result);
	}
	
	public void getFormData() {
		postAndCacheInterface(base_path,"getFormData","formData");
	}
	
	private String postToInterface(String target,String targetBasePath) {
		HashMap<String, String> paraMap = new HashMap<String, String>();
		Enumeration<String> names = getRequest().getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			sb.append(name+"="+getRequest().getParameter(name)).append("&");
//			paraMap.put(name, getRequest().getParameter(name));
		}
//		String result = HttpKitExt.post(target+targetBasePath, paraMap,"",99999999);
		String result = HttpKitExt.post(target+targetBasePath, null,sb.toString(),99999999);
		return result;
	}
	
	private void postAndCacheInterface(String target,String targetBasePath,String cacheName) {
		HashMap<String, String> paraMap = new HashMap<String, String>();
		HttpServletRequest request = getRequest();
		Enumeration<String> names = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String param = "";
			try {
				param = URLEncoder.encode(getRequest().getParameter(name), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sb.append(name+"="+param).append("&");
			paraMap.put(name, getRequest().getParameter(name));
		}
//		if(CacheKit.get(cacheName, paraMap)==null){
			String result = HttpKitExt.post(target+targetBasePath, null,sb.toString(),99999999);
//			String result = HttpKitExt.post(target+targetBasePath, paraMap,"",99999999);
//			CacheKit.put(cacheName, paraMap, result);
			renderText(result);
//		} else {
//			renderText((String) CacheKit.get(cacheName, paraMap));
//		}
		
	}
	
	public void clearCacheInterface() {
		String cacheName = getPara();
		if (cacheName!=null) {
			CacheKit.removeAll(cacheName);
		}
	}
}
