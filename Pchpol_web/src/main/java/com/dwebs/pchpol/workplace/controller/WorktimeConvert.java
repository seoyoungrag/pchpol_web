/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorktimeConvert.java
 * 2. Package : com.dwebs.pchpol.workplace.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 11. 16. 오전 1:02:16
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 11. 16. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.controller;

import java.util.HashMap;
import java.util.Map;

import com.dwebs.pchpol.model.WorkplacePlacementDetail;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorktimeConvert.java
 * 3. Package  : com.dwebs.pchpol.workplace.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 11. 16. 오전 1:02:16
 * </PRE>
 */
public class WorktimeConvert {

    private static final Map<String, String> myMap;
    static
    {
        myMap = new HashMap<String, String>();
        for(int i=0;i<9;i++){
        	myMap.put("time"+String.valueOf(i+1),"0"+String.valueOf(i)+":00~0"+String.valueOf(i+1)+":00");
        }
        myMap.put("time10","09:00~10:00");
        for(int i=10;i<24;i++){
        	myMap.put("time"+String.valueOf(i+1),String.valueOf(i)+":00~"+String.valueOf(i+1)+":00");
        }
    }
	

	public static String startAndEnd(WorkplacePlacementDetail workplacePlacementDetail) {
		return myMap.get(workplacePlacementDetail.getWorkplacePlacementDetailWorkTime());
	}

	public static String end(WorkplacePlacementDetail workplacePlacementDetail) {
		return myMap.get(workplacePlacementDetail.getWorkplacePlacementDetailWorkTime()).substring(6, 11);
		
	}

	public static String start(WorkplacePlacementDetail workplacePlacementDetail) {
		return myMap.get(workplacePlacementDetail.getWorkplacePlacementDetailWorkTime()).substring(0, 5);
	}
}
