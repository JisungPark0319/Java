<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>sensorTest</title>
</head>
<body>
	<table border="1">
		<c:forEach var="sensorList" items="${sensor }">
			<tr>
				<td>${sensorList.id }</td>
				<td>${sensorList.data }</td>
				<td>${sensorList.createDate }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>