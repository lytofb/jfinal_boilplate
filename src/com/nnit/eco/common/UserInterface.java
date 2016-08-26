package com.nnit.eco.common;

import java.util.List;

import com.dao.User_Role_Rel;
import com.dao.Users;

public interface UserInterface {

	public abstract String saveUser(String userName, String userNick,
			String password);

	public abstract boolean checkUserName(String userName);

	public abstract boolean checkUserNick(String userNick);

	public abstract List<Users> getUsers();

	public abstract List<User_Role_Rel> getUserRoleRelaById(String id);

	public abstract String delUser(Integer id);

	public abstract String updateUser(Integer id, String userName,
			String userNick);

	public abstract void deleteRoleRelByUserId(Integer id);

	public abstract void addRoleUserRel(Integer role_info_id, Integer user_info_id);

	public abstract String resetUser(Integer id);

}