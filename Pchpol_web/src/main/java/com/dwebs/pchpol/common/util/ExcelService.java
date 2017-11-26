package com.dwebs.pchpol.common.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dwebs.pchpol.code.service.CodeService;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.unit.service.UnitService;

@Component("excelService")
public class ExcelService  {
	@Autowired
	@Qualifier("codeService")
	CodeService codeService;
	static Logger logger = Logger.getLogger(ExcelService.class.getName());

	@Autowired
	@Qualifier("unitService")
	private UnitService unitService;
	
	@SuppressWarnings({ "resource", "unchecked" })
	public JSONObject readExcelFile(String filePath, String type) {
		JSONObject result = new JSONObject();
		int insertedRows = 0;
		int allRows = 0;
		int errorsRows = 0;
		JSONArray errorlist = new JSONArray();
		try {
			OPCPackage opcPackage = OPCPackage.open(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
			opcPackage.close();
			int rowindex=0;
			XSSFSheet sheet=workbook.getSheetAt(0);
			
			//행의 수
			int rows=sheet.getPhysicalNumberOfRows();
			// 엑셀 row수를 10000개로 제한하자. 메모리가 어떻게 될지 알 수가 없으니.	
			result.put("2th try-catch error", errorlist);
        	StringBuffer troopsE = new StringBuffer();
        	StringBuffer rankE = new StringBuffer();
        	StringBuffer positionE = new StringBuffer();
        	StringBuffer etcE = new StringBuffer();
			for(rowindex=3;rowindex<=rows && rowindex <= 10000; rowindex++){
				allRows++;
		        	Row row=sheet.getRow(rowindex);
			        try {
					    if(row !=null){
					    	if(type.equals("stand")||type.equals("duty")||type.equals("female")||type.equals("indiv")){
							    //연번	지방청	부대명	세부소속	계급	성명	직위	폴넷아이디	생년월일
							    Unit unit = new Unit();
							    unit.setTroopsType("stand");
							    Code troops = new Code();
							    troops.setCodeCategory("troops");
							    troops.setCode1depth((row.getCell(1).getStringCellValue()).trim());
							    String code2depth = "";
							    if(type.equals("stand")){
							    	code2depth = "경찰관 기동대";
							    }else if(type.equals("female")){
							    	code2depth = "여경";
							    }else if(type.equals("duty")){
							    	code2depth = "의경 중대";
							    }
							    troops.setCode2depth(code2depth);
							    troops.setCode3depth((row.getCell(2).getStringCellValue()).trim());
							    troops.setCode4depth((row.getCell(3).getStringCellValue()).trim());
							    Code retCd = codeService.getCode(troops);
							    if(retCd.getCodeNo()==0){
							    	//codeService.insertCode(troops);
							    	throw new Exception("troops");
							    }else{
								    unit.setCode1(retCd);
							    }
							    
							    Code rank = new Code();
							    rank.setCodeCategory("rank");
							    rank.setCode1depth((row.getCell(4).getStringCellValue()).trim());
							    retCd = codeService.getCode(rank);
						    	if(retCd.getCodeNo()==0){
						    		//codeService.insertCode(rank);
							    	throw new Exception("rank");
							    }else{
								    unit.setCode2(retCd);
							    }
							    
							    unit.setUnitName((row.getCell(5).getStringCellValue()).trim());
							    
							    Code position = new Code();
							    position.setCodeCategory("position");
							    position.setCode1depth((row.getCell(6).getStringCellValue()).trim());
							    retCd = codeService.getCode(position);
							    if(retCd.getCodeNo()==0){
						    		//codeService.insertCode(position);
							    	throw new Exception("position");
							    }else{
								    unit.setCode4(retCd);
							    }
							    
							    unit.setUnitPolId((row.getCell(7).getStringCellValue()).trim());
							    unit.setUnitBirth((row.getCell(8).getStringCellValue()).trim());
							    //unitService.insertOrUpdate(unit);
					    	}
					    }
					    insertedRows++;
				} catch (Exception e) {
					e.getMessage();
					errorsRows++;
					String msg = String.valueOf((int)(row.getCell(0).getNumericCellValue()))+",";
					if(e.getMessage().equals("troops")){
						troopsE.append(msg);
					}else if(e.getMessage().equals("rankE")){
						rankE.append(msg);
					}else if(e.getMessage().equals("positionE")){
						positionE.append(msg);
					}else{
						etcE.append("'"+e.getMessage()+":"+msg+"'");
					}
				}
			}
			errorlist.add("troops Error: " + troopsE.toString());
			errorlist.add("rank Error: " + rankE.toString());
			errorlist.add("position Error: " + positionE.toString());
			errorlist.add("etc Error: " + etcE.toString());
		} catch (Exception e) {
			e.getMessage();
			errorlist.add("1th try-catch error: "+e.getMessage());
		}
		result.put("insertedRows", insertedRows +"/"+ allRows);
		result.put("errorsRows", errorsRows);
		return result;
    }
	
	
}