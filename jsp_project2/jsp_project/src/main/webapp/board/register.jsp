<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
</head>
<body>
<h1>Board Register Page</h1>

<form action="/brd/insert" method="post" enctype="multipart/form-data">
title : <input type="text" name="title"> <br>
writer : <input type="text" value="${ses.id }" name="writer" readonly="readonly"> <br>
content : <textarea rows="3" cols="30" name="content"></textarea> <br>
imageFile : <input type="file" id="file" name="image_file" accept="image/png, image/jpg, image/jpeg, image/bmp, image/gif"> <br>
<button type="submit">register</button>
</form>
</body>
</html>