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
<h1>Product Detail Page</h1>
	<table border="1">
			<tr>
				<th>pno</th>
				<td>${pvo.pno }</td>
			</tr>
			<tr>
				<th>pname</th>
				<td>${pvo.pname }</td>
			</tr>
			<tr>
				<th>price</th>
				<td>${pvo.price }</td>
			</tr>
			<tr>
				<th>regdate</th>
				<td>${pvo.regdate }</td>
			</tr>
			<tr>
				<th>madeby</th>
				<td>${pvo.madeby }</td>
			</tr>
	</table>

	<a href="modify.pd?pno=${pvo.pno }"><button type="button">modify</button></a>
	<a href="remove.pd?pno=${pvo.pno }"><button type="button">delete</button></a>
</body>
</html>