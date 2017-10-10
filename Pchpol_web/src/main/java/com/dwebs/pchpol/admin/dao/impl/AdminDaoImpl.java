/**
 * 0. Project  : 문서변환서버
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
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.admin.dao.AdminDao;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Admin_;

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
@Transactional
public class AdminDaoImpl implements AdminDao {
	@PersistenceContext
	private EntityManager em;
	/*
    SessionFactory sessionFactory;
    
	public AdminDaoImpl(SessionFactory sessionfactory){
	    setSessionFactory(sessionfactory);
	}
 */
	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminDao#getAdmin(java.lang.String)
	 */
	@Override
	public Admin getAdmin(String adminId) {
		/*
		//Get Session
		Session session = sessionFactory.getCurrentSession();
		
		// Create CriteriaBuilder
		CriteriaBuilder cb = session.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
		
		// Create Root(query) 
		Root<Admin> root = query.from(Admin.class);
		query.select(root)
			.where(cb.equal(root.get(Admin_.adminId), adminId));
		
		// Excute Root
		Admin result = session.createQuery(query).getSingleResult();

		return result;*/
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminDao#getAllAdmin()
	 */
	@Transactional
	@Override
	public List<Admin> getAllAdmin() {
		/*Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
		Root<Admin> root = query.from(Admin.class);
		query.select(root);
		List<Admin> result = session.createQuery(query).getResultList();
		return result;
*/
		return null;
	}
	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminDao#getAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public Admin getAdmin(String adminId, String adminPassword) {
		/*Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Admin> query = cb.createQuery(Admin.class);
		Root<Admin> root = query.from(Admin.class);
		query.select(root)
			.where(cb.equal(root.get(Admin_.adminId), adminId))
			.where(cb.equal(root.get(Admin_.adminPassword), adminPassword));
		Admin result = session.createQuery(query).getSingleResult();

		return result;*/
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminDao#setAdmin(com.dwebs.pchpol.admin.Admin)
	 */
	@Override
	public Admin setAdmin(Admin admin) {
		/*Session session = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			if(admin.getAdminNo()==0){
				session.persist(admin);
			}else{
				session.update(admin);
			}
			tx.commit();
		} catch (DataAccessException e) {
			e.printStackTrace();
			//throw new MessageException(Utility.getRootCause(e).getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			//throw new MessageException(Utility.getRootCause(e).getMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();
		}*/
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.AdminDao#getUserDisplayInfo(java.lang.String)
	 */
	@Override
	public String getUserDisplayInfo(String adminId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.dao.AdminDao#getList(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public List<Admin> getList(PagingVO pagingVO) throws Exception{
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


}
