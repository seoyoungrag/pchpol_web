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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-pyeongchang04.png" alt="정선 알파인 경기장" />
              </figure>
              <figcaption>정선 알파인 경기장</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-alpine-ski.png" alt="알파인 스키" /><span>알파인 스키</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;알파인 스키</h4>
                <p>남자 3 세부종목 : 활강 / 슈퍼대회전 / 복합</p>
                <p>여자 3 세부종목 : 활강 / 슈퍼대회전 / 복합</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang-stadium06.jpg" alt="정선 알파인 경기장 지도" /> --%>
                <p>주소 : 강원도 정선군 북평면 오대천로 625</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
