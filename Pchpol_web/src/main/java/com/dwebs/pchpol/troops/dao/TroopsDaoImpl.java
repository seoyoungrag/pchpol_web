/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : TroopsDaoImpl.java
 * 2. Package : com.dwebs.pchpol.troops.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 12:59:32
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.troops.dao;

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

import com.dwebs.pchpol.model.Code_;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsFacilityPlacement_;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.model.TroopsPlacement_;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TroopsDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.troops.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 12:59:32
 * </PRE>
 */
@Component("troopsDao")
public class TroopsDaoImpl implements TroopsDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#getTroopsWorkplace(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public TroopsPlacement getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TroopsPlacement> cQuery = builder.createQuery(TroopsPlacement.class);
		Root<TroopsPlacement> from = cQuery.from(TroopsPlacement.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//지방청, 구분, 소속, 세부소속
		if((troopsWorkplaceSearch.getCode1().getCodeNo()!=0)){
			restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.codeNo), troopsWorkplaceSearch.getCode1().getCodeNo()));
		}else{
			if((troopsWorkplaceSearch.getCode1().getCodeCategory()!=null) && (!troopsWorkplaceSearch.getCode1().getCodeCategory().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.codeCategory), troopsWorkplaceSearch.getCode1().getCodeCategory()));
			}
			if((troopsWorkplaceSearch.getCode1().getCode1depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code1depth), troopsWorkplaceSearch.getCode1().getCode1depth()));
			}
			if((troopsWorkplaceSearch.getCode1().getCode2depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code2depth), troopsWorkplaceSearch.getCode1().getCode2depth()));
			}
			if((troopsWorkplaceSearch.getCode1().getCode3depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code3depth), troopsWorkplaceSearch.getCode1().getCode3depth()));
			}
			if((troopsWorkplaceSearch.getCode1().getCode4depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode4depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code4depth), troopsWorkplaceSearch.getCode1().getCode4depth()));
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<TroopsPlacement> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList().size()==0? new TroopsPlacement() : typedQuery.getResultList().get(0);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#getTroopsFacilityListByTroops(com.dwebs.pchpol.model.TroopsFacilityPlacement)
	 */
	@Override
	public List<TroopsFacilityPlacement> getTroopsFacilityListByTroops(TroopsFacilityPlacement troopsFacilitySearch) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TroopsFacilityPlacement> cQuery = builder.createQuery(TroopsFacilityPlacement.class);
		Root<TroopsFacilityPlacement> from = cQuery.from(TroopsFacilityPlacement.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//지방청, 구분, 소속, 세부소속
		if((troopsFacilitySearch.getCode().getCodeNo()!=0)){
			restrictions.add(builder.equal(from.get(TroopsFacilityPlacement_.code).get(Code_.codeNo), troopsFacilitySearch.getCode().getCodeNo()));
		}else{
			if((troopsFacilitySearch.getCode().getCodeCategory()!=null) && (!troopsFacilitySearch.getCode().getCodeCategory().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsFacilityPlacement_.code).get(Code_.codeCategory), troopsFacilitySearch.getCode().getCodeCategory()));
			}
			if((troopsFacilitySearch.getCode().getCode1depth()!=null) && (!troopsFacilitySearch.getCode().getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsFacilityPlacement_.code).get(Code_.code1depth), troopsFacilitySearch.getCode().getCode1depth()));
			}
			if((troopsFacilitySearch.getCode().getCode2depth()!=null) && (!troopsFacilitySearch.getCode().getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsFacilityPlacement_.code).get(Code_.code2depth), troopsFacilitySearch.getCode().getCode2depth()));
			}
			if((troopsFacilitySearch.getCode().getCode3depth()!=null) && (!troopsFacilitySearch.getCode().getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsFacilityPlacement_.code).get(Code_.code3depth), troopsFacilitySearch.getCode().getCode3depth()));
			}
			if((troopsFacilitySearch.getCode().getCode4depth()!=null) && (!troopsFacilitySearch.getCode().getCode4depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsFacilityPlacement_.code).get(Code_.code4depth), troopsFacilitySearch.getCode().getCode4depth()));
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<TroopsFacilityPlacement> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList();
	}

}
