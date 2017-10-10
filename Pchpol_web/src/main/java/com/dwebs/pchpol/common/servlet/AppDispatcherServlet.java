package com.dwebs.pchpol.common.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Class Name : CodeServlet.java <br>
 */
public class AppDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = -8191793258251930012L;
	
	private static final Logger logger = LoggerFactory.getLogger(AppDispatcherServlet.class);

	/**
	 * Controller를 초기화 한다.
	 * 
	 * @param ServletConfig
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			//MemoryUtil.initialize(getWebApplicationContext());
		} catch (Exception e) {
			logger.error(e.getMessage());
			new ServletException("Error CodeServlet.init()");
		}
	}
}
