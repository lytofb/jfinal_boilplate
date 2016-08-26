package com.common;

import java.util.List;

import com.dao.Roles;
import com.dao.Template;
import com.dao.Users;
import com.eco.service.ECOTemplateService;
import com.eco.service.ECOUsersService;
import com.jfinal.core.Controller;
import com.nnit.eco.common.TemplateInterface;
import com.nnit.eco.common.UserInterface;

public class TemplateController extends Controller {
	
	UserInterface userService = new ECOUsersService();
	TemplateInterface templateService = new ECOTemplateService();
	public void getTemplateList() {
		List<Template> templateList =templateService.getTemplate();
		List<Users> userList = userService.getUsers();
		setAttr("templateList", templateList);
		setAttr("userList", userList);
		render("/emp2/template/template.html");
		// renderJson(userList);
	}
	public void getTemplateUser(){
		Integer id =getParaToInt("id");
		//查询所有角色和用户之间关联
		List<Template> checkList = templateService.userRelation(id);
		int [] array=new int[checkList.size()];
		for(int i = 0 ; i <checkList.size() ; i ++){
			array[i]=checkList.get(i).get("user_info_id"); 
		}
		setAttr("checkList", checkList);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(array);
		String result=jsonArray.toString();
		renderText(result);
	}
	
	public void delTemplate() {
		Integer id = getParaToInt("id");
		String result = templateService.delTemplate(id);
		renderText(result);
	}
	
	public void editTemplateRel(){
		Integer id =getParaToInt("id");
		String ids[] = getPara("checkids").split(",");
		templateService.delTemplateRel(id);
		for(int i =0 ; i < ids.length ; i++){
			if(ids[i]!=""){
			templateService.addUserRel(Integer.parseInt(ids[i]),id);
			}
		}
		renderText("true");
	}
	

}
