package com.service;

import java.util.List;

import com.dao.Role_Menu_Rel;
import com.dao.SysMenu;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.ehcache.CacheKit;
import com.nnit.bean.DipTreeNode;
import com.toolkit.DipConstants;
import com.toolkit.DipTreeUtil;

public class DipMenuService {
	
	public List<SysMenu> getRootMenuByUser(Integer userid,String local) {
		// TODO Auto-generated method stub
		String sql = "select * from (SELECT sys_menu.*,global_menu_name.menu_name FROM localkey JOIN global_menu_name ON localkey.local_key = global_menu_name.local_key "
				+ "JOIN sys_menu ON sys_menu.menu_id = global_menu_name.menu_id WHERE localkey.local_key = '"+local.toLowerCase()+"') as foo "
				+ "where menu_id in(select menu_info_id from role_menu_rel where role_info_id in(select role_info_id from user_role_rel where user_info_id='"
				+ userid + "'))  and  parentid = 0  order by menu_order";
		List<SysMenu> sysList = SysMenu.dao.find(sql);
		return sysList;
	}

	
	public List<SysMenu> getChildMenuByUser(Integer userid,String local) {
		// TODO Auto-generated method stub
		String sql = "select * from (SELECT sys_menu.*,global_menu_name.menu_name FROM localkey JOIN global_menu_name ON localkey.local_key = global_menu_name.local_key "
				+ "JOIN sys_menu ON sys_menu.menu_id = global_menu_name.menu_id WHERE localkey.local_key = '"+local.toLowerCase()+"') as foo "
				+ "where menu_id in(select menu_info_id from role_menu_rel where role_info_id in(select role_info_id from user_role_rel where user_info_id='"
				+ userid + "'))  order by menu_order";
		List<SysMenu> sysList = SysMenu.dao.find(sql);
		return sysList;
	}

	
	public List<SysMenu> getAllRootMenu() {
		// TODO Auto-generated method stub
		String sql = "select * from sys_menu where parentid = 0 order by menu_order";
		List<SysMenu> sysList = SysMenu.dao.find(sql);
		return sysList;
	}

	
	public List<SysMenu> getAllMenu() {
		// TODO Auto-generated method stub
		String sql = "select * from sys_menu order by menu_order ";
		List<SysMenu> sysList = SysMenu.dao.find(sql);
		return sysList;
	}

	
	public List<SysMenu> getAllChildMenu() {
		// TODO Auto-generated method stub
		String sql = "select * from sys_menu where parentid > 0 order by menu_order";
		List<SysMenu> sysList = SysMenu.dao.find(sql);
		return sysList;
	}

	
	public List<SysMenu> getMenuByRole(Integer roleid) {
		// TODO Auto-generated method stub
		String sql = "select sys_menu.* from sys_menu join role_menu_rel on menu_id = menu_info_id where parentid > 0 and role_info_id ="
				+ roleid + " order by menu_order";
		List<SysMenu> menu_rel = SysMenu.dao.find(sql);
		return menu_rel;
	}

	
	public void deleteMenuRelByRole(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from role_menu_rel where role_info_id =" + id;
		Db.update(sql);
	}

	
	public void addMenuRel(Integer menu_info_id, Integer role_info_id) {
		// TODO Auto-generated method stub
		Role_Menu_Rel menu_rel = new Role_Menu_Rel().set("menu_info_id",
				menu_info_id).set("role_info_id", role_info_id);
		menu_rel.save();
	}

	
	public List<SysMenu> getThreeMenusByUrl(String menuUrl) {
		String sql = "select * from sys_menu where parentid = (select parentid from sys_menu where menu_url = '"
				+ menuUrl + "' and menu_level = 3 limit 1) order by menu_order";
		return SysMenu.dao.find(sql);
//		String cacheKey = "getThreeMenusByUrl?" + menuUrl;
//		return SysMenu.dao.findByCache(Constants.menuCache, cacheKey, sql);
	}

	
	public String getFirstMenuUrl() {
		String cacheKey = "getFirstMenu";
//		String index = CacheKit.get(Constants.menuCache, cacheKey);
		String index = null;
		if(index == null) {
			String sql = "SELECT * FROM sys_menu WHERE parentid = "
					+ "(select menu_id from sys_menu where parentid = 0 ORDER by menu_order limit 1) "
					+ "ORDER BY menu_order";
			SysMenu menu = SysMenu.dao.findFirst(sql);
			index = "/eco/main_page";
			if(menu != null && menu.getStr("menu_url") != null) {
				index = menu.getStr("menu_url");
			}
			CacheKit.put(DipConstants.menuCache, cacheKey, index);
		}
		
		return index;
	}

	
	public List<DipTreeNode> getMenuTree() {
//		List<TreeNode> nodeList = CacheKit.get(Constants.menuCache, "getMenuTree");
		List<DipTreeNode> nodeList = null;
		if(nodeList == null) {
			List<SysMenu> list = getAllMenu();
			DipTreeUtil util = new DipTreeUtil(list);
			nodeList = util.getAllNode();
			CacheKit.put(DipConstants.menuCache, "getMenuTree", nodeList);
		}
		return nodeList;
	}

	
	/**
	 * 判断指定菜单是否被使用
	 * @param menuId
	 * @return
	 */
	private boolean isUsed(int menuId) {
		String sql = "select * from role_menu_rel where deleteflag = 1 and menu_info_id = " + menuId;
		Role_Menu_Rel rmr = Role_Menu_Rel.dao.findFirst(sql);
		if(rmr == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 指定父菜单下是否已包含sort序号
	 * @param sort
	 * @param parentId
	 * @return
	 */
	private boolean hasSort(int sort, int parentId) {
		String sql = "select * from sys_menu where parentid = " + parentId
				+ " and menu_order = " + sort;
		SysMenu menu = SysMenu.dao.findFirst(sql);
		if(menu == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 根据父id查询子菜单的最大排序号
	 * @param parentId
	 * @return
	 */
//	private int getMaxSortByPId(int parentId) {
//		String sql = "select max(menu_sort) maxSort from sys_menu where parentid = " + parentId;
//		return SysMenu.dao.findFirst(sql).getLong("maxSort").intValue();
//	}

}
