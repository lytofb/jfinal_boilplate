package main.java.com.toolkit;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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

	public static String generateuuid(){
		UUID uuid = UUID.randomUUID();
		String randomUUIDString = uuid.toString();
		return randomUUIDString;
	}
}
