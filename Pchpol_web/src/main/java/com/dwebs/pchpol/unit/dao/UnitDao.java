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
	List<Unit> getListByUnit(PagingVO pagingVO, Unit unit);

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
	int getTotCntByUnit(PagingVO pagingVO, Unit unit);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 15. 오후 8:03:38
	 * </PRE>
	 *   @return void
	 *   @param unit
	 */
	void insertOrUpdate(Unit unit);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 15. 오후 8:43:02
	 * </PRE>
	 *   @return Unit
	 *   @param id
	 *   @return
	 */
	Unit getById(String id);

	/**
	 * <PRE>
	 * 1. MethodName : getByPchId
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 7. 오후 8:11:39
	 * </PRE>
	 *   @return Object
	 *   @param loginId
	 *   @return
	 */
	Unit getByPchId(String loginId);

	/**
	 * <PRE>
	 * 1. MethodName : deleteByIds
	 * 2. ClassName  : UnitDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 14. 오후 9:25:20
	 * </PRE>
	 *   @return void
	 *   @param ids
	 */
	void deleteByIds(List<Integer> ids);

}
