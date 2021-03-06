/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : LoginController.java
 * 2. Package : com.dwebs.pchpol.login.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 29. 오전 3:36:01
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dwebs.pchpol.admin.service.AdminService;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.model.Admin;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : LoginController.java
 * 3. Package  : com.dwebs.pchpol.login.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 29. 오전 3:36:01
 * </PRE>
 */
@RestController
public class LoginAdminController {

	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;

	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginProcess(HttpServletRequest request, @ModelAttribute Admin admin){
		Response res = new Response();
		Admin loginAdmin = adminService.login(admin);
		if(loginAdmin==null){
			res = new Response(false, "fail");
			res.setData(admin);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("admin", loginAdmin);
			res.setData(loginAdmin);
		}
		return ResponseEntity.ok(res);
	}


	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
}
