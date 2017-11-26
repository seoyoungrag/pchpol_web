<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    if( url && data ){ 
        data = typeof data == 'string' ? data : jQuery.param(data);
        var inputs = '';
        jQuery.each(data.split('&'), function(){ 
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
        });
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
        .appendTo('body').submit().remove();
    };
};
function fileDownload(filename, oriname){
	$.download('${pageContext.request.contextPath}/fileDownload','fileName='+filename+'&oriName='+oriname);
}
function getViewById(id){
	viewObj = new ViewObj();
	viewObj.url = "${pageContext.request.contextPath}/board/";
	viewObj.id = id;
	var successFunc = function(res){
		$("#boardNo").val(res.data.boardNo);
		//$("#boardArea").val(res.data.boardArea);
		$('input:radio[name=boardArea]:input[value='+res.data.boardArea+']').attr("checked", true);
		//$("#boardCategory").val(res.data.boardCategory);
		$('input:radio[name=boardCategory]:input[value='+res.data.boardCategory+']').attr("checked", true);
		$("#editor_contents_source").val(res.data.boardContent);
		daumEditor = new DaumEditor('BBS');
		daumEditor.create();
		daumEditor.setContent();
		$("#boardTitle").val(res.data.boardTitle);
		$("#boardType").val(res.data.boardType);
		//$("#attaches").val(res.data.attaches);
		$("#admin").val(res.data.admin);
		$("#unit").val(res.data.unit);
	    $("#dropzone").dropzone({
			maxFiles: 1,
	        url: "${pageContext.request.contextPath}/excelUpload",
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
				this.on("maxfilesexceeded", function(file){
				    alert("No more files please!");
				});
	            this.on('addedfile', function(file){
	                var preview = document.getElementsByClassName('dz-preview');
	                preview = preview[preview.length - 1];
	                var imageName = document.createElement('span');
	                //imageName.innerHTML = file.name;
	                imageName.innerHTML = "<a class='text-center' style='cursor:auto;' href='javascript:fileDownload(\""+file.name+"\");'>"+file.name+"</span>";
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
	}
	viewObj.ajax(successFunc);
}
function reg(){
	var getFileName = $(".dz-filename > span");
	if(getFileName.length != 0){
		var fileList = "";
		$('.dz-filename > span').each(function(idx) {
			fileList += $(this).html() + "*";
		});	
		$("#fileList").val(fileList);
	}
	sendFormByAjax();
	return false;
}

jQuery(document).ready(function($) {
	Dropzone.autoDiscover = false;
	$('.selectpicker').selectpicker('refresh')
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
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/unit/excelupload" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;부대원 부대 타입
								</label>
								<div class="col-xs-10 col-sm-10 margin-top5" >
									<input type="radio" name="troosType" value="stand" checked="checked"/>경찰관 기동대
									<input type="radio" name="troosType" value="duty"/>의무 중대
									<input type="radio" name="troosType" value="female"/>여경
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
							<div class="form-group">
								<div class="col-xs-12">
									<div class="col-xs-12 text-center">
										<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
											등록
										</button>
										<button class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
											취소
										</button>
									</div>
								</div>
							</div>
                   			<input type='hidden' id='fileList' name='fileList' value=''/>
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>

<link rel="stylesheet" href="${pageContext.request.contextPath}/ref/bootstrap/css/dropzone.css" />
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery.core.js"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/dropzone.js"></script>
<input class="dz-hidden-input" style="left: 0px; top: 0px; width: 0px; height: 0px; visibility: hidden; position: absolute;" type="file" multiple="multiple">
</body>
</html>