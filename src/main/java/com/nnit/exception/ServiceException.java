package main.java.com.nnit.exception;

/**
 * 业务逻辑异常类
 * @author yhaili
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7441343262041577682L;
	
	/**
	 * 异常Code
	 */
	private String errorCode;
	/**
	 * 返回页面
	 */
	private String errorPage;
	
	public ServiceException(ServiceExceptionEnums boExceptionEnums) {
		this(boExceptionEnums, null);
	}
	
	public ServiceException(ServiceExceptionEnums boExceptionEnums, String returnPage) {
		super(boExceptionEnums.errorMsg());
		this.errorCode = boExceptionEnums.errorCode();
		this.errorPage = returnPage;
	}
	
	public ServiceException(String errorMsg) {
		this(errorMsg, null);
	}

	public ServiceException(String errorMsg, String returnPage) {
		super(errorMsg);
		this.errorCode = "";
		this.errorPage = returnPage;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorPage() {
		return errorPage;
	}
}
