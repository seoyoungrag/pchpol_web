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
package com.dwebs.pchpol.troops.dao.impl;

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

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Code_;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsFacilityPlacement_;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.model.TroopsPlacement_;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.model.WorkplacePlacementDetail_;
import com.dwebs.pchpol.model.WorkplacePlacement_;
import com.dwebs.pchpol.troops.dao.TroopsDao;
import com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops;

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
	public List<TroopsPlacement> getTroopsWorkplace(TroopsPlacement troopsWorkplaceSearch) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TroopsPlacement> cQuery = builder.createQuery(TroopsPlacement.class);
		Root<TroopsPlacement> from = cQuery.from(TroopsPlacement.class);
		
		List<Predicate> restrictions = new ArrayList<Predicate>();
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//부대: 지방청, 구분, 소속, 세부소속
		if(troopsWorkplaceSearch.getCode1()!=null){
			if((troopsWorkplaceSearch.getCode1()!=null&&troopsWorkplaceSearch.getCode1().getCodeNo()!=0)){
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
		}
		//근무지: 지역, 시설, 근무지, 세부구분
		if(troopsWorkplaceSearch.getCode2()!=null){
			if(troopsWorkplaceSearch.getCode2().getCodeNo()!=0){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.codeNo), troopsWorkplaceSearch.getCode2().getCodeNo()));
			}else{
				if((troopsWorkplaceSearch.getCode2().getCodeCategory()!=null) && (!troopsWorkplaceSearch.getCode2().getCodeCategory().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.codeCategory), troopsWorkplaceSearch.getCode2().getCodeCategory()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode1depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode1depth().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code1depth), troopsWorkplaceSearch.getCode2().getCode1depth()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode2depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode2depth().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code2depth), troopsWorkplaceSearch.getCode2().getCode2depth()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode3depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode3depth().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code3depth), troopsWorkplaceSearch.getCode2().getCode3depth()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode4depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode4depth().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code4depth), troopsWorkplaceSearch.getCode2().getCode4depth()));
				}
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<TroopsPlacement> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList();
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

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#insertOrUpdate(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public void insertOrUpdate(TroopsPlacement troopsPlacement) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(troopsPlacement.getTroopsPlacementNo()>0){
			em.merge(troopsPlacement);
		}else{
			em.persist(troopsPlacement);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#deleteDuplicate(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public void deleteDuplicate(TroopsPlacement troopsPlacement) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<TroopsPlacement> cQuery = builder.createQuery(TroopsPlacement.class);
		Root<TroopsPlacement> from = cQuery.from(TroopsPlacement.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.codeNo), troopsPlacement.getCode1().getCodeNo()));
		restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.codeNo), troopsPlacement.getCode2().getCodeNo()));

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<TroopsPlacement> typedQuery = em.createQuery(cQuery);
		List<TroopsPlacement> deletingList = typedQuery.getResultList();
		
		em.getTransaction().begin();
		for(TroopsPlacement tp : deletingList){
			em.remove(tp);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#getByid(java.lang.String)
	 */
	@Override
	public TroopsPlacement getByid(String id) {
		EntityManager em = emf.createEntityManager();
		TroopsPlacement result = em.find(TroopsPlacement.class, Integer.parseInt(id));
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<WorkplaceWithTroops> getTroopsWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO, WorkplaceWithTroops workplaceWithTroops) {
		Code workplaceSearch = workplaceWithTroops.getWorkplace(); //근무지검색에 사용
		Code troopsSearch = workplaceWithTroops.getTroops(); //부대검색에 사용

		EntityManager em = emf.createEntityManager();
		
		//map으로 받으려면 쿼리를 생성해야한다.
		/*HashMap<String, String> map=new HashMap<String, String>();
		Query q = em.createQuery("select a.troopsPlacementNo, troops.code1depth,troops.code2depth,troops.code3depth "
				+ "from TroopsPlacement a, Code troops, Code workplace "
				+ "where a.code1=troops and a.code2=workplace "
				+ "group by a.troopsPlacementNo, troops.code1depth, troops.code2depth, troops.code3depth")
				; 

		    List<Object[]> list = q.getResultList();

		    for (Object[] result : list) {
		        map.put(result[0].toString(), result[1].toString());
		    }
		 */
		
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<WorkplaceWithTroops> cQuery = builder.createQuery(WorkplaceWithTroops.class);
		Root<TroopsPlacement> from = cQuery.from(TroopsPlacement.class);


		cQuery.select(builder.construct(WorkplaceWithTroops.class,
				from.get(TroopsPlacement_.code2).get(Code_.code1depth), 
				from.get(TroopsPlacement_.code2).get(Code_.code2depth),
				from.get(TroopsPlacement_.code1).get(Code_.code1depth),
				from.get(TroopsPlacement_.code1).get(Code_.code2depth),
				from.get(TroopsPlacement_.code1).get(Code_.code3depth))
		    );
		
		
		List<Predicate> restrictions = new ArrayList<Predicate>();
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//부대: 지방청, 구분, 소속, 세부소속
		//지방청, 구분, 소속까지만 검색
		if(troopsSearch!=null){
			if((troopsSearch.getCode1depth()!=null) && (!troopsSearch.getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code1depth), troopsSearch.getCode1depth()));
			}
			if((troopsSearch.getCode2depth()!=null) && (!troopsSearch.getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code2depth), troopsSearch.getCode2depth()));
			}
			if((troopsSearch.getCode3depth()!=null) && (!troopsSearch.getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code3depth), troopsSearch.getCode3depth()));
			}
		}
		//근무지: 지역, 대회시설, 근무지, 임무 
		//지역id, 지역, 대회시설까지만 검색
		if(workplaceSearch!=null){
			if((workplaceSearch.getCode1depth()!=null) && (!workplaceSearch.getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code1depth), workplaceSearch.getCode1depth()));
			}
			if((workplaceSearch.getCode2depth()!=null) && (!workplaceSearch.getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code2depth), workplaceSearch.getCode2depth()));
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));

		List<Expression> groupBys = new ArrayList<Expression>();
		groupBys.add(from.get(TroopsPlacement_.code2).get(Code_.code1depth));
		groupBys.add(from.get(TroopsPlacement_.code2).get(Code_.code2depth));
		groupBys.add(from.get(TroopsPlacement_.code1).get(Code_.code1depth));
		groupBys.add(from.get(TroopsPlacement_.code1).get(Code_.code2depth));
		groupBys.add(from.get(TroopsPlacement_.code1).get(Code_.code3depth));
		
		cQuery.groupBy(groupBys.toArray(new Expression[]{}));
		
		//정렬순서조건이 있는경우 정렬순서 조건 쿼리를 생성한다.
		if(!pagingVO.getSidx().equals("")&&!pagingVO.getSord().equals("")){
			Expression<?> e;
			if(pagingVO.getSidx().startsWith("workplace.")){
				String column = pagingVO.getSidx().substring("workplace.".length());
				e = from.get(TroopsPlacement_.code2).get(column);
			}else if(pagingVO.getSidx().startsWith("troops.")){
				String column = pagingVO.getSidx().substring("troops.".length());
				e = from.get(TroopsPlacement_.code1).get(column);
			}else{
				 e = from.get(pagingVO.getSidx());
			}
			if(pagingVO.getSord().equals("asc")){
				cQuery.orderBy(builder.asc(e));
			}else{
				cQuery.orderBy(builder.desc(e));
			}
		}

		//생성한 쿼리를 실행한다.
		TypedQuery<WorkplaceWithTroops> typedQuery = em.createQuery(cQuery);
		List<WorkplaceWithTroops> result = new ArrayList<WorkplaceWithTroops>();
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
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#getTotCntWorkplaceGroupByTroopsCode3depth(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int getTotCntWorkplaceGroupByTroopsCode3depth(PagingVO pagingVO, WorkplaceWithTroops workplaceWithTroops) {
		Code workplaceSearch = workplaceWithTroops.getWorkplace(); //근무지검색에 사용
		Code troopsSearch = workplaceWithTroops.getTroops(); //부대검색에 사용
		
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<TroopsPlacement> from = cQuery.from(TroopsPlacement.class);
		cQuery.select(builder.count(from));

		List<Predicate> restrictions = new ArrayList<Predicate>();

		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//부대: 지방청, 구분, 소속, 세부소속
		//지방청, 구분, 소속까지만 검색
		if(troopsSearch!=null){
			if((troopsSearch.getCode1depth()!=null) && (!troopsSearch.getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code1depth), troopsSearch.getCode1depth()));
			}
			if((troopsSearch.getCode2depth()!=null) && (!troopsSearch.getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code2depth), troopsSearch.getCode2depth()));
			}
			if((troopsSearch.getCode3depth()!=null) && (!troopsSearch.getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code1).get(Code_.code3depth), troopsSearch.getCode3depth()));
			}
		}
		//근무지: 지역, 대회시설, 근무지, 임무 
		//지역id, 지역, 대회시설까지만 검색
		if(workplaceSearch!=null){
			if(workplaceSearch.getCodeNo()!=0){
				restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.codeNo), workplaceSearch.getCodeNo()));
			}else{
				if((workplaceSearch.getCode1depth()!=null) && (!workplaceSearch.getCode1depth().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code1depth), workplaceSearch.getCode1depth()));
				}
				if((workplaceSearch.getCode2depth()!=null) && (!workplaceSearch.getCode2depth().equals(""))){
					restrictions.add(builder.equal(from.get(TroopsPlacement_.code2).get(Code_.code2depth), workplaceSearch.getCode2depth()));
				}
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));

		List<Expression> groupBys = new ArrayList<Expression>();
		groupBys.add(from.get(TroopsPlacement_.code2).get(Code_.code1depth));
		groupBys.add(from.get(TroopsPlacement_.code2).get(Code_.code2depth));
		groupBys.add(from.get(TroopsPlacement_.code1).get(Code_.code1depth));
		groupBys.add(from.get(TroopsPlacement_.code1).get(Code_.code2depth));
		groupBys.add(from.get(TroopsPlacement_.code1).get(Code_.code3depth));
		
		cQuery.groupBy(groupBys.toArray(new Expression[]{}));
		
		//생성한 쿼리를 실행한다. group by때문에 count가 group by되어서 group 별로 계산한 count결과를 리턴한다. 이 리스트의 size를 리턴하면 된다. 
		int result = em.createQuery(cQuery).getResultList().size();
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#getWorkplaceTroops(com.dwebs.pchpol.model.TroopsPlacement)
	 */
	@Override
	public List<WorkplacePlacement> getWorkplaceTroops(TroopsPlacement troopsWorkplaceSearch) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<WorkplacePlacement> cQuery = builder.createQuery(WorkplacePlacement.class);
		Root<WorkplacePlacement> from = cQuery.from(WorkplacePlacement.class);
		
		List<Predicate> restrictions = new ArrayList<Predicate>();
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//부대: 지방청, 구분, 소속, 세부소속
		if(troopsWorkplaceSearch.getCode1()!=null){
			if((troopsWorkplaceSearch.getCode1()!=null&&troopsWorkplaceSearch.getCode1().getCodeNo()!=0)){
				restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.codeNo), troopsWorkplaceSearch.getCode1().getCodeNo()));
			}else{
				if((troopsWorkplaceSearch.getCode1().getCodeCategory()!=null) && (!troopsWorkplaceSearch.getCode1().getCodeCategory().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.codeCategory), troopsWorkplaceSearch.getCode1().getCodeCategory()));
				}
				if((troopsWorkplaceSearch.getCode1().getCode1depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode1depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code1depth), troopsWorkplaceSearch.getCode1().getCode1depth()));
				}
				if((troopsWorkplaceSearch.getCode1().getCode2depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode2depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code2depth), troopsWorkplaceSearch.getCode1().getCode2depth()));
				}
				if((troopsWorkplaceSearch.getCode1().getCode3depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode3depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code3depth), troopsWorkplaceSearch.getCode1().getCode3depth()));
				}
				if((troopsWorkplaceSearch.getCode1().getCode4depth()!=null) && (!troopsWorkplaceSearch.getCode1().getCode4depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code4depth), troopsWorkplaceSearch.getCode1().getCode4depth()));
				}
			}
		}
		//근무지: 지역, 시설, 근무지, 세부구분
		if(troopsWorkplaceSearch.getCode2()!=null){
			if(troopsWorkplaceSearch.getCode2().getCodeNo()!=0){
				restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.codeNo), troopsWorkplaceSearch.getCode2().getCodeNo()));
			}else{
				if((troopsWorkplaceSearch.getCode2().getCodeCategory()!=null) && (!troopsWorkplaceSearch.getCode2().getCodeCategory().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.codeCategory), troopsWorkplaceSearch.getCode2().getCodeCategory()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode1depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode1depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code1depth), troopsWorkplaceSearch.getCode2().getCode1depth()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode2depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode2depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code2depth), troopsWorkplaceSearch.getCode2().getCode2depth()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode3depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode3depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code3depth), troopsWorkplaceSearch.getCode2().getCode3depth()));
				}
				if((troopsWorkplaceSearch.getCode2().getCode4depth()!=null) && (!troopsWorkplaceSearch.getCode2().getCode4depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code4depth), troopsWorkplaceSearch.getCode2().getCode4depth()));
				}
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<WorkplacePlacement> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.troops.dao.TroopsDao#getWorkplaceTroops(com.dwebs.pchpol.model.WorkplacePlacement)
	 */
	@Override
	public WorkplacePlacement getWorkplaceTroops(WorkplacePlacement wps) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<WorkplacePlacement> cQuery = builder.createQuery(WorkplacePlacement.class);
		Root<WorkplacePlacement> from = cQuery.from(WorkplacePlacement.class);
		
		Join<WorkplacePlacement, WorkplacePlacementDetail> wpdJoin = from.join(WorkplacePlacement_.workplacePlacementDetail);
		wpdJoin.join(WorkplacePlacementDetail_.unit);
        
		List<Predicate> restrictions = new ArrayList<Predicate>();
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//부대: 지방청, 구분, 소속, 세부소속
		if(wps.getCode1()!=null){
			if((wps.getCode1()!=null&&wps.getCode1().getCodeNo()!=0)){
				restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.codeNo), wps.getCode1().getCodeNo()));
			}else{
				if((wps.getCode1().getCodeCategory()!=null) && (!wps.getCode1().getCodeCategory().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.codeCategory), wps.getCode1().getCodeCategory()));
				}
				if((wps.getCode1().getCode1depth()!=null) && (!wps.getCode1().getCode1depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code1depth), wps.getCode1().getCode1depth()));
				}
				if((wps.getCode1().getCode2depth()!=null) && (!wps.getCode1().getCode2depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code2depth), wps.getCode1().getCode2depth()));
				}
				if((wps.getCode1().getCode3depth()!=null) && (!wps.getCode1().getCode3depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code3depth), wps.getCode1().getCode3depth()));
				}
				if((wps.getCode1().getCode4depth()!=null) && (!wps.getCode1().getCode4depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code1).get(Code_.code4depth), wps.getCode1().getCode4depth()));
				}
			}
		}
		//근무지: 지역, 시설, 근무지, 세부구분
		if(wps.getCode2()!=null){
			if(wps.getCode2().getCodeNo()!=0){
				restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.codeNo), wps.getCode2().getCodeNo()));
			}else{
				if((wps.getCode2().getCodeCategory()!=null) && (!wps.getCode2().getCodeCategory().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.codeCategory), wps.getCode2().getCodeCategory()));
				}
				if((wps.getCode2().getCode1depth()!=null) && (!wps.getCode2().getCode1depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code1depth), wps.getCode2().getCode1depth()));
				}
				if((wps.getCode2().getCode2depth()!=null) && (!wps.getCode2().getCode2depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code2depth), wps.getCode2().getCode2depth()));
				}
				if((wps.getCode2().getCode3depth()!=null) && (!wps.getCode2().getCode3depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code3depth), wps.getCode2().getCode3depth()));
				}
				if((wps.getCode2().getCode4depth()!=null) && (!wps.getCode2().getCode4depth().equals(""))){
					restrictions.add(builder.equal(from.get(WorkplacePlacement_.code2).get(Code_.code4depth), wps.getCode2().getCode4depth()));
				}
			}
		}
		//근무일자 검색
		if(wps.getWorkplacePlacementWorkDt()!=null){
			restrictions.add(builder.equal(from.get(WorkplacePlacement_.workplacePlacementWorkDt),  wps.getWorkplacePlacementWorkDt()));
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<WorkplacePlacement> typedQuery = em.createQuery(cQuery);
		//검색 결과가 없다고 exception을 발생시킴..
		//typedQuery.getSingleResult()
		List<WorkplacePlacement> list = typedQuery.getResultList();
		WorkplacePlacement result = null;
		if(list.size()>0){
			result = list.get(0);
		}
		return result;
	}
}
