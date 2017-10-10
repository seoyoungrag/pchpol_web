package com.dwebs.pchpol.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ConvertCode {
	
	public enum ConvertType {
		/**
		 * hwp-hwp: 한글변환 
		 */
		CONVERT_TYPE_HWP_HWP("hwp-hwp"), 
		/**
		 * hwp-img: 이미지변환
		 */
		CONVERT_TYPE_HWP_IMG("hwp-img");
	    private String value;
	    ConvertType(String value){
	        this.value = value;
	    }
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		public static String convertTypeList(){
	    	List<String> convertTypeList = new ArrayList<String>();
	    	for(ConvertType type : ConvertType.values()){
	    		convertTypeList.add(type.getValue());
	    	}
	    	return StringUtils.join(convertTypeList,",").toString();
		}
	}

	public enum ConvertStatus {
		/**
		 * 대기중 
		 */
		READY("R"), 
		/**
		 * 진행중
		 */
		PROCESSING("P"), 
		/**
		 * 완료
		 */
		SUCESS("S"), 
		/**
		 * 실패
		 */
		FAIL("F");
	    private String value;
	    ConvertStatus(String value){
	        this.value = value;
	    }
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		public static String convertStatusList(){
	    	List<String> convertStatusList = new ArrayList<String>();
	    	for(ConvertStatus type : ConvertStatus.values()){
	    		convertStatusList.add(type.getValue());
	    	}
	    	return StringUtils.join(convertStatusList,",").toString();
		}
	}

	public enum StatisticType {
		/**
		 * 변환 타입 통계 
		 */
		CONVERT_TYPE("ConvertType"),
		/**
		 * 변환 상태 통계
		 */
		CONVERT_STATUS("ConvertStatus"),
		/**
		 * 변환 시간별 상태 히스토리 통계
		 */
		CONVERT_HOURLY_STAT("ConvertHourlyStat");
	    private String value;
	    StatisticType(String value){
	        this.value = value;
	    }
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		public static String statisticTypeList(){
	    	List<String> statisticTypeList = new ArrayList<String>();
	    	for(StatisticType type : StatisticType.values()){
	    		statisticTypeList.add(type.getValue());
	    	}
	    	return StringUtils.join(statisticTypeList,",").toString();
		}
	}

	public enum InsertType {
		/**
		 * I: 이미지 입력 
		 */
		I("I"), 
		/**
		 * T: 텍스트 입력
		 */
		T("T");
	    private String value;
	    InsertType(String value){
	        this.value = value;
	    }
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		public static String insertTypeList(){
	    	List<String> insertTypeList = new ArrayList<String>();
	    	for(InsertType type : InsertType.values()){
	    		insertTypeList.add(type.getValue());
	    	}
	    	return StringUtils.join(insertTypeList,",").toString();
		}
	}
}
