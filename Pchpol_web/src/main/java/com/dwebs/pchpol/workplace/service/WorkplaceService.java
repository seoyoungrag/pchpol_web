/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceService.java
 * 2. Package : com.dwebs.pchpol.workplace.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 20. 오전 10:49:02
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 20. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.service;

import java.util.List;

import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.workplace.vo.WorkplaceDetail;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceService.java
 * 3. Package  : com.dwebs.pchpol.workplace.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 20. 오전 10:49:02
 * </PRE>
 */
public interface WorkplaceService {

	/**
	 * <PRE>
	 * 1. MethodName : getDetailList
	 * 2. ClassName  : WorkplaceService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 10:49:35
	 * </PRE>
	 * @param wp 
	 *   @return List<WorkplacePlacementDetail>
	 *   @param workplaceDetail
	 *   @return
	 */
	List<WorkplacePlacementDetail> getDetailList(WorkplacePlacement wp, WorkplaceDetail workplaceDetail);

	/**
	 * <PRE>
	 * 1. MethodName : insertWorkplacement
	 * 2. ClassName  : WorkplaceService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 11:06:56
	 * </PRE>
	 *   @return void
	 *   @param wp
	 */
	void insertWorkplacement(WorkplacePlacement wp);

	/**
	 * <PRE>
	 * 1. MethodName : insertWorkplacementDetailList
	 * 2. ClassName  : WorkplaceService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 11:14:01
	 * </PRE>
	 *   @return void
	 *   @param detailList
	 */
	void insertWorkplacementDetailList(List<WorkplacePlacementDetail> detailList);

	/**
	 * <PRE>
	 * 1. MethodName : findWp
	 * 2. ClassName  : WorkplaceService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 23. 오후 2:19:16
	 * </PRE>
	 *   @return WorkplacePlacement
	 *   @param wp
	 *   @return
	 */
	List<WorkplacePlacement> findWp(WorkplacePlacement wp);

}
