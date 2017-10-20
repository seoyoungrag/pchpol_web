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
	dropdwonOption.submitFieldName = "mobilDate";
	dropdwonOption.defaultDate = "2018-01-01";
	$("#mobilDate").dateDropdowns(dropdwonOption);
	$('.year').selectpicker('refresh');
	$('.month').selectpicker('refresh');
	$('.day').selectpicker('refresh');
	setSelectPickerWorkplace();
	getList();
});    
function nonAction(){}
var listObj;
function getList(){
	function nonAction(rowid){}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailList?listType=jqgrid";
	listObj.colNames = ['No.','지역','대회시설','근무지','세부구분','배치부대','현황'];
	listObj.colModel = [
							{name:'workplace.codeNo',hidden:true, width:"70", align:"center"},
	               	   		{name:'workplace.code1depth',index:'code1depth', width:"70", align:"center"},
	               	   		{name:'workplace.code2depth',index:'code2depth', width:"70", align: "center"},
	               	   		{name:'workplace.code3depth',index:'code3depth', width:"70", align: "center"},
	               	   		{name:'workplace.code4depth',index:'code4depth', width:"70", align: "center"},
	               	   		{name:'troopsDetail', sortable:false , width:"70", align:"center", formatter:formatterTroopsDetail},
	               	   		{name:'workplace.codeNo', sortable:false , width:"70", align:"center", formatter:formatterTroopsDetailModify}
	               	   	];
	listObj.idColName = 'codeNo';
	listObj.jqgrid(nonAction);
}
function formatterTroopsDetail(cellvalue, options, rowObject)
{
	var id = rowObject.workplace.codeNo;
	var retVal;
	if(cellvalue.length>0){
		retVal = cellvalue[0].code1depth+' '+cellvalue[0].code2depth+' '+cellvalue[0].code3depth+' '
		$.each(cellvalue, function (index, text) {
			retVal +=text.code4depth+', ';
		});
		retVal = retVal.slice(0,-2); 
	}else{
		retVal = '<span onclick="popup(\'reg\','+options.rowId+')" class="text-primary">등록하기</span>';
	}
	return retVal
}
function formatterTroopsDetailModify(cellvalue, options, rowObject)
{
	var id = cellvalue;
	var retVal;
	if(rowObject.troopsDetail.length>0){
		retVal = '<span onclick="popup(\'view\','+options.rowId+')" class="text-danger">수정하기</span>';
	}else{
		retVal = '등록준비';
	}
	return retVal
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&workplace.code1depth="+jQuery("#workplace_code1depth").val()
				+"&workplace.code2depth="+jQuery("#workplace_code2depth").val()
				+"&mobilDate="+jQuery("#mobilDate").val()
				)
				,page:1
				}).trigger("reloadGrid");
	return false;
}
function popup(viewType,rowid){
	var mobilDate= jQuery("#mobilDate").val();
	var codeNo = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.codeNo');
	var code1depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code1depth');
	var code2depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code2depth');
	var code3depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code3depth');
	var code4depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code4depth');
	
   	var popWidth = 830;
	var popHeight = 640;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailView.do?viewType="+viewType
			+"&codeNo="+codeNo
			+"&code1depth="+code1depth
			+"&code2depth="+code2depth
			+"&code3depth="+code3depth
			+"&code4depth="+code4depth
			+"&mobilDate="+mobilDate,'',param);
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
					<div class="col-sm-5">
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>근무정보 배치 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-xs-1 col-sm-3 col-md-3 col-lg-7 text-right" style="margin-bottom:10px;">
											</div>
											<div class="col-xs-8 col-sm-7 col-md-7 col-lg-5 text-right" style="margin-bottom:10px;">
												<label>
													<input type="hidden" id="mobilDate">
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="workplace_code1depth">
													<option value="">지역</option>
												</select>
												</label>
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="workplace_code2depth">
													<option value="">대회시설</option>
												</select>
												</label>
												<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="gridReload();">검색</button>
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
									<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="javascript:popup('reg');">미 등록 근무지 보기</button>
								</div><div class="col-sm-6 text-right" style="padding-top: 20px;">
									<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="javascript:popup('reg');">전체 근무 현황 보기</button>
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
