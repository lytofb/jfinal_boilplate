package main.java.com.nnit.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TitleInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		String actionKey = inv.getActionKey();
		String queryString = inv.getController().getRequest().getQueryString();
		
		String url = actionKey;
		if (queryString!=null) {
			url = url+"?"+queryString;
		}
		Record record = Db.findFirstByCache("pagetitle", url, "select menu_name from sys_menu where menu_url = ?",url);
		if (record!=null) {
			inv.getController().setAttr("mvalue", record.getStr("menu_name"));
		}
		inv.invoke();
	}

}
