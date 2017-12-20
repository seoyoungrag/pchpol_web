/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : ExcelController.java
 * 2. Package : com.dwebs.pchpol.excel.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 29. 오전 1:49:52
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.excel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dwebs.pchpol.code.service.CodeService;
import com.dwebs.pchpol.common.util.RequestUtil;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.troops.service.TroopsService;
import com.dwebs.pchpol.unit.service.UnitService;
import com.dwebs.pchpol.workplace.controller.WorktimeConvert;
import com.dwebs.pchpol.workplace.service.WorkplaceService;
import com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : ExcelController.java
 * 3. Package  : com.dwebs.pchpol.excel.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 29. 오전 1:49:52
 * </PRE>
 */
@RestController
public class ExcelController {

	@Autowired
	@Qualifier("unitService")
	private UnitService unitService;

	@Autowired
	@Qualifier("codeService")
	CodeService codeService;
	
	@Autowired
	@Qualifier("troopsService")
	private TroopsService troopsService;

	@Autowired
	@Qualifier("workplaceService")
	private WorkplaceService workplaceService;
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping("/excel/unit/list.do")
	public ModelAndView excepUnit(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute Unit unit,
			@RequestParam(value="sheetName") String sheetName
			) {
		HashMap<String, Object> paraMap = new RequestUtil().getParameterMap(request);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 1. Excel sheet name
			String sheetStyle = "normal";

			// 2. Excel sheet title(목록)
			String columnStr = (String) paraMap.get("columns");
			columnStr = columnStr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
			List<String> title = Arrays.asList(columnStr.split("\\s*,\\s*"));

			// 3. Excel data 조회
			List<Unit> list = new ArrayList<Unit>();
			PagingVO pagingVO = new PagingVO();
			pagingVO.setPaging(request);
			pagingVO.setStartNum(0);
			pagingVO.setRows(10000);
			try {
				list = unitService.getListByUnit(pagingVO,unit); //조회 결과
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 4. ExcelDownView로 데이터를 넘겨주기 위한 작업
			Map map;
			List excelList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				// title의 목록과 동일하게 구성
				map = new HashMap();
				Unit value = list.get(i);
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				if(unit.getTroopsType().equals("stand")){
					map.put(title.get(0), String.valueOf(i));
					if(value.getCode1()!=null){
						map.put(title.get(1), value.getCode1().getCode1depth());
						map.put(title.get(2), value.getCode1().getCode2depth());
						map.put(title.get(3), value.getCode1().getCode3depth());
						map.put(title.get(4), value.getCode1().getCode4depth());
					}
					if(value.getCode2()!=null){
						map.put(title.get(5), value.getCode2().getCode1depth());
					}
					map.put(title.get(6), value.getUnitName());
					/* 직위 삭제
					if(value.getCode4()!=null){
						map.put(title.get(7), value.getCode4().getCode1depth());
					}
					map.put(title.get(8), value.getUnitPolId());
					map.put(title.get(9), value.getUnitBirth());
					*/
					map.put(title.get(7), value.getUnitPolId());
					map.put(title.get(8), value.getUnitBirth());
				}else if(unit.getTroopsType().equals("indiv")){
					map.put(title.get(0), String.valueOf(i));
					if(value.getCode1()!=null){
						map.put(title.get(1), value.getCode1().getCode1depth());
						map.put(title.get(2), value.getCode1().getCode2depth());
					}
					if(value.getCode3()!=null){
						map.put(title.get(3), value.getCode3().getCode1depth());
					}
					map.put(title.get(4), value.getUnitName());
					/* 직위삭제
					if(value.getCode4()!=null){
						map.put(title.get(5), value.getCode4().getCode1depth());
					}
					map.put(title.get(6), value.getUnitPolId());
					map.put(title.get(7), value.getUnitBirth());
					if(value.getCode5()!=null){
						map.put(title.get(8), value.getCode5().getCode1depth());
						map.put(title.get(9), value.getCode5().getCode2depth());
						map.put(title.get(10), value.getCode5().getCode3depth());
					}
					map.put(title.get(11), transFormat.format(value.getUnitMobilStartDt()));
					map.put(title.get(12), transFormat.format(value.getUnitMobilEndDt()));
					*/
					map.put(title.get(5), value.getUnitPolId());
					map.put(title.get(6), value.getUnitBirth());
					if(value.getCode5()!=null){
						map.put(title.get(7), value.getCode5().getCode1depth());
						map.put(title.get(8), value.getCode5().getCode2depth());
						map.put(title.get(9), value.getCode5().getCode3depth());
					}
					map.put(title.get(10), transFormat.format(value.getUnitMobilStartDt()));
					map.put(title.get(11), transFormat.format(value.getUnitMobilEndDt()));
				}else if(unit.getTroopsType().equals("female")){
					map.put(title.get(0), String.valueOf(i));
					if(value.getCode1()!=null){
						map.put(title.get(1), value.getCode1().getCode1depth());
						map.put(title.get(2), value.getCode1().getCode2depth());
					}
					if(value.getCode3()!=null){
						map.put(title.get(3), value.getCode3().getCode1depth());
					}
					map.put(title.get(4), value.getUnitName());
					if(value.getCode4()!=null){
						map.put(title.get(5), value.getCode4().getCode1depth());
					}
					map.put(title.get(6), value.getUnitPolId());
					map.put(title.get(7), value.getUnitBirth());
					if(value.getCode5()!=null){
						map.put(title.get(8), value.getCode5().getCode1depth());
						map.put(title.get(9), value.getCode5().getCode2depth());
						map.put(title.get(10), value.getCode5().getCode3depth());
					}
					map.put(title.get(11), transFormat.format(value.getUnitMobilStartDt()));
					map.put(title.get(12), transFormat.format(value.getUnitMobilEndDt()));
				}
				
				excelList.add(map);
			}

			// 5. HashMap에 담아 ModelAndView 리턴할 때 모두 함께 담아서 보냄
			resultMap.put("sheetNm", sheetName);
			resultMap.put("sheetSt", sheetStyle);
			resultMap.put("Title", title);
			resultMap.put("ListExcelDown", excelList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("ExcelDownView", "ListExcelDownMap", resultMap);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@RequestMapping("/excel/workplace/troopsWorkplacePlacementDetailList.do")
	public ModelAndView excepTroopsWorkplacePlacementDetailList(
			HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute WorkplaceWithTroops workplaceWithTroops,
			@RequestParam(value="sheetName") String sheetName
			) {
		HashMap<String, Object> paraMap = new RequestUtil().getParameterMap(request);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 1. Excel sheet name
			String sheetStyle = "normal";
			// 2. Excel sheet title(목록)
			String columnStr = (String) paraMap.get("columns");
			columnStr = columnStr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\"", "");
			List<String> title = Arrays.asList(columnStr.split("\\s*,\\s*"));

			// 3. Excel data 조회
			List<WorkplaceWithTroops> list = new ArrayList<WorkplaceWithTroops>();
			PagingVO pagingVO = new PagingVO();
			pagingVO.setPaging(request);
			pagingVO.setStartNum(0);
			pagingVO.setRows(10000);
			pagingVO.setSearchType("codeCategory");
			pagingVO.setSearchWord("workplace");
			try {
				if(workplaceWithTroops.getMobilDate()==null){
					try {
						workplaceWithTroops.setMobilDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20180101" ));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				sheetName+="("+transFormat.format(workplaceWithTroops.getMobilDate())+")";
				//1. 근무지 코드값으로 페이징한다.
				List<Code> WorkplaceList = codeService.getCodeListByCode(pagingVO, workplaceWithTroops.getWorkplace());
				for(Code code : WorkplaceList){
					//2. 근무지 코드값별로 세부소속값을 가져온다.(상설부대배치테이블-TroopsPlacement, 근무정보테이블-WorkplacePlacement)
					WorkplaceWithTroops wwt = new WorkplaceWithTroops();
					list.add(wwt);
					//근무지 셋팅
					wwt.setWorkplace(code);
					//부대 리스트 초기화
					wwt.setTroopsDetail(new ArrayList<Code>());
					//부대별 근무시간 초기화
					wwt.setWorkTimes(new ArrayList<String>());
					//근무일자 셋팅
					wwt.setMobilDate(workplaceWithTroops.getMobilDate());
					//근무지의 부대 리스트 셋팅
					List<WorkplacePlacement> wps = troopsService.getWorkplaceTroops(wwt);
					Set<WorkplacePlacementDetail> detailSet = new HashSet<WorkplacePlacementDetail>();
					for(WorkplacePlacement wp : wps){
						wwt.getTroopsDetail().add(wp.getCode1());
						detailSet.addAll(new HashSet<>(wp.getWorkplacePlacementDetail()));
					}					
					List<WorkplacePlacementDetail> detailList = new ArrayList<WorkplacePlacementDetail>(detailSet);
					
					WorkplacePlacementDetailAscending ascending = new WorkplacePlacementDetailAscending(); 
					Collections.sort(detailList, ascending);
					
					StringBuffer str = new StringBuffer();
					
					if(detailList.size()==1){
						String startEnd = WorktimeConvert.startAndEnd(detailList.get(0));
						str.append(startEnd);
					}else if(detailList.size()>1){
						String start = WorktimeConvert.start(detailList.get(0));
						String end = WorktimeConvert.end(detailList.get(detailList.size()-1));
						str.append(start+"~"+end);
					}else{
						
					}
					wwt.getWorkTimes().add(str.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 4. ExcelDownView로 데이터를 넘겨주기 위한 작업
			Map map;
			List excelList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				// title의 목록과 동일하게 구성
				map = new HashMap();
				WorkplaceWithTroops value = list.get(i);
				map.put(title.get(0), String.valueOf(i));
				if(value.getWorkplace()!=null){
					map.put(title.get(1), value.getWorkplace().getCode1depth());
					map.put(title.get(2), value.getWorkplace().getCode2depth());
					map.put(title.get(3), value.getWorkplace().getCode3depth());
					map.put(title.get(4), value.getWorkplace().getCode4depth());
				}
				if(value.getWorkTimes()!=null&&!value.getWorkTimes().equals("")){
					map.put(title.get(5), value.getWorkTimes().get(0));
				}
				if(value.getTroopsDetail()!=null&&value.getTroopsDetail().size()>0){
					StringBuffer str = new StringBuffer();
					str.append(value.getTroopsDetail().get(0).getCode1depth()+" "+value.getTroopsDetail().get(0).getCode2depth()+" "+value.getTroopsDetail().get(0).getCode3depth());
					for(Code troops : value.getTroopsDetail()){
						str.append(troops.getCode4depth()!=null ? ""+troops.getCode4depth()+",": "");
					}
					map.put(title.get(6), str.toString().substring(0,str.length()-1));
				}
				
				
				excelList.add(map);
			}

			// 5. HashMap에 담아 ModelAndView 리턴할 때 모두 함께 담아서 보냄
			resultMap.put("sheetNm", sheetName);
			resultMap.put("sheetSt", sheetStyle);
			resultMap.put("Title", title);
			resultMap.put("ListExcelDown", excelList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("ExcelDownView", "ListExcelDownMap", resultMap);
	}
}

class WorkplacePlacementDetailAscending implements Comparator<WorkplacePlacementDetail> {
	 
    @Override
    public int compare(WorkplacePlacementDetail o2, WorkplacePlacementDetail o1) {
		return Integer.valueOf(o2.getWorkplacePlacementDetailWorkTime().substring(4, o2.getWorkplacePlacementDetailWorkTime().length())).compareTo(Integer.valueOf(o1.getWorkplacePlacementDetailWorkTime().substring(4, o1.getWorkplacePlacementDetailWorkTime().length())));
    }
 
}
