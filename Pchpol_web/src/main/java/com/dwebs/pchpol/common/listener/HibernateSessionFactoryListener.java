/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : HibernateSessionFactoryListener.java
 * 2. Package : com.dwebs.pchpol.common.listener
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 29. 오후 3:35:31
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : HibernateSessionFactoryListener.java
 * 3. Package  : com.dwebs.pchpol.common.listener
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 29. 오후 3:35:31
 * </PRE>
 */
@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {
	protected final Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryListener.class);

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
    	SessionFactory sessionFactory = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
    	if(sessionFactory != null && !sessionFactory.isClosed()){
    		logger.info("Closing sessionFactory");
    		sessionFactory.close();
    	}
    	logger.info("Released Hibernate sessionFactory resource");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
    	Configuration configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
    	logger.info("Hibernate Configuration created successfully");
    	
    	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	logger.info("ServiceRegistry created successfully");
    	SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
    	logger.info("SessionFactory created successfully");
    	
    	sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
    	logger.info("Hibernate SessionFactory Configured successfully");
	}

}
