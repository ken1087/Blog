<%@page import="cos.com.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty list and state != 1}">
	<c:redirect url="board" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>kang Blog</title>
<!-- Latest compiled and minified CSS -->
<!-- 부트스트랩 -->

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- 부트스트랩 -->
</head>
<style>
.page-item.active .page-link {
	z-index: 1;
	color: #343A40;
	background-color: #343A40;
	border-color: #343A40;
}
</style>
<body>
	<c:import url="/base/header.jsp"></c:import>
	<div class="container">
		<table class="table table-dark table-hover" border="1">
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자아이디</td>
				<td>조회수</td>
				<td>작성일</td>
				<td>수정일</td>
			</tr>
			<tbody id="ajaxtable">
				<c:forEach var="item" items="${list}">
					<tr class="atable">
						<td>${item.num}</td>
						<td><a href="board?cmd=boardView&num=${item.num}&userID=${item.userID}">${item.title}</a></td>
						<td>${item.userID}</td>
						<td>${item.readCount}</td>
						<td>${item.createDate}</td>
						<td>${item.updateDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination">
			<c:choose>
				<c:when test="${start==0}">
					<li class="page-item disabled"><a class="page-link" href="board?cmd=boardListPage&start=${start-5 }&end=${end-5}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class=" btn btn-dark" href="board?cmd=boardListPage&start=${start-5 }&end=${end-5}">Previous</a></li>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${maxListNum <= start+5}">
					<li class="page-item disabled"><a class="page-link" href="board?cmd=boardListPage&start=${start +5 }&end=${end+5}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class=" btn btn-dark" href="board?cmd=boardListPage&start=${start+5 }&end=${end+5}">Next</a></li>
				</c:otherwise>
			</c:choose>
			
			
		</ul>
		
		<a href="board?cmd=boardWrite"><button type="button"
				class="btn btn-dark">글쓰기</button></a> <a
			href="board?cmd=memberView&userID=${sessionScope.userID}"><button
				type="button" class="btn btn-dark">내 정보</button></a>
	</div>
	<c:import url="/base/footer.jsp"></c:import>
</body>
</html>