<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	//setSelectPickerTroops('indiv'); 
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code1_code1depth';
	selectpickerObj.category = 'troops';
	selectpickerObj.troopstype = 'indiv';
	selectpickerObj.depth = '1';
	selectpickerObj.setByCode();
	$("#code1_code1depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code2depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'indiv';
		selectpickerObj.depth = '2';
		selectpickerObj.parentVal = [$("#code1_code1depth").val()]; 
		$('#code1_code2depth').html('<option value="">구분 선택</option>');
		selectpickerObj.setByCode();
	});
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code3_code1depth';
	selectpickerObj.category = 'mobilfunction';
	selectpickerObj.setByCode();
	getList();
});    
var listObj;
function excel(type){
	var params = "sheetName=개인별동원자리스트&troopsType=indiv&colNamesArr=" + listObj.excelColNames.length + "&columns=" + listObj.excelColNames;
	if(type=='search'){
		params += "&code1.code1depth="+jQuery("#code1_code1depth").val()
		+"&code1.code2depth="+jQuery("#code1_code2depth").val()
		+"&code3.code1depth="+jQuery("#code3_code1depth").val()
		+"&searchType=unitName&searchWord="+jQuery("#searchWord").val();
	}
	var uri = '${pageContext.request.contextPath}/excel/unit/list.do';
	jQuery(listObj.grid).jqGrid('excelExport', {url:uri+'?'+encodeURI(params)});
}
function getList(){
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/unit/list?troopsType=indiv&listType=jqgrid";
	listObj.excelColNames = ['No.','지방청','구분','기능','성명','폴넷아이디','생년월일','배치지역','배치장소','임무','동원시작','동원종료'];
	listObj.colNames = ['unitNo','','No.','지방청','구분','기능','성명','폴넷아이디','생년월일','배치지역','배치장소','임무','동원시작','동원종료'];
	listObj.colModel = [
	              	   		{name:'unitNo',hidden:true, index:'unitNo', width:"30", align: "center"},
							{name: "select", width: "40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                                classes: "defaultCursor",
                                formatter: function (c,o,r) {
            			   			var id = r.unitNo;
                                    return "<input type='checkbox' uid='"+id+"'>";
                                } },
                            { name: "No", width:"40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                                classes: "jqgrid-rownum active defaultCursor",
                                formatter: function () {
                                    var p = $(this).jqGrid("getGridParam"),
                                        rn = p.curRowNum + (parseInt(p.page, 10) - 1)*parseInt(p.rowNum, 10);
                                    p.curRowNum++;
                                    return rn.toString();
                                } },
	               	   		{name:'code1.code1depth', width:"70", align:"center"},
	               	   		{name:'code1.code2depth', width:"70", align: "center"},
	               	   		{name:'code3.code1depth', width:"70", align:"center"},
	               	   		{name:'unitName', width:"70", index:'unitName', align:"center"},
	               	   		{name:'unitPolId', width:"120", index:'unitPolId', align:"center"},
	               	   		{name:'unitBirth',  width:"70", index:'unitBirth', align:"center"},
	               	   		{name:'mobilPlacementArea', width:"70", align:"center"},
	               	 		{name:'code5.code1depth', width:"70", align:"center"},
	               	 		{name:'code5.code2depth', width:"70", align:"center"},
	               	 		{name:'unitMobilStartDt',  width:"70", index:'unitMobilStartDt', align:"center", formatter:dateFormatterGmt},
	               	 		{name:'unitMobilEndDt',  width:"70", index:'unitMobilEndDt', align:"center", formatter:dateFormatterGmt}
	               	   	];
	listObj.idColName = 'unitNo';
	listObj.preventSelectCell = ['select','No'];
	listObj.jqgrid();
}
function deleteRow(){
	if(confirm('선택한 부대원 정보를 삭제하시겠습니까?')){
	var rows = jQuery(listObj.grid).find('input:checked');
	var ids = [];
	$.each(rows, function(idx, txt){
		ids.push($(txt).attr('uid'));		
	});
	$.ajax({
	 type: "POST",
	 url:contextPath+'/unit/delete', 
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
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&code1.code1depth="+jQuery("#code1_code1depth").val()
				+"&code1.code2depth="+jQuery("#code1_code2depth").val()
				+"&code3.code1depth="+jQuery("#code3_code1depth").val()
				+"&searchType=unitName&searchWord="+jQuery("#searchWord").val()
				)
				,page:1
				}).trigger("reloadGrid");
	return false;
}
function popup(viewType, rowid){
   	var popWidth = 680;
	var popHeight = 520;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/unit/view.do?viewType="+viewType+"&unitType=indiv&unitNo="+rowid,'',param);
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
						<h4 class="m-t-0 header-title"><b>개인별 동원자 리스트</b></h4>
					</div>
					<form onsubmit="return gridReload()" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
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
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="code3_code1depth">
														<option value="">기능</option>
													</select>
													</label>
												</div>
												<div class="pull-left">
													<label>
														<div class="form-group" style="margin-bottom:0;">
															<div class="pull-left mr10">
																<input type="text" id="searchWord" class="form-control form-control-middle" placeholder="" value="">
															</div>
															<div class="pull-left">
																<button class="btn btn-primary waves-effect waves-light" type="button" onclick="gridReload();">Search</button>
															</div>
														</div>
													</label>
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
					        <div class="row">
								<table id="list-grid" style="width:98%;"></table>
								<div id="list-pager"></div>
							</div>
							<div class="row board-bottom">
								<div class="col-sm-6 text-left">
									<button class="btn btn-primary waves-effect waves-light" type="button" onclick="javascript:excel('all');">전체 엑셀 다운로드</button>
									<button class="btn btn-primary waves-effect waves-light" type="button" onclick="javascript:excel('search');">선택 검색 다운로드</button>
								</div>
								<div class="col-sm-6 text-right">
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:deleteRow();">동원자 삭제하기</button>
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:popup('reg');">동원자 추가하기</button>
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
