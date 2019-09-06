<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kang Blog</title>
</head>
<body>
<c:import url="/base/header.jsp"></c:import>
<style>
	.container{
		width:70%;
	}
</style>
<div class="container">
	<table border="1" class="table table-borderd table-border2" style="width:100%">
		<tr style="background: #343A40">
			<td style="color: white">${member.num }</td>
		</tr>
		
		<tr style="background: #343A40">
			<td style="color: white">${member.userID }</td>
		</tr>
		
		<tr style="background: #343A40">
			<td style="color: white">${member.userEmail} </td>
		</tr>
		
		<tr style="background: #343A40">
			<td style="color: white">${member.userAddress }</td>
		</tr>
		
		<tr style="background: #343A40">
			<td style="color: white">${member.userPhone }</td>
		</tr>
		
		<tr style="background: #343A40">
			<td style="color: white">${member.userGender }</td>
		</tr>
	</table>
	<a href="member?cmd=memberupdate&userID=${sessionScope.userID}" class="btn btn-dark">수정</a>
</div>
		
		
		

<c:import url="/base/footer.jsp"></c:import>
</body>
</html>