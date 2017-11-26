/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AddrService.java
 * 2. Package : com.dwebs.pchpol.addr.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 2:30:26
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.addr.service;

import java.util.List;

import com.dwebs.pchpol.addr.vo.Addr;
import com.dwebs.pchpol.common.vo.PagingVO;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AddrService.java
 * 3. Package  : com.dwebs.pchpol.addr.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 2:30:26
 * </PRE>
 */
public interface AddrService {

	/**
	 * <PRE>
	 * 1. MethodName : getAddrList
	 * 2. ClassName  : AddrService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 2:30:33
	 * </PRE>
	 *   @return List<Addr>
	 *   @param addr
	 *   @return
	 */
	List<Addr> getAddrList(PagingVO pagingVO, Addr addr);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntAddr
	 * 2. ClassName  : AddrService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 2:30:36
	 * </PRE>
	 *   @return int
	 *   @param addr
	 *   @return
	 */
	int getTotCntAddr(PagingVO pagingVO, Addr addr);

}
