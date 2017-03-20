package main.java.com.nnit.interceptor;

import org.apache.shiro.SecurityUtils;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import main.java.com.toolkit.DipI18nTool;

public class DipSysMenuInterceptor implements Interceptor {
	
//	DipMenuService menuService = new DipMenuService();
	public void intercept(Invocation ai) {
		// TODO Auto-generated method stub
//		RequiresRoles reqRole = ai.getMethod().getAnnotation(RequiresRoles.class);
//		if(reqRole != null) {
			Long userId = (Long) SecurityUtils.getSubject().getSession().getAttribute("userId");
			if(userId != null) {
				String local = DipI18nTool.getLocal(ai.getController()); 
//				List<SysMenu> rootList = menuService.getRootMenuByUser(userId,local);
//				List<SysMenu> childList = menuService.getChildMenuByUser(userId,local);
//				DipTreeUtil treeUtil = new DipTreeUtil(rootList,childList);
//				List<DipTreeNode> lstTreeNode = treeUtil.getTreeNode();
				ai.getController().setAttr("userid", userId);
//				ai.getController().setAttr("menuList", lstTreeNode);
			} else {
//				ai.getController().setAttr("menuList", new ArrayList<DipTreeNode>());
			}
//		}
		
		ai.invoke();
	}

}
