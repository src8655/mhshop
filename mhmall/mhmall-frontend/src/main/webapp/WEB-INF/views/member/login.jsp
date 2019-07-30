<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>쇼핑몰</title>
<link href='${pageContext.servletContext.contextPath}/assets/css/main.css' rel='stylesheet' type="text/css" />
<link href='${pageContext.servletContext.contextPath}/assets/css/pc.css' rel='stylesheet' type="text/css" />
<link href='${pageContext.servletContext.contextPath}/assets/css/tablet.css' rel='stylesheet' type="text/css" />
<link href='${pageContext.servletContext.contextPath}/assets/css/mobile.css' rel='stylesheet' type="text/css" />
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery-1.4.2.min.js" type="text/javascript"></script>


<script type="text/javascript">
$(function(){
	$("#leftbtn").click(function(){
		$("#main_scroll").stop().animate({'scrollLeft':0},500);
	});
	$("#rightbtn").click(function(){
		$("#main_scroll").stop().animate({'scrollLeft':715},500);
	});
	$("#header_searchbt").click(function(){
		$("#mheader_c").slideToggle(200);
	});
});
</script>
</head>
<body>
<div id="top_bg">
  <ul>
    <li><a href="#">고객센터</a></li>
    <li><a href="#" style="color:#adc12c;">나의 쇼핑정보 <img src="${pageContext.servletContext.contextPath}/assets/images/myinfo.jpg" alt="나의 쇼핑정보" /></a></li>
    <li><a href="#">장바구니</a></li>
    <li><a href="#">회원가입</a></li>
    <li><a href="#" style="border:0px;">로그인</a></li>
    <li></li>
  </ul>
</div>
<div id="header_bg">
<div id="header">
  <div id="header_searchbt"><a href="#100"><div></div></a></div>
  <h1><a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/logo.jpg" alt="logo" /></a></h1>
  <div id="header_c">
    <ul id="header_c_t">
      <li><a href="#">산삼/장뇌삼</a></li>
      <li><a href="#">약용버섯</a></li>
      <li><a href="#">식물</a></li>
      <li><a href="#">진액</a></li>
      <li><a href="#">액기스</a></li>
      <li><a href="#" style="border:0px;">홍삼가공</a></li>
    </ul>
    <div id="header_c_c">
    <form>
      <div id="header_c_c_l">
        <input type="text" name="search" id="header_c_c_l_text" />
      </div>
      <div id="header_c_c_r">
        <input type="submit" value="" id="header_c_c_l_button" />
      </div>
    </form>
    </div>
    <ul id="header_c_b" style="display:none;">
      <li><a href="#">베스트셀러</a></li>
      <li><a href="#" style="border:0px;">추천상품</a></li>
    </ul>
  </div>
  <div id="header_r">
    <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/top_right_menu.jpg" alt="박광희김치" /></a>
  </div>
  <div id="header_my">
  	<a href="#100">
  		<div></div>
  		<div></div>
  		<div></div>
  	</a>
  </div>
</div>
</div>

	<div id="mheader_c">
		<div id="mheader_c_c">
			<form>
				<div id="mheader_c_c_l">
					<input type="text" name="search" id="mheader_c_c_l_text" />
				</div>
				<div id="mheader_c_c_r">
					<button type="submit" value="" id="mheader_c_c_l_button"><div></div></button>
				</div>
			</form>
		</div>
	</div>
	
	
	
	
	
	
	
	
<form action="login" method="post" name="login">
<div class="login_bg">
	<h1>12번가<span style="color:#333333;">로그인</span></h1>
	<div class="login">
		<div class="login_box">
			<div class="login_box_t">
				<div class="login_box_t_l">
					<input type="text" name="id" value="${user_id}" placeholder="12번가 아이디 입력" />
					<input type="password" name="password" style="margin-top:3px;" placeholder="비밀번호 입력" />
				</div>
				<a href="#10" onclick="login.submit();">로그인</a>
			</div>
			<div class="login_box_c">
				<input type="checkbox" name="auto_ids" value="1" id="auto_id" <c:if test="${user_id ne ''}">checked</c:if> /><label for="auto_id">아이디 저장</label>
			</div>
			<ul>
				<li><a href="join.o" style="color:#6283e3;border:1px solid #6283e3;">회원가입</a></li>
				<li><a href="login_find_id.o">아이디 찾기</a></li>
				<li><a href="login_find_pw.o">비밀번호 찾기</a></li>
			</ul>
		</div>
	</div>
</div>
<input type="submit" value="로그인" style="display:none;" />
</form>
id : ${authUser.id}<br />
name : ${authUser.name}<br />
message : ${message}<br />




</body>
</html>