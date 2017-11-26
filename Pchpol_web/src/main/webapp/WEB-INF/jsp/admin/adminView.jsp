<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">
var selectpickerObj;
var viewObj;
function getViewById(id){
	viewObj = new ViewObj();
	viewObj.url = "${pageContext.request.contextPath}/admin/";
	viewObj.id = id;
	var successFunc = function(res){
		$("#adminNo").val(res.data.adminNo);
		$("#adminDept").val(res.data.adminDept);
		$("#adminEtc").val(res.data.adminEtc);
		$("#adminName").val(res.data.adminName);
		$("#adminId").val(res.data.adminId);
		$("#adminPassword").val(res.data.adminPassword);
		$("#adminRank").val(res.data.adminRank);
		
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'codeAdminType';
		selectpickerObj.category = 'admin';
		selectpickerObj.selectVal = res.data.code.codeNo;
		selectpickerObj.setByCode();
	}
	viewObj.ajax(successFunc);
}
function reg(){
	sendFormByAjax();
	return false;
}
jQuery(document).ready(function($) {
	if('${type}'=='view'){
		getViewById('${adminNo}');
		$(".header-title").html("관리자 계정 수정하기");
		$("#submitBtn").html("계정 수정하기");
	}else if('${type}'=='reg'){
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'codeAdminType';
		selectpickerObj.category = 'admin';
		selectpickerObj.setByCode();
	}
});
</script>
</head>
<body>
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content">
			<div class="container leftright-padding">
				<h4 class="m-t-0 header-title popup-title text-center">
					<span>관리자 계정 추가하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/admin" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;권한
								</label>
								<div class="col-xs-10 col-sm-10">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" name="code.codeNo" id="codeAdminType">
									<option value="">권한 선택</option>
								</select>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									&nbsp;소속
								</label>
								<div class="col-xs-10 col-sm-10">
									<input class="form-control" required=""  placeholder="지방청/구분까지 입력, 예)서울 경찰청 경찰기동대" name="adminDept" id="adminDept" required >
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;계급</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" type="text" placeholder="계급을 입력 하세요" name="adminRank" id="adminRank" >
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;성명
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="성명을 입력 하세요" name="adminName" id="adminName" required>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;아이디
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="아이디를 입력 하세요" name="adminId" id="adminId" required>
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;패스워드
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="패스워드를 입력 하세요" name="adminPassword" id="adminPassword" required>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;비고
								</label>
								<div class="col-xs-10 col-sm-10">
									<input class="form-control" type="text" placeholder="비고를 입력 하세요" name="adminEtc" id="adminEtc">
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-12">
									<div class="col-xs-9 text-right">
										<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
											계정 추가하기
										</button>
										<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
											취소
										</button>
									</div>
									<div class="col-xs-3 text-right">
										<button class="btn btn-inverse btn-rounded waves-effect" onclick="return false">
											권한보기
										</button>
									</div>
								</div>
							</div>
							<input type="hidden" name="adminNo" id="adminNo" value="0">
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>