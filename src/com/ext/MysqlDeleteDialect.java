package com.ext;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;


public class MysqlDeleteDialect extends MysqlDialect {

	public String forModelDeleteById(Table table) {
		String[] primaryKey = table.getPrimaryKey();
		StringBuilder sql = new StringBuilder(45);
		sql.append("update `");
		sql.append(table.getName());
		sql.append("` set deleteflag = 0");
		sql.append(" where `").append(primaryKey[0]).append("` = ?");
		return sql.toString();
	}
	
	public String forModelFindById(Table table, String columns) {
		StringBuilder sql = new StringBuilder("select ");
		if (columns.trim().equals("*")) {
			sql.append(columns);
		}
		else {
			String[] columnsArray = columns.split(",");
			for (int i=0; i<columnsArray.length; i++) {
				if (i > 0)
					sql.append(", ");
				sql.append("`").append(columnsArray[i].trim()).append("`");
			}
		}
		sql.append(" from `");
		sql.append(table.getName());
		sql.append("` where `").append(table.getPrimaryKey()).append("` = ?");
		sql.append(" and deleteflag = 1");
		return sql.toString();
	}
	
	public String forDbFindById(String tableName, String primaryKey, String columns) {
		StringBuilder sql = new StringBuilder("select ");
		if (columns.trim().equals("*")) {
			sql.append(columns);
		}
		else {
			String[] columnsArray = columns.split(",");
			for (int i=0; i<columnsArray.length; i++) {
				if (i > 0)
					sql.append(", ");
				sql.append("`").append(columnsArray[i].trim()).append("`");
			}
		}
		sql.append(" from `");
		sql.append(tableName.trim());
		sql.append("` where `").append(primaryKey).append("` = ?");
		sql.append(" and deleteflag = 1");
		return sql.toString();
	}


}
