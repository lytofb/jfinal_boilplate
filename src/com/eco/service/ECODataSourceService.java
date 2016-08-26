package com.eco.service;


import java.util.List;

import com.common.PermissionController;
import com.common.RoleController;
import com.dao.DataSource;
import com.dao.Permission;
import com.jfinal.log.Logger;
import com.nnit.eco.common.DataSourceInterface;

public class ECODataSourceService implements DataSourceInterface {
	private static Logger logger = Logger.getLogger(RoleController.class);

	/* (non-Javadoc)
	 * @see com.service.DataSourceInterface#getDataSources()
	 */
	@Override
	public List<DataSource> getDataSources() {
		// TODO Auto-generated method stub
		String sql = "select * from m_datasource where deleted = 1";
		List<DataSource> DataSources = DataSource.dao.find(sql);  
		return DataSources;
	}
}
