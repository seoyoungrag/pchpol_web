/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : TestController.java
 * 2. Package : com.dwebs.pchpol.common.test
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 25. 오후 4:20:27
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 25. :            : 신규 개발.
 */
package com.dwebs.pchpol.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.util.UtilFile;
import com.dwebs.pchpol.common.vo.Response;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TestController.java
 * 3. Package  : com.dwebs.pchpol.common.test
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 25. 오후 4:20:27
 * </PRE>
 */
@RestController
public class TestController extends BaseController {
	@RequestMapping(value = "api/test", method = RequestMethod.GET)
	public ResponseEntity<?> test(){
		Response res = new Response();
	
		logger.info("test");
		return ResponseEntity.ok(res);
	}
//  파일을 업로드하는 컨트롤러 클래스 메소드
    @RequestMapping(value = "api/upload", method = RequestMethod.POST)
//  인자로 MulfiPartFile 객체, MultipartHttpServletRequest 객체, 업로드 하려는 도메인 클래스를 받는다
    public ResponseEntity<?> upload(HttpServletRequest req, HttpServletResponse response){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile multipartFile =  null; // multipart file class depends on which class you use assuming you are using org.springframework.web.multipart.commons.CommonsMultipartFile
        Iterator<String> iterator = multipartRequest.getFileNames();
        UtilFile utilFile = new UtilFile();
        List<String> uploadPath = new ArrayList<String>();
    	String id = String.valueOf(System.currentTimeMillis());
    	String boardId = multipartRequest.getParameter("boardId");
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
		Response res = new Response();

        //System.out.println("RewardController reAddProCtrl reward : " + reward);
        
//      UtilFile 객체 생성
//      파일 업로드 결과값을 path로 받아온다(이미 fileUpload() 메소드에서 해당 경로에 업로드는 끝났음)
        //String uploadPath = utilFile.fileUpload(request, uploadFile, reward);
        
//      해당 경로만 받아 db에 저장
        //int n = rewardService.reAddServ(uploadPath, reward);
        
        //System.out.println("RewardController reAddProCtrl n : " + n);
        //System.out.println("RewardController reAddProCtrl uploadPath : " + uploadPath);
		res.setData(uploadPath);
		return ResponseEntity.ok(res);
    }
}


