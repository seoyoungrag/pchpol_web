/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AttachService.java
 * 2. Package : com.dwebs.pchpol.attach.service
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 28. 오후 10:33:43
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 28. :            : 신규 개발.
 */
package com.dwebs.pchpol.attach.service;

import java.util.List;

import com.dwebs.pchpol.model.Attach;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AttachService.java
 * 3. Package  : com.dwebs.pchpol.attach.service
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 28. 오후 10:33:43
 * </PRE>
 */
public interface AttachService {

	/**
	 * <PRE>
	 * 1. MethodName : insertAttaches
	 * 2. ClassName  : AttachService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:33:54
	 * </PRE>
	 *   @return void
	 *   @param attaches
	 */
	void insertAttaches(List<Attach> attaches);

	/**
	 * <PRE>
	 * 1. MethodName : insertBoardAttaches
	 * 2. ClassName  : AttachService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:38:01
	 * </PRE>
	 *   @return void
	 *   @param attaches
	 */
	void insertBoardAttaches(List<Attach> attaches);

	/**
	 * <PRE>
	 * 1. MethodName : deleteFiles
	 * 2. ClassName  : AttachService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 12. 오후 9:02:06
	 * </PRE>
	 *   @return void
	 *   @param deletingFileArr
	 */
	void deleteFiles(List<Integer> deletingFileArr);

	/**
	 * <PRE>
	 * 1. MethodName : insertBoardAttachesMobile
	 * 2. ClassName  : AttachService
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 11. 12. 오후 9:38:51
	 * </PRE>
	 *   @return void
	 *   @param attaches
	 */
	void insertBoardAttachesMobile(List<Attach> attaches);

}
