<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">
<head>
	<jsp:include page="/mobile/head.jsp" flush="false" />
</head>
<body>
<c:set var="pageSize" value="10"/>
<c:set var="root" value="${data.rows}"/>
<c:set var="page" value="${data.page}"/>
<c:set var="total" value="${data.total}"/>
<c:set var="records" value="${data.records}"/>
<c:set var="startNum" value="${(page - 1) * pageSize + 1 }"/>
<script>
var page = '${page}';
var rows = '${pageSize}';
</script>
<div class="wrap_hide">   
    <div class="wrap">
        <!-- // id : header -->
        <header id ="header">
			<jsp:include page="/mobile/header.jsp" flush="false" />
        </header>
        <!-- // id : header -->
        
        <!-- //  : contents -->
        <section class="contents sub">

          <h2><a class="btn_back" href="javascript:history.back();"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-back.png" alt="뒤로가기" /></a>공지사항</h2>
          <div class="con">
 
            <div class="select-form">
              <select onchange="javascrtip:areaChange(this);" id="area">           
                <option value="">전체</option>
                <option value="평창">평창</option>
                <option value="정선">정선</option>
                <option value="강릉">강릉</option>
              </select>
            </div>

            <!--20171030 수정 -->
            <div class="table table_style01 report-table">
              <table>
                <caption>공지사항 리스트 표</caption>
                <colgroup>
                  <col width="13%;"/>
                  <col width="65%;"/>
                  <col width="17%;"/>
                </colgroup>
                <tbody>
					<c:forEach items="${root}" var="board" >
						<tr>
							<th>${startNum}</th>
		                    <td class="tit" boardNo="${board.boardNo }">
								<a href="#" onclick="javascript:goBoard(this);">[${board.boardCategory }] ${board.boardTitle }</a>                 
		                    </td>
				          	<td class="date">
				          		<fmt:formatDate pattern = "yyyy-MM-dd" value = "${board.boardWriteDt}" />
				          	</td>
		            	</tr>
		            	<c:set var="startNum" value="${startNum+1}"/>
					</c:forEach>
                </tbody>
              </table>
            </div>
             <!--20171030 수정 -->

            <div class="txt-center">
              <div class="search-form">
                <input type="text" id="searchWord" value="${pagingVO.searchWord }"/>
                <button onclick="javascript:search(this);"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-search.png" alt="검색" /></button>
              </div>
            </div>

            <!-- paging -->
            <c:set var="href" value="./list.do?sidx=boardWriteDt&sord=desc&rows=${pageSize }"/>
            <div class="wrap_pager txt-center">
            	<c:if test="${page ne 1}">
              		<a href="${href }&page=${page-1 }" ><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-arrow-left.png" alt="이전 페이지" /></a>
              	</c:if>
				<c:forEach begin="1" end="${total }" varStatus="status">
				    <c:if test="${page eq status.count}">
				        <a href="${href }&page=${status.count }" class="num on">
				    </c:if>
				    <c:if test="${page ne status.count}">
				        <a href="${href }&page=${status.count }" class="num">
				    </c:if>
				    <span>${status.count }</span></a>
				</c:forEach>
				<c:if test="${page != '1' && page ne total}">
              		<a href="${href }&page=${page+1 }" class="btn_next"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-arrow-right.png" alt="다음 페이지" /></a>
              	</c:if>
            </div>
            <!-- // paging -->

            <script type="text/javascript">
                $(function(){
                  paging();
                  $("#area").val("${searchBoard.boardArea}");
                });
            </script>

          </div>
        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
