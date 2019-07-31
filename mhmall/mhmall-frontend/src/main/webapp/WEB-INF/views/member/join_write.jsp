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
	
	
	
	
	
	
	
	
<div class="join_agree_bg">

<div class="join_agree">
	<div class="join_agree_header">
		<h1>
			개인 회원가입
		</h1>
		<ul>
			<li>3 가입완료</li>
			<li style="color:#ea0000;border-bottom:2px solid #ea0000;">2 정보입력</li>
			<li>1 약관동의</li>
		</ul>
	</div>
	<p class="join_agree_c">회원정보를 입력해주세요. 모두 입력해야 가입이 가능합니다.</p>
<form action="join_write_post.o" method="post" id="join">
	<ul class="joins">
		<li>
			<h1><span style="color:#e61337;">•</span> 이름</h1>
			<div><input type="text" name="name" class="join_input" placeholder="이름을 입력해 주세요" /></div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 아이디</h1>
			<div>
					<input type="text" name="id" id="user_ids"  class="join_input" placeholder="ID를 입력해 주세요" style="width:50%;" />
					<input type="button" value="중복확인" onclick="" class="join_id_button" />
					<p style="line-height:26px;margin:0 0 0 5px;"><span style="color:#3985ac;font-size:12px;" id="id_ch_value">아이디를 입력해 주세요</span></p>
				
			</div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 비밀번호</h1>
			<div><input type="password" name="password" class="join_input" placeholder="비밀번호를 입력해 주세요" /></div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 비밀번호 확인</h1>
			<div><input type="password" name="password2" class="join_input" placeholder="비밀번호를 다시 한번 입력해 주세요" /></div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 이메일</h1>
			<div><input type="text" name="email" class="join_input" placeholder="이메일을 입력해 주세요" /></div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 우편번호</h1>
			<div>
				<input type="text" name="zipcode" id="addr_code" style="width:30%;" class="join_input" />
				<input type="button" value="우편번호 찾기" onclick="btn_find_daum()" class="join_id_button" />
				
			</div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 주소</h1>
			<div><input type="text" name="addr" id="addr" class="join_input" /></div>
		</li>
		<li>
			<h1><span style="color:#e61337;">•</span> 휴대폰 <span style="font-weight:bold;color:red;font-size:11px;"></span></h1>
			<div><input type="text" name="phone1" style="width:20%;" class="join_input" /> - <input type="text" name="phone2" style="width:20%;" class="join_input" /> - <input type="text" name="phone3" style="width:20%;" class="join_input" /></div>
		</li>
	</ul>
	<div class="join_red_button">
		<a href="#10" onclick="join_submit();">회원가입</a>
	</div>
</form>
</div>

</div>










</body>
</html>