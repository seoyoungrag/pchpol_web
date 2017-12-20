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
                  <img src="${pageContext.request.contextPath}/mobile/resources/images/img-gangneung02.png" alt="강릉 컬링 센터" />
              </figure>
              <figcaption>강릉 컬링 센터</figcaption>
              <div class="event">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-cur.png" alt="컬링" /><span>컬링</span>
              </div>
              <div class="event_detail">
                <h3>경기종목</h3>
                <h4>&middot;컬링</h4>
                <p>컬링 : 남 / 여</p>
                <p>혼성 경기</p>
              </div>
              <div class="map">
                <%-- <img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung-stadium02.jpg" alt="강릉 컬링 센터 지도" /> --%>
                <p>주소 : 강원도 강릉시 교동 632</p>
              </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>
</body>
</html>
