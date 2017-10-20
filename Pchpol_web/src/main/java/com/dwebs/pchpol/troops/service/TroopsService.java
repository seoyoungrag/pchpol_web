/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : TroopsService.java
 * 2. Package : com.dwebs.pchpol.troops
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 12:54:31
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.troops.service;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TroopsService.java
 * 3. Package  : com.dwebs.pchpol.troops
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 12:54:31
 * </PRE>
 */
public interface TroopsService {

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplace
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 1:32:20
	 * </PRE>
	 *   @return TroopsPlacement
	 *   @param troopsWorkplaceSearch
	 *   @return
	 */
	List<TroopsPlacement> getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch);

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsFacilityListByTroops
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 1:36:14
	 * </PRE>
	 *   @return List<TroopsFacilityPlacement>
	 *   @param troopsFacilitySearch
	 *   @return
	 */
	List<TroopsFacilityPlacement> getTroopsFacilityListByTroops(TroopsFacilityPlacement troopsFacilitySearch);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 18. 오후 9:01:10
	 * </PRE>
	 *   @return void
	 *   @param troopsPlacement
	 */
	void insertOrUpdate(TroopsPlacement troopsPlacement);

	/**
	 * <PRE>
	 * 1. MethodName : insertTroopsWorkplacePlacement
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 18. 오후 9:17:56
	 * </PRE>
	 *   @return void
	 *   @param troopsPlacement
	 */
	void insertTroopsWorkplacePlacement(TroopsPlacement troopsPlacement);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 18. 오후 9:46:05
	 * </PRE>
	 *   @return TroopsPlacement
	 *   @param troopsPlacementNo
	 *   @return
	 */
	TroopsPlacement getById(String troopsPlacementNo);

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplaceGroupByTroopsCode3depth
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오전 11:03:30
	 * </PRE>
	 *   @return List<WorkplaceWithTroops>
	 *   @param pagingVO
	 *   @param workplaceWithTroops
	 *   @return
	 */
	List<WorkplaceWithTroops> getTroopsWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO, WorkplaceWithTroops workplaceWithTroops);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntWorkplaceGroupByTroopsCode3depth
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 12:04:17
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param workplaceWithTroops
	 *   @return
	 */
	int getTotCntWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO, WorkplaceWithTroops workplaceWithTroops);

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplace
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 3:36:07
	 * </PRE>
	 *   @return List<Code>
	 *   @param tp
	 *   @return
	 */
	List<TroopsPlacement> getTroopsWorkplace(WorkplaceWithTroops tp);

	/**
	 * <PRE>
	 * 1. MethodName : getWorkplaceTroops
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 6:17:08
	 * </PRE>
	 *   @return List<WorkplacePlacement>
	 *   @param wwt
	 *   @return
	 */
	List<WorkplacePlacement> getWorkplaceTroops(WorkplaceWithTroops wwt);

	/**
	 * <PRE>
	 * 1. MethodName : getWorkplaceTroops
	 * 2. ClassName  : TroopsService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오후 2:53:09
	 * </PRE>
	 *   @return WorkplacePlacement
	 *   @param wps
	 *   @return
	 */
	WorkplacePlacement getWorkplaceTroops(WorkplacePlacement wps);
}
