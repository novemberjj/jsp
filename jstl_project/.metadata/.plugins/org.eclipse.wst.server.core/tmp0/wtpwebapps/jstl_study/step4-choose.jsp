<%@page import="model.PersonVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- import 여기다가 써도 됨 묶어서~ -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL choose 연습</title>
</head>
<body>
<!-- model 안에 PersonVO class 생성 멤버변수 : name, age 추가
	생성자, getter/setter -->
	<%
		PersonVO p= new PersonVO("홍길동",30);
		request.setAttribute("p", p);
	%>
	
	<!-- EL 방식으로 name, age를 출력 -->
	${requestScope.p.name }, ${p.age }세 <!-- requsetScope가 생략되어있는 것 -->
	<hr>
	
	<!-- 나이가 20살 이상이면 성인입니다. -->
	<c:if test="${p.age>=20 }">
	${p.name } 성인입니다. <br>
	</c:if>
	<hr>
	
	<!-- 다중조건을 사용할 경우 choose, while, otherwise -->
	<c:choose>
		<c:when test="${p.age>=20}"> <!-- 값이 큰 조건을 먼저 적는다 -->
			성인입니다. <br>
		</c:when>
		<c:when test="${p.age>=10}">
			청소년입니다. <br>
		</c:when>
		<c:otherwise>
			유아입니다. <br>
		</c:otherwise>
	</c:choose>
	
	<!-- step5-form.jsp : 이름과 나이 입력받기 step5-action.jsp로 전송 -->
	<!-- step5-action.jsp : 값을 받아 이름 출력, 나이 출력 후 c:choose -->
	<!-- 나이가 20이상이면 성인, 15세 이상이면 청소년, 5살 이상이면 어린이, 1살 이상이면 유아, 나머지 아직 안태어남 -->
</body>
</html>