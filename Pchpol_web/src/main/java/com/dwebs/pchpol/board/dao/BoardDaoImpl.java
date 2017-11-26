/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : BoardDaoImpl.java
 * 2. Package : com.dwebs.pchpol.board.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 5:47:50
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.board.dao;

import java.util.ArrayList;
import java.util.Date;
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
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Board;
import com.dwebs.pchpol.model.Board_;
import com.dwebs.pchpol.model.Unit;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : BoardDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.board.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 5:47:50
 * </PRE>
 */
@Component("boardDao")
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.dao.BoardDao#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Board)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO, Board board) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Board> from = cQuery.from(Board.class);
		cQuery.select(builder.count(from));

		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(board.getBoardType()!=null&&!board.getBoardType().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardType), board.getBoardType()));
		}
		if(board.getBoardCategory()!=null&&!board.getBoardCategory().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardCategory), board.getBoardCategory()));
		}
		if(board.getBoardArea()!=null&&!board.getBoardArea().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardArea), board.getBoardArea()));
		}
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(pagingVO.getSearchType()!=null && !pagingVO.getSearchType().equals("")){
			if(pagingVO.getSearchType().equals("recent")){
				if(board.getBoardWriteDt()!=null){
					restrictions.add(builder.between(from.get(Board_.boardWriteDt), board.getBoardWriteDt(), new Date()));
				}
			}else{
				restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
			}
			
			
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//쿼리를 실행한다.
		Long result = em.createQuery(cQuery).getSingleResult();
		
		return result.intValue();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.dao.BoardDao#getList(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Board)
	 */
	@Override
	public List<Board> getList(PagingVO pagingVO, Board board) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Board> cQuery = builder.createQuery(Board.class);
		Root<Board> from = cQuery.from(Board.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(board.getBoardType()!=null&&!board.getBoardType().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardType), board.getBoardType()));
		}
		if(board.getBoardCategory()!=null&&!board.getBoardCategory().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardCategory), board.getBoardCategory()));
		}
		if(board.getBoardArea()!=null&&!board.getBoardArea().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardArea), board.getBoardArea()));
		}
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
		}else{
			cQuery.orderBy(builder.desc(from.get(Board_.boardWriteDt)));
		}
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Board> typedQuery = em.createQuery(cQuery);
		List<Board> result = new ArrayList<Board>();
		if(pagingVO.getStartNum()!=0){
			typedQuery.setFirstResult(pagingVO.getStartNum());
			typedQuery.setMaxResults(pagingVO.getRows());
		}
		else if(pagingVO.getPage()>0){
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
	 * @see com.dwebs.pchpol.board.dao.BoardDao#getById(java.lang.String)
	 */
	@Override
	public Board getById(String boardNo) {
		EntityManager em = emf.createEntityManager();
		Board result = em.find(Board.class, Integer.parseInt(boardNo));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.admin.dao.AdminDao#reg(com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public void insertOrUpdate(Board board) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(board.getBoardNo()>0){
			em.merge(board);
		}else{
			em.persist(board);
		}
		em.getTransaction().commit();
		em.close();
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.dao.BoardDao#getTop1()
	 */
	@Override
	public Board getTop1(Board board) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Board> cQuery = builder.createQuery(Board.class);
		Root<Board> from = cQuery.from(Board.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(board.getBoardType()!=null&&!board.getBoardType().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardType), board.getBoardType()));
		}
		if(board.getBoardCategory()!=null&&!board.getBoardCategory().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardCategory), board.getBoardCategory()));
		}
		if(board.getBoardArea()!=null&&!board.getBoardArea().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardArea), board.getBoardArea()));
		}
		
		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//정렬순서조건이 있는경우 정렬순서 조건 쿼리를 생성한다.
		cQuery.orderBy(builder.desc(from.get(Board_.boardWriteDt)));
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Board> typedQuery = em.createQuery(cQuery);
		typedQuery.setMaxResults(1);
		
		Board result = typedQuery.getSingleResult();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.dao.BoardDao#getList(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Board, com.dwebs.pchpol.model.Unit, com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public List<Board> getList(PagingVO pagingVO, Board board, Unit unit, Admin admin) {
		EntityManager em = emf.createEntityManager();
		//기본 select ~ from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Board> cQuery = builder.createQuery(Board.class);
		Root<Board> from = cQuery.from(Board.class);

		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(board.getBoardType()!=null&&!board.getBoardType().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardType), board.getBoardType()));
		}
		if(board.getBoardCategory()!=null&&!board.getBoardCategory().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardCategory), board.getBoardCategory()));
		}
		if(board.getBoardArea()!=null&&!board.getBoardArea().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardArea), board.getBoardArea()));
		}
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(!pagingVO.getSearchType().equals("")){
			restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
		}
		if(admin!=null){
			
		}else{
			if(unit!=null){
				Predicate privacy = builder.or(builder.equal(from.get(Board_.isPrivacy), "N"), builder.equal(from.get(Board_.unit), unit));
				restrictions.add(privacy);
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
		}else{
			cQuery.orderBy(builder.desc(from.get(Board_.boardWriteDt)));
		}
		
		//생성한 쿼리를 실행한다.
		TypedQuery<Board> typedQuery = em.createQuery(cQuery);
		List<Board> result = new ArrayList<Board>();
		if(pagingVO.getStartNum()!=0){
			typedQuery.setFirstResult(pagingVO.getStartNum());
			typedQuery.setMaxResults(pagingVO.getRows());
		}
		else if(pagingVO.getPage()>0){
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
	 * @see com.dwebs.pchpol.board.dao.BoardDao#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Board, com.dwebs.pchpol.model.Unit, com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO, Board board, Unit unit, Admin admin) {
		EntityManager em = emf.createEntityManager();
		//select count(*) from ~ 쿼리를 생성한다.
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
		Root<Board> from = cQuery.from(Board.class);
		cQuery.select(builder.count(from));

		List<Predicate> restrictions = new ArrayList<Predicate>();
		if(board.getBoardType()!=null&&!board.getBoardType().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardType), board.getBoardType()));
		}
		if(board.getBoardCategory()!=null&&!board.getBoardCategory().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardCategory), board.getBoardCategory()));
		}
		if(board.getBoardArea()!=null&&!board.getBoardArea().equals("")){
			restrictions.add(builder.equal(from.get(Board_.boardArea), board.getBoardArea()));
		}
		//검색조건이 있는 경우 검색조건 쿼리를 생성한다.
		if(pagingVO.getSearchType()!=null && !pagingVO.getSearchType().equals("")){
			if(pagingVO.getSearchType().equals("recent")){
				if(board.getBoardWriteDt()!=null){
					restrictions.add(builder.between(from.get(Board_.boardWriteDt), board.getBoardWriteDt(), new Date()));
				}
			}else{
				restrictions.add(builder.like(from.<String>get(pagingVO.getSearchType()), "%"+pagingVO.getSearchWord()+"%"));
			}
			
			
		}

		if(admin!=null){
			
		}else{
			if(unit!=null){
				Predicate privacy = builder.or(builder.equal(from.get(Board_.isPrivacy), "N"), builder.equal(from.get(Board_.unit), unit));
				restrictions.add(privacy);
			}
		}

		cQuery.where(restrictions.toArray(new Predicate[]{}));
		
		//쿼리를 실행한다.
		Long result = em.createQuery(cQuery).getSingleResult();
		
		return result.intValue();
	}

}
