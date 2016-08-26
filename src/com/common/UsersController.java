package com.common;

import java.util.List;

import com.dao.Roles;
import com.dao.User_Role_Rel;
import com.dao.Users;
import com.eco.service.ECORoleService;
import com.eco.service.ECOUsersService;
import com.jfinal.core.Controller;
import com.nnit.eco.common.RoleInterface;
import com.nnit.eco.common.UserInterface;
import com.toolkit.Md5Kit;

public class UsersController extends Controller {

	UserInterface usersService = new ECOUsersService();
	RoleInterface roleService = new ECORoleService();

	public void addUser() {
		String userName = getPara("userName");
		String userNick = getPara("userNick");
		String password = "";
		try {
			password = Md5Kit.getMd5("123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = usersService.saveUser(userName,userNick,password);  
		renderText(result);
	}

	public void editRoleRel() {
		Integer id = getParaToInt("id");
		String ids[] = getPara("checkids").split(",");
		usersService.deleteRoleRelByUserId(id);
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=""){
			usersService.addRoleUserRel(Integer.parseInt(ids[i]), id);
			roleService.addUserRoleRela(Integer.parseInt(ids[i]), id);
			}
		}
		renderText("true");
	}

	public void getUserList() {
		List<Users> userList = usersService.getUsers();
		List<Roles> roleList = roleService.getRoles();
		setAttr("userList", userList);
		setAttr("roleList", roleList);
		render("/emp2/user/userForm.html");
		// renderJson(userList);
	}

	public void getUserRel() {
		String id = getPara("id");
		List<User_Role_Rel> checkList = usersService.getUserRoleRelaById(id);
		int[] array = new int[checkList.size()];
		for (int i = 0; i < checkList.size(); i++) {
			array[i] = checkList.get(i).get("role_info_id");
		}
		System.out.println(checkList);
		setAttr("checkList", checkList);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray
				.fromObject(array);
		String result = jsonArray.toString();
		renderText(result);
	}

	public void updateUser() {
		Integer id = getParaToInt("id");
		String userName = getPara("userName");
		String userNick = getPara("userNick");
		String result = usersService.updateUser(id, userName, userNick);
		renderText(result);
	}

	public void delUser() {
		Integer id = getParaToInt("id");
		String result = usersService.delUser(id);
		renderText(result);
	}
	public void resetUser() {
		Integer id = getParaToInt("id");
		String result = usersService.resetUser(id);
		renderText(result);
	}
}
