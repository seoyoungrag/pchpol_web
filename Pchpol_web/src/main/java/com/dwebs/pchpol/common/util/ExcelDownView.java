/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : ExcelDownView.java
 * 2. Package : com.dwebs.pchpol.common.util
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 29. 오전 2:34:10
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : ExcelDownView.java
 * 3. Package  : com.dwebs.pchpol.common.util
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 29. 오전 2:34:10
 * </PRE>
 */
public class ExcelDownView extends AbstractExcelView {
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int topTitleRow = 0;
		int titleDateRow = 2;
		int titleRow = 3;
		int contentRow = 4;
		// 1. 각각의 controller에서 마지막에 넘긴 ModelAndView 가져옴
		Map<String, Object> map = (Map<String, Object>) model.get("ListExcelDownMap");

		// 2. 시트이름 설정
		String sheetName = (String) map.get("sheetNm");
		String sheetStyle = (String) map.get("sheetSt");// 시트 스타일 설정

		// 3. 엑셀파일 생성
		HSSFCell cell = null;
 
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.createRow(topTitleRow).createCell(topTitleRow).setCellValue(sheetName);// 시트(0, 0)셀에 제목과 같은 텍스트 세팅
		sheet.setDefaultColumnWidth((short) 12);

		// 셀병합
		sheet.addMergedRegion(new CellRangeAddress(0, // 시작 행번호
				0, // 마지막 행번호
				0, // 시작 열번호
				5 // 마지막 열번호
		));

		// 4. 컬럼제목 및 색세팅
		// 셀 폰트 & 스타일 추가
		CellStyle titleCellStyle = workbook.createCellStyle();
		titleCellStyle.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
		titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
		titleCellStyle.setBorderBottom(BorderStyle.THIN);
		titleCellStyle.setBorderTop(BorderStyle.THIN);
		titleCellStyle.setBorderRight(BorderStyle.THIN);
		titleCellStyle.setBorderLeft(BorderStyle.THIN);
		titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		Font font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);

		CellStyle contentCellStyle = workbook.createCellStyle();
		contentCellStyle.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
		contentCellStyle.setAlignment(HorizontalAlignment.CENTER);
		contentCellStyle.setBorderBottom(BorderStyle.THIN);
		contentCellStyle.setBorderTop(BorderStyle.THIN);
		contentCellStyle.setBorderRight(BorderStyle.THIN);
		contentCellStyle.setBorderLeft(BorderStyle.THIN);
		contentCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		

		// 엑셀 상단제목세팅
		List<Object> titleList = new ArrayList<Object>();
		titleList = (List<Object>) map.get("Title");
		
		if (sheetStyle.equals("normal")) {
			sheet.createRow(titleRow);
			for (int i = 0; i < titleList.size(); i++) {
				sheet.getRow(titleRow).createCell(i);
				cell = sheet.getRow(titleRow).getCell(i);
				cell.setCellValue(titleList.get(i).toString());
				cell.setCellStyle(titleCellStyle);
			}
		}
		

		// 5. 조회된 데이터 세팅
		HashMap getMap = new HashMap();
		List<Object> listExcelDownList = (List<Object>) map.get("ListExcelDown");
		// 데이터 세팅
		if (sheetStyle.equals("normal")) {
			for (int i = 0; i < listExcelDownList.size(); i++) {
				getMap = (HashMap) listExcelDownList.get(i);
				sheet.createRow(contentRow+i);
				for (int j = 0; j < titleList.size(); j++) {
					sheet.getRow(contentRow+i).createCell(j);
					cell = sheet.getRow(contentRow + i).getCell(j);
					//적재현황 조회 같은 케이스는... 다르게 처리
					int maxLength = 32767;
					String text = (String) getMap.get(titleList.get(j).toString());
					if(text != null && text.length() > maxLength){
						text = text.substring(0,maxLength);
					}
					cell.setCellValue(text);
					
					contentCellStyle.setWrapText(true);
				    cell.setCellStyle(contentCellStyle);

				    //increase row height to accomodate two lines of text
				    sheet.getRow(contentRow+i).setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

				    //adjust column width to fit the content
				    sheet.autoSizeColumn((short)2);
				}
			}
			// these cords have been separated from second loop(for(int j = 0; j < titleList.size(); j++)) to enhance the download speed!
			for (int k = 0; k < titleList.size(); k++) {
				try{
					sheet.autoSizeColumn((short) k);// cell width auto resize
					sheet.setColumnWidth(k, (sheet.getColumnWidth(k)) + 512); // 자동조정 후 잘리는 부분조정
				}catch(Exception e){//12.23 hssf가 아닌 ss를 쓰도록 했는데 hssf를 계속 씀.. 버그인 것 같음.. 로깅 처리
					logger.error("[error] "+e.getMessage()+", sheetName: "+sheet.getSheetName()+", title: "+ titleList.get(k));
				}
			}

		}
		
		// 6. 상단 제목(날짜 정보 여부에 따라 등) 추가 셋팅
	}
}