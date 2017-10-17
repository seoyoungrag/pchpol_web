<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	setSelectPickerTroops();
	getList();
});    
var listObj;
function getList(){
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/facility/troopsFacilityPlacementList?listType=jqgrid";
	listObj.colNames = ['No.','지방청','구분','부대명','세부소속','배치지역','대회시설','동원시작','동원종료','숙영시설','급식시설'];
	listObj.colModel = [
							{name:'troops.codeNo',hidden:true, index:'codeNo', width:"30", align: "center"},
	               	   		{name:'troops.code1depth', index:'code1depth', width:"70", align:"center"},
	               	   		{name:'troops.code2depth', index:'code2depth', width:"70", align: "center"},
	               	   		{name:'troops.code3depth', index:'code3depth', width:"70", align: "center"},
	               	   		{name:'troops.code4depth', index:'code4depth', width:"70", align:"center"},
	               	   		{name:'workspace.code1depth', sortable:false, width:"70", align:"center"},
	               	 		{name:'workspace.code2depth', sortable:false, width:"70", align:"center"},
	               	 		{name:'facilityMobilStartDt', sortable:false,  width:"70", align:"center", formatter:dateFormatterGmt},
	               	 		{name:'facilityMobilEndDt', sortable:false,  width:"70", align:"center", formatter:dateFormatterGmt},
	               	 		{name:'bedFacility', sortable:false, width:"70", align:"center"},
	               	 		{name:'foodFacility', sortable:false, width:"70", align:"center"}
	               	   	];
	listObj.idColName = 'codeNo';
	listObj.jqgrid();
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&troops.code1depth="+jQuery("#code1_code1depth").val()
				+"&troops.code2depth="+jQuery("#code1_code2depth").val()
				+"&troops.code3depth="+jQuery("#code1_code3depth").val()
				+"&troops.code4depth="+jQuery("#code1_code4depth").val()
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
					<div class="col-sm-5">
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>숙소별 숙영/급식 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-xs-1 col-sm-3 col-md-3 col-lg-7"></div>
											<div class="col-xs-5 col-sm-5 col-md-5 col-lg-4 text-right">
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code1depth">
													<option value="">지방청</option>
												</select>
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code2depth">
													<option value="">구분</option>
												</select>
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code3depth">
													<option value="">부대명</option>
												</select>
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" name="code1.code4depth" id="code1_code4depth">
													<option value="">세부소속</option>
												</select>
												</label>
											</div>
											<div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
											<label>
												<div class="form-group" style="margin-bottom:10px;">
													<div class="col-md-12 text-left">
														<button class="btn btn-primary waves-effect waves-light" type="button" onclick="gridReload();">검색</button>
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
					        <div>
								<table id="list-grid" style="width:98%;"></table>
								<div id="list-pager"></div>
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
