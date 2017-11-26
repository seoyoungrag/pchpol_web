/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CommentServiceImpl.java
 * 2. Package : com.dwebs.pchpol.comment.service.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 6. 오후 11:46:16
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 6. :            : 신규 개발.
 */
package com.dwebs.pchpol.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.comment.dao.CommentDao;
import com.dwebs.pchpol.comment.service.CommentService;
import com.dwebs.pchpol.model.Comment;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CommentServiceImpl.java
 * 3. Package  : com.dwebs.pchpol.comment.service.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 6. 오후 11:46:16
 * </PRE>
 */
@Component("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	@Qualifier("commentDao")
	public CommentDao commentDao;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.comment.service.CommentService#insertOrUpdate(com.dwebs.pchpol.model.Comment)
	 */
	@Override
	public void insertOrUpdate(Comment comment) {
		commentDao.insertOrUpdate(comment);
	}

}
