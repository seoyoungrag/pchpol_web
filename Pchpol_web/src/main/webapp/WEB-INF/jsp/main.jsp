<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="common/header.jsp" flush="false" />
</head>
<body>
	<!-- Begin menu -->
	<header> 
	<!-- 로고를 상단 가운데에 따로 띄우려면 topbar 주석 -->
	<div class="container-fluid">
		<div class="topbar">
			<jsp:include page="common/topmenu.jsp" flush="false" />
		</div>
	</div>
	</header>
	<!-- menu End --> 
	
	<!-- Body -->
	<div class="wrapper">       
		<div id="content-page" class="content-page">
			<jsp:include page="empty.jsp" flush="false" />
		</div>
	</div>
	<!-- end body -->
	
</body>
</html>
