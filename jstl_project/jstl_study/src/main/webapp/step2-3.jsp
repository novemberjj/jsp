<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL param 연습2</title>
</head>
<body>
	<!-- 스클립틀릿 방식에서는 넘어오는 parameter값이 문자열로 인식
	age+1의 값을 전달하면 201 나이 뒤에 1이 추가되는 형태로 전송 -->
	1. 스클립틀릿 방식으로 파라미터를 전달받음 <br>
	<%= request.getParameter("nick") %> <br>
	<%= request.getParameter("age")+1 %>세 <br> <!-- 201세 -->
	<br><br>
	<hr>
	<!-- EL 방식은 내부적으로 형변환(Integer.parseInt())
	이 이루어져 연산이 가능 20+1로 인식되어 전송 -->
	2. EL 방식으로 파라미터를 전달받음 <br>
	${param.nick } <br>
	${param.age+1 }세 <br><!-- 21세 -->
	<a href="step2-2.jsp">step2-2로 이동</a>
	<br><br>
	
	<hr>
	<form action="step2-4.jsp">
		<input type="checkbox" name="food" value="삼겹살"> 삼겹살 <br>
		<input type="checkbox" name="food" value="목살"> 목살 <br>
		<!-- food가 2개라 배열형태로 전달됨 -->
		<input type="submit" value="전송">
	</form>
	
</body>
</html>