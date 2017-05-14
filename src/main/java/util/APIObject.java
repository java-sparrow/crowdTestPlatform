package main.java.util;

import org.json.JSONObject;

public class APIObject {
	/**
	 * 空构造器，默认初始化值为：成功状态码、成功提示信息、空数据返回对象
	 */
	public APIObject() {
		super();
	}

	/**
	 * 构造器初始化：使用指定参数作为 状态码 和 提示信息，并同时设置 返回数据对象
	 * @param status 状态码
	 * @param message 提示信息
	 * @param data 返回数据对象
	 */
	public APIObject(int status, String message, Object data) {
		super();
		
		this.status = status;
		this.message = message;
		this.data = data;
	}

	/**
	 * 构造器初始化：使用指定参数作为 状态码 和 提示信息
	 * @param status 状态码
	 * @param message 提示信息
	 */
	public APIObject(int status, String message) {
		super();
		
		this.status = status;
		this.message = message;
	}

	/**
	 * 构造器初始化：使用指定参数作为 错误提示信息，并自动设置 普通错误状态码
	 * @param message 错误提示信息
	 */
	public APIObject(String message) {
		super();
		
		this.message = message;
		
		// 设置 普通错误状态码
		this.status = ERROR_STATUS;
	}
	
	/**
	 * 构造器初始化：使用指定参数作为 错误状态码，并自动设置 普通错误提示信息
	 * @param status 错误状态码
	 */
	public APIObject(int status) {
		super();
		
		this.status = status;

		// 设置 普通错误提示信息
		this.message = ERROR_MESSAGE;
	}

	
	/**
	 * 成功状态码
	 */
	private static final int SUCCESS_STATUS = 0;
	/**
	 * 成功提示信息
	 */
	private static final String SUCCESS_MESSAGE = "OK";
	/**
	 * 普通错误状态码
	 */
	private static final int ERROR_STATUS = -1;
	/**
	 * 普通错误提示信息
	 */
	private static final String ERROR_MESSAGE = "操作失败";
	
	// 服务器繁忙
	private static final int SERVER_BUSY_STATUS = 500;
	private static final String SERVER_BUSY_MESSAGE = "服务器繁忙，请稍后重试...";
	// 未知错误
	private static final int UNKNOW_ERROR_STATUS = -100;
	private static final String UNKNOW_ERROR_MESSAGE = "未知错误";

	
	/**
	 * 状态码
	 */
	private int status = SUCCESS_STATUS;
	/**
	 * 提示信息
	 */
	private String message = SUCCESS_MESSAGE;
	/**
	 * 返回数据对象（存放API响应数据）
	 */
	private Object data = null;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * setData 的别名。<br>
	 * 没有对应的 getReturnData() 方法，否则 toJSON 时 会多出一个 returnData 的属性。
	 * @param returnData setData() 的参数
	 */
	public void setReturnData(Object returnData) {
		setData(returnData);
	}


	// 设置 “服务器繁忙” 提示
	public void setServerBusy(int busyStatus, String busyMessage) {
		this.status = busyStatus;
		this.message = busyMessage;
	}
	public void setServerBusy(String busyMessage) {
		setServerBusy(SERVER_BUSY_STATUS, busyMessage);
	}
	public void setServerBusy() {
		setServerBusy(SERVER_BUSY_MESSAGE);
	}
	// 设置 “未知错误” 提示
	public void setUnknowError(int errorStatus, String unknowErrorMessage) {
		this.status = errorStatus;
		this.message = unknowErrorMessage;
	}
	public void setUnknowError(String unknowErrorMessage) {
		setUnknowError(UNKNOW_ERROR_STATUS, unknowErrorMessage);
	}
	public void setUnknowError() {
		setUnknowError(UNKNOW_ERROR_MESSAGE);
	}
	
	
	public String toJSON() {
		if (this.data == null) {
			// 不能使用 new Object() ，否则会被序列化为 "java.lang.Object@xxxxxxxx" 形式 而不是空的 {}
			this.data = new JSONObject();
		}
		
		JSONObject apiObject = new JSONObject(this);
		
		String jsonString = apiObject.toString();
		
		System.out.println("输出json ---- " + jsonString);
		
		return jsonString;
	}
}
