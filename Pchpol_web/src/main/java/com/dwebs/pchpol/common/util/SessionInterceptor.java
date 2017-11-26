package com.dwebs.pchpol.common.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dwebs.pchpol.model.Admin;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	List<String> urls;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setUrls(List urls) {
		this.urls = urls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		for (int i = 0; i < urls.size(); i++) {

			if (request.getRequestURI().matches(urls.get(i))) {
				return true;
			}

			if (request.getRequestURI().indexOf(urls.get(i)) != -1) {
				return true;
			}
		}

		try {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");

			if (admin == null || admin.getAdminNo() == 0) {
				response.sendRedirect(request.getContextPath() + "/login.do");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
