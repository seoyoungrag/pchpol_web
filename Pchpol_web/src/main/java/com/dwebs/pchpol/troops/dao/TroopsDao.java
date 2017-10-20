/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : troopsDao.java
 * 2. Package : com.dwebs.pchpol.troops.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 12:58:31
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.troops.dao;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : troopsDao.java
 * 3. Package  : com.dwebs.pchpol.troops.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 12:58:31
 * </PRE>
 */
public interface TroopsDao {

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplace
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 1:39:55
	 * </PRE>
	 *   @return TroopsPlacement
	 *   @param troopsWorkplaceSearch
	 *   @return
	 */
	List<TroopsPlacement> getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch);

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsFacilityListByTroops
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 1:39:58
	 * </PRE>
	 *   @return List<TroopsFacilityPlacement>
	 *   @param troopsFacilitySearch
	 *   @return
	 */
	List<TroopsFacilityPlacement> getTroopsFacilityListByTroops(TroopsFacilityPlacement troopsFacilitySearch);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 18. 오후 9:01:52
	 * </PRE>
	 *   @return void
	 *   @param troopsPlacement
	 */
	void insertOrUpdate(TroopsPlacement troopsPlacement);

	/**
	 * <PRE>
	 * 1. MethodName : deleteDuplicate
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 18. 오후 9:28:55
	 * </PRE>
	 *   @return void
	 *   @param troopsPlacement
	 */
	void deleteDuplicate(TroopsPlacement troopsPlacement);

	/**
	 * <PRE>
	 * 1. MethodName : getByid
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 18. 오후 9:46:42
	 * </PRE>
	 *   @return TroopsPlacement
	 *   @param troopsPlacementNo
	 *   @return
	 */
	TroopsPlacement getByid(String troopsPlacementNo);

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplaceGroupByTroopsCode3depth
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오전 11:06:34
	 * </PRE>
	 *   @return List<WorkplaceWithTroops>
	 *   @param pagingVO
	 *   @param workplaceWithTroops
	 *   @return
	 */
	List<WorkplaceWithTroops> getTroopsWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO,
			WorkplaceWithTroops workplaceWithTroops);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntWorkplaceGroupByTroopsCode3depth
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 12:04:52
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param workplaceWithTroops
	 *   @return
	 */
	int getTotCntWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO, WorkplaceWithTroops workplaceWithTroops);

	/**
	 * <PRE>
	 * 1. MethodName : getWorkplaceTroops
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 6:18:22
	 * </PRE>
	 *   @return List<WorkplacePlacement>
	 *   @param tpSearch
	 *   @return
	 */
	List<WorkplacePlacement> getWorkplaceTroops(TroopsPlacement tpSearch);

	/**
	 * <PRE>
	 * 1. MethodName : getWorkplaceTroops
	 * 2. ClassName  : TroopsDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오후 2:55:14
	 * </PRE>
	 *   @return WorkplacePlacement
	 *   @param wps
	 *   @return
	 */
	WorkplacePlacement getWorkplaceTroops(WorkplacePlacement wps);

}
