<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
</head>
<body>
	<h1>Product Register Page</h1> <br>
	<form action="/insert.pd" method="post">
		Name : <input type="text" name="pname"> <br>
		Price : <input type="text" name="price"> <br>
		MadeBy : <input type="text" name="madeby"> <br>
		
		<button type="submit">register</button>
	</form>

</body>
</html>