<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
	getList();
	$('.selectpicker').selectpicker('refresh');
});    
function deleteRow(){
	var rows = jQuery(listObj.grid).find('input:checked');
	if(rows.length==0){
		alert('삭제할 행을 선택해 주세요.');
		return false;
	}
	if(confirm('선택한 게시물을 삭제하시겠습니까?')){
	var ids = [];
	$.each(rows, function(idx, txt){
		ids.push($(txt).attr('uid'));		
	});
	$.ajax({
	 type: "POST",
	 url:contextPath+'/board/delete', 
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
function adminFormatter(c,o,r){
	if(r.unit!= null && typeof r.unit !== 'undefined' ){
		return r.unit.unitName;
	}else{
		return '';
	}
	//return '';
}
function troopsFormatter(c,o,r){
	if(r.unit!= null && typeof r.unit !== 'undefined' ){
		return r.unit.code1.code1depth+' '+r.unit.code1.code2depth+' '+r.unit.code1.code3depth+' '+r.unit.code1.code4depth;
	}else{
		return '';
	}
	//return '';
}
function getList(){
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/board/list?listType=jqgrid&boardType=normal";
	listObj.colNames = ['boardNo','','No.','구분','내용','부대명','작성자','게시일'];
	listObj.colModel = [
	           	   		{name:'boardNo',hidden:true, index:'boardNo', width:"30", align: "center"},
						{name: "select", width: "40", sortable:false, resizable:false, hidedlg:true, search:false, align:"center", fixed:true,
                            classes: "defaultCursor",
                            formatter: function (c,o,r) {
        			   			var id = r.boardNo;
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
	           	   		{name:'boardCategory', width:"70",align:"center"},
	           	   		{name:'boardTitle', width:"200", align: "left"},
	           	   		{name:'unit',  width:"70",align:"center", formatter:troopsFormatter},
	           	   		{name:'unit', width:"70", align:"center", formatter:adminFormatter},
	           	   		{name:'boardWriteDt', width:"100", align:"center", formatter:dateFormatter}
	           	   		];
	listObj.idColName = 'boardNo';
	listObj.preventSelectCell = ['select','No'];
	listObj.jqgrid();
}
function gridReload(){
	jQuery(listObj.grid).jqGrid('setGridParam',{url:encodeURI(listObj.url+"&searchType=boardTitle&searchWord="+jQuery("#searchWord").val()+"&boardCategory="+jQuery("#boardCategory").val()),page:1}).trigger("reloadGrid");
}
function popup(type, boardNo){
   	var popWidth = 840;
  	var popHeight = 800;
  	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/board/view.do?type="+type+"&boardType=normal&boardNo="+boardNo,'',param);
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
						<h4 class="m-t-0 header-title"><b>상황보고&건의사항</b></h4>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:0; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-12">
										<div class="row">
											<div class="col-lg-1">
											</div>
											<div class="search-section">
												<div class="pull-left mr30">
													<label>
														<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="boardCategory">
															<option value="">전체</option>
															<option value="상황">상황</option>
															<option value="건의">건의</option>
														</select>
													</label>
												</div>
												<div class="pull-left">
													<label>
														<div class="form-group">
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
								<div class="col-sm-12 text-right" style="padding-top: 20px;">
									<c:if test="${admin.code.codeOrderNo eq '1'}">
										<button class="btn waves-effect waves-light" type="button" onclick="javascript:deleteRow();">게시물 삭제하기</button>
									</c:if>
								</div>
							</div>
							<!-- <div class="row">
								<div class="col-sm-12 text-right" style="padding-top: 20px;">
									<button class="btn btn-silver btn-rounded waves-effect waves-light" type="button" onclick="javascript:popup('reg');">등록하기</button>
								</div>
							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end body -->
	
</body>
</html>
