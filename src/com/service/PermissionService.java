package com.service;


import java.util.List;

import com.common.PermissionController;
import com.dao.Permission;
import com.jfinal.log.Logger;
import com.nnit.eco.common.PermissionInterface;

public class PermissionService implements PermissionInterface {
	private static Logger logger = Logger.getLogger(PermissionController.class);

	/* (non-Javadoc)
	 * @see com.service.PermissionInterface#getPermission()
	 */
	public List<Permission> getPermission() {
		// TODO Auto-generated method stub
		String sql = "select * from permission_info where deleteflag = 1";
		List<Permission> Permissions = Permission.dao.find(sql);
		return Permissions;
	}
}
