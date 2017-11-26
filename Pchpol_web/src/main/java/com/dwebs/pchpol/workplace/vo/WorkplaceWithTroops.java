/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceWithTroops.java
 * 2. Package : com.dwebs.pchpol.workplace.vo
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 12:40:00
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dwebs.pchpol.model.Code;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceWithTroops.java
 * 3. Package  : com.dwebs.pchpol.workplace.vo
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 12:40:00
 * </PRE>
 */
public class WorkplaceWithTroops implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3825871477237923447L;
	
	/**
	 * 부대는 지방청, 구분, 부대명, 세부소속 값이 담겨있는데 부대명을 기준으로 로우를 그룹화해야한다. 
	 */
	Code workplace; //근무지
	Code troops;
	List<Code> troopsDetail; //세부소속ID와, 세부소속값
	Date mobilDate;
	List<String> workTimes;
	
	public WorkplaceWithTroops() {
		super();
	}

	public WorkplaceWithTroops(Code workplace, Code troops, List<Code> troopsDetail) {
		super();
		this.workplace = workplace;
		this.troops = troops;
		this.troopsDetail = troopsDetail;
	}

	public WorkplaceWithTroops(Code workplace, Code troops, List<Code> troopsDetail, Date mobilDate,
			List<String> workTimes) {
		super();
		this.workplace = workplace;
		this.troops = troops;
		this.troopsDetail = troopsDetail;
		this.mobilDate = mobilDate;
		this.workTimes = workTimes;
	}

	public WorkplaceWithTroops(String col_0_0_, String col_1_0_, String col_2_0_, String col_3_0_, String col_4_0_) {
		super();
		Code workplace = new Code();
		workplace.setCode1depth(col_0_0_);
		workplace.setCode2depth(col_1_0_);
		Code troops = new Code();
		troops.setCode1depth(col_2_0_);
		troops.setCode2depth(col_3_0_);
		troops.setCode3depth(col_4_0_);
		this.workplace = workplace;
		this.troops = troops;
		this.troopsDetail = new ArrayList<Code>();
		this.workTimes = new ArrayList<String>();
	}

	public WorkplaceWithTroops(String col_0_0_, String col_1_0_) {
		super();
		Code workplace = new Code();
		workplace.setCode1depth(col_0_0_);
		workplace.setCode2depth(col_1_0_);
		Code troops = new Code();
		this.workplace = workplace;
		this.troops = troops;
		this.troopsDetail = new ArrayList<Code>();
		this.workTimes = new ArrayList<String>();
	}
	
	public Code getWorkplace() {
		return workplace;
	}
	public void setWorkplace(Code workplace) {
		this.workplace = workplace;
	}
	public Code getTroops() {
		return troops;
	}
	public void setTroops(Code troops) {
		this.troops = troops;
	}
	public List<Code> getTroopsDetail() {
		return troopsDetail;
	}
	public void setTroopsDetail(List<Code> troopsDetail) {
		this.troopsDetail = troopsDetail;
	}

	public Date getMobilDate() {
		return mobilDate;
	}

	public void setMobilDate(Date mobilDate) {
		this.mobilDate = mobilDate;
	}

	public List<String> getWorkTimes() {
		return workTimes;
	}

	public void setWorkTimes(List<String> workTimes) {
		this.workTimes = workTimes;
	}

}
