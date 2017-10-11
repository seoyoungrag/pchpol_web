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
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

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
@Transactional
public class CodeDaoImpl implements CodeDao {
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.code.dao.CodeDao#getCodeListByCategory(java.lang.String)
	 */
	@Override
	public List<Code> getCodeListByCategory(String category) {
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Code> cQuery = builder.createQuery(Code.class);
		Root<Code> from = cQuery.from(Code.class);

		//where 조건을 추가한다.
		Predicate restriction = builder.like(from.get(Code_.codeCategory), category);
		cQuery.where(restriction);
		
		//order by 조건을 추가한다.
		cQuery.orderBy(builder.asc(from.get(Code_.codeOrderNo)));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Code> typedQuery = em.createQuery(cQuery);
		List<Code> result = new ArrayList<Code>();
		
		result = typedQuery.getResultList();
		return result;
	}

}
