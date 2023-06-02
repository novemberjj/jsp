<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="model.CarVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL연습</title>
</head>
<body>
	<h3>EL(Expression Language)</h3>>
	<%
		CarVO car = new CarVO("1234","소나타",2000);
		//request.setAttribute : request 객체에 값을 저장하는 역할
		//request.getAttribute : request 객체의 값을 가져오는 역할
		//request.setAttribute("변수명",값);
		request.setAttribute("cvo", car);
	%>
	<!-- 변수 출력 -->
	<strong>1. CarVO의 객체에서 변수 출력</strong><br>
	<!-- 방법 1 : requestScope를 사용 -->
	${requestScope.cvo.name}<br>
	
	<!-- 방법 2 : requsetScope (생략가능) -->
	${cvo.name}, ${cvo.num }, ${cvo.price }<br>
	
	<hr>
	
	<!-- 리스트로 변수 출력 -->
	<%
		ArrayList<CarVO> list = new ArrayList<>();
		list.add(new CarVO("4567","벤츠",5000));
		session.setAttribute("carList", list);
	%>
	<strong>2. CarVO의 객체를 session영역으로 설정 list로 출력</strong><br>
	${sessionScope.carList[0].name}<br>
	<hr>
	
	<!-- 해쉬맵으로 변수 출력 -->
	<%
		HashMap<String,CarVO>map= new HashMap<>();
		map.put("car1",new CarVO("7894","아반떼",2000));
		request.setAttribute("carMap",map);
	%>
	<strong>3. CarVO의 객체에서 HashMap으로 출력</strong><br>
	${requestScope.carMap.car1.name}<br>
	
	<a href="step2-1.jsp">step2-1로 이동</a>
	
</body>
</html>