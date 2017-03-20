package main.java.com.toolkit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {

		System.out.print(1472700191535L%(3600*24*1000));
		System.out.print(1472700191535L-1472700191535L%(3600*24*1000));
		try {
			new SimpleDateFormat("mm:ss").getDateInstance().parse("14:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if("0123456789".matches("\\d*")){
			System.out.println(111);
		}

		if("000".matches("\\d*")){
			System.out.println(222);
		}

		if("15��59".replaceAll("��",":").matches("((1|0?)[0-9]|2[0-3]):([0-5][0-9])")){
			System.out.println(333);
		}

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
