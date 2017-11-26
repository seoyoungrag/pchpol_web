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

          <h2><a class="btn_back" href="javascript:history.back();"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-back.png" alt="뒤로가기" /></a>근무정보</h2>
          <div class="con board">
 						
			<div class="table table_style02">
              <table>
                <caption>근무정보 표</caption>
                <tbody>
                  <tr>
                    <th><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-work-name.png" alt="소속정보" /></th>
                    <td>
                    <c:choose>
                    <c:when test="${empty unit}">
                    	<p>근무자가 아닙니다.</p>
                    </c:when>
                    <c:otherwise>
                       <p>${unit.code2.code1depth }&nbsp;${unit.unitName }</p>
                       <p class="belong">(${unit.code1.code1depth }&nbsp;${unit.code1.code2depth }&nbsp;${unit.code1.code3depth }&nbsp;${unit.code1.code4depth }&nbsp;${unit.code1.code5depth })</p>
                    </c:otherwise>
                    </c:choose>
                    </td>
                  </tr>
                  <tr>
                    <th><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-work-date.png" alt="날짜" /></th>
                    <td>
                    	<fmt:formatDate pattern="yyyy년 MM월 dd일" value="${mobilDate }" />
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <div class="datepicker-table">
              <table>
                <tr>
                  <th>날짜 검색</th>
                  <td class="txt-center"><input type="text" id="datepicker" readonly/></td>
                  <td class="txt-right"><label for="datepicker" class="btn_datepicker"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-datepicker.png" alt="달력열기" /></label></td>           
                </tr>
              </table>
            </div>

            <div class="table table_style02 work-place">
              <table>
                <caption>근무장소</caption>
                <tbody>
                  <tr>
                    <th>근무장소</th>
                    <td class="place">
                    <c:choose>
                    <c:when test="${!empty placemetnInfo.code2}">
                    	${placemetnInfo.code2.code1depth }&nbsp;${placemetnInfo.code2.code2depth }&nbsp;${placemetnInfo.code2.code3depth }&nbsp;/&nbsp;${placemetnInfo.code2.code4depth }
                    </c:when>
                    <c:otherwise>
                    	근무정보가 없습니다.
                    </c:otherwise>
                    </c:choose>
                    </td>
                  </tr>
                  <tr>
                    <th rowspan="24">근무편성</th>
                    <td>
                      <p class="time">00:00 ~ 01:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time1' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time1' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">01:00 ~ 02:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time2' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time2' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">02:00 ~ 03:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time3' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time3' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">03:00 ~ 04:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time4' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time4' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">04:00 ~ 05:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time5' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time5' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">05:00 ~ 06:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time6' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time6' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">06:00 ~ 07:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time7' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time7' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">07:00 ~ 08:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time8' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time8' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">08:00 ~ 09:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time9' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time9' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">09:00 ~ 10:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time10' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time10' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">10:00 ~ 11:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time11' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time11' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">11:00 ~ 12:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time12' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time12' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">12:00 ~ 13:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time13' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time13' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">13:00 ~ 14:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time14' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time14' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">14:00 ~ 15:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time15' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time15' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">15:00 ~ 16:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time16' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time16' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">16:00 ~ 17:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time17' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time17' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">17:00 ~ 18:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time18' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time18' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">18:00 ~ 19:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time19' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time19' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">19:00 ~ 20:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time20' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time20' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">20:00 ~ 21:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time21' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time21' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">21:00 ~ 22:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time22' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time22' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">22:00 ~ 23:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time23' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time23' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                  <tr>
                   <td>
                      <p class="time">23:00 ~ 24:00</p>
                      <p class="person">
                      <c:set var="cnt" value="0"/>
					  <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time24' }">
                       	<c:set var="cnt" value="${cnt+1 }"/>
                       </c:if>
                      </c:forEach>
                      <c:set var="tmpCnt" value="0"/>
                      <c:forEach items="${placementDetail}" var="detail">
                       <c:if test="${detail.workplacePlacementDetailWorkTime == 'time24' }">
                       <c:set var="tmpCnt" value="${tmpCnt+1 }"/>
	                   <c:choose>
	                   <c:when test="${!empty unit && unit.unitName == detail.unit.unitName}">
                       	<p style="font-weight:bold;"> ${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }</p>
	          			</c:when>
	                   <c:otherwise>
                       	${detail.unit.code2.code1depth }&nbsp;${detail.unit.unitName }
	                   </c:otherwise>
	                   </c:choose>
                       	<c:if test="${cnt ne tmpCnt }">
                       	,
                       </c:if>
                       </c:if>
                      </c:forEach>
                      </p>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>

								
					</div>
          </div>
          <script type="text/javascript">
              //jquery-ui msie 오류 관련
              jQuery.browser = {};
              (function () {
                  jQuery.browser.msie = false;
                  jQuery.browser.version = 0;
                  if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
                      jQuery.browser.msie = true;
                      jQuery.browser.version = RegExp.$1;
                  }

                  //datepicker
                  $( "#datepicker" ).datepicker({
                    dateFormat: 'yy년 mm월 dd일',
                    closeText: '닫기',
                    altFormat: "yyyy-mm-dd",
                    changeMonth: true,
                    changeYear: true,
                    dayNamesShort : [ "일", "월", "화", "수", "목", "금", "토" ],
                    monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
                    onSelect: function(dateText, inst) {
                    	var year = inst.selectedYear;
                    	var month = Number(inst.selectedMonth)+Number(1);
                    	var day = inst.selectedDay;
                    	var formedDate = year+'-'+month+'-'+day;
                    	location.href=encodeURI("workinfo.do?mobileDate="+formedDate);
                      }
                  });
                  $( "#datepicker" ).datepicker( "setDate", '<fmt:formatDate pattern="yy년 MM월 dd일" value="${mobilDate }" />' );
              })();
          </script>
        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
