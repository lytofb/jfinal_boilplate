package com.eco.service;

import java.util.List;

import com.common.SaleForceController;
import com.common.UsersController;
import com.dao.Role_Permission_Rel;
import com.dao.Roles;
import com.dao.Template;
import com.dao.User_Role_Rel;
import com.dao.Users;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.nnit.eco.common.TemplateInterface;

public class ECOTemplateService implements TemplateInterface {
	private static Logger logger = Logger.getLogger(UsersController.class);
	
	
	/* (non-Javadoc)
	 * @see com.service.TemplateInterface#getTemplate()
	 */
	@Override
	public List<Template> getTemplate() {
		// TODO Auto-generated method stub
		String sql = "select distinct(template_id),template_name from user_template";
		List<Template> templates = Template.dao.find(sql);
		return templates;
	}
	
	public List<Template> getTemplateUser(Integer id){
		String sql = "select user_info_id from user_template_rel where template_info_id=? and deleteflag=1";
		List<Template> templates = Template.dao.find(sql,id);
		return templates;
		
	}
	
	public void addUserRel(Integer user_info_id , Integer template_info_id ) {
		// TODO Auto-generated method stub
		Record user_template_rel = new Record().set("user_info_id", user_info_id).set("template_info_id", template_info_id);
		Db.save("user_template_rel", user_template_rel);
	}  
	/* (non-Javadoc)
	 * @see com.service.TemplateInterface#delTemplate(java.lang.String)
	 */
	@Override
	public String delTemplate(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from user_template where template_id =?";
		int i = Db.update(sql,id);
		System.out.println(i);
		return null;
	}

	@Override
	public List<Template> userRelation(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select user_info_id from user_template_rel where template_info_id=? and deleteflag=1";
		List<Template> templates = Template.dao.find(sql,id);
		return templates;
	}

	@Override
	public Template getTemplateById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select distinct(template_id),template_name from user_template where template_id=?";
		Template template = Template.dao.findFirst(sql,id);
		return template;
	}

	@Override
	public void delTemplateRel(Integer id) {
		// TODO Auto-generated method stub
		String sql="update user_template_rel set deleteflag=0 where template_info_id=?";
		Db.update(sql,id);
	}



}
