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
var troops = new Array(); 
<c:forEach items="${troopsList}" var="item">
var t = {
		codeNo: '${item.codeNo}',
		code1depth: '${item.code1depth}',
		code2depth: '${item.code2depth}',
		code3depth: '${item.code3depth}',
		code4depth: '${item.code4depth}',
} 
troops.push(t);
</c:forEach>
var checkedUnitNo = new Array(); 
<c:forEach items="${checkedUnitNo}" var="item">
checkedUnitNo.push('${item}');
</c:forEach>
jQuery(document).ready(function($) {
	var troopsInfo = troops[0].code1depth+' '+troops[0].code2depth+' '+troops[0].code3depth;
	$("#troopsInfo").html(troopsInfo);
	setUnitList(troops[0]); 
	$.each(troops, function(idx, tp){
	    $('select#troops_code4depth').append($('<option>', {
	        value: tp.codeNo,
	        text : tp.code4depth
	    })); 
	});
	$('select#troops_code4depth').val(troops[0].codeNo);
	$('.selectpicker').selectpicker('refresh');
	$("#troops_code4depth").change(function() {
		var troosNoParmas = '&troopsNo='+$('select#troops_code4depth').val();
		$.each(troops, function(idx, text){
			if(text.codeNo!=$('select#troops_code4depth').val()){
				troosNoParmas+= '&troopsNo='+text.codeNo;
			}
		});
		location.href="${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementUnitView.do?time=${time}"+troosNoParmas;
	});
});
function reg(){
	var txt =troops[0].code4depth+': ';
	var a = 0;
	$.each($(':checkbox[name=unitNo	]:checked'),function(idx, el){
		a++;
		txt+= $(this).next('label').html()+', ';
    });
	if(a>0){
		txt = txt.slice(0,-2);
	}else{
		txt = '';
	}
	var group = window.parent.opener.jQuery("input:checkbox[name='troopsNo']");
	$.each(group, function(idx, el){
		if($(el).val()==$('select#troops_code4depth').val()){
			if (!$(el).is(":checked")) {
				//$(el).trigger('click');
				$(el).prop('checked',true);
			}
			window.parent.opener.chgTroops($(el),"${time}",txt);
		}
	});
	window.parent.close();
}
function setUnitList(troops){
	$.ajax({
		type : 'GET',
		url : "${pageContext.request.contextPath}/unit/list?listType=list"
				+"&code1.code1depth="+troops.code1depth
				+"&code1.code2depth="+troops.code2depth
				+"&code1.code3depth="+troops.code3depth
				+"&code1.code4depth="+troops.code4depth
				+"&rows=10000",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		cache : false,
		success : function(res){
			if(res.success){
				var txt='';
				$.each(res.data, function(index,text){
					if((index+1)%5==0){
						txt+='<div class="form-group bottom-line">';
					}
					txt+='<label class="col-xs-2 col-sm-2 control-label text-center">';
					var isChecked = false;
					$.each(checkedUnitNo, function(index, no){
						if(no==text.unitNo){
							isChecked = true;
						}
					});
					if(isChecked){
						txt+='<input type="checkbox" name="unitNo" value="'+text.unitNo+'" checked>';
					}else{
						txt+='<input type="checkbox" name="unitNo" value="'+text.unitNo+'">';
					}
					txt+='<label style="font-size: 0.6em;">'
					+'<input type="hidden" name="${time}" value="'+text.unitNo+'">'
					+text.unitName+' '+text.code2.code1depth
					+'</label>'
					+'</input>'
					+'</label>';
					if((index+1)%5==0){
						txt+='</div>';
					}
				});
				$("#unitList").html(txt);
			}else{
				alert('데이터 조회를 실패하였습니다.');
			}
		},
		error : function(res){
				alert('데이터 조회를 실패하였습니다.');
		}
	});
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
					<span>인원 등록하기</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<!-- <form onsubmit="return reg()" class="form-horizontal"> -->
						<div class="form-horizontal">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;부대</label>
								<label class="col-xs-8 col-sm-8 margin-top5 control-label input-label text-left" id="troopsInfo">
								</label>
								<label class="col-xs-2 col-sm-2 control-label text-right">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="troops_code4depth">
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;인원
								</label>
								<label class="col-xs-10 col-sm-10 control-label" id="unitList" style="height:300px;overflow-y:scroll;overflow-x:hidden;">
								</label>
							</div>
							<div class="form-group">
								<div class="col-xs-12">
									<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									</label>
									<div class="col-xs-3 col-sm-3 text-left">
										<button class="btn btn-inverse btn-rounded waves-effect" onclick="return false">
											선택 인원 반복 설정
										</button>
									</div>
									<div class="col-xs-7 col-sm-7 text-right">
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn" onclick="javascript:reg();">
										인원 등록하기
									</button>
									<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:window.parent.close();">
										취소
									</button>
									</div>
								</div>
							</div>
						<!-- </form> -->
						</div>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>