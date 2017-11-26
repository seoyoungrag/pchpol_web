<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<jsp:include page="/mobile/head.jsp" flush="false" />
</head>
<body>
<div class="wrap_hide">
    
    <div class="wrap">
    	
        <!-- // id : header -->
        <header id ="header">
			<jsp:include page="/mobile/header.jsp" flush="false" />
        </header>
        <!-- // id : header -->
        
        <section class="contents main">
          <h2>QUICK MENU</h2>
          <div class="quick_m">
            <ul class="group"> 
              <li>
                <figure><a href="${pageContext.request.contextPath}/mobile/notice/list.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-notice.png" alt="공지사항" /></a></figure>
                <figcaption>공지사항 </figcaption>
              </li>
              <li>
                <figure><a href="${pageContext.request.contextPath}/mobile/work/workinfo.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-workinfo.png" alt="근무정보" /></a></figure>
                <figcaption>근무정보</figcaption>
              </li>
              <li>
                <figure><a href="${pageContext.request.contextPath}/mobile/camp/lodge.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-meal.png" alt="숙영/급식" /></a></figure>
                <figcaption>숙영/급식</figcaption>
              </li>
              <li>
                <figure><a href="${pageContext.request.contextPath}/mobile/olympic/schedule.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-olympic.png" alt="평창올림픽" /></a></figure>
                <figcaption>평창올림픽</figcaption>
              <li>
                <figure><a href="${pageContext.request.contextPath}/mobile/report/list.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-report.png" alt="상황보고" /></a></figure>
                <figcaption>상황보고</figcaption>
              </li>
              <li>
                <figure><a href="${pageContext.request.contextPath}/mobile/proposal/list.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-recommendation.png" alt="건의사항" /></a></figure>
                <figcaption>건의사항</figcaption>
              </li>
            </ul>
          </div>
        </section>

    </div>
</div>

</body>
</html>
