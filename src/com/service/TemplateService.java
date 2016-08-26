package com.service;

import java.util.List;

import com.common.SaleForceController;
import com.common.UsersController;
import com.dao.Roles;
import com.dao.Template;
import com.dao.User_Role_Rel;
import com.dao.Users;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.nnit.eco.common.TemplateInterface;

public class TemplateService implements TemplateInterface {
	private static Logger logger = Logger.getLogger(UsersController.class);
	
	
	/* (non-Javadoc)
	 * @see com.service.TemplateInterface#getTemplate()
	 */
	@Override
	public List<Template> getTemplate() {
		// TODO Auto-generated method stub
		String sql = "select distinct(template_id),template_name from user_template;";
		List<Template> templates = Template.dao.find(sql);
		return templates;
	}
	
	/* (non-Javadoc)
	 * @see com.service.TemplateInterface#delTemplate(java.lang.String)
	 */
	@Override
	public String delTemplate(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from user_template where template_id =?";
		int i = Db.update(sql,id);
		return null; 
	}

	@Override
	public List<Template> userRelation(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select distinct(template_id),template_name,sys_user.nickname "
				+ "from user_template join sys_user on sys_user.id = userid where template_id=? and deleteflag=1";
		List<Template> templates = Template.dao.find(sql,id);
		return templates;
	}
	
	public List<Template> addUserRel(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select distinct(template_id),template_name,sys_user.nickname "
				+ "from user_template join sys_user on sys_user.id = userid where template_id=?";
		List<Template> templates = Template.dao.find(sql,id);
		return templates;
	}

	@Override
	public Template getTemplateById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserRel(Integer userid, Integer user_info_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delTemplateRel(Integer id) {
		// TODO Auto-generated method stub
		String sql="update user_template_rel set deleteflag=0 where template_info_id=?";
		Db.update(sql,id);
	}

	
	


}
