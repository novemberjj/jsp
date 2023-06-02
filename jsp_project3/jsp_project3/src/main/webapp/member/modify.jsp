<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
</head>
<body>
<h1>Modify Page</h1>
<form action="/mem/edit">
Id : <input type="text" name="id" value="${ses.id }" readonly="readonly"> <br>
Password : <input type="text" name="password" value="${ses.password }"> <br>
Email : <input type="text" name="email" value="${ses.email }"> <br>
Age : <input type="text" name="age" value="${ses.age }"> <br>
<button type="submit">수정완료</button>
</form>
</body>
</html>