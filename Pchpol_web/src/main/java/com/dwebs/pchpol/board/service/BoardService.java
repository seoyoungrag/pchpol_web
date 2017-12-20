/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : boardService.java
 * 2. Package : com.dwebs.pchpol.board.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 5:44:21
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.board.service;

import java.util.List;

import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Board;
import com.dwebs.pchpol.model.Unit;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : boardService.java
 * 3. Package  : com.dwebs.pchpol.board.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 5:44:21
 * </PRE>
 */
public interface BoardService {

	/**
	 * <PRE>
	 * 1. MethodName : getList
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 5:44:50
	 * </PRE>
	 *   @return List<Board>
	 *   @param pagingVO
	 * @param board 
	 *   @return
	 */
	List<Board> getList(PagingVO pagingVO, Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCnt
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 5:44:54
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 * @param board 
	 *   @return
	 */
	int getTotCnt(PagingVO pagingVO, Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getById
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 7:09:47
	 * </PRE>
	 *   @return Board
	 *   @param boardNo
	 *   @return
	 */
	Board getById(String boardNo);

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 7:11:57
	 * </PRE>
	 *   @return void
	 *   @param board
	 */
	void insertOrUpdate(Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getTop1
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 7. 오후 10:18:58
	 * </PRE>
	 *   @return Board
	 *   @return
	 */
	Board getTop1(Board board);

	/**
	 * <PRE>
	 * 1. MethodName : getListMobile
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 23. 오후 1:29:43
	 * </PRE>
	 *   @return List<Board>
	 *   @param pagingVO
	 *   @param board
	 * @param admin 
	 * @param unit 
	 *   @return
	 */
	List<Board> getListMobile(PagingVO pagingVO, Board board, Unit unit, Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : getTotCntMobile
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 23. 오후 1:30:54
	 * </PRE>
	 *   @return int
	 *   @param pagingVO
	 *   @param board
	 *   @param unit
	 *   @param admin
	 *   @return
	 */
	int getTotCntMobile(PagingVO pagingVO, Board board, Unit unit, Admin admin);

	/**
	 * <PRE>
	 * 1. MethodName : deleteByIds
	 * 2. ClassName  : BoardService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 27. 오후 5:00:24
	 * </PRE>
	 *   @return void
	 *   @param ids
	 */
	void deleteByIds(List<Integer> ids);

}
