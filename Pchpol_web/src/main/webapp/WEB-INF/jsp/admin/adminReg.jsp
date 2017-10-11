<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script type="text/javascript">
function setSelectpickerByCode(divName, category){
	$.ajax({
	 type: "GET",
	 url:'${pageContext.request.contextPath}/code/list/'+category, 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(res) {
		   			$.each(res.data, function (index, text) {
		   			    $('select#'+divName).append($('<option>', {
		   			        value: res.data[index].codeNo,
		   			        text : res.data[index].code1depth
		   			    }));
		   			});
		   			$('select#'+divName).selectpicker('refresh');
				}
		});
}
function sendFormByAjax(){
	var regForm = $("#regForm");
	var formData = regForm.serialize();
	$.ajax({
		type : regForm.attr("method"),
		url : regForm.attr("action"),
		cache : false,
		data : formData,
		success : function(response){
			console.log(response);
			if(response.success){
				alert('데이터를 입력하였습니다.');
				window.opener.jQuery("#list-grid").trigger("reloadGrid");
				self.close();
			}else{
			alert('데이터 입력을 실패하였습니다.');
			}
		},
		error : function(response){
			console.log(response);
			alert('데이터 입력을 실패하였습니다.');
		}
	});
	return false;
}
jQuery(document).ready(function($) {
	setSelectpickerByCode("codeAdminType","ADMIN");
});
</script>
</head>
<body>
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content">
			<div class="container leftright-padding">
				<h4 class="m-t-0 header-title popup-title">
					<span>관리자 계정 추가하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return sendFormByAjax()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/admin" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;권한
								</label>
								<div class="col-xs-10 col-sm-10">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code.codeNo" id="codeAdminType">
									<option value="">권한 선택</option>
								</select>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									&nbsp;소속
								</label>
								<div class="col-xs-10 col-sm-10">
									<input class="form-control" required=""  placeholder="지방청/구분까지 입력, 예)서울 경찰청 경찰기동대" name="adminDept" required >
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;계급</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" type="text" placeholder="계급을 입력 하세요" name="adminRank" >
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;성명
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="성명을 입력 하세요" name="adminName" required>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;아이디
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="아이디를 입력 하세요" name="adminId" required>
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;패스워드
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="패스워드를 입력 하세요" name="adminPassword" required>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;비고
								</label>
								<div class="col-xs-10 col-sm-10">
									<input class="form-control" type="text" placeholder="비고를 입력 하세요" name="adminEtc">
								</div>
							</div>
							<div class="form-group encdecButton">
								<div class="col-sm-offset-3 col-sm-9 text-right">
									<button class="btn btn-primary waves-effect waves-light" id="submitBtn">
										확인
									</button>
									<button class="btn btn-default waves-effect m-l-5" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>