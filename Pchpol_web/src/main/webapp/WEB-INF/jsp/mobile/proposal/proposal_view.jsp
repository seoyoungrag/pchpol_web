<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ko">
<head>
	<jsp:include page="/mobile/head.jsp" flush="false" />
</head>

<body>
<script>
var boardNo = '${board.boardNo}';
var curBoardSeq = Number('${curBoardSeq}');
var page = '${page}';
var rows = '${rows}';

function goModify(){
	var url = "./modify.do?curBoardNo=${curBoardNo}&prevBoardNo=${prevBoardNo}&nextBoardNo=${nextBoardNo}&prevBoardTitle="+encodeURIComponent("${prevBoardTitle}")+"&nextBoardTitle="+encodeURIComponent("${nextBoardTitle}");
	url += '&page=${page}&rows=${rows}&sidx=boardWriteDt&sord=desc&boardType=normal&curBoardSeq=${curBoardSeq}';
	location.href=url;	
}

</script>
	<div class="wrap_hide">
		<div class="wrap">
			<!-- // id : header -->
			<header id="header">
				<jsp:include page="/mobile/header.jsp" flush="false" />
			</header>
			<!-- // id : header -->

			<!-- //  : contents -->
			<section class="contents sub">
				<h2>
					<a class="btn_back" href="javascript:history.back();">
					<img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-back.png" alt="뒤로가기" /></a>
					건의사항
				</h2>
				<div class="con board">

					<div class="board_view">
						<dl class="tittle">
							<dd>
								<span class="table_cell ">
									<p class="tit">
										<span>${board.boardTitle }</span>
									</p>
									<p class="date">
										<fmt:formatDate pattern="yyyy-MM-dd" value="${board.boardWriteDt}" />
									</p>
								</span>
							</dd>
						</dl>

						<div class="content">
						<div readonly style="min-height:200px;border: 1px solid #e0e0e0;overflow: auto;">${board.boardContent }</div>
						
						</div>
						<c:if test="${fn:length(board.attaches)!=0}">
							<div class="attach">
								<span class="tit">첨부파일</span>
								<c:forEach items="${board.attaches}" var="attach">
									<a class="file" href="#" onclick="javascript:fileDownload('${attach.attachServerName }','${attach.attachOriName }')">${attach.attachOriName }</a>
									<%-- <a class="file" href="${pageContext.request.contextPath}/resources/files/${attach.attachServerName }" download='${attach.attachOriName }')">${attach.attachOriName }</a> --%>
								</c:forEach>
							</div>
						</c:if>
              			<div class="board_btn board_3btn">
							<a href="#" onclick="javascript:toggleComment();">댓글</a> 
							<a class="navy" href="#" onclick="javascript:goList();">목록</a>
                  			<a class="blue" href="#" onclick="javascript:goModify();">수정</a>
						</div>
					</div>
					<c:set var="commentCnt" value="0" />
					<c:forEach items="${board.comments}" var="comment">
						<c:if
							test="${comment.commentDeleteYn != 'Y' && comment.comment.commentNo == null}">
							<c:set var="commentCnt" value="${commentCnt + 1 }" />
						</c:if>
					</c:forEach>
					<div class="comment">
						<p class="tit">
							댓글
							<c:if test="${commentCnt!=0}">
								<span class="numbering">[${commentCnt}]</span>
							</c:if>
						</p>
						<span class="line"></span>
						<div class="write group" style="display:none;">
							<div class="write_box">
								<textarea name="commentContent" placeholder="댓글을 입력해 주세요"></textarea>
								<button onclick="javascript:regComment(this);">등록</button>
							</div>
						</div>
						<div class="list">
							<ul>
								<c:forEach items="${board.comments}" var="comment">
									<c:if
										test="${comment.commentDeleteYn != 'Y' && comment.comment.commentNo == null}">
										<li><c:choose>
												<c:when test="${comment.commentWriterType == 'ADMIN'}">
													<p class="info">${comment.admin.adminName }
												</c:when>
												<c:otherwise>
													<p class="info">${comment.unit.code2.code1depth }&nbsp;${comment.unit.unitName }
												</c:otherwise>
											</c:choose> 
										<span class="date"> 
											<fmt:formatDate pattern="yyyy.MM.dd kk:mm:ss" value="${comment.commentWriteDt}" />
										</span>
											</p>
											<p class="txt">${comment.commentContent }</p>
											<div class="comment-function">
												<a class="btn_comment" href="#" onclick="javascript:showWriteBox(this);">댓글</a>
												<c:choose>
													<c:when test="${comment.commentWriterType == 'ADMIN'}">
														<c:if test="${admin.adminNo eq comment.admin.adminNo }">
															<a class="btn_comment" href="#" onclick="javascript:commentUpdatable(this);">수정</a>
															<a class="btn_del" href="#">
																<img onclick="javascript:deleteComment(this);" src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-replydel.png" alt="댓글삭제">
															</a>
														</c:if>
													</c:when>
													<c:otherwise>
														<c:if test="${unit.unitNo eq comment.unit.unitNo }">
															<a class="btn_comment" href="#" onclick="javascript:commentUpdatable(this);">수정</a>
															<a class="btn_del" href="#">
																<img onclick="javascript:deleteComment(this);" src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-replydel.png" alt="댓글삭제">
															</a>
														</c:if>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="write_box write" style="display: none;">
												<textarea name=commentContent parentCommentNo="${comment.commentNo }" placeholder="댓글을 입력해 주세요."></textarea>
												<button onclick="javascript:regComment(this);">등록</button>
											</div>
											<div class="write_box update" style="display: none;">
												<textarea commentNo="${comment.commentNo }" placeholder="댓글을 입력해 주세요.">${comment.commentContent }</textarea>
												<button onclick="javascript:updateComment(this);">수정</button>
											</div></li>
										<c:set var="parentCommentNo" value="${comment.commentNo }" />
										<c:forEach items="${comment.comments}" var="comment">
											<c:if test="${comment.commentDeleteYn != 'Y' }">
												<li class="reply">
													<c:choose>
														<c:when test="${comment.commentWriterType == 'ADMIN'}">
															<p class="info">${comment.admin.adminName }
														</c:when>
														<c:otherwise>
															<p class="info">${comment.unit.code2.code1depth }&nbsp;${comment.unit.unitName }
														</c:otherwise>
													</c:choose> 
													<span class="date"> 
														<fmt:formatDate pattern="yyyy.MM.dd kk:mm:ss" value="${comment.commentWriteDt}" />
													</span>
													</p>
													<p class="txt">${comment.commentContent }</p>
													<div class="comment-function">
														<c:choose>
															<c:when test="${comment.commentWriterType == 'ADMIN'}">
																<c:if test="${admin.adminNo eq comment.admin.adminNo }">
																	<a class="btn_comment" href="#" onclick="javascript:commentUpdatable(this);">수정</a>
																	<a class="btn_del" href="#">
																		<img onclick="javascript:deleteComment(this);" src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-replydel.png" alt="댓글삭제">
																	</a>
																</c:if>
															</c:when>
															<c:otherwise>
																<c:if test="${unit.unitNo eq comment.unit.unitNo }">
																	<a class="btn_comment" href="#" onclick="javascript:commentUpdatable(this);">수정</a>
																	<a class="btn_del" href="#">
																		<img onclick="javascript:deleteComment(this);" src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-replydel.png" alt="댓글삭제">
																	</a>
																</c:if>
															</c:otherwise>
														</c:choose>
													</div>
													<div class="write_box update" style="display: none;">
														<textarea commentNo="${comment.commentNo }" parentCommentNo="${parentCommentNo }" placeholder="댓글을 입력해 주세요.">${comment.commentContent }</textarea>
														<button onclick="javascript:updateComment(this);">수정</button>
													</div>
													</li>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					<ul class="list_btn_wrap">
						<c:if test="${prevBoardNo != '0' }">
						<li class="prev">
							<span>
								<img src="${pageContext.request.contextPath}/mobile/resources/images/ico-board-prev.png" alt="이전글">
							</span> 
							<a href="#" onclick="javascript:goAnotherBoard('${prevBoardNo }','prev')">${prevBoardTitle }</a>
						</li>
						</c:if>
						<c:if test="${nextBoardNo != '0' }">
						<li class="next">
							<span>
								<img src="${pageContext.request.contextPath}/mobile/resources/images/ico-board-next.png" alt="다음글">
							</span> <a href="#" onclick="javascript:goAnotherBoard('${nextBoardNo }','next')">${nextBoardTitle }</a>
						</li>
						</c:if>
					</ul>
				</div>
		</div>
		</section>
		<!-- //  : contents -->
	</div>
	</div>

</body>
</html>
