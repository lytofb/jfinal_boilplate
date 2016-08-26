package com.common;


import java.util.List;

import com.dao.DataSource;
import com.dao.Permission;
import com.dao.Roles;
import com.dao.Users;
import com.eco.service.ECODataSourceService;
import com.eco.service.ECOPermissionService;
import com.eco.service.ECORoleService;
import com.eco.service.ECOUsersService;
import com.jfinal.core.Controller;
import com.nnit.eco.common.DataSourceInterface;
import com.nnit.eco.common.PermissionInterface;
import com.nnit.eco.common.RoleInterface;
import com.nnit.eco.common.UserInterface;

public class RoleController extends Controller{
	
	RoleInterface roleService = new ECORoleService();
	UserInterface userService = new ECOUsersService();
	PermissionInterface permissionService = new ECOPermissionService();
	DataSourceInterface dataSourceService = new ECODataSourceService();
	public void getRoleList() {
		List<Roles> roleList = roleService.getRoles();
		List<Users> userList = userService.getUsers();
		List<Permission> permissionList = permissionService.getPermission();
		List<DataSource> dataSourceList = dataSourceService.getDataSources();
		setAttr("userList", userList);
		setAttr("roleList", roleList);
		setAttr("permissionList", permissionList);
		setAttr("dataSourceList", dataSourceList);
		
		render("/emp2/role/roleForm.html");
	}

	public void getUserRel(){
		Integer id =getParaToInt("id");
		//查询所有角色和用户之间关联
		List<Roles> checkList = roleService.userRelation(id);
		int [] array=new int[checkList.size()];
		for(int i = 0 ; i <checkList.size() ; i ++){
			array[i]=checkList.get(i).get("user_info_id"); 
		}
		setAttr("checkList", checkList);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(array);
		String result=jsonArray.toString();
		renderText(result);
	}
	
	public void getPermissionRel(){
		Integer id =getParaToInt("id");
		List<Roles> checkList = roleService.permissionRelation(id);
		int [] array=new int[checkList.size()];
		for(int i = 0 ; i <checkList.size() ; i ++){
			array[i]=checkList.get(i).get("permission_info_id");
		} 
		setAttr("checkList", checkList);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(array);
		String result=jsonArray.toString();
		renderText(result);
	}
	public void getSchemeRel(){ 
		Integer id =getParaToInt("id");
		List<Roles> checkList = roleService.schemeRelation(id);
		int [] array=new int[checkList.size()];
		for(int i = 0 ; i <checkList.size() ; i ++){
			array[i]=checkList.get(i).get("scheme_info_id");
		}
		System.out.println(checkList);
		setAttr("checkList", checkList);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(array);
		System.out.println(jsonArray);
		String result=jsonArray.toString();
		renderText(result);
	}
	
	public void editUserRel(){
		Integer id =getParaToInt("id");
		String ids[] = getPara("checkids").split(",");
		roleService.deleteUserRelByRoleId(id);
		for(int i =0 ; i < ids.length ; i++){
			if(ids[i]!=""){
			roleService.addUserRoleRela(Integer.parseInt(ids[i]),id);
			}
		}
		renderText("true");
	}
	
	public void editPermissionRel(){
		Integer id =getParaToInt("id");
		String ids[] = getPara("checkids").split(",");
		roleService.deletePermissionRelByRoidId(id);
		for(int i =0 ; i < ids.length ; i++){
			if(ids[i]!=""){
			roleService.addPermissionRoleRela(Integer.parseInt(ids[i]),id);
			}
		}
		renderText("true");
	}
	
	public void editDataSourceRel(){
		Integer id =getParaToInt("id");
		String ids[] = getPara("checkids").split(",");
		roleService.deleteDataSourceRelByRole(id);
		for(int i =0 ; i < ids.length ; i++){
			if(ids[i]!=""){
			roleService.addDataSourceRel(Integer.parseInt(ids[i]),id);
			}
		}
		renderText("true");
	}
	
	public void addRole(){
		String roleName = getPara("roleName");
		String roleNick = getPara("roleNick");
	    String result= roleService.saveRole(roleName,roleNick);  
	    renderText(result);
	}
	
	public void delRole(){
		Integer id =getParaToInt("id");
	    String result= roleService.delRole(id);
		renderText(result);
	}
	
	public void updateRole() {
		Integer id =getParaToInt("id");
		String roleNick = getPara("roleNick");
	    String result= roleService.updateRole(id, roleNick);
		renderText(result);
	}

}
