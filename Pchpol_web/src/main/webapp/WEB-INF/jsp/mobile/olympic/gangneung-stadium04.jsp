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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-gangneung04.png" alt="강릉 아이스 아레나" />
              </figure>
              <figcaption>강릉 아이스 아레나</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-figure-skating.png" alt="피겨 스케이팅" /><span>피겨 스케이팅</span>
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-short-track.png" alt="쇼트트랙 스피드 스케이팅" /><span>쇼트트랙 스피드 스케이팅</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;피겨 스케이팅</h4>
                <p>남자 1 세부종목 : 싱글</p>
                <p>여자 1 세부종목 : 싱글</p>
                <p>혼성 2 세부종목 : 페어 / 아이스 댄스</p>
                <p>단체 1 세부종목 : 팀 이벤트</p>
                <h4>&middot;쇼트트랙 스피드 스케이팅</h4>
                <p>남자 4 세부종목 : 500m / 1,000m / 1,500m / 계주 5,000m</p>
                <p>여자 4 세부종목 : 500m / 1,000m / 1,500m / 계주 3,000m</p>
              </div>
              <div class="map">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung-stadium04.jpg" alt="강릉 아이스 아레나 지도" />
                <p>주소 : 강원도 강릉시 교동 468-42</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
