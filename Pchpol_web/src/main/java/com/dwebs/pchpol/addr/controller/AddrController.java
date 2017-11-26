/**
 * 0. Project  : 평창올림픽 동원경찰 업무시스템
 *
 * 1. FileName : AddrController.java
 * 2. Package : com.dwebs.pchpol.addr
 * 3. Comment : 
 * 4. 작성자  : yrseo
 * 5. 작성일  : 2017. 10. 23. 오전 1:49:14
 * 6. 변경이력 : 
 *                    이름     : 일자          : 근거자료   : 변경내용
 *                   ------------------------------------------------------
 *                    yrseo : 2017. 10. 23. :            : 신규 개발.
 */
package com.dwebs.pchpol.addr.controller;

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

import com.dwebs.pchpol.addr.service.AddrService;
import com.dwebs.pchpol.addr.vo.Addr;
import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : AddrController.java
 * 3. Package  : com.dwebs.pchpol.addr
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 23. 오전 1:49:14
 * </PRE>
 */
@RestController
public class AddrController extends BaseController {

	@Autowired
	@Qualifier("addrService")
	AddrService addrService;
	
	/**
	 * <PRE>
	 * 1. MethodName : findAddrPage
	 * 2. ClassName  : AddrController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 2:30:43
	 * </PRE>
	 *   @return ModelAndView
	 *   @param area
	 *   @param zip
	 *   @return
	 */
	@RequestMapping(value = "addr/findAddr.do")
	public ModelAndView findAddrPage(
			@RequestParam(value="area") String area,
			@RequestParam(value="zip") String zip
			) {
		ModelAndView mav = new ModelAndView("findAddr");
		mav.addObject("area",area);
		mav.addObject("zip",zip);
		return mav;
	}

	/**
	 * <PRE>
	 * 1. MethodName : findAddr
	 * 2. ClassName  : AddrController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 23. 오전 2:30:41
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param listType
	 *   @param addr
	 *   @param req
	 *   @return
	 */
	@RequestMapping(value = "addr/findAddr", method = RequestMethod.GET)
	public ResponseEntity<?> findAddr(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute Addr addr,
			HttpServletRequest req){
		Response res = new Response();
		List<Addr> list = new ArrayList<Addr>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = addrService.getAddrList(pagingVO, addr);
			totCnt = addrService.getTotCntAddr(pagingVO, addr); 
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<Addr> jqGridData = new JQGridVO<Addr>();
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
}
