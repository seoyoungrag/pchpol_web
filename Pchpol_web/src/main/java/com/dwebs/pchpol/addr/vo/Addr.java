/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AddrVo.java
 * 2. Package : com.dwebs.pchpol.addr.vo
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 2:20:33
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.addr.vo;

import java.io.Serializable;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AddrVo.java
 * 3. Package  : com.dwebs.pchpol.addr.vo
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 2:20:33
 * </PRE>
 */
public class Addr implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1609663568355382367L;
	String zipCode;
	String address;
	String roadNm;
	String buildingMainNm;
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoadNm() {
		return roadNm;
	}
	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}
	public String getBuildingMainNm() {
		return buildingMainNm;
	}
	public void setBuildingMainNm(String buildingMainNm) {
		this.buildingMainNm = buildingMainNm;
	}
}
