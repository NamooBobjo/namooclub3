<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file = "/WEB-INF/views/common/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>나무 커뮤니티</title>

<style type="text/css">
body{
	padding-top: 100px;
	padding-bottom: 40px;
	background-color: #ecf0f1;
}

.login-header {
	max-width: 400px;
	padding: 15px 29px 25px;
	margin: 0 auto;
	background-color: #2c3e50;
	color: white;
	text-align: center;
	-webkit-border-radius: 15px 15px 0px 0px;
	-moz-border-radius: 15px 15px 0px 0px;
	border-radius: 15px 15px 0px 0px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.login-footer {
	max-width: 400px;
	margin: 0 auto 20px;
	padding-left: 10px;
}

.form-signin {
	max-width: 400px;
	padding: 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	-webkit-border-radius: 0px 0px 15px 15px;
	-moz-border-radius: 0px 0px 15px 15px;
	border-radius: 0px 0px 15px 15px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,
        .form-signin .checkbox  {
	margin-bottom: 15px;
}

.form-signin input[type="text"], .form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.form-btn {
	text-align: center;
	padding-top: 20px;
}
</style>
</head>
<body onload = "callCookie();">
	<div class="container">

		<!-- header -->
		<div class="login-header">
			<h2 class="form-signin-heading">나무 커뮤니티</h2>
		</div>

		<!-- form -->
		
		<form class = "form-signin" action="login.do" method = "post" name = "myform" onSubmit = "return sendit();">
			<input type="text" class="form-control" name = "userID" id="inputEmail" placeholder="아이디" required> 
			<input type="password"class="form-control" name = "userPS" id="inputPassword" placeholder="비밀번호" required>
			<label class="checkbox">
			 <input type="checkbox" name = "idsave" onclick = "saveid(this.form)" value="remember-me"> 아이디 기억하기
			</label>
	
		
			<div class="row form-btn">
				<button class="btn btn-large btn-warning" type="submit">로그인</button>				
				<button class="btn btn-large btn-default" onclick = "joinPage(); return false; " >회원가입</button>
			</div>
			</form>
		
	

		<!-- footer -->
		<div class="login-footer">
			<p>© NamooSori 2014.</p>
		</div>
	</div>
	<script>
	function joinPage() { 
	    location.href = "view/user/join.xhtml";
	    return false;
	}
	
	function setCookie(name,value,expiredays){
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate()+expiredays);
		document.cookie = name + "="+escape(value) + ";path =/; expires=" +todayDate.toGMTString()+";";
	
	}

	function getCookie(Name){
		var search = Name +"=";
		if(document.cookie.length>0){
			offset = document.cookie.indexOf(search);
			if(offset!=-1){
				offset+= search.length;
				end = document.cookie.indexOf(";", offset);
				if(end==-1){
					end = document.cookie.length;
				}
				return unescape(document.cookie.substring(offset,end));
			}
		}
	}
	
	function sendit(){
		if(document.myform.idsave.checked == true){
			setCookie("userID",document.myform.userID.value,7);
		}
		else{
			setCookie("userID",document.myform.userID.value,0);
		}
	}
	
	function callCookie(){
		document.myform.userID.focus();
		if(getCookie("userID")){
			document.myform.userID.value = getCookie("userID");
			document.myform.idsave.checked = true;
		}
	}
	</script>

</body>
</html>