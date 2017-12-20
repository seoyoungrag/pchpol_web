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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-pyeongchang01.png" alt="알펜시아 크로스컨트리 센터" />
              </figure>
              <figcaption>알펜시아 크로스컨트리 센터</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-biathlon.png" alt="바이애슬론" /><span>크로스 컨트리 스키</span>
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-nordic.png" alt="노르딕 복합" /><span>노르딕 복합</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;크로스컨트리 스키</h4>
                <p>남자 6 세부종목 : 15km / 15km+15km 스키애슬론 / 스프린트 / 팀 스프린트 / 50km 단체출발 / 4 * 10km 남자계주</p>
                <p>여자 6 세부종목 : 10km / 7.5km+7.5km 스키애슬론 / 스프린트 / 팀 스프린트 / 30km 단체출발 / 4 * 5km 남자계주</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang-stadium02.jpg" alt="알펜시아 크로스컨트리 센터 지도" /> --%>
                <p>주소 : 강원도 평창군 대관령면 수하리 425</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
