/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CodeDao.java
 * 2. Package : com.dwebs.pchpol.code.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 11. 오후 1:20:03
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 11. :            : 신규 개발.
 */
package com.dwebs.pchpol.code.dao;

import java.util.List;

import com.dwebs.pchpol.model.Code;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CodeDao.java
 * 3. Package  : com.dwebs.pchpol.code.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 11. 오후 1:20:03
 * </PRE>
 */
public interface CodeDao {

	/**
	 * <PRE>
	 * 1. MethodName : getCodeListByCategory
	 * 2. ClassName  : CodeDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 1:20:13
	 * </PRE>
	 *   @return List<Code>
	 *   @param category
	 *   @return
	 */
	List<Code> getCodeListByCategory(String category);

}
