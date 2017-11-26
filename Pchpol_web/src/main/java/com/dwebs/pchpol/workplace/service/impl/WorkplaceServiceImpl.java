/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceServiceImpl.java
 * 2. Package : com.dwebs.pchpol.workplace.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 20. 오전 10:49:53
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 20. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.workplace.dao.WorkplaceDao;
import com.dwebs.pchpol.workplace.service.WorkplaceService;
import com.dwebs.pchpol.workplace.vo.WorkplaceDetail;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.workplace.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 20. 오전 10:49:53
 * </PRE>
 */
@Component("workplaceService")
public class WorkplaceServiceImpl implements WorkplaceService {
	@Autowired
	@Qualifier("workplaceDao")
	public WorkplaceDao workplaceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dwebs.pchpol.workplace.service.WorkplaceService#getDetailList(com.
	 * dwebs.pchpol.workplace.vo.WorkplaceDetail)
	 */
	@Override
	public List<WorkplacePlacementDetail> getDetailList(WorkplacePlacement wp, WorkplaceDetail workplaceDetail) {

		List<WorkplacePlacementDetail> detailList = new ArrayList<WorkplacePlacementDetail>();
		Map<String,int[]> timeMap = new HashMap<String,int[]>();
		timeMap.put("time1", workplaceDetail.getTime1());
		timeMap.put("time2", workplaceDetail.getTime2());
		timeMap.put("time3", workplaceDetail.getTime3());
		timeMap.put("time4", workplaceDetail.getTime4());
		timeMap.put("time5", workplaceDetail.getTime5());
		timeMap.put("time6", workplaceDetail.getTime6());
		timeMap.put("time7", workplaceDetail.getTime7());
		timeMap.put("time8", workplaceDetail.getTime8());
		timeMap.put("time9", workplaceDetail.getTime9());
		timeMap.put("time10", workplaceDetail.getTime10());
		timeMap.put("time11", workplaceDetail.getTime11());
		timeMap.put("time12", workplaceDetail.getTime12());
		timeMap.put("time13", workplaceDetail.getTime13());
		timeMap.put("time14", workplaceDetail.getTime14());
		timeMap.put("time15", workplaceDetail.getTime15());
		timeMap.put("time16", workplaceDetail.getTime16());
		timeMap.put("time17", workplaceDetail.getTime17());
		timeMap.put("time18", workplaceDetail.getTime18());
		timeMap.put("time19", workplaceDetail.getTime19());
		timeMap.put("time20", workplaceDetail.getTime20());
		timeMap.put("time21", workplaceDetail.getTime21());
		timeMap.put("time22", workplaceDetail.getTime22());
		timeMap.put("time23", workplaceDetail.getTime23());
		timeMap.put("time24", workplaceDetail.getTime24());

		for(int i=1;i<25;i++){
			String key = "time"+i;
			int[] time = timeMap.get(key);
			if(time!=null){
				for (int j = 0; j < time.length; j++) {
					WorkplacePlacementDetail wpd = new WorkplacePlacementDetail();
					detailList.add(wpd);
					wpd.setWorkplacePlacement(wp);
					wpd.setWorkplacePlacementDetailWorkTime(key);
					Unit unit = new Unit();
					unit.setUnitNo((time[j]));
					wpd.setUnit(unit);
				}
			}
		}
		
		return detailList;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.service.WorkplaceService#insertWorkplacement(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public void insertWorkplacement(WorkplacePlacement wp) {
		workplaceDao.deleteDuplicate(wp);
		this.insertOrUpdate(wp);
	}

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : WorkplaceServiceImpl
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 20. 오전 11:08:56
	 * </PRE>
	 *   @return void
	 *   @param wp
	 */
	private void insertOrUpdate(WorkplacePlacement wp) {
		workplaceDao.insertOrUpdate(wp);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.service.WorkplaceService#insertWorkplacementDetailList(java.util.List)
	 */
	@Override
	public void insertWorkplacementDetailList(List<WorkplacePlacementDetail> detailList) {
		workplaceDao.insertOrUpdate(detailList);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.service.WorkplaceService#findWp(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public List<WorkplacePlacement> findWp(WorkplacePlacement wp) {
		return workplaceDao.findWp(wp);
	}

}
