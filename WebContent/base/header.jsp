<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 메뉴바 시작 -->
	
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		
	</head>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	  <!-- Brand -->
	  <a class="navbar-brand" href="index.jsp">Blog</a>
	
	  <!-- Toggler/collapsibe Button -->
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <!-- Navbar links -->
	  <div class="collapse navbar-collapse" id="collapsibleNavbar">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	      <c:choose>
			<c:when test="${empty sessionScope.userID}">
		    	<a class="nav-link" href="member?cmd=memberLogin">로그인</a> 
			</c:when>
		  	<c:otherwise>
		    	<a class= "nav-link" href="member?cmd=memberLogout">로그아웃</a> 
			</c:otherwise>
		  </c:choose>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="member?cmd=memberJoin">회원가입</a>
	      </li>
	    </ul>
	  </div>
	  <div> 
	  <style>
	  	.custom-select{
	  		width:90px;
	  	}
	  	.form-control{
	  		width:200px;
	  	}
	  	.search_box{
	  		display: grid;
	  		grid-template-columns: 1fr 2fr 1fr;
	  	}
	  </style>
		<form action="board?cmd=boardListsearch" method="POST">
			<div class="search_box">
	  			<select name="option" class="custom-select" >
	  				<option value="title">제목</option>
	  				<option value="userID">작성자</option>
	  				<option value="num">번호</option>
	  			</select>
	  	
	  		
	  			<input type="text" name="search" onkeyup="" class=" form-control">
	  			<input class="btn btn-dark" type="submit" value="검색"/>
	  		</div>
	  	</form>
	  	
			  	
	  	
	  	
	  	
	  	
	  </div>
	</nav>
	<!-- 메뉴바 끝 -->
