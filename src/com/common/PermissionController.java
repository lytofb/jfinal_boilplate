package com.common;


import java.util.List;

import com.dao.Permission;
import com.eco.service.ECOPermissionService;
import com.jfinal.core.Controller;
import com.nnit.eco.common.PermissionInterface;

public class PermissionController extends Controller{
	
	PermissionInterface permissionService = new ECOPermissionService();
	
	public void getPermissionList(){
		List<Permission> perimissionList =permissionService.getPermission();
		
		for(int i = 0 ; i< perimissionList.size();i++){
			System.out.println(perimissionList.get(i).getStr("permission_name"));
		}
		setAttr("perimissionList", perimissionList);
		render("/emp2/user/userForm.html");
	}

}
