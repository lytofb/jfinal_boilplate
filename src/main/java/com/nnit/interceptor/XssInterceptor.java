package main.java.com.nnit.interceptor;

import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class XssInterceptor implements Interceptor {

	private final static Whitelist user_content_filter = Whitelist.relaxed();  
	static {  
	    user_content_filter.addTags("embed","object","param","span","div");  
	    user_content_filter.addAttributes(":all", "style", "class", "id", "name");  
	    user_content_filter.addAttributes("object", "width", "height","classid","codebase");      
	    user_content_filter.addAttributes("param", "name", "value");  
	    user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");  
	}  
	public void intercept(Invocation inv) {
		Map<String, String[]> paraMap = inv.getController().getParaMap();
		Set<String> keySet = paraMap.keySet();
		for (String key : keySet) {
			String[] parames = paraMap.get(key);
			for (String para : parames) {
				if (Jsoup.isValid(para, user_content_filter)) {
					continue;
				} else {
					throw new RuntimeException("xss founded");
				}
			}
		}
		inv.invoke();
	}

}
