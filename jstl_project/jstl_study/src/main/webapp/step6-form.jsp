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
<!-- get 방식 : 쿼리스트링을 달고 URL 상에서 이동하는 방식 -->
<!-- post 방식 : 데이터를 숨겨서 가는 방식 보안상, 내용이 많을 경우 -->
	<form action="step6-action.jsp" method="post">
		주문자 : <input type="text" name="name"> <br>
		목록 <br>
		<input type="checkbox" name="food" value="사과">사과
		<input type="checkbox" name="food" value="배">배
		<input type="checkbox" name="food" value="귤">귤
		<input type="checkbox" name="food" value="감">감
		<input type="checkbox" name="food" value="바나나">바나나
		<button type="submit">주문</button>
	</form>
</body>
</html>