<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=devicee-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link class="include" rel="stylesheet" type="text/css"
	href="js/jquery.jqplot.min.css" />
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.jqplot.min.js"></script>
<title>모니터링</title>

<script>
	$(function() {
		$(".btnOn").on("click", function(e) {
			$.ajax({
				url:"http://localhost:8080/app/button/on.do?id=${device.id}",
				dataType:"text",
				success:function(data) {
					alert(data);
				},
				error:function() {
					alert("fail");
				}
			});
		});
		$(".btnOff").on("click", function(e) {
			$.ajax({
				url:"http://localhost:8080/app/button/off.do?id=${device.id}",
				dataType:"text",
				success:function(data) {
					alert(data);
				},
				error:function() {
					alert("fail");
				}
			});
		});
		
		var auto_1 = setInterval(function() {
		    $.ajax({
		   	 url:"http://localhost:8080/app/sensor/getdata.do?id=${device.id}",
			 dataType:"json",
			 success:function(data) {
				 var arr = [];
				 for(var i in data) {
					 arr.push(data[i].data);
				 }
				 //$("#graph").remove();
				 $("#graph").empty();
				 $.jqplot ('graph', [arr.reverse()],
							{
					 			title : '디바이스 온도',
					 			axes : {
					 				yaxis: {
					 					label: "온도 값"
					 				}
					 			}
							}		 
				 );
			 },
			 error:function() {
				 console.log("fail");
			 }
		    });
		}, 1000);
	});
</script>

</head>
<body>
	디바이스 ID : ${device.id} <br />
	디바이스 Name : ${device.name} <br />
	디바이스 연결 상태 : ${access.state} <br />
	<p>
		<c:if test="${access.state eq 'CONNECT'}"> 
			Device 연결
			<p>
				<button class="btnOn">on</button>
				<button class="btnOff">off</button>
			</p>
			<p>
				<div id="graph" style="width: 300px; height: 300px;"></div>
			</p>
		</c:if>
	</p>
</body>
</html>