/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : FacilityServiceImpl.java
 * 2. Package : com.dwebs.pchpol.facility.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 11:43:33
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.facility.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.facility.dao.FacilityDao;
import com.dwebs.pchpol.facility.service.FacilityService;
import com.dwebs.pchpol.model.Facility;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : FacilityServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.facility.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 11:43:33
 * </PRE>
 */
@Component("facilityService")
public class FacilityServiceImpl implements FacilityService {
	
	@Autowired
	@Qualifier("facilityDao")
	public FacilityDao facilityDao;
	
	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.service.FacilityService#insertFacility(com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public void insertFacility(Facility facility) {
		//중복제거 기준 컬럼은 우편번호, 상호명이다.
		//facilityDao.deleteDuplicate(facility);
		//중복제거 하지 않고 id를 반환해서 수정한다.
		Facility oriFacility = this.findFacility(facility);
		if(oriFacility!=null){
			facility.setFacilityNo(oriFacility.getFacilityNo());
		}
		this.insertOrUpdate(facility);
	}

	/**
	 * <PRE>
	 * 1. MethodName : findFacility
	 * 2. ClassName  : FacilityServiceImpl
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 4:09:25
	 * </PRE>
	 *   @return Facility
	 *   @param facility
	 *   @return
	 */
	private Facility findFacility(Facility facility) {
		List<Facility> list = facilityDao.getFacility(facility);
		if(list.size()==0){
			return null;
		}
		return facilityDao.getFacility(facility).get(0);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.service.FacilityService#insertOrUpdate(com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public void insertOrUpdate(Facility facility) {
		facilityDao.insertOrUpdate(facility);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.service.FacilityService#getFacility(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public List<Facility> getFacility(PagingVO pagingVO, Facility facility) {
		return facilityDao.getFacility(pagingVO, facility);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.service.FacilityService#getTotCntFacility(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public int getTotCntFacility(PagingVO pagingVO, Facility facility) {
		return facilityDao.getTotCntFacility(pagingVO, facility);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.service.FacilityService#getById(int)
	 */
	@Override
	public Facility getById(int facilityNo) {
		return facilityDao.getById(facilityNo);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.service.FacilityService#deleteByIds(java.util.List)
	 */
	@Override
	public void deleteByIds(List<Integer> ids) {
		facilityDao.deleteByIds(ids);
	}
}
