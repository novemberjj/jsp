<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
<h1>Register Page</h1>
<form action="/brd/insert" method="post" enctype="multipart/form-data">
writer : <input type="text" name="writer" value="${ses.id }"> <br>
title : <input type="text" name="title"> <br>
content : <textarea rows="3" cols="30" name="content"></textarea> <br>
image : <input type="file" id="file" name="img_file"
accept="image/png, image/jpg, image/jpeg, image/bmp, image/gif"> <br>

<button type="submit">register</button>
</form>

</body>
</html>