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

          <h2><a class="btn_back" href="javascript:history.back();"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-back.png" alt="뒤로가기" /></a>숙영/급식 정보</h2>
          <div class="link_btn">
            <a class="txt-right" href="${pageContext.request.contextPath}/mobile/camp/lodge.do">숙영정보</a>
            <a class="txt-left on" href="${pageContext.request.contextPath}/mobile/camp/meal.do">급식정보</a>
          </div>

          <div class="con box_con">
            <ul>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/pyeongchang-alpensia.pdf" download class="letterP"><span>평창<br/>(알펜시아 인근)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/pyeongchang-phoenix.pdf" download class="letterP"><span>평창<br/>(휘닉스파크 인근)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/pyeongchang-accommodation.pdf" download class="letterP"><span>평창<br/>(숙소별 주변)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/gangneung-olympic-park.pdf" download class="letterG"><span>강릉<br/>(올림픽파크 인근)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/gangneung-kwandong-hockey.pdf" download class="letterG"><span>강릉<br/>(관동하키센터 인근)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/gangneung-etc.pdf" download class="letterG"><span>강릉<br/>(기타)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/gangneung-accommodation.pdf" download class="letterG"><span>강릉<br/>(숙소별 주변)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/jeongseon-accommodation.pdf" download class="letterJ"><span>정선<br/>(숙소별 주변)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/sokcho-accommodation.pdf" download class="letterS"><span>속초<br/>(숙소별 주변)</span></a>
              </li>
              <li>
                <a href="${pageContext.request.contextPath}/mobile/resources/download/hoengseong-accommodation.pdf" download class="letterH"><span>횡성<br/>(숙소 주변)</span></a>
              </li>
            </ul>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
