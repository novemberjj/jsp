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
<form action="/mem/edit" method="post">
ID : <input type="text" value="${ses.id}" readonly="readonly" name="id"> <br>
Password : <input type="text" value="${ses.password}" name="password"> <br>
email : <input type="text" value="${ses.email}" name="email"> <br>
age : <input type="text" value="${ses.age}" name="age"> <br>
<!-- 수정되지 않은 데이터 같이 가져감 -->
<input type="hidden" name="reg_date" value="${ses.reg_date }">
<input type="hidden" name="last_login" value="${ses.last_login }">

<button type="submit">modify</button>

</form>
</body>
</html>