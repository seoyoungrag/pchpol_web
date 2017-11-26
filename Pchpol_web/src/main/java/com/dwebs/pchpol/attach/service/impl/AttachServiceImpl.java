/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AttachServiceImpl.java
 * 2. Package : com.dwebs.pchpol.attach.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 10:34:13
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.attach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.attach.dao.AttachDao;
import com.dwebs.pchpol.attach.service.AttachService;
import com.dwebs.pchpol.model.Attach;
import com.dwebs.pchpol.model.Board;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AttachServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.attach.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 10:34:13
 * </PRE>
 */
@Component("attachService")
public class AttachServiceImpl implements AttachService {
	@Autowired
	@Qualifier("attachDao")
	public AttachDao attachDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.service.AttachService#insertAttaches(java.util.List)
	 */
	@Override
	public void insertAttaches(List<Attach> attaches) {
		attachDao.insertAttaches(attaches);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.service.AttachService#insertBoardAttaches(java.util.List)
	 */
	@Override
	public void insertBoardAttaches(List<Attach> attaches) {
		if(attaches.size()>0){
			this.beforeDeleteByBoardNo(attaches.get(0).getBoard().getBoardNo());
			this.insertAttaches(attaches);
		}
	}

	/**
	 * <PRE>
	 * 1. MethodName : beforeDeleteByBoardNo
	 * 2. ClassName  : AttachServiceImpl
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:39:13
	 * </PRE>
	 *   @return void
	 *   @param boardNo
	 */
	private void beforeDeleteByBoardNo(int boardNo) {
		Attach at = new Attach();
		Board bo = new Board();
		bo.setBoardNo(boardNo);
		at.setBoard(bo);
		attachDao.deleteByBoard(at);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.service.AttachService#deleteFiles(java.util.List)
	 */
	@Override
	public void deleteFiles(List<Integer> deletingFileArr) {
		attachDao.deleteFiles(deletingFileArr);
	}

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.attach.service.AttachService#insertBoardAttachesMobile(java.util.List)
	 */
	@Override
	public void insertBoardAttachesMobile(List<Attach> attaches) {
		if(attaches.size()>0){
			this.insertAttaches(attaches);
		}
	}

}
