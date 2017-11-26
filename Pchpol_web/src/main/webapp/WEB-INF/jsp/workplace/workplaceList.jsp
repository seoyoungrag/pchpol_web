<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	//setSelectPickerWorkplace();
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'workplace_code1depth'; //지역(근무지)
	selectpickerObj.category = 'workplace';
	selectpickerObj.depth = '1';
	selectpickerObj.setByCode();
	$("#workplace_code1depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'workplace_code2depth';
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '2';
		selectpickerObj.parentVal = [$("#workplace_code1depth").val()]; 
		$('#workplace_code2depth').html('<option value="">대회시설 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#workplace_code2depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'workplace_code3depth';
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '3';
		selectpickerObj.parentVal = [$("#workplace_code1depth").val(), $("#workplace_code2depth").val()]; 
		$('#workplace_code3depth').html('<option value="">근무지 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#workplace_code3depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'workplace_code4depth';
		selectpickerObj.category = 'workplace';
		selectpickerObj.depth = '4';
		selectpickerObj.parentVal = [$("#workplace_code1depth").val(), $("#workplace_code2depth").val(), $("#workplace_code3depth").val()]; 
		$('#workplace_code4depth').html('<option value="">세부구분 선택</option>');
		selectpickerObj.setByCode();
	});
	getList();
});    
var listObj;
function getList(){
	function nonAction(rowid){}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/code/list?listType=jqgrid&searchType=codeCategory&searchWord=workplace";
	listObj.colNames = ['No.','지역','대회시설','근무지','세부구분'];
	listObj.colModel = [
	              	   		{name:'codeNo',hidden:true, width:"30", align: "center"},
	               	   		{name:'code1depth', width:"70", align:"center"},
	               	   		{name:'code2depth', width:"70", align: "center"},
	               	   		{name:'code3depth', width:"70", align:"center"},
	               	   		{name:'code4depth', width:"70", align:"center"}
	               	   	];
	listObj.idColName = 'codeNo';
	listObj.jqgrid(nonAction);
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&code1depth="+jQuery("#workplace_code1depth").val()
				+"&code2depth="+jQuery("#workplace_code2depth").val()
				+"&code3depth="+jQuery("#workplace_code3depth").val()
				+"&code4depth="+jQuery("#workplace_code4depth").val()
				+"&searchType=codeCategory&searchValue=troops"
				)
				,page:1
				}).trigger("reloadGrid");
	return false;
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
						<h4 class="m-t-0 header-title"><b>근무지 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:0; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-lg-1"></div>
											<div class="search-section">
												<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="workplace_code1depth">
														<option value="">지역</option>
													</select>
												</label>
												<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="workplace_code2depth">
														<option value="">대회시설</option>
													</select>
												</label>
												<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="workplace_code3depth">
														<option value="">근무지</option>
													</select>
												</label>
												<label>
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" name="code1.code4depth" id="workplace_code4depth">
														<option value="">세부구분</option>
													</select>
												</label>
												<button class="btn btn-primary waves-effect waves-light" type="button" onclick="gridReload();">Search</button>
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
