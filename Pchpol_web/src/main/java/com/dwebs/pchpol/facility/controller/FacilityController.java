/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : FacilityController.java
 * 2. Package : com.dwebs.pchpol.facility
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오전 11:56:43
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.facility.controller;

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

import com.dwebs.pchpol.code.service.CodeService;
import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.facility.service.FacilityService;
import com.dwebs.pchpol.facility.vo.TroopsPlacementWithFacilities;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Facility;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.troops.service.TroopsService;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : FacilityController.java
 * 3. Package  : com.dwebs.pchpol.facility
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오전 11:56:43
 * </PRE>
 */
@RestController
public class FacilityController extends BaseController {

	@Autowired
	@Qualifier("codeService")
	CodeService codeService;
	
	@Autowired
	@Qualifier("troopsService")
	private TroopsService troopsService;

	@Autowired
	@Qualifier("facilityService")
	private FacilityService facilityService;
	
	/**
	 * <PRE>
	 * 1. MethodName : troopsFacilityPlacementListPage
	 * 2. ClassName  : FacilityController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오전 11:58:29
	 * </PRE>
	 *   @return ModelAndView
	 *   @return
	 */
	@RequestMapping(value = "/facility/troopsFacilityPlacementList.do")
	public ModelAndView troopsFacilityPlacementListPage() {
		return new ModelAndView("facility/troopsFacilityPlacementList");
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsFacilityPlacementList
	 * 2. ClassName  : FacilityController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 2:31:52
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param listType
	 *   @param troopsPlacementWithFacilities
	 *   @param req
	 *   @return
	 */
	@RequestMapping(value = "/facility/troopsFacilityPlacementList", method = RequestMethod.GET)
	public ResponseEntity<?> getTroopsFacilityPlacementList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute TroopsPlacementWithFacilities troopsPlacementWithFacilities,
			HttpServletRequest req){
		Response res = new Response();
		Code troopsSearch = troopsPlacementWithFacilities.getTroops();
		List<TroopsPlacementWithFacilities> list = new ArrayList<TroopsPlacementWithFacilities>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		pagingVO.setSearchType("codeCategory");
		pagingVO.setSearchWord("troops");
		try {
			List<Code> troopsList = codeService.getCodeListByCode(pagingVO, troopsSearch); //페이징 및 검색 기준은 부대기준이다.
			//부대정보에서 관계되어있는 근무지배치정보를 가져올 수 있다.
			//바로 부대별근무지 테이블을 읽어오지 않고 부대별 테이블을 읽은 후에 부대별 근무지를 조회하는 이유는, 아직 배치되지 않은 부대 정보들도 가지고 오기 위함이다.
			for(Code troops : troopsList){ 
				TroopsPlacementWithFacilities tpwf = new TroopsPlacementWithFacilities();
				list.add(tpwf);
				//부대별로 근무지를 셋팅한다.
				tpwf.setTroops(troops);
				TroopsPlacement tps = new TroopsPlacement();
				tps.setCode1(troops);
				List<TroopsPlacement> tp = troopsService.getTroopsWorkplace(tps);
				if(tp.size()>0){
					tpwf.setWorkplace(tp.get(0).getCode2());
				}
				//부대별로 시설정보를 셋팅한다.
				List<TroopsFacilityPlacement> troopsFacilityList = troops.getTroopsFacilityPlacements();
				for(TroopsFacilityPlacement f : troopsFacilityList){
					tpwf.setFacilityMobilStartDt(f.getTroopsFacilityMobilStartDt());
					tpwf.setFacilityMobilEndDt(f.getTroopsFacilityMobilEndDt());
					//시설별로 숙영, 급식 시설을 분류한다.
					if(f.getFacility()!=null && f.getFacility().getFacilityType().equals("food")){
						tpwf.getFoodFacility().add(f.getFacility());
					}else if(f.getFacility()!=null && f.getFacility().getFacilityType().equals("bed")){
						tpwf.getBedFacility().add(f.getFacility());
					}
				}
			}
			totCnt = codeService.getTotCntByCode(pagingVO, troopsSearch); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<TroopsPlacementWithFacilities> jqGridData = new JQGridVO<TroopsPlacementWithFacilities>();
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
	 * 1. MethodName : reg
	 * 2. ClassName  : FacilityController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 11:42:43
	 * </PRE>
	 *   @return ModelAndView
	 *   @param facType
	 *   @return
	 */
	@RequestMapping(value = "/facility/reg.do")
	public ModelAndView reg(
			@RequestParam(value="facType") String facType
			) {
		ModelAndView mav = new ModelAndView("facility/facilityView_"+facType);
		return mav;
	}
	
	@RequestMapping(value = "/facility/view.do")
	public ModelAndView reg(
			@RequestParam(value="facType") String facType,
			@RequestParam(value="facilityNo") String facilityNo) {
		ModelAndView mav = new ModelAndView("facility/facilityPop_"+facType);
		mav.addObject("facilityNo",facilityNo);
		return mav;
	}
	@RequestMapping(value = "/facility/{facilityNo}")
	public ResponseEntity<?> getFacility(
			@PathVariable("facilityNo") int facilityNo
			) {
		Response res = new Response();
		Facility fac = facilityService.getById(facilityNo); 
		res.setData(fac);
		return ResponseEntity.ok(res);
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : troopsFacilityPlacementViewPage
	 * 2. ClassName  : FacilityController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 11:42:41
	 * </PRE>
	 *   @return ModelAndView
	 *   @param troopsPlacementWithFacilities
	 *   @return
	 */
	@RequestMapping(value = "/facility/troopsFacilityPlacementView.do")
	public ModelAndView troopsFacilityPlacementViewPage(
			@ModelAttribute TroopsPlacementWithFacilities troopsPlacementWithFacilities
			) {
		ModelAndView mav = new ModelAndView("facility/troopsFacilityPlacementView");
		mav.addObject("tfp",troopsPlacementWithFacilities);
		return mav;
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdateFacility
	 * 2. ClassName  : FacilityController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 11:42:40
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param facility
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/facility", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateFacility(@ModelAttribute Facility facility) throws Exception {
		Response res = new Response();
		//주소와 상호명이 중복되면 수정함.
		facilityService.insertFacility(facility);
		res.setData(facility);
		return ResponseEntity.ok(res);
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : findFacPage
	 * 2. ClassName  : FacilityController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 27. 오후 5:17:36
	 * </PRE>
	 *   @return ModelAndView
	 *   @param facType
	 *   @return
	 */
	@RequestMapping(value = "/facility/findFac.do")
	public ModelAndView findFacPage(
			@RequestParam(value="facType") String facType,
			@RequestParam(value="curIdx") String curIdx
			) {
		ModelAndView mav = new ModelAndView("facility/findFac");
		mav.addObject("facType",facType);
		mav.addObject("curIdx",curIdx);
		return mav;
	}
	

	@RequestMapping(value = "/facility", method = RequestMethod.GET)
	public ResponseEntity<?> getFacilityList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute Facility facility,
			HttpServletRequest req){
		Response res = new Response();
		List<Facility> list = new ArrayList<Facility>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = facilityService.getFacility(pagingVO, facility);
			totCnt = facilityService.getTotCntFacility(pagingVO, facility); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<Facility> jqGridData = new JQGridVO<Facility>();
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
	

	@RequestMapping(value = "/facility/troopsFacilityPlacement", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateTroopsFacilityPlacement(
			@ModelAttribute TroopsPlacementWithFacilities troopsFacilities
			) throws Exception {
		Response res = new Response();
		if(troopsFacilities.getTroops().getCodeNo()==0){
			res = new Response(false, "troopsNo is empty");
			return ResponseEntity.ok(res);
		}else{
			//1. 같은 값을 가진 row를 지우고 입력
			troopsService.insertTroopsFacilityPlacement(troopsFacilities);
			res.setData(troopsFacilities);
		}
		return ResponseEntity.ok(res);
	}
}
