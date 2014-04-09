<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>클럽탈퇴</title>
</head>
<body>


	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<font color="lightblue">${loginUser}님 환영합니다~!</font>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="../logout.do">로그아웃</a></li>
				</ul>
			</div>
		</div>
	</div>
	
		<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h2 id="container">클럽 탈퇴하기</h2>
				</div>
				<div class="well">
 			<p>정말 클럽을 탈퇴하시겠습니까?</p>
            <div class="well">
                <form class="form-horizontal" action="clwithdraw.do?clubId=${clubId}&cmId=${cmId}" method = "post">
                    <fieldset>
                        <div class="form-group">
                           <div class="col-lg-10">
									Club Name : <input type="text" class="form-control"
										name="cmName" value="${clubName}" disabled>
								</div>
                        </div>
                       <div class="form-group">
								<div class="col-lg-10"></div>
							</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="submit" class="btn btn-primary">확인</button>
									<button class="btn btn-default" onclick="cancelPage(); return false;">취소</button>
								</div>
							</div>
						</fieldset>
   				  </form>
				</div>
			</div>
		</div>
	</div>
<!-- Footer ========================================================================================== -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <ul class="list-unstyled">
                    <li class="pull-right"><a href="#top">위로 이동</a></li>
                    <li><a href="#">커뮤니티 홈</a></li>
                    <li><a href="#">회원탈퇴</a></li>
                </ul>
                <p>© NamooSori 2014.</p>
            </div>
        </div>
    </footer>
</div>
	<script>
	function cancelPage() { 
	   window.history.back();
	    return false;
	}
	</script>
	
</body>
</html>