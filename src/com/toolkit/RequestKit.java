package com.toolkit;

import javax.servlet.http.HttpServletRequest;

public class RequestKit {

	public static Boolean isAjax(HttpServletRequest request) {
		if (request==null) {
			return false;
		}
		String ajaxRequest = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(ajaxRequest)) {
			return true;
		}
    	return false;
	}
}
