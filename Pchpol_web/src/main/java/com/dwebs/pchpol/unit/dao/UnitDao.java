/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : UnitDao.java
 * 2. Package : com.dwebs.pchpol.unit.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 13. 오전 9:45:41
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 13. :            : 신규 개발.
 */
package com.dwebs.pchpol.unit.dao;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Unit;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : UnitDao.java
 * 3. Package  : com.dwebs.pchpol.unit.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 13. 오전 9:45:41
 * </PRE>
 */
public interface UnitDao {

	/**
	 * <PRE>
	 * 1. MethodName : getListByTroopsTypeAndCode
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오후 1:15:24
	 * </PRE>
	 *   @return List<Unit>
	 *   @param pagingVO
	 *   @param troopsType
	 *   @param code
	 *   @return
	 */
	List<Unit> getListByTroopsTypeAndCode(PagingVO pagingVO, String troopsType, Code code);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntByTroopsTypeAndCode
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오후 1:15:27
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param troopsType
	 *   @param code
	 *   @return
	 */
	int getTotCntByTroopsTypeAndCode(PagingVO pagingVO, String troopsType, Code code);

}
