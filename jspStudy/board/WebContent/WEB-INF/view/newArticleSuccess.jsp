<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 등록</title>
</head>
<body>
	게시글을 등록했습니다.
	<br>
	<a href="${pageContext.request.contextPath }/article/list.do">[게시글목록보기]</a>
	<a href="${pageContext.request.contextPath }/article/read.do?no=${newArticleNo}">[게시글내용보기]</a>
</body>
</html>