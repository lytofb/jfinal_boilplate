package com.nnit.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.nnit.exception.ServiceException;
import com.toolkit.DipI18nTool;

public class ExceptionInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation ai) {
		try {
			ai.invoke();
		} catch (ServiceException e) {
			Controller controller = ai.getController();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("errorCode", e.getErrorCode());
			map.put("errorMsg", DipI18nTool.getError(controller, e.getErrorCode()));
			
			if(isAjax(controller.getRequest())) {
				controller.renderJson(map);
			} else {
				controller.setAttrs(map);
			}
		} catch (Exception e) {
			Controller controller = ai.getController();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("errorCode", "");
			map.put("errorMsg", e.getMessage());
			
			if(isAjax(controller.getRequest())) {
				controller.renderJson(map);
			} else {
				controller.setAttrs(map);
			}
		}
	}
	
	/**
	 * 判断建当前请求是否为ajax请求
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
    	String ajaxRequest = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(ajaxRequest)) {
			return true;
		}
    	return false;
    }

}
