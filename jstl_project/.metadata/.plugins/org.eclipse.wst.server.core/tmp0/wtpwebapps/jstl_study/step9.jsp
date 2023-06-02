<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.PersonVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!-- c:set var="변수" value="지정 값" -->
	
	<%
		//스클립틀릿은 이어진다
		PersonVO p = new PersonVO("홍길동",45);
		request.setAttribute("pvo", p);
	%>
	
	<%--스클립틀릿(자바코드)으로 확인하는 방법 --%>
	이름 : <%= p.getName() %> <br>
	나이 : <%= p.getAge() %> <br>
	<hr>
	
	<%-- EL 방식으로 확인하는 방법 --%>
	이름 : ${pvo.name } <br>
	이름 : ${pvo.age } <br>
	<hr>
	
	<c:set var="person" value="${pvo }"> <!-- 그냥 변수 선언 하는 것 -->
	</c:set>
		이름 : ${person.name} <br>
		나이 : ${person.age} <br>
	
</body>
</html>