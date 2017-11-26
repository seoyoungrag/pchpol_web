package com.dwebs.pchpol.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.dwebs.pchpol.attach.service.AttachService;
import com.dwebs.pchpol.board.service.BoardService;
import com.dwebs.pchpol.common.controller.BaseController;
import com.dwebs.pchpol.common.util.FileHandler;
import com.dwebs.pchpol.common.vo.JQGridVO;
import com.dwebs.pchpol.common.vo.PagingVO;
import com.dwebs.pchpol.common.vo.Response;
import com.dwebs.pchpol.model.Admin;
import com.dwebs.pchpol.model.Attach;
import com.dwebs.pchpol.model.Board;
import com.dwebs.pchpol.model.Unit;

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
public class BoardController extends BaseController {

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	@Autowired
	@Qualifier("attachService")
	private AttachService attachService;

	/**
	 * <PRE>
	 * 1. MethodName : boardListPage
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 6:44:20
	 * </PRE>
	 *   @return ModelAndView
	 *   @param boardType
	 *   @return
	 */
	@RequestMapping(value = "/board/list.do")
	public ModelAndView boardListPage(
			@RequestParam(value="boardType", required=false, defaultValue="normal") String boardType
			) {
		return new ModelAndView("board/boardList_"+boardType);
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
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ResponseEntity<?> getAdminList(
			@RequestParam(value="listType", required=false, defaultValue="jqgrid") String listType, 
			@ModelAttribute Board board, 
			HttpServletRequest req){
		Response res = new Response();
		List<Board> list = new ArrayList<Board>();
		int totCnt =  0;
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPaging(req);
		try {
			list = boardService.getList(pagingVO, board); //조회 결과
			totCnt = boardService.getTotCnt(pagingVO, board); //전체 페이지의 게시물 수
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new Response(false, e.getMessage()));
		} 
		pagingVO.setListCount(totCnt);
		pagingVO.setLastPage();
		if(listType.equals("jqgrid")){
			JQGridVO<Board> jqGridData = new JQGridVO<Board>();
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
	 * 1. MethodName : boardView
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 6:44:16
	 * </PRE>
	 *   @return ModelAndView
	 *   @param type
	 *   @param boardNo
	 *   @return
	 */
	@RequestMapping(value = "/board/view.do")
	public ModelAndView boardView(
			@RequestParam(value="type", required=true, defaultValue="view") String type,
			@RequestParam(value="boardType", required=false, defaultValue="normal") String boardType,
			@RequestParam(value="boardNo", required=false) String boardNo) {
		ModelAndView mav = new ModelAndView("board/boardView_"+boardType);
		mav.addObject("type",type);
		mav.addObject("boardType",boardType);
		mav.addObject("boardNo",boardNo);
		return mav;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getBoardById
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 
	 * 4. 작성자    : yrseo
	 * 5. 작성일    : 2017. 10. 28. 오후 7:11:22
	 * </PRE>
	 *   @return ResponseEntity<?>
	 *   @param boardNo
	 *   @return
	 */
	@RequestMapping(value = "/board/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<?> getBoardById(@PathVariable("boardNo") String boardNo) {
		Response res = new Response();
		Board board = boardService.getById(boardNo);
		res.setData(board);
		return ResponseEntity.ok(res);
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
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public ResponseEntity<?> insertOrUpdateBoard(
			HttpServletRequest request, 
			@ModelAttribute Board board,
			@RequestParam(value="fileList", required=false) String fileListArr
			
			) throws Exception {
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("admin");
		board.setAdmin(admin);
		if(admin==null){
			if(board.getBoardType().equals("notice")){
				Admin user = (Admin)session.getAttribute("user");
				//TODO 테스트
				if(user==null){
					user = new Admin();
					user.setAdminNo(2);
				}
				board.setAdmin(user);
			}else{
				Unit user = (Unit)session.getAttribute("user");
				//TODO 테스트
				if(user==null){
					user = new Unit();
					user.setUnitNo(2);
				}
				board.setUnit(user);
			}
		}
		Response res = new Response();
		boardService.insertOrUpdate(board);

		List<Attach> attaches = setFileList(request, fileListArr, board);
		board.setAttaches(attaches);
		attachService.insertBoardAttaches(attaches);
		
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
				}
			}
		}
		return attaches;
	}
}
