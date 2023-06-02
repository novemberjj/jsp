<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Register Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>Board Register Page</h1>
<!--  
<form action="/brd/insert" method="post" enctype="multipart/form-data">
title : <input type="text" name="title"> <br>
writer : <input type="text" name="writer" value="${ses.id }" readonly="readonly"> <br>
content : <textarea rows="3" cols="30" name="content"></textarea> <br>
imageFile : <input type="file" id="file" name="image_file" accept="image/png, image/jpg, image/jpeg, image/bmp, image/gif"> <br>
<button type="submit">register</button>
</form>
-->
<br>
<form action="/brd/insert" method="post" enctype="multipart/form-data">
  <div class="mb-3" style="width: 500px">
    <label for="exampleInputEmail1" class="form-label">title</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="title" >
  </div>
  <div class="mb-3" style="width: 500px">
    <label for="exampleInputPassword1" class="form-label">writer</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="writer" value="${ses.id }" readonly="readonly">
  </div>
<div class="mb-3" style="width: 500px">
  <label for="exampleFormControlTextarea1" class="form-label">content</label>
  <textarea class="form-control" id="exampleFormControlTextarea1" cols="30" rows="3" name="content"></textarea>
</div>
<div class="mb-3" style="width: 500px">
  <label for="formFile" class="form-label">imageFile</label>
  <input class="form-control" type="file" id="formFile" name="image_file" accept="image/png, image/jpg, image/jpeg, image/bmp, image/gif">
</div>
  <button type="submit" class="btn btn-primary">등록</button>
</form>

</body>
</html>