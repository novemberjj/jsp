<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>Index Page</h1>

<c:if test="${ses.id ne null }">
<br>
${ses.id }님이 login 하였습니다. <br>
${ses.id }님은 ${ses.age }세 입니다. <br>
계정생성일 : ${ses.reg_date } <br>
마지막 접속일 : ${ses.last_login } <br>
<br>
<div class="btn-group" role="group" aria-label="Basic outlined example">
<table border="1">
<tr>
<td><a href="/brd/page"><button type="button" class="btn btn-outline-primary">게시판</button></a> <br></td>
<td><a href="/brd/register"><button type="button" class="btn btn-outline-primary">게시글 작성</button></a></td>
<td><a href="/mem/logout"><button type="button" class="btn btn-outline-primary">로그아웃</button></a> </td>
</tr>
<tr>
<td><a href="/mem/modify"><button type="button" class="btn btn-outline-primary">회원정보수정</button></a></td>
<td><a href="/mem/list"> <button type="button" class="btn btn-outline-primary">회원리스트</button> </a></td>
<td><a href="/mem/remove"><button type="button" class="btn btn-outline-primary">회원탈퇴</button></a> <br></td>
</tr>
</table>
</div>
</c:if>


<c:if test="${ses.id eq null }">
<br>
<div class="btn-group" role="group" aria-label="Basic outlined example">
  <a href="/brd/page"><button type="button" class="btn btn-outline-primary">게시판</button></a>
  <a href="/mem/login"><button type="button" class="btn btn-outline-primary">로그인</button></a>
  <a href="/mem/join"><button type="button" class="btn btn-outline-primary">회원가입</button></a>
</div>


</c:if>

<script type="text/javascript">
const msg_login=`<c:out value="${msg_login}" />`;
console.log(msg_login);
if(msg_login==='0'){
	alert('로그인 정보가 올바르지 않습니다.');
}
</script>
</body>
</html>