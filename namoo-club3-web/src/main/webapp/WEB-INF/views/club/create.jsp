<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	
<title>클럽개설</title>
<%@ include file = "/WEB-INF/views/common/common.jsp"  %>
	
</head>
<body>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
   
        <div class="navbar-collapse collapse navbar-responsive-collapse">
         <font color="lightblue">${loginUser}님 환영합니다~!</font>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="logout.do">로그아웃</a></li>
              
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <!-- ★★★ Contents -->
            <div class="page-header">
                <h2 id="container">클럽 개설하기</h2>
            </div>

            <div class="well">
                <p>나와 같은 관심사를 가진 멤버를 모집하고 열심히 운영하여 클럽을 성장시켜 보세요.</p>
                <form class="form-horizontal" action="clCreate.do?cmId=${cmId}" method = "post">
                    <fieldset>
                    
                       <div class="form-group">
                            <label class="col-lg-2 control-label">클럽 카테고리</label>

                            <div class="col-lg-10">
                                <select class="form-control" id="select" name = "category">
                                <c:forEach var="cate" items="${category}" varStatus="list">
                                    <option>${cate}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    
                      
                        <div class="form-group">
                            <label class="col-lg-2 control-label">클럽명</label>

                            <div class="col-lg-10">
                                <input type="text" class="form-control" placeholder="클럽명" name = "clName">
                            </div>
                        </div>
                      
                        <div class="form-group">
                            <label for="textArea" class="col-lg-2 control-label" >클럽 대표문구</label>

                            <div class="col-lg-10">
                                <textarea class="form-control" rows="3" id="textArea" name = "content"></textarea>
                                <span class="help-block">클럽을 소개하는 대표문구를 입력해 주세요. 클럽 홈화면에 입력하신 문구가 출력됩니다.</span>
                            </div>
                        </div>
                      
                      
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="submit" class="btn btn-primary">확인</button>
                                <button class="btn btn-default" onclick ="cancelPage(); return false;">취소</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
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
