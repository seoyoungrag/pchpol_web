/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : FacilityService.java
 * 2. Package : com.dwebs.pchpol.facility.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 11:43:04
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.facility.service;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Facility;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : FacilityService.java
 * 3. Package  : com.dwebs.pchpol.facility.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 11:43:04
 * </PRE>
 */
public interface FacilityService {

	/**
	 * <PRE>
	 * 1. MethodName : insertFacility
	 * 2. ClassName  : FacilityService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 11:43:11
	 * </PRE>
	 *   @return void
	 *   @param facility
	 */
	void insertFacility(Facility facility);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : FacilityService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 11:44:16
	 * </PRE>
	 *   @return void
	 *   @param facility
	 */
	void insertOrUpdate(Facility facility);

	/**
	 * <PRE>
	 * 1. MethodName : getFacility
	 * 2. ClassName  : FacilityService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 27. 오후 5:22:11
	 * </PRE>
	 *   @return List<Facility>
	 *   @param pagingVO
	 *   @param facility
	 *   @return
	 */
	List<Facility> getFacility(PagingVO pagingVO, Facility facility);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntFacility
	 * 2. ClassName  : FacilityService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 27. 오후 5:22:15
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param facility
	 *   @return
	 */
	int getTotCntFacility(PagingVO pagingVO, Facility facility);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : FacilityService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 27. 오후 5:45:52
	 * </PRE>
	 *   @return Facility
	 *   @param facilityNo
	 *   @return
	 */
	Facility getById(int facilityNo);

	/**
	 * <PRE>
	 * 1. MethodName : deleteByIds
	 * 2. ClassName  : FacilityService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 27. 오후 4:49:19
	 * </PRE>
	 *   @return void
	 *   @param ids
	 */
	void deleteByIds(List<Integer> ids);

}
