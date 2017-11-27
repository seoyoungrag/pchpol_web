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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.facility.vo.TroopsPlacementWithFacilities;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.troops.dao.TroopsDao;
import com.dwebs.pchpol.troops.service.TroopsService;
import com.dwebs.pchpol.workplace.controller.TroopsPlacementDelete;
import com.dwebs.pchpol.workplace.controller.TroopsPlacementModel;
import com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops;

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
	public List<TroopsPlacement> getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch) {
		return troopsDao.getTroopsWorkplace(troopsWorkplaceSearch);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTroopsFacilityListByTroops(com.dwebs.pchpol.model.TroopsFacilityPlacement)
	 */
	@Override
	public List<TroopsFacilityPlacement> getTroopsFacilityListByTroops(TroopsFacilityPlacement troopsFacilitySearch) {
		return troopsDao.getTroopsFacilityListByTroops(troopsFacilitySearch);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#insertOrUpdate(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public void insertOrUpdate(TroopsPlacement troopsPlacement) {
		troopsDao.insertOrUpdate(troopsPlacement);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#insertTroopsWorkplacePlacement(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public void insertTroopsWorkplacePlacement(TroopsPlacement troopsPlacement) {
		troopsDao.deleteDuplicate(troopsPlacement);
		this.insertOrUpdate(troopsPlacement);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getById(java.lang.String)
	 */
	@Override
	public TroopsPlacement getById(String troopsPlacementNo) {
		return troopsDao.getByid(troopsPlacementNo);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTroopsWorkplaceGroupByTroopsCode3depth(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops)
	 */
	@Override
	public List<WorkplaceWithTroops> getTroopsWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO,
			WorkplaceWithTroops workplaceWithTroops) {
		return troopsDao.getTroopsWorkplaceGroupByTroopsCode3depth(pagingVO, workplaceWithTroops);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTotCntWorkplaceGroupByTroopsCode3depth(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops)
	 */
	@Override
	public int getTotCntWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO, WorkplaceWithTroops workplaceWithTroops) {
		return troopsDao.getTotCntWorkplaceGroupByTroopsCode3depth(pagingVO, workplaceWithTroops);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTroopsWorkplace(com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops)
	 */
	@Override
	public List<TroopsPlacement> getTroopsWorkplace(WorkplaceWithTroops tp) {
		//'배치지역, 대회시설, 지방청, 구분, 부대명'까지 그룹화한 값으로 TroopsPlacement 테이블에서 조회해서 가져온다.
		Code searchTroops = new Code();
		if(tp.getTroops().getCode1depth()==null){
			//부대없으면 통과
			return new ArrayList<TroopsPlacement>();
		}
		searchTroops.setCode1depth(tp.getTroops().getCode1depth());
		searchTroops.setCode2depth(tp.getTroops().getCode2depth());
		searchTroops.setCode3depth(tp.getTroops().getCode3depth());
		Code searchWorkplace = new Code();
		searchWorkplace.setCode1depth(tp.getWorkplace().getCode1depth());
		searchWorkplace.setCode2depth(tp.getWorkplace().getCode2depth());
		searchWorkplace.setCode3depth(tp.getWorkplace().getCode3depth());
		TroopsPlacement tpSearch = new TroopsPlacement();
		tpSearch.setCode1(searchTroops);
		tpSearch.setCode2(searchWorkplace);
		return troopsDao.getTroopsWorkplace(tpSearch);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getWorkplaceTroops(com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops)
	 */
	@Override
	public List<WorkplacePlacement> getWorkplaceTroops(WorkplaceWithTroops wwt) {
		//'배치지역, 대회시설'까지 그룹화한 값으로 WorkplacePlacement 테이블에서 조회해서 가져온다.
		//Code searchWorkplace = new Code();
		//searchWorkplace.setCode1depth(wwt.getWorkplace().getCode1depth());
		//searchWorkplace.setCode2depth(wwt.getWorkplace().getCode2depth());
		//searchWorkplace.setCodeNo(wwt.getWorkplace().getCodeNo());
		WorkplacePlacement wp = new WorkplacePlacement();
		wp.setCode2(wwt.getWorkplace());
		wp.setWorkplacePlacementWorkDt(wwt.getMobilDate());
		return troopsDao.getWorkplaceTroops(wp);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getWorkplaceTroops(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public WorkplacePlacement getWorkplaceTroops(WorkplacePlacement wps) {
		List<WorkplacePlacement> list = troopsDao.getWorkplaceTroops(wps);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getWorkplaceTroops(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public List<WorkplacePlacement> getWorkplaceTroops(PagingVO pagingVO, WorkplacePlacement wp) {
		return troopsDao.getWorkplaceTroops(pagingVO, wp);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getTotCntWorkplaceTroops(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public int getTotCntWorkplaceTroops(PagingVO pagingVO, WorkplacePlacement wp) {
		return troopsDao.getTotCntWorkplaceTroops(pagingVO,wp);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#getWorkplacePlacement(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public WorkplacePlacement getWorkplacePlacement(WorkplacePlacement wp) {
		return troopsDao.getWorkplacePlacement(wp);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#insertTroopsFacilityPlacement(com.dwebs.pchpol.facility.vo.TroopsPlacementWithFacilities)
	 */
	@Override
	public void insertTroopsFacilityPlacement(TroopsPlacementWithFacilities troopsFacilities) {
		troopsDao.deleteDuplicate(troopsFacilities);
		this.insertOrUpdate(troopsFacilities);
	}

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : TroopsServiceImpl
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 1:24:03
	 * </PRE>
	 *   @return void
	 *   @param troopsFacilities
	 */
	private void insertOrUpdate(TroopsPlacementWithFacilities troopsFacilities) {
		troopsDao.insertOrUpdate(troopsFacilities);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#deleteWorkplaceTroopsByIds(java.util.List)
	 */
	@Override
	public void deleteWorkplaceTroopsByIds(List<Integer> ids) {
		troopsDao.deleteWorkplaceTroopsByIds(ids);
		
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#insertTroopsWorkplacePlacement(com.dwebs.pchpol.workplace.controller.TroopsPlacementModel)
	 */
	@Override
	public void insertTroopsWorkplacePlacement(TroopsPlacementModel troopsPlacement) {
		TroopsPlacement tp = new TroopsPlacement();
		Code code2 = new Code();
		code2.setCodeNo(troopsPlacement.getCode2());
		tp.setCode2(code2);
		if(troopsPlacement.getCode1()!=null&&troopsPlacement.getCode1().size()>0){
			for(int no : troopsPlacement.getCode1()){
				tp.setTroopsPlacementNo(0);
				Code code1 = new Code();
				code1.setCodeNo(no);
				tp.setCode1(code1);
				troopsDao.deleteDuplicate(tp);
				this.insertOrUpdate(tp);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.service.TroopsService#deleteTroopsPlacement(com.dwebs.pchpol.workplace.controller.TroopsPlacementDelete)
	 */
	@Override
	public void deleteTroopsPlacement(TroopsPlacementDelete delete) {
		TroopsPlacement stp = new TroopsPlacement();
		stp.setCode1(delete.getTroops());
		stp.setCode2(delete.getWorkplace());
		List<TroopsPlacement> list = troopsDao.getTroopsWorkplace(stp);
		troopsDao.deleteTroopsPlacement(list);
	}


}
