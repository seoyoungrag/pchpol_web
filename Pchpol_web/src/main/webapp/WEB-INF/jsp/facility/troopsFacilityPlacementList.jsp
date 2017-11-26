<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	//setSelectPickerTroops();
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code1_code1depth';
	selectpickerObj.category = 'troops';
	selectpickerObj.troopstype = 'stand';
	selectpickerObj.depth = '1';
	selectpickerObj.setByCode();

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
	getList();
});    

function facilityFormatter (cellvalue, options, rowObject)
{	
	var retVal = '';
	$.each(cellvalue, function(idx, txt){
		retVal += txt.facilityName+'</br>';
	});
	return retVal
}
var listObj;
function getList(){
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/facility/troopsFacilityPlacementList?listType=jqgrid";
	//listObj.colNames = ['No.','지방청','구분','부대명','세부소속','배치지역','대회시설','동원시작','동원종료','숙영시설','급식시설'];
	listObj.colNames = ['No.','지방청','구분','부대명','세부소속','배치지역','대회시설','동원시작','동원종료','숙영시설'];
	listObj.colModel = [
							{name:'troops.codeNo',hidden:true, index:'codeNo', width:"30", align: "center"},
	               	   		{name:'troops.code1depth', index:'code1depth', width:"70", align:"center"},
	               	   		{name:'troops.code2depth', index:'code2depth', width:"70", align: "center"},
	               	   		{name:'troops.code3depth', index:'code3depth', width:"70", align: "center"},
	               	   		{name:'troops.code4depth', index:'code4depth', width:"70", align:"center"},
	               	   		{name:'workplace.code1depth', index: 'troopsPlacements2.code2.code1depth', width:"70", align:"center"},
	               	 		{name:'workplace.code2depth', index: 'troopsPlacements2.code2.code2depth', width:"70", align:"center"},
	               	 		{name:'facilityMobilStartDt', sortable:false,  width:"70", align:"center", formatter:dateFormatterGmt},
	               	 		{name:'facilityMobilEndDt', sortable:false,  width:"70", align:"center", formatter:dateFormatterGmt},
	               	 		{name:'bedFacility', sortable:false, width:"70", align:"center",formatter:facilityFormatter}
	               	 		//{name:'foodFacility', sortable:false, width:"70", align:"center",formatter:facilityFormatter}
	               	   	];
	listObj.idColName = 'codeNo';
	listObj.jqgrid(popup);
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
function popup(rowid){
	var troopsNo = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.codeNo');
	var code1depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code1depth');
	var code2depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code2depth');
	var code3depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code3depth');
	var code4depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code4depth');
   	var popWidth = 650;
	var popHeight = 550;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open(encodeURI("${pageContext.request.contextPath}/facility/troopsFacilityPlacementView.do?1=1"
			+"&troops.codeNo="+troopsNo
			+"&troops.code1depth="+code1depth
			+"&troops.code2depth="+code2depth
			+"&troops.code3depth="+code3depth
			+"&troops.code4depth="+code4depth)
			,'',param);
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
					<div class="">
						<h4 class="m-t-0 header-title"><b>숙소별 숙영 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:0; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-lg-1"></div>
											<div class="search-section">
												<div class="pull-left mr30" style="margin-bottom:0;">
													<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="code1_code1depth">
														<option value="">지방청</option>
													</select>
													</label>
													<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="code1_code2depth">
														<option value="">구분</option>
													</select>
													</label>
													<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="code1_code3depth">
														<option value="">부대명</option>
													</select>
													</label>
													<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" name="code1.code4depth" id="code1_code4depth">
														<option value="">세부소속</option>
													</select>
													</label>
												</div>
												<div class="pull-left" style="margin-bottom:0;">
													<button class="btn btn-primary waves-effect waves-light" type="button" onclick="gridReload();">Search</button>
												</div>
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
