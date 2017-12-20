<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	getList();
});   
function deleteRow(){
	var rows = jQuery(listObj.grid).find('input:checked');
	if(rows.length==0){
		alert('삭제할 행을 선택해 주세요.');
		return false;
	}
	if(confirm('선택한 관리자 정보를 삭제하시겠습니까?')){
	var ids = [];
	$.each(rows, function(idx, txt){
		ids.push($(txt).attr('uid'));		
	});
	$.ajax({
	 type: "POST",
	 url:contextPath+'/admin/delete', 
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
var listObj;
function getList(){
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/admin/list?listType=jqgrid";
	listObj.colNames = ['adminNo','','No.','권한','소속','계급','성명','비고','아이디','패스워드'];
	listObj.colModel = [
	           	   		{name:'adminNo',hidden:true, index:'adminNo', width:"30", align: "center"},
						{name: "select", width: "40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                            classes: "defaultCursor",
                            formatter: function (c,o,r) {
        			   			var id = r.adminNo;
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
	           	   		{name:'code.code1depth', index:'code', width:"70",align:"center"},
	           	   		{name:'adminDept',index:'adminDept', width:"120", align: "left"},
	           	   		{name:'adminRank', index:'adminRank', width:"70",align:"center"},
	           	   		{name:'adminName',index:'adminName', width:"70", align:"center"},
	           	   		{name:'adminEtc',index:'adminEtc', width:"200", align:"left"},
	           	   		{name:'adminId', hidden:true, index:'adminId'},		
	           	   		{name:'adminPassword',hidden:true,index:'adminPassword'}
	           	   		];
	listObj.idColName = 'adminNo';
	listObj.preventSelectCell = ['select','No'];
	listObj.jqgrid();
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{url:encodeURI(listObj.url+"&searchType="+jQuery("#searchType").val()+"&searchWord="+jQuery("#searchWord").val()),page:1}).trigger("reloadGrid");
}
function popup(type, rowid){
   	var popWidth = 640;
  	var popHeight = 400;
  	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/admin/view.do?type="+type+"&adminNo="+rowid,'',param);
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
						<h4 class="m-t-0 header-title"><b>관리자 리스트</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:0; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-lg-1"></div>
											<div class="search-section">
												<div class="pull-left mr30">
													<label>
													<select id="searchType" class="selectpicker form-control form-control-middle" data-container="body">
															<option value="adminName">성명</option>
															<option value="adminDept">소속</option>
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
								<div class="col-sm-12 text-right">
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:deleteRow();">관리자 삭제하기</button>
									<button class="btn waves-effect waves-light" type="button" onclick="javascript:popup('reg');">계정 추가하기</button>
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
