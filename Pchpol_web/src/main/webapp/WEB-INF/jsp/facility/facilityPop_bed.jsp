<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
function findAddrPop(){
	var zip = $("#facilityAddrZip").val();
	var area = $("#facilityArea").val();
	
	var area = "";
   	var popWidth = 650;
	var popHeight = 740;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	var url = "${pageContext.request.contextPath}/addr/findAddr.do?zip="+zip+"&area="+area;
	win = window.open(url,'',param);
	return false;
}
jQuery(function ($) {
	setSelectPickerFacilityArea();
	$.ajax({
		type : 'GET',
		url : '${pageContext.request.contextPath}/facility/${facilityNo}',
		cache : false,
		success : function(res){
			if(res.success){
				$("#facilityShelterOther").val(res.data.facilityShelterOther);
				$("#facilityChargerTel").val(res.data.facilityChargerTel);
				$("#facilityChargerName").val(res.data.facilityChargerName);
				$("#facilityAddrSebu").val(res.data.facilityAddrSebu);
				$("#facilityAddrJuso").val(res.data.facilityAddrJuso);
				$("#facilityAddrZip").val(res.data.facilityAddrZip);
				$("#facilityName").val(res.data.facilityName);
				$("#facilityArea_code1depth").val(res.data.facilityArea);
				$('.selectpicker').selectpicker('refresh');
			}else{
				alert('데이터 조회 실패하였습니다.');
			}
		},
		error : function(res){
			alert('데이터 조회 실패하였습니다.');
		}
	});
});    
function successFunc(res){
	if(res.success){
		alert('데이터를 입력하였습니다.');
		self.location.reload();
	}else{
		alert('데이터 입력을 실패하였습니다.');
		alert(res.message);
	}
}
function reg(){
	var area = $("#facilityArea_code1depth").val();
	if(typeof area === 'undefined' || area==''){
		return false;
	}else{
		$("#facilityArea").val(area);
	}
	sendFormByAjax(successFunc);
	return false;
}
</script>
</head>
<body>
<!-- Begin menu -->
<!-- menu End --> 
<!-- Body -->
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content text-center">
			<div class="container leftright-padding">
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/facility" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;시설명</label>
								<label class="col-xs-5 col-sm-5 control-label input-label">
								<input class="form-control" type="text" placeholder="시설이름을 입력 하세요" name="facilityName" id="facilityName" >
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label text-right">
								&nbsp;지역</label>
								<label class="col-xs-2 col-sm-2 control-label input-label text-left">
								<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="facilityArea_code1depth">
									<option value="">지역</option>
								</select>
								<input type="hidden" name="facilityArea" id="facilityArea">
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									&nbsp;주소
								</label>
								<div class="col-xs-2 col-sm-2 text-right">
									<input class="form-control" type="text" placeholder="우편번호" name="facilityAddrZip" id="facilityAddrZip" >
								</div>
								<div class="col-xs-2 col-sm-2 text-center" style="padding: 0;">
									<button class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="return findAddrPop();">우편번호 찾기</button>
								</div>
								<label class="col-xs-3 col-sm-3  text-left">
									<input class="form-control" type="text" placeholder="주소" name="facilityAddrJuso" id="facilityAddrJuso" >
								</label>
								<label class="col-xs-2 col-sm-2  text-left">
									<input class="form-control" type="text" placeholder="세부주소" name="facilityAddrSebu" id="facilityAddrSebu" >
								</label>
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;상세내용</label>
								<label class="col-xs-9 col-sm-9 control-label">
									<div class="form-group">
										<label class="col-xs-2 col-sm-2 control-label" style="margin-top: 5px;">
										&nbsp;담당자</label>
										<label class="col-xs-4 col-sm-4 control-label input-label">
										<input class="form-control" type="text" placeholder="담당자를 입력 하세요" name="facilityChargerName" id="facilityChargerName" >
										</label>
										<label class="col-xs-2 col-sm-2 control-label" style="margin-top: 5px;">
										&nbsp;연락처</label>
										<label class="col-xs-4 col-sm-4 control-label input-label">
										<input class="form-control" type="text" placeholder="연락처를 입력 하세요" name="facilityChargerTel" id="facilityChargerTel" >
										</label>
									</div>
									<div class="form-group">
										<div class="col-xs-12 col-sm-12 control-label" >
											<textarea style="width:100%;" id="facilityShelterOther" name="facilityShelterOther" rows="10">
											</textarea>
										</div>
									</div>
								</label>
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
										수정하기
									</button>
									<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden" name="facilityNo" value="${facilityNo}">
							<input type="hidden" name="facilityType" value="bed">
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
	<!-- end body -->
</body>
</html>
