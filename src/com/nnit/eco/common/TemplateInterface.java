package com.nnit.eco.common;

import java.util.List;

import com.dao.Template;

public interface TemplateInterface {

	public abstract List<Template> getTemplate();

	public abstract String delTemplate(Integer id);

	public abstract List<Template> userRelation(Integer id);

	public abstract Template getTemplateById(Integer id);
	
	public abstract void addUserRel(Integer userid, Integer user_info_id);

	public abstract void delTemplateRel(Integer id);


}