<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-collapse collapse navbar-responsive-collapse">
				<font color="lightblue">${loginUser}님 환영합니다~!</font>
				<ul class="nav navbar-nav navbar-right">
			
                <li class="active"><a href="#">커뮤니티 홈</a></li>
                <li><a href="#">인기 커뮤니티</a></li>
                <li><a href="#">랭킹</a></li>
                <li><a href="#">공지사항</a></li>
            
				
					<li><a href="logout.do">로그아웃</a></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"  data-toggle="dropdown">설정 <b class="caret"></b></a>
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
				<div class="jumbotron">
					<h1>나무 커뮤니티</h1>
					<br> <a href="cmcreate.xhtml" class="btn btn-warning btn-lg">커뮤니티
						생성</a>

				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-lg-12">

				<div id="communityList" class="tab-content">
					<ul class="nav nav-tabs" style="margin-bottom: 15px;">
						<li class="active">
						<a href="#joined" data-toggle="tab">가입 커뮤니티</a></li>
						<li class="">
						<a href="#unjoined" data-toggle="tab">미가입 커뮤니티</a></li>
					</ul>



					<div class="tab-pane fade active in" id="joined">
						<div class="page-header">
							<h2 id="container">가입 커뮤니티</h2>
						</div>
						<ul class="list-group">
							<c:forEach var="community" items="${managedCommunities}" varStatus="list">
								<li class="list-group-item"><span class="badge">
								 <fmt:formatDate	value="${community.openDate}" pattern="yyyy-MM-dd" /></span> 
											<span class="label label-info">관리</span>
									<h4><a href = "clList.xhtml?cmId=${community.id}">${community.name} (클럽 수 :${community.clubs.size()}개 , 회원 수 : ${community.members.size()})</a></h4> <span class="badge"><a	href='cmRemove.xhtml?cmId=${community.id}'>
									<font	color="black">삭제</font></a></span>
									<p>${community.description }</p>
								</li>
							</c:forEach>

							<c:forEach var="community" items="${belongCommunities}" 	varStatus="list">

								<li class="list-group-item"><span class="badge">
								<fmt:formatDate value="${community.openDate}" pattern="yyyy-MM-dd" /></span>
									<h4><a href = "clList.xhtml?cmId=${community.id}">${community.name} (클럽 수 :${community.clubs.size()}개 , 회원 수 : ${community.members.size()})</a> </h4>
									<p>${community.description }</p>
									<button type="button" class="btn btn-default btn-sm"
										onclick="location.href='withdraw.xhtml?cmId=${community.id}'">멤버탈퇴</button>
								</li>
							</c:forEach>
							
						</ul>
					</div>

					<div class="tab-pane fade" id="unjoined">

						<div class="page-header">
							<h2 id="container">미가입 커뮤니티</h2>
						</div>

						<ul class="list-group">
							<c:forEach var="community" items="${communities}"
								varStatus="list">
								<li class="list-group-item"><span class="badge"><fmt:formatDate
											value="${community.openDate}" pattern="yyyy-MM-dd" /></span>
									<h4>${community.name} (클럽 수 :${community.clubs.size()}개 , 회원 : ${community.members.size()}명)</h4>
									<p>${community.description }</p>
									<button type="button" class="btn btn-default btn-sm"
										onclick="location.href = 'cmjoin.xhtml?cmId=${community.id}'">커뮤니티
										가입</button></li>
							</c:forEach>
				
						</ul>
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
					<li><a href="cmList.xhtml">커뮤니티 홈</a></li>
					<li><a href="user/withdraw.xhtml">회원탈퇴</a></li>
				</ul>
				<p>© NamooSori 2014.</p>
			</div>
		</div>
		</footer>

	</div>



</body>
</html>