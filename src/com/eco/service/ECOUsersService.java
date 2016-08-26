package com.eco.service;

import java.util.ArrayList;
import java.util.List;

import com.common.SaleForceController;
import com.common.UsersController;
import com.dao.Roles;
import com.dao.User_Role_Rel;
import com.dao.Users;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.nnit.eco.common.UserInterface;
import com.toolkit.Md5Kit;

public class ECOUsersService implements UserInterface {
	private static Logger logger = Logger.getLogger(UsersController.class);
	
	
	/* (non-Javadoc)
	 * @see com.service.UserInterface#saveUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveUser(String userName,String userNick,String password) {
	   String result ="" ;
	   if(checkUserName(userName)==true){
		   if(checkUserNick(userNick)==true){
			   new Users().set("user_name", userName).set("user_nick",userNick).set("password", password).save();
			   Users user = new Users().searchFirst("user_name", userName);
			   result = user.get("id").toString();
		   }else{
			   result="userNickExist";
		   }
	   }else{
		   result="userNameExist";
	   }
       return result;
	  
	}
	/* (non-Javadoc)
	 * @see com.service.UserInterface#checkUserName(java.lang.String)
	 */
	@Override
	public boolean checkUserName(String userName){
		Boolean result = false;
		Users user = new Users().searchFirst("user_name", userName);
		if(user==null){
			result=true;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.service.UserInterface#checkUserNick(java.lang.String)
	 */
	@Override
	public boolean checkUserNick(String userNick){
		Boolean result = false;
		if(Users.dao.searchFirst("user_nick", userNick)==null){
			result=true;
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see com.service.UserInterface#getUsers()
	 */
	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
     	String sql = "select * from sys_user ";
		
		List<Record> results =Db.find(sql);
		List<Users> users = new ArrayList();
		for(int i = 0 ; i < results.size() ; i ++){
			Users user = new Users(); 
			user.set("id", results.get(i).getInt("id"));
			user.set("user_nick", results.get(i).getStr("nickname"));
			user.set("user_name", "#");
			users.add(user);
		}
		return users;
	}
	/* (non-Javadoc)
	 * @see com.service.UserInterface#getUserRoleRelaById(java.lang.String)
	 */
	@Override
	public List<User_Role_Rel> getUserRoleRelaById(String id) {
		// TODO Auto-generated method stub
		String sql="select role_info_id from user_role_rel where deleteflag=1 and user_info_id="+id;
		List<User_Role_Rel> urr =  User_Role_Rel.dao.find(sql);
		return urr;
	}
	/* (non-Javadoc)
	 * @see com.service.UserInterface#delUser(java.lang.String)
	 */
	@Override
	public String delUser(Integer id) {
		// TODO Auto-generated method stub
		String result=null;
	    if(Users.dao.deleteById(id)==true){
	    	result="删除成功";
	    }else{
	    	result="删除失败";
	    }
		return result;   
	}


	/* (non-Javadoc)
	 * @see com.service.UserInterface#updateUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String updateUser(Integer id,String userName,String userNick) {
		String result ="" ;
	    if(checkUserNick(userNick)==true){
		   Users.dao.findById(id).set("user_name", userName).set("user_nick", userNick).update();
		   Users user = new Users().searchFirst("user_name", userName);
		   result = user.get("id").toString();
	    }else{
		   result="userNickExist";
	    }
        return result;
	}



	/* (non-Javadoc)
	 * @see com.service.UserInterface#deleteRoleRelByUserId(java.lang.String)
	 */
	@Override
	public void deleteRoleRelByUserId(Integer id) {
		// TODO Auto-generated method stub
		String sql="update user_role_rel set deleteflag=0 where user_info_id=?";
		Db.update(sql,id);
		
	}


	/* (non-Javadoc)
	 * @see com.service.UserInterface#addRoleUserRel(java.lang.String, java.lang.String)
	 */
	@Override
	public void addRoleUserRel(Integer role_info_id, Integer user_info_id) {
		// TODO Auto-generated method stub
		new User_Role_Rel().set("role_info_id", role_info_id).set("user_info_id", user_info_id).save();
			 
	}
	/* (non-Javadoc)
	 * @see com.service.UserInterface#resetUser(java.lang.String)
	 */
	@Override
	public String resetUser(Integer id) {
		// TODO Auto-generated method stub
		int result = 0;
		String password="";
		try {
			password = Md5Kit.getMd5("123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="update user_info set password=? where id=? and deleteflag=1";
		result =Db.update(sql,password,id);
		System.out.println(result);
		return String.valueOf(result) ;   
	}


/*	public String getIdByName(String userName) {
		String result =null ;
//		Users user = new Users().find
		Users user= new Users().dao.searchFirst("user_name", userName);
		if(user==null){
			result="找不到该用户";
		}else{
			result=user.get("id").toString();
		}
			return result;    
	}*/



}
