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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.model.Code;
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
	@RequestMapping(value = "/unit/list/{type}", method = RequestMethod.GET)
	public ResponseEntity<?> getUnitList(
			@PathVariable(value="type") String troopsType, 
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute Code code,
			HttpServletRequest req){
		Response res = new Response();
		List<Unit> list = new ArrayList<Unit>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = unitService.getListByTroopsTypeAndCode(pagingVO,troopsType,code); //조회 결과
			totCnt = unitService.getTotCntByTroopsTypeAndCode(pagingVO,troopsType,code); //전체 페이지의 게시물 수
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
	 *   @param type
	 *   @param adminNo
	 *   @return
	 */
	@RequestMapping(value = "/unit/view.do")
	public ModelAndView unitView(
			@RequestParam(value="type", required=true, defaultValue="view") String type,
			@RequestParam(value="unitType", required=true, defaultValue="stand") String unitType,
			@RequestParam(value="unitNo", required=false) String unitNo) {
		ModelAndView mav = new ModelAndView("unit/unitView_"+unitType);
		mav.addObject("type",type);
		mav.addObject("unitType", unitType);
		mav.addObject("unitNo",unitNo);
		return mav;
	}
}
