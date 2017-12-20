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
function deleteRow(){
	var rows = jQuery(listObj.grid).find('input:checked');
	if(rows.length==0){
		alert('삭제할 행을 선택해 주세요.');
		return false;
	}
	if(confirm('선택한 근무 정보를 삭제하시겠습니까?')){
	var ids = [];
	$.each(rows, function(idx, txt){
		ids.push($(txt).attr('uid'));		
	});
	$.ajax({
	 type: "POST",
	 url:contextPath+'/workplace/deleteTroopsWorkplacePlacement', 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 data: JSON.stringify(ids),
		 success: function(res) {
			 if(res.success){
				 alert('데이터를 삭제하였습니다.');
				 gridReload();
			 }else{
				 alert('데이터를 삭제를 실패하였습니다.');
			 }
		 },
			error : function(res){
					alert('통신 중 실패하였습니다.');
			}
		});
	}
}    
function nonAction(){}
var listObj;
function getList(){
	function nonAction(rowid){}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailList?listType=jqgrid";
	listObj.excelColNames = ['No.','지역','대회시설','근무지','세부구분','근무시간','배치부대'];
	listObj.colNames = ['codeNo','','No.','지역','대회시설','근무지','세부구분','배치부대','근무시간','현황'];
	listObj.colModel = [
							{name:'workplace.codeNo',hidden:true, width:"70", align:"center"},
							{name: "select", width: "40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                                classes: "defaultCursor",
                                formatter: function (c,o,r) {
            			   			var id = r.workplace.codeNo;
                                    return "<input type='checkbox' name='selectRow' uid='"+id+"'>";
                                } },
                            { name: "No", width:"40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                                classes: "jqgrid-rownum active defaultCursor",
                                formatter: function () {
                                    var p = $(this).jqGrid("getGridParam"),
                                        rn = p.curRowNum + (parseInt(p.page, 10) - 1)*parseInt(p.rowNum, 10);
                                    p.curRowNum++;
                                    return rn.toString();
                                } },
	               	   		{name:'workplace.code1depth',index:'code1depth', width:"30", align:"center"},
	               	   		{name:'workplace.code2depth',index:'code2depth', width:"50", align: "center"},
	               	   		{name:'workplace.code3depth',index:'code3depth', width:"40", align: "center"},
	               	   		{name:'workplace.code4depth',index:'code4depth', width:"40", align: "center"},
	               	   		{name:'troopsDetail', sortable:false , width:"200", align:"center", formatter:formatterTroopsDetail},
	               	   		{name:'workTimes', sortable:false , width:"200", align:"center"},
	               	   		{name:'workplace.codeNo', sortable:false , width:"50", align:"center", formatter:formatterTroopsDetailModify}
	               	   	];
	listObj.idColName = 'codeNo';
	listObj.preventSelectCell = ['select','No'];
	listObj.jqgrid(nonAction);
}
function formatterTroopsDetail(cellvalue, options, rowObject)
{
	var id = rowObject.workplace.codeNo;
	var retVal;
	if(cellvalue.length>0){
		retVal = cellvalue[0].code1depth+' '+cellvalue[0].code2depth+' '+cellvalue[0].code3depth+' '
		$.each(cellvalue, function (index, text) {
			var tname = text.code4depth;
			if(text.code4depth==null){
				//tname = text.code3depth;
				tname = '';
			}
			retVal +=tname+', ';
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
		var troopsNo = rowObject.troopsDetail[0].codeNo;
		var troopsCode1depth = rowObject.troopsDetail[0].code1depth;
		var troopsCode2depth = rowObject.troopsDetail[0].code2depth;
		var troopsCode3depth = rowObject.troopsDetail[0].code3depth;
		var troopsCode4depth = rowObject.troopsDetail[0].code4depth;
		retVal = '<span onclick="popup(\'view\','+options.rowId+',\''+troopsNo+'\',\''+troopsCode1depth+'\',\''+troopsCode2depth+'\',\''+troopsCode3depth+'\',\''+troopsCode4depth+'\')" class="text-danger">수정하기</span>';
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
function gridReloadNotReg(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&workplace.code1depth="+jQuery("#workplace_code1depth").val()
				+"&workplace.code2depth="+jQuery("#workplace_code2depth").val()
				+"&mobilDate="+jQuery("#mobilDate").val()
				+"&reloadType=notReg"
				)
				,page:1
				});
	jQuery(listObj.grid).jqGrid('setGridParam',{rowNum:10000}).trigger("reloadGrid");
}
function gridReloadNotAll(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&workplace.code1depth="+jQuery("#workplace_code1depth").val()
				+"&workplace.code2depth="+jQuery("#workplace_code2depth").val()
				+"&mobilDate="+jQuery("#mobilDate").val()
				)
				,page:1
				});
	jQuery(listObj.grid).jqGrid('setGridParam',{rowNum:10000}).trigger("reloadGrid");
}
function popup(viewType,rowid,troopsNo,troopsCode1depth,troopsCode2depth,troopsCode3depth,troopsCode4depth){
	var mobilDate= jQuery("#mobilDate").val();
	var codeNo = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.codeNo');
	var code1depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code1depth');
	var code2depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code2depth');
	var code3depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code3depth');
	var code4depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code4depth');
	
	var url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetailView.do?viewType="+viewType
	+"&workplace.codeNo="+codeNo
	+"&workplace.code1depth="+code1depth
	+"&workplace.code2depth="+code2depth
	+"&workplace.code3depth="+code3depth
	+"&workplace.code4depth="+code4depth
	+"&mobilDate="+mobilDate;
	if(viewType=='view'&&typeof troopsNo !== 'undefined'){
		url+="&troops.codeNo="+troopsNo
		+"&troops.code1depth="+troopsCode1depth
		+"&troops.code2depth="+troopsCode2depth
		+"&troops.code3depth="+troopsCode3depth
		+"&troops.code4depth="+troopsCode4depth;
	}
	
   	var popWidth = 830;
	var popHeight = 640;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open(encodeURI(url),'',param);
}
function excel(type){
	var params = "sheetName=근무정보배치리스트&colNamesArr=" + listObj.excelColNames.length + "&columns=" + listObj.excelColNames;
	+"&mobilDate="+jQuery("#mobilDate").val();
	if(type=='search'){
		params += "&workplace.code1depth="+jQuery("#workplace_code1depth").val()
		+"&workplace.code2depth="+jQuery("#workplace_code2depth").val();
	}
	var uri = '${pageContext.request.contextPath}/excel/workplace/troopsWorkplacePlacementDetailList.do';
	jQuery(listObj.grid).jqGrid('excelExport', {url:uri+'?'+encodeURI(params)});
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
						<h4 class="m-t-0 header-title"><b>근무정보 배치 리스트</b></h4>
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
													<input type="hidden" id="mobilDate">
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
							<div class="row board-bottom">
								<div class="col-sm-6 text-left">
									<button class="btn btn-primary waves-effect waves-light" type="button" onclick="javascript:gridReloadNotReg();">미 등록 근무지 보기</button>
									<button class="btn btn-primary waves-effect waves-light" type="button" onclick="javascript:excel('all');">엑셀 다운로드</button>
								</div><div class="col-sm-6 text-right">
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:deleteRow();">근무정보 배치 삭제하기</button>
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:gridReloadNotAll();">전체 근무 현황 보기</button>
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
