/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AttachDaoImpl.java
 * 2. Package : com.dwebs.pchpol.attach.dao.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 10:35:38
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.attach.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.attach.dao.AttachDao;
import com.dwebs.pchpol.model.Attach;
import com.dwebs.pchpol.model.Attach_;
import com.dwebs.pchpol.model.Board_;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AttachDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.attach.dao.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 10:35:38
 * </PRE>
 */
@Component("attachDao")
public class AttachDaoImpl implements AttachDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.dao.AttachDao#insertAttaches(java.util.List)
	 */
	@Override
	public void insertAttaches(List<Attach> attaches) {
		EntityManager em = emf.createEntityManager();
		if(attaches.size()>0){
			em.getTransaction().begin();
			for(Attach at : attaches){
				em.persist(at);
			}
			em.getTransaction().commit();
		}
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.dao.AttachDao#deleteByBoard(com.dwebs.pchpol.model.Attach)
	 */
	@Override
	public void deleteByBoard(Attach at) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Attach> cQuery = builder.createQuery(Attach.class);
		Root<Attach> from = cQuery.from(Attach.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(Attach_.board).get(Board_.boardNo), at.getBoard().getBoardNo()));

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Attach> typedQuery = em.createQuery(cQuery);
		List<Attach> deletingList = typedQuery.getResultList();
		if(deletingList.size()>0){
			em.getTransaction().begin();
			for(Attach tp : deletingList){
				em.remove(tp);
			}
			em.getTransaction().commit();
		}
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.dao.AttachDao#deleteFiles(java.util.List)
	 */
	@Override
	public void deleteFiles(List<Integer> deletingFileArr) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Attach> cQuery = builder.createQuery(Attach.class);
		Root<Attach> from = cQuery.from(Attach.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		Expression<Integer> exp = from.get(Attach_.attachNo);
		restrictions.add(exp.in(deletingFileArr));

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Attach> typedQuery = em.createQuery(cQuery);
		List<Attach> deletingList = typedQuery.getResultList();
		if(deletingList.size()>0){
			em.getTransaction().begin();
			for(Attach tp : deletingList){
				em.remove(tp);
			}
			em.getTransaction().commit();
		}
		em.close();
	}
}
