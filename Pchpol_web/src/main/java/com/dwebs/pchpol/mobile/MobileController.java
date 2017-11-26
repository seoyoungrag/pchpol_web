/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : LoginController.java
 * 2. Package : com.dwebs.pchpol.login.controller
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 29. 오전 3:36:01
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 29. :            : 신규 개발.
 */
package com.dwebs.pchpol.mobile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dwebs.pchpol.attach.service.AttachService;
import com.dwebs.pchpol.board.service.BoardService;
import com.dwebs.pchpol.comment.service.CommentService;
import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.util.FileHandler;
import com.dwebs.pchpol.common.util.UtilFile;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.facility.vo.TroopsPlacementWithFacilities;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Attach;
import com.dwebs.pchpol.model.Board;
import com.dwebs.pchpol.model.Code;
import com.dwebs.pchpol.model.Comment;
import com.dwebs.pchpol.model.TroopsFacilityPlacement;
import com.dwebs.pchpol.model.Unit;
import com.dwebs.pchpol.model.WorkplacePlacement;
import com.dwebs.pchpol.model.WorkplacePlacementDetail;
import com.dwebs.pchpol.troops.service.TroopsService;

@RestController
public class MobileController extends BaseController {

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	@Autowired
	@Qualifier("attachService")
	private AttachService attachService;
	@Autowired
	@Qualifier("commentService")
	private CommentService commentService;
	@Autowired
	@Qualifier("troopsService")
	private TroopsService troopsService;

	@RequestMapping(value = "/mobile/index.do", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/index");
		return mav;
	}

	@RequestMapping(value = "/mobile/notice/list.do", method = RequestMethod.GET)
	public ModelAndView noticeList(@ModelAttribute Board board, HttpServletRequest request) {
		board.setBoardType("notice");
		List<Board> list = new ArrayList<Board>();
		int totCnt = 0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		try {
			list = boardService.getList(pagingVO, board); // 조회 결과
			totCnt = boardService.getTotCnt(pagingVO, board); // 전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
		}

		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		JQGridVO<Board> jqGridData = new JQGridVO<Board>();
		jqGridData.setPage(pagingVO.getPage()); // 조회 결과
		jqGridData.setTotal(String.valueOf(pagingVO.getTotal())); // 마지막 페이지 번호
		jqGridData.setRecords(String.valueOf(totCnt)); // 전체 페이지의 게시물 수
		jqGridData.setRows(list);
		ModelAndView mav = new ModelAndView("mobile/notice/notice_list");
		mav.addObject("searchBoard", board);
		mav.addObject("data", jqGridData);
		mav.addObject("pagingVO", pagingVO);
		return mav;
	}

