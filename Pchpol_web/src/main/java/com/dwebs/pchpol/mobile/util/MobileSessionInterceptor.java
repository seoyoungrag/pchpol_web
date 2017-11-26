package com.dwebs.pchpol.mobile.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Unit;

public class MobileSessionInterceptor extends HandlerInterceptorAdapter {

	List<String> chkUrls;
	List<String> unchkUrls;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setChkUrls(List chkUrls) {
		this.chkUrls = chkUrls;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setUnchkUrls(List unchkUrls) {
		this.unchkUrls = unchkUrls;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		for (int i = 0; i < unchkUrls.size(); i++) {

			if (request.getRequestURI().matches(unchkUrls.get(i))) {
				return true;
			}

			if (request.getRequestURI().indexOf(unchkUrls.get(i)) != -1) {
				return true;
			}
		}

		for (int i = 0; i < chkUrls.size(); i++) {
			if (request.getRequestURI().matches(chkUrls.get(i))||request.getRequestURI().indexOf(chkUrls.get(i)) != -1 ) {
				try {
					HttpSession session = request.getSession();
					Admin admin = (Admin) session.getAttribute("admin");
					Unit unit = (Unit) session.getAttribute("unit");
					
					if (admin == null || admin.getAdminNo() == 0) {
						if(unit == null || unit.getUnitNo() == 0){
							response.sendRedirect(request.getContextPath() + "/mobile/login.do");
							return false;	
						}else{
							return true;
						}
					}else{
						return true;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				return true;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
	}
}
