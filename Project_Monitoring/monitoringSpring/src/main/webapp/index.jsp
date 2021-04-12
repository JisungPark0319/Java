<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>모니터링</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty devicePage }"><jsp:forward page="accesslist.do"/></c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<td>디바이스</td>
					<td>접속 상태</td>
					<td>시간</td>
				</tr>
				<c:if test="${devicePage.hasNoDevice() }">
					<tr>
						<td colspan="4">디바이스가 없습니다</td>
					</tr>
				</c:if>
				<c:forEach var="device" items="${devicePage.device }">
					<tr>
						<td>
							<a href="getDevice.do?id=${device.id }">
							<c:out value="${device.id }"/>
							</a>
						</td>
						<td>${device.state }</td>
						<td>${device.accessDate }</td>
					</tr>
				</c:forEach>
				<c:if test="${devicePage.hasDevice() }">
					<tr>
						<td colspan="4">
							<c:if test="${devicePage.startPage > 5 }">
								<a href="accesslist.do?pageNo=${devicePage.startPage - 5 }">[이전]</a>
							</c:if>
							<c:forEach var="pNo" begin="${devicePage.startPage }" end="${devicePage.endPage }">
								<a href="accesslist.do?pageNo=${pNo }">[${pNo }]</a>
							</c:forEach>
							<c:if test="${devicePage.endPage < devicePage.totalPages }">
								<a href="accesslist.do?pageNo=${devicePage.startPage + 5 }">[다음]</a>
							</c:if>
						</td>
					</tr>
				</c:if>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>