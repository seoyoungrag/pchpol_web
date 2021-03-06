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

	/**
	 * <PRE>
	 * 1. MethodName : reg
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 1:52:13
	 * </PRE>
	 *   @return void
	 *   @param admin
	 */
	public void insertOrUpdate(Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 4:00:22
	 * </PRE>
	 *   @return Admin
	 *   @param id
	 *   @return
	 */
	public Admin getById(String id);

	/**
	 * <PRE>
	 * 1. MethodName : login
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 29. 오전 3:45:06
	 * </PRE>
	 *   @return Admin
	 *   @param admin
	 *   @return
	 */
	public Admin login(Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : deleteByIds
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 16. 오후 2:01:51
	 * </PRE>
	 *   @return void
	 *   @param ids
	 */
	public void deleteByIds(List<Integer> ids);

	/**
	 * <PRE>
	 * 1. MethodName : loginById
	 * 2. ClassName  : AdminDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 12. 8. 오후 3:08:00
	 * </PRE>
	 *   @return Admin
	 *   @param admin
	 *   @return
	 */
	public Admin loginById(Admin admin);
}
