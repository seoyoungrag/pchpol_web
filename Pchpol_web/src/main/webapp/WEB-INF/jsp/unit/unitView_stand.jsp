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
	viewObj.url = "${pageContext.request.contextPath}/unit/";
	viewObj.id = id;
	var successFunc = function(res){
		$("#unitNo").val(res.data.unitNo);
		$("#troopsType").val(res.data.troopsType);
		$("#unitBirth").val(res.data.unitBirth);
		$("#unitName").val(res.data.unitName);
		$("#unitPolId").val(res.data.unitPolId);
		$("#troopsCodeNo").val(res.data.code1.codeNo); //소속
		$("#rankCodeNo").val(res.data.code2.codeNo); //계급
		/* $("#positionCodeNo").val(res.data.code4.codeNo); //직위 */
		
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code1depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '1';
		selectpickerObj.selectVal = res.data.code1.code1depth;
		selectpickerObj.setByCode();
		
		selectpickerObj.divName = 'code1_code2depth';
		selectpickerObj.depth = '2';
		selectpickerObj.selectVal = res.data.code1.code2depth;
		selectpickerObj.parentVal = [$("#code1_code1depth").val()];
		selectpickerObj.setByCode();

		selectpickerObj.divName = 'code1_code3depth';
		selectpickerObj.depth = '3';
		selectpickerObj.selectVal = res.data.code1.code3depth;
		selectpickerObj.parentVal.push($("#code1_code2depth").val()); 
		selectpickerObj.setByCode();

		selectpickerObj.divName = 'code1_code4depth';
		selectpickerObj.depth = '4';
		selectpickerObj.selectVal = res.data.code1.code4depth;
		selectpickerObj.parentVal.push($("#code1_code3depth").val()); 
		selectpickerObj.setByCode();

		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'rankCodeNo';
		selectpickerObj.category = 'rank';
		selectpickerObj.selectVal = res.data.code2.codeNo;
		selectpickerObj.setByCode();
	}
	viewObj.ajax(successFunc);
}
function reg(){
	var code = {
			codeCategory: 'troops', 
			code1depth: $("#code1_code1depth").val(), 
			code2depth: $("#code1_code2depth").val(), 
			code3depth: $("#code1_code3depth").val(), 
			code4depth: $("#code1_code4depth").val()
			};
	$.ajax({
		type: "GET",
		url : "${pageContext.request.contextPath}/code",
		cache : false,
		data: code,
		success : function(res){
			if(res.success){
				$("#troopsCodeNo").val(res.data.codeNo);
				sendFormByAjax();
			}else{
				alert('데이터 입력을 실패하였습니다.');
			}
		},
		error : function(res){
			alert('데이터 입력을 실패하였습니다.');
		}
	});
	return false;
}
jQuery(document).ready(function($) {
	$("#code1_code1depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code2depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '2';
		selectpickerObj.parentVal = [$("#code1_code1depth").val()]; 
		$('#code1_code2depth').html('<option value="">구분 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#code1_code2depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code3depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '3';
		selectpickerObj.parentVal = [$("#code1_code1depth").val(),$("#code1_code2depth").val()]; 
		$('#code1_code3depth').html('<option value="">부대명 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#code1_code3depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code4depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '4';
		selectpickerObj.parentVal = [$("#code1_code1depth").val(),$("#code1_code2depth").val(),$("#code1_code3depth").val()]; 
		$('#code1_code4depth').html('<option value="">세부소속 선택</option>');
		selectpickerObj.setByCode();
	});
	if('${viewType}'=='view'){
		getViewById('${unitNo}');
		$(".header-title").html("상설 부대원 수정하기");
		$("#submitBtn").html("상설 부대원 수정하기");
	}else if('${viewType}'=='reg'){
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code1depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '1';
		selectpickerObj.setByCode();
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'rankCodeNo';
		selectpickerObj.category = 'rank';
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
					<span>상설 부대원 추가하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/unit" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;지방청</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code1depth">
									<option value="">지방청 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;구분
								</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code2depth">
									<option value="">구분 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;부대명</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code3depth">
									<option value="">부대명 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;세부소속
								</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code4depth">
									<option value="">세부소속 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;계급</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code2.codeNo" id="rankCodeNo">
									<option value="">계급 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;성명
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="성명을 입력 하세요" name="unitName" id="unitName" required>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;생년월일</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" type="text" placeholder="생년월일을 입력 하세요" name="unitBirth" id="unitBirth" >
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;폴넷아이디
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="폴넷아이디를 입력 하세요" name="unitPolId" id="unitPolId" required>
								</div>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
										상설 부대원 추가하기
									</button>
									<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden" name="unitNo" id="unitNo" value="0">
							<input type="hidden" name="troopsType" id="troopsType" value="stand">
							<input type="hidden" name="code1.codeNo" id=troopsCodeNo>
							<!-- <input type="hidden" name="code4.codeNo" id="positionCodeNo"> -->
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>