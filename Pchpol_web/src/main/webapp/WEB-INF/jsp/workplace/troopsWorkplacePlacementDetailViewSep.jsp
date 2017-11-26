<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">

var initWidth;
var initHeight;

var beforePrint = function(mapId) {
    //initWidth = window.innerWidth+window.outerWidth;
    //initHeight = window.innerHeight+window.outerHeight;
    //window.resizeTo(initWidth,2000);
};
var afterPrint = function() {
	//window.resizeTo(initWidth,initHeight);
};
if (window.matchMedia) {
    var mediaQueryList = window.matchMedia('print');
    mediaQueryList.addListener(function(mql) {
        if (mql.matches) {
            beforePrint();
        } else {
            afterPrint();
        }
    });
}

window.onbeforeprint = beforePrint;
window.onafterprint = afterPrint;
function printW(){
    $("#worktimelist").height($("#worktimelist").children().length*55);
    window.print();
	//pageprint("regForm");
}
var troops = {
		codeNo: '${troops.codeNo}',
		code1depth: '${troops.code1depth}',
		code2depth: '${troops.code2depth}',
		code3depth: '${troops.code3depth}',
		code4depth: '${troops.code4depth}'
} 
var workplace = {
		codeNo: '${workplace.codeNo}',
		code1depth: '${workplace.code1depth}',
		code2depth: '${workplace.code2depth}',
		code3depth: '${workplace.code3depth}',
		code4depth: '${workplace.code4depth}'
}
var mobilDate = '${mobilDate}';
jQuery(document).ready(function($) {
	$("#workplaceInfo").html(workplace.code1depth+'시 '+workplace.code2depth+' '+workplace.code3depth+' '+workplace.code4depth);
	$("#dateInfoDesc").html(dtConvertDashToTxt(mobilDate));
	$("#dateInfo").val(mobilDate);
	$("#code1_code1depth").html(troops.code1depth);
	$("#code1_code2depth").html(troops.code2depth);
	$("#code1_code3depth").html(troops.code3depth+' '+troops.code4depth);
	setWorktimelist();
	if($("#code1_code2depth").val()=='경찰관 기동대'){
		$("#code1_code3depth_value").html("&nbsp;부대명");
	}else if($("#code1_code2depth").val()=='의경 중대'){
		$("#code1_code3depth_value").html("&nbsp;중대");
	}
	chgTroops();
});
function chgTroops(){
	$.ajax({
		type : 'GET',
		url : '${pageContext.request.contextPath}/workplace/workplaceTroopsDetail?workplaceNo='+workplace.codeNo+'&troopsNo='+troops.codeNo+'&mobilDate=${mobilDate}',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		cache : false,
		async: true,
		success : function(res){
			if(res.success){
				for(var i =1; i < 25;i++){
					$("#time"+i).html("");
				}
				if(res.data){
					$.each(res.data,function(idx, data){
						var a = 0;
						var txt = '';
						$.each(data, function(idxt, time){
							txt+='<input type="hidden" name="'+idx+'" value="'+time.unitNo+'">'
							+time.unitName;
							if(time.code2){
								txt+=' '+time.code2.code1depth
							}
							txt+=', ';
							a++;
						});
						if(a>0){
							txt = txt.slice(0,-2);
						}
						$("#"+idx).html(txt);
				    });
				}
			}else{
				alert('데이터 조회를 실패하였습니다.');
			}
		},
		error : function(res){
				alert('데이터 조회를 실패하였습니다.');
		}
	});
}
function setWorktimelist(){
	var txt='';
	for(var i=0;i<24;i++){
		var stime=i;
		var etime=i+1;
		if(i<10){
			stime='0'+i;
		}
		if(i<9){
			etime='0'+(i+1);
		}
		txt+='<div class="form-group bottom-line">'
		+'<label class="col-xs-3 col-sm-3 margin-top5 control-label text-right">&nbsp;'+stime+':00~'+etime+':00</label>'
		+'<label class="col-xs-9 col-sm-9 control-label text-center">'
		+'<span id="time'+(i+1)+'" style="font-size: 0.8em;"></span>'
		+'</label>'
		+'</div>'
		;
	}
	$("#worktimelist").html(txt);
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
					<span>근무지 배치 등록하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementDetail" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;근무정보</label>
								<label class="col-xs-10 col-sm-10 margin-top5 control-label input-label text-left" id="workplaceInfo">
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;지방청</label>
								<label class="col-xs-5 col-sm-5 margin-top5 control-label input-label text-left" id="code1_code1depth">
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;구분
								</label>
								<label class="col-xs-3 col-sm-3 margin-top5 control-label input-label text-left" id="code1_code2depth" >
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label" id="code1_code3depth_value">
								&nbsp;부대명
								</label>
								<label class="col-xs-5 col-sm-5 margin-top5 control-label input-label control-label text-left" id="code1_code3depth">
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;근무날짜
								</label>
								<label class="col-xs-3 col-sm-3 margin-top5 control-label input-label text-left" id="dateInfoDesc">
								</label>
								<input type="hidden" name="mobilDate" id="dateInfo">
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;근무시간
								</label>
								<label class="col-xs-10 col-sm-10 control-label" id="worktimelist" style="height:300px;overflow-y:scroll;overflow-x:hidden;">
								</label>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button type="button" class="btn btn-silver btn-rounded waves-effect waves-light" onclick="javascript:printW();">
										출력하기
									</button>
									<button type="button"  class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										닫기
									</button>
								</div>
							</div>
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>