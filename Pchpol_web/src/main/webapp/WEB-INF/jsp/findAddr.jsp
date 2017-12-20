<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">
var zip = '${zip}';
var area = '${area}';
var listObj;
function selectRow(rowid){
	var address = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'address');
	var zipcode = jQuery(listObj.grid).jqGrid ('getCell', rowid, 'zipCode');
	if (confirm("이 주소를 입력하시겠습니까?\n"+zipcode+" "+address) == true){  
		window.opener.$("#facilityAddrZip").val(zipcode);
		window.opener.$("#facilityAddrJuso").val(address);
		self.close();
	}else{   
	    return;
	}
}
function getList(){
	jQuery(listObj.grid).jqGrid('setGridParam',{url:encodeURI(listObj.url+"&roadNm="+$("#roadNm").val()+"&buildingMainNm="+$("#buildingMainNm").val()),page:1}).trigger("reloadGrid");
}
jQuery(document).ready(function($) {
	listObj = new ListObj();
	listObj.grid = "#list-grid";
	listObj.pager = "#list-pager";
	listObj.url = "${pageContext.request.contextPath}/addr/findAddr?listType=jqgrid";
	listObj.colNames = ['우편번호','주소'];
	listObj.colModel = [
							{name:'zipCode', sortable: false, width:"20", align:"center"},
	               	   		{name:'address', sortable: false, width:"80", align:"left"}
	               	   	];
	listObj.idColName = 'zipCode';
	listObj.jqgrid(selectRow);
});
function reg(){
	return false;
}
function findAddr(){
	var roadNm = $("#roadNm").val();
	var mainNm = $("#buildingMainNm").val();
	if(roadNm==''){
		alert('도로명을 입력해주세요');
		return false;
	}
	if(mainNm==''){
		alert('건물번호를 입력해주세요');
		return false;
	}
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
					<span>주소검색</span>
				</h4>
				<form onsubmit="return false" class="form-horizontal" action="#">
				<div class="col-xs-12 col-sm-12 col-lg-12">
					<div class="card-box updown-padding">
						<div class="form-group bottom-line">
							<label class="col-xs-12 col-sm-12 col-md-12 control-label text-center input-label">
							&nbsp;검색을 원하는 지역의 도로명, 건물번호를 입력하세요. 예) 반포대로 58</label>
						</div>
						<div class="form-group bottom-line">
							<div class="col-xs-1 col-sm-1 col-md-1">
							</div>
							<div class="col-xs-5 col-sm-5 col-md-5">
								<input class="form-control" required="" type="text" placeholder="도로명을 입력하세요." id="roadNm" required>
							</div>
							<div class="col-xs-3 col-sm-3 col-md-3">
								<input class="form-control" required="" type="text" placeholder="건물번호를 입력하세요." id="buildingMainNm" required>
							</div>
							<div class="col-xs-2 col-sm-2 col-md-2 text-left" style="padding: 0;">
								<button class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="javascript:findAddr();">검색</button>
							</div>
							<div class="col-xs-1 col-sm-1 col-md-1">
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