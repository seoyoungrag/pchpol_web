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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-gangneung05.png" alt="강릉 스피드 스케이팅 경기장" />
              </figure>
              <figcaption>강릉 스피드 스케이팅 경기장</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-speed-skating.png" alt="스피드 스케이팅" /><span>스피드 스케이팅</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;스피드 스케이팅</h4>
                <p>남자 7 세부종목 : 500m / 1,000m / 1,500m / 5,000m / 10,000m / 팀 추월 / 매스스타트</p>
                <p>여자 7 세부종목 : 500m / 1,000m / 1,500m / 3,000m / 5,000m / 팀 추월 / 매스스타트</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung-stadium05.jpg" alt="강릉 스피드 스케이팅 경기장 지도" /> --%>
                <p>주소 : 강원도 강릉시 포남동 251</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
