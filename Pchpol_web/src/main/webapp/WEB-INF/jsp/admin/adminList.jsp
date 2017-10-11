<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="common/header.jsp" flush="false" />
<script>
jQuery(function ($) {
   	jQuery(grid).jqGrid({
   		url : url,
   		datatype: "json",
   		jsonReader : {
   		          		root: "data.rows", //게시글 리스트
   		          		page: "data.page", //현재 페이지
   						total: "data.total", //전체 페이지
   						records: "data.records" //전체 레코드
   					},
   	   	colNames:['No.','권한','소속','계급','성명','비고','아이디','패스워드'],
   	   	colModel:[
   	   		{name:'adminNo',hidden:true, index:'adminNo', width:"30", align: "center"},
   	   		{name:'code.code1depth', index:'code', width:"70",align:"center"},
   	   		{name:'adminDept',index:'adminDept', width:"120", align: "center"},
   	   		{name:'adminRank', index:'adminRank', width:"70",align:"center"},
   	   		{name:'adminName',index:'adminName', width:"70", align:"center"},
   	   		{name:'adminEtc',index:'adminEtc', width:"200", align:"center"},
   	   		{name:'adminId', hidden:true, index:'adminId'},		
   	   		{name:'adminPassword',hidden:true,index:'adminPassword'}
   	   	],
		styleUI : 'Bootstrap',
        rownumbers: true,
		multiselect: false,
        height : 'auto',
   	   	rowNum:10,
   	   	rowList:[10,20,30],
   	   	pager: pager,
   	   	sortname: 'adminNo',
   	    viewrecords: true,
   	    sortorder: "desc",
   	    autowidth:true,
   	    shrinkToFit: true,
	   	onSelectRow:function(rowid){
	   		var id = jQuery(grid).jqGrid ('getCell', rowid, 'adminNo');
	   		popup('view',id);
	   	},
	   	loadError : function(xhr,st,err) { 
	   	    alert("리스트갱신이 실패했습니다.\nType: "+st+"; Response: "+ xhr.status + " "+xhr.statusText);
	   	},
   	    loadComplete : function(res)
   		{	
   	    	if(!res.success){
	   	    	alert("리스트갱신이 실패했습니다.\nmessage: "+res.message+"; execDt: "+ res.execDt);
   			}
   	    }
   	});
});        
var grid = "#list-grid";
var pager = "#list-pager";
var url = '${pageContext.request.contextPath}/admin/list?listType=jqgrid';
function gridReload(){
	jQuery(grid).jqGrid('setGridParam',{url:encodeURI(url+"&searchType="+jQuery("#searchType").val()+"&searchWord="+jQuery("#searchWord").val()),page:1}).trigger("reloadGrid");
}

function popup(type, rowid){
	var windowtitle ='';
	if(type=='view'){
		windowtitle='관리자정보 보기';
	}else if(type=='reg'){
		windowtitle='관리자정보 등록';
	}
   	var popWidth = 640;
  		var popHeight = 400;
  		var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/admin/view.do?type="+type+"&adminNo="+rowid,windowtitle,param);
}
</script>
</head>
<body>
	<!-- Begin menu -->
	<header> 
	<!-- 로고를 상단 가운데에 따로 띄우려면 topbar 주석 -->
	<div class="container-fluid">
		<div class="topbar">
			<jsp:include page="common/topmenu.jsp" flush="false" />
		</div>
	</div>
	</header>
	<!-- menu End --> 
	<!-- Body -->
	<div class="wrapper">       
		<div id="content-page" class="content-page">
			<div class="content" style="margin-top:60px;">
				<div class="container" style="">
					<div class="col-sm-5">
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>관리자 리스트</b></h4>
					</div>
					<div class="col-sm-7 text-right" style="padding-right:30px;">
						<button class="btn btn-default waves-effect waves-light" type="button" onclick="javascript:popup('reg');">등록</button>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" id="noticeSearchForm" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-11">
										<div class="row">
											<div class="col-xs-3 col-sm-6 col-md-6 col-lg-9"></div>
											<div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
												<label>
												<select id="searchType" class="form-control form-control-middle">
														<option value="adminName">성명</option>
														<option value="adminDept">소속</option>
												</select>
												</label>
											</div>
											<div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
											<label>
												<input type="text" id="searchWord" class="form-control form-control-middle" placeholder="" value="">
											</label>
											</div>
											<div class="col-xs-3 col-sm-2 col-md-2 col-lg-1">
												<div class="form-group" style="margin-bottom:10px;">
													<div class="col-md-12 text-left">
													<button class="btn btn-primary waves-effect waves-light" type="button" onclick="gridReload();">검색</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-1">
										<!-- <form class="form-horizontal" role="form"> -->
										<div class="form-group" style="margin-bottom:10px; margin-right:-10px;">
											<div class="col-md-12 text-right">
										    </div>
										</div>
										<!-- </form> -->
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
