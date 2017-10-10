/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : AdminDao.java
 * 2. Package : com.dwebs.pchpol.admin
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 29. 오전 11:17:24
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.admin.dao;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AdminDao.java
 * 3. Package  : com.dwebs.pchpol.admin
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 29. 오전 11:17:24
 * </PRE>
 */
public interface AdminDao {
	
	public List<Admin> getAllAdmin();
	
	public Admin getAdmin(String adminId);
	
	public Admin getAdmin(String adminId, String adminPassword);

	public Admin setAdmin(Admin Admin);

	public String getUserDisplayInfo(String adminId);

	/**
	 * <PRE>
	 * 1. MethodName : getList
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 10. 오후 12:28:57
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
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 10. 오후 12:29:00
	 * </PRE>
	 *   @return long
	 *   @param pagingVO
	 *   @return
	 */
	public int getTotCnt(PagingVO pagingVO);
}
