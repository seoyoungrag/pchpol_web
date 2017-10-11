<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common/header.jsp" flush="false" />
</head>
<body>
<div id="wrapper">
	<div class="">
	<!-- Start content -->
		<div class="content" style="">
			<div class="container" style="padding-left:0px; padding-right:0px;">
				<h4 class="m-t-0 header-title" style="padding:10px; padding-top:15px; margin-bottom:0px;"><i class="zmdi zmdi-transform"></i><span>관리자 계정 추가하기</span></h4>
				<div class="col-lg-12" style="margin-bottom:10px;">
					<div class="card-box" style="padding-bottom:10px; padding-top:10px;">
						<form class="form-horizontal" role="form" id="encDecRegForm" name="encDecRegForm" method="post" action="${pageContext.request.contextPath}/encDecinsert.do?" accept-charset="utf-8">
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-4 control-label" style="float:left; margin-top:5px;"><i class="zmdi zmdi-flare">
								</i>&nbsp;권한
								</label>
								<div class="col-sm-8" style="float:right;">
								<select class="selectpicker form-control"  data-size="15" required data-width="auto" name="schema" id="schema" onchange="goSelectTable(this.value)">
									<option value="">권한 선택</option>
								</select>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-4 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;소속
								</label>
								<div class="col-sm-8" style="float:right;">
									<input class="form-control" required=""  placeholder="소속을 입력 하세요" id="prjNm" name="prjNm" required >
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-6 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;계급</label>
								<div class="col-sm-6" style="float:right;">
									<input class="form-control" required="" type="text" placeholder="계급을 입력 하세요" id="reason" name="reason" required >
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-5 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;성명
								</label>
								<div class="col-sm-7" style="float:right;">
									<input class="form-control" required="" type="text" placeholder="성명을 입력 하세요" id="docNumber" name="docNumber" required>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-5 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;아이디
								</label>
								<div class="col-sm-7" style="float:right;">
									<input class="form-control" required="" type="text" placeholder="아이디를 입력 하세요" id="adminId" name="adminId" required>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-5 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;패스워드
								</label>
								<div class="col-sm-7" style="float:right;">
									<input class="form-control" required="" type="text" placeholder="패스워드를 입력 하세요" id="password" name="password" required>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-5 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;비고
								</label>
								<div class="col-sm-7" style="float:right;">
									<input class="form-control" required="" type="text" placeholder="비고를 입력 하세요" id="etc" name="etc" required>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:10px; padding-right:10px;border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-4 control-label" style="float:left; margin-top:7px;">
								<i class="zmdi zmdi-flare"></i>&nbsp;아이디</label>
								<div class='col-sm-4 input-group date' id='datetimepicker6' style="float:right;width:40%">
								     <input type='text' class="form-control" name="validateDate" id="validateDate" value="" required/>
								     <span class="input-group-addon">
								         <span class="glyphicon glyphicon-calendar"></span>
								     </span>
								 </div>
							</div>
							<input type="hidden" id="fileList" name="fileList" />
							<div class="form-group" style="padding-bottom:10px; border-bottom-width:1px; border-bottom-style:dotted; border-bottom-color:#ddd; margin-bottom:10px;">
								<label class="col-sm-6 control-label" style="float:left; margin-top:7px;"><i class="zmdi zmdi-flare"></i>&nbsp;법률검토결과</label>
								<%
								if(isRe.equals("isView")){
								%>
								<%if(!fileName.equals("")){%>
								<div class="col-sm-7" style="float:right;">
									<input type="button" value="다운로드" id="fileDownloadBtn" onclick="javascript:fileDownload(this);" >
								</div>
								                      		<%}else{%>
								<div class="col-sm-7" style="float:right;">
									<input class="form-control" type="text" placeholder="첨부파일 없음" disabled>
								</div>
								                      		<%} %>	
								<%
								}else{
								%>
								<div class="input-group" style="margin-right:10px;">
									<input type="text" class="form-control" readonly required>
								<label class="input-group-btn">
								    <span class="btn btn-default">
								        	찾아보기<input type="file" id="fileArr"  name="fileArr"  style="display: none;" multiple="multiple" onchange="javascript:fileUpLoad(this);" />
								        </span>
								    </label>
								</div>
								<%
								}
								%>
							</div>
							<div class="form-group encdecButton" style="margin-bottom:0px;">
								<div class="col-sm-offset-3 col-sm-9 text-right">
									<button class="btn btn-primary waves-effect waves-light" type="submit">
										확인
									</button>
								<button class="btn btn-default waves-effect m-l-5" type="reset" onclick="javascript:winclose();">
										취소
									</button>
								</div>
							</div>
							<input type="hidden"  id="colTblNm" name="colTblNm"/>
						</form>	
						<form id="download" class="" action="fileDownload" method="post" >
							<input type="hidden" name="fileName" id="filename" value="<%=fileName%>"/>
							<input type="hidden" name="userId" value="<%=ownerId%>"/>
						</form>
					</div>
              		<input type="hidden"  id="colTblId" name="colTblId"/>
               	</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>