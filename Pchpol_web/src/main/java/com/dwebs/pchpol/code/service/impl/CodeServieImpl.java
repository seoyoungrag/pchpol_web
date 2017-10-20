/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CodeServieImpl.java
 * 2. Package : com.dwebs.pchpol.code.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 11. 오후 1:18:57
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 11. :            : 신규 개발.
 */
package com.dwebs.pchpol.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.code.dao.CodeDao;
import com.dwebs.pchpol.code.service.CodeService;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Code;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CodeServieImpl.java
 * 3. Package  : com.dwebs.pchpol.code.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 11. 오후 1:18:57
 * </PRE>
 */
@Component("codeService")
public class CodeServieImpl implements CodeService {
	@Autowired
	@Qualifier("codeDao")
	public CodeDao codeDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.service.CodeService#getCodeListByCategory(java.lang.String)
	 */
	@Override
	public List<Code> getCodeListByCategory(String category) {
		return codeDao.getCodeListByCategory(category);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.service.CodeService#getCode(com.dwebs.pchpol.model.Code)
	 */
	@Override
	public Code getCode(Code code) {
		return codeDao.getCode(code);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.service.CodeService#getCodeListByCode(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Code)
	 */
	@Override
	public List<Code> getCodeListByCode(PagingVO pagingVO, Code troopsSearch) {
		return codeDao.getCodeListByCode(pagingVO, troopsSearch);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.service.CodeService#getTotCntByCode(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Code)
	 */
	@Override
	public int getTotCntByCode(PagingVO pagingVO, Code troopsSearch) {
		return codeDao.getTotCntByCode(pagingVO, troopsSearch);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.service.CodeService#getCodeList(com.dwebs.pchpol.model.Code)
	 */
	@Override
	public List<Code> getCodeList(Code searchTroops) {
		return codeDao.getCodeList(searchTroops);
	}



}
