package com.toolkit;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.nnit.bean.DatatableServerSideBean;

public class DatatableServerKit {

	public static DatatableServerSideBean dealWithDatatableRequest(HttpServletRequest request) {
		Enumeration<String> paraNames = request.getParameterNames();
		DatatableServerSideBean serversideBean = new DatatableServerSideBean();
		while (paraNames.hasMoreElements()) {
			String key = (String) paraNames.nextElement();
			
			String regex = "^columns\\[(\\d+)\\]\\[(\\w+)\\]$";
			String searchcolumnRegex = "^columns\\[(\\d+)\\]\\[(\\w+)\\]\\[(\\w+)\\]$";
			String orderRegex = "^order\\[(\\d+)\\]\\[(\\w+)\\]$";
			String searchRegex = "^search\\[(\\w+)\\]$";
			Boolean matchregexFlag = Pattern.matches(regex, key);
			Boolean matchsearchcolumnRegexFlag = Pattern.matches(searchcolumnRegex, key);
			Boolean orderRegexFlag = Pattern.matches(orderRegex, key);
			Boolean searchRegexFlag = Pattern.matches(searchRegex, key);
			
			if (matchregexFlag) {
				Pattern p = Pattern.compile(regex);  
		        Matcher m = p.matcher(key);
		        m.find();
		        Integer index = Integer.valueOf(m.group(1));
		        String property = m.group(2);
		        String value = request.getParameter(key);
		        TreeMap<Integer, HashMap<String, Object>> columnsMap = serversideBean.getColumns();
		        if (!columnsMap.containsKey(index)) {
					columnsMap.put(index, new HashMap<String, Object>());
				}
				HashMap<String, Object> columns = columnsMap.get(index);
				columns.put(property, value);
				
			} else if(matchsearchcolumnRegexFlag){
				Pattern p = Pattern.compile(searchcolumnRegex);  
		        Matcher m = p.matcher(key);
		        m.find();
		        Integer index = Integer.valueOf(m.group(1));
		        String property = m.group(2);
		        String secondProperty = m.group(3);
		        String value = request.getParameter(key);
		        TreeMap<Integer, HashMap<String, Object>> columnsMap = serversideBean.getColumns();
		        if (!columnsMap.containsKey(index)) {
					columnsMap.put(index, new HashMap<String, Object>());
				}
				HashMap<String, Object> columns = columnsMap.get(index);
				if (!columns.containsKey(property)) {
					columns.put(property, new  HashMap<String, Object>());
				}
				HashMap<String, Object> secondMap = (HashMap<String, Object>) columns.get(property);
				secondMap.put(secondProperty, value);
			} else if(orderRegexFlag){
				Pattern p = Pattern.compile(orderRegex);  
		        Matcher m = p.matcher(key);
		        m.find();
		        Integer index = Integer.valueOf(m.group(1));
		        String property = m.group(2);
		        String value = request.getParameter(key);
		        TreeMap<Integer, HashMap<String, Object>> orderMap = serversideBean.getOrder();
		        if (!orderMap.containsKey(index)) {
		        	orderMap.put(index, new HashMap<String, Object>());
				}
				HashMap<String, Object> order = orderMap.get(index);
				order.put(property, value);
				
			} else if(searchRegexFlag){
				Pattern p = Pattern.compile(searchRegex);  
		        Matcher m = p.matcher(key);
		        m.find();
		        String property = m.group(1);
		        String value = request.getParameter(key);
		        TreeMap<String,Object> searchMap = serversideBean.getSearch();
		        searchMap.put(property, value);
			}
		}
		return serversideBean;
	}
}
