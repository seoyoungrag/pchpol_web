package com.dwebs.pchpol.code.controller;

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

import com.dwebs.pchpol.code.service.CodeService;
import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.model.Code;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : CodeController.java
 * 3. Package  : com.dwebs.pchpol.code.controller
 * 4. Comment  : 
 * 5. 작성자   : yrseo
 * 6. 작성일   : 2017. 10. 11. 오후 1:12:13
 * </PRE>
 */
@RestController
public class CodeController extends BaseController {

	@Autowired
	@Qualifier("codeService")
	CodeService codeService;
	
	/**
	 * <PRE>
	 * 1. MethodName : getCodeList
	 * 2. ClassName  : CodeController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 11. 오후 3:57:39
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param category
	 *   @return
	 */
	@RequestMapping(value = "/code/list/{category}", method = RequestMethod.GET)
	public ResponseEntity<?> getCodeList(@PathVariable("category") String category) {
		Response res = new Response();
		List<Code> codes= codeService.getCodeListByCategory(category); 
		res.setData(codes);
		return ResponseEntity.ok(res);
	}

	/**
	 * <PRE>
	 * 1. MethodName : getCodeNo
	 * 2. ClassName  : CodeController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 17. 오후 2:47:50
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param code
	 *   @return
	 */
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public ResponseEntity<?> getCodeNo(Code code) {
		Response res = new Response();
		Code resultCode = codeService.getCode(code);
		res.setData(resultCode);
		return ResponseEntity.ok(res);
	}
	

	@RequestMapping(value = "/code/list", method = RequestMethod.GET)
	public ResponseEntity<?> getCodeList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType,
			@ModelAttribute Code code,
			HttpServletRequest req){
		Response res = new Response();
		List<Code> list = new ArrayList<Code>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = codeService.getCodeListByCode(pagingVO, code); //조회 결과
			totCnt = codeService.getTotCntByCode(pagingVO, code); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<Code> jqGridData = new JQGridVO<Code>();
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
