<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>Modify Page</h1>

<form action="/mem/edit" method="post">
  <div class="mb-3" style="width: 300px">
    <label for="exampleInputEmail1" class="form-label">ID</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="id" value="${ses.id }" readonly="readonly">
  </div>
  <div class="mb-3" style="width: 300px">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="password" value="${ses.password }">
  </div>
  <div class="mb-3" style="width: 300px">
    <label for="exampleInputPassword1" class="form-label">Email</label>
    <input type="email" class="form-control" id="exampleInputPassword1" name="email" value="${ses.email }">
  </div>
  <div class="mb-3" style="width: 300px">
    <label for="exampleInputPassword1" class="form-label">Age</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="age" value="${ses.age }">
  </div>
  <button type="submit" class="btn btn-primary">수정완료</button>
</form>

</body>
</html>