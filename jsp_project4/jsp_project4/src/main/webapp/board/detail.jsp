<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Detail Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<h1>Board Detail Page</h1>
<div>
<c:if test="${bvo.image_file ne null }">
<img alt="없음" src="/_fileUpload/${bvo.image_file }">
</c:if>
</div>
<table class="table">
<tr>
<th>bno</th>
<td>${bvo.bno }</td>
</tr>
<tr>
<th>reg_date</th>
<td>${bvo.reg_date }</td>
</tr>
<tr>
<th>read_count</th>
<td>${bvo.read_count }</td>
</tr>
<tr>
<th>title</th>
<td>${bvo.title }</td>
</tr>
<tr>
<th>writer</th>
<td>${bvo.writer }</td>
</tr>
<tr>
<th>content</th>
<td>${bvo.content }</td>
</tr>
</table>
<br>
<a href="/brd/modify?bno=${bvo.bno }"><button>수정</button></a>
<a href="/brd/remove?bno=${bvo.bno }"><button>삭제</button></a>

<div>
<br><br>
<h3>comment line</h3>
<input type="text" id="cmtWriter" value="${ses.id }" readonly="readonly"> <br>
<input type="text" id="cmtText" placeholder="Add Comment">
<button type="button" id="cmtAddBtn">댓글 등록</button>
</div> <br>

<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
      
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
const bnoVal=`<c:out value="${bvo.bno}" />`
</script>
<script src="/resources/board_detail.js"></script>
<script type="text/javascript">
printCommentList(bnoVal);
</script>
</body>
</html>