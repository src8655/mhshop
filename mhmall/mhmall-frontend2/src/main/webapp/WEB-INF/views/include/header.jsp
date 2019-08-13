<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <script src="${pageContext.servletContext.contextPath}/assets/vendor/jquery/jquery.tmpl.min.js"></script>

<script>
$(function(){

	// 검색버튼
	$(".search_btn").click(function(){
		var action = $(this).parent().parent().attr("data-action");
		var kwd = $(this).parent().prev().children().val();
		location.href=action + kwd;
	});
	
	// 검색어 입력 후 엔터
	$(".search_inp").keydown(function (key) {
		if(key.keyCode == 13) {
			$(".search_btn").trigger("click");
			return;
		}
	});

});
</script>
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
			<c:if test="${authUser eq null}">
		        <li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/member/login">로그인</a></li>
		        <li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/member/join">회원가입</a></li>
			</c:if>
			<c:if test="${authUser ne null}">
				<li class="nav-item active">
					<a class="nav-link">
						<span style="font-weight:bold;">
							${authUser.name}
						</span>
						님
					</a>
				</li>
				<c:if test="${authUser.role eq 'ROLE_ADMIN'}">
					<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/item" style="font-weight:bold;">관리페이지</a></li>
				</c:if>
				<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/member/logout">로그아웃</a><li>
			
			</c:if>
			<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/item/basket">장바구니</a></li>
			<c:if test="${authUser eq null}">
				<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/orders/guest/view">주문정보</a></li>
			</c:if>
			<c:if test="${authUser ne null}">
				<li class="nav-item active"><a class="nav-link" href="${pageContext.servletContext.contextPath}/orders/member/list">주문정보</a></li>
			</c:if>
        </ul>
        
        <div
        <c:if test="${categoryNoPath eq null}">
	       data-action="${pageContext.servletContext.contextPath}/item/list/-1/1/"
        </c:if>
        <c:if test="${categoryNoPath ne null}">
	       data-action="${pageContext.servletContext.contextPath}/item/list/${categoryNoPath}/1/"
        </c:if>
        class="col-lg-4 row form-inline">
	        <div class="col-md-8 col-sm-9 col-8 pr-0">
		        <input class="form-control mr-1 search_inp" type="text" placeholder="Search" style="width:100%;" aria-label="Search" value="${kwdPath}" />
		      </div>
	        <div class="col-md-4 col-sm-3 col-4 pl-1 pr-0">
		      <button class="btn btn-outline-white pl-0 pr-0 search_btn" style="width:100%;border:1px solid #ffffff;color:#ffffff;" type="button">Search</button>
		      </div>
		  </div>
		  
      </div>
      
      
    </div>
  </nav>

  