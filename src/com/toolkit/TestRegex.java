package com.toolkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
		String regex = "^columns\\[(\\d+)\\]\\[(\\w+)\\]$";
		Boolean matchFlag = Pattern.matches(regex, "columns[0][searchable]");
//		Boolean matchFlag = Pattern.matches("^columns\\[\\d+\\]\\[\\w+\\]$", "columns[0][searchable]");
		Pattern p = Pattern.compile(regex);  
        Matcher m = p.matcher("columns[0][searchable]");
        while(m.find()){
        	System.out.println(m.group(0));
        	System.out.println(m.group(1));
        	System.out.println(m.group(2));
        }
		System.out.println(matchFlag);
	}
}
