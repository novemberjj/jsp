<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h1>Board Modify Page</h1>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<br>
<!-- 
<form action="/brd/edit" method="post" enctype="multipart/form-data">
bno : <input type="text" name="bno" value="${bvo.bno }" readonly="readonly">
title : <input type="text" name="title" value="${bvo.title }"> <br>
writer : <input type="text" name="writer" value="${bvo.writer }" readonly="readonly"> <br>
content : <textarea rows="3" cols="30" name="content">${bvo.content }</textarea> <br>
imageFile : <img alt="없음" src="/_fileUpload/th_${bvo.image_file }">
<input type="hidden" name="image_file" value="${bvo.image_file }"> <br>
<input type="file" name="new_file"> <br>
<button type="submit">수정완료</button>
</form>
 -->

<form action="/brd/edit" method="post" enctype="multipart/form-data">
  <div class="mb-3" style="width: 500px">
    <label for="exampleInputEmail1" class="form-label">bno</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="bno" value="${bvo.bno }" readonly="readonly" >
  </div>
  <div class="mb-3" style="width: 500px">
    <label for="exampleInputEmail1" class="form-label">title</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="title" value="${bvo.title }" >
  </div>
  <div class="mb-3" style="width: 500px">
    <label for="exampleInputPassword1" class="form-label">writer</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="writer" value="${bvo.writer }" readonly="readonly">
  </div>
<div class="mb-3" style="width: 500px">
  <label for="exampleFormControlTextarea1" class="form-label">content</label>
  <textarea class="form-control" id="exampleFormControlTextarea1" cols="30" rows="3" name="content">${bvo.content }</textarea>
</div>
<div class="mb-3" style="width: 500px">
  <label for="formFile" class="form-label">imageFile</label>
  <img alt="없음" src="/_fileUpload/th_${bvo.image_file }">
  <input type="hidden" name="image_file" value="${bvo.image_file }"> <br>
  <input class="form-control" type="file" id="formFile" name="new_file" accept="image/png, image/jpg, image/jpeg, image/bmp, image/gif">
</div>
  <button type="submit" class="btn btn-primary">수정완료</button>
</form>
</body>
</html>