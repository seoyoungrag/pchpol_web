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
	
	$("#code5_code1depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code5_code2depth';
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '2';
		selectpickerObj.parentVal = [$("#code5_code1depth").val()]; 
		$('#code5_code2depth').html('<option value="">배치장소 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#code5_code2depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code5_code3depth';
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '3';
		selectpickerObj.parentVal = [$("#code5_code1depth").val(), $("#code5_code2depth").val()]; 
		$('#code5_code3depth').html('<option value="">임무 선택</option>');
		selectpickerObj.setByCode();
	});
	
	if('${viewType}'=='view'){
		getViewById('${unitNo}');
		$(".header-title").html("여경 기동대 수정하기");
		$("#submitBtn").html("여경 기동대 수정하기");
	}else if('${viewType}'=='reg'){
		dropdwonOption.submitFieldName =  "unitMobilStartDt";
		$("#unitMobilStartDt").dateDropdowns(dropdwonOption);
		dropdwonOption.submitFieldName =  "unitMobilEndDt";
		$("#unitMobilEndDt").dateDropdowns(dropdwonOption);
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'troopsCodeNo'; //지방청
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'female';
		selectpickerObj.setByCode();

		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'rankCodeNo'; //계급
		selectpickerObj.category = 'rank';
		selectpickerObj.setByCode();
		
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code5_code1depth';  //배치지역
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '1';
		selectpickerObj.setByCode();
	}
});
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
		$("#troopsCodeNo").val(res.data.code1.codeNo); //지방청
		$("#code1_code3depth").html(res.data.code1.code3depth); //부대
		$("#code1_code4depth").html(res.data.code1.code4depth); //세부소속
		$("#rankCodeNo").val(res.data.code2.codeNo); //계급
		$("#workplaceNo").val(res.data.code5.codeNo); //배치지역, 배치장소, 임무

		dropdwonOption.submitFieldName =  "unitMobilStartDt";
		dropdwonOption.defaultDate = dateFormatterGmt(res.data.unitMobilStartDt);
		$("#unitMobilStartDt").dateDropdowns(dropdwonOption);
		dropdwonOption.submitFieldName =  "unitMobilEndDt";
		dropdwonOption.defaultDate = dateFormatterGmt(res.data.unitMobilStartDt);
		$("#unitMobilEndDt").dateDropdowns(dropdwonOption);
		
		$("#unitMobilStartDt").val(dateFormatterGmt(res.data.unitMobilStartDt)); //동원시작일
		$("#unitMobilEndDt").val(dateFormatterGmt(res.data.unitMobilEndDt)); //동원종료일
		/* $("#positionCodeNo").val(res.data.code4.codeNo); //직위 */
		
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'troopsCodeNo'; //지방청
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'female';
		selectpickerObj.selectVal = res.data.code1.codeNo;
		selectpickerObj.setByCode();

		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'rankCodeNo';  //계급
		selectpickerObj.category = 'rank';
		selectpickerObj.selectVal = res.data.code2.codeNo;
		selectpickerObj.setByCode();

		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code5_code1depth';  //배치지역
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '1';
		selectpickerObj.selectVal = res.data.code5.code1depth;
		selectpickerObj.setByCode();

		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code5_code2depth';  //배치장소
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '2';
		selectpickerObj.selectVal = res.data.code5.code2depth;
		selectpickerObj.parentVal = [$("#code5_code1depth").val()]; 
		selectpickerObj.setByCode();

		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code5_code3depth';  //임무
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '3';
		selectpickerObj.selectVal = res.data.code5.code3depth;
		selectpickerObj.parentVal = [$("#code5_code1depth").val(), $("#code5_code2depth").val()]; 
		selectpickerObj.setByCode();
		//$('#code5_code2depth').val(res.data.code5.code2depth); //배치장소
		//$('#code5_code3depth').val(res.data.code5.code3depth); //배치장소
		
	}
	viewObj.ajax(successFunc);
}
function reg(){
	var isProceed = true;
	var code = {
			codeCategory: 'workplace', 
			code1depth: $("#code5_code1depth").val(), 
			code2depth: $("#code5_code2depth").val(),
			code2depth: $("#code5_code2depth").val()
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
					<span>여경 기동대 추가하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/unit" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;지방청</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" name="code1.codeNo" id="troopsCodeNo">
									<option value="">지방청 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;구분
								</label>
								<label class="col-xs-4 col-sm-4 margin-top5 control-label input-label" id="code1_code3depth">
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;세부소속</label>
								<label class="col-xs-4 col-sm-4 margin-top5 control-label input-label" id="code1_code4depth">
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
								&nbsp;계급</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" name="code2.codeNo" id="rankCodeNo">
									<option value="">계급 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;폴넷아이디
								</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" required="" type="text" placeholder="폴넷아이디를 입력 하세요" name="unitPolId" id="unitPolId" required>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;생년월일</label>
								<div class="col-xs-4 col-sm-4">
									<input class="form-control" type="text" placeholder="생년월일을 입력 하세요" name="unitBirth" id="unitBirth" >
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;배치지역</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-live-search="true" data-size="15" required data-width="auto" id="code5_code1depth">
									<option value="">배치지역 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;배치장소</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-live-search="true" data-size="15" required data-width="auto" id="code5_code2depth">
									<option value="">배치장소 선택</option>
								</select>
								</label>
								<!-- 
								<div class="col-xs-2 col-sm-2">
									<input class="form-control" type="text" placeholder="배치장소" id="code5_code2depth">
								</div>
								<div class="col-xs-2 col-sm-2">
									<button class="btn btn-default waves-effect m-l-5" onclick="return popup('2');">
										검색
									</button>
								</div>
								 -->
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;임무</label>
								<label class="col-xs-4 col-sm-4 control-label">
								<select class="selectpicker form-control" data-container="body" data-live-search="true" data-size="15" required data-width="auto" id="code5_code3depth">
									<option value="">임무 선택</option>
								</select>
								</label>
								<!-- 
								<div class="col-xs-2 col-sm-2">
									<input class="form-control" type="text" placeholder="임무" id="code5_code3depth">
								</div>
								<div class="col-xs-2 col-sm-2">
									<button class="btn btn-default waves-effect m-l-5" onclick="return popup('3');">
										검색
									</button>
								</div>
								 -->
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;동원기간</label>
								<div class="col-xs-4 col-sm-4 text-right">
									<input type="hidden" id="unitMobilStartDt">
								</div>
								<div class="col-xs-1 col-sm-1 text-center">
								~
								</div>
								<div class="col-xs-5 col-sm-5 text-left">
									<input type="hidden" id="unitMobilEndDt">
								</div>
							</div>
							<div class="form-group encdecButton">
								<div class="text-center">
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
										여경 기동대 추가하기
									</button>
									<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden" name="unitNo" id="unitNo" value="0">
							<input type="hidden" name="troopsType" id="troopsType" value="female">
							<input type="hidden" name="code5.codeNo" id="workplaceNo">
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