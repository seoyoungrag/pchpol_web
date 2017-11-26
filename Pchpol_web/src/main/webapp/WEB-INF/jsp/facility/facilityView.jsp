<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	$("#facilityType").change(function(){
		
	});
});    
</script>
</head>
<body>
<!-- Begin menu -->
<header> 
<!-- 로고를 상단 가운데에 따로 띄우려면 topbar 주석 -->
<div class="container-fluid">
	<div class="topbar">
		<jsp:include page="/common/topmenu.jsp" flush="false" />
	</div>
</div>
</header>
<!-- menu End --> 
<!-- Body -->
<div id="wrapper" style="margin-top:80px;">
	<div class="">
	<!-- Start content -->
		<div class="content text-center">
			<div class="container leftright-padding">
				<h4 class="m-t-0 header-title popup-title text-center">
					<span>소속별 숙영, 급식 시설 적용하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/facility/troopsFacilityPlacement" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-12 col-sm-12 control-label text-right">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="facilityType">
									<option value="bed">숙영시설</option>
									<option value="food">급식시설</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;시설명</label>
								<label class="col-xs-4 col-sm-4 control-label input-label">
								<input class="form-control" type="text" placeholder="시설이름을 입력 하세요" name="facilityName" id="facilityName" >
								</label>
								<label class="col-xs-3 col-sm-3 margin-top5 control-label text-right">
								&nbsp;지역</label>
								<label class="col-xs-3 col-sm-3 control-label input-label text-left">
								<input class="form-control" type="text" placeholder="지역" name="area" id="area" >
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;주소</label>
								<label class="col-xs-4 col-sm-4 control-label input-label text-left">
								<div class="col-xs-6 col-sm-6 text-right">
								<input class="form-control" type="text" placeholder="우편번호" name="facilityName" id="facilityName" >
								</div>
								<div class="col-xs-6 col-sm-6 text-left" style="padding: 0;">
								<button class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="javascript:self.close();">우편번호 찾기</button>
								</div>
								</label>
								<label class="col-xs-3 col-sm-3 control-label input-label text-left">
								<input class="form-control" type="text" placeholder="주소" name="facilityName" id="facilityName" >
								</label>
								<label class="col-xs-3 col-sm-3 control-label input-label text-left">
								<input class="form-control" type="text" placeholder="세부주소" name="facilityName" id="facilityName" >
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;상세내용</label>
								<label class="col-xs-10 col-sm-10 control-label">
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
										<div>
											<label class="col-xs-2 col-sm-2 control-label input-label" style="margin-top: 5px;">
												&nbsp;목욕탕
											</label>
											<label class="col-xs-10 col-sm-10 control-label input-label">
												<input class="form-control" type="text" placeholder="목욕탕을 입력 하세요" name="facilityShelterBath" id="facilityShelterBath" >
											</label>
										</div>
										<div>
											<label class="col-xs-2 col-sm-2 control-label input-label" style="margin-top: 5px;">
												&nbsp;세탁실
											</label>
											<label class="col-xs-10 col-sm-10 control-label input-label">
												<input class="form-control" type="text" placeholder="세탁실을 입력 하세요" name="facilityShelterLaundry" id="facilityShelterLaundry" >
											</label>
										</div>
										<div>
											<label class="col-xs-2 col-sm-2 control-label input-label" style="margin-top: 5px;">
												&nbsp;기타 편의시설
											</label>
											<label class="col-xs-10 col-sm-10 control-label input-label">
												<input class="form-control" type="text" placeholder="기타 편의시설을 입력 하세요" name="facilityShelterOther" id="facilityShelterOther" >
											</label>
										</div>
									</div>
								</label>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
										추가/수정하기
									</button>
									<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
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
	<!-- end body -->
</body>
</html>
