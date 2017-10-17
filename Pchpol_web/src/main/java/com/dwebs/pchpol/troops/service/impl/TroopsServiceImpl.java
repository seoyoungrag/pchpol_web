/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : TroopsServiceImpl.java
 * 2. Package : com.dwebs.pchpol.troops.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 12:55:50
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.troops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.troops.dao.TroopsDao;
import com.dwebs.pchpol.troops.service.TroopsService;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TroopsServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.troops.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 12:55:50
 * </PRE>
 */
@Component("troopsService")
public class TroopsServiceImpl implements TroopsService {
	@Autowired
	@Qualifier("troopsDao")
	public TroopsDao troopsDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTroopsWorkplace(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public TroopsPlacement getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch) {
		return troopsDao.getTroopsWorkplace(troopsWorkplaceSearch);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTroopsFacilityListByTroops(com.dwebs.pchpol.model.TroopsFacilityPlacement)
	 */
	@Override
	public List<TroopsFacilityPlacement> getTroopsFacilityListByTroops(TroopsFacilityPlacement troopsFacilitySearch) {
		return troopsDao.getTroopsFacilityListByTroops(troopsFacilitySearch);
	}

}
