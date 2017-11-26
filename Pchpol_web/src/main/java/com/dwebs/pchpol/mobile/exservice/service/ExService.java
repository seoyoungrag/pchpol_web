/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : ExService.java
 * 2. Package : com.dwebs.pchpol.mobile.exservice.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 7. 오후 8:10:18
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 7. :            : 신규 개발.
 */
package com.dwebs.pchpol.mobile.exservice.service;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : ExService.java
 * 3. Package  : com.dwebs.pchpol.mobile.exservice.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 7. 오후 8:10:18
 * </PRE>
 */
public interface ExService {

	/**
	 * <PRE>
	 * 1. MethodName : login
	 * 2. ClassName  : ExService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 7. 오후 8:10:33
	 * </PRE>
	 *   @return void
	 *   @param loginId
	 *   @param loginPassword
	 */
	void login(String loginId, String loginPassword);

}
