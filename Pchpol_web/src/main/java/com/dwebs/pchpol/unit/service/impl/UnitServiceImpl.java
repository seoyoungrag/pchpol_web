/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : UnitServiceImpl.java
 * 2. Package : com.dwebs.pchpol.unit.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 13. 오전 9:44:55
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 13. :            : 신규 개발.
 */
package com.dwebs.pchpol.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.unit.dao.UnitDao;
import com.dwebs.pchpol.unit.service.UnitService;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : UnitServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.unit.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 13. 오전 9:44:55
 * </PRE>
 */
@Component("unitService")
public class UnitServiceImpl implements UnitService {
	@Autowired
	@Qualifier("unitDao")
	public UnitDao unitDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.service.UnitService#getList(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public List<Unit> getList(PagingVO pagingVO) {
		return unitDao.getList(pagingVO);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.service.UnitService#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO) {
		return unitDao.getTotCnt(pagingVO);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.service.UnitService#getListByType(com.dwebs.pchpol.common.vo.PagingVO, java.lang.String)
	 */
	@Override
	public List<Unit> getListByType(PagingVO pagingVO, String type) {
		return unitDao.getListByType(pagingVO, type);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.service.UnitService#getTotCntByType(com.dwebs.pchpol.common.vo.PagingVO, java.lang.String)
	 */
	@Override
	public int getTotCntByType(PagingVO pagingVO, String type) {
		return unitDao.getTotCntByType(pagingVO, type);
	}

}
