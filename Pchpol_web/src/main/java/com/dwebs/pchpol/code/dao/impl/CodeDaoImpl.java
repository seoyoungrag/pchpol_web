/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CodeDaoImpl.java
 * 2. Package : com.dwebs.pchpol.code.dao.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 11. 오후 1:20:47
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 11. :            : 신규 개발.
 */
package com.dwebs.pchpol.code.dao.impl;

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

import com.dwebs.pchpol.code.dao.CodeDao;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Code_;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CodeDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.code.dao.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 11. 오후 1:20:47
 * </PRE>
 */
@Component("codeDao")
public class CodeDaoImpl implements CodeDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.dao.CodeDao#getCodeListByCategory(java.lang.String)
	 */
	@Override
	public List<Code> getCodeListByCategory(String category) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Code> cQuery = builder.createQuery(Code.class);
		Root<Code> from = cQuery.from(Code.class);

		//where 조건을 추가한다.
		Predicate restriction = builder.equal(builder.upper(from.get(Code_.codeCategory)), category.toUpperCase());
		cQuery.where(restriction);
		
		//order by 조건을 추가한다.
		cQuery.orderBy(builder.asc(from.get(Code_.codeOrderNo)));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Code> typedQuery = em.createQuery(cQuery);
		List<Code> result = new ArrayList<Code>();
		
		result = typedQuery.getResultList();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.dao.CodeDao#getCode(com.dwebs.pchpol.model.Code)
	 */
	@Override
	public Code getCode(Code code) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Code> cQuery = builder.createQuery(Code.class);
		Root<Code> from = cQuery.from(Code.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		//지방청, 구분, 소속, 세부소속
		if((code.getCodeNo()!=0)){
			restrictions.add(builder.equal(from.get(Code_.codeNo), code.getCodeNo()));
		}else{
			if((code.getCodeCategory()!=null) && (!code.getCodeCategory().equals(""))){
				restrictions.add(builder.equal(from.get(Code_.codeCategory), code.getCodeCategory()));
			}
			if((code.getCode1depth()!=null) && (!code.getCode1depth().equals(""))){
				restrictions.add(builder.equal(from.get(Code_.code1depth), code.getCode1depth()));
			}
			if((code.getCode2depth()!=null) && (!code.getCode2depth().equals(""))){
				restrictions.add(builder.equal(from.get(Code_.code2depth), code.getCode2depth()));
			}
			if((code.getCode3depth()!=null) && (!code.getCode3depth().equals(""))){
				restrictions.add(builder.equal(from.get(Code_.code3depth), code.getCode3depth()));
			}
			if((code.getCode4depth()!=null) && (!code.getCode4depth().equals(""))){
				restrictions.add(builder.equal(from.get(Code_.code4depth), code.getCode4depth()));
			}
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Code> typedQuery = em.createQuery(cQuery);
		return typedQuery.getResultList().get(0);
	}

}
