function logout() {
	if (confirm('로그아웃 하시겠습니까?')) {
		document.location.href = '${pageContext.request.contextPath}/logout.do';
	}
}
function bodyLoad(url) {
	$('#content-page').load(url);
}