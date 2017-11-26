package com.dwebs.pchpol.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FileHandler extends HttpServlet {    

    private static final String STORE_PATH_KEY = "uploadPath";
	public static final String FILE_STORE_PATH;
    static {
    	Properties props = new Properties();
			try {
				props.load(FileHandler.class.getClassLoader().getResourceAsStream("/pch_config/file.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			FILE_STORE_PATH = props.getProperty(STORE_PATH_KEY);
			
    }

/*	@Value("#{fileProperties['uploadPath']}")
	public String saveLocation;*/
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(FileHandler.class.getName());

	public FileHandler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//UtilFile utilFile = new UtilFile();
		String reqUrl = request.getRequestURL().toString();
		request.setCharacterEncoding("UTF-8");
		//saveLocation = utilFile.getSaveLocation(request);
		String saveLocation = FILE_STORE_PATH;
		if (reqUrl.contains("fileUpload")) {
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					for (FileItem item : multiparts) {
						if (!item.isFormField()) {
							String name = new File(item.getName()).getName();
							File directory = new File(saveLocation + File.separator);
							if (directory.exists() == false) {
								directory.mkdirs();
							}
							item.write(new File(saveLocation + File.separator + name));
							if (logger.isDebugEnabled()) {
								logger.debug("upload file path :  " + saveLocation + File.separator + name);
							}
						}
					}
					request.setAttribute("message", "File Uploaded Successfully");
				} catch (Exception ex) {
					request.setAttribute("message", "File Upload Failed due to " + ex);
				}
			} else {
				request.setAttribute("message", "Sorry this Servlet only handles file upload request");
			}
			// request.getRequestDispatcher("/noticeList.do").forward(request,
			// response);
		/*} else if (reqUrl.contains("mobileFileUpload")) {
			if (ServletFileUpload.isMultipartContent(request)) {
		        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		        MultipartFile multipartFile =  null; // multipart file class depends on which class you use assuming you are using org.springframework.web.multipart.commons.CommonsMultipartFile
		        Iterator<String> iterator = multipartRequest.getFileNames();
		        UtilFile utilFile = new UtilFile();
		        List<String> uploadPath = new ArrayList<String>();
		    	String id = String.valueOf(System.currentTimeMillis());
		        while (iterator.hasNext()) {
		            String key = (String) iterator.next();
		            multipartFile = (MultipartFile) multipartRequest.getFile(key);
		            String uPath = utilFile.fileUpload(multipartRequest, multipartFile, new Object(), id);
		            uploadPath.add(uPath);
		        }
		        //파일이 쪼개져있고, 확장자가 zip인 경우
		        try {
		        	Set<String> fileSet = new HashSet<String>();
		        	File file;
		        	String path;
		        	String partedFileName;
		        	String part;
		        	String ext;
		        	String oriFileName;
		        	for(String uPath : uploadPath){
		        		//현재 업로드된 파일
		        		file = new File(uPath);
		        		//파일이 업로드된 경로
		            	path = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator));
		            	//파일명
		            	partedFileName = file.getName();
		            	//파일이 끝의 확장자 가져오기
		            	part = partedFileName.substring(partedFileName.lastIndexOf('.')+1,partedFileName.length());
		            	try{
		            		Integer.parseInt(part);
		            		//파일 확장자가 zip인가?
		            		String extFileName = partedFileName.substring(0,partedFileName.length()-("."+part).length());
		            		ext = extFileName.substring(extFileName.lastIndexOf('.')+1,extFileName.length());
		            		if(ext.equalsIgnoreCase("zip")){
		                    	//확장자를 제외한 파일경로
		                    	oriFileName = partedFileName.substring(0,partedFileName.lastIndexOf("."));
		                    	//쪼개진 파일을 묶기 위해 묶어질 파일경로 set 생성
		                    	fileSet.add(path +File.separator+ oriFileName);
		                	}
		            	}catch(NumberFormatException e){
		            		logger.info("not a seperated file: "+uPath);
		            	}
		        	}
		        	//파일을 묶고 압축해제한다.
		        	for(String oriFilePath : fileSet){
		        		logger.info("join and decompress: "+oriFilePath);
		            	try{
		            		file = new File(oriFilePath);
		        			UtilFile.join(oriFilePath);	
		        			UtilFile.decompress(oriFilePath, file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));
		            	}catch(NumberFormatException e){
		            		e.printStackTrace();
		            	}

		        	}
		        	//분할 및 압축된 파일을 삭제한다.
		        	for(String oriFilePath : fileSet){
		        		logger.info("delete: "+oriFilePath);
		            	try{
		            		file = new File(oriFilePath);
		            		file.delete();
		            	}catch(NumberFormatException e){
		            		e.printStackTrace();
		            	}

		        	}
		        	for(String uPath : uploadPath){
		        		logger.info("delete: "+uPath);
		            	try{
		            		file = new File(uPath);
		            		file.delete();
		            	}catch(NumberFormatException e){
		            		e.printStackTrace();
		            	}
		        	}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("message", "Sorry this Servlet only handles file upload request");
			}*/
		} else if (reqUrl.contains("fileUploadForView")) {
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					String name = "";
					if (multiparts.size() > 0) {
						FileItem item = multiparts.get(0);
						if (!item.isFormField()) {
							name = new File(item.getName()).getName();
							File directory = new File(saveLocation + File.separator);
							if (directory.exists() == false) {
								directory.mkdirs();
							}
							item.write(new File(saveLocation + File.separator + File.separator + name));
							if (logger.isDebugEnabled()) {
								logger.debug(
										"upload file path :  " + saveLocation + File.separator + File.separator + name);
							}
						}
						response.setContentType("text/plain); charset=UTF-8");
						PrintWriter pw = response.getWriter();
						pw.print("{\"imageurl\" : \"" + saveLocation + File.separator + File.separator + name
								+ "\",\"filename\":\"" + name + "\",\"filesize\":600,\"imagealine\":\"C\"}");
						pw.flush();
						pw.close();
					} else {
						request.setAttribute("message", "Sorry this Servlet only handles file upload request");
					}
				} catch (Exception ex) {
					request.setAttribute("message", "File Upload Failed due to " + ex);
				}
			} else {
				request.setAttribute("message", "Sorry this Servlet only handles file upload request");
			}
		} else if (reqUrl.contains("fileDownload")) {
			String fileName = (String) request.getParameter("fileName");
			String[] fileNameArr = fileName.split("\\*");
			try {

				if (fileNameArr.length > 1) {
					List<File> filelist = new ArrayList<File>();
					for (int i = 0; i < fileNameArr.length; i++) {
						File filess = new File(
								saveLocation + File.separator + fileNameArr[i]);
						filelist.add(filess);
					}
					response.setContentType("application/octet-stream;charset=UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=downloadZip.zip" + ";");
					response.setHeader("Pragma", "no-cache;");
					response.setHeader("Expires", "-1;");
					// this.zip(filelist, new
					// BufferedOutputStream(response.getOutputStream()),fileNameArr);
				} else {
					String serverFile = saveLocation + File.separator + fileNameArr[0];

					if (logger.isDebugEnabled()) {
						logger.debug("download file path :  " + saveLocation + File.separator + fileNameArr[0]);
					}

					File downfile = new File(serverFile);
					if (!downfile.exists()) {
						throw new FileNotFoundException();
					}
					BufferedInputStream fin = null;
					BufferedOutputStream fout = null;

					String encFileName = new String();
					if (logger.isDebugEnabled()) {
						logger.debug("encFileName :  " + encFileName + ", " + encFileName.replaceAll("\\+", "%20"));
					}

					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < fileNameArr[0].length(); i++) {
						char c = fileNameArr[0].charAt(i);
						if (c > '~') {
							sb.append(URLEncoder.encode("" + c, "UTF-8"));
						} else {
							sb.append(c);
						}
					}
					encFileName = sb.toString();
					response.setContentType("application/octet-stream;charset=UTF-8");
					String oriName = (String) request.getParameter("oriName");
					if(oriName!=null&&!oriName.equals("")){
						sb = new StringBuffer();
						for (int i = 0; i < oriName.length(); i++) {
							char c = oriName.charAt(i);
							if (c > '~') {
								sb.append(URLEncoder.encode("" + c, "UTF-8"));
							} else {
								sb.append(c);
							}
						}
						encFileName = sb.toString();
					}
					response.setHeader("Content-Disposition", "attachment; filename=" + encFileName + ";");
					response.setHeader("Content-Transfer-Encoding", "binary;");
					response.setHeader("Pragma", "no-cache;");
					response.setHeader("Expires", "-1;");

					byte buf[] = new byte[2 * 1024];
					fin = new BufferedInputStream(new FileInputStream(serverFile));
					fout = new BufferedOutputStream(response.getOutputStream());
					int fread = 0;

					while ((fread = fin.read(buf)) != -1) {
						fout.write(buf, 0, fread);
					}
					if (fout != null) {
						fout.flush();
						fout.close();
					}
					if (fin != null) {
						fin.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}