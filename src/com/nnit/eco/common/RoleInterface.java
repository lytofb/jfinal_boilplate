package com.nnit.eco.common;

import java.util.List;

import com.dao.Roles;
import com.dao.User_Role_Rel;

public interface RoleInterface {

	public abstract List<Roles> getRoles();

	public abstract List<Roles> userRelation(Integer id);

	public abstract List<Roles> permissionRelation(Integer id);

	public abstract List<Roles> schemeRelation(Integer id);

	public abstract Roles getRoleByID(int id);

	public abstract void addUserRoleRela(Integer user_info_id,
			Integer role_info_id);

	public abstract User_Role_Rel getUserRoleRelaById(Integer id, Integer roleId);

	public abstract Long getCountRoleUsers(Integer id);

	public abstract void deleteUserRelByRoleId(User_Role_Rel urr);

	public abstract List<User_Role_Rel> getUserRoleRelIds(Integer id);

	public abstract String saveRole(String roleName, String roleNick);

	public abstract boolean checkRoleName(String roleName);

	public abstract boolean checkRoleNick(String roleNick);

	public abstract String delRole(Integer id);

	public abstract String updateRole(Integer id, String roleNick);

	public abstract void deletePermissionRelByRoidId(Integer id);

	public abstract void deleteUserRelByRoleId(Integer id);

	public abstract void addPermissionRoleRela(Integer ids,
			Integer role_info_id);

	public abstract void deleteDataSourceRelByRole(Integer id);

	public abstract void addDataSourceRel(Integer scheme_info_id,
			Integer role_info_id);

}