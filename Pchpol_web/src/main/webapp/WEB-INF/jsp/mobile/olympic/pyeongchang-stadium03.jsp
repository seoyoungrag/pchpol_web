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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-pyeongchang02.png" alt="알펜시아 스키점프 센터" />
              </figure>
              <figcaption>알펜시아 스키점프 센터</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-nordic02.png" alt="노르딕 복합" /><span>노르딕 복합</span>
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-skijump.png" alt="스키점프" /><span>스키점프</span>
                <img class="w20" src="${pageContext.request.contextPath}/mobile/resources/images/ico-snowboard.png" alt="스노보드" /><span>스노보드</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;스키점프</h4>
                <p>남자 3 세부종목 : 노멀힐 남자 개인 / 라지힐 남자 개인/ 남자 팀</p>
                <p>여자 1 세부종목 : 노멀힐 여자 개인</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang-stadium03.jpg" alt="알펜시아 스키점프 센터 지도" /> --%>
                <p>주소 : 강원도 평창군 대관령면 수하리 240-19</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
