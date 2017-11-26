/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : TroopsPlacementModel.java
 * 2. Package : com.dwebs.pchpol.workplace.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 16. 오후 2:16:36
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 16. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.controller;

import java.io.Serializable;
import java.util.List;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TroopsPlacementModel.java
 * 3. Package  : com.dwebs.pchpol.workplace.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 16. 오후 2:16:36
 * </PRE>
 */
public class TroopsPlacementModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -543756980390955941L;
	private List<Integer> code1; //부대
	private int code2; //근무지
	public List<Integer> getCode1() {
		return code1;
	}
	public void setCode1(List<Integer> code1) {
		this.code1 = code1;
	}
	public int getCode2() {
		return code2;
	}
	public void setCode2(int code2) {
		this.code2 = code2;
	}
	public TroopsPlacementModel() {
		super();
	}
	public TroopsPlacementModel(List<Integer> code1, int code2) {
		super();
		this.code1 = code1;
		this.code2 = code2;
	}

}
