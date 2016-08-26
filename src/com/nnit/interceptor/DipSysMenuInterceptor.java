package com.nnit.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;

import com.dao.SysMenu;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.nnit.bean.DipTreeNode;
import com.service.DipMenuService;
import com.toolkit.DipI18nTool;
import com.toolkit.DipTreeUtil;

public class DipSysMenuInterceptor implements Interceptor {
	DipMenuService menuService = new DipMenuService();
	@Override
	public void intercept(Invocation ai) {
		// TODO Auto-generated method stub
//		RequiresRoles reqRole = ai.getMethod().getAnnotation(RequiresRoles.class);
//		if(reqRole != null) {
			Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
			if(userId != null) {
				String local = DipI18nTool.getLocal(ai.getController()); 
				List<SysMenu> rootList = menuService.getRootMenuByUser(userId,local);
				List<SysMenu> childList = menuService.getChildMenuByUser(userId,local);
				DipTreeUtil treeUtil = new DipTreeUtil(rootList,childList);
				List<DipTreeNode> lstTreeNode = treeUtil.getTreeNode();
				ai.getController().setAttr("userid", userId);
				ai.getController().setAttr("menuList", lstTreeNode);
			} else {
				ai.getController().setAttr("menuList", new ArrayList<DipTreeNode>());
			}
//		}
		
		ai.invoke();
	}

}
