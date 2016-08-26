package com.service;


import java.util.List;

import com.common.RoleController;
import com.dao.Role_Permission_Rel;
import com.dao.Role_Scheme_Rel;
import com.dao.Roles;
import com.dao.User_Role_Rel;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.nnit.eco.common.RoleInterface;

public class RoleService implements RoleInterface {
	private static Logger logger = Logger.getLogger(RoleController.class);

	/* (non-Javadoc)
	 * @see com.service.RoleInterface#getRoles()
	 */
	@Override
	public List<Roles> getRoles() {
		String sql = "select * from role_info where deleteflag = 1";
		List<Roles> roles = Roles.dao.find(sql);
		return roles;
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#userRelation(java.lang.String)
	 */
	@Override
	public List<Roles> userRelation(Integer id){
		String sql = "select urr.user_info_id ,r.role_name from user_role_rel urr join user_info " 
				+"u on urr.user_info_id=u.id join role_info r on urr.role_info_id=r.id where role_info_id=? and urr.deleteflag=1 ";
		List<Roles> roles = Roles.dao.find(sql,id);
		return roles;
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#permissionRelation(java.lang.String)
	 */
	@Override
	public List<Roles> permissionRelation(Integer id){
		String sql ="select permission_info_id,permission_name from role_permission_rel rpr join permission_info p "
				+ "on rpr.permission_info_id =p.id where role_info_id=? and rpr.deleteflag=1;";
		List<Roles> roles = Roles.dao.find(sql,id);
		return roles;
	}
	
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#schemeRelation(java.lang.String)
	 */
	@Override
	public List<Roles> schemeRelation(Integer id) {
		// TODO Auto-generated method stub
		String sql ="select scheme_info_id,md.name from role_scheme_rel rsr join m_datasource md "
				+ "on rsr.scheme_info_id =md.id where role_info_id=? and rsr.deleteflag=1;";
		List<Roles> roles = Roles.dao.find(sql,id);
		return roles;
	}
	
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#getRoleByID(int)
	 */
	@Override
	public Roles getRoleByID(int id){
		return new Roles().dao.findById(id);
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#addUserRoleRela(java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserRoleRela(Integer user_info_id,Integer role_info_id) {
		// TODO Auto-generated method stub
		new User_Role_Rel().set("user_info_id", user_info_id).set("role_info_id", role_info_id).save();
		 
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#getUserRoleRelaById(java.lang.String, java.lang.String)
	 */
	@Override
	public User_Role_Rel getUserRoleRelaById(Integer id,Integer roleId) {
		// TODO Auto-generated method stub
		System.out.println(id+"----"+roleId);
		String sql="select * from user_role_rel where user_info_id="+id+" and role_info_id="+roleId;
		User_Role_Rel urr =  User_Role_Rel.dao.findFirst(sql);
		System.out.println(urr==null);
		return urr;
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#getCountRoleUsers(java.lang.String)
	 */
	@Override
	public Long getCountRoleUsers(Integer id){
		String sql ="select count(*)  countUsers from user_role_rel rel where role_info_id="+id;
		return User_Role_Rel.dao.findFirst(sql).get("countUsers");
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#deleteUserRelByRoleId(com.dao.User_Role_Rel)
	 */
	@Override
	public void deleteUserRelByRoleId(User_Role_Rel urr) {
		// TODO Auto-generated method stub
		User_Role_Rel.dao.deleteById(urr.get("id"));
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#getUserRoleRelIds(java.lang.String)
	 */
	@Override
	public List<User_Role_Rel> getUserRoleRelIds(Integer id) {
		// TODO Auto-generated method stub
		return User_Role_Rel.dao.search("role_info_id", id);
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#saveRole(java.lang.String, java.lang.String)
	 */
	@Override
	public String saveRole(String roleName, String roleNick) {
		// TODO Auto-generated method stub
		 String result ="" ;
		   if(checkRoleName(roleName)==true){
			   if(checkRoleNick(roleNick)==true){
				   new Roles().set("role_name", roleName).set("role_nickname",roleNick).save();
				   Roles role= new Roles().searchFirst("role_name", roleName);
				   result = role.get("id").toString();
			   }else{
				   result="roleNickExist";
			   }
		   }else{
			   result="roleNameExist";
		   } 
	       return result;
	}
	
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#checkRoleName(java.lang.String)
	 */
	@Override
	public boolean checkRoleName(String roleName){
		Boolean result = false;
		Roles roles = new Roles().searchFirst("role_name", roleName);
		if(roles==null){
			result=true;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#checkRoleNick(java.lang.String)
	 */
	@Override
	public boolean checkRoleNick(String roleNick){
		Boolean result = false;
		if(Roles.dao.searchFirst("role_nickname", roleNick)==null){
			result=true;
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#delRole(java.lang.String)
	 */
	@Override
	public String delRole(Integer id) {
		// TODO Auto-generated method stub
		String result=null;
	    if(Roles.dao.deleteById(id)==true){
	    	result="删除成功";
	    }else{
	    	result="删除失败";
	    }
		return result;  
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#updateRole(java.lang.String, java.lang.String)
	 */
	@Override
	public String updateRole(Integer id,String roleNick) {
		// TODO Auto-generated method stub
		String result ="" ;
		   if(checkRoleNick(roleNick)==true){
			   Roles.dao.findById(id).set("role_nickname", roleNick).update();
			   Roles role = new Roles().searchFirst("role_nickname", roleNick);
			   result = role.get("id").toString(); 
		   }else{
			   result="roleNickExist";
		   }
		   return result;
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#deletePermissionRelByRoidId(java.lang.String)
	 */
	@Override
	public void deletePermissionRelByRoidId(Integer id) {
		// TODO Auto-generated method stub
		String sql="update role_permission_rel set deleteflag=0 where role_info_id=?";
		Db.update(sql,id);
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#deleteUserRelByRoleId(java.lang.String)
	 */
	@Override
	public void deleteUserRelByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String sql="update user_role_rel set deleteflag=0 where role_info_id=?";
		Db.update(sql,id);
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#addPermissionRoleRela(java.lang.String, java.lang.String)
	 */
	@Override
	public void addPermissionRoleRela(Integer permission_info_id, Integer role_info_id) {
		// TODO Auto-generated method stub
		new Role_Permission_Rel().set("permission_info_id", permission_info_id).set("role_info_id", role_info_id).save();
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#deleteDataSourceRelByRole(java.lang.String)
	 */
	@Override
	public void deleteDataSourceRelByRole(Integer id) {
		// TODO Auto-generated method stub
		String sql="update role_scheme_rel set deleteflag=0 where role_info_id=?";
		Db.update(sql,id);
		
	}
	/* (non-Javadoc)
	 * @see com.service.RoleInterface#addDataSourceRel(java.lang.String, java.lang.String)
	 */
	@Override
	public void addDataSourceRel(Integer scheme_info_id, Integer role_info_id) {
		// TODO Auto-generated method stub
		new Role_Scheme_Rel().set("scheme_info_id", scheme_info_id).set("role_info_id", role_info_id).save();
		
	}



}
