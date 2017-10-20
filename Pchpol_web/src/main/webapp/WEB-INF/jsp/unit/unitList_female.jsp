<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	setSelectPickerTroops('female');
	getList();
});    
var listObj;
function getList(){
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/unit/list?troopsType=female&listType=jqgrid";
	listObj.colNames = ['No.','지방청','부대명','세부소속','성명','직위','폴넷아이디','생년월일','배치지역','배치장소','임무','동원시작','동원종료'];
	listObj.colModel = [
	              	   		{name:'unitNo',hidden:true, index:'unitNo', width:"30", align: "center"},
	               	   		{name:'code1.code1depth', width:"70", align:"center"},
	               	   		{name:'code1.code3depth', width:"70", align: "center"},
	               	   		{name:'code1.code4depth', width:"70", align:"center"},
	               	   		{name:'unitName', width:"70", index:'unitName', align:"center"},
	               	   		{name:'code4.code1depth', index:'unitNo', width:"70", align:"center"},
	               	   		{name:'unitPolId', width:"120", index:'unitPolId', align:"center"},
	               	   		{name:'unitBirth',  width:"70", index:'unitBirth', align:"center"},
	               	   		{name:'code5.code1depth', width:"70", align:"center"},
	               	 		{name:'code5.code2depth', width:"70", align:"center"},
	               	 		{name:'code5.code3depth', width:"70", align:"center"},
	               	 		{name:'unitMobilStartDt',  width:"70", index:'unitMobilStartDt', align:"center", formatter:dateFormatterGmt},
	               	 		{name:'unitMobilEndDt',  width:"70", index:'unitMobilEndDt', align:"center", formatter:dateFormatterGmt}
	               	   	];
	listObj.idColName = 'unitNo';
	listObj.jqgrid();
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&code1.code1depth="+jQuery("#code1_code1depth").val()
				+"&code1.code2depth=여경"
				+"&code1.code3depth="+jQuery("#code1_code3depth").val()
				+"&code1.code4depth="+jQuery("#code1_code4depth").val()
				+"&searchType=unitName&searchWord="+jQuery("#searchWord").val()
				)
				,page:1
				}).trigger("reloadGrid");
	return false;
}
function popup(viewType, rowid){
   	var popWidth = 680;
	var popHeight = 490;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/unit/view.do?viewType="+viewType+"&unitType=female&unitNo="+rowid,'',param);
}
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
					<div class="col-sm-12">
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>여경 기동대 리스트</b></h4>
					</div>
					<form onsubmit="return gridReload()" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-xs-2 col-sm-4 col-md-4 col-lg-7"></div>
											<div class="col-xs-5 col-sm-5 col-md-4 col-lg-3 text-right">
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code1depth">
													<option value="">지방청</option>
												</select>
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code3depth">
													<option value="">부대명</option>
												</select>
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code4depth">
													<option value="">세부소속</option>
												</select>
												</label>
											</div>
											<div class="col-xs-3 col-sm-2 col-md-2 col-lg-2">
											<label>
												<div class="form-group" style="margin-bottom:10px;">
													<div class="col-md-8 text-left">
														<input type="text" id="searchWord" class="form-control form-control-middle" placeholder="" value="">
													</div>
													<div class="col-md-4 text-left">
														<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="gridReload();">검색</button>
													</div>
												</div>
											</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div class="col-lg-12" >
						<div class="card-box">
					        <div class="row">
								<table id="list-grid" style="width:98%;"></table>
								<div id="list-pager"></div>
							</div>
							<div class="row">
								<div class="col-sm-6 text-left" style="padding-top: 20px;">
									<button class="btn btn-inverse waves-effect waves-light" type="button" onclick="#">전체 엑셀 다운로드</button>
									<button class="btn btn-inverse waves-effect waves-light" type="button" onclick="#">선택 검색 다운로드</button>
								</div>
								<div class="col-sm-6 text-right" style="padding-top: 20px;">
									<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="javascript:popup('reg');">여경 기동대 추가하기</button>
								</div>
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
