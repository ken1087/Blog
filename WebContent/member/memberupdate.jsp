<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
</head>
<body>
<c:import url="/base/header.jsp"></c:import>
<style>
		body{
			text-align: center;
		}
		.contanier{
			display:inline-block;
			width:50%;
			text-align: left;
		}
		input[type=text]{
            width: 100%;
        }
        input[type=password]{
            width: 100%;
        }
        input[type=date]{
            width: 100%;
        }
</style>
<form action="member?cmd=memberviewudapteproc" name="form" id="form" method="POST" onsubmit="return validateJoin()">
<div class ="contanier">
<table class="table" border="1" style="width:100%">
	<tr style="background: #343A40">
		<td><input name="num" type="text" value="${member.num}" width="100%" readOnly/></td>
	</tr>
	<tr style="background: #343A40">
		<td><input name="userID" type="text" value="${member.userID}" readOnly/></td>
	</tr>
	<tr style="background: #343A40">
		<td><input id="pw1" type="password" name="userPassword" placeholder="비밀번호" required/></td>
	</tr>
	<tr style="background: #343A40">
		<td><input id="pw2" type="password" placeholder="비밀번호 확인" required/></td>
	</tr>
	<tr style="background: #343A40">
		<td><input name="userEmail" type="text" value="${member.userEmail}" /></td>
	</tr>
	<tr style="background: #343A40">
		<td>
			<input type="button" onClick="goPopup();" class="btn btn-dark" value="주소검색"/>
			
			<input type="text"  style="width:100%;" id="roadFullAddr"  placeholder="주소"  name="userAddress" value="${member.userAddress}" readOnly/>
			</td>
	</tr>
	<tr style="background: #343A40">
		<td><input name="userPhone" type="text" value="${member.userPhone}" /></td>
	</tr>
	<tr style="background: #343A40">
		<td><input name="userGender" type="text" value="${member.userGender}" readOnly/></td>
	</tr>
</table>
	<input type="submit" class="btn btn-dark" value="변경"/>
	</div>
</form>

<script>
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
<c:import url="/base/footer.jsp"></c:import>
</body>
</html>