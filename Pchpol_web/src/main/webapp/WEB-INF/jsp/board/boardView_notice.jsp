<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script type="text/javascript">
var serverName = '${pageContext.request.serverName}';
var contextroot = '${pageContext.request.contextPath}';
var selectpickerObj;
var viewObj;
//Ajax 파일 다운로드
jQuery.download = function(url, data, method){
    // url과 data를 입력받음
    if( url && data ){ 
        // data 는  string 또는 array/object 를 파라미터로 받는다.
        data = typeof data == 'string' ? data : jQuery.param(data);
        // 파라미터를 form의  input으로 만든다.
        var inputs = '';
        jQuery.each(data.split('&'), function(){ 
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
        });
        // request를 보낸다.
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
        .appendTo('body').submit().remove();
    };
};
function fileDownload(filename, oriname){
	$.download('${pageContext.request.contextPath}/fileDownload','fileName='+filename+'&oriName='+oriname);
    /* .done(function () { 
    	//alert('File download a success!'); 
   	})
    .fail(function () { 
    	//alert('File download failed!'); 
   	}); */
}
function getViewById(id){
	viewObj = new ViewObj();
	viewObj.url = "${pageContext.request.contextPath}/board/";
	viewObj.id = id;
	var successFunc = function(res){
		$("#boardNo").val(res.data.boardNo);
		//$("#boardArea").val(res.data.boardArea);
		try{
		$('input:radio[name=boardArea]:input[value='+res.data.boardArea+']').attr("checked", true);
		//$("#boardCategory").val(res.data.boardCategory);
		$('input:radio[name=boardCategory]:input[value='+res.data.boardCategory+']').attr("checked", true);
		}catch(e){
			
		}
		var boxText = res.data.boardContent.replace(/\n/g, '<br/>');
		$("#editor_contents_source").val(boxText);
		<c:if test="${admin.code.codeOrderNo ne '4'}">
		daumEditor = new DaumEditor('BBS');
		daumEditor.create();
		daumEditor.setContent();
		</c:if>
		$("#boardTitle").val(res.data.boardTitle);
		$("#boardType").val(res.data.boardType);
		//$("#attaches").val(res.data.attaches);
		$("#admin").val(res.data.admin);
		$("#unit").val(res.data.unit);
	    $("#dropzone").dropzone({
	        url: "${pageContext.request.contextPath}/fileUpload",
	        success: function (file, response) {
	            var imgName = response;
	            //file.previewElement.appendChild(file._downloadLink(file.name));   
	            file.previewElement.classList.add("dz-success"); 
	            //return file.previewElement.classList.add("dz-success");
	        },
	        error: function (file, response) {
	            file.previewElement.classList.add("dz-error");
	        },
	        init: function() {
	            this.on('addedfile', function(file){
	                var preview = document.getElementsByClassName('dz-preview');
	                preview = preview[preview.length - 1];
	                var imageName = document.createElement('span');
	                //imageName.innerHTML = file.name;
	                imageName.innerHTML = "<a class='text-center' style='cursor:auto;' href='javascript:fileDownload(\""+file.name+"\",\""+file.name+"\");'>"+file.name+"</span>";
	                preview.insertBefore(imageName, preview.firstChild);
	            });
	            thisDropzone = this;
                $.each(res.data.attaches, function(key,value){
                    var mockFile = { name: value.attachOriName, size: value.attachFileSize }; 
                    thisDropzone.options.addedfile.call(thisDropzone, mockFile);
                    thisDropzone.options.thumbnail.call(thisDropzone, mockFile, "${pageContext.request.contextPath}/files/"+value.attachServerName);
                    thisDropzone.options.complete.call(thisDropzone, mockFile);
                    var preview = document.getElementsByClassName('dz-preview');
                    preview = preview[preview.length - 1];
                    var imageName = document.createElement('span');
                    imageName.innerHTML = "<a class='text-center' style='cursor:auto;' href='javascript:fileDownload(\""+value.attachServerName+"\",\""+value.attachOriName+"\");'>"+value.attachOriName+"</span>";
                    preview.insertBefore(imageName, preview.firstChild);
                }); 
	        }
	    });
		<c:if test="${admin.code.codeOrderNo eq '4'}">
			$('#wrapper input').attr('readonly', 'readonly');
			$('#wrapper input').attr("disabled", true);
			$('.dz-remove').remove();
			$('#dropzone').removeClass('dz-clickable');
			var boxText = res.data.boardContent.replace(/\n/g, '<br/>');
			$("#daum_editor_panel").html(boxText);
			
			//document.getElementById("tx_toolbar_basic").style.display = "none";
			//document.getElementById("tx_toolbar_advanced").style.display = "none";
			//var edite = $('#tx_canvas_wysiwyg').contents().find('.tx-content-container').removeAttr("contenteditable");
		</c:if>
	}
	viewObj.ajax(successFunc);
}
function reg(){
	if (validation()) {
		var getFileName = $(".dz-filename > span");
		if(getFileName.length != 0){
			var fileList = "";
			$('.dz-filename > span').each(function(idx) {
				fileList += $(this).html() + "*";
			});	
			$("#fileList").val(fileList);
		}
		sendFormByAjax();
	}
	return false;
}

