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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-pyeongchang01.png" alt="알펜시아 바이애슬론 센터" />
              </figure>
              <figcaption>알펜시아 바이애슬론 센터</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-biathlon.png" alt="바이애슬론" /><span>바이애슬론</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;바이애슬론</h4>
                <p>남자 5 세부종목 : 개인 20km / 스프린트 10km / 추적12.5km/ 단체출발 15km / 계주 4 * 7.5km</p>
                <p>여자 5 세부종목 : 개인 15km / 스프린트 7.5km / 추적10km/ 단체출발 12.5km / 계주 4 * 6km 기타 1 세부종목 : 혼성계주</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang-stadium01.jpg" alt="알펜시아 바이애슬론 센터 지도" /> --%>
                <p>주소 : 강원도 평창군 대관령면 수하리 425</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
