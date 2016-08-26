package com.toolkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRuntime {
	
	public static void main(String[] args) throws Exception {
		TestRuntime tr = new TestRuntime();
//		tr.testFormat();
//		tr.deleteChart();
//		String command = "python3 /home/dev/script_dev/TestReturn.py";
		String command = "python C:\\Users\\NEC\\PycharmProjects\\Snippet\\TimerScripts\\TestReturn.py 1";
		System.out.println(tr.testRuntime(command));
	}
	
	public String testRuntime(String command) throws Exception {
		String system = System.getProperty("os.name");
		Boolean isWindows = system.toLowerCase().indexOf("windows")>=0;
		Process process = Runtime.getRuntime().exec(command);
		String encode = isWindows?"GBK":"utf-8";
		BufferedReader reader = 
                new BufferedReader(new InputStreamReader(process.getInputStream(),encode));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ( (line = reader.readLine()) != null) {
		   builder.append(line);
		   builder.append(System.getProperty("line.separator"));
		}
		String result = builder.toString();
		if (result.length()>2) {
			result = result.substring(0,result.length()-2);
		} else {
			throw new Exception("null return results");
		}
		return result;
	}
	
	private void testFormat() {
		String sql = "select m.m_name from m_member as m,m_source_element as e where "
				+ "m.deleteflag = 1 and m.m_element_id = e.id and e.field=\'%s\' and e.source_id = %d";
		sql = sql.format(sql, "sub_category",1);
		System.out.println(sql);
	}
	
	private void deleteChart() {
		StringBuffer sb = new StringBuffer("uuid");
		sb.delete(sb.length()-2, sb.length());
		System.out.println(sb.toString());
	}

}
