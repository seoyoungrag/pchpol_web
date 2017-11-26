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
            <a class="txt-right on" href="${pageContext.request.contextPath}/mobile/camp/lodge.do">숙영정보</a>
            <a class="txt-left" href="${pageContext.request.contextPath}/mobile/camp/meal.do">급식정보</a>
          </div>

          <div class="con board">
            <c:choose>
            <c:when test="${!empty tpwf}">
            
			<c:set var="startDt" value="${tpwf.facilityMobilStartDt}"/>
			<c:set var="endDt" value="${tpwf.facilityMobilEndDt}"/>
			<c:forEach items="${tpwf.bedFacility}" var="facility" >
            <div class="table table_style02 ">
              <table>
                <caption>숙영/급식 정보 표</caption>
                <tbody>
                  <tr>
                    <th>소속</th>
                    <td colspan="2"  class="txt-light">
	                    <c:choose>
	                    <c:when test="${empty unit}">
	                    	${adminDept }(근무자가 아닙니다.)
	                    </c:when>
	                    <c:otherwise>
	                       ${unit.code1.code1depth }&nbsp;${unit.code1.code2depth }&nbsp;${unit.code1.code3depth }&nbsp;${unit.code1.code4depth }&nbsp;${unit.code1.code5depth }
	                    </c:otherwise>
	                    </c:choose>
                    </td>
                  </tr>
                  <tr>
                    <th>기간</th>
                    <td colspan="2"  class="txt-light"><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${startDt }" /> ~ <fmt:formatDate pattern="yyyy년 MM월 dd일" value="${endDt }" /></td>
                  </tr>
                  <tr>
                    <th rowspan="5">정보</th>
                    <td class="txt-center txt-navy border-right pad10">숙소명</td>
                    <td class="txt-light">${facility.facilityName }</td>
                  </tr>
                  <tr>
                    <td class="txt-center txt-navy border-right pad10">주소</td>
                    <td class="txt-light">${facility.facilityAddrZip }&nbsp;${facility.facilityAddrJuso }&nbsp;${facility.facilityAddrSebu }</td>
                  </tr>
                  <tr>
                    <td colspan="2">
                      <p class="txt-navy"> ＜상세내용＞</p>
                      <p class="txt-light">
						<textarea readonly style="border:0px;width:100%;height:150px;">${facility.facilityShelterOther }</textarea>
                      </p>
                    </td>
                  </tr>
                  <tr>
                    <td class="txt-center txt-navy border-right pad10">담당자</td>
                    <td class="txt-light">${facility.facilityChargerName}</td>
                  </tr>
                  <tr>
                    <td class="txt-center txt-navy border-right pad10">연락처</td>
                    <td class="txt-light">${facility.facilityChargerTel}</td>
                  </tr>
                </tbody>
              </table>
            </div>
            </c:forEach>
            </c:when>
            <c:otherwise>
               	<p>배치된 숙소가 없습니다.</p>
            </c:otherwise>
            </c:choose>
          </div>

        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
