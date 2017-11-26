<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="mobile-web-app-capable" content="yes">
<title>::평창올림픽 동원경찰관::</title>
<link href="../resources/css/common.css" rel="stylesheet" type="text/css">
<link href="../resources/css/style.css" rel="stylesheet" type="text/css">
<script src="../resources/js/jquery-1.12.1.min.js"></script>
<script src="../resources/js/common.js"></script>
</head>

<body>
<div class="wrap_hide">   
    <div class="wrap">
        <!-- // id : header -->
        <header id ="header">
            <div class="cover"></div>
            <a class="h_btn" href="../index.html"><img src="../resources/images/ico-btn-home.png" alt="홈으로" /></a>
            <h1><a href="../index.html">평창올림픽 동원경찰관</a></h1>
            <a href="#this" class="m_btn"><img src="../resources/images/ico-header-menu.png" alt="메뉴"></a>                      
            <div class="menu_wrap">
              <div class="m_top">
                    <span>상경</span> <span>한기석</span> <span>님</span>
                    <a class="" href="#this">Log-out</a>
                    <a class="home" href="../index.html"><img src="../resources/images/ico-btn-home.png" alt="홈으로" /></a>
                  </div>
                  <div class="m_gnb">
                    <li><a href="../notice/notice_list.html">공지사항</a></li>
                      <li><a href="../work/workinfo.html">근무정보</a></li>
                      <li>
                        <a class="de1" href="#this">숙영/급식</a>
                        <ul class="de2">
                          <li><a href="../camp/lodge.html">- 숙영시설</a></li>
                          <li><a href="../camp/meal.html">- 급식시설</a></li>
                        </ul>
                      </li>
                      <li>
                        <a class="de1"  href="#this">평창올림픽</a>
                        <ul class="de2" >
                          <li><a href="../olympic/schedule.html">- 경기일정</a></li>
                          <li><a href="">- 대회시설 정보</a></li>
                          <li><a href="">- 주변 편의시설</a></li>
                        </ul>
                      </li>
                      <li><a href="../report/report_list.html">상황보고</a></li>
                      <li><a href="../proposal/proposal_list.html">건의사항</a></li>
                  </div>
                  <div class="btn_call">
                    <a href="tel:123-1234"><img src="../resources/images/ico-login-call.png" alt="콜센터연결"/>콜센터 연결</a>
                  </div>
            </div>
            <!-- // 공지-->
            <aside>
              <a class="notice over-hidden" href="../notice/notice_view.html">
                <img src="../resources/images/ico-header-notice.png" alt="공지사항" />
                <span class="font-bold">[긴급]</span> 아이스하키 결승전 15:00 (A경기장)
              </a>
              <a class="alert"  href="../notice/notice_list.html"><img src="../resources/images/ico-header-alert.png" alt="공지 알람" /><span class="num">11</span></a>
            </aside>
            <!-- // 공지-->
        </header>
        <!-- // id : header -->

        
        <!-- //  : contents -->
        <section class="contents sub">

          <h2><a class="btn_back" href="javascript:history.back();"><img src="../resources/images/ico-btn-back.png" alt="뒤로가기" /></a>공지사항</h2>
          <div class="con">
 
            <div class="select-form">
              <select name="" id="">           
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
	                <c:set var="pageSize" value="10"/>
					<c:set var="root" value="${data.rows}"/>
					<c:set var="page" value="${data.page}"/>
					<c:set var="total" value="${data.total}"/>
					<c:set var="records" value="${data.records}"/>
					<c:set var="startNum" value="${(page - 1) * pageSize + 1 }"/>
					<c:forEach items="${root}" var="board" >
						<tr>
							<th>${startNum}</th>
			                    <td class="tit">
			                       <a href="notice_view.html">[${board.boardCategory }] ${board.boardTitle }</a>                 
			                    </td>
					          	<td class="date">
						          	${board.boardWriteDt }
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
                <input type="text" />
                <button><img src="../resources/images/ico-btn-search.png" alt="검색" /></button>
              </div>
            </div>

            <!-- paging -->
            <div class="wrap_pager txt-center">
              <a href="#this" ><img src="../resources/images/ico-arrow-left.png" alt="이전 페이지" /></a>
              <c:forEach begin="0" end="${Long.parseLong(total) }" varStatus="num">
			    <c:if test="${page eq num}">
			        <a href="#this" class="num on">
			    </c:if>
			    <c:if test="${page nq num}">
			        <a href="#this" class="num">
			    </c:if>
			    <span>${num }</span></a>
			</c:forEach>
              <a href="#this" class="btn_next"><img src="../resources/images/ico-arrow-right.png" alt="다음 페이지" /></a>
            </div>
            <!-- // paging -->

            <script type="text/javascript">
                $(function(){
                  paging();
                });
            </script>

          </div>
        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
