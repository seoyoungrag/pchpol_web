<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<head>
	<jsp:include page="/mobile/head.jsp" flush="false" />
</head>
<body class="">
  <div class="login-wrap">
    <div class="logo">
      <img class="police" src="${pageContext.request.contextPath}/mobile/resources/images/logo-police.png" alt="Police" />
      <img class="olympic" src="${pageContext.request.contextPath}/mobile/resources/images/logo-olympic.png" alt="PyeongChang Olympic" />
    </div>
    <div class="login-form">
        <form action="">
          <legend>평창동계올림픽 동원경찰관 로그인폼</legend>
          <div class="login-input">
            <input class="id" id="loginId" type="text" placeholder="통합포털 계정"/>
            <a href="#this" class="del" data-id="id" ><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-login-del.png" alt="통합포털 계정 수정" /></a>
          </div>
          <div class="login-input pw">
            <input class="password" id="loginPassword" type="password" placeholder="비밀번호 입력"/>
            <a href="#this" class="del" data-id="password" ><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-login-del.png" alt="비밀번호 수정" /></a>
          </div>
          <a class="btn-login" href="#" onclick="javasciprt:doLogin()">로그인</a>
        </form>
    </div>
    <div class="login-footer">
        <a class="call" href="tel:02-123-1234"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-login-call.png" alt="콜센터연결" />콜센터 연결</a>
        <a class="help" href="">화면 도움말<img src="${pageContext.request.contextPath}/mobile/resources/images/ico-login-help.png" alt="화면 도움말" /></a>
    </div>
  </div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${login eq 'empty'}">
	alert('동원지원 시스템에 로그인 정보가 없습니다.\n관리자에게 문의하세요.');
	</c:if>
  $(".del").bind('click', function(){
    var str_id = $(this).attr("data-id");
    $("."+str_id).val('');
  });
});


function doLogin() {
	var loginId = $("#loginId").val();
	var loginPassword = $("#loginPassword").val();

	if (loginId == '') {
		alert('아이디를 입력해 주세요.');
		$("#loginId").focus();
	}
	if (loginPassword == '') {
		alert('비밀번호를 입력해 주세요.');
		$("#loginPassword").focus();
	}

	$.ajax({
		type : 'post',
		async : true,
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		url : '${pageContext.request.contextPath}/mobile/login',
		data : 'loginId='+loginId+'&loginPassword='+loginPassword,
		success : function(data) {
			if (data.success) {
				if(typeof app !== 'undefined'){
					app.login(loginId, loginPassword);
				}
				location.href = '${pageContext.request.contextPath}/mobile/index.do';
			} else {
				alert('로그인이 실패하였습니다.');
			}
		},
		error : function(data1, status, err) {
			alert('로그인 중 알 수 없는 오류가 발생하였습니다.');
		}
	});
	return false;
}
</script>
</html>
