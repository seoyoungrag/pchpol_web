<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
</head>
<body>
	<!-- Body -->
	<div class=" login-wrap">
	<div class="wrapper-page">
		<div class="text-center">
			<p class="logo logo-lg">
			    <img class="police" src="${pageContext.request.contextPath}/ref/images/logo-police.png" alt="Police" />
   				<img class="olympic" src="${pageContext.request.contextPath}/ref/images/logo-olympic.png" alt="PyeongChang Olympic" />
			</p>
		</div>
		<div class="text-center"
			style="width: 60%; margin-left: 20%; margin-top: 5%;">
			<form class="form-horizontal m-t-20" onsubmit="return false;">
				<div class="form-group">
					<div class="col-xs-12">
						<input class="form-control" type="text" required id="loginId" placeholder="Username"> 
						<i class="fa fa-user-circle-o form-control-feedback l-h-34"></i>
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-12">
						<input class="form-control" type="password" required id="loginPassword" placeholder="Password"> 
						<i class="fa fa-key form-control-feedback l-h-34"></i>
					</div>
				</div>

				<div class="form-group">
					<div class="col-xs-12" style="margin: 10% 0% 0% 0%;">
						<div class="checkbox checkbox-primary" style="float: left;">
							<input id="checkbox-signup" type="checkbox"> 
							<label for="checkbox-signup"> Remember me </label>
						</div>
						<div class="col-xs-12"
							style="margin: 0px; padding: 0px; width: 114px; float: right;">
							<button
								class="btn btn-primary btn-custom w-md waves-effect waves-light"
								onclick="doLogin();">Log In</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
<!-- 	
	<div style="width: 100%; height: 100px; background-color: #494e50; text-align: center; color: #fff; padding-top: 20px;">
		본 서비스는 Chrome 브라우저 1920 * 1080 해상도에 최적화 되었습니다.
	</div>
 -->
	<div id="loginFailModal" class="modal fade modal-lg" tabindex="-1"
		role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">로그인 인증 실패안내</h4>
				</div>
				<div class="modal-body">
					<p>로그인이 실패하였습니다. 관리자에게 문의하세요.</p>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			getUserIdFromCookie();
		});

		function rememberMe() {
			$.cookie('cookieLoginId', $('#loginId').val());
		}

		function getUserIdFromCookie() {
			if (!!$.cookie('cookieLoginId')) {
				$('#loginId').val($.cookie('cookieLoginId'));
				$('#checkbox-signup').attr('checked', 'true');
			}
		}

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

			if ($('#checkbox-signup').val()) {
				rememberMe();
			}

			$.ajax({
				type : 'post',
				async : true,
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				url : '${pageContext.request.contextPath}/login?adminId='
						+ loginId
						+ '&adminPassword='
						+ encodeURIComponent(loginPassword),
				success : function(data) {
					if (data.success) {
						//
						if(data.data.code.code1depth=='동원부대'){
							location.href = '${pageContext.request.contextPath}/admin/main.do';
						}else{
							location.href = '${pageContext.request.contextPath}/unit/list.do?type=stand';	
						}
						
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
	<!-- end body -->
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery.cookie.js"></script>
</body>
</html>
