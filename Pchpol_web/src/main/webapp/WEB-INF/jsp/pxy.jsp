<%@page import="java.io.DataOutputStream"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!
/**
 * @param istream
 * @param ostream
 * @throws Exception
 */
private void copy(InputStream istream, OutputStream ostream) throws Exception {
	int bufferSize = 4*4*1024;
	byte[] buffer = new byte[bufferSize];
	int read;
	while ((read = istream.read(buffer)) != -1) {
		ostream.write(buffer, 0, read);
	}
}

/**
 * @param connection
 * @param request
 */
private void transferHTTPRequestHeaders(HttpURLConnection connection, HttpServletRequest request){
	//TODO make sure all headers are copied to target, see for HTTP headers http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html
	//Do request.getProperties to get request properties
	if(request.getHeader("Accept") != null){
		connection.setRequestProperty("Accept", request.getHeader("Accept"));
	}
	if(request.getHeader("Accept-Charset") != null){
		connection.setRequestProperty("Accept-Charset", request.getHeader("Accept-Charset"));
	}
	if(request.getHeader("Accept-Encoding") != null){
		//TODO browsers accept gzipped, should proxy accept gzip and how to handle it?
		//connection.setRequestProperty("Accept-Encoding", request.getHeader("Accept-Encoding"));
	}
	if(request.getHeader("Authorization") != null){
		connection.setRequestProperty("Authorization", request.getHeader("Authorization"));
	}
	if(request.getHeader("Connection") != null){
		//TODO HTTP/1.1 proxies MUST parse the Connection header field before a message is forwarded and, for each connection-token in this field, remove any header field(s) from the message with the same name as the connection-token.
		//connection.setRequestProperty("Connection", request.getHeader("Connection"));
	}

	//set de-facto standard proxy headers (x-forwarded-for, others?s)
	if(request.getHeader("X-Forwarded-For") != null){
		connection.setRequestProperty("X-Forwarded-For", request.getHeader("X-Forwarded-For"));//TODO append IP proxy
	} else{
		connection.setRequestProperty("X-Forwarded-For", request.getRemoteAddr());//TODO append IP proxy
	}
}

/**
 * @param connection
 * @param request
 */
private void transferHTTPRequestHeadersForPOST(HttpURLConnection connection, HttpServletRequest request){
	if(request.getHeader("Content-Type") != null){
		connection.setRequestProperty( "Content-Type",request.getContentType());
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	} else {
		//throw exception?
	}
}

/**
 * @param remoteHost
 * @return
 */
private boolean isAllowedHost(String remoteHost){
	//TODO checking of host
	return true;
}
%>
<%
out.clear();
out = pageContext.pushBody();

HttpURLConnection connection = null;
InputStream istream = null; //input to proxy
OutputStream ostream = null; //output from proxy
InputStream connectionIstream = null; //output for the target is input for the connection
OutputStream connectionOstream = null; //input for the target is output for the connection

String remoteHost = request.getRemoteHost(); // get host address of client - for checking allowedHosts
boolean allowedHost = isAllowedHost(remoteHost); //The allowedHosts are the hosts that are allowed to use the Open Proxy.

try {
	// easy way to ignore case of param?
	if(request.getParameter("url") != null && request.getParameter("url") != "" && allowedHost) {

		// HTTPUrlConnection looks at http.proxyHost and http.proxyPort system properties.
		// Make sure these properties are set these if you are behind a proxy.

		//step 1: initialize
		String requestMethod = request.getMethod();

		URL targetURL = null;

		//step 2: proxy requests
		if (requestMethod.equals("GET")){
			String query = "?";
			for( Enumeration e = request.getParameterNames() ; e.hasMoreElements();){
				String parameterName = e.nextElement().toString();
				if( !parameterName.equals("url") )
				query += "&" + URLEncoder.encode( parameterName ) + "=" + URLEncoder.encode( request.getParameter( parameterName ) );
			}

			String url = request.getParameter("url");
			if( url.indexOf( "http://" ) == -1 ) url = "http://" + url;
			if( query.length() > 2 )
			{
				query = query.substring( 2 );
				url = url + "?" + query;
			}

			targetURL = new URL(url);
			connection = (HttpURLConnection) targetURL.openConnection();
			connection.setRequestMethod(requestMethod);

			transferHTTPRequestHeaders(connection, request);

			//default for setDoInput is true
			connectionIstream = connection.getInputStream();
		};
		if (requestMethod.equals("POST")){
			targetURL = new URL(request.getParameter("url"));
			connection = (HttpURLConnection) targetURL.openConnection();
			connection.setRequestMethod(requestMethod);

			transferHTTPRequestHeaders(connection, request);
			transferHTTPRequestHeadersForPOST(connection, request);
			int clength = request.getContentLength();//clength is for checking if there is a POST body. Is that sufficient?

			if(clength > 0) {
				istream = request.getInputStream();

				StringBuilder sb = new StringBuilder();
				for(Map.Entry<String, String[]> e : request.getParameterMap().entrySet() )
				{
					if(sb.length() > 0) sb.append('&');
					String[] temp =e.getValue();
					for( String s:temp )
					{
						sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(s, "UTF-8"));
					}
				}

				String urlParameters = sb.toString();

				connection.setDoOutput(true);//for POST we need to write to connection
				connection.setRequestProperty( "Content-Length",Integer.toString(clength)); //only valid for POST request
				connectionOstream = connection.getOutputStream();
				DataOutputStream wr = new DataOutputStream(connectionOstream);
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				//copy the request body to remote outputStream
				copy(istream, connectionOstream);
			}
			connectionIstream = connection.getInputStream();
		}

		//step 3: return output
		//can output be the same for GET/POST? or different return headers?
		//servlet may return 3 things: status code, response headers, response body
		// status code and headers have to be set before response body
		response.setContentType(connection.getContentType());
		ostream = response.getOutputStream();
		copy(connectionIstream, ostream);
	}
	// if not targetURL send page that targetURL is required param
} catch (Exception e){
	response.setStatus(500); //what will user get? default page for response code
	//WMS/WFS have specific responses to errors
	//response.getWriter();//will writing custom result help
	//e.printStackTrace();
} finally {
	if(istream != null) { istream.close(); }
	if(ostream != null) { ostream.close(); }
	if(connectionIstream != null) { connectionIstream.close(); }
	if(connectionOstream != null) { connectionOstream.close(); }
}

%>