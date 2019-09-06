<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kang Blog</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
</head>
<style>
	#titlesize{
		width: 600px;
	}
</style>
<body>
<c:import url="/base/header.jsp"></c:import>
<style>
	body{
		text-align: center;
	}
	.container{
		width: 70%;
		text-align: left;
	}
</style>
<div class="container">
<h2>게시글 수정하기</h2>
<form action="board?cmd=boardUpdateProc" method="POST">
	<br />
		<input type="text" id="titlesize" style="width:100%" class="form-control form-control-lg" name="title" value="${boardu.title }" /><br />
		<textarea id="summernote" style="width:100%" name="content">${boardu.content }</textarea>
		<input type="hidden" name="userID" value="<%=session.getAttribute("userID") %>" /><br /> 
		<input type="hidden" name="num" value="${boardu.num }" /><br />
		<input type="submit" value="수정 완료" class="btn btn-dark" />
	</form>
</div>
	<script>
	$('#summernote').summernote({
		  height: 300,                 // set editor height
		  minHeight: null,             // set minimum height of editor
		  maxHeight: null,             // set maximum height of editor
		  focus: true                  // set focus to editable area after initializing summernote
		});
	 $('.dropdown-toggle').dropdown();
    </script>
<c:import url="/base/footer.jsp"></c:import>
</body>
</html>