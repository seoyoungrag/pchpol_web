package com.dwebs.pchpol.common.vo;

import java.util.Calendar;
import java.util.Date;

public class Response {

	public static final String R_MSG_EMPTY = "";
	public static final String R_MSG_SUCCESS = "success";
	public static final String R_MSG_FAIL = "fail";
	public static final String R_CODE_SUCCESS = "1";
	public static final String R_CODE_FAIL = "0";

	//private final String responseCode;
	private final boolean success;
	private final Date execDt;
	private final String message;

	private Object data;

	public Response() {
		this.execDt = Calendar.getInstance().getTime();
		this.message = Response.R_MSG_SUCCESS;
		//this.responseCode = Response.R_CODE_SUCCESS;
		this.success = true;
		this.data = null;
	}

	/**
	 * A Creates a new instance of Response
	 *
	 * @param code
	 * @param message
	 * @param execDt
	 */
	/*
	public Response(final String code, final String message, final Date execDt) {

		this.execDt = execDt == null ? Calendar.getInstance().getTime() : execDt;
		this.message = message == null ? Response.R_MSG_EMPTY : message;
		this.responseCode = code == null ? Response.R_CODE_SUCCESS : code;
		this.data = null;
	}*/
	public Response(final boolean code, final String message, final Date execDt) {

		this.execDt = execDt == null ? Calendar.getInstance().getTime() : execDt;
		this.message = message == null ? Response.R_MSG_EMPTY : message;
		this.success = code;
		this.data = null;
	}

	public Response(final boolean code, final String message) {

		this.execDt = Calendar.getInstance().getTime();
		this.message = message == null ? Response.R_MSG_EMPTY : message;
		this.success = code;
		this.data = null;
	}
	/**
	 * @return the execDt
	 */
	public Date getExecDt() {

		return this.execDt;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {

		return this.message;
	}

	/**
	 * @return the response
	 */
	public Object getData() {

		return this.data;
	}

	/**
	 * @return the responseCode
	 */
	/*
	public String getResponseCode() {

		return this.responseCode;
	}
	 */
	
	/**
	 * sets the response object
	 *
	 * @param obj
	 * @return
	 */
	public Response setData(final Object obj) {

		this.data = obj;
		return this;
	}

	/**
	 * <PRE>
	 * 1. MethodName : isSuccess
	 * 2. ClassName  : Response
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 9. 4. 오후 1:10:03
	 * </PRE>
	 *   @return boolean
	 *   @return
	 */
	public boolean isSuccess() {
		return success;
	}
	
}
