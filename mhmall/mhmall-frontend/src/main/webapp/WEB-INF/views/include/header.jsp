<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="kr">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>MHMALL</title>

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.servletContext.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.servletContext.contextPath}/assets/css/shop-homepage.css" rel="stylesheet">
  <script src="${pageContext.servletContext.contextPath}/assets/vendor/jquery/jquery.min.js"></script>

</head>

<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" style="background:#20a8d8;">
    <div class="container">
      <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">MH SHOP</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
      
      
        <ul class="navbar-nav mr-auto">
			<sec:authorize access="!isAuthenticated()">
		        <li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/member/login">로그인</a></li>
		        <li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/member/join">회원가입</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item active">
					<a class="nav-link">
						<span style="font-weight:bold;">
							<sec:authentication property="principal.name"/>
						</span>
						님
					</a>
				</li>
				<sec:authorize access="hasRole('ADMIN')">
					<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/item" style="font-weight:bold;">관리페이지</a></li>
				</sec:authorize>
				<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/member/logout">로그아웃</a><li>
			
			</sec:authorize>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/item/basket">장바구니</a></li>
			<li class="nav-item active"><a class="nav-link" href="">주문정보</a></li>
        </ul>
        
        
	      <form class="row form-inline my-2 my-lg-0 ml-auto mr-2">
	        <div class="col-md-8 col-sm-9 col-8">
		        <input class="form-control mr-1" type="search" placeholder="Search" style="width:100%;" aria-label="Search">
		      </div>
	        <div class="col-md-4 col-sm-3 col-4">
		      <button class="btn btn-outline-white" style="width:100%;border:1px solid #ffffff;color:#ffffff;" type="submit">Search</button>
		      </div>
		    </form>
      </div>
      
      
    </div>
  </nav>

  