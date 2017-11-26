/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : BoardDao.java
 * 2. Package : com.dwebs.pchpol.board.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 5:46:00
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.board.dao;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Board;
import com.dwebs.pchpol.model.Unit;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : BoardDao.java
 * 3. Package  : com.dwebs.pchpol.board.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 5:46:00
 * </PRE>
 */
public interface BoardDao {

	/**
	 * <PRE>
	 * 1. MethodName : getTotCnt
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 5:47:33
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param board
	 *   @return
	 */
	int getTotCnt(PagingVO pagingVO, Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getList
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 5:47:37
	 * </PRE>
	 *   @return List<Board>
	 *   @param pagingVO
	 *   @param board
	 *   @return
	 */
	List<Board> getList(PagingVO pagingVO, Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 7:10:04
	 * </PRE>
	 *   @return Board
	 *   @param boardNo
	 *   @return
	 */
	Board getById(String boardNo);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 7:11:06
	 * </PRE>
	 *   @return void
	 *   @param board
	 */
	void insertOrUpdate(Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getTop1
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 7. 오후 10:19:27
	 * </PRE>
	 *   @return Board
	 *   @return
	 */
	Board getTop1(Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getList
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 23. 오후 1:32:08
	 * </PRE>
	 *   @return List<Board>
	 *   @param pagingVO
	 *   @param board
	 *   @param unit
	 *   @param admin
	 *   @return
	 */
	List<Board> getList(PagingVO pagingVO, Board board, Unit unit, Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCnt
	 * 2. ClassName  : BoardDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 23. 오후 1:32:11
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param board
	 *   @param unit
	 *   @param admin
	 *   @return
	 */
	int getTotCnt(PagingVO pagingVO, Board board, Unit unit, Admin admin);


}
