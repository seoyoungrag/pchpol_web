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
import com.dwebs.pchpol.facility.vo.TroopsPlacementWithFacilities;
import com.dwebs.pchpol.model.Code;
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
			//바로 부대별근무지 테이블을 읽어오지 않고 부대별 테이블을 읽은 후에 부대별 근무지를 조회하는 이유는, 아직 배치되지 않은 부대 정보들도 가지고 오기 위함이다.
			for(Code troops : troopsList){ //부대별로 근무지를 셋팅한다. 
				TroopsPlacementWithFacilities tpwf = new TroopsPlacementWithFacilities();
				list.add(tpwf);
				tpwf.setTroops(troops);
				
				TroopsPlacement troopsWorkplaceSearch = new TroopsPlacement();
				troopsWorkplaceSearch.setCode1(troops);
				//부대별 배치정보를 가져온다.
				TroopsPlacement troopsWorkplace = troopsService.getTroopsWorkplace(troopsWorkplaceSearch); 
				tpwf.setWorkspace(troopsWorkplace.getCode2());

				TroopsFacilityPlacement troopsFacilitySearch = new TroopsFacilityPlacement();
				troopsFacilitySearch.setCode(troops);
				//부대별 시설정보를 가져온다.
				List<TroopsFacilityPlacement> troopsFacilityList = troopsService.getTroopsFacilityListByTroops(troopsFacilitySearch);
				for(TroopsFacilityPlacement f : troopsFacilityList){
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
	

	@RequestMapping(value = "/facility/reg.do")
	public ModelAndView reg() {
		return new ModelAndView("facility/facilityView");
	}
}
