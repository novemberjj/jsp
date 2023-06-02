<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- Query String으로 달고오는 애들은 param으로 받는다 -->
이름 : ${param.name} <br>
나이 : ${param.age} <br>

<c:choose> <!-- 숫자 큰거부터 써야된대 -->
<c:when test="${param.age>=20}">성인입니다.<br></c:when>
<c:when test="${param.age>=15}">청소년입니다.<br></c:when>
<c:when test="${param.age>=5}">어린이입니다.<br></c:when>
<c:when test="${param.age>=1}">유아입니다.<br></c:when>
<c:otherwise>안 태어났음<br></c:otherwise>
</c:choose>

<a href="step6-foreach.jsp">step 6 이동</a>
</body>
</html>