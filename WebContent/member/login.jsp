<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kang Blog</title>
</head>
<body>
<c:import url="/base/header.jsp"></c:import>
<%
	application.setAttribute("test", "어플리케이션");
%>
<style>
	body{
		text-align: center;
	}
	.container{
		width:600px;
		display: inline-block;
		margin-top: 300px;
	}
	.form-control form-control-lg{
		
	}
</style>
<div class="container">
	<form action="member?cmd=memberLoginProc" method="POST">
		<input type="text" name="userID" style="width:100%" placeholder="로그인" class="form-control form-control-lg" required/><br />
		<input type="password" name="userPassword" style="width:100%" placeholder="비밀번호" class="form-control form-control-lg" required/><br />
		Remember me?<input type="checkbox" name ="idSave" value="on"/><br />
		<input type="submit" style="width:100%" class="bb btn btn-dark" value="로그인" />
	</form>
</div>
	<c:import url="/base/footer.jsp"></c:import>
</body>
</html>
