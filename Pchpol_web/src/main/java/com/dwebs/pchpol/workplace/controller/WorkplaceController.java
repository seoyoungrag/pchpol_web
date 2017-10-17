/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceController.java
 * 2. Package : com.dwebs.pchpol.workplace.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 2:41:54
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dwebs.pchpol.common.controller.BaseController;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceController.java
 * 3. Package  : com.dwebs.pchpol.workplace.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 2:41:54
 * </PRE>
 */
@RestController
public class WorkplaceController extends BaseController {

	@RequestMapping(value = "/workplace/list.do")
	public ModelAndView workplaceListPage() {
		return new ModelAndView("workplace/workplaceList");
	}
}
