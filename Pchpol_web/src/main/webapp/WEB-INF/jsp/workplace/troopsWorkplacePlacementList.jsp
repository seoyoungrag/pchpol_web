<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	setSelectPickerTroops('stand');
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
	var row = jQuery(listObj.grid).find('input:checked');
	if(row.length==0){
		alert('삭제할 행을 선택해 주세요.');
		return false;
	}
	if(confirm('선택한 상설부대 배치 정보를 삭제하시겠습니까?')){
	var workplace = {};
	workplace.code1depth = $(row).attr('workplace.code1depth');
	workplace.code2depth = $(row).attr('workplace.code2depth');
	var troops = {};
	troops.code1depth = $(row).attr('troops.code1depth');
	troops.code2depth = $(row).attr('troops.code2depth');
	troops.code3depth = $(row).attr('troops.code3depth');
	var data = {};
	data.troops = troops;
	data.workplace = workplace;
	$.ajax({
	 type: "POST",
	 url:contextPath+'/workplace/deleteTroopsPlacement', 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 data: JSON.stringify(data),
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
function onSelectRow(rowid){
		//jqgrid의 한 행은 group by되서 보여지는 값이며, 현재 ui는 개별적인 수정을 할 수 있는 구조가 아님.
		//var id = jQuery(listObj.grid).jqGrid ('getCell', rowid, listObj.idColName);
		var w_code1depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code1depth');
		var w_code2depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code2depth');
		var t_code1depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code1depth');
		var t_code2depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code2depth');
		var t_code3depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'troops.code3depth');
		t_code3depth = t_code3depth.replace(t_code2depth,'').trim();
		popup('view','',w_code1depth,w_code2depth,t_code1depth,t_code2depth,t_code3depth);
}
var listObj;
function getList(){
	function nonAction(rowid){}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementList?listType=jqgrid";
	listObj.colNames = ['','지역','대회시설','지방청','기동대/의경 구분','구분','부대명'];
	listObj.colModel = [
						{name: "select", width: "40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                            classes: "defaultCursor",
                            formatter: function (c,o,r) {
                                return "<input type='radio' name='selectRow' workplace.code1depth='"+r.workplace.code1depth+"' workplace.code2depth='"+r.workplace.code2depth+"' troops.code1depth='"+r.troops.code1depth+"' troops.code2depth='"+r.troops.code2depth+"' troops.code3depth='"+r.troops.code3depth+"' ";
                            } },
               	   		{name:'workplace.code1depth', width:"70", align:"center"},
               	   		{name:'workplace.code2depth', width:"70", align: "center"},
               	   		{name:'troops.code1depth', width:"70", align: "center"},
               	   		{name:'troops.code2depth', hidden:true, width:"70", align: "center"},
               	   		{name:'troops.code3depth', width:"70", align:"center", formatter:formatterCode3depth},
               	   		{name:'troopsDetail', sortable:false , width:"70", align:"center", formatter:formatterTroopsDetail}
	               	   	];
	listObj.idColName = 'workplace.code1depth';
	listObj.preventSelectCell = ['select'];
	listObj.jqgrid(onSelectRow);
}
function formatterCode3depth(cellvalue, options, rowObject)
{	
	retVal = '';
	if(rowObject.troops.code2depth!=null){
		var retVal = rowObject.troops.code2depth+' '+rowObject.troops.code3depth;
	}
	return retVal
}
function formatterTroopsDetail(cellvalue, options, rowObject)
{	
	var retVal = '';
	$.each(cellvalue, function (index, text) {
		if(!text.code4depth){
			retVal +='';
		}else{
			retVal +=text.code4depth+',';
		}
	});
	if(retVal != ''){
		retVal = retVal.slice(0,-1);
	}
	return retVal
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&workplace.code1depth="+jQuery("#workplace_code1depth").val()
				+"&workplace.code2depth="+jQuery("#workplace_code2depth").val()
				+"&troops.code2depth="+jQuery("#code1_code2depth").val()
				)
				,page:1
				}).trigger("reloadGrid");
	return false;
}
function popup(viewType, rowid, w_code1depth, w_code2depth,t_code1depth,t_code2depth,t_code3depth){
   	var popWidth = 680;
	var popHeight = 380;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	var url ="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementView.do?viewType="+viewType;
	url+="&w_code1depth="+w_code1depth+"&w_code2depth="+w_code2depth;
	url+="&t_code1depth="+t_code1depth+"&t_code2depth="+t_code2depth+"&t_code3depth="+t_code3depth;
	
	window.open(encodeURI(url),'',param);
}
function gridReloadAll(){
	jQuery(listObj.grid).jqGrid('setGridParam',{
		url:encodeURI(
				listObj.url
				+"&workplace.code1depth="+jQuery("#workplace_code1depth").val()
				+"&workplace.code2depth="+jQuery("#workplace_code2depth").val()
				+"&troops.code2depth="+jQuery("#code1_code2depth").val()
				)
				,page:1
				});
	jQuery(listObj.grid).jqGrid('setGridParam',{rowNum:100000}).trigger("reloadGrid");
	jQuery(listObj.pager).hide();
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
						<h4 class="m-t-0 header-title"><b>상설부대 배치 리스트</b></h4>
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
													<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="code1_code2depth">
														<option value="">구분</option>
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
								<div class="col-sm-12 text-right">
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:popup('reg');">상설 부대 배치 등록하기</button>
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:deleteRow();">상설 부대 배치 삭제하기</button>
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:gridReloadAll();">전체 배치 현황 보기</button>
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
