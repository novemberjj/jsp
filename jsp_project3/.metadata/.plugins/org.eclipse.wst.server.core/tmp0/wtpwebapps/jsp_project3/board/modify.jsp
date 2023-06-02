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
<form action="/brd/edit" method="post" enctype="multipart/form-data">
bno : <input type="text" name="bno" value=${bvo.bno } readonly="readonly"> <br>
writer : <input type="text" name="writer" value="${bvo.writer }" readonly="readonly"> <br>
title : <input type="text" name="title" value="${bvo.title }"> <br>
content : <textarea rows="3" cols="30" name="content">${bvo.content }</textarea> <br>
image : <img alt="없음" src="/_fileUpload/th_${bvo.img_file }">
<input type="hidden" name="img_file" value="${bvo.img_file }"> <br>
<input type="file" name="new_file">
<button type="submit">modify</button>
</form>
</body>
</html>