/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceDaoImpl.java
 * 2. Package : com.dwebs.pchpol.workplace.dao.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 20. 오전 11:09:34
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 20. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.model.Code_;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.model.WorkplacePlacementDetail_;
import com.dwebs.pchpol.model.WorkplacePlacement_;
import com.dwebs.pchpol.workplace.dao.WorkplaceDao;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.workplace.dao.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 20. 오전 11:09:34
 * </PRE>
 */
@Component("workplaceDao")
public class WorkplaceDaoImpl implements WorkplaceDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.dao.WorkplaceDao#deleteDuplicate(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public void deleteDuplicate(WorkplacePlacement wp) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<WorkplacePlacement> cQuery = builder.createQuery(WorkplacePlacement.class);
		Root<WorkplacePlacement> from = cQuery.from(WorkplacePlacement.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.codeNo), wp.getCode1().getCodeNo()));
		restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.codeNo), wp.getCode2().getCodeNo()));

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<WorkplacePlacement> typedQuery = em.createQuery(cQuery);
		List<WorkplacePlacement> deletingList = typedQuery.getResultList();
		
		em.getTransaction().begin();
		for(WorkplacePlacement tp : deletingList){
			em.remove(tp);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.dao.WorkplaceDao#insertOrUpdate(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public void insertOrUpdate(WorkplacePlacement wp) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(wp.getWorkplacePlacementNo()>0){
			em.merge(wp);
		}else{
			em.persist(wp);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.dao.WorkplaceDao#insertOrUpdate(java.util.List)
	 */
	@Override
	public void insertOrUpdate(List<WorkplacePlacementDetail> detailList) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for(WorkplacePlacementDetail wpd : detailList){
			//id가 0보다 크면 update
			if(wpd.getWorkplacePlacementDetailNo()>0){
				em.merge(wpd);
			}else{
				em.persist(wpd);
			}	
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.workplace.dao.WorkplaceDao#findWp(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public List<WorkplacePlacement> findWp(WorkplacePlacement wp) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<WorkplacePlacement> cQuery = builder.createQuery(WorkplacePlacement.class);
		Root<WorkplacePlacement> from = cQuery.from(WorkplacePlacement.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(WorkplacePlacement_.workplacePlacementWorkDt), wp.getWorkplacePlacementWorkDt()));

		Join<WorkplacePlacement, WorkplacePlacementDetail> wpdJoin = from.join(WorkplacePlacement_.workplacePlacementDetail);
		
		Expression<Unit> exp = wpdJoin.get(WorkplacePlacementDetail_.unit);
		List<Unit> inUnit = new ArrayList<Unit>();
		if(wp.getWorkplacePlacementDetail()!=null&&wp.getWorkplacePlacementDetail().size()>0){
			for(WorkplacePlacementDetail wpd : wp.getWorkplacePlacementDetail()){
				inUnit.add(wpd.getUnit());
			}
		}
		Predicate inUnitPre = exp.in(inUnit);
		
		restrictions.add(inUnitPre);
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<WorkplacePlacement> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList();
	}

}
