/**
 * 0. Project  : 문서변환서버
 *
 * 1. FileName : VoNotFoundException.java
 * 2. Package : com.dwebs.converter.common.handler
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 9. 5. 오전 12:42:18
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 9. 5. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.handler.exception;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : VoNotFoundException.java
 * 3. Package  : com.dwebs.converter.common.exception
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 5. 오전 12:42:18
 * </PRE>
 */
public class ResultNotFoundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738253686798500214L;

    /**
     * result 명
     */
    private String resultName;
    /**
     * reuslt를 찾기 위한 필드명
     */
    private String searchName;
    /**
     * result를 찾기 위한 필드값
     */
    private String searchValue;
	public ResultNotFoundException(String message){
		super(message);
	}
	public ResultNotFoundException(String resultName, String searchName, String searchValue){
		this.resultName = resultName;
		this.searchName = searchName;
		this.searchValue = searchValue;
	}
	public String getResultName() {
		return resultName;
	}
	public String getSearchName() {
		return searchName;
	}
	public String getSearchValue() {
		return searchValue;
	}
	

}
