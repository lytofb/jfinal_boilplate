package com.toolkit;

import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.kit.StrKit;

public class DipI18nTool {
	public static String localePara = "_locale";
	private static String BaseName = "i18n_error";
	
	public static String get(Controller c, String key) {
		String local = DipI18nTool.getLocal(c);
		Res res = I18n.use();
		if (local!=null) {
			res = I18n.use(local);
		}
		return res.get(key);
	}
	
	public static String getError(Controller c, String key) {
		String local = getLocal(c);
		Res res = I18n.use(BaseName);
		if (local!=null) {
			res = I18n.use(BaseName, local);
		}
		return res.get(key);
	}
	
	public static String getLocal(Controller c) {
		String local = c.getPara(localePara);
		if(StrKit.isBlank(local)) {							// get locale from cookie and use the default locale if it is null
			local = c.getCookie(localePara);
			if (StrKit.isBlank(local)) {
//				local = I18n.defaultLocale;
				Res res = I18n.use();
				local = res.get("common.local");
			}
		}
		return local;
	}
}
