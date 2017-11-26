/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AdminService.java
 * 2. Package : com.dwebs.pchpol.admin
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 29. 오후 1:40:21
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.admin.service;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AdminService.java
 * 3. Package  : com.dwebs.pchpol.admin
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 29. 오후 1:40:21
 * </PRE>
 */
public interface AdminService {

	/**
	 * <PRE>
	 * 1. MethodName : getList
	 * 2. ClassName  : AdminService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 10. 오후 12:20:01
	 * </PRE>
	 *   @return List<Admin>
	 *   @param pagingVO
	 *   @return
	 * @throws Exception 
	 */
	public List<Admin> getList(PagingVO pagingVO) throws Exception;

	/**
	 * <PRE>
	 * 1. MethodName : getTotCnt
	 * 2. ClassName  : AdminService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 10. 오후 12:20:06
	 * </PRE>
	 *   @return long
	 *   @param pagingVO
	 *   @return
	 */
	public int getTotCnt(PagingVO pagingVO);

	/**
	 * <PRE>
	 * 1. MethodName : reg
	 * 2. ClassName  : AdminService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 1:51:08
	 * </PRE>
	 *   @return void
	 *   @param admin
	 */
	public void insertOrUpdate(Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : AdminService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 3:59:02
	 * </PRE>
	 *   @return List<Code>
	 *   @param adminNo
	 *   @return
	 */
	public Admin getById(String adminNo);

	/**
	 * <PRE>
	 * 1. MethodName : login
	 * 2. ClassName  : AdminService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 29. 오전 3:44:29
	 * </PRE>
	 *   @return Admin
	 *   @param admin
	 *   @return
	 */
	public Admin login(Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : deleteByIds
	 * 2. ClassName  : AdminService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 16. 오후 2:01:19
	 * </PRE>
	 *   @return void
	 *   @param ids
	 */
	public void deleteByIds(List<Integer> ids);

}
