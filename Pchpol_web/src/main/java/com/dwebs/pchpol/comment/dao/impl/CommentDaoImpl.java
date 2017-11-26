/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CommentDaoImpl.java
 * 2. Package : com.dwebs.pchpol.comment.dao.impl
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 6. 오후 11:47:59
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 6. :            : 신규 개발.
 */
package com.dwebs.pchpol.comment.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.comment.dao.CommentDao;
import com.dwebs.pchpol.model.Comment;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CommentDaoImpl.java
 * 3. Package  : com.dwebs.pchpol.comment.dao.impl
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 6. 오후 11:47:59
 * </PRE>
 */
@Component("commentDao")
public class CommentDaoImpl implements CommentDao {
	@Autowired
	private EntityManagerFactory emf;

	/* (non-Javadoc)
	 * @see com.dwebs.pchpol.comment.dao.CommentDao#insertOrUpdate(com.dwebs.pchpol.model.Comment)
	 */
	@Override
	public void insertOrUpdate(Comment comment) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//id가 0보다 크면 update
		if(comment.getCommentNo()>0){
			em.merge(comment);
		}else{
			em.persist(comment);
		}
		em.getTransaction().commit();
		em.close();
	}

}
