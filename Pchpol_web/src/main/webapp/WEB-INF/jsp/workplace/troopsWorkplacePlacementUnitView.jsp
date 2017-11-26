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
function checkedUnit(el){
	if($(el).prop('checked')){
		
		var mobilDate = window.parent.opener.jQuery("#dateInfo").val();
		var workplaceNo = window.parent.opener.workplaceNo;
		var unitNo = $(el).val(); 
		$.ajax({
			type : 'GET',
			url : encodeURI("${pageContext.request.contextPath}/workplace/troopsWorkplaceDetail?1=1"
					+"&mobilDate="+mobilDate
					+"&workplaceNo="+workplaceNo
					+"&unitNo="+unitNo),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			cache : false,
			success : function(res){
				if(res.success){
					if(res.data.length>0){
						alert('다른 근무지에 배치된 인원입니다.\n'+res.data[0].code2.code1depth+' '+res.data[0].code2.code2depth+' '+res.data[0].code2.code3depth+' '+res.data[0].code2.code4depth);
						$(el).prop('checked',false);
					}
				}
			}
		});
	}
}
jQuery(document).ready(function($) {
	var troopsInfo = troops[0].code1depth+' '+troops[0].code2depth+' '+troops[0].code3depth;
	$("#troopsInfo").html(troopsInfo);
	setUnitList(troops[0]); 
	if(troops.length==1&&(troops[0].code4depth==null||troops[0].code4depth=='')){
		 $('#troopsSelect').hide();
	}else{
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
			location.href=encodeURI("${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementUnitView.do?time=${time}"+troosNoParmas);
		});
	}
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
	if(a==0){
		alert('선택된 인원이 없습니다.');
		return false;
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
function autoReg(){
	var group = window.parent.opener.jQuery("input:checkbox[name='troopsNo']");
	$.each(group, function(idx, el){
		if($(el).val()==$('select#troops_code4depth').val()){
			if (!$(el).is(":checked")) {
				//$(el).trigger('click');
				$(el).prop('checked',true);
			}
		}
	});
	
	window.parent.opener.initWorktimelist();
	window.parent.opener.setWorktimelist();
	
	var chklist = $(':checkbox[name=unitNo	]:checked');
	if(chklist.length==0){
		alert('선택된 인원이 없습니다.');
		return false;
	}
	var maxN = chklist.length-1;
	var initN = 0;
	for(var i = 1 ; i <= 24; i++){
		var tname = troops[0].code4depth;
		if(troops[0].code4depth == ''){
			tname = troops[0].code3depth;
		}
		var txt =tname+': ';
		txt +=
			'<input type="hidden" name="time'+i+'" value="'+$(chklist[initN]).val()+'">'
			+$(chklist[initN]).next('label').text();
		window.parent.opener.jQuery("#"+'time'+i).html(txt);
		if(initN==maxN){
			initN=0;
		}else{
			initN++;
		}
	}
	
	window.parent.close();
}
function setUnitList(troops){
	$.ajax({
		type : 'GET',
		url : encodeURI("${pageContext.request.contextPath}/unit/list?listType=list"
				+"&code1.code1depth="+troops.code1depth
				+"&code1.code2depth="+troops.code2depth
				+"&code1.code3depth="+troops.code3depth
				+"&code1.code4depth="+troops.code4depth
				+"&rows=10000"),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		cache : false,
		success : function(res){
			if(res.success){
				var txt='';
				$.each(res.data, function(index,text){
					var rank = '';
					if(text.code2 && typeof text.code2.code1depth !== 'undefined' ){
						rank = text.code2.code1depth;						
					}
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
						txt+='<input type="checkbox" name="unitNo" value="'+text.unitNo+'" checked onclick="javascript:checkedUnit(this);">';
					}else{
						txt+='<input type="checkbox" name="unitNo" value="'+text.unitNo+'" onclick="javascript:checkedUnit(this);">';
					}
					txt+='<label style="font-size: 0.6em;">'
					+'<input type="hidden" name="${time}" value="'+text.unitNo+'">'
					+text.unitName+' '+rank
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
								<label class="col-xs-2 col-sm-2 control-label text-right" id="troopsSelect">
								<select class="selectpicker form-control" data-container="body" data-size="15" required data-width="auto" id="troops_code4depth">
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
										<button class="btn btn-inverse btn-rounded waves-effect" onclick="javascript:autoReg();">
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