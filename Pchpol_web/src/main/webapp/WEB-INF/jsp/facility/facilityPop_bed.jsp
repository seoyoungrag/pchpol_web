<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ref/reg.css" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
//Ajax 파일 다운로드
jQuery.popup = function(url){
    if( url ){ 
    	window.open(url,'_blank');
    };
};
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
function fileDownload2(filename, oriname){
	$.download('${pageContext.request.contextPath}/fileDownload','fileName='+filename+'&oriName='+filename);
}
function fileDownload(filename, oriname){
	$.popup('${pageContext.request.contextPath}/mobile/resources/images/'+filename);
}
function findAddrPop(){
	var zip = $("#facilityAddrZip").val();
	var area = $("#facilityArea").val();
	
	var area = "";
   	var popWidth = 1150;
	var popHeight = 740;
	var width = screen.width;
	var height = screen.height;
	var left = (screen.width/2)-(popWidth/2);
	var top = (screen.height/2)-(popHeight/2);
	var param = "width="+popWidth+",height="+popHeight+",left="+left+",top="+top;
	var url = "${pageContext.request.contextPath}/addr/findAddr.do?zip="+zip+"&area="+area;
	win = window.open(url,'',param);
	return false;
}
var thisDropzone;
jQuery(function ($) {
	Dropzone.autoDiscover = false;
	$("#dropzone").dropzone({
    	maxFilesize: 0.5,
    	maxFiles:1,
    	acceptedFiles: [".jpg",".png"],
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
            	if(this.files.length==1	&&preview.length>1){
            		$(preview[0]).remove();
            	}
                if (this.files.length>1) {
                    this.removeFile(this.files[0]);
                }
                preview = preview[preview.length - 1];
                var imageName = document.createElement('span');
                //imageName.innerHTML = file.name;
                imageName.innerHTML = "<a class='text-center' style='cursor:auto;' href='javascript:fileDownload2(\""+file.name+"\");'>"+file.name+"</span>";
                preview.insertBefore(imageName, preview.firstChild);
            });
            thisDropzone = this;
        }
    });
	Dropzone.prototype.accept = function(file, done) {
	      if (file.size > this.options.maxFilesize * 1024 * 1024) {
	         alert(file.name+" 은 "+Math.floor(file.size/1024/1024)+ " Mbytes 입니다. 최대업로드 용량은 "+this.options.maxFilesize+" Mbytes입니다.");
	         thisDropzone.removeFile(file);
	        return done(this.options.dictFileTooBig.replace("{{filesize}}", Math.round(file.size / 1024 / 10.24) / 100).replace("{{maxFilesize}}", this.options.maxFilesize));
	      }
	      /* 
	      else if (!Dropzone.isValidFile(file, this.options.acceptedFiles)) {
	        return done(this.options.dictInvalidFileType);
	      } else if ((this.options.maxFiles != null) && this.getAcceptedFiles().length >= this.options.maxFiles) {
	        done(this.options.dictMaxFilesExceeded.replace("{{maxFiles}}", this.options.maxFiles));
	        return this.emit("maxfilesexceeded", file);
	      } 
	      */ 
	      else {
	        return this.options.accept.call(this, file, done);
	      }
	  };
	setSelectPickerFacilityArea();
	$.ajax({
		type : 'GET',
		url : '${pageContext.request.contextPath}/facility/${facilityNo}',
		cache : false,
		async : false,
		success : function(res){
			if(res.success){
				$("#facilityShelterOther").val(res.data.facilityShelterOther);
				$("#facilityChargerTel").val(res.data.facilityChargerTel);
				$("#facilityChargerName").val(res.data.facilityChargerName);
				$("#facilityAddrSebu").val(res.data.facilityAddrSebu);
				$("#facilityAddrJuso").val(res.data.facilityAddrJuso);
				$("#facilityAddrZip").val(res.data.facilityAddrZip);
				$("#facilityName").val(res.data.facilityName);
				$("#facilityArea_code1depth").val(res.data.facilityArea);
				$('.selectpicker').selectpicker('refresh');
	            if(res.data.facImg&&res.data.facImg!=''){
                    var mockFile = { name: res.data.facImg  }; 
                    thisDropzone.options.addedfile.call(thisDropzone, mockFile);
                    thisDropzone.options.thumbnail.call(thisDropzone, mockFile, "${pageContext.request.contextPath}/mobile/resources/images/"+res.data.facImg);
                    thisDropzone.options.complete.call(thisDropzone, mockFile);
                    var preview = document.getElementsByClassName('dz-preview');
                    preview = preview[preview.length - 1];
                    var imageName = document.createElement('span');
                    imageName.innerHTML = "<a class='text-center' style='cursor:auto;' download='"+res.data.facImg+"' href='${pageContext.request.contextPath}/mobile/resources/images/"+res.data.facImg+"'>"+res.data.facImg+"</span>";
                    preview.insertBefore(imageName, preview.firstChild);
	            }
			}else{
				alert('데이터 조회 실패하였습니다.');
			}
		},
		error : function(res){
			alert('데이터 조회 실패하였습니다.');
		}
	});
});    
function successFunc(res){
	if(res.success){
		alert('데이터를 입력하였습니다.');
		self.location.reload();
	}else{
		alert('데이터 입력을 실패하였습니다.');
		alert(res.message);
	}
}
function reg(){
	var area = $("#facilityArea_code1depth").val();
	if(typeof area === 'undefined' || area==''){
		alert('지역을 지정해주세요.');
		return false;
	}else{
		$("#facilityArea").val(area);
	}
	var getFileName = $(".dz-filename > span");
	if(getFileName.length != 0){
		var fileList = "";
		$('.dz-filename > span').each(function(idx) {
			fileList += $(this).html() + "*";
		});	
		$("#fileList").val(fileList);
	}
	sendFormByAjax(successFunc);
	return false;
}
</script>
</head>
<body>
<!-- Begin menu -->
<!-- menu End --> 
<!-- Body -->
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content text-center">
			<div class="container leftright-padding">
				<div class="col-lg-12">
					<div class="card-box updown-padding">
						<form onsubmit="return reg()" class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/facility" accept-charset="utf-8">
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;시설명</label>
								<label class="col-xs-5 col-sm-5 control-label input-label">
								<input class="form-control" type="text" placeholder="시설이름을 입력 하세요" name="facilityName" id="facilityName" >
								</label>
								<label class="col-xs-2 col-sm-2 margin-top5 control-label text-right">
								&nbsp;지역</label>
								<label class="col-xs-2 col-sm-2 control-label input-label text-left">
								<select class="selectpicker form-control" data-container="body" data-size="15" data-width="auto" id="facilityArea_code1depth">
									<option value="">지역</option>
								</select>
								<input type="hidden" name="facilityArea" id="facilityArea">
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
									&nbsp;주소
								</label>
								<div class="col-xs-2 col-sm-2 text-right">
									<input class="form-control" type="text" placeholder="우편번호" name="facilityAddrZip" id="facilityAddrZip" >
								</div>
								<div class="col-xs-2 col-sm-2 text-center" style="padding: 0;">
									<button class="btn btn-default btn-rounded waves-effect"  style="font-size: 0.9em;" onclick="return findAddrPop();">우편번호 찾기</button>
								</div>
								<label class="col-xs-3 col-sm-3  text-left">
									<input class="form-control" type="text" placeholder="주소" name="facilityAddrJuso" id="facilityAddrJuso" >
								</label>
								<label class="col-xs-2 col-sm-2  text-left">
									<input class="form-control" type="text" placeholder="세부주소" name="facilityAddrSebu" id="facilityAddrSebu" >
								</label>
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;상세내용</label>
								<label class="col-xs-9 col-sm-9 control-label">
									<div class="form-group">
										<label class="col-xs-2 col-sm-2 control-label" style="margin-top: 5px;">
										&nbsp;담당자</label>
										<label class="col-xs-4 col-sm-4 control-label input-label">
										<input class="form-control" type="text" placeholder="담당자를 입력 하세요" name="facilityChargerName" id="facilityChargerName" >
										</label>
										<label class="col-xs-2 col-sm-2 control-label" style="margin-top: 5px;">
										&nbsp;연락처</label>
										<label class="col-xs-4 col-sm-4 control-label input-label">
										<input class="form-control" type="text" placeholder="연락처를 입력 하세요" name="facilityChargerTel" id="facilityChargerTel" >
										</label>
									</div>
									<div class="form-group">
										<div class="col-xs-12 col-sm-12 control-label" >
											<textarea style="width:100%;" id="facilityShelterOther" name="facilityShelterOther" rows="10">
											</textarea>
										</div>
									</div>
								</label>
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group bottom-line">
								<label class="col-xs-2 col-sm-2 margin-top5 control-label">
								&nbsp;시설 이미지</label>
								<div class="col-xs-9 col-sm-9">
								    <div class="dropzone dz-clickable" id="dropzone" action="${pageContext.request.contextPath}/fileUpload" name="dropzone" method="post" enctype="multipart/form-data">
								    	<div class="dz-default dz-message"><span>파일 업로드</span>
								    	</div>
								    </div>
								</div>
								<label class="col-xs-1 col-sm-1 margin-top5 control-label"></label>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button class="btn btn-silver btn-rounded waves-effect" id="submitBtn">
										수정하기
									</button>
									<button type="button" class="btn btn-default btn-rounded waves-effect" onclick="javascript:self.close();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden" name="facilityNo" value="${facilityNo}">
							<input type="hidden" name="facilityType" value="bed">
                   			<input type='hidden' id='fileList' name='fileList' value=''/>
						</form>	
					</div>
               	</div>
            </div>
        </div>
    </div>
</div>
<!-- end body -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/ref/bootstrap/css/dropzone.css" />
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/jquery.core.js"></script>
<script src="${pageContext.request.contextPath}/ref/bootstrap/js/dropzone.js"></script>
<input class="dz-hidden-input" style="left: 0px; top: 0px; width: 0px; height: 0px; visibility: hidden; position: absolute;" type="file" multiple="multiple">
</body>
</html>
