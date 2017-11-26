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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
	 * 3. Comment   : 상설부대 배치 리스트 가져오기, 근무지 코드를 code1depth, code2depth까지 그룹화해서 가져온 다음, 각 상설부대 배치된 로우로우들을 가져와야한다.
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
				Ascending ascending = new Ascending(); 
				Collections.sort(tps, ascending);
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
			@RequestParam(value="w_code1depth", required=false) String w_code1depth,
			@RequestParam(value="w_code2depth", required=false) String w_code2depth,
			@RequestParam(value="t_code1depth", required=false) String t_code1depth,
			@RequestParam(value="t_code2depth", required=false) String t_code2depth,
			@RequestParam(value="t_code3depth", required=false) String t_code3depth
			){
		ModelAndView mav = new ModelAndView("workplace/troopsWorkplacePlacementView");
		mav.addObject("viewType",viewType);
		mav.addObject("w_code1depth",w_code1depth);
		mav.addObject("w_code2depth",w_code2depth);
		mav.addObject("t_code1depth",t_code1depth);
		mav.addObject("t_code2depth",t_code2depth);
		mav.addObject("t_code3depth",t_code3depth);
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
	public ResponseEntity<?> insertOrUpdateWorkplaceTroopsWorkplacePlacement(@ModelAttribute TroopsPlacementModel troopsPlacement) throws Exception {
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
			@RequestParam(value="reloadType", required=false, defaultValue="all") String reloadType,
			HttpServletRequest req){
		if(workplaceWithTroops.getMobilDate()==null){
			try {
				workplaceWithTroops.setMobilDate(new SimpleDateFormat( "yyyyMMdd" ).parse( "20180101" ));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
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
					str.append(start+":"+end);
				}else{
					
				}
				wwt.getWorkTimes().add(str.toString());
				/*
				for(WorkplacePlacementDetail wd : detailList){
					str.append(wd.getWorkplacePlacementDetailWorkTime()+",");
				}
				if(str.length()>0){
					wwt.getWorkTimes().add(str.toString().substring(0,str.length()-1));
				}else{
					wwt.getWorkTimes().add("");
				}
				*/
				//부대 리스트별 근무시간 셋팅
				/*
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
				*/
			}
			int num = 0;
			if(reloadType.equals("notReg")){
				/*
				Iterator<WorkplaceWithTroops> iterator = list.iterator();
				while (iterator.hasNext()){
					WorkplaceWithTroops mp = iterator.next();
					if(mp.getTroopsDetail().size()!=0){
						num++;
						iterator.remove();
					}
				 }
				 */
				List<WorkplaceWithTroops> notReg = new ArrayList<WorkplaceWithTroops>();
				for (WorkplaceWithTroops wwt : list) {
					if (wwt.getTroopsDetail().size() == 0) {
						notReg.add(wwt);
					}
				}
				list = notReg;
			}
			totCnt -= num;
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
			@ModelAttribute WorkplaceWithTroops workplaceWithTroops,
			HttpServletRequest req
			){
		ModelAndView mav = new ModelAndView("workplace/troopsWorkplacePlacementDetailView");
		mav.addObject("viewType",viewType);
		mav.addObject("workplaceCode",workplaceWithTroops.getWorkplace());
		mav.addObject("mobilDate",new SimpleDateFormat("yyyy-MM-dd").format(workplaceWithTroops.getMobilDate()));
		mav.addObject("troops",workplaceWithTroops.getTroops());
		//상설부대배치한 부대 리스트를 가져온다. 검색조건은 근무지이다.(지역, 대회시설)
		/* 상설부대 배치랑 상관없다.
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
		*/
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
			@RequestParam(value="workplaceNo", required=true) int workplaceNo,
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
		Code workplace = new Code();
		workplace.setCodeNo(workplaceNo);
		wps.setCode2(workplace);
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
	

	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetailListSep.do")
	public ModelAndView troopsWorkplacePlacementDetailListSepPage() {
		return new ModelAndView("workplace/troopsWorkplacePlacementDetailListSep");
	}
	

	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetailListSep", method = RequestMethod.GET)
	public ResponseEntity<?> troopsWorkplacePlacementDetailListSep(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute WorkplacePlacement wp,
			HttpServletRequest req){
		if(wp.getWorkplacePlacementWorkDt()==null){
			try {
				wp.setWorkplacePlacementWorkDt(new SimpleDateFormat( "yyyyMMdd" ).parse( "20180101" ));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Response res = new Response();
		List<WorkplacePlacement> list;
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		pagingVO.setSearchType("codeCategory");
		pagingVO.setSearchWord("workplace");
		try {
			list = troopsService.getWorkplaceTroops(pagingVO, wp);
			totCnt = troopsService.getTotCntWorkplaceTroops(pagingVO, wp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<WorkplacePlacement> jqGridData = new JQGridVO<WorkplacePlacement>();
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
	

	@RequestMapping(value = "/workplace/troopsWorkplacePlacementDetailViewSep.do")
	public ModelAndView troopsWorkplacePlacementDetailViewSep(
			@ModelAttribute WorkplacePlacement wp,
			HttpServletRequest req
			){
		ModelAndView mav = new ModelAndView("workplace/troopsWorkplacePlacementDetailViewSep");
		wp = troopsService.getWorkplacePlacement(wp);
		mav.addObject("workplace",wp.getCode2());
		mav.addObject("troops",wp.getCode1());
		mav.addObject("mobilDate",new SimpleDateFormat("yyyy-MM-dd").format(wp.getWorkplacePlacementWorkDt()));
		return mav;
	}

	@RequestMapping(value = "/workplace/deleteTroopsWorkplacePlacement", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTroopsWorkplacePlacement(@RequestBody List<Integer> ids) {
		Response res = new Response();
		troopsService.deleteWorkplaceTroopsByIds(ids); 
		res.setData(ids);
		return ResponseEntity.ok(res);
	}
	
	@RequestMapping(value = "/workplace/troopsWorkplaceDetail", method = RequestMethod.GET)
	public ResponseEntity<?> getTroopsWorkplaceDetail(
			@RequestParam(value="unitNo", required=true) int unitNo,
			@RequestParam(value="workplaceNo", required=true) int workplaceNo,
			@RequestParam(value="mobilDate", required=true) Date mobilDate,
			HttpServletRequest req
			){
		Response res = new Response();
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		pagingVO.setRows(10000);
		WorkplacePlacementDetail wpd = new WorkplacePlacementDetail();
		Unit unit = new Unit();
		unit.setUnitNo(unitNo);
		wpd.setUnit(unit);
		List<WorkplacePlacementDetail> wpdl = new ArrayList<WorkplacePlacementDetail>();
		wpdl.add(wpd);
		WorkplacePlacement wp = new WorkplacePlacement();
		wp.setWorkplacePlacementDetail(wpdl);
		wp.setWorkplacePlacementWorkDt(mobilDate);
		
		List<WorkplacePlacement> findWp = workplaceService.findWp(wp);
		Set<WorkplacePlacement> retWp = new HashSet<WorkplacePlacement>();
		for(WorkplacePlacement fWp : findWp){
			if(fWp.getCode2().getCodeNo()!=workplaceNo){
				retWp.add(fWp);
			}
		}
		res.setData(new ArrayList<WorkplacePlacement>(retWp));
		//return할때 join되는 entity가 섞여 있으면 @ManyToOne(fetch = FetchType.EAGER)를 설정하거나 @jsonpropertyignore로 설정해야 한다.
		return ResponseEntity.ok(res);
	}

}

class Descending implements Comparator<TroopsPlacement> {
 
    @Override
    public int compare(TroopsPlacement o1, TroopsPlacement o2) {
    	if(o1.getCode1().getCode4depth()==null){
    		return o2.getCode1().getCode3depth().compareTo(o1.getCode1().getCode3depth());
    	}else{
    		return o2.getCode1().getCode4depth().compareTo(o1.getCode1().getCode4depth());
    	}
    }
 
}
 
class Ascending implements Comparator<TroopsPlacement> {
 
    @Override
    public int compare(TroopsPlacement o2, TroopsPlacement o1) {
    	if(o1.getCode1().getCode4depth()==null){
    		return o2.getCode1().getCode3depth().compareTo(o1.getCode1().getCode3depth());
    	}else{
    		return o2.getCode1().getCode4depth().compareTo(o1.getCode1().getCode4depth());
    	}
    }
 
}

class StrAscending implements Comparator<String> {
	 
    @Override
    public int compare(String o2, String o1) {
		return o2.compareTo(o1);
    }
 
}

class WorkplacePlacementDetailAscending implements Comparator<WorkplacePlacementDetail> {
	 
    @Override
    public int compare(WorkplacePlacementDetail o2, WorkplacePlacementDetail o1) {
		return Integer.valueOf(o2.getWorkplacePlacementDetailWorkTime().substring(4, o2.getWorkplacePlacementDetailWorkTime().length())).compareTo(Integer.valueOf(o1.getWorkplacePlacementDetailWorkTime().substring(4, o1.getWorkplacePlacementDetailWorkTime().length())));
    }
 
}
