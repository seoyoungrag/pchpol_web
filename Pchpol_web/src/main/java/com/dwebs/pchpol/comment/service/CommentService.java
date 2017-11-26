/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CommentService.java
 * 2. Package : com.dwebs.pchpol.comment.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 6. 오후 11:45:11
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 6. :            : 신규 개발.
 */
package com.dwebs.pchpol.comment.service;

import com.dwebs.pchpol.model.Comment;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CommentService.java
 * 3. Package  : com.dwebs.pchpol.comment.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 6. 오후 11:45:11
 * </PRE>
 */
public interface CommentService {

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdate
	 * 2. ClassName  : CommentService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 6. 오후 11:45:39
	 * </PRE>
	 *   @return void
	 *   @param comment
	 */
	void insertOrUpdate(Comment comment);

}
