<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">
var troops = {
		codeNo: '${tfp.troops.codeNo}',
		code1depth: '${tfp.troops.code1depth}',
		code2depth: '${tfp.troops.code2depth}',
		code3depth: '${tfp.troops.code3depth}',
		code4depth: '${tfp.troops.code4depth}'
} 
jQuery(document).ready(function($) {
	$("#troopsInfo").html(troops.code1depth+' '+troops.code2depth+' '+troops.code3depth+' '+troops.code4depth);
	dropdwonOption.submitFieldName =  "facilityMobilStartDt";
	$("#facilityMobilStartDt").dateDropdowns(dropdwonOption);
	dropdwonOption.submitFieldName =  "facilityMobilEndDt";
	$("#facilityMobilEndDt").dateDropdowns(dropdwonOption);
});
var viewObj;
function reg(){
	$.each($("input[name=facilityNo]"), function(idx, obj){
		if($(obj).val()==''){
			$(obj).remove();	
		}
	});
	sendFormByAjax();
	//self.close();
	return false;
}
function add(divname){
	var facNo = $("#"+divname+"No").val();
	if(!facNo || facNo==''){
		return false;
	}
	var facName = $("select[id="+divname+"] option[value='"+facNo+"']").text();
	var txt = "<span id='added"+facNo+"'>"
			+"<input type='hidden' name='facilityNo' value='"+facNo+"'>"
			+facName
			+"</span>";
	if($("#"+divanme).text().length!=0){
		txt = ', '+txt;
	}
	$("#"+divanme).html(txt);
}
function findFacPop(facType, curIdx){
	var area = "";
   	var popWidth = 1024;
	var popHeight = 740;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	var url = "${pageContext.request.contextPath}/facility/findFac.do?facType="+facType+"&curIdx="+curIdx;
	win = window.open(encodeURI(url),'',param);

	return false;
}
function addFac(facType, idx){
	var txt = '';
	var i = Number(idx)+1;
	txt+='<div class="form-group">'
		+'<label class="col-xs-6 col-sm-6 text-right">'
		+'<input type="hidden" name="facilityNo" id="'+facType+'No'+i+'" >'
		+'<input class="form-control" type="text" placeholder="검색해서 입력하세요." id="'+facType+'Addr'+i+'" >'
		+'</label>'
		+'<label class="col-xs-2 col-sm-2 text-left">'
		+'<button type="button" class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="return findFacPop(\''+facType+'\',\''+(i)+'\');">시설검색</button>'
		+'</label>'
		+'<label class="col-xs-4 col-sm-4 control-label text-right">'
		+'<button type="button" class="btn btn-silver btn-rounded waves-effect" onclick="return addFac(\''+facType+'\',\''+(i)+'\');">'
		+'추가하기'
		+'</button>'
		+'</label>'
		+'</div>';
	$("#"+facType+"list").append(txt);
	return false;
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
					<span>소속별 숙영, 급식 시설 적용하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/facility/troopsFacilityPlacement" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;소속</label>
								<label class="col-xs-10 col-sm-10 margin-top5 control-label input-label text-left" id="troopsInfo">
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;동원기간</label>
								<div class="col-xs-4 col-sm-4 text-right">
									<input type="hidden" id="facilityMobilStartDt">
								</div>
								<div class="col-xs-1 col-sm-1 text-center">
								~
								</div>
								<div class="col-xs-5 col-sm-5 text-left">
									<input type="hidden" id="facilityMobilEndDt">
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;숙영시설</label>
								<label class="col-xs-10 col-sm-10 control-label" id="bedlist">
									<div class="form-group">
										<label class="col-xs-6 col-sm-6 text-right">
											<input type="hidden" name="facilityNo" id="bedNo1" >
											<input class="form-control" type="text" placeholder="검색해서 입력하세요." id="bedAddr1" readonly>
										</label>
										<label class="col-xs-2 col-sm-2 text-left">
											<button type="button" class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="return findFacPop('bed','1');">시설검색</button>
										</label>
										<label class="col-xs-4 col-sm-4 control-label text-right">
											<button type="button" class="btn btn-silver btn-rounded waves-effect" onclick="return addFac('bed','1')">
											추가하기
											</button>
										</label>
									</div>
								</label>
							</div>
							<!-- 
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;급식시설</label>
								<label class="col-xs-10 col-sm-10 control-label" id="foodlist">
									<div class="form-group">
										<label class="col-xs-6 col-sm-6 text-right">
											<input type="hidden" name="facilityNo" id="foodNo1" >
											<input class="form-control" type="text" placeholder="검색해서 입력하세요." id="foodAddr1" readonly>
										</label>
										<label class="col-xs-2 col-sm-2 text-left">
											<button type="button" class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="return findFacPop('food','1');">시설검색</button>
										</label>
										<label class="col-xs-4 col-sm-4 control-label text-right">
											<button type="button" class="btn btn-silver btn-rounded waves-effect" onclick="return addFac('food','1')">
											추가하기
											</button>
										</label>
									</div>
								</label>
							</div>
							 -->
							<div class="form-group">
								<div class="text-center">
									<button type="button" class="btn btn-silver btn-rounded waves-effect" onclick="javascript:reg();" id="submitBtn">
										시설 추가/수정하기
									</button>
									<button type="button" class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden" name="troops.codeNo" value="${tfp.troops.codeNo}">
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>