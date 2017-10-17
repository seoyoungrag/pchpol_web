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

import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;

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
	TroopsPlacement getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch);

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

}
