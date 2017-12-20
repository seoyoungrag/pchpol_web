/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : CommonUtil.java
 * 2. Package : com.dwebs.converter.common.util
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 4. 오후 1:47:19
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 4. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.dwebs.pchpol.common.vo.ExceptionBody;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CommonUtil.java
 * 3. Package  : com.dwebs.converter.common.util
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 4. 오후 1:47:19
 * </PRE>
 */
public class CommonUtil {
	public static String escapeHtml(String str){
		String regex1 = "\\<.*?\\>";
		@SuppressWarnings("unused")
		String regex2 = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
		String retVal = "";
		retVal = str.replaceAll(regex1, "");
		return retVal;
	}
	public static String null2str(String org) {
		return null2str(org, "");
	}
	
	public static String null2str(String org, String converted) {
		if ((org == null) || (org.trim().length() == 0)) {
			return converted;
		}
		return org.trim();
	}
	public static String makerErrStr(List<ObjectError> errs){
    	List<String> errStrs = new ArrayList<String>();
    	for(ObjectError err : errs){
    		errStrs.add(err.getDefaultMessage());
    	}
    	return StringUtils.join(errStrs,", ").toString();
	}

	/**
	 * <PRE>
	 * 1. MethodName : fromBindingErrors
	 * 2. ClassName  : CommonUtil
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 9. 6. 오전 11:17:37
	 * </PRE>
	 *   @return List<String>
	 *   @param bindingResult
	 *   @return
	 */
	public static List<String> fromBindingErrors(BindingResult bindingResult) {
		List<String> errs = new ArrayList<String>();
    	for(ObjectError err : bindingResult.getAllErrors()){
    		errs.add(err.getDefaultMessage());
    	}
		return errs;
	}

    /**
     * <PRE>
     * 1. MethodName : setRequestInfoToExceptionBody
     * 2. ClassName  : ExceptionControllerAdviceHndlr
     * 3. Comment   : exception 정보에 request정보를 담기 위한 메소드
     * 4. 작성자    : yrseo
     * 5. 작성일    : 2017. 9. 7. 오후 3:13:26
     * </PRE>
     *   @return void
     *   @param request
     *   @param eBody
     */
    public static void setRequestInfoToExceptionBody(HttpServletRequest request, ExceptionBody eBody){

        String ip = request.getHeader("X-FORWARDED-FOR");
        if(ip==null){
        	ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip==null){
        	ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null){
            ip = request.getRemoteAddr();
        }
        
        eBody.setClientIp(ip);
        eBody.setRequestMethod(request.getMethod());
        eBody.setRequestUri(request.getRequestURI());
        if(request.getMethod().equalsIgnoreCase("POST")||request.getMethod().equalsIgnoreCase("PUT")){
        	try {
				eBody.setRequestBody(IOUtils.toString(request.getInputStream(), "UTF-8"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
    }
}
