/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : UnitDaoImpl.java
 * 2. Package : com.dwebs.pchpol.unit.dao.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 13. 오전 9:46:20
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 13. :            : 신규 개발.
 */
package com.dwebs.pchpol.unit.dao.impl;

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
import com.dwebs.pchpol.model.Code_;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.model.Unit_;
import com.dwebs.pchpol.unit.dao.UnitDao;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : UnitDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.unit.dao.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 13. 오전 9:46:20
 * </PRE>
 */
@Component("unitDao")
public class UnitDaoImpl implements UnitDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getListByTroopsTypeAndCode(com.dwebs.pchpol.common.vo.PagingVO, java.lang.String, com.dwebs.pchpol.model.Code)
	 */
	@Override
	public List<Unit> getListByUnit(PagingVO pagingVO, Unit unit) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Unit> cQuery = builder.createQuery(Unit.class);
		Root<Unit> from = cQuery.from(Unit.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(Unit_.troopsType), unit.getTroopsType()));
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(!pagingVO.getSearchType().equals("")){
			restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}

		//지방청, 구분, 소속, 세부소속
		if(unit.getCode1()!=null){
			if((unit.getCode1().getCode1depth()!=null) && (!unit.getCode1().getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code1depth), unit.getCode1().getCode1depth()));
			}
			if((unit.getCode1().getCode2depth()!=null) && (!unit.getCode1().getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code2depth), unit.getCode1().getCode2depth()));
			}
			if((unit.getCode1().getCode3depth()!=null) && (!unit.getCode1().getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code3depth), unit.getCode1().getCode3depth()));
			}
			if((unit.getCode1().getCode4depth()!=null) && (!unit.getCode1().getCode4depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code4depth), unit.getCode1().getCode4depth()));
			}
		}
		//임무
		if(unit.getCode3()!=null){
			if((unit.getCode3().getCode1depth()!=null) && (!unit.getCode3().getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code3).get(Code_.code1depth), unit.getCode3().getCode1depth()));
			}	
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
		TypedQuery<Unit> typedQuery = em.createQuery(cQuery);
		List<Unit> result = new ArrayList<Unit>();
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
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getTotCntByTroopsTypeAndCode(com.dwebs.pchpol.common.vo.PagingVO, java.lang.String, com.dwebs.pchpol.model.Code)
	 */
	@Override
	public int getTotCntByUnit(PagingVO pagingVO, Unit unit) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Unit> from = cQuery.from(Unit.class);
		cQuery.select(builder.count(from));

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(Unit_.troopsType), unit.getTroopsType()));
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(!pagingVO.getSearchType().equals("")){
			restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}

		//지방청, 구분, 소속, 세부소속
		if(unit.getCode1()!=null){
			if((unit.getCode1().getCode1depth()!=null) && (!unit.getCode1().getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code1depth), unit.getCode1().getCode1depth()));
			}
			if((unit.getCode1().getCode2depth()!=null) && (!unit.getCode1().getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code2depth), unit.getCode1().getCode2depth()));
			}
			if((unit.getCode1().getCode3depth()!=null) && (!unit.getCode1().getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code3depth), unit.getCode1().getCode3depth()));
			}
			if((unit.getCode1().getCode4depth()!=null) && (!unit.getCode1().getCode4depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code1).get(Code_.code4depth), unit.getCode1().getCode4depth()));
			}
		}
		//임무
		if(unit.getCode3()!=null){
			if((unit.getCode3().getCode1depth()!=null) && (!unit.getCode3().getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(Unit_.code3).get(Code_.code1depth), unit.getCode3().getCode1depth()));
			}	
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//쿼리를 실행한다.
		Long result = em.createQuery(cQuery).getSingleResult();
		
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#insertOrUpdate(com.dwebs.pchpol.model.Unit)
	 */
	@Override
	public void insertOrUpdate(Unit unit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(unit.getUnitNo()>0){
			em.merge(unit);
		}else{
			em.persist(unit);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getById(java.lang.String)
	 */
	@Override
	public Unit getById(String id) {
		EntityManager em = emf.createEntityManager();
		Unit result = em.find(Unit.class, Integer.parseInt(id));
		return result;
	}

}
