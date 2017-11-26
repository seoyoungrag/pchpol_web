/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : UnitController.java
 * 2. Package : com.dwebs.pchpol.unit.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 12. 오전 11:31:07
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 12. :            : 신규 개발.
 */
package com.dwebs.pchpol.unit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.util.ExcelService;
import com.dwebs.pchpol.common.util.FileHandler;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.model.Attach;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.unit.service.UnitService;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : UnitController.java
 * 3. Package  : com.dwebs.pchpol.unit.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 12. 오전 11:31:07
 * </PRE>
 */
@RestController
public class UnitController extends BaseController {

	@Autowired
	@Qualifier("unitService")
	private UnitService unitService;
	@Autowired
	@Qualifier("excelService")
	private ExcelService excelService;
	
	@RequestMapping(value = "/unit/list.do")
	public ModelAndView unitListPage(@RequestParam(value="type", required=false, defaultValue="stand") String type) {
		return new ModelAndView("unit/unitList_"+type);
	}

	/**
	 * <PRE>
	 * 1. MethodName : getAdminList
	 * 2. ClassName  : AdminController
	 * 3. Comment   : '상단메뉴-관리자 정보-관리자 리스트'페이지에서 jqgrid의 데이터를 채우기 위해 ajax호출된다. 검색조건과 페이징정보는 PagingVO에 담아서 사용한다.
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 10. 오전 11:40:38
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param listType
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/unit/list", method = RequestMethod.GET)
	public ResponseEntity<?> getUnitList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute Unit unit,
			HttpServletRequest req){
		Response res = new Response();
		List<Unit> list = new ArrayList<Unit>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = unitService.getListByUnit(pagingVO,unit); //조회 결과
			totCnt = unitService.getTotCntByUnit(pagingVO,unit); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<Unit> jqGridData = new JQGridVO<Unit>();
			jqGridData.setPage(pagingVO.getPage()); //조회 결과
			jqGridData.setTotal(String.valueOf(pagingVO.getTotal())); //마지막 페이지 번호
			jqGridData.setRecords(String.valueOf(totCnt)); //전체 페이지의 게시물 수
			jqGridData.setRows(list);
			res.setData(jqGridData);
		}else{
			res.setData(list);
		}
		//return할때 join되는 entity가 섞여 있으면 @ManyToOne(fetch = FetchType.EAGER)를 설정하거나 @jsonpropertyignore로 설정해야 한다.
		return ResponseEntity.ok(res);
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : unitView
	 * 2. ClassName  : UnitController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 13. 오전 10:24:21
	 * </PRE>
	 *   @return ModelAndView
	 *   @param viewType
	 *   @param adminNo
	 *   @return
	 */
	@RequestMapping(value = "/unit/view.do")
	public ModelAndView unitView(
			@RequestParam(value="viewType", required=true, defaultValue="view") String viewType,
			@RequestParam(value="unitType", required=true, defaultValue="stand") String unitType,
			@RequestParam(value="unitNo", required=false) String unitNo) {
		ModelAndView mav = new ModelAndView("unit/unitView_"+unitType);
		mav.addObject("viewType",viewType);
		mav.addObject("unitType", unitType);
		mav.addObject("unitNo",unitNo);
		return mav;
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdateUnit
	 * 2. ClassName  : UnitController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 15. 오후 6:29:47
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param unit
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/unit", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateUnit(@ModelAttribute Unit unit) throws Exception {
		Response res = new Response();
		unitService.insertOrUpdate(unit);
		res.setData(unit);
		return ResponseEntity.ok(res);
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : getUnitById
	 * 2. ClassName  : UnitController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 15. 오후 8:41:46
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param unitNo
	 *   @return
	 */
	@RequestMapping(value = "/unit/{unitNo}", method = RequestMethod.GET)
	public ResponseEntity<?> getUnitById(@PathVariable("unitNo") String unitNo) {
		Response res = new Response();
		Unit unit = unitService.getById(unitNo); 
		res.setData(unit);
		return ResponseEntity.ok(res);
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : unitSub
	 * 2. ClassName  : UnitController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 16. 오후 2:33:06
	 * </PRE>
	 *   @return ModelAndView
	 *   @param viewType
	 *   @return
	 */
	@RequestMapping(value = "/unit/sub.do")
	public ModelAndView unitSub(
			@RequestParam(value="name", required=true, defaultValue="workplace") String name,
			@RequestParam(value="depth", required=true, defaultValue="2") String depth
			){
		ModelAndView mav = new ModelAndView("unit/unitSub_"+name);
		mav.addObject("depth",depth);
		return mav;
	}

	@RequestMapping(value = "/unit/delete", method = RequestMethod.POST)
	public ResponseEntity<?> getUnitById(@RequestBody List<Integer> ids) {
		Response res = new Response();
		unitService.deleteByIds(ids); 
		res.setData(ids);
		return ResponseEntity.ok(res);
	}
	

	@RequestMapping(value = "/unit/excelupload.do", method = RequestMethod.GET)
	public ModelAndView exceluploadView() {
		ModelAndView mav = new ModelAndView("unit/excelupload");
		return mav;
	}

	@RequestMapping(value = "/unit/excelupload", method = RequestMethod.POST)
	public ResponseEntity<?> excelupload(
			@RequestParam(value="fileList", required=false) String fileListArr,
			@RequestParam(value="troosType", required=false) String troosType
			) {
		Response res = new Response();
		List<Attach> attaches = null;
		try {
			attaches = setFileList(fileListArr);
			JSONObject result =  excelService.readExcelFile(FileHandler.FILE_STORE_PATH+ File.separator +attaches.get(0).getAttachServerName(), troosType);
			String a = result.toString();
			res.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(attaches!=null){
			
		}
		return ResponseEntity.ok(res);
	}

	private List<Attach> setFileList(String fileListArr) throws Exception {

		String saveLocation = FileHandler.FILE_STORE_PATH;

		List<Attach> attaches = new ArrayList<Attach>();
		
		if(!fileListArr.equals("")){
			String[] fileList = fileListArr.split("\\*");
			if(fileList.length != 0 && fileListArr.contains("*")){
				File directory = new File(saveLocation);
				
	 	        if(directory.exists() == false){
	 	        	directory.mkdirs();
	 	        }
				
				for(int i = 0; i <fileList.length; i++){
					Attach attach = new Attach();
					String fileStrNm = UUID.randomUUID().toString().replaceAll("\\-", "");
					 
					FileInputStream inputStream = new FileInputStream(saveLocation + File.separator + fileList[i].replaceAll("&amp;", "&"));  
					Long filesize = inputStream.getChannel().size();
					FileOutputStream outputStream = new FileOutputStream(saveLocation + File.separator + fileStrNm);
					  
					FileChannel fcin =  inputStream.getChannel();
					FileChannel fcout = outputStream.getChannel();
					  
					long size = fcin.size();
					fcin.transferTo(0, size, fcout);
					  
					fcout.close();
					fcin.close();
					  
					outputStream.close();
					inputStream.close();
					
					attach.setAttachOriName(fileList[i]);
					attach.setAttachServerName(fileStrNm);
					//fileList[i].substring(fileList[i].lastIndexOf("."))
					attach.setAttachFileSize(String.valueOf(filesize.intValue()));
					attaches.add(attach);
				}
			}
		}
		return attaches;
	}
}
