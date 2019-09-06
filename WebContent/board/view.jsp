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
	pageContext.setAttribute("data", "안녕");
%>
  <style>
        *{
            margin: 0;
            padding: 0;

        }
        .container{
            width: 75%;
            text-align: center;
            margin-top: 30px;
            
        }
        .userID{
        	margin-top:20px;
            width: 100%;
            display: grid;
            grid-template-columns: auto auto;
            justify-content: space-between;
            color: rgb(100, 100, 100);
        }
        .text_userID{
        	width: 100%;
        }
        .text{
            width: 100%;
            
        }
        .text_title{
        	width: 100%;
        }
        .text1{
            width: 150px;
            text-align: left;
            
        }
        .title{
            
            color: rgb(100, 100, 100);
            text-align: left;
        }
        .content{
            border: 1px solid lightgrey;
            height: 400px;
            text-align: left;
            margin-top: 20px;
            padding: 15px;
        }
        .a{
        	text-align:right;
        }
       
         .reply_{
            background-color: aqua;
            display: inline-block;
        }
        .reply{
            width: 100%;
        }
      	.reply-box2{
      		display: grid;
      		grid-template-columns: 1fr  6fr 1fr;
      		text-align: left;
      		height:50px;
      		border: 1px solid lightgrey;
      	}
    </style>

																								
   <div class="container">
   		<div class="a">
	       <a href="board?cmd=boardDelete&num=${boardw.num}&userID=${boardw.userID}" class="bb btn btn-dark">삭제</a>
	
		   <a href="board?cmd=boardUpdate&num=${boardw.num}" class="bb btn btn-dark">수정</a>
	   </div>
	   <form action="/blog/reply?cmd=replyWrite" method="post">
       <div class="userID">
            <div class="text_userID">글쓴이 : ${boardw.userID}</div>
            <div class="text1">
                <div>조회수 : ${boardw.readCount}</div>
                <div>작성일 : ${boardw.createDate}</div>
                <div>수정일 : ${boardw.updateDate}</div>
            </div>
       </div>
       <div class="title">
           <div class="text">글번호 : ${boardw.num}</div>
           <div class="text_title">제목 : ${boardw.title}</div>
       </div>
       <div class="content">${boardw.content}</div>
       
       
       
       
      	 <div class="reply-box">
      		 <c:forEach var="item" items="${list}">
      		 
      		 
	      	 	<div class="reply-box2">
	      	 		<div><b>${item.userID}</b></div>
	      	 		<div>${item.replyContent}</div>
	      	 		<div>${item.createDate}</div>
	      	 	</div>
	      	 	
      	 	</c:forEach>
      	 </div>
        
  		<div>
	       <textarea name="replyContent" id="replyContent" style="width:100%" class="reply" placeholder="댓글쓰기.." autocomplete="off" autocorrect="off" ></textarea>
	       <input type="hidden" style="width:100%; name="boardNum" value="${board.num}" />
	       <input style="width:100%; height:100%;" onclick="boardReplyWrite()"  class="btn btn-dark" type="button" value="댓글작성" />
	    </div>  
 		</form>
 	</div>
 	
 	
 	
 		<script>
	 		let replyContent;
	 		let userID;
	 		let createDate;
	 			function boardReplyWrite(){
	 				//댓글
	 				let reply_el = document.querySelector("#replyContent")
	 				replyContent = reply_el.value;
	 				//아이디
	 				userID = "${sessionScope.userID}";
	 				
	 				//
	 				//let createDate = new Date().toJSON();
	 				createDate = getTimeStamp();
	 			
	 				
	 				let boardNum = "${boardw.num}";
	 				
	 				let replyDto = {
	 					'replyContent' : replyContent,
	 					'userID' : userID,
	 					'boardNum' : boardNum
	 				};
	 				fetch("http://localhost:8000/kiblog2/reply",{
	 					method : 'POST',
	 					headers :{
	 						'Accept' : 'text/plain',
	 						'Content-Type' : 'application/json'
	 					},
	 					body : JSON.stringify(replyDto)
	 				}).then(function(res){
	 					return res.text();
	 				}).then(function(result){
	 					if(result == 'ok'){
	 				            addDiv();
	 					}else{
	 						alert('errordayo');
	 					}
	 				})
	 			};
	 			function addDiv(){
	 				let newDiv = document.createElement("div");
	 		        let items = document.querySelector('.reply-box');
	 		        let content = document.querySelector('#replyContent');
	 		      	newDiv.innerHTML = '<div class="reply-box2"><div><b>'+userID+'</b></div><div>'+replyContent+'</div><div>'+createDate+'</div></div>';
	 		        items.prepend(newDiv);
	 		      	content.value="";
	 			    }
	 			function getTimeStamp() {
	 				  var d = new Date();
	 				  var s =
	 				    leadingZeros(d.getFullYear(), 4) + '-' +
	 				    leadingZeros(d.getMonth() + 1, 2) + '-' +
	 				    leadingZeros(d.getDate(), 2) 
	 				 /*   + ' ' +

	 				    leadingZeros(d.getHours(), 2) + ':' +
	 				    leadingZeros(d.getMinutes(), 2) + ':' +
	 				    leadingZeros(d.getSeconds(), 2) */
	 				    ;

	 				  return s;
	 				}

	 				function leadingZeros(n, digits) {
	 				  var zero = '';
	 				  n = n.toString();

	 				  if (n.length < digits) {
	 				    for (i = 0; i < digits - n.length; i++)
	 				      zero += '0';
	 				  }
	 				  return zero + n;
	 				}
 
	 			
 		</script>
			
	
		
	<c:import url="/base/footer.jsp"></c:import>
</body>
</html>