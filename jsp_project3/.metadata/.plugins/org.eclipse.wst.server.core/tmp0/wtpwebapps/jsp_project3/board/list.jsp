<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>Board List Page</h1>
<!-- 검색 라인 -->
<form action="/brd/page" method="post">
<div>
<c:set value="${pgh.pgvo.type }" var="typed"></c:set>
<select name="type">
<option ${typed==null ? 'selected':'' }>Choose</option>
<option value="t" ${typed eq 't' ? 'selected':'' }>title</option>
<option value="w" ${typed eq 'w' ? 'selected':'' }>writer</option>
<option value="c" ${typed eq 'c' ? 'selected':'' }>content</option>
<option value="tc" ${typed eq 'tc' ? 'selected':'' }>title or content</option>
<option value="tw" ${typed eq 'tw' ? 'selected':'' }>bno</option>
<option value="cw" ${typed eq 'cw' ? 'selected':'' }>reg_date</option>
<option value="m" ${typed eq 'm' ? 'selected':'' }>내가 쓴 글</option>
</select>
<input type="text" name="keyword" placeholder="Serach"">
<input type="hidden" name="pageNo" value="${pgh.pgvo.pageNo }">
<input type="hidden" name="qty" value="${pgh.pgvo.qty }">
<input type="hidden" name="id" value="${ses.id }">
<button type="submit">Search</button>
</div>
</form>

<table class="table table-hover">
<tr>
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>작성일</th>
<th>조회수</th>
</tr>
<tbody>
<c:forEach items="${list }" var="bvo">
<tr>
<td>${bvo.bno }</td>
<td>
<c:if test="${bvo.img_file ne null }">
<img alt="없음" src="/_fileUpload/th_${bvo.img_file }">
</c:if>
<a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
<td>${bvo.writer }</td>
<td>${bvo.reg_date }</td>
<td>${bvo.read_count }</td>
</tr>
</c:forEach>
</tbody>
</table>
<a href="/"><button type="button">index</button></a><br>

<!-- 페이지 네이션 위치 -->
<!-- 컨트롤러에서 page 정보를 싣고 와야 함 -->
<!-- start~endPage까지 숫자 반복 forEach -->

<!-- 이전 페이지 -->
<c:if test="${pgh.prev }">
	<a href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ▷ </a>
</c:if>

<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
	<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } | </a>
</c:forEach>

<!-- 다음 페이지 -->
<c:if test="${pgh.next }">
	<a href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ▷ </a>
</c:if>


</body>
</html>