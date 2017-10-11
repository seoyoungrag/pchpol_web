/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AdminDaoImpl.java
 * 2. Package : com.dwebs.pchpol.admin
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 29. 오전 11:20:01
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.admin.dao.AdminDao;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AdminDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.admin
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 29. 오전 11:20:01
 * </PRE>
 */
@Component("adminDao")
public class AdminDaoImpl implements AdminDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.dao.AdminDao#getList(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public List<Admin> getList(PagingVO pagingVO) throws Exception{
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Admin> cQuery = builder.createQuery(Admin.class);
		Root<Admin> from = cQuery.from(Admin.class);

		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(!pagingVO.getSearchType().equals("")){
			Predicate restriction = builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%");
			cQuery.where(restriction);
		}
		
		//정렬순서조건이 있는경우 정렬순서 조건 쿼리를 생성한다.
		if(!pagingVO.getSidx().equals("")&&!pagingVO.getSord().equals("")){
			if(pagingVO.getSord().equals("asc")){
				cQuery.orderBy(builder.asc(from.get(pagingVO.getSidx())));
			}else{
				cQuery.orderBy(builder.desc(from.get(pagingVO.getSidx())));
			}
		}
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Admin> typedQuery = em.createQuery(cQuery);
		List<Admin> result = new ArrayList<Admin>();
		if(pagingVO.getPage()>0){
			int page = pagingVO.getPage();
			int pageSize = pagingVO.getRows();
			int startIndex =( (page -1) * pageSize);	
			typedQuery.setFirstResult(startIndex);
			typedQuery.setMaxResults(pageSize);
		}
		
		result = typedQuery.getResultList();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.dao.AdminDao#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Admin> entityRoot = cQuery.from(Admin.class);
		cQuery.select(builder.count(entityRoot));
		
		//검색조건이 있는경우 정렬순서 조건 쿼리를 생성한다.
		if(!pagingVO.getSearchType().equals("")){
			cQuery.where(builder.like(entityRoot.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}
		
		//쿼리를 실행한다.
		Long result = em.createQuery(cQuery).getSingleResult();
		
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.dao.AdminDao#reg(com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public void insertOrUpdate(Admin admin) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(admin.getAdminNo()>0){
			em.merge(admin);
		}else{
			em.persist(admin);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.dao.AdminDao#getById(java.lang.String)
	 */
	@Override
	public Admin getById(String id) {
		EntityManager em = emf.createEntityManager();
		Admin result = em.find(Admin.class, Integer.parseInt(id));
		return result;
	}

}
