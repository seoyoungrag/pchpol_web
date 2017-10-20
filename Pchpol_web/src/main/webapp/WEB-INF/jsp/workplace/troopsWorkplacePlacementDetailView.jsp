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
<c:forEach items="${troops}" var="item">
var tp = {
		codeNo: '${item.codeNo}',
		code1depth: '${item.code1depth}',
		code2depth: '${item.code2depth}',
		code3depth: '${item.code3depth}',
		code4depth: '${item.code4depth}',
} 
troops.push(tp);
</c:forEach>
jQuery(document).ready(function($) {
	$("#workplaceInfo").html('${workplaceCode.code1depth}시 ${workplaceCode.code2depth} ${workplaceCode.code3depth} ${workplaceCode.code4depth}');
	$("#dateInfoDesc").html(dtConvertDashToTxt('${mobilDate}'));
	$("#dateInfo").val('${mobilDate}');
	setWorktimelist();
	//근무지(지역 배치시설)에 상설배치된 부대만 가져와야 한다.
	//1.상설부대리스트를 가져온다. -> List<Code>
	//2.code리스트에 속한 것만 가져온다.
	selectpickerObj = new SelectpickerObj();
	selectpickerObj.divName = 'code1_code1depth'; //지방청(소속)
	selectpickerObj.category = 'troops';
	selectpickerObj.troopstype = 'stand';
	selectpickerObj.depth = '1';
	selectpickerObj.restrictValue = troops; //List<Code>를 javascript로 변환한 값
	selectpickerObj.setByCode();
	
	$("#code1_code1depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code2depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '2';
		selectpickerObj.restrictValue = troops; //List<Code>를 javascript로 변환한 값
		selectpickerObj.parentVal = [$("#code1_code1depth").val()]; 
		$('#code1_code2depth').html('<option value="">구분 선택</option>');
		selectpickerObj.setByCode();
	});
	$("#code1_code2depth").change(function() { 
		selectpickerObj = new SelectpickerObj();
		selectpickerObj.divName = 'code1_code3depth';
		selectpickerObj.category = 'troops';
		selectpickerObj.troopstype = 'stand';
		selectpickerObj.depth = '3';
		selectpickerObj.restrictValue = troops; //List<Code>를 javascript로 변환한 값
		selectpickerObj.parentVal = [$("#code1_code1depth").val(),$("#code1_code2depth").val()]; 
		$('#code1_code3depth').html('<option value="">부대명 선택</option>');
		selectpickerObj.setByCode();
		if($("#code1_code2depth").val()=='경찰관 기동대'){
			$("#code1_code3depth_value").html("&nbsp;부대명");
		}else if($("#code1_code2depth").val()=='의경 중대'){
			$("#code1_code3depth_value").html("&nbsp;중대");
		}
	});
	$("#code1_code3depth").change(function() { 
		var code4depthTxt='';
		$.each(troops, function(index, text){
			if(text.code1depth==$("#code1_code1depth").val()&&text.code2depth==$("#code1_code2depth").val()&&text.code3depth==$("#code1_code3depth").val()){
				code4depthTxt+='<input type="checkbox" name="troopsNo" value="'+text.codeNo+'" onchange="chgTroops(this)"><label>'+text.code4depth+'</label></input>';	
			}
		});
		$("#troops_code4depth").html(code4depthTxt);
	});
	if('${viewType}'=='view'){
		getView();
		$(".header-title").html("근무지 배치 수정하기");
	}else if('${viewType}'=='reg'){}

});
function chgTroops(el,divId,txt){
	var $box = $(el);
	if ($box.is(":checked")) {
		var group = "input:checkbox[name='" + $box.attr("name") + "']";
		$(group).prop("checked", false);
		$box.prop("checked", true);
	} else {
		$box.prop("checked", false);
	}
	var troopsNo=$box.val();
	//제대별 근무자리스트 가져오기
	//제대와 근무일자를 통해서 workplace_placement의 키를 가져올 수 있고, 키로 workplace_placement_detail 테이블을 조회할 수 있다.
	$.ajax({
		type : 'GET',
		url : '${pageContext.request.contextPath}/workplace/workplaceTroopsDetail?troopsNo='+troopsNo+'&mobilDate=${mobilDate}',
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		cache : false,
		async: true,
		success : function(res){
			//res.data -> time1, time2, time3
			//res.data.time1 -> time1[0], time1[1]
			//res.data.time1[0] -> unitNo, unitName
			if(res.success){
				for(var i =1; i < 25;i++){
					$("#time"+i).html("");
				}
				if(res.data){
					$.each(res.data,function(idx, data){
						var a = 0;
						var txt =$box.next('label').text()+': ';
						$.each(data, function(idxt, time){
							txt+='<input type="hidden" name="'+idx+'" value="'+time.unitNo+'">'
							+time.unitName+' '+time.code2.code1depth+', ';
							a++;
						});
						if(a>0){
							txt = txt.slice(0,-2);
						}
						$("#"+idx).html(txt);
				    });
				}
				if(typeof divId !== 'undefined'&&typeof txt !== 'undefined'){
					jQuery("#"+divId).html(txt);
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
var selectpickerObj;

function getView(){}
function reg(){
	var a = 0;
	$.each($('form input[type=checkbox]:checked'),function(idx, el){
		a++;
    });
	if(a==0){
		alert('결과를 저장할 부대(지방청-구분-부대명-세부소속)를 선택해주세요.');
	}else{
		sendFormByAjax();
	}
	return false;
}
function regUnit(time){
	var troopsIdsSerialized = ''; 
	//체크는 한 제대만 되고, 체크한 제대가 리스트의 첫번째로 넘어가게 한다.
	$.each($('form input[type=checkbox]:checked'),function(idx, el){
		troopsIdsSerialized += '&' + $(el).attr('name') + '=' + $(el).val();
    });
	$.each($('form input[type=checkbox]')
			.filter(function(idx){return $(this).prop('checked') === false }), 
			function(idx, el){ 
				var emptyVal = ""; 
				troopsIdsSerialized += '&' + $(el).attr('name') + '=' + $(el).val(); 
			});

	var unitIdsSerialized = '';
	$.each($('form input[name='+time+']'),function(idx, el){
	//$.each($(':hidden [name='+time+']'),function(idx, el){
		unitIdsSerialized += '&unitNo' + '=' + $(el).val();
    });
	if(troopsIdsSerialized.length==0){
		alert('배치할 부대(지방청-구분-부대명)를 선택해주세요.');
	}else{
	   	var popWidth = 880;
		var popHeight = 540;
		var width = screen.width;
		var height = screen.height;
		var left = (screen.width/2)-(popWidth/2);
		var top = (screen.height/2)-(popHeight/2);
		var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
		win = window.open(null,'',param);
		var url = "${pageContext.request.contextPath}/workplace/troopsWorkplacePlacementUnitView.do"+"?time="+time+troopsIdsSerialized+unitIdsSerialized
	    win.document.write('<iframe width="100%", height="100%" src="'+url+'" frameborder="0" allowfullscreen></iframe>')
		
	}
	return false;
}
var win;
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
		+'<label class="col-xs-7 col-sm-7 control-label text-center">'
		+'<span id="time'+(i+1)+'" style="font-size: 0.8em;"></span>'
		+'</label>'
		+'<label class="col-xs-2 col-sm-2 control-label text-left">'
		+'	<button class="btn btn-silver btn-rounded waves-effect" onclick="return regUnit(\'time'+(i+1)+'\');">'
		+'	인원등록'
		+'	</button>'
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
								<label class="col-xs-5 col-sm-5 control-label text-left">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code1depth">
									<option value="">지방청 선택</option>
								</select>
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;구분
								</label>
								<label class="col-xs-3 col-sm-3 control-label text-left">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code2depth">
									<option value="">구분 선택</option>
								</select>
								</label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label" id="code1_code3depth_value">
								&nbsp;부대명
								</label>
								<label class="col-xs-2 col-sm-2 control-label text-left">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" id="code1_code3depth">
									<option value="">부대명 선택</option>
								</select>
								</label>
								<label id="troops_code4depth" class="col-xs-3 col-sm-3 margin-top5 control-label text-left">
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
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
										저장하기
									</button>
									<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden" name="workplaceNo" id="workplaceNo" value="${workplaceCode.codeNo}">
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>