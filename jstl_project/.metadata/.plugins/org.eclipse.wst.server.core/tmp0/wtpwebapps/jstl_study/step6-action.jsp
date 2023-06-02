<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//post 방식의 한글 처리 (인코딩 설정)
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
주문자 : ${param.name} <br>
<hr>
주문목록 <br>
<c:forEach items="${paramValues.food }" var="fname" varStatus="i">
	${i.count }. ${fname } <br>
</c:forEach>
주문하신 메뉴 나왔습니다


<!-- HTTP 상태 500 내부 서버 오류 메시지 잘 보기  -->
</body>
</html>