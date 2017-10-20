/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceDao.java
 * 2. Package : com.dwebs.pchpol.workplace.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 20. 오전 11:07:39
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 20. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.dao;

import java.util.List;

import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceDao.java
 * 3. Package  : com.dwebs.pchpol.workplace.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 20. 오전 11:07:39
 * </PRE>
 */
public interface WorkplaceDao {

	/**
	 * <PRE>
	 * 1. MethodName : deleteDuplicate
	 * 2. ClassName  : WorkplaceDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 11:08:53
	 * </PRE>
	 *   @return void
	 *   @param wp
	 */
	void deleteDuplicate(WorkplacePlacement wp);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : WorkplaceDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 11:09:05
	 * </PRE>
	 *   @return void
	 *   @param wp
	 */
	void insertOrUpdate(WorkplacePlacement wp);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : WorkplaceDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 11:14:20
	 * </PRE>
	 *   @return void
	 *   @param detailList
	 */
	void insertOrUpdate(List<WorkplacePlacementDetail> detailList);

}
