<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="mobile-web-app-capable" content="yes">
<title>::평창올림픽 동원경찰관::</title>
<link href="${pageContext.request.contextPath}/mobile/resources/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/mobile/resources/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/mobile/resources/css/style.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/mobile/resources/js/jquery-1.12.1.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/resources/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/resources/js/common.js"></script>
<script>
var getContextPath = function()
{
	var offset = null;
	var ctxPath = null;
	if( location.host.length > 0 )
	{
		offset = location.href.indexOf(location.host)+location.host.length;
		ctxPath = location.href.substring(offset+1,location.href.lastIndexOf( "/" ) );
		ctxPath = "/"+ctxPath.split("/")[0];
	}
	else{
		ctxPath = ".";
	}
	return ctxPath;
};
var contextPath = getContextPath();
var fullPath = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '')+contextPath;

function goBoard(btn){
	var boardNo = $(btn).parent().attr('boardNo');
	var prevBoardNo = $(btn).parent().parent().prev().find('.tit').attr('boardNo')||'0';
	var prevBoardTitle = $(btn).parent().parent().prev().find('.tit').find('a').text()||'';
	var nextBoardNo = $(btn).parent().parent().next().find('.tit').attr('boardNo')||'0';
	var nextBoardTitle = $(btn).parent().parent().next().find('.tit').find('a').text()||'';
	var curBoardSeq = $(btn).parent().parent().find('th').text()-1;
	var url = "./view.do?curBoardNo="+boardNo+"&prevBoardNo="+prevBoardNo+"&nextBoardNo="+nextBoardNo+"&prevBoardTitle="+encodeURIComponent(prevBoardTitle)+"&nextBoardTitle="+encodeURIComponent(nextBoardTitle);
	url += '&page='+page+'&rows='+rows+'&sidx=boardWriteDt&sord=desc&curBoardSeq='+curBoardSeq
	location.href=url;	
}
function search(btn){
	var area = $("#area").val();
	var searchWord = $(btn).parent().find('input').val();
	var searchType = 'boardTitle';
	var url = "./list.do?sidx=boardWriteDt&sord=desc&page="+page+"&rows="+rows+"&searchType="+searchType+"&searchWord="+searchWord+"&boardArea="+area;
	location.href=url;	
}
function areaChange(select){
	var area = $(select).val();
	var searchWord = $("#searchWord").val();
	var searchType = 'boardTitle';
	var url = "./list.do?sidx=boardWriteDt&sord=desc&page="+page+"&rows="+rows+"&searchType="+searchType+"&searchWord="+searchWord+"&boardArea="+area;
	location.href=url;
}

jQuery.jSubmit = function(url, data, method){
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
	var full = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '');
	var url = full+contextPath+'/fileDownload';
	var param = 'fileName='+encodeURIComponent(filename)+'&oriName='+encodeURIComponent(oriname);
	if(typeof app !== 'undefined'){
		//var url = contextPath+'/fileDownload?fileName='+filename+'&oriName='+oriname;
		app.fileDownload(url, param);
	}else{
		$.jSubmit(contextPath+'/fileDownload','fileName='+filename+'&oriName='+oriname);
	}
}
function toggleComment(){
	$(".comment>.write").toggle();
}
function showWriteBox(btn){
	$(btn).parent().parent().find('.update').hide();
	$(btn).parent().parent().find('.txt').show();
	$(btn).parent().parent().find('.write').toggle();
}
function commentUpdatable(btn){
	$(btn).parent().parent().find('.write').hide();
	$(btn).parent().parent().find('.txt').toggle();
	$(btn).parent().parent().find('.update').toggle();
}
function regComment(btn){
	var commentContent = $(btn).parent().find('textarea[name=commentContent]').val();
	var parentCommentNo = $(btn).parent().find('textarea').attr('parentCommentNo')||'0';
	var params = 'board.boardNo='+boardNo+'&commentContent='+commentContent+'&comment.commentNo='+parentCommentNo;
	$.ajax({
		type : 'POST',
		url : contextPath+'/mobile/comment',
		data: params,
		success : function(res){
			if(res.success){
				alert('댓글을 입력하였습니다.');
				location.reload();  
			}else{
				alert('데이터 조회를 실패하였습니다.');
			}
		},
		error : function(res){
				alert('데이터 조회를 실패하였습니다.');
		}
	});
	//$.jSubmit('${pageContext.request.contextPath}/mobile/comment','board.boardNo='+boardNo+'&commentContent='+commentContent,'post');
}
function updateComment(btn){
	var commentContent = $(btn).parent().find('textarea').val();
	var commentNo = $(btn).parent().find('textarea').attr('commentNo');
	var parentCommentNo = $(btn).parent().find('textarea').attr('parentCommentNo')||'0';
	var params = 'board.boardNo='+boardNo+'&commentNo='+commentNo+'&commentContent='+commentContent+'&comment.commentNo='+parentCommentNo;
	$.ajax({
		type : 'POST',
		url : contextPath+'/mobile/comment',
		data: params,
		success : function(res){
			if(res.success){
				alert('댓글을 입력하였습니다.');
				location.reload();  
			}else{
				alert('데이터 조회를 실패하였습니다.');
			}
		},
		error : function(res){
				alert('데이터 조회를 실패하였습니다.');
		}
	});
	//$.jSubmit('${pageContext.request.contextPath}/mobile/comment','board.boardNo='+boardNo+'&commentNo='+commentNo+'&commentContent='+commentContent,'post');
}
function deleteComment(btn){
	var commentContent = $(btn).parent().parent().parent().find('.update').find('textarea').val();
	var commentNo = $(btn).parent().parent().parent().find('.update').find('textarea').attr('commentNo');
	var parentCommentNo = $(btn).parent().parent().parent().find('.update').find('textarea').attr('parentCommentNo')||'0';
	var params = 'board.boardNo='+boardNo+'&commentNo='+commentNo+'&commentContent='+commentContent+'&comment.commentNo='+parentCommentNo+'&commentDeleteYn=Y';
	$.ajax({
		type : 'POST',
		url : contextPath+'/mobile/comment',
		data: params,
		success : function(res){
			if(res.success){
				alert('댓글을 삭제하였습니다.');
				location.reload();  
			}else{
				alert('데이터 조회를 실패하였습니다.');
			}
		},
		error : function(res){
				alert('데이터 조회를 실패하였습니다.');
		}
	});
}
function goAnotherBoard(boardNo, type){
	if(type == 'prev'){
		curBoardSeq-=1;
	}else{
		curBoardSeq+=1;
	}
	var url = "./view.do?curBoardNo="+boardNo;
	url += '&page='+page+'&rows='+rows+'&sidx=boardWriteDt&sord=desc&curBoardSeq='+curBoardSeq
	location.href=url;	
}
function goList(){
	var url = "./list.do?sidx=boardWriteDt&sord=desc&page="+page+"&rows="+rows;
	location.href=url;	
}

