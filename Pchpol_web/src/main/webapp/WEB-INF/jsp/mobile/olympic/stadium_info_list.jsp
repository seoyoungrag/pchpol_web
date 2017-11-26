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

          <div class="con">
            <h3 class="txt-medium">경기장</h3>
            <div class="stadium">
              <h4>평창 마운틴 클러스터</h4>
              <ol>
                <li><a href="./stadiums.do?view=pyeongchang-stadium01"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang01.png" alt="1. 알펜시아 바이애슬론 센터" />1. 알펜시아 바이애슬론 센터</a></li>
                <li><a href="./stadiums.do?view=pyeongchang-stadium02"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang02.png" alt="2. 알펜시아 크로스컨트리 센터" />2. 알펜시아 크로스컨트리 센터</a></li>
                <li><a href="./stadiums.do?view=pyeongchang-stadium03"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang03.png" alt="3. 알펜시아 스키점프 센터" />3. 알펜시아 스키점프 센터</a></li>
                <li><a href="./stadiums.do?view=pyeongchang-stadium04"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang04.png" alt="4. 올림픽 슬라이딩 센터" />4. 올림픽 슬라이딩 센터</a></li>
                <li><a href="./stadiums.do?view=pyeongchang-stadium05"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang05.png" alt="5. 휘닉스 스노 경기장" />5. 휘닉스 스노 경기장</a></li>
                <li><a href="./stadiums.do?view=pyeongchang-stadium06"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang06.png" alt="6. 정선 알파인 경기장" />6. 정선 알파인 경기장</a></li>
                <li><a href="./stadiums.do?view=pyeongchang-stadium07"><img src="${pageContext.request.contextPath}/mobile/resources/images/pyeongchang07.png" alt="7. 용평 알파인 경기장" />7. 용평 알파인 경기장</a></li>
              </ol>
            </div>
            <div class="stadium">
              <h4 class="pt10">강릉 코스탈 클러스터</h4>
              <ol>
                <li><a href="./stadiums.do?view=gangneung-stadium01"><img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung1.png" alt="1. 관동 하키 센터" />1. 관동 하키 센터</a></li>
                <li><a href="./stadiums.do?view=gangneung-stadium02"><img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung2.png" alt="2. 강릉 컬링 센터" />2. 강릉 컬링 센터</a></li>
                <li><a href="./stadiums.do?view=gangneung-stadium03"><img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung3.png" alt="3. 강릉 하키 센터" />3. 강릉 하키 센터</a></li>
                <li><a href="./stadiums.do?view=gangneung-stadium04"><img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung4.png" alt="4. 강릉 아이스 아레나" />4. 강릉 아이스 아레나</a></li>
                <li><a href="./stadiums.do?view=gangneung-stadium05"><img src="${pageContext.request.contextPath}/mobile/resources/images/gangneung5.png" alt="5. 강릉 스피드 스케이팅 경기장" />5. 강릉 스피드 스케이팅 경기장</a></li>
              </ol>
            </div>
            <div class="stadium">
              <h4 class="pt10">비경기장</h4>
              <ol>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium01.png" alt="1. 국제 방송 센터" />1. 국제 방송 센터</a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium02.png" alt="2. 메인 프레스 센터" />2. 메인 프레스 센터</a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium03.png" alt="3. 평창 올림픽 플라자" />3. 평창 올림픽 플라자</a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium04.png" alt="4. 평창 올림픽 스타디움" />4. 평창 올림픽 스타디움</a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium05.png" alt="5. 평창 올림픽 선수촌" />5. 평창 올림픽 선수촌</a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium06.png" alt="6. 강릉 올림픽 파크" />6. 강릉 올림픽 파크</a></li>
                <li><a href="#"><img src="${pageContext.request.contextPath}/mobile/resources/images/no_stadium07.png" alt="7. 강릉 올림픽 선수촌" />7. 강릉 올림픽 선수촌</a></li>
              </ol>
            </div>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
