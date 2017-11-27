/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : TroopsPlacementDelete.java
 * 2. Package : com.dwebs.pchpol.workplace.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 26. 오후 8:12:31
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 26. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.controller;

import java.io.Serializable;

import com.dwebs.pchpol.model.Code;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : TroopsPlacementDelete.java
 * 3. Package  : com.dwebs.pchpol.workplace.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 26. 오후 8:12:31
 * </PRE>
 */
public class TroopsPlacementDelete implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 201354757666499411L;
	Code troops;
	Code workplace;
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
	
	
}
