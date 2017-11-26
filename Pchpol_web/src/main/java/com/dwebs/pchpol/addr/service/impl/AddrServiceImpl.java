/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AddrServiceImpl.java
 * 2. Package : com.dwebs.pchpol.addr.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 2:31:03
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.addr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.addr.dao.AddrDao;
import com.dwebs.pchpol.addr.service.AddrService;
import com.dwebs.pchpol.addr.vo.Addr;
import com.dwebs.pchpol.common.vo.PagingVO;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AddrServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.addr.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 2:31:03
 * </PRE>
 */
@Component("addrService")
public class AddrServiceImpl implements AddrService {
	@Autowired
	@Qualifier("addrDao")
	public AddrDao addrDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.addr.service.AddrService#getAddrList(com.dwebs.pchpol.addr.vo.Addr)
	 */
	@Override
	public List<Addr> getAddrList(PagingVO pagingVO, Addr addr) {
		return addrDao.getAddrList(pagingVO, addr);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.addr.service.AddrService#getTotCntAddr(com.dwebs.pchpol.addr.vo.Addr)
	 */
	@Override
	public int getTotCntAddr(PagingVO pagingVO, Addr addr) {
		return addrDao.getTotCntAddr(pagingVO, addr);
	}

}
