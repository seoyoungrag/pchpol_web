/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CodeService.java
 * 2. Package : com.dwebs.pchpol.code.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 11. 오후 1:18:14
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 11. :            : 신규 개발.
 */
package com.dwebs.pchpol.code.service;

import java.util.List;

import com.dwebs.pchpol.model.Code;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CodeService.java
 * 3. Package  : com.dwebs.pchpol.code.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 11. 오후 1:18:14
 * </PRE>
 */
public interface CodeService {

	/**
	 * <PRE>
	 * 1. MethodName : getCodeListByCategory
	 * 2. ClassName  : CodeService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 1:18:25
	 * </PRE>
	 *   @return List<Code>
	 *   @param category
	 *   @return
	 */
	List<Code> getCodeListByCategory(String category);

}
