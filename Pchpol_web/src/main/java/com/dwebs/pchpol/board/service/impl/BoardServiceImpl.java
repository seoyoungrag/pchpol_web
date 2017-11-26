/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : BoardServiceImpl.java
 * 2. Package : com.dwebs.pchpol.board.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 5:45:18
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.board.dao.BoardDao;
import com.dwebs.pchpol.board.service.BoardService;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Board;
import com.dwebs.pchpol.model.Unit;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : BoardServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.board.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 5:45:18
 * </PRE>
 */
@Component("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	@Qualifier("boardDao")
	public BoardDao boardDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#getList(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public List<Board> getList(PagingVO pagingVO, Board board) {
		return boardDao.getList(pagingVO, board);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#getTotCnt(com.dwebs.pchpol.common.vo.PagingVO)
	 */
	@Override
	public int getTotCnt(PagingVO pagingVO, Board board) {
		return boardDao.getTotCnt(pagingVO, board);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#getById(java.lang.String)
	 */
	@Override
	public Board getById(String boardNo) {
		return boardDao.getById(boardNo);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#insertOrUpdate(com.dwebs.pchpol.model.Board)
	 */
	@Override
	public void insertOrUpdate(Board board) {
		boardDao.insertOrUpdate(board);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#getTop1()
	 */
	@Override
	public Board getTop1(Board board) {
		return boardDao.getTop1(board);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#getListMobile(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Board, com.dwebs.pchpol.model.Unit, com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public List<Board> getListMobile(PagingVO pagingVO, Board board, Unit unit, Admin admin) {
		return boardDao.getList(pagingVO, board, unit, admin);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.board.service.BoardService#getTotCntMobile(com.dwebs.pchpol.common.vo.PagingVO, com.dwebs.pchpol.model.Board, com.dwebs.pchpol.model.Unit, com.dwebs.pchpol.model.Admin)
	 */
	@Override
	public int getTotCntMobile(PagingVO pagingVO, Board board, Unit unit, Admin admin) {
		return boardDao.getTotCnt(pagingVO, board, unit, admin);
	}

}
