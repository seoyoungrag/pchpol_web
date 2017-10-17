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

import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;

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
	TroopsPlacement getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch);

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
}
