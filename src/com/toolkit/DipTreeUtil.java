package com.toolkit;


import java.util.ArrayList;
import java.util.List;

import com.dao.SysMenu;
import com.nnit.bean.DipTreeNode;


public class DipTreeUtil {
//	private final static String MENU_ID = "menu_";
//	
//	private final static String BTN_ID = "btn_";
	
	List<SysMenu> rootMenus;
	List<SysMenu> childMenus;
	List<SysMenu> allMenus;
	
	public DipTreeUtil(List<SysMenu> rootMenus,List<SysMenu> childMenus){
		this.rootMenus = rootMenus;
		this.childMenus = childMenus;
	}  
	public DipTreeUtil(List<SysMenu> allMenu){
		this.allMenus = allMenu ;
	}  
	
	public List<DipTreeNode> getTreeNode(){
		return getRootNodes();
	}
	
	public List<DipTreeNode> getAllNode(){
		return getAllNodes();
	}
	
	public List<DipTreeNode> getAllAndRoot() {
		List<DipTreeNode> list = new ArrayList<DipTreeNode>();
		DipTreeNode please = new DipTreeNode();
		please.setId(-1);
		please.setName("--请选择--");
		list.add(please);
		
		DipTreeNode root = new DipTreeNode();
		root.setId(0);
		root.setName("根菜单");
		root.setOpen(true);
		list.add(root);
		
		list.addAll(getAllNodes());
		return list;
	}
	
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private DipTreeNode MenuToNode(SysMenu menu){
		if(menu == null){
			return null;
		}
		DipTreeNode node = new DipTreeNode();
		node.setId(menu.getInt("menu_id"));
		node.setName(menu.getStr("menu_name"));
		node.setUrl(menu.getStr("menu_url"));
		node.setpId(menu.getInt("parentid"));
		node.setIcon(menu.getStr("icon"));
		return node;
	}
	
	private DipTreeNode MenuToAllNode(SysMenu menu){
		if(menu == null){
			return null;
		}
		DipTreeNode node = new DipTreeNode();
		node.setId(menu.getInt("menu_id"));
		node.setName(menu.getStr("menu_name"));
		if(menu.getInt("parentid")==null){
			node.setpId(0);
			node.setOpen(true);
		}else{
			node.setpId(menu.getInt("parentid"));
			node.setOpen(true);
		}
		
		node.setChecked(false);
		return node;
	}
	
	
	private List<DipTreeNode> getAllNodes(){
		List<DipTreeNode> allNodes = new ArrayList<DipTreeNode>();
		for(SysMenu menu : allMenus){
			DipTreeNode node = MenuToAllNode(menu);
			if(node != null){
				allNodes.add(node);
			}
		}
		return allNodes;
	}
	
	private List<DipTreeNode> getRootNodes(){
		List<DipTreeNode> rootNodes = new ArrayList<DipTreeNode>();
		for(SysMenu menu : rootMenus){
			DipTreeNode node = MenuToNode(menu);
			if(node != null){
				addChlidNodes(node);
				rootNodes.add(node);
			}
		}
		return rootNodes;
	}
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private void addChlidNodes(DipTreeNode rootNode){
		List<DipTreeNode> childNodes = new ArrayList<DipTreeNode>();  
		for(SysMenu menu : childMenus){
			if(rootNode.getId()==menu.getInt("parentid")){
				DipTreeNode node = MenuToNode(menu);
				childNodes.add(node);
			}
		}
		rootNode.setChildNodes(childNodes);
	}
	
}
