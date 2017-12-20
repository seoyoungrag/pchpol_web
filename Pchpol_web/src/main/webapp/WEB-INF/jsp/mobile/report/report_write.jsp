<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="ko">
<head>
	<jsp:include page="/mobile/head.jsp" flush="false" />
</head>
<body>
<script>
function successFunc(){
	alert('데이터를 입력하였습니다.');
	location.href="./list.do";
}

jQuery(document).ready(function($) {
	$("#boardSubCategory").change();
});
</script>
<div class="wrap_hide">
	<div class="wrap">
		<!-- // id : header -->
		<header id="header">
			<jsp:include page="/mobile/header.jsp" flush="false" />
		</header>
		<!-- // id : header -->
        
        <!-- //  : contents -->
        <section class="contents sub">

          <h2><a class="btn_back" href="javascript:history.back();"><img src="../resources/images/ico-btn-back.png" alt="뒤로가기" /></a>상황보고</h2>
          <div class="con">

            <div class="table table_style02 report_table2">
            <form class="form-horizontal" role="form" id="regForm" method="post" action="${pageContext.request.contextPath}/mobile/board" accept-charset="utf-8">
              <table>
                <caption>상황보고 작성 표</caption>
                <tbody>
                  <tr>
                    <th>제목</th>
                    <td><input type="text" name="boardTitle" required/></td>
                  </tr>
                  <tr>
                    <th>분류</th>
                    <td>
                    <select id="boardSubCategory" name="boardSubCategory" onchange="javascript:reportSubCategoryChange(this.value);" >
					  <option value="발생보고(긴급)">발생보고(긴급)</option>
					  <option value="대회일반">대회일반</option>
					  <option value="수범사례">수범사례</option>
					</select>
					</td>
                  </tr>
                  <tr>
                    <th>비밀글</th>
                    <td>
                    <select id="isPrivacy" name="isPrivacy" >
					  <option value="N" selected>전체</option>
					  <option value="Y">비밀</option>
					</select>
					</td>
                  </tr>
                  <tr>
                    <th>내용</th>
                    <td><div style="min-height:200px;border: 1px solid #e0e0e0;" contenteditable="true" id='boardContentDiv'></div></td>
                  </tr>
                  <tr>
                    <th>첨부파일</th>
                    <td>
                      <div class="filebox"> 
                        <input class="upload-name" value="파일선택" disabled="disabled"> <label for="ex_filename" class="txt-light">찾아보기</label> 
                        <input type="file" id="ex_filename" name="file[]" multiple="multiple" onchange="javascript:listingFiles();" class="upload-hidden">
                        <p id="fileList_names"></p> 
                      </div>
                  </td>
                  </tr>
                </tbody>
              </table>
              <input type="hidden" name="boardType" value="normal">
              <input type="hidden" name="boardCategory" value="상황">
				<input type="hidden" id="boardContent" name="boardContent" value="">
              <input type='hidden' id='fileList' name='fileList' value=''/>
              </form>
            </div>

            <div class="btn_wrap txt-center mt20">
                <a class="btn twin_btn bg_navy txt-light mr10" href="./list.do">취소</a>
                <a class="btn twin_btn bg_orange txt-light" href="#" onclick="javascript:boardWrite();">등록</a>
            </div>



          </div>
        </section>
        <!-- //  : contents -->
    </div>
</div>

</body>
</html>