function validation() {

	var check = false;
	var content = daumEditor.getContent();
	content = content.replace(/<br\s?\/?>/g,"\n");
	content = content.replace(/<p>/g,"");
	content = content.replace(/<p\s?\/?>/g,"\n");
	$("#boardContent").val(content);

	if ($("#title").val() == "") {
		alert("제목이 입력 되지 않았습니다.");
		check = false;
	} else if ($("#content").val() == "<p><br></p>") {
		alert("본문이 입력 되지 않았습니다.");
		check = false;
	} else {
		check = true;
	}

	return check;
}
jQuery(document).ready(function($) {
	Dropzone.autoDiscover = false;
	$('.selectpicker').selectpicker('refresh')
	if('${type}'=='view'){
		getViewById('${boardNo}');
		$("#submitBtn").html("수정하기");
	}else if('${type}'=='reg'){
		$("#boardNo").val('0')
		daumEditor = new DaumEditor('BBS');
		daumEditor.create();
	    $("#dropzone").dropzone({
	        url: "${pageContext.request.contextPath}/fileUpload",
	        success: function (file, response) {
	            var imgName = response;
	            file.previewElement.classList.add("dz-success");
	        },
	        error: function (file, response) {
	            file.previewElement.classList.add("dz-error");
	        },
	        init: function() {
	            this.on('addedfile', function(file){
	                var preview = document.getElementsByClassName('dz-preview');
	                preview = preview[preview.length - 1];
	                var imageName = document.createElement('span');
	                imageName.innerHTML = "<a class='text-center' style='cursor:auto;' href='javascript:fileDownload(\""+file.name+"\");'>"+file.name+"</span>";
	                preview.insertBefore(imageName, preview.firstChild);
	            });
            }
	    });
	}
});
</script>
</head>
<body>
<form name="tx_editor_form" id="tx_editor_form" action="" method="post" accept-charset="utf-8"></form>
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content">
			<div class="container leftright-padding">
				<h4 class="m-t-0 header-title popup-title text-center">
					<span>공지사항</span>
				</h4>
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/board" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;글제목
								</label>
								<div class="col-xs-10 col-sm-10">
									<input class="form-control" required="" type="text" placeholder="글제목을 입력하세요." name="boardTitle" id="boardTitle" required >
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									&nbsp;상태
								</label>
								<div class="col-xs-4 col-sm-4 margin-top5 text-center">
									<input type="radio" name="boardCategory" value="일반" checked="checked"/>일반
									<input type="radio" name="boardCategory" value="긴급"/>긴급
								</div>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									&nbsp;지역
								</label>
								<div class="col-xs-4 col-sm-4 margin-top5 text-center">
									<input type="radio" name="boardArea" value="" checked="checked"/>전체
									<input type="radio" name="boardArea" value="평창"/>평창
									<input type="radio" name="boardArea" value="정선"/>정선
									<input type="radio" name="boardArea" value="강릉"/>강릉
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;첨부파일</label>
								<div class="col-xs-10 col-sm-10">
								    <div class="dropzone dz-clickable" id="dropzone" action="${pageContext.request.contextPath}/fileUpload" name="dropzone" method="post" enctype="multipart/form-data">
								    	<div class="dz-default dz-message"><span>파일 업로드</span>
								    	</div>
								    </div>
								</div>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;본문
								</label>
								<div class="col-xs-10 col-sm-10" id='daum_editor_panel'>
								</div>
							</div>
							<c:if test="${admin.code.codeOrderNo eq '1' || admin.code.codeOrderNo eq '2' || admin.code.codeOrderNo eq '3'}">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="col-xs-12 text-center">
											<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
												글쓰기
											</button>
											<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
												취소
											</button>
										</div>
									</div>
								</div>
							</c:if>
							<input type="hidden" name="boardNo" id="boardNo" value="${boardNo}">
							<input type="hidden" name="boardType" id="boardType" value="${boardType}">
							<input type='hidden' id='boardContent' name='boardContent' value=''/>
                   			<input type='hidden' id='fileList' name='fileList' value=''/>
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
<textarea id="editor_contents_source" style='display:none;'></textarea>
<script src="${pageContext.request.contextPath}/ref/bootstrap/ndaum/js/editor_loader.js?environment=production" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/ndaum/js/jquery.browser.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/ndaum/js/jquery.webkitresize.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/ndaum/js/daumeditor.js" charset="utf-8"></script>


<link rel="stylesheet" href="${pageContext.request.contextPath}/ref/bootstrap/css/dropzone.css" />
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery.core.js"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/dropzone.js"></script>
<input class="dz-hidden-input" style="left: 0px; top: 0px; width: 0px; height: 0px; visibility: hidden; position: absolute;" type="file" multiple="multiple">
</body>
</html>