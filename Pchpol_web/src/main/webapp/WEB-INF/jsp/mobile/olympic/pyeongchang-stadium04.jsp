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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-pyeongchang03.png" alt="올림픽 슬라이딩 센터" />
              </figure>
              <figcaption>올림픽 슬라이딩 센터</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-bobsleigh.png" alt="봅슬레이" /><span>봅슬레이</span>
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-luge.png" alt="루지" /><span>루지</span>
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-skeleton.png" alt="스켈레톤" /><span>스켈레톤</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;루지</h4>
                <p>남자 1 세부종목 : 싱글</p>
                <p>여자 1 세부종목 : 싱글</p>
                <p>남자 단체 1 세부종목 : 더블</p>
                <p>혼성 단체 : 팀 계주</p>
                <h4>&middot;봅슬레이</h4>
                <p>남자 2 세부종목 : 2인승 / 4인승</p>
                <p>여자 1 세부종목 : 2인승</p>
                <h4>&middot;스켈레톤</h4>
                <p>남자 1 세부종목 : 남자</p>
                <p>여자 1 세부종목 : 여자</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang-stadium04.jpg" alt="올림픽 슬라이딩 센터 지도" /> --%>
                <p>주소 : 강원도 평창군 대관령면 용산리 153-4</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