	@RequestMapping(value = "/mobile/notice/view.do", method = RequestMethod.GET)
	public ModelAndView noticeView(@RequestParam(value = "curBoardNo", required = false) String curBoardNo,
			@RequestParam(value = "prevBoardNo", required = false) String prevBoardNo,
			@RequestParam(value = "nextBoardNo", required = false) String nextBoardNo,
			@RequestParam(value = "prevBoardTitle", required = false) String prevBoardTitle,
			@RequestParam(value = "nextBoardTitle", required = false) String nextBoardTitle,
			/* 본문에서 다른 본문으로 이동했을 때 이전 게시글과 다음 게시글을 가져오기 위해서 시작 */
			@RequestParam(value = "curBoardSeq", required = false) int curBoardSeq, @ModelAttribute Board board,
			HttpServletRequest request
	/* 이전 게시글과 다음 게시글을 가져오기 위해서 끝 */
	) {
		board.setBoardType("notice");
		ModelAndView mav = new ModelAndView("mobile/notice/notice_view");
		mav.addObject("curBoardNo", curBoardNo);
		Board curBoard = boardService.getById(curBoardNo);
		mav.addObject("board", curBoard);
		mav.addObject("searchBoard", board);
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		mav.addObject("rows", pagingVO.getRows());
		mav.addObject("page", pagingVO.getPage());
		mav.addObject("curBoardSeq", curBoardSeq);
		if (prevBoardNo != null) {
			mav.addObject("prevBoardNo", prevBoardNo);
			mav.addObject("prevBoardTitle", prevBoardTitle);
			mav.addObject("nextBoardNo", nextBoardNo);
			mav.addObject("nextBoardTitle", nextBoardTitle);
		} else {
			// 리스트에서 가져온게 아니라 본문에서 이동했을 때는 DB 조회
			List<Board> list = new ArrayList<Board>();
			// 쿼리상 현재 게시글 순서에서 -1한 값부터 3개를 가져온다.
			pagingVO.setStartNum(curBoardSeq == 0 ? curBoardSeq : curBoardSeq - 1);
			pagingVO.setRows(3);
			try {
				list = boardService.getList(pagingVO, board); // 조회 결과
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (curBoardSeq != 0) {
				mav.addObject("prevBoardNo", list.get(0).getBoardNo());
				mav.addObject("prevBoardTitle", list.get(0).getBoardTitle());
			} else {
				mav.addObject("prevBoardNo", "0");
			}

			if (list.size() == 3) {
				mav.addObject("nextBoardNo", curBoardSeq == 0 ? list.get(1).getBoardNo() : list.get(2).getBoardNo());
				mav.addObject("nextBoardTitle",
						curBoardSeq == 0 ? list.get(1).getBoardTitle() : list.get(2).getBoardTitle());
			} else {
				mav.addObject("nextBoardNo", "0");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/comment", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateComment(HttpServletRequest request, @ModelAttribute Comment comment)
			throws Exception {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		// TODO 테스트
		/*
		 * if(admin==null){ admin = new Admin(); admin.setAdminNo(2); }
		 */
		Unit unit = (Unit) session.getAttribute("unit");
		if (admin != null) {
			comment.setCommentWriterType("ADMIN");
			comment.setAdmin(admin);
		} else {
			comment.setCommentWriterType("UNIT");
			comment.setUnit(unit);
		}
		Response res = new Response();
		if (comment.getComment().getCommentNo() == 0) {
			comment.setComment(null);
		}
		commentService.insertOrUpdate(comment);

		res.setData(comment);
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "/mobile/notice/top1.do", method = RequestMethod.GET)
	public ResponseEntity<?> noticeTop1(@ModelAttribute Board board) {
		Response res = new Response();
		Board retBoard = boardService.getTop1(board);
		res.setData(retBoard);
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "/mobile/notice/count.do", method = RequestMethod.GET)
	public ResponseEntity<?> countAll(@ModelAttribute Board board) {
		Response res = new Response();
		Date date = new Date();
		// Java 시간 더하기
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 1시간 전
		cal.add(Calendar.HOUR, -1);
		board.setBoardWriteDt(cal.getTime());  
		board.setBoardCategory("긴급");
		PagingVO pagingVO = new PagingVO();
		pagingVO.setSearchType("recent");
		int cnt = boardService.getTotCnt(pagingVO, board);
		res.setData(cnt);
		return ResponseEntity.ok(res);
	}

	@RequestMapping(value = "/mobile/work/workinfo.do", method = RequestMethod.GET)
	public ModelAndView workinfo(
			HttpServletRequest request,
			@RequestParam(value="mobileDate", required=false) Date mobilDate) {
		ModelAndView mav = new ModelAndView("mobile/work/workinfo");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		//사용자 정보에서 근무정보를 가져온다.
		//1. 사용자 정보의 부대정보로 부대가 배치된 근무지를 가져온다.
		WorkplacePlacement wp = new WorkplacePlacement();
		if(mobilDate == null){
			mobilDate = new Date();
			/*
			try {
				mobilDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20180101" );
			} catch (ParseException e) {
				e.printStackTrace();
			}
			*/
		}
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(mobilDate);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);

		mobilDate = cal.getTime();

		mav.addObject("mobilDate",mobilDate);
		if(unit!=null){
			wp.setWorkplacePlacementWorkDt(mobilDate);
			wp.setCode1(unit.getCode1());
			WorkplacePlacement placeInfo = troopsService.getWorkplaceTroops(wp);
			mav.addObject("placemetnInfo",placeInfo);
			//2. 근무지의 세부 배치 정보를 가져온다.
			if(placeInfo != null){ //세부 배치 정보가 없을 수 있다.
				List<WorkplacePlacementDetail> placementDetail = placeInfo.getWorkplacePlacementDetail();
				mav.addObject("placementDetail",placementDetail);
			}
			mav.addObject("unit", unit);
		}
		return mav;
	}


	@RequestMapping(value = "/mobile/camp/lodge.do", method = RequestMethod.GET)
	public ModelAndView campLodge(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/camp/lodge");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			//사용자 정보에서 근무정보를 가져온다.
			//1. 사용자 정보의 부대정보로 부대가 배치된 시설정보를 가져온다.
			Code troops = unit.getCode1();
			TroopsFacilityPlacement troopsFacilitySearch = new TroopsFacilityPlacement();
			troopsFacilitySearch.setCode(troops);
			troopsService.getTroopsFacilityListByTroops(troopsFacilitySearch);
			//List<TroopsFacilityPlacement> troopsFacilityList = troops.getTroopsFacilityPlacements();
			List<TroopsFacilityPlacement> troopsFacilityList = troopsService.getTroopsFacilityListByTroops(troopsFacilitySearch);
			TroopsPlacementWithFacilities tpwf = new TroopsPlacementWithFacilities();
			for(TroopsFacilityPlacement f : troopsFacilityList){
				tpwf.setFacilityMobilStartDt(f.getTroopsFacilityMobilStartDt());
				tpwf.setFacilityMobilEndDt(f.getTroopsFacilityMobilEndDt());
				//시설별로 숙영, 급식 시설을 분류한다. -> 숙영만 가져오면 된다.
				if(f.getFacility()!=null && f.getFacility().getFacilityType().equals("bed")){
					tpwf.getBedFacility().add(f.getFacility());
				}
			}
			mav.addObject("tpwf", tpwf);
			mav.addObject("unit", unit);
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/camp/meal.do", method = RequestMethod.GET)
	public ModelAndView campMeal(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/camp/meal");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			mav.addObject("unit", unit);
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/olympic/schedule.do", method = RequestMethod.GET)
	public ModelAndView schedule(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/olympic/schedule");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			mav.addObject("unit", unit);
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/report/list.do", method = RequestMethod.GET)
	public ModelAndView reportList(@ModelAttribute Board board, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/report/report_list");
		List<Board> list = new ArrayList<Board>();
		int totCnt = 0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		HttpSession session = request.getSession();
		Unit unit = (Unit) session.getAttribute("unit");
		Admin admin = (Admin)session.getAttribute("admin");
		try {
			board.setBoardType("normal");
			board.setBoardCategory("상황");
			list = boardService.getListMobile(pagingVO, board, unit, admin); // 조회 결과
			totCnt = boardService.getTotCntMobile(pagingVO, board, unit, admin); // 전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
		}

		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		JQGridVO<Board> jqGridData = new JQGridVO<Board>();
		jqGridData.setPage(pagingVO.getPage()); // 조회 결과
		jqGridData.setTotal(String.valueOf(pagingVO.getTotal())); // 마지막 페이지 번호
		jqGridData.setRecords(String.valueOf(totCnt)); // 전체 페이지의 게시물 수
		jqGridData.setRows(list);
		mav.addObject("searchBoard", board);
		mav.addObject("data", jqGridData);
		mav.addObject("pagingVO", pagingVO);
		return mav;
	}

	@RequestMapping(value = "/mobile/report/view.do", method = RequestMethod.GET)
	public ModelAndView reportView(@RequestParam(value = "curBoardNo", required = false) String curBoardNo,
			@RequestParam(value = "prevBoardNo", required = false) String prevBoardNo,
			@RequestParam(value = "nextBoardNo", required = false) String nextBoardNo,
			@RequestParam(value = "prevBoardTitle", required = false) String prevBoardTitle,
			@RequestParam(value = "nextBoardTitle", required = false) String nextBoardTitle,
			/* 본문에서 다른 본문으로 이동했을 때 이전 게시글과 다음 게시글을 가져오기 위해서 시작 */
			@RequestParam(value = "curBoardSeq", required = false) int curBoardSeq, @ModelAttribute Board board,
			HttpServletRequest request
	/* 이전 게시글과 다음 게시글을 가져오기 위해서 끝 */
	) {
		board.setBoardType("normal");
		board.setBoardCategory("상황");
		ModelAndView mav = new ModelAndView("mobile/report/report_view");
		mav.addObject("curBoardNo", curBoardNo);
		Board curBoard = boardService.getById(curBoardNo);
		mav.addObject("board", curBoard);
		mav.addObject("searchBoard", board);
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		mav.addObject("rows", pagingVO.getRows());
		mav.addObject("page", pagingVO.getPage());
		mav.addObject("curBoardSeq", curBoardSeq);
		if (prevBoardNo != null) {
			mav.addObject("prevBoardNo", prevBoardNo);
			mav.addObject("prevBoardTitle", prevBoardTitle);
			mav.addObject("nextBoardNo", nextBoardNo);
			mav.addObject("nextBoardTitle", nextBoardTitle);
		} else {
			// 리스트에서 가져온게 아니라 본문에서 이동했을 때는 DB 조회
			List<Board> list = new ArrayList<Board>();
			// 쿼리상 현재 게시글 순서에서 -1한 값부터 3개를 가져온다.
			pagingVO.setStartNum(curBoardSeq == 0 ? curBoardSeq : curBoardSeq - 1);
			pagingVO.setRows(3);
			try {
				list = boardService.getList(pagingVO, board); // 조회 결과
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (curBoardSeq != 0) {
				mav.addObject("prevBoardNo", list.get(0).getBoardNo());
				mav.addObject("prevBoardSubCategory", list.get(0).getBoardSubCategory());
				mav.addObject("prevBoardTitle", list.get(0).getBoardTitle());
			} else {
				mav.addObject("prevBoardNo", "0");
			}

			if (list.size() == 3) {
				mav.addObject("nextBoardNo", curBoardSeq == 0 ? list.get(1).getBoardNo() : list.get(2).getBoardNo());
				mav.addObject("nextBoardSubCategory", curBoardSeq == 0 ? list.get(1).getBoardSubCategory() : list.get(2).getBoardSubCategory());
				mav.addObject("nextBoardTitle", curBoardSeq == 0 ? list.get(1).getBoardTitle() : list.get(2).getBoardTitle());
			} else {
				mav.addObject("nextBoardNo", "0");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/report/write.do", method = RequestMethod.GET)
	public ModelAndView reportWrite(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/report/report_write");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			mav.addObject("unit", unit);
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/report/modify.do", method = RequestMethod.GET)
	public ModelAndView reportModify(@RequestParam(value = "curBoardNo", required = false) String curBoardNo,
			@RequestParam(value = "prevBoardNo", required = false) String prevBoardNo,
			@RequestParam(value = "nextBoardNo", required = false) String nextBoardNo,
			@RequestParam(value = "prevBoardTitle", required = false) String prevBoardTitle,
			@RequestParam(value = "nextBoardTitle", required = false) String nextBoardTitle,
			/* 본문에서 다른 본문으로 이동했을 때 이전 게시글과 다음 게시글을 가져오기 위해서 시작 */
			@RequestParam(value = "curBoardSeq", required = false) int curBoardSeq, @ModelAttribute Board board,
			HttpServletRequest request
	/* 이전 게시글과 다음 게시글을 가져오기 위해서 끝 */
	) {
		ModelAndView mav = new ModelAndView("mobile/report/report_modify");
		mav.addObject("curBoardNo", curBoardNo);
		Board curBoard = boardService.getById(curBoardNo);
		mav.addObject("board", curBoard);
		mav.addObject("searchBoard", board);
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		mav.addObject("rows", pagingVO.getRows());
		mav.addObject("page", pagingVO.getPage());
		mav.addObject("curBoardSeq", curBoardSeq);
		if (prevBoardNo != null) {
			mav.addObject("prevBoardNo", prevBoardNo);
			mav.addObject("prevBoardTitle", prevBoardTitle);
			mav.addObject("nextBoardNo", nextBoardNo);
			mav.addObject("nextBoardTitle", nextBoardTitle);
		} else {
			// 리스트에서 가져온게 아니라 본문에서 이동했을 때는 DB 조회
			List<Board> list = new ArrayList<Board>();
			// 쿼리상 현재 게시글 순서에서 -1한 값부터 3개를 가져온다.
			pagingVO.setStartNum(curBoardSeq == 0 ? curBoardSeq : curBoardSeq - 1);
			pagingVO.setRows(3);
			try {
				list = boardService.getList(pagingVO, board); // 조회 결과
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (curBoardSeq != 0) {
				mav.addObject("prevBoardNo", list.get(0).getBoardNo());
				mav.addObject("prevBoardTitle", list.get(0).getBoardTitle());
			} else {
				mav.addObject("prevBoardNo", "0");
			}

			if (list.size() == 3) {
				mav.addObject("nextBoardNo", curBoardSeq == 0 ? list.get(1).getBoardNo() : list.get(2).getBoardNo());
				mav.addObject("nextBoardTitle",
						curBoardSeq == 0 ? list.get(1).getBoardTitle() : list.get(2).getBoardTitle());
			} else {
				mav.addObject("nextBoardNo", "0");
			}
		}
		return mav;
	}
	
	@RequestMapping(value = "/mobile/proposal/list.do", method = RequestMethod.GET)
	public ModelAndView proposalList(@ModelAttribute Board board, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/proposal/proposal_list");
		List<Board> list = new ArrayList<Board>();
		int totCnt = 0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		try {
			board.setBoardType("normal");
			board.setBoardCategory("건의");
			list = boardService.getList(pagingVO, board); // 조회 결과
			totCnt = boardService.getTotCnt(pagingVO, board); // 전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
		}

		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		JQGridVO<Board> jqGridData = new JQGridVO<Board>();
		jqGridData.setPage(pagingVO.getPage()); // 조회 결과
		jqGridData.setTotal(String.valueOf(pagingVO.getTotal())); // 마지막 페이지 번호
		jqGridData.setRecords(String.valueOf(totCnt)); // 전체 페이지의 게시물 수
		jqGridData.setRows(list);
		mav.addObject("searchBoard", board);
		mav.addObject("data", jqGridData);
		mav.addObject("pagingVO", pagingVO);
		return mav;
	}
	

	@RequestMapping(value = "/mobile/proposal/view.do", method = RequestMethod.GET)
	public ModelAndView proposalView(@RequestParam(value = "curBoardNo", required = false) String curBoardNo,
			@RequestParam(value = "prevBoardNo", required = false) String prevBoardNo,
			@RequestParam(value = "nextBoardNo", required = false) String nextBoardNo,
			@RequestParam(value = "prevBoardTitle", required = false) String prevBoardTitle,
			@RequestParam(value = "nextBoardTitle", required = false) String nextBoardTitle,
			/* 본문에서 다른 본문으로 이동했을 때 이전 게시글과 다음 게시글을 가져오기 위해서 시작 */
			@RequestParam(value = "curBoardSeq", required = false) int curBoardSeq, @ModelAttribute Board board,
			HttpServletRequest request
	/* 이전 게시글과 다음 게시글을 가져오기 위해서 끝 */
	) {
		board.setBoardType("normal");
		board.setBoardCategory("건의");
		ModelAndView mav = new ModelAndView("mobile/proposal/proposal_view");
		mav.addObject("curBoardNo", curBoardNo);
		Board curBoard = boardService.getById(curBoardNo);
		mav.addObject("board", curBoard);
		mav.addObject("searchBoard", board);
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		mav.addObject("rows", pagingVO.getRows());
		mav.addObject("page", pagingVO.getPage());
		mav.addObject("curBoardSeq", curBoardSeq);
		if (prevBoardNo != null) {
			mav.addObject("prevBoardNo", prevBoardNo);
			mav.addObject("prevBoardTitle", prevBoardTitle);
			mav.addObject("nextBoardNo", nextBoardNo);
			mav.addObject("nextBoardTitle", nextBoardTitle);
		} else {
			// 리스트에서 가져온게 아니라 본문에서 이동했을 때는 DB 조회
			List<Board> list = new ArrayList<Board>();
			// 쿼리상 현재 게시글 순서에서 -1한 값부터 3개를 가져온다.
			pagingVO.setStartNum(curBoardSeq == 0 ? curBoardSeq : curBoardSeq - 1);
			pagingVO.setRows(3);
			try {
				list = boardService.getList(pagingVO, board); // 조회 결과
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (curBoardSeq != 0) {
				mav.addObject("prevBoardNo", list.get(0).getBoardNo());
				mav.addObject("prevBoardTitle", list.get(0).getBoardTitle());
			} else {
				mav.addObject("prevBoardNo", "0");
			}

			if (list.size() == 3) {
				mav.addObject("nextBoardNo", curBoardSeq == 0 ? list.get(1).getBoardNo() : list.get(2).getBoardNo());
				mav.addObject("nextBoardTitle",
						curBoardSeq == 0 ? list.get(1).getBoardTitle() : list.get(2).getBoardTitle());
			} else {
				mav.addObject("nextBoardNo", "0");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/proposal/write.do", method = RequestMethod.GET)
	public ModelAndView proposalWrite(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/proposal/proposal_write");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			mav.addObject("unit", unit);
		}
		return mav;
	}

	@RequestMapping(value = "/mobile/proposal/modify.do", method = RequestMethod.GET)
	public ModelAndView proposalModify(@RequestParam(value = "curBoardNo", required = false) String curBoardNo,
			@RequestParam(value = "prevBoardNo", required = false) String prevBoardNo,
			@RequestParam(value = "nextBoardNo", required = false) String nextBoardNo,
			@RequestParam(value = "prevBoardTitle", required = false) String prevBoardTitle,
			@RequestParam(value = "nextBoardTitle", required = false) String nextBoardTitle,
			/* 본문에서 다른 본문으로 이동했을 때 이전 게시글과 다음 게시글을 가져오기 위해서 시작 */
			@RequestParam(value = "curBoardSeq", required = false) int curBoardSeq, @ModelAttribute Board board,
			HttpServletRequest request
	/* 이전 게시글과 다음 게시글을 가져오기 위해서 끝 */
	) {
		ModelAndView mav = new ModelAndView("mobile/proposal/proposal_modify");
		mav.addObject("curBoardNo", curBoardNo);
		Board curBoard = boardService.getById(curBoardNo);
		mav.addObject("board", curBoard);
		mav.addObject("searchBoard", board);
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(request);
		mav.addObject("rows", pagingVO.getRows());
		mav.addObject("page", pagingVO.getPage());
		mav.addObject("curBoardSeq", curBoardSeq);
		if (prevBoardNo != null) {
			mav.addObject("prevBoardNo", prevBoardNo);
			mav.addObject("prevBoardTitle", prevBoardTitle);
			mav.addObject("nextBoardNo", nextBoardNo);
			mav.addObject("nextBoardTitle", nextBoardTitle);
		} else {
			// 리스트에서 가져온게 아니라 본문에서 이동했을 때는 DB 조회
			List<Board> list = new ArrayList<Board>();
			// 쿼리상 현재 게시글 순서에서 -1한 값부터 3개를 가져온다.
			pagingVO.setStartNum(curBoardSeq == 0 ? curBoardSeq : curBoardSeq - 1);
			pagingVO.setRows(3);
			try {
				list = boardService.getList(pagingVO, board); // 조회 결과
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (curBoardSeq != 0) {
				mav.addObject("prevBoardNo", list.get(0).getBoardNo());
				mav.addObject("prevBoardTitle", list.get(0).getBoardTitle());
			} else {
				mav.addObject("prevBoardNo", "0");
			}

			if (list.size() == 3) {
				mav.addObject("nextBoardNo", curBoardSeq == 0 ? list.get(1).getBoardNo() : list.get(2).getBoardNo());
				mav.addObject("nextBoardTitle",
						curBoardSeq == 0 ? list.get(1).getBoardTitle() : list.get(2).getBoardTitle());
			} else {
				mav.addObject("nextBoardNo", "0");
			}
		}
		return mav;
	}

	/**
	 * <PRE>
	 * 1. MethodName : insertOrUpdateBoard
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 7:11:49
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param board
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/mobile/board", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateBoard(
			HttpServletRequest request, 
			@ModelAttribute Board board,
			@RequestParam(value="fileList", required=false) String fileListArr,
			@RequestParam(value="deletingFileNo", required=false) List<Integer> deletingFileArr
			) {
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		Unit unit = (Unit)session.getAttribute("unit");
		Response res = new Response();
		if(admin==null){
			if(unit!=null){
				board.setUnit(unit);
			}else{
				res = new Response(false, "session invalid");
				return ResponseEntity.ok(res);
			}
		}else{
			board.setAdmin(admin);
		}
		boardService.insertOrUpdate(board);
		logger.info("insert board: "+board.getBoardNo());
		List<Attach> attaches = null;
		try {
			attaches = setFileList(request, fileListArr, board);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board.setAttaches(attaches);
		attachService.insertBoardAttachesMobile(attaches);
		
		if(deletingFileArr != null && deletingFileArr.size()>0){
			attachService.deleteFiles(deletingFileArr);
		}
		res.setData(board);
		return ResponseEntity.ok(res);
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : getFileList
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 10:29:49
	 * </PRE>
	 *   @return List<Attach>
	 *   @param request
	 *   @param fileListArr
	 *   @return
	 * @throws FileNotFoundException 
	 */
	private List<Attach> setFileList(HttpServletRequest request, String fileListArr, Board board) throws Exception {

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
					attach.setBoard(board);
					//fileList[i].substring(fileList[i].lastIndexOf("."))
					attach.setAttachFileSize(String.valueOf(filesize.intValue()));
					attaches.add(attach);

					logger.info("insert board - attaches: "+saveLocation + File.separator + fileStrNm);
				}
			}
		}
		return attaches;
	}
//  파일을 업로드하는 컨트롤러 클래스 메소드
    @RequestMapping(value = "/mobile/mobileFileUpload", method = RequestMethod.POST)
//  인자로 MulfiPartFile 객체, MultipartHttpServletRequest 객체, 업로드 하려는 도메인 클래스를 받는다
    public ResponseEntity<?> upload(HttpServletRequest req, HttpServletResponse response){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile multipartFile =  null; // multipart file class depends on which class you use assuming you are using org.springframework.web.multipart.commons.CommonsMultipartFile
        Iterator<String> iterator = multipartRequest.getFileNames();
        UtilFile utilFile = new UtilFile();
        List<String> uploadPath = new ArrayList<String>();
    	String id = String.valueOf(System.currentTimeMillis());
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            multipartFile = (MultipartFile) multipartRequest.getFile(key);
            String uPath = utilFile.fileUpload(multipartRequest, multipartFile, new Object(), id);
            uploadPath.add(uPath);
        }
        //파일이 쪼개져있고, 확장자가 zip인 경우
        try {
        	Set<String> fileSet = new HashSet<String>();
        	File file;
        	String path;
        	String partedFileName;
        	String part;
        	String ext;
        	String oriFileName;
        	for(String uPath : uploadPath){
        		//현재 업로드된 파일
        		file = new File(uPath);
        		//파일이 업로드된 경로
            	path = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator));
            	//파일명
            	partedFileName = file.getName();
            	//파일이 끝의 확장자 가져오기
            	part = partedFileName.substring(partedFileName.lastIndexOf('.')+1,partedFileName.length());
            	try{
            		Integer.parseInt(part);
            		//파일 확장자가 zip인가?
            		String extFileName = partedFileName.substring(0,partedFileName.length()-("."+part).length());
            		ext = extFileName.substring(extFileName.lastIndexOf('.')+1,extFileName.length());
            		if(ext.equalsIgnoreCase("zip")){
                    	//확장자를 제외한 파일경로
                    	oriFileName = partedFileName.substring(0,partedFileName.lastIndexOf("."));
                    	//쪼개진 파일을 묶기 위해 묶어질 파일경로 set 생성
                    	fileSet.add(path +File.separator+ oriFileName);
                	}
            	}catch(NumberFormatException e){
            		logger.info("not a seperated file: "+uPath);
            		e.printStackTrace();
            	}
        	}
        	//파일을 묶고 압축해제한다.
        	for(String oriFilePath : fileSet){
        		logger.info("join and decompress: "+oriFilePath);
            	try{
            		file = new File(oriFilePath);
        			UtilFile.join(oriFilePath);	
        			UtilFile.decompress(oriFilePath, file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf(File.separator)));
            	}catch(NumberFormatException e){
            		e.printStackTrace();
            	}

        	}
        	//분할 및 압축된 파일을 삭제한다.
        	for(String oriFilePath : fileSet){
        		logger.info("delete: "+oriFilePath);
            	try{
            		file = new File(oriFilePath);
            		file.delete();
            	}catch(NumberFormatException e){
            		e.printStackTrace();
            	}

        	}
        	for(String uPath : uploadPath){
        		logger.info("delete: "+uPath);
            	try{
            		file = new File(uPath);
            		file.delete();
            	}catch(NumberFormatException e){
            		e.printStackTrace();
            	}
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Response res = new Response();

        //System.out.println("RewardController reAddProCtrl reward : " + reward);
        
//      UtilFile 객체 생성
//      파일 업로드 결과값을 path로 받아온다(이미 fileUpload() 메소드에서 해당 경로에 업로드는 끝났음)
        //String uploadPath = utilFile.fileUpload(request, uploadFile, reward);
        
//      해당 경로만 받아 db에 저장
        //int n = rewardService.reAddServ(uploadPath, reward);
        
        //System.out.println("RewardController reAddProCtrl n : " + n);
        //System.out.println("RewardController reAddProCtrl uploadPath : " + uploadPath);
		res.setData(uploadPath);
		return ResponseEntity.ok(res);
    }
    
    @RequestMapping(value = "/mobile/olympic/stadiumInfoList.do", method = RequestMethod.GET)
	public ModelAndView stadium_info_list(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("mobile/olympic/stadium_info_list");
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			mav.addObject("unit", unit);
		}
		return mav;
	}

    @RequestMapping(value = "/mobile/olympic/stadiums.do", method = RequestMethod.GET)
	public ModelAndView stadiums(
			HttpServletRequest request,
			@RequestParam(value="view", required=true) String view
			) {
		ModelAndView mav = new ModelAndView("mobile/olympic/"+view);
		HttpSession session = request.getSession();
		//세션에서 사용자 정보를 가져온다.
		Unit unit = (Unit) session.getAttribute("unit");
		if(unit==null){
			return mav;
		}else{
			mav.addObject("unit", unit);
		}
		return mav;
	}

}
