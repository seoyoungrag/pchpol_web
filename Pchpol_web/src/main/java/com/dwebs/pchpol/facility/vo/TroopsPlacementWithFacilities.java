/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : TroopsPlacementWithFacilities.java
 * 2. Package : com.dwebs.pchpol.facility.vo
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 12:40:00
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.facility.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Facility;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TroopsPlacementWithFacilities.java
 * 3. Package  : com.dwebs.pchpol.facility.vo
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 12:40:00
 * </PRE>
 */
public class TroopsPlacementWithFacilities implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3825871477237923447L;
	
	Code troops;
	Code workplace;
	List<Facility> foodFacility = new ArrayList<Facility>();
	List<Facility> bedFacility = new ArrayList<Facility>();
	Date facilityMobilEndDt;
	Date facilityMobilStartDt;
	int[] facilityNo;
	public Code getTroops() {
		return troops;
	}
	public void setTroops(Code troops) {
		this.troops = troops;
	}
	public Code getWorkplace() {
		return workplace;
	}
	public void setWorkplace(Code workplace) {
		this.workplace = workplace;
	}
	public List<Facility> getFoodFacility() {
		return foodFacility;
	}
	public void setFoodFacility(List<Facility> foodFacility) {
		this.foodFacility = foodFacility;
	}
	public List<Facility> getBedFacility() {
		return bedFacility;
	}
	public void setBedFacility(List<Facility> bedFacility) {
		this.bedFacility = bedFacility;
	}
	public Date getFacilityMobilEndDt() {
		return facilityMobilEndDt;
	}
	public void setFacilityMobilEndDt(Date facilityMobilEndDt) {
		this.facilityMobilEndDt = facilityMobilEndDt;
	}
	public Date getFacilityMobilStartDt() {
		return facilityMobilStartDt;
	}
	public void setFacilityMobilStartDt(Date facilityMobilStartDt) {
		this.facilityMobilStartDt = facilityMobilStartDt;
	}
	public int[] getFacilityNo() {
		return facilityNo;
	}
	public void setFacilityNo(int[] facilityNo) {
		this.facilityNo = facilityNo;
	}
	
}
