package com.dwebs.pchpol.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dwebs.pchpol.code.service.CodeService;
import com.dwebs.pchpol.common.controller.BaseController;
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

	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public ResponseEntity<?> getCodeNo(Code code) {
		Response res = new Response();
		Code resultCode = codeService.getCode(code);
		res.setData(resultCode);
		return ResponseEntity.ok(res);
	}
}
