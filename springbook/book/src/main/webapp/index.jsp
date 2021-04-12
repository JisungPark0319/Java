<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>회원제 게시판</title>
</head>
<body>
	<u:isLogin>
	CT: ${authUser.name}님 안녕하세요.
	<a href="logout.do">[로그아웃]</a>
		<a href="changePwd.do">[암호 변경]</a>
		<a href="article/list.do">[게시글목록]</a>
	</u:isLogin>
	<u:notLogin>
	CT: <a href="join.do">[회원가입]</a>
		<a href="login.do">[로그인]</a>
	</u:notLogin>
	</br>
</body>
</html>