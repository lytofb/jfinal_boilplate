package main.java.com.ext;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.jfinal.plugin.IPlugin;

public class TimerPlugin implements IPlugin {
	
	private HashMap<String, Integer> clazzInterMap = new HashMap<String, Integer>();
	
	/**
	 * @param clazz 定时任务类
	 * @param intervalSecond 定时执行时间间隔
	 */
	public void addClass(Class clazz,Integer intervalSecond) {
		clazzInterMap.put(clazz.getName(), intervalSecond);
	}

	public boolean start() {
		try {
			for (String clazzName : clazzInterMap.keySet()) {
				Integer interval = clazzInterMap.get(clazzName);
				Class clazz;
				try {
					clazz = Class.forName(clazzName);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				try {
					clazz.getConstructor(Integer.class).newInstance(interval);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	public boolean stop() {
		// TODO Auto-generated method stub
		return true;
	}

}
