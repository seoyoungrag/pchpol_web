/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : AdminServiceImpl.java
 * 2. Package : com.dwebs.pchpol.admin
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 29. 오후 1:40:46
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.admin.dao.AdminDao;
import com.dwebs.pchpol.admin.service.AdminService;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;

@Component("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	@Qualifier("adminDao")
	public AdminDao adminDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminService#getAllAdmin()
	 */
	@Override
	public List<Admin> getAllAdmin() {
		return adminDao.getAllAdmin();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminService#getAdmin(java.lang.String)
	 */
	@Override
	public Admin getAdmin(String adminId) {
		return adminDao.getAdmin(adminId);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminService#getAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin getAdmin(String adminId, String adminPassword) {
		return adminDao.getAdmin(adminId, adminPassword);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminService#setAdmin(com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public Admin setAdmin(Admin Admin) {
		return adminDao.setAdmin(Admin);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.service.AdminService#getList(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public List<Admin> getList(PagingVO pagingVO) throws Exception {
		return adminDao.getList(pagingVO);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.service.AdminService#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO) {
		return adminDao.getTotCnt(pagingVO);
	}

}
