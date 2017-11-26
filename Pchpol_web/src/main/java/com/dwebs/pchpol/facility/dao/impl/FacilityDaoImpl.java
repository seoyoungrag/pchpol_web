/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : FacilityDaoImpl.java
 * 2. Package : com.dwebs.pchpol.facility.dao.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 11:49:13
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.facility.dao.impl;

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

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.facility.dao.FacilityDao;
import com.dwebs.pchpol.model.Facility;
import com.dwebs.pchpol.model.Facility_;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : FacilityDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.facility.dao.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 11:49:13
 * </PRE>
 */
@Component("facilityDao")
public class FacilityDaoImpl implements FacilityDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.dao.FacilityDao#deleteDuplicate(com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public void deleteDuplicate(Facility facility) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Facility> cQuery = builder.createQuery(Facility.class);
		Root<Facility> from = cQuery.from(Facility.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(Facility_.facilityName), facility.getFacilityName()));
		restrictions.add(builder.equal(from.get(Facility_.facilityAddrZip), facility.getFacilityAddrZip()));

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Facility> typedQuery = em.createQuery(cQuery);
		List<Facility> deletingList = typedQuery.getResultList();
		if(deletingList.size()>0){
			em.getTransaction().begin();
			for(Facility f : deletingList){
				em.remove(f);
			}
			em.getTransaction().commit();
		}
		em.close();
		
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.dao.FacilityDao#insertOrUpdate(com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public void insertOrUpdate(Facility facility) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(facility.getFacilityNo()>0){
			em.merge(facility);
		}else{
			em.persist(facility);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.dao.FacilityDao#getFacility(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public List<Facility> getFacility(PagingVO pagingVO, Facility facility) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Facility> cQuery = builder.createQuery(Facility.class);
		Root<Facility> from = cQuery.from(Facility.class);

		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(facility.getFacilityType()!=null){
			restrictions.add(builder.equal(from.get(Facility_.facilityType), facility.getFacilityType()));
		}
		
		if(!pagingVO.getSearchType().equals("")){
			restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//정렬순서조건이 있는경우 정렬순서 조건 쿼리를 생성한다.
		if(!pagingVO.getSidx().equals("")&&!pagingVO.getSord().equals("")){
			if(pagingVO.getSord().equals("asc")){
				cQuery.orderBy(builder.asc(from.get(pagingVO.getSidx())));
			}else{
				cQuery.orderBy(builder.desc(from.get(pagingVO.getSidx())));
			}
		}
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Facility> typedQuery = em.createQuery(cQuery);
		List<Facility> result = new ArrayList<Facility>();
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
	 * @see com.dwebs.pchpol.facility.dao.FacilityDao#getTotCntFacility(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public int getTotCntFacility(PagingVO pagingVO, Facility facility) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Facility> from = cQuery.from(Facility.class);
		cQuery.select(builder.count(from));

		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(facility.getFacilityType()!=null){
			restrictions.add(builder.equal(from.get(Facility_.facilityType), facility.getFacilityType()));
		}
		
		if(!pagingVO.getSearchType().equals("")){
			restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}
		
		//쿼리를 실행한다.
		Long result = em.createQuery(cQuery).getSingleResult();
		
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.dao.FacilityDao#getById(int)
	 */
	@Override
	public Facility getById(int facilityNo) {
		EntityManager em = emf.createEntityManager();
		Facility result = em.find(Facility.class, facilityNo);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.facility.dao.FacilityDao#getFacility(com.dwebs.pchpol.model.Facility)
	 */
	@Override
	public List<Facility> getFacility(Facility facility) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Facility> cQuery = builder.createQuery(Facility.class);
		Root<Facility> from = cQuery.from(Facility.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(Facility_.facilityName), facility.getFacilityName()));
		restrictions.add(builder.equal(from.get(Facility_.facilityAddrZip), facility.getFacilityAddrZip()));

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Facility> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList();
	}

}
