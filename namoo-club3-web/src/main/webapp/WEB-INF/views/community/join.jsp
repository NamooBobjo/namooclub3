<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file = "/WEB-INF/views/common/common.jsp"  %>
<title>Insert title here</title>
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
                <h2 id="container">${cmName} 커뮤니티 멤버 가입하기</h2>
            </div>

            <div class="well">
                <p>아래 질문 내용들을 정성껏 작성해 주세요.</p>
                <form class="form-horizontal" action="cmjoin.do?cmId=${cmId}&clId=${clId}" method = "post">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">커뮤니티 가입 목적은 무엇입니까?</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" placeholder="커뮤니티명">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">과거에 유사 커뮤니티에 가입하신 적이 있으시면 과거 활동을 적어 주세요.</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" placeholder="커뮤니티명">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="textArea" class="col-lg-2 control-label">커뮤니티 운영자에게 하고 싶은 말씀은 무엇인가요?</label>

                            <div class="col-lg-10">
                                <textarea class="form-control" rows="3" id="textArea"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="submit" class="btn btn-primary">확인</button>
                                <button class="btn btn-default" onclick = "cancelPage(); return false;">취소</button>
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