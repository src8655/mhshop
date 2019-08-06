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
  <link href="${pageContext.servletContext.contextPath}/assets/css/heroic-features.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.servletContext.contextPath}/assets/css/simple-sidebar.css" rel="stylesheet">
  <script src="${pageContext.servletContext.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
  

</head>

<body>


  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">관리자페이지</div>
      <div class="list-group list-group-flush">
        <a href="${pageContext.servletContext.contextPath}/admin/category" class="list-group-item list-group-item-action bg-light">카테고리 관리</a>
        <a href="${pageContext.servletContext.contextPath}/admin/item" class="list-group-item list-group-item-action bg-light">상품관리</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">주문관리</a>
        <a href="${pageContext.servletContext.contextPath}/admin/member" class="list-group-item list-group-item-action bg-light">회원관리</a>
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">






      <!-- 메뉴버튼 -->
      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Toggle Menu</button>
        
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="#">상품추가</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">상품추가</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">상품추가</a>
            </li>
          </ul>
        </div>
      </nav>