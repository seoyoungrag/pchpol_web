/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : WorkplaceController.java
 * 2. Package : com.dwebs.pchpol.workplace.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 17. 오후 2:41:54
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 17. :            : 신규 개발.
 */
package com.dwebs.pchpol.workplace.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.TroopsPlacement;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.troops.service.TroopsService;
import com.dwebs.pchpol.unit.service.UnitService;
import com.dwebs.pchpol.workplace.service.WorkplaceService;
import com.dwebs.pchpol.workplace.vo.WorkplaceDetail;
import com.dwebs.pchpol.workplace.vo.WorkplaceWithTroops;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : WorkplaceController.java
 * 3. Package  : com.dwebs.pchpol.workplace.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 17. 오후 2:41:54
 * </PRE>
 */
@RestController
public class WorkplaceController extends BaseController {

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
	/**
	 * <PRE>
	 * 1. MethodName : workplaceListPage
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 3:01:41
	 * </PRE>
	 *   @return ModelAndView
	 *   @return
	 */
	@RequestMapping(value = "/workplace/list.do")
	public ModelAndView workplaceListPage() {
		return new ModelAndView("workplace/workplaceList");
	}

	/**
	 * <PRE>
	 * 1. MethodName : troopsWorkplacePlacementListPage
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 3:01:46
	 * </PRE>
	 *   @return ModelAndView
	 *   @return
	 */
	@RequestMapping(value = "/workplace/troopsWorkplacePlacementList.do")
	public ModelAndView troopsWorkplacePlacementListPage() {
		return new ModelAndView("workplace/troopsWorkplacePlacementList");
	}

	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplacePlacementList
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 3:02:35
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param listType
	 *   @param troopsPlacementWithFacilities
	 *   @param req
	 *   @return
	 */
	@RequestMapping(value = "/workplace/troopsWorkplacePlacementList", method = RequestMethod.GET)
	public ResponseEntity<?> getTroopsWorkplacePlacementList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute WorkplaceWithTroops workplaceWithTroops,
			HttpServletRequest req){
		Response res = new Response();
		List<WorkplaceWithTroops> list = new ArrayList<WorkplaceWithTroops>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			//근무지별로 부대배치한 로우를 가져온다. 
			//부대를 배치할대는 세부소속(code4depth)기준으로 배치하지만, 리스트에서는 부대명(code1depth, code2depth, code3depth)까지만 그룹화해서 보여줘야 한다.
			//1. 부대명까지 그룹화해서 페이징된 리스트를 가지고 온다.
			list = troopsService.getTroopsWorkplaceGroupByTroopsCode3depth(pagingVO, workplaceWithTroops);
			for(WorkplaceWithTroops wwt : list){
				//2. 그룹화한 로우별로 세부소속값을 가져온다.(상설부대배치테이블-TroopsPlacement, 근무정보테이블-WorkplacePlacement)
				List<TroopsPlacement> tps = troopsService.getTroopsWorkplace(wwt);
				for(TroopsPlacement tp : tps){
					wwt.getTroopsDetail().add(tp.getCode1());
				}
			}
			totCnt = troopsService.getTotCntWorkplaceGroupByTroopsCode3depth(pagingVO, workplaceWithTroops); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<WorkplaceWithTroops> jqGridData = new JQGridVO<WorkplaceWithTroops>();
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
	 * 1. MethodName : troopsWorkplaceView
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 3:29:12
	 * </PRE>
	 *   @return ModelAndView
	 *   @param viewType
	 *   @param code1depth
	 *   @param code2depth
	 *   @return
	 */
	@RequestMapping(value = "/workplace/troopsWorkplacePlacementView.do")
	public ModelAndView troopsWorkplaceView(
			@RequestParam(value="viewType", required=true, defaultValue="view") String viewType,
			@RequestParam(value="code1depth", required=false) String code1depth,
			@RequestParam(value="code2depth", required=false) String code2depth
			){
		ModelAndView mav = new ModelAndView("workplace/troopsWorkplacePlacementView");
		mav.addObject("viewType",viewType);
		mav.addObject("code1depth",code1depth);
		mav.addObject("code2depth",code2depth);
		return mav;
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdateWorkplaceTroopsWorkplacePlacement
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 3:29:09
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param troopsPlacement
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/workplace/troopsWorkplacePlacement", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateWorkplaceTroopsWorkplacePlacement(@ModelAttribute TroopsPlacement troopsPlacement) throws Exception {
		Response res = new Response();
		//중복 로우를 삭제하고 입력함
		troopsService.insertTroopsWorkplacePlacement(troopsPlacement);
		res.setData(troopsPlacement);
		return ResponseEntity.ok(res);
	}

	//사용안하고 있음
	/**
	 * <PRE>
	 * 1. MethodName : getTroopsWorkplacePlacementById
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 19. 오후 3:28:56
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param troopsPlacementNo
	 *   @param workplace
	 *   @return
	 */
	@RequestMapping(value = "/workplace/troopsWorkplacePlacement/{troopsPlacementNo}", method = RequestMethod.GET)
	public ResponseEntity<?> getTroopsWorkplacePlacementById(
			@PathVariable("troopsPlacementNo") String troopsPlacementNo,
			@ModelAttribute Code workplace
			) {
		Response res = new Response();
		TroopsPlacement troopsPlacement = null;
		if(troopsPlacementNo.equals("false")){
			troopsPlacement = new TroopsPlacement();
			troopsPlacement.setCode1(new Code());
			troopsPlacement.setCode2(workplace);
		}else{
			troopsPlacement = troopsService.getById(troopsPlacementNo);
		}
		res.setData(troopsPlacement);
		return ResponseEntity.ok(res);
	}

	/**
	 * <PRE>
	 * 1. MethodName : troopsWorkplacePlacementListPage
	 * 2. ClassName  : WorkplaceController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 3:01:46
	 * </PRE>
	 *   @return ModelAndView
	 *   @return
	 */
	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetailList.do")
	public ModelAndView troopsWorkplacePlacementDetailListPage() {
		return new ModelAndView("workplace/troopsWorkplacePlacementDetailList");
	}
	

	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetailList", method = RequestMethod.GET)
	public ResponseEntity<?> troopsWorkplacePlacementDetailList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute WorkplaceWithTroops workplaceWithTroops,
			HttpServletRequest req){
		Response res = new Response();
		List<WorkplaceWithTroops> list = new ArrayList<WorkplaceWithTroops>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		pagingVO.setSearchType("codeCategory");
		pagingVO.setSearchWord("workplace");
		try {
			//1. 근무지 코드값으로 페이징한다.
			List<Code> WorkplaceList = codeService.getCodeListByCode(pagingVO, workplaceWithTroops.getWorkplace());
			totCnt = codeService.getTotCntByCode(pagingVO, workplaceWithTroops.getWorkplace()); //전체 페이지의 게시물 수
			for(Code code : WorkplaceList){
				//2. 근무지 코드값별로 세부소속값을 가져온다.(상설부대배치테이블-TroopsPlacement, 근무정보테이블-WorkplacePlacement)
				WorkplaceWithTroops wwt = new WorkplaceWithTroops();
				list.add(wwt);
				wwt.setWorkplace(code);
				wwt.setTroopsDetail(new ArrayList<Code>());
				List<WorkplacePlacement> wps = troopsService.getWorkplaceTroops(wwt);
				for(WorkplacePlacement wp : wps){
					wwt.getTroopsDetail().add(wp.getCode1());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<WorkplaceWithTroops> jqGridData = new JQGridVO<WorkplaceWithTroops>();
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
	
	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetailView.do")
	public ModelAndView troopsWorkplacePlacementDetailView(
			@RequestParam(value="viewType", required=true, defaultValue="view") String viewType,
			@ModelAttribute Code code,
			@RequestParam(value="mobilDate", required=false) String mobilDate,
			HttpServletRequest req
			){
		ModelAndView mav = new ModelAndView("workplace/troopsWorkplacePlacementDetailView");
		mav.addObject("viewType",viewType);
		mav.addObject("workplaceCode",code);
		mav.addObject("mobilDate",mobilDate);
		//상설부대배치한 부대 리스트를 가져온다. 검색조건은 근무지이다.(지역, 대회시설)
		WorkplaceWithTroops workplaceWithTroops = new WorkplaceWithTroops();
		workplaceWithTroops.setWorkplace(code);
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		pagingVO.setRows(10000);
		List<Code> troops = new ArrayList<Code>();
		try {
			List<WorkplaceWithTroops> list = troopsService.getTroopsWorkplaceGroupByTroopsCode3depth(pagingVO, workplaceWithTroops);
			for(WorkplaceWithTroops wwt : list){
				//2. 그룹화한 로우별로 세부소속값을 가져온다.(상설부대배치테이블-TroopsPlacement, 근무정보테이블-WorkplacePlacement)
				List<TroopsPlacement> tps = troopsService.getTroopsWorkplace(wwt);
				for(TroopsPlacement tp : tps){
					troops.add(tp.getCode1());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		mav.addObject("troops",troops);
		return mav;
	}

	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetail", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdatetroopsWorkplacePlacementDetail(@ModelAttribute WorkplaceDetail workplaceDetail) throws Exception {
		Response res = new Response();
		res.setData(workplaceDetail);
		if(workplaceDetail.getTroopsNo()==null||workplaceDetail.getTroopsNo().length==0){
			res = new Response(false, "troopsNo is empty");
			return ResponseEntity.ok(res);
		}else if(workplaceDetail.getWorkplaceNo()==0){
			res = new Response(false, "workplaceNo is empty");
			return ResponseEntity.ok(res);
		}else if(workplaceDetail.getMobilDate()==null){
			res = new Response(false, "mobilDate is empty");
			return ResponseEntity.ok(res);
		}else{
			//1. 근무지 번호, 부대번호, 근무일 입력
			//workplaceNo, troopsNo[], mobilDate, 부대번호는 0번쨰가 필수로 온다.
			int troopsNo = workplaceDetail.getTroopsNo()[0];
			WorkplacePlacement wp = new WorkplacePlacement();
			Code troops = new Code();
			troops.setCodeNo(troopsNo);
			wp.setCode1(troops);
			Code workplace = new Code();
			workplace.setCodeNo(workplaceDetail.getWorkplaceNo());
			wp.setCode2(workplace);
			wp.setWorkplacePlacementWorkDt(workplaceDetail.getMobilDate());
			//같은 값을 가진 row를 지우고 입력, 지울 때 시간별 근무정보도 다 지운다.
			workplaceService.insertWorkplacement(wp);
			//2. 근무정보 번호, 근무시간, 부대원 번호 입력
			//workplacePlacementNo, time1~24[]
			List<WorkplacePlacementDetail> detailList = workplaceService.getDetailList(wp, workplaceDetail);
			//그대로 입력한다.
			workplaceService.insertWorkplacementDetailList(detailList);
			res.setData(workplaceDetail);
		}
		
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "/workplace/troopsWorkplacePlacementUnitView.do")
	public ModelAndView troopsWorkplacePlacementUnitView(
			@RequestParam(value="unitNo", required=false) int[] unitNo,
			@RequestParam(value="troopsNo", required=true) int[] troopsNo,
			@RequestParam(value="time", required=true) String time,
			HttpServletRequest req
			){
		ModelAndView mav = new ModelAndView("workplace/troopsWorkplacePlacementUnitView");
		//부대정보는 담아서 보낸다..
		List<Code> troopsList = new ArrayList<Code>();
		if(troopsNo!=null){
			for(int i = 0 ; i<troopsNo.length; i ++){
				Code code = new Code();
				code.setCodeNo(troopsNo[i]);
				troopsList.add(codeService.getCode(code));
			}
		}
		mav.addObject("time",time);
		mav.addObject("checkedUnitNo",unitNo);
		mav.addObject("troopsList",troopsList);
		return mav;
	}
	
	@RequestMapping(value = "/workplace/workplaceTroopsDetail", method = RequestMethod.GET)
	public ResponseEntity<?> getTroopsWorkplacePlacementList(
			@RequestParam(value="troopsNo", required=true) int troopsNo,
			@RequestParam(value="mobilDate", required=true) Date mobilDate,
			HttpServletRequest req
			){
		Response res = new Response();
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		pagingVO.setRows(10000);
		WorkplacePlacement wps = new WorkplacePlacement();
		Code troops = new Code();
		troops.setCodeNo(troopsNo);
		wps.setCode1(troops);
		wps.setWorkplacePlacementWorkDt(mobilDate);
		WorkplacePlacement wp = troopsService.getWorkplaceTroops(wps);
		if(wp!=null&&wp.getWorkplacePlacementDetail()!=null){
			Map<String,List<Unit>> detailList = new HashMap<>();
			for(WorkplacePlacementDetail wpd : wp.getWorkplacePlacementDetail()){
				if(detailList.get(wpd.getWorkplacePlacementDetailWorkTime())==null){
					detailList.put(wpd.getWorkplacePlacementDetailWorkTime(), new ArrayList<Unit>());
				}
				detailList.get(wpd.getWorkplacePlacementDetailWorkTime()).add(wpd.getUnit());
			}
			res.setData(detailList);
		}
		//return할때 join되는 entity가 섞여 있으면 @ManyToOne(fetch = FetchType.EAGER)를 설정하거나 @jsonpropertyignore로 설정해야 한다.
		return ResponseEntity.ok(res);
	}
}
