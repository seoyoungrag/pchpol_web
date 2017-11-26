/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : UtilFile.java
 * 2. Package : com.dwebs.pchpol.common.util
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 25. 오후 4:35:51
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 25. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : UtilFile.java
 * 3. Package  : com.dwebs.pchpol.common.util
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 25. 오후 4:35:51
 * </PRE>
 */
@Component
public class UtilFile {
	protected static final Logger logger = LoggerFactory.getLogger(UtilFile.class);
    String fileName = "";
    
//  프로젝트 내 지정된 경로에 파일을 저장하는 메소드
//  DB에는 업로드된 전체 경로명으로만 지정되기 때문에(업로드한 파일 자체는 경로에 저장됨)
//  fileUpload() 메소드에서 전체 경로를 리턴받아 DB에 경로 그대로 저장   
    public String fileUpload(MultipartHttpServletRequest request,
                                        MultipartFile uploadFile, Object obj, String id) {
        String path = "";
        String fileName = "";
        
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        try {
			fileName = new String(uploadFile.getOriginalFilename().getBytes("8859_1"),"UTF-8");
            byte[] bytes = uploadFile.getBytes();
            path = getSaveLocation(request, obj);
            
            logger.info("UtilFile fileUpload fileName : " + fileName);
            logger.info("UtilFile fileUpload uploadPath : " + path);
            
            File file = new File(path);
            
            //파일명이 중복으로 존재할 경우
            if (fileName != null && !fileName.equals("")) {
                if (file.exists()) {
                	//파일명 앞에 업로드 시간 초단위로 붙여 파일명 중복을 방지
                    // 파일 명 변경(uuid로 암호화) 
                    //String ext = fileName.substring(fileName.lastIndexOf('.')); // 확장자 
                    //String saveFileName = getUuid() + ext;
                	//현재시간을 입력해버리면 나중에 찾아갈 수가 없다.
                    //fileName = System.currentTimeMillis() + "_" + fileName;
                	fileName = id + "_" + fileName;
                    
                    file = new File(path + fileName);
                }
            }
            
            logger.info("UtilFile fileUpload final fileName : " + fileName);
            logger.info("UtilFile fileUpload file : " + file);
            
            out = new FileOutputStream(file);
            
            logger.info("UtilFile fileUpload out : " + out);
            
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path + fileName;
    }

//  업로드 파일 저장 경로 얻는 메소드
//  업로드한 파일의 경로가 도메인 별로 달라야 했기 때문에 도메인의 형을 비교하여 파일 저장 정로를 다르게 지정함
    String getSaveLocation(MultipartHttpServletRequest request, Object obj) {
        
        //String uploadPath = request.getSession().getServletContext().getRealPath("/");
    	String uploadPath = FileHandler.FILE_STORE_PATH + File.separator;
        /*
//      Reward인 경우
        if (obj instanceof Reward) {
            attachPath += "reward/";
//      Approval인 경우
        } else if(obj instanceof Draft) {
            attachPath += "approval/";
//      Document인 경우            
        } else {
            attachPath += "document/";
        }
        */
        File file = new File(uploadPath);
        if(!file.exists()){
        	file.mkdirs();
        }
        logger.info("UtilFile getSaveLocation path : " + uploadPath);
        
        return uploadPath;
    }

    //파일 쪼개는 사이즈
    public static long chunkSize = (long)(10 * 1024 * 1024);
    //파일 묶기 메소드
    public static void join(String baseFilename) throws IOException
    {
        // 쪼개진 파일 수를 가져온다.
        int numberParts = getNumberParts(baseFilename);

        // 파일들을 합친다.
        // 쪼개진 파일들 중 일부러 삭제하지 않았으면 정상적으로 파일이 생성되어야 한다.
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(baseFilename));
        for (int part = 0; part < numberParts; part++)
        {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(baseFilename + "." + part));

            int b;
            while ( (b = in.read()) != -1 )
                out.write(b);

            in.close();
        }
        out.close();
    }
    //쪼개진 파일 가져오기
    public static List<File> getSplitedFiles(String baseFilename) throws IOException{
        List<File> fileAr = new ArrayList<File>();
        File directory = new File(baseFilename).getAbsoluteFile().getParentFile();
        final String justFilename = new File(baseFilename).getName();
        String[] matchingFiles = directory.list(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.startsWith(justFilename) && name.substring(justFilename.length()).matches("^\\.\\d+$");
            }
        });
        for(int i = 0 ;i < matchingFiles.length;i++){
            fileAr.add(new File(directory.getAbsolutePath()+"/"+matchingFiles[i]));
        }
        return fileAr;
    }
    //쪼개진 파일 갯수 가져오기
    private static int getNumberParts(String baseFilename) throws IOException
    {
        // 같은 디렉터리내의 .0 으로 끝나는 파일들을 찾아 갯수를 반환한다.
        File directory = new File(baseFilename).getAbsoluteFile().getParentFile();
        final String justFilename = new File(baseFilename).getName();
        String[] matchingFiles = directory.list(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.startsWith(justFilename) && name.substring(justFilename.length()).matches("^\\.\\d+$");
            }
        });
        return matchingFiles.length;
    }
    //파일 쪼개기
    public static void split(String filename) throws FileNotFoundException, IOException
    {
        //파일을 연다.
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));

        //파일 사이즈를 가져 온다.
        File f = new File(filename);
        long fileSize = f.length();

        // 파일쪼개어 저장하기 청크사이즈만큼 반복한다.
        int subfile;
        for (subfile = 0; subfile < fileSize / chunkSize; subfile++)
        {
            //쪼갤 파일의 스트림 생성한다.
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename + "." + subfile));

            //스트림에 파일을 생성한다. byte를 쓴다.
            for (int currentByte = 0; currentByte < chunkSize; currentByte++)
            {
                // 원본 파일에서 청크 사이즈 만큼 파일을 읽어와 쪼갤 파일에 쓴다.
                out.write(in.read());
            }

            // 파일스트림을 닫는다.
            out.close();
        }

        // 마지막으로 쪼갠 파일은 청크사이즈 보다 작음
        if (fileSize != chunkSize * (subfile - 1))
        {
            // outfile 생성
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filename + "." + subfile));

            // 나머지 남은 파일 byte 쓴다
            int b;
            while ((b = in.read()) != -1)
                out.write(b);

            // 파일스트림을 닫는다.
            out.close();
        }

        // 원본 파일 스트림을 닫는다.
        in.close();
    }
    //압축할 때 지정할 파일확장자
    public static String compressedFIleExt = ".zip";
    //파일을 압축(원본, 압축할 파일명)
    public static void compress(String inputFile, String compressedFile) {
        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            File inputFileH = new File(inputFile);


            // 압축 파라메터 초기화
            ZipParameters parameters = new ZipParameters();

            // 무손실 압축 지정
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);


            //DEFLATE_LEVEL_FASTEST     - Lowest compression level but higher speed of compression
            //DEFLATE_LEVEL_FAST        - Low compression level but higher speed of compression
            //DEFLATE_LEVEL_NORMAL  - Optimal balance between compression level/speed
            //DEFLATE_LEVEL_MAXIMUM     - High compression level with a compromise of speed
            //DEFLATE_LEVEL_ULTRA       - Highest compression level but low speed
            // 보통속도의 압축률
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            //Set the encryption flag to true
            // 암호화 지정 안함
            parameters.setEncryptFiles(false);

            //Set the encryption method to AES Zip Encryption
            // 암호화 지정시 AES로 압축
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);

            //AES_STRENGTH_128 - For both encryption and decryption
            //AES_STRENGTH_192 - For decryption only
            //AES_STRENGTH_256 - For both encryption and decryption
            //Key strength 192 cannot be used for encryption. But if a zip file already has a
            //file encrypted with key strength of 192, then Zip4j can decrypt this file
            // 암호화 키 생성
            parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

            // 파일을 압축한다.
            zipFile.addFile(inputFileH, parameters);
            long uncompressedSize = inputFileH.length();
            File outputFileH = new File(compressedFile);
            long comrpessedSize = outputFileH.length();

            //logger.info.println("Size "+uncompressedSize+" vs "+comrpessedSize);
            double ratio = (double) comrpessedSize / (double) uncompressedSize;
            logger.info("File compressed with compression ratio : " + ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //파일 압축을 해제(압축된 파일, 아북풀 파일명)
    public static void decompress(String compressedFile, String destination) {
        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            /*
            if (zipFile.isEncrypted()) {
                zipFile.setPassword("123");
            }
            */
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }

        logger.info("File Decompressed");
    }

	/**
	 * <PRE>
	 * 1. MethodName : getSaveLocation
	 * 2. ClassName  : UtilFile
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:16:42
	 * </PRE>
	 *   @return String
	 *   @param request
	 *   @return
	 */
	public String getSaveLocation(HttpServletRequest request) {
        //String uploadPath = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = FileHandler.FILE_STORE_PATH;
        File file = new File(uploadPath);
        if(!file.exists()){
        	file.mkdirs();
        }
        logger.info("UtilFile getSaveLocation path : " + uploadPath);
        
        return uploadPath;
	}
}