function sendFormByAjax(successFunc){
	var regForm = $("#regForm");
	var formData = regForm.serialize();
	$.ajax({
		type : regForm.attr("method"),
		url : regForm.attr("action"),
		cache : false,
		data : formData,
		success : function(res){
			if(typeof successFunc === 'undefined'){
				if(res.success){
					alert('데이터를 입력하였습니다.');
					alert(res.message);
				}else{
					alert('데이터 입력을 실패하였습니다.');
					alert(res.message);
				}
			}else{
				successFunc(res);
			}
		},
		error : function(res){
			alert('데이터 입력을 실패하였습니다.');
		}
	});
}
function listingFiles(){
	var fileInput = document.getElementById("ex_filename");
	var files = fileInput.files;
    var file;
    var fileList = '';
    for (var i = 0; i < files.length; i++) {
        file = files[i];
        fileList += "<span>"+file.name + "&nbsp;<a href='#' onclick='deleteFile(this);'>삭제</a><br/></span>";
    }
    document.getElementById("fileList_names").innerHTML=fileList;
}

function deleteFile(obj){
	$(obj).parent().remove();
}

function boardWrite(){
	var url = fullPath+'/mobile/mobileFileUpload/';
	$("#boardContent").val($("#boardContentDiv").html());
	if(typeof app !== 'undefined'){
		app.fileupload(url);
	}else{
		
	}
	var fileInput = document.getElementById("ex_filename");
	var files = fileInput.files;
	if(files.length != 0){
		var fileList = "";
	    $.each(files, function(idx, file){
	    	fileList += file.name + "*";
	    });
		$("#fileList").val(fileList);
	}
	sendFormByAjax(successFunc);
	return false;
}

function deleteUploadedFile(obj){
	$(obj).parent().remove();
	var attachId = $(obj).siblings("input[name=uploadedFileNo]").val();
	var fileName = $(obj).siblings("input[name=uploadedFileNo]").attr("fileName");
	var txt = "<span><input type='hidden' name='deletingFileNo' value='"+attachId+"' fileName='"+fileName+"' >"+fileName+"&nbsp;<a href='#' onclick='cancelDeleteingFile(this);'>삭제 취소</a><br/></span>";
	$("#deletedFileList_names").append(txt);
}
function cancelDeleteingFile(obj){
	$(obj).parent().remove();
	var attachId = $(obj).siblings("input[name=deletingFileNo]").val();
	var fileName = $(obj).siblings("input[name=deletingFileNo]").attr("fileName");
	var txt = "<span><input type='hidden' name='uploadedFileNo' value='"+attachId+"' fileName='"+fileName+"' >"+fileName+"&nbsp;<a href='#' onclick='deleteUploadedFile(this);'>삭제</a><br/></span>";
	$("#uploadedFileList_names").append(txt);
}
</script>