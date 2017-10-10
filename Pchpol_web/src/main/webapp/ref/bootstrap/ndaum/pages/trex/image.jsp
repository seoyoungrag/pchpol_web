<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
  String contextPath = (String)request.getContextPath();
%>    
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Daum에디터 - 이미지 첨부</title> 
<script src="../../js/popup.js" type="text/javascript" charset="utf-8"></script>
<script src="jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="jquery.form.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=contextPath%>/resources/kt/js/dropzone.js"></script>
<link rel="stylesheet" href="../../css/popup.css" type="text/css"  charset="utf-8"/>
<link rel="stylesheet" href="<%=contextPath%>/resources/kt/css/dropzone.css" />
<script type="text/javascript">
// <![CDATA[
	$(function(){
		$("#saveBtn").click(function(){
			var getFileName = $(".dz-filename > span");
			
			if(getFileName.length != 0){
				var fileList = "";
    			$('.dz-filename > span').each(function(idx) {
    				fileList += $(this).html() + "*";
    			});	
    			$("#fileList").val(fileList);
			}
			$("#regForm").submit();
		})
		$("#regForm").ajaxForm({
			beforeSubmit: function(data, form, option){
				//validation 체크 
				//console.log(data);
				//console.log(form);
				//실패시 return false 처리
				return true;
			},
			success: function(response, status){
				done(response);
			},
			error: function(){
				alert('error');
			}
		})
	})
	function done(response) {
		if (typeof(execAttach) == 'undefined') { //Virtual Function
	        return;
	    }
		
		var response_object = $.parseJSON(response);
		//response_object.imageurl=response_object.imageurl.replace(/-/gi,"+").replace(/_/gi,"/");
		//console.log(response_object.imageurl);
		execAttach(response_object);
		/* 
		var _mockdata = {
			'imageurl': 'http://cfile284.uf.daum.net/image/116E89154AA4F4E2838948',
			'filename': 'editor_bi.gif',
			'filesize': 640,
			'imagealign': 'C',
			'originalurl': 'http://cfile284.uf.daum.net/original/116E89154AA4F4E2838948',
			'thumburl': 'http://cfile284.uf.daum.net/P150x100/116E89154AA4F4E2838948'
		};
		execAttach(_mockdata); */
		closeWindow();
	}

	function initUploader(){
	    var _opener = PopupUtil.getOpener();
	    if (!_opener) {
	        alert('잘못된 경로로 접근하셨습니다.');
	        return;
	    }
	    
	    var _attacher = getAttacher('image', _opener);
	    registerAction(_attacher);
	}
// ]]>
</script>
</head>
<body onload="initUploader();">
<div class="wrapper">
	<div class="header">
		<h1>사진 첨부</h1>
	</div>	
	<div class="body">
		<dl class="alert">
		    <dt>사진 첨부 확인</dt>
		    <dd>
		    	<!-- 확인을 누르시면 임시 데이터가 사진첨부 됩니다.<br /> 
				인터페이스는 소스를 확인해주세요. -->
                <form class="dropzone dz-clickable" id="dropzone" action="<%=contextPath%>/fileUploadForView" name="dropzone" method="post" enctype="multipart/form-data">
                	<div class="dz-default dz-message"><span>file upload</span>
                	</div>
                </form>       
                <input class="dz-hidden-input" style="left: 0px; top: 0px; width: 0px; height: 0px; visibility: hidden; position: absolute;" type="file" multiple="multiple">
                <form id="regForm" method="post" action="<%=contextPath%>/commonBbs/contentImgInsert.do" accept-charset="utf-8">
         			<input type='hidden' id='fileList' name='fileList' value=''/>
         		</form>
		</dd>
		</dl>
	</div>
	<div class="footer">
		<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
		<ul>
			<li class="submit"><a href="#" id="saveBtn" title="등록" class="btnlink">등록</a> </li>
			<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
		</ul>
	</div>
</div>
</body>
</html>