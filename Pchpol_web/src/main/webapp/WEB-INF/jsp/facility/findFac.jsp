<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">
var facType = '${facType}';
var curIdx = '${curIdx}';
var listObj;
function formatterfacilityDetail(cellvalue, options, rowObject)
{
		return '<span onclick="detailPopup('+cellvalue+')" class="text-primary">상세보기</span>';
}
function formatterfacilityInsert(cellvalue, options, rowObject)
{
	return '<span onclick="insert('+options.rowId+')" class="text-danger">입력</span>';
}
function insert(rowid){
	var facilityNo = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'facilityNo');
	var facilityArea = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'facilityArea');
	var facilityName = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'facilityName');
	var facilityAddrJuso = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'facilityAddrJuso');
	if (confirm("시설을 입력하시겠습니까?\n"+facilityArea+" "+facilityName+" "+facilityAddrJuso) == true){
		window.opener.jQuery('#${facType}No${curIdx}').val(facilityNo);
		window.opener.jQuery('#${facType}Addr${curIdx}').val(facilityName);
		self.close();
	}else{   
	    return;
	}
}
function detailPopup(id){
   	var popWidth = 1024;
	var popHeight = 540;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	var url = "${pageContext.request.contextPath}/facility/view.do?facType=${facType}&facilityNo="+id;
	win = window.open(url,'',param);

	return false;
}
function getList(){
	function selectRow(rowid){
	}
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/facility?listType=jqgrid&facilityType=${facType}";
	listObj.colNames = ['No.','지역','시설명','주소','상세','입력'];
	listObj.colModel = [
							{name:'facilityNo', hidden:true, sortable: false, width:"10", align:"center"},
							{name:'facilityArea', width:"110", align:"center"},
	               	   		{name:'facilityName',  width:"110", align:"left"},
	               	   		{name:'facilityAddrJuso',  width:"150", align:"left"},
	               	   		{name:'facilityNo',  width:"70", align:"center", sortable:false, formatter:formatterfacilityDetail},
	               	   		{name:'facilityNo',  width:"70", align:"center", sortable:false, formatter:formatterfacilityInsert}
	               	   	];
	listObj.idColName = 'facilityNo';
	listObj.jqgrid(selectRow);
}
jQuery(document).ready(function($) {
	getList();
});
function reg(){
	return false;
}
function findFac(){
	var facName = $("#facName").val();
	jQuery(listObj.grid).jqGrid('setGridParam',{url:encodeURI(listObj.url+"&searchType=facilityName&searchWord="+facName),page:1}).trigger("reloadGrid");
	getList();
}
</script>
</head>
<body>
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content">
			<div class="container leftright-padding">
				<h4 class="m-t-0 header-title popup-title text-center">
					<span>시설 검색</span>
				</h4>
				<form onsubmit="return false" class="form-horizontal" action="#">
				<div class="col-xs-12 col-sm-12 col-lg-12">
					<div class="card-box updown-padding">
						<div class="form-group bottom-line">
							<div class="col-xs-10 col-sm-10 col-md-10">
								<input class="form-control" required="" type="text" placeholder="시설명을 입력하세요." id="facName" required>
							</div>
							<div class="col-xs-2 col-sm-2 col-md-2 text-left" style="padding: 0;">
								<button class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="javascript:findFac();">검색</button>
							</div>
						</div>
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
               	</form>
            </div>
        </div>
    </div>
</div>
</body>
</html>