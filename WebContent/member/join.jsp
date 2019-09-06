<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		alert(roadFullAddr);
		document.form.roadFullAddr.value = roadFullAddr;
}

</script>
<title>kang Blog</title>
<style>

.form-control.form-control-lg {
	margin-top: 2%;
	width: 350px;
	display: inline-block;
}
.contanier{
	margin-top:100px;
	display:inline-block;
	text-align: left;
}

</style>
</head>
<body>
<%@ include file="/base/header.jsp"%>
<style>
	body{
	text-align: center;
	}	
</style>
<div class="contanier">
	<form action="member?cmd=memberJoinProc" name="form" id="form" method="POST" onsubmit="return validateJoin()">
	
		<input id="userID" type="text" name="userID" placeholder="아이디" class="form-control form-control-lg" required /> <input type="button" value="중복확인" onclick="validateDuplicateID()" class="bb btn btn-dark"/><br />
		<input id="pw1" type="password" name="userPassword" placeholder="비밀번호" class="form-control form-control-lg" required/><br />
		<input id="pw2" type="password" placeholder="비밀번호 확인" class="form-control form-control-lg" required/><br />
		<input type="button" onClick="goPopup();" class="bb btn btn-dark" value="주소검색" style="margin: 10px 0px"/>
		<div id="list"></div>
		<div id="callBackDiv">
		<td><input type="text"   id="roadFullAddr"  placeholder="주소"  name="userAddress" class="form-control form-control-lg" readOnly/></td></tr>
		</div>
		<input type="email" name="userEmail" placeholder="이메일" class="form-control form-control-lg" required/><br />
		<input type="text" name="userPhone" placeholder="전화번호" class="form-control form-control-lg" required/><br />
		남 : <input type="radio" name="userGender" value="남" checked/>
		여 : <input type="radio" name="userGender" value="여"/><br />
		
		<input type="submit" value="회원가입" class="btn btn-dark" />

	</form>
</div>
<c:import url="/base/footer.jsp"></c:import>
<script>
	let check = 1;
	function loadAjax(userID) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				alert(this.responseText);
				if(this.responseText == "ok"){
					alert("중복된 아이디가 없습니다.");
					check = 0;
				}else{
					
					alert("중복된 아이디가	있습니다.");
				}
			}
		};
		xhttp.open("GET", "rest?userID="+userID, true);
		xhttp.send();
	}//회원가입 중복확인
	
	
					
	function validateDuplicateID() {
		var userID_element = document.querySelector("#userID");
		var userID = userID_element.value;
		loadAjax(userID);
		
	}//중복확인

		
	function validateJoin() {
		
		if(check == 1){
			return false;
			}
	
		var pw1 = document.querySelector("#pw1");
		var pw2 = document.querySelector("#pw2");
		console.log(pw1);
		console.log(pw1.value);
		console.log(pw2.value);

		if (pw1.value === pw2.value) {
			return true;
		} else {
			pw1.value = "";
			pw2.value = "";
			pw1.focus();
			alert("비밀번호가 일치하지 않습니다.")
			return false;
		}
	}
</script>
	
</body>
</html>