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
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getList(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public List<Unit> getList(PagingVO pagingVO) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Unit> cQuery = builder.createQuery(Unit.class);
		Root<Unit> from = cQuery.from(Unit.class);

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
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Unit> entityRoot = cQuery.from(Unit.class);
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
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getListByType(com.dwebs.pchpol.common.vo.PagingVO, java.lang.String)
	 */
	@Override
	public List<Unit> getListByType(PagingVO pagingVO, String type) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Unit> cQuery = builder.createQuery(Unit.class);
		Root<Unit> from = cQuery.from(Unit.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(Unit_.troopsType), type));
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
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
	 * @see com.dwebs.pchpol.unit.dao.UnitDao#getTotCntByType(com.dwebs.pchpol.common.vo.PagingVO, java.lang.String)
	 */
	@Override
	public int getTotCntByType(PagingVO pagingVO, String type) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Unit> entityRoot = cQuery.from(Unit.class);
		cQuery.select(builder.count(entityRoot));

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(entityRoot.get(Unit_.troopsType), type));
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(!pagingVO.getSearchType().equals("")){
			restrictions.add(builder.like(entityRoot.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//쿼리를 실행한다.
		Long result = em.createQuery(cQuery).getSingleResult();
		
		return result.intValue();
	}

}
