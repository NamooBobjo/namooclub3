<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-collapse collapse navbar-responsive-collapse">
			<font color="lightblue">${userName}님 환영합니다~!</font>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="../logout.do">로그아웃</a></li>
					
					 <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">설정 <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">개인정보</a></li>
                      </ul>
                </li>
					
				</ul>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h2 id="container">회원 탈퇴하기</h2>
				</div>
				<div class="well">
					<p>정말 커뮤니티의 회원을 탈퇴하시겠습니까?</p>
					<form class="form-horizontal"
						action="withdraw.do" method="post">
						<fieldset>
							<div class="form-group">
								<div class="col-lg-10">
									이름 : <input type="text" class="form-control" name="userName"
										value="${userName}" disabled>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10">
									비밀번호 : <input type="text" class="form-control" name="password"  placeholder="비밀번호">
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="submit" class="btn btn-primary">확인</button>
									<button class="btn btn-default"
										onclick="cancelPage(); return false;">취소</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
		<script>
	function cancelPage() { 
	   window.history.back();
	    return false;
	}
	</script>
</body>
</html>