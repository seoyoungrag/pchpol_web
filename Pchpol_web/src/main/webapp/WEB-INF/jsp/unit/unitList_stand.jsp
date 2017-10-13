<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script>
function setSelectPickerTroopsType(){
	$.ajax({
	 type: "GET",
	 url:'${pageContext.request.contextPath}/code/list/troops', 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(res) {
			 if(res.success){
				var code1depth = [];
				var code2depth = [];
				var code3depth = [];
				var code4depth = [];
				$.each(res.data, function (index, text) {
					if($.inArray(text.code1depth, code1depth) == -1 ){
						code1depth.push(text.code1depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code2depth, code2depth) == -1 ){
						code2depth.push(text.code2depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code3depth, code3depth) == -1 ){
						code3depth.push(text.code3depth)
					}
				});
				$.each(res.data, function (index, text) {
					if($.inArray(text.code4depth, code4depth) == -1 ){
						code4depth.push(text.code4depth)
					}
				});
				$.each(code1depth, function (index, text) {
				    $('select#code1depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code2depth, function (index, text) {
				    $('select#code2depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code3depth, function (index, text) {
				    $('select#code3depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$.each(code4depth, function (index, text) {
				    $('select#code4depth').append($('<option>', {
				        value: text,
				        text : text
				    }));
				});
				$('.selectpicker').selectpicker('refresh')
			 }else{
					alert('데이터 조회를 실패하였습니다.');
			 }
		 },
			error : function(res){
					alert('데이터 조회를 실패하였습니다.');
			}
		});
}
function setSelectpickerByCode(divName, category, depth, selectVal){
	$.ajax({
	 type: "GET",
	 url:'${pageContext.request.contextPath}/code/list/'+category, 
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(res) {
			 if(res.success){
				if(typeof depth === 'undefined'){
					$.each(res.data, function (index, text) {
					 if(depth=='1'){
						    $('select#'+divName).append($('<option>', {
						        value: res.data[index].codeNo,
						        text : res.data[index].code1depth
						    }));
					 }else if(depth=='2'){
						    $('select#'+divName).append($('<option>', {
						        value: res.data[index].codeNo,
						        text : res.data[index].code2depth
						    }));
					 }else if(depth=='3'){
						    $('select#'+divName).append($('<option>', {
						        value: res.data[index].codeNo,
						        text : res.data[index].code3depth
						    }));
					 }else if(depth=='4'){
						    $('select#'+divName).append($('<option>', {
						        value: res.data[index].codeNo,
						        text : res.data[index].code4depth
						    }));
					 }else if(depth=='5'){
						    $('select#'+divName).append($('<option>', {
						        value: res.data[index].codeNo,
						        text : res.data[index].code5depth
						    }));
					 }
					});
				}else{
					$.each(res.data, function (index, text) {
					    $('select#'+divName).append($('<option>', {
					        value: res.data[index].codeNo,
					        text : res.data[index].code1depth
					    }));
					});
				}
				if(typeof selectVal === 'undefined'){
				}else{
					$('select#'+divName).val(selectVal);
				}
				$('.selectpicker').selectpicker('refresh')
			 }else{
					alert('데이터 조회를 실패하였습니다.');
			 }
		 },
			error : function(res){
					alert('데이터 조회를 실패하였습니다.');
			}
		});
}
jQuery(function ($) {
	setSelectPickerTroopsType();
	/* 
	setSelectpickerByCode('code1.code1depth','troops','1');
	setSelectpickerByCode('code1.code2depth','troops','2');
	setSelectpickerByCode('code1.code3depth','troops','3');
	setSelectpickerByCode('code1.code4depth','troops','4');
	 */
   	jQuery(grid).jqGrid({
   		url : url,
   		datatype: "json",
   		jsonReader : {
   		          		root: "data.rows", //게시글 리스트
   		          		page: "data.page", //현재 페이지
   						total: "data.total", //전체 페이지
   						records: "data.records" //전체 레코드
   					},
   	   	colNames:['No.','지방청','구분','부대명','세부소속','계급','성명','폴넷아이디','생년월일'],
   	   	colModel:[
   	   		{name:'unitNo',hidden:true, index:'adminNo', width:"30", align: "center"},
   	   		{name:'code1.code1depth', width:"70", align:"center"},
   	   		{name:'code1.code2depth', width:"120", align: "center"},
   	   		{name:'code1.code3depth', width:"70", align:"center"},
   	   		{name:'code1.code4depth', width:"70", align:"center"},
   	   		{name:'code2.code1depth', width:"70", align:"center"},
   	   		{name:'unitName', width:"70", index:'unitName', align:"center"},
   	   		{name:'unitPolId', width:"120", index:'unitPolId', align:"center"},
   	   		{name:'unitBirth',  width:"70", index:'unitBirth', align:"center"}	
   	   	],
		styleUI : 'Bootstrap',
        rownumbers: true,
		multiselect: false,
        height : 'auto',
   	   	rowNum:10,
   	   	rowList:[10,20,30],
   	   	pager: pager,
   	   	sortname: 'unitNo',
   	    viewrecords: true,
   	    sortorder: "desc",
   	    autowidth:true,
   	    shrinkToFit: true,
	   	onSelectRow:function(rowid){
	   		var id = jQuery(grid).jqGrid ('getCell', rowid, 'unitNo');
	   		popup('view','stand',id);
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
var url = "${pageContext.request.contextPath}/unit/list/stand?listType=jqgrid";
function gridReload(){
	jQuery(grid).jqGrid('setGridParam',{
		url:encodeURI(
				url
				+"&code1depth="+jQuery("#code1depth").val()
				+"&code2depth="+jQuery("#code2depth").val()
				+"&code3depth="+jQuery("#code3depth").val()
				+"&code4depth="+jQuery("#code4depth").val()
				+"&searchType=unitName&searchWord="+
				jQuery("#searchWord").val()
				)
				,page:1
				}).trigger("reloadGrid");
}

function popup(type, unitType, rowid){
	var windowtitle ='';
	if(type=='view'){
		windowtitle='상설부대원 보기';
	}else if(type=='reg'){
		windowtitle='상설부대원 등록';
	}
   	var popWidth = 640;
  		var popHeight = 400;
  		var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	window.open("${pageContext.request.contextPath}/unit/view.do?type="+type+"&unitTYpe="+unitType+"&unitNo="+rowid,windowtitle,param);
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
					<div class="col-sm-5">
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>상설부대원 리스트</b></h4>
					</div>
					<div class="col-sm-7 text-right" style="padding-right:30px;">
						<button class="btn btn-default waves-effect waves-light" type="button" onclick="javascript:popup('reg','stand');">등록</button>
					</div>
					<form onsubmit="gridReload(); return false" class="form-horizontal" role="form" id="noticeSearchForm" method="post" action="#" accept-charset="utf-8">
						<div class="col-sm-12" style="">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
								<div class="row">
									<div class="col-md-11">
										<div class="row">
											<div class="col-xs-1 col-sm-3 col-md-3 col-lg-6"></div>
											<div class="col-xs-5 col-sm-5 col-md-5 col-lg-4">
												<label>
												<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code1.code1depth" id="code1depth">
													<option value="">지방청</option>
												</select>
												<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code1.code2depth" id="code2depth">
													<option value="">구분</option>
												</select>
												<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code1.code3depth" id="code3depth">
													<option value="">부대명</option>
												</select>
												<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="code1.code4depth" id="code4depth">
													<option value="">세부소속</option>
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
