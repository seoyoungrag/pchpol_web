<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/ref/js/topmenu.js"></script>
<div class="topbar-left">
	<div class="text-center">
		<%-- <a class="logo" id="logo" href="#" onclick="javascript:bodyLoad('${pageContext.request.contextPath}/admin/main.do');"> --%>
		<a class="logo" id="logo" href="${pageContext.request.contextPath}/admin/main.do">
			<span class="logo-small"><i class="mdi mdi-radar"></i>동원경찰업무시스템</span>
			<span class="logo-large"><i class="mdi mdi-radar"></i></span>
		</a>
	</div>
</div>
<div class="navbar navbar-default" role="navigation" >
	<div class="container">
		<div>
			<ul class="nav navbar-nav hidden-xs">
				<!-- <li><a class="waves-effect" href="#">Files</a></li> -->
				<c:if test="${admin.code.codeOrderNo eq '1' || admin.code.codeOrderNo eq '2'}">
					<li class="dropdown">
					<a class="dropdown-toggle waves-effect waves-primary forscreensize" role="button" aria-expanded="true" aria-haspopup="true" href="#"
						data-toggle="dropdown">관리자 정보 <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<%-- <li><a href="#" onclick="javascript:bodyLoad('${pageContext.request.contextPath}/admin/list.do');">관리자 리스트</a></li> --%>
							<li><a href="${pageContext.request.contextPath}/admin/list.do"">관리자 리스트</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test="${admin.code.codeOrderNo eq '1' || admin.code.codeOrderNo eq '2' || admin.code.codeOrderNo eq '3'}">
				<li class="dropdown">
				<a
					class="dropdown-toggle waves-effect waves-primary forscreensize"
					role="button" aria-expanded="false" aria-haspopup="true" href="#"
					data-toggle="dropdown">회원 리스트 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/unit/list.do?type=stand">상설 부대원 리스트</a></li>
						<li><a href="${pageContext.request.contextPath}/unit/list.do?type=indiv">개인별 동원자 리스트</a></li>
						<%-- 1104 여경을 상설 부대원에 포함
						<li><a href="${pageContext.request.contextPath}/unit/list.do?type=female">여경 리스트</a></li> --%>
					</ul>
				</li>
				</c:if>
				<c:if test="${admin.code.codeOrderNo eq '1' || admin.code.codeOrderNo eq '2' || admin.code.codeOrderNo eq '3'}">
				<li class="dropdown">
				<a
					class="dropdown-toggle waves-effect waves-primary forscreensize"
					role="button" aria-expanded="false" aria-haspopup="true" href="#"
					data-toggle="dropdown">숙영 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/facility/troopsFacilityPlacementList.do">소속별 숙영시설 리스트</a></li>
						<li><a href="${pageContext.request.contextPath}/facility/reg.do?facType=bed">숙영시설</a></li>
					</ul>
				</li>
				</c:if>
				<c:if test="${admin.code.codeOrderNo eq '1' || admin.code.codeOrderNo eq '2' || admin.code.codeOrderNo eq '3'}">
				<li class="dropdown ">
				<a class="dropdown-toggle waves-effect waves-primary forscreensize" role="button" aria-expanded="false" aria-haspopup="true" href="#"
					data-toggle="dropdown">근무지 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/workplace/list.do">근무지 리스트</a></li>
						<li><a
							href="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementList.do">상설 부대 배치</a></li>
					</ul>
				</li>
				</c:if>
				<li class="dropdown">
				<a
					class="dropdown-toggle waves-effect waves-primary forscreensize"
					role="button" aria-expanded="false" aria-haspopup="true" href="#"
					data-toggle="dropdown">근무 정보 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailList.do">근무 정보</a></li>
						<li><a href="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailListSep.do">근무 일지</a></li>
					</ul>
				</li>
				<li class="dropdown">
				<a
					class="dropdown-toggle waves-effect waves-primary forscreensize"
					role="button" aria-expanded="false" aria-haspopup="true" href="#"
					data-toggle="dropdown">게시판 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/board/list.do?boardType=normal">상황/건의사항</a></li>
						<li><a href="${pageContext.request.contextPath}/board/list.do?boardType=notice">공지사항</a></li>
					</ul>
				</li>
			</ul>

			<ul class="nav navbar-nav navbar-right pull-right">

				<li class="dropdown hidden-xs"><a
					class="dropdown-toggle waves-effect waves-light forscreensize"
					aria-expanded="true" href="#" data-toggle="dropdown"
					data-target="#"> ${admin.adminName} </a></li>
				<li class="hidden-xs"><a class="waves-effect waves-light"
					href="javascript:logout();"
					style="margin-top: 25px; line-height: 20px;"><i
						class="zmdi zmdi-square-right"></i></a></li>


			</ul>

		</div>
	</div>
</div>


<script>
var admin = '${admin.adminName}';
function logout(){
	if(confirm('로그아웃 하시겠습니까?')){
		location.href = '${pageContext.request.contextPath}/logout.do';
	}
}
</script>