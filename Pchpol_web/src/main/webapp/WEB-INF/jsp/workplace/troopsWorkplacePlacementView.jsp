<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">
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
		if($("#code1_code2depth").val()=='경찰관 기동대'){
			$("#code1_code3depth_value").html("&nbsp;부대명");
			$("#code1_code4depth_value").html("&nbsp;세부소속");
		}else if($("#code1_code2depth").val()=='의경 중대'){
			$("#code1_code3depth_value").html("&nbsp;중대");
			$("#code1_code4depth_value").html("&nbsp;소대");
		}
	});
	$("#code1_code3depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code4depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '4';
		selectpickerObj.parentVal = [$("#code1_code1depth").val(),$("#code1_code2depth").val(),$("#code1_code3depth").val()]; 
		$('#code1_code4depth').html('<option value="">세부소속 선택</option><option value="all">전체 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#code2_code1depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code2_code2depth';
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '2';
		selectpickerObj.parentVal = [$("#code2_code1depth").val()]; 
		$('#code2_code2depth').html('<option value="">대회시설 선택</option>');
		selectpickerObj.setByCode();
	});
	var code;
	$("#troopsCodeNo").change(function() {
		if($("#troopsCodeNo").val()!=''){
			code = {
					codeCategory: 'troops_female', 
					codeNo: $("#troopsCodeNo").val()
					};
			$.ajax({
				type: "GET",
				url : "${pageContext.request.contextPath}/code",
				cache : false,
				data: code,
				async: false,
				success : function(res){
					if(res.success){
						$("#code1_code3depth").html(res.data.code3depth);
						$("#code1_code4depth").html(res.data.code4depth);
					}else{
						alert('데이터 조회를 실패하였습니다.');
					}
				},
				error : function(res){
					alert('데이터 조회를 실패하였습니다.');
				}
			});
		}
	});
	
	if('${viewType}'=='view'){
		getView();
		$(".header-title").html("상설부대 배치 수정하기");
		$("#submitBtn").html("상설부대 배치 수정하기");
	}else if('${viewType}'=='reg'){
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code2_code1depth'; //지역(근무지)
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '1';
		selectpickerObj.setByCode();
	}
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code1_code1depth'; //지방청(소속)
	selectpickerObj.category = 'troops';
	selectpickerObj.troopstype = 'stand';
	selectpickerObj.depth = '1';
	selectpickerObj.setByCode();
});
var selectpickerObj;

//지금 ui는 개별적인 수정이 불가능한 형태, 오직 등록만 가능하다.
function getView(){
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code2_code1depth'; //지역(근무지)
	selectpickerObj.category = 'workplace';
	selectpickerObj.selectVal = '${w_code1depth}';
	selectpickerObj.depth = '1';
	selectpickerObj.setByCode();
	
	selectpickerObj.divName = 'code2_code2depth'; //배치장소(근무지)
	selectpickerObj.selectVal = '${w_code2depth}';
	selectpickerObj.parentVal = [$("#code2_code1depth").val()];
	selectpickerObj.depth = '2';
	selectpickerObj.setByCode();

	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code1_code1depth';
	selectpickerObj.category = 'troops';
	selectpickerObj.troopstype = 'stand';
	selectpickerObj.depth = '1';
	selectpickerObj.selectVal = '${t_code1depth}';
	selectpickerObj.setByCode();
	
	selectpickerObj.divName = 'code1_code2depth';
	selectpickerObj.depth = '2';
	selectpickerObj.selectVal = '${t_code2depth}';
	selectpickerObj.parentVal = [$("#code1_code1depth").val()];
	selectpickerObj.setByCode();

	selectpickerObj.divName = 'code1_code3depth';
	selectpickerObj.depth = '3';
	selectpickerObj.selectVal = '${t_code3depth}';
	selectpickerObj.parentVal.push($("#code1_code2depth").val()); 
	selectpickerObj.setByCode();
	
	$("#code1_code3depth").trigger('change');
}
function reg(){
	var isProceed = true;
	var code = {
			codeCategory: 'workplace', 
			code1depth: $("#code2_code1depth").val(), 
			code2depth: $("#code2_code2depth").val()
			};
	$.ajax({
		type: "GET",
		url : "${pageContext.request.contextPath}/code",
		cache : false,
		data: code,
		async: false,
		success : function(res){
			if(res.success){
				$("#workplaceNo").val(res.data.codeNo);
			}else{
				alert('데이터 입력을 실패하였습니다.');
				isProceed = false;
			}
		},
		error : function(res){
			alert('데이터 입력을 실패하였습니다.');
			isProceed = false;
		}
	});
	//세부소속을 여러개 등록
	var code4 = $("#code1_code4depth").val();
	var code4html = '';
	var code4s = [];
	if(code4 != 'all'){
		code = {
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
			async: false,
			success : function(res){
				if(res.success){
					code4html += '<input type="hidden" name="code1" id="troopsNo" value="'+res.data.codeNo+'">';
					$("#troops").html(code4html);
				}else{
					alert('데이터 입력을 실패하였습니다.');
					isProceed = false;
				}
			},
			error : function(res){
				alert('데이터 입력을 실패하였습니다.');
				isProceed = false;
			}
		});
	}else{
		$("#code1_code4depth option").each(function(idx, option)
				{
				    if($(option).val()!='' && $(option).val()!='all'){
				    	code4s.push($(option).val());
				    }
				});
		$.each(code4s, function(idx, txt){
			code = {
					codeCategory: 'troops', 
					code1depth: $("#code1_code1depth").val(), 
					code2depth: $("#code1_code2depth").val(),
					code3depth: $("#code1_code3depth").val(), 
					code4depth: txt
					};
			$.ajax({
				type: "GET",
				url : "${pageContext.request.contextPath}/code",
				cache : false,
				data: code,
				async: false,
				success : function(res){
					if(res.success){
						code4html += '<input type="hidden" name="code1" id="troopsNo" value="'+res.data.codeNo+'">';
					}else{
						alert('데이터 입력을 실패하였습니다.');
						isProceed = false;
					}
				},
				error : function(res){
					alert('데이터 입력을 실패하였습니다.');
					isProceed = false;
				}
			});
		});
		$("#troops").html(code4html);
	}
	if(isProceed==true){
		sendFormByAjax();
	}
	return false;
}
</script>
</head>
<body>
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content">
			<div class="container leftright-padding">
				<h4 class="m-t-0 header-title popup-title text-center">
					<span>상설부대 배치 등록하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacement" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;지역</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="code2_code1depth">
									<option value="">지역 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;대회시설
								</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="code2_code2depth">
									<option value="">대회시설 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;지방청</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="code1_code1depth">
									<option value="">지방청 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;구분
								</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="code1_code2depth">
									<option value="">구분 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label" id="code1_code3depth_value">
								&nbsp;부대명
								</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="code1_code3depth">
									<option value="">부대명 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label" id="code1_code4depth_value">
								&nbsp;세부소속
								</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="code1_code4depth">
									<option value="">세부소속 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group encdecButton">
								<div class="text-center">
									<button type="button" class="btn btn-silver btn-rounded waves-effect" onclick="javascript:reg();" id="submitBtn">
										상설부대 배치 추가하기
									</button>
									<button type="button" class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<div id="troops">
							</div>
							<input type="hidden" name="code2" id="workplaceNo">
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>