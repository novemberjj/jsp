<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>List Page</h1>

<table class="table">
<thead>
<tr>
<th>ID</th>
<th>email</th>
<th>age</th>
<th>reg_date</th>
<th>last_login</th>
</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="mvo">
<tr>
<td>${mvo.id }</td>
<td>${mvo.email }</td>
<td>${mvo.age }</td>
<td>${mvo.reg_date }</td>
<td>${mvo.last_login }</td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>