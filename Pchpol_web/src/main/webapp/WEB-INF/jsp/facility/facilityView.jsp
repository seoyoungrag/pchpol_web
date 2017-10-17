<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
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
	<div class="wrapper">       
		<div id="content-page" class="content-page">
			<div class="content" style="margin-top:60px;">
				<div class="container" style="">
					<div class="container leftright-padding">
						<h4 class="m-t-0 header-title popup-title">
							<span>숙영/급식 추가하기</span>
						</h4>
						<div class="col-lg-12">
							<div class="card-box updown-padding">
								<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/unit" accept-charset="utf-8">
									<div class="form-group bottom-line">
										<label class="col-xs-2 col-sm-2 margin-top5 control-label">
										&nbsp;지방청</label>
										<label class="col-xs-4 col-sm-4 control-label">
										<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code1.codeNo" id="troopsCodeNo">
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
										<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code2.codeNo" id="rankCodeNo">
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
										<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code5_code1depth">
											<option value="">배치지역 선택</option>
										</select>
										</label>
									</div>
									<div class="form-group bottom-line">
										<label class="col-xs-2 col-sm-2 margin-top5 control-label">
										&nbsp;배치장소</label>
										<label class="col-xs-4 col-sm-4 control-label">
										<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code5_code2depth">
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
										<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code5_code3depth">
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
										<div class="col-sm-offset-3 col-sm-9 text-right">
											<button class="btn btn-primary waves-effect waves-light" id="submitBtn">
												확인
											</button>
											<button class="btn btn-default waves-effect m-l-5" onclick="javascript:self.close();">
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
	</div>
	<!-- end body -->
</body>
</html>
