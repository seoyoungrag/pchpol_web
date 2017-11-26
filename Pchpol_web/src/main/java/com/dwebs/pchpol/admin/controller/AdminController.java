package com.dwebs.pchpol.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.dwebs.pchpol.admin.service.AdminService;
import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.model.Admin;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AdminController.java
 * 3. Package  : com.dwebs.pchpol.admin
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 9. 26. 오후 12:30:08
 * </PRE>
 */
@RestController
public class AdminController extends BaseController {

	@Autowired
	@Qualifier("adminService")
	private AdminService adminService;
	
	/**
	 * <PRE>
	 * 1. MethodName : main
	 * 2. ClassName  : AdminController
	 * 3. Comment   : 메인화면을 호출한다. 현재 프로젝트의 index.jsp에서 main.do를 호출하게 되어있다.
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 9. 26. 오후 12:30:41
	 * </PRE>
	 *   @return ModelAndView
	 *   @param request
	 *   @param response
	 *   @return
	 */
	@RequestMapping(value = "/admin/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
	/**
	 * <PRE>
	 * 1. MethodName : empty
	 * 2. ClassName  : AdminController
	 * 3. Comment   :  빈화면이다. 메인화면의 본문페이지로 뿌려주고 있다.
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 9. 26. 오후 2:49:02
	 * </PRE>
	 *   @return ModelAndView
	 *   @param request
	 *   @param response
	 *   @return
	 */
	@RequestMapping(value = "/admin/empty.do")
	public ModelAndView empty(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("admin/empty");
		return mav;
	}

	/**
	 * <PRE>
	 * 1. MethodName : adminList
	 * 2. ClassName  : AdminController
	 * 3. Comment   : '상단메뉴-관리자 정보-관리자 리스트' 페이지로 이동한다.
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 9. 26. 오후 3:34:52
	 * </PRE>
	 *   @return ModelAndView
	 *   @param request
	 *   @param response
	 *   @return
	 */
	@RequestMapping(value = "/admin/list.do")
	public ModelAndView adminListPage() {
		return new ModelAndView("admin/adminList");
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
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ResponseEntity<?> getAdminList(@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType, HttpServletRequest req){
		Response res = new Response();
		List<Admin> list = new ArrayList<Admin>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = adminService.getList(pagingVO); //조회 결과
			totCnt = adminService.getTotCnt(pagingVO); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<Admin> jqGridData = new JQGridVO<Admin>();
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
	 * 1. MethodName : insertAdmin
	 * 2. ClassName  : AdminController
	 * 3. Comment   : '상단메뉴-관리자 정보-관리자 리스트-등록/뷰에서 확인 클릭' 등록 및 수정을 수행한다. model의 id가 0이면 등록, 0보다 크면 수정한다. id = adminNo
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 3:58:04
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param admin
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateAdmin(@ModelAttribute Admin admin) throws Exception {
		Response res = new Response();
		adminService.insertOrUpdate(admin);
		res.setData(admin);
		return ResponseEntity.ok(res);
	}
	
	/**
	 * <PRE>
	 * 1. MethodName : adminRegPage
	 * 2. ClassName  : AdminController
	 * 3. Comment   : '상단메뉴-관리자 정보-관리자 리스트-뷰' 뷰화면 페이지로 이동한다. 등록,수정,삭제가 동일한 페이지를 사용한다. type으로 구분한다. 등록:reg, 수정/뷰:view
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 3:57:46
	 * </PRE>
	 *   @return ModelAndView
	 *   @param type
	 *   @param adminNo
	 *   @return
	 */
	@RequestMapping(value = "/admin/view.do")
	public ModelAndView adminView(
			@RequestParam(value="type", required=true, defaultValue="view") String type,
			@RequestParam(value="adminNo", required=false) String adminNo) {
		ModelAndView mav = new ModelAndView("admin/adminView");
		mav.addObject("type",type);
		mav.addObject("adminNo",adminNo);
		return mav;
	}
	

	/**
	 * <PRE>
	 * 1. MethodName : getAdminById
	 * 2. ClassName  : AdminController
	 * 3. Comment   : '상단메뉴-관리자 정보-관리자 리스트-뷰' 뷰화면에서 관리자 정보를 불러오기 위해 ajax호출된다. id를 받아 조회한다. id = adminNo
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 3:58:58
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param adminNo
	 *   @return
	 */
	@RequestMapping(value = "/admin/{adminNo}", method = RequestMethod.GET)
	public ResponseEntity<?> getAdminById(@PathVariable("adminNo") String adminNo) {
		Response res = new Response();
		Admin admin = adminService.getById(adminNo); 
		res.setData(admin);
		return ResponseEntity.ok(res);
	}
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	public ResponseEntity<?> getUnitById(@RequestBody List<Integer> ids) {
		Response res = new Response();
		adminService.deleteByIds(ids); 
		res.setData(ids);
		return ResponseEntity.ok(res);
	}
}
