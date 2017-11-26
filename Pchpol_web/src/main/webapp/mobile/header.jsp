<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function logout(){
	if(confirm('로그아웃 하시겠습니까?')){
		location.href = '${pageContext.request.contextPath}/mobile/logout.do';
	}
}
jQuery(document).ready(function($) {
	
	$.ajax({
		type : 'get',
		async : true,
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		url : '${pageContext.request.contextPath}/mobile/notice/top1.do?boardType=notice',
		success : function(res) {
			if (res.success) {
				if(typeof res.data !== 'undefined'){
					var url = "${pageContext.request.contextPath}/mobile/notice/view.do?curBoardNo="+res.data.boardNo;
					url += '&page=1&rows=10&sidx=boardWriteDt&sord=desc&boardType=notice&curBoardSeq=0';
					$("#noticeTopLink").attr('href',url);
					$("#noticeTopCategory").html("["+res.data.boardCategory+"]");
					$("#noticeTopTitle").html(res.data.boardTitle);
				}
				
			} else {
			}
		},
		error : function(data1, status, err) {
		}
	});
	$.ajax({
		type : 'get',
		async : true,
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		url : '${pageContext.request.contextPath}/mobile/notice/count.do?boardType=notice',
		success : function(res) {
			if (res.success) {
				if(typeof res.data !== 'undefined'){
					$("#boardCnt").html(res.data);			
				}
			} else {
			}
		},
		error : function(data1, status, err) {
		}
	});
});
</script>
<div class="cover"></div>
            <a class="h_btn" href="${pageContext.request.contextPath}/mobile/index.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-home.png" alt="홈으로" /></a>
            <h1><a href="${pageContext.request.contextPath}/mobile/index.do">평창올림픽 동원경찰관</a></h1>
            <a href="#" class="m_btn"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-header-menu.png" alt="메뉴"></a>                      
            <div class="menu_wrap">
            <div class="m_top">
                    <c:choose>
                    <c:when test="${!empty admin}">
            			<span>${admin.adminName }</span>
           			</c:when>
                    <c:otherwise>
            			<span>${unit.code2.code1depth }</span> <span>${unit.unitName }</span>
                    </c:otherwise>
                    </c:choose>
            		<span>님</span>
                    <a class="" href="javascript:logout();">Log-out</a>
                    <a class="home" href="${pageContext.request.contextPath}/mobile/index.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-btn-home.png" alt="홈으로" /></a>
                  </div>
                  <div class="m_gnb">
                    <li><a href="${pageContext.request.contextPath}/mobile/notice/list.do">공지사항</a></li>
                      <li><a href="${pageContext.request.contextPath}/mobile/work/workinfo.do">근무정보</a></li>
                      <li>
                        <a class="de1" href="#this">숙영/급식</a>
                        <ul class="de2">
                          <li><a href="${pageContext.request.contextPath}/mobile/camp/lodge.do">- 숙영시설</a></li>
                          <li><a href="${pageContext.request.contextPath}/mobile/camp/meal.do">- 급식시설</a></li>
                        </ul>
                      </li>
                      <li>
                        <a class="de1"  href="#this">평창올림픽</a>
                        <ul class="de2" >
                          <li><a href="${pageContext.request.contextPath}/mobile/olympic/schedule.do">- 경기일정</a></li>
                          <li><a href="${pageContext.request.contextPath}/mobile/olympic/stadiumInfoList.do">- 대회시설 정보</a></li>
                        </ul>
                      </li>
                      <li><a href="${pageContext.request.contextPath}/mobile/report/list.do">상황보고</a></li>
                      <li><a href="${pageContext.request.contextPath}/mobile/proposal/list.do">건의사항</a></li>
                  </div>
                  <div class="btn_call">
                    <a href="tel:123-1234"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-login-call.png" alt="콜센터연결"/>콜센터 연결</a>
                  </div>
            </div>
            <!-- // 공지-->
            <aside>
              <a id="noticeTopLink" class="notice over-hidden" href="notice/notice_view.html">
                <img src="${pageContext.request.contextPath}/mobile/resources/images/ico-header-notice.png" alt="공지사항" />
                <span id="noticeTopCategory" class="font-bold"></span> <span id="noticeTopTitle" ></span>
              </a>
              <a class="alert"  href="${pageContext.request.contextPath}/mobile/notice/list.do"><img src="${pageContext.request.contextPath}/mobile/resources/images/ico-header-alert.png" alt="공지 알람" /><span id="boardCnt" class="num"></span></a>
            </aside>
            <!-- // 공지-->
            
            
<textarea id="report1" style="display:none;">            
(긴급)
□ 발생일시 및 장소 
 ○ 2018. 0. 0. 00:00, 평창 ㅇㅇㅇ경기장 
□ 대상자 인적사항 
 ○ 성  명 :     
 ○ 연락처 : 
 ○ 생년월일 : 
□ 용의자 관련사항
 ○ 성  명 : 
 ○ 인상착의 : 
□ 발생 개요
 ○ 
□ 조치 사항 
 ○   :   경 신고접수(접수자 ㅇㅇ 홍길동) 
 ○   :   경 무전전파 및 현장조치
</textarea>  
<textarea  id="report2" style="display:none;">
□ 일시·장소 : 2018. 0. 0. 00:00, 평창 ㅇㅇㅇ경기장 
□ 상황구분 : (예시) 선수부상 / 경기지연 / 시설고장
□ 상황개요
 ○   
</textarea>  
<textarea  id="report3" style="display:none;">
□ 일시·장소 : 2018. 0. 0. 00:00, 평창 ㅇㅇㅇ경기장 
□ 주요내용
 (예시) 00경기장에서 부모와 떨어져 울고있는 미아발견, 경기장 안내방송으로 부모를 찾아 안전하게 인계
□ 근무자
  - 00기동대 0제대 00 홍길동(010-0000-0000)
</textarea>  
<script>
function reportSubCategoryChange(el){
	if(el=="발생보고(긴급)"){
		$("#boardContent").val($("#report1").val());
	}else if(el=='대회일반'){
		$("#boardContent").val($("#report2").val());
	}else if(el=='수범사례'){
		$("#boardContent").val($("#report3").val());
	}
}
</script>