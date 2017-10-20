<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" flush="false" />
<script src="${pageContext.request.contextPath}/ref/js/common_board.js"></script>
<script>
jQuery(function ($) {
});    
</script>
</head>
<body>
	<!-- Begin menu -->
	<header> 
	<!-- 로고를 상단 가운데에 따로 띄우려면 topbar 주석 -->
	<div class="container-fluid">
		<div class="topbar">
			<jsp:include page="/common/topmenu.jsp" flush="false" />
		</div>
	</div>
	</header>
	<!-- menu End --> 
	<!-- Body -->
	<div class="wrapper">       
		<div id="content-page" class="content-page">
			<div class="content" style="margin-top:60px;">
				<div class="container">
					<div class="col-sm-5">
						<h4 class="m-t-0 header-title" style="padding:10px;"><b>숙영/급식 추가하기</b></h4>
					</div>
								
                    <form class="form-horizontal" role="form" id="linkageRegForm" name="linkageRegForm" method="post" action="${pageContext.request.contextPath}/linkage/insert.do" accept-charset="utf-8">
						<div class="col-sm-12">
							<div class="card-box" style="margin-bottom:10px; padding-bottom:0px;">
                       			<div class="row">
                        				<div class="col-md-6" style="padding-left:20px;">
                        					
	                                            <div class="form-group" style="margin-bottom:10px;">
	                                                <label class="col-md-3 control-label" style="text-align:left;">제목 :</label>
	                                                <div class="col-md-9">
	                                                    <input class="form-control" type="text" id="title" name="title" placeholder="제목을 입력 하세요.">
	                                                </div>
	                                            </div>
	                                            <!-- <div class="form-group" style="margin-bottom:10px;">
	                                                <label class="col-md-3 control-label" style="text-align:left;">업무구분 :</label>
	                                                <div class="col-md-9">
	                                                    <input class="form-control" type="text" value="업무구분을 입력 하세요.">
	                                                </div>
	                                            </div> -->
	                                            <!-- <div class="form-group" style="margin-bottom:10px;">
	                                                <label class="col-md-3 control-label">컬럼 한글명 :</label>
	                                                <div class="col-md-9">
	                                                <label class="col-md-3 control-label" style="text-align:left;">업무구분 :</label>
	                                                <div class="col-md-9">
	                                                    <input class="form-control" type="text" value="검색어를 입력 하세요.">
	                                                </div>
	                                            </div> -->
                        				</div>

                        				<div class="col-md-6" style="padding-right:20px;">
                                            <div class="form-group" style="margin-bottom:10px;">
                                                <label class="col-md-3 control-label" style="text-align:left;">자료유형 :  </label>
                                                <div class="col-md-6">
                                                    <select class="selectpicker"  name="referenceType">
													  <option value="1">접수대기</option>
													  <option value="2">접수보류</option>
													  <option value="3">접수반려</option>
													  <option value="4">작업진행</option>
													  <option value="5">작업완료</option>
													  <option value="6">작업취소</option>
													</select>
                                                </div>
                                                <div class="col-md-3 text-right">
                                    				<button class="btn btn-silver waves-effect waves-light" type="button"  onclick="javascript:linkageInsert();">등록</button>
                                                    <button class="btn btn-warning waves-effect waves-light" onclick="javascript:goList();" type="button" >취소</button>
                                                </div>
                                            </div>
                        				</div>
                        			</div>
                       			<div id='daum_editor_panel'></div>
                       		<input type='hidden' id='content' name='content' value=''/>
                       		<input type='hidden' id='fileList' name='fileList' value=''/>
                       		</div>
                        </div>
                        </form>
                         <div class="col-md-12 portlets">
                            <div class="m-b-30">
                                <form class="dropzone dz-clickable" id="dropzone" action="../fileUpload" name="dropzone" method="post" enctype="multipart/form-data">
                                	<div class="dz-default dz-message"><span>file upload</span>
                                	</div>
                                </form>
							</div>
		               	</div>
		</div>
	</div>
	<!-- end body -->
</body>
</html>
