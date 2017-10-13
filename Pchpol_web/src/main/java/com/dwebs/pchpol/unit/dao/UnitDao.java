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
import com.dwebs.pchpol.model.Admin;
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
	 * 1. MethodName : getList
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오전 9:45:47
	 * </PRE>
	 *   @return List<Admin>
	 *   @param pagingVO
	 *   @return
	 */
	List<Unit> getList(PagingVO pagingVO);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCnt
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오전 9:45:51
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @return
	 */
	int getTotCnt(PagingVO pagingVO);

	/**
	 * <PRE>
	 * 1. MethodName : getListByType
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오전 10:11:47
	 * </PRE>
	 *   @return List<Unit>
	 *   @param pagingVO
	 *   @param type
	 *   @return
	 */
	List<Unit> getListByType(PagingVO pagingVO, String type);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntByType
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오전 10:11:54
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param type
	 *   @return
	 */
	int getTotCntByType(PagingVO pagingVO, String type);

}
