/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : ExceptionResponse.java
 * 2. Package : com.dwebs.converter.common.vo
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 6. 오전 11:11:05
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 6. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.vo;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : ExceptionResponse.java
 * 3. Package  : com.dwebs.converter.common.vo
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 6. 오전 11:11:05
 * </PRE>
 */
public class ExceptionBody {
	
	private String clientIp;
	private String requestUri;
	private String requestMethod;
	private String requestBody;
    private String errorCode;
    private Object errors;
    
    
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Object getErrors() {
		return errors;
	}
	public void setErrors(Object errors) {
		this.errors = errors;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
    
}
