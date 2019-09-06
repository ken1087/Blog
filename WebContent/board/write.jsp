<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kang Blog</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
</head>
<body>
<c:import url="/base/header.jsp"></c:import>
<style>
	body{
		text-align: center;
	}
	.container{
		width:70%;
		text-align: left;
	}
</style>
<div class="container">
   <form action="board?cmd=boardWriteProc" method="POST">
      <br /><input type="text" style="width:100%" class="form-control form-control-lg" name="title"  placeholder="제목"/><br />
   
         <textarea id="summernote" name="content"></textarea>
         <input type="hidden" name="userID" value="<%=session.getAttribute("userID") %>"/><br />
      <input type="submit" value="등록" style="width:100%" class="bb btn btn-dark" />
      </form>
   <script>
   $('#summernote').summernote({
        height: 300,                 // set editor height
        minHeight: null,             // set minimum height of editor
        maxHeight: null,             // set maximum height of editor
        focus: true                  // set focus to editable area after initializing summernote
      });
    $('.dropdown-toggle').dropdown();
    </script>
</div>
  <c:import url="/base/footer.jsp"></c:import>
</body>
</html>