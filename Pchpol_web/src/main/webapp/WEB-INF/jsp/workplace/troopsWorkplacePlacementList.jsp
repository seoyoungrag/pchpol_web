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
	setSelectPickerWorkplace();
	getList();
});    
function onSelectRow(rowid){
		//jqgrid의 한 행은 group by되서 보여지는 값이며, 현재 ui는 개별적인 수정을 할 수 있는 구조가 아님.
		//var id = jQuery(listObj.grid).jqGrid ('getCell', rowid, listObj.idColName);
		var code1depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code1depth');
		var code2depth = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'workplace.code2depth');
		popup('view','',code1depth,code2depth);
}
var listObj;
function getList(){
	function nonAction(rowid){}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementList?listType=jqgrid";
	listObj.colNames = ['지역','대회시설','지방청','기동대/의경 구분','구분','부대명'];
	listObj.colModel = [
	               	   		{name:'workplace.code1depth', width:"70", align:"center"},
	               	   		{name:'workplace.code2depth', width:"70", align: "center"},
	               	   		{name:'troops.code1depth', width:"70", align: "center"},
	               	   		{name:'troops.code2depth', hidden:true, width:"70", align: "center"},
	               	   		{name:'troops.code3depth', width:"70", align:"center", formatter:formatterCode3depth},
	               	   		{name:'troopsDetail', sortable:false , width:"70", align:"center", formatter:formatterTroopsDetail}
	               	   	];
	listObj.idColName = 'workplace.code1depth';
	listObj.jqgrid(onSelectRow);
}
function formatterCode3depth(cellvalue, options, rowObject)
{
	var retVal = rowObject.troops.code2depth+' '+rowObject.troops.code3depth;
	return retVal
}
function formatterTroopsDetail(cellvalue, options, rowObject)
{	
	var retVal = '';
	$.each(cellvalue, function (index, text) {
		if(!text.code4depth){
			retVal +='';
		}else{
			retVal +=text.code4depth+'</br>';
		}
	});
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
function popup(viewType, rowid, code1depth, code2depth){
   	var popWidth = 680;
	var popHeight = 320;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementView.do?viewType="+viewType+"&code1depth="+code1depth+"&code2depth="+code2depth,'',param);
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
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>상설부대 배치 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-xs-1 col-sm-3 col-md-3 col-lg-7"></div>
											<div class="col-xs-8 col-sm-7 col-md-7 col-lg-5 text-right" style="margin-bottom:10px;">
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
												<label>
												<select class="selectpicker form-control"  data-size="15" data-width="auto" id="code1_code2depth">
													<option value="">구분</option>
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
								<div class="col-sm-12 text-right" style="padding-top: 20px;">
									<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="javascript:popup('reg');">상설 부대 배치 등록하기</button>
									<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="javascript:popup('reg');">전체 배치 현황 보기</button>
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
