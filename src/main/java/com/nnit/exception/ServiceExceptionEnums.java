package main.java.com.nnit.exception;

public enum ServiceExceptionEnums {
	//default
	DEFAULT_DATA_NO_EXIST("DEFAULT_DATA_NO_EXIST","数据不存在"),
	DEFAULT_FAIL_OF_DATA_VALID("DEFAULT_FAIL_OF_DATA_VALID", "数据检验失败，存在不合法信息"),
	DEFAULT_ADD_FAIL("DEFAULT_ADD_FAIL", "数据添加失败，存入数据库时出现异常"),
	DEFAULT_ADD_FAIL_OF_NAME_EXIST("DEFAULT_ADD_FAIL_OF_NAME_EXIST", "数据添加失败，名称已存在"),
	DEFAULT_UPDATE_FAIL("DEFAULT_UPDATE_FAIL", "数据更新失败，更新数据库时出现异常"),
	DEFAULT_UPDATE_FAIL_OF_NOT_EXIST("DEFAULT_UPDATE_FAIL_OF_NOT_EXIST", "数据更新失败，您更新的数据不存在"),
	DEFAULT_DEL_FAIL("DEFAULT_DEL_FAIL", "数据删除失败，删除数据库记录时出现异常"),
	DEFAULT_DEL_FAIL_OF_NO_EXIST("DEFAULT_DEL_FAIL_OF_NO_EXIST", "数据删除失败，删除的数据记录不存在"),
	
	//菜单
	MENU_DEL_FAIL_OF_USED("MENU_DEL_FAIL_OF_USED","菜单删除失败，当前菜单正在被使用中"),
	MENU_ADD_FAIL_OF_SORT_USED("MENU_ADD_FAIL_OF_SORT_USED","菜单添加失败，排序已被占用"),
	
	
	
	;
	
	private String errorCode;
	private String errorMsg;
	
	private ServiceExceptionEnums(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String errorCode() {
		return errorCode;
	}

	public String errorMsg() {
		return errorMsg;
	}
}
