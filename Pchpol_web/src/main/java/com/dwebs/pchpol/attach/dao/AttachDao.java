/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AttachDao.java
 * 2. Package : com.dwebs.pchpol.attach.dao
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 10:35:14
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.attach.dao;

import java.util.List;

import com.dwebs.pchpol.model.Attach;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AttachDao.java
 * 3. Package  : com.dwebs.pchpol.attach.dao
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 10:35:14
 * </PRE>
 */
public interface AttachDao {

	/**
	 * <PRE>
	 * 1. MethodName : insertAttaches
	 * 2. ClassName  : AttachDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:35:22
	 * </PRE>
	 *   @return void
	 *   @param attaches
	 */
	void insertAttaches(List<Attach> attaches);

	/**
	 * <PRE>
	 * 1. MethodName : delete
	 * 2. ClassName  : AttachDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:40:07
	 * </PRE>
	 *   @return void
	 *   @param at
	 */
	void deleteByBoard(Attach at);

	/**
	 * <PRE>
	 * 1. MethodName : deleteFiles
	 * 2. ClassName  : AttachDao
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 12. 오후 9:02:40
	 * </PRE>
	 *   @return void
	 *   @param deletingFileArr
	 */
	void deleteFiles(List<Integer> deletingFileArr);

}
