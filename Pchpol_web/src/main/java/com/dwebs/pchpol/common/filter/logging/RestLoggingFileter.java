/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : RestLoggingFileter.java
 * 2. Package : com.dwebs.converter.common.filter
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 6. 오전 11:34:51
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 6. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.filter.logging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : RestLoggingFileter.java
 * 3. Package  : com.dwebs.converter.common.filter
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 6. 오전 11:34:51
 * </PRE>
 */
@WebFilter(filterName = "RestLoggingFileter", urlPatterns = { "/converts/*" })
public class RestLoggingFileter implements Filter {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		//nothing to do
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);

        if(!(requestWrapper.getMethod().equalsIgnoreCase("get") && requestWrapper.getRequestURI().startsWith("/doc_converter/converts/statistic/"))
        	&&!(requestWrapper.getMethod().equalsIgnoreCase("get") && requestWrapper.getRequestURI().equals("/doc_converter/converts"))
        		){
	        Enumeration<String> requestHeaderNames = requestWrapper.getHeaderNames();
	        while (requestHeaderNames.hasMoreElements()) {
	            String headerName = requestHeaderNames.nextElement();
	            Enumeration<String> headers = requestWrapper.getHeaders(headerName);
	            while (headers.hasMoreElements()) {
	                String headerValue = headers.nextElement();
	                logger.info(" Request HEADER - {} : {} ", headerName, headerValue );
	            }
	            
	        }
	        logger.info(" Request METHOD:{} URI:{}  ", requestWrapper.getMethod(), requestWrapper.getRequestURI());
	        if(requestWrapper.getMethod().equalsIgnoreCase("POST")||requestWrapper.getMethod().equalsIgnoreCase("PUT")){
	        	logger.info(" Request Body:  {}", IOUtils.toString(requestWrapper.getInputStream(), "UTF-8"));
	        }
        }
        filterChain.doFilter(requestWrapper, servletResponse);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//nothing to do
	}
	
	class HttpRequestWrapper extends HttpServletRequestWrapper {

		private byte[] bodyData;
		
		public HttpRequestWrapper(HttpServletRequest request) throws IOException {
			super(request);
			InputStream is = super.getInputStream();
			bodyData = IOUtils.toByteArray(is);
		}
		
		@Override
		public ServletInputStream getInputStream() throws IOException {
			final ByteArrayInputStream bis = new ByteArrayInputStream(bodyData);
			return new ServletImpl(bis);
		}
	}

	class ServletImpl extends ServletInputStream {

		private InputStream is;

		public ServletImpl(InputStream bis) {
			is = bis;
		}

		@Override
		public int read() throws IOException {
			return is.read();
		}

		@Override
		public int read(byte[] b) throws IOException {
			return is.read(b);
		}
	}
}