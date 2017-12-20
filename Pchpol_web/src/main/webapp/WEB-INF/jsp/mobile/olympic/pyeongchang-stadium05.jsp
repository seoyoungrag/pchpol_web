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
<div class="wrap_hide">   
    <div class="wrap">
		<!-- // id : header -->
		<header id="header">
			<jsp:include page="/mobile/header.jsp" flush="false" />
		</header>
        <!-- // id : header -->


        
        <!-- //  : contents -->
        <section class="contents sub">

          <h2><a class="btn_back" href="javascript:history.back();"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-back.png" alt="뒤로가기" /></a>대회시설정보</h2>

          <div class="con stadium_view">
               <figure>
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-pyeongchang04.png" alt="휘닉스 스노 경기장" />
              </figure>
              <figcaption>휘닉스 스노 경기장</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-freeski.png" alt="프리스타일 스키" /><span>프리스타일 스키</span>
                <img class="w20" src="${pageContext.request.contextPath}/mobile/resources/images/ico-snowboard02.png" alt="스노보드" /><span>스노보드</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;프리스타일 스키</h4>
                <p>남자 5 세부종목 : 에어리얼 / 모글 / 스키 크로스 / 스키 하프파이프 / 스키 슬로프스타일</p>
                <p>여자 5 세부종목 : 에어리얼 / 모글 / 스키 크로스 / 스키 하프파이프 / 스키 슬로프스타일</p>
                <h4>&middot;스노보드</h4>
                <p>남자 4 세부종목 : 평행대회전 / 하프파이프 / 스노보드 크로스 / 슬로프스타일</p>
                <p>여자 4 세부종목 : 평행대회전 / 하프파이프 / 스노보드 크로스 / 슬로프스타일</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang-stadium05.jpg" alt="휘닉스 스노 경기장 지도" /> --%>
                <p>주소 : 강원도 평창군 봉평면 무이리 729-7</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
