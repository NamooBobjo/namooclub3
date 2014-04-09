<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file = "/WEB-INF/views/common/common.jsp"  %>
</head>
<body>

	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<font color="lightblue">${loginUser}님 환영합니다~!</font>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="../logout.do">로그아웃</a></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">설정 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">개인정보</a></li>
						</ul></li>

				</ul>
			</div>
		</div>
	</div>
	
	
	

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h2 id="container">커뮤니티 개설하기</h2>
				</div>
				<div class="well">
					<p>나와 같은 관심사를 가진 멤버를 모집하고 열심히 운영하여 커뮤니티를 성장시켜 보세요.</p>
					<form class="form-horizontal" action="cmcreate.do" method="post">
						<fieldset>
							<div class="form-group">
								<label class="col-lg-2 control-label">커뮤니티명</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" name="cmName">
								</div>
							</div>
						<div class="form-group">
                            <label class="col-lg-2 control-label">클럽 카테고리</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" placeholder="카테고리 1" name = "category" required>
                                <input type="text" class="form-control" placeholder="카테고리 2" name = "category">
                                <input type="text" class="form-control" placeholder="카테고리 3" name = "category">
                                <input type="text" class="form-control" placeholder="카테고리 4" name = "category">
                                <input type="text" class="form-control" placeholder="카테고리 5" name = "category">
                                <input type="text" class="form-control" placeholder="카테고리 6" name = "category">
                            </div>
                        </div>
						
							<div class="form-group">
								<label for="textArea" class="col-lg-2 control-label">커뮤니티 대표문구</label>
								<div class="col-lg-10">
									<textarea class="form-control" rows="3" name="description"></textarea>
									<br /> <span class="help-block">커뮤니티를 소개하는 대표문구를 입력해
										주세요. 커뮤니티 홈화면에 입력하신 문구가 출력됩니다.</span>
								<button type="submit" class="btn btn-primary">확인</button>
                                <button class="btn btn-default" onclick = "cancelPage();return false;">취소</button>
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