/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : CustomViewResolver.java
 * 2. Package : com.dwebs.pchpol.common.view
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 5. 오후 10:04:42
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 5. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.view;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CustomViewResolver.java
 * 3. Package  : com.dwebs.pchpol.common.view
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 5. 오후 10:04:42
 * </PRE>
 */
public class CustomViewResolver extends UrlBasedViewResolver implements Ordered {
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.UrlBasedViewResolver#loadView(java.lang.String, java.util.Locale)
	 */
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		AbstractUrlBasedView view = buildView(viewName);
		View viewObj = (View) getApplicationContext().getAutowireCapableBeanFactory().initializeBean(view, viewName);
		if(viewObj instanceof JstlView){
			JstlView jv = (JstlView) viewObj;
			if(jv.getBeanName().indexOf(".jsp")!=-1){
				return null;
			}
		}
		return viewObj;
	}
}
