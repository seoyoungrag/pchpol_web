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
	dropdwonOption.submitFieldName = "workplacePlacementWorkDt";
	dropdwonOption.defaultDate = "2018-01-01";
	$("#workplacePlacementWorkDt").dateDropdowns(dropdwonOption);
	$('.year').selectpicker('refresh');
	$('.month').selectpicker('refresh');
	$('.day').selectpicker('refresh');
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
	getList();
});    
function nonAction(){}
var listObj;
function getList(){
	function nonAction(rowid){}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailListSep?listType=jqgrid";
	listObj.colNames = ['No.','지역','대회시설','근무지','세부구분','배치부대','현황'];
	listObj.colModel = [
							{name:'workplacePlacementNo',hidden:true, width:"70", align:"center"},
	               	   		{name:'code2.code1depth', width:"30", align:"center"},
	               	   		{name:'code2.code2depth', width:"50", align: "center"},
	               	   		{name:'code2.code3depth', width:"40", align: "center"},
	               	   		{name:'code2.code4depth', width:"40", align: "center"},
	               	   		{name:'code1', sortable:false, width:"200", align:"center", formatter:formatterTroopsDetail},
	               	   		{name:'code2.codeNo', sortable:false , width:"50", align:"center", formatter:formatterTroopsDetailModify}
	               	   	];
	listObj.idColName = 'workplacePlacementNo';
	listObj.jqgrid(nonAction);
}
function formatterTroopsDetail(cellvalue, options, rowObject)
{	
	var retVal = '';
	if(cellvalue){
		retVal = cellvalue.code1depth+' '+cellvalue.code2depth+' '+cellvalue.code3depth+' '+cellvalue.code4depth;
	}
	return retVal
}
function formatterTroopsDetailModify(cellvalue, options, rowObject)
{
	var id = cellvalue;
	var retVal = '<span onclick="popup(\'view\','+options.rowId+')" class="text-danger">근무일지보기</span>';
	return retVal
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&code2.code1depth="+jQuery("#workplace_code1depth").val()
				+"&code2.code2depth="+jQuery("#workplace_code2depth").val()
				+"&workplacePlacementWorkDt="+jQuery("#workplacePlacementWorkDt").val()
				)
				,page:1
				}).trigger("reloadGrid");
	return false;
}
function popup(viewType,rowid){
	var workplacePlacementWorkDt= jQuery("#workplacePlacementWorkDt").val();
	var workplacePlacementNo = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplacePlacementNo');
	
   	var popWidth = 830;
	var popHeight = 640;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailViewSep.do?1=1"
			+"&workplacePlacementNo="+workplacePlacementNo
			+"&workplacePlacementWorkDt="+workplacePlacementWorkDt,'',param);
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
				<div class="container">
					<div class="">
						<h4 class="m-t-0 header-title"><b>근무일지 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12">
							<div class="card-box" style="margin-bottom:0; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-lg-1"></div>
											<div class="search-section">
												<label>
													<input type="hidden" id="workplacePlacementWorkDt">
												</label>
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
					        <div class="row">
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
