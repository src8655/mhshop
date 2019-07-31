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
    <li><a href="#" style="color:#adc12c;">나의 쇼핑정보 <img src="${pageContext.servletContext.contextPath}/assets/images/myinfo.jpg" alt="나의 쇼핑정보" /></a></li>
    <li><a href="#">장바구니</a></li>
    <c:if test="${authUser eq null}">
	    <li><a href="member/join">회원가입</a></li>
	    <li><a href="member/login" style="border:0px;">로그인</a></li>
    </c:if>
    <c:if test="${authUser ne null}">
	    <li><a href="member/logout">로그아웃</a></li>
	    <li><a><span style="font-weight:bold;">${authUser.name}</span> 님</a></li>
    </c:if>
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
	
<div id="contents">
  <div id="main">
  	<div class="main_menu_bg">
    <ul class="main_menu">
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_01.jpg" alt="쌀/잡곡" />쌀/잡곡</a>
      </li>
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_02.jpg" alt="과일/채소" />과일/채소</a>
      </li>
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_03.jpg" alt="축산물" />축산물</a>
      </li>
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_04.jpg" alt="건강식품" />건강식품</a>
      </li>
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_05.jpg" alt="가공식품" />가공식품</a>
      </li>
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_06.jpg" alt="수산물" />수산물</a>
      </li>
      <li>
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_07.jpg" alt="김치/장류" />김치/장류</a>
      </li>
      <li style="border:0px;">
        <img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_dot.jpg" alt="점" class="main_menu_dot" />
        <a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/left_menu_08.jpg" alt="공예/공산품" />공예/공산품</a>
      </li>
    </ul>
    </div>
    
	<div class="main_best">
		<div class="arrow_l"><a href="#100" id="leftbtn"><div></div></a></div>
		<div class="arrow_r"><a href="#100" id="rightbtn"><div></div></a></div>
		<h1>베스트셀러</h1>
		<div class="main_best_scroll">
		<div class="main_best_scroll_in" id="main_scroll">
		<ul>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
			<li>
			  <a href="#">
			    <img src="${pageContext.servletContext.contextPath}/assets/images/test_product.jpg" alt="우렁이쌀(4kg)" width="100%" /><br />
				우렁이쌀(4kg)<br />
			    <span style="font-weight:bold;">15,000원</span>
				</a>
			</li>
		</ul>
		</div>
		</div>
	</div>
	
	
      
  </div>

  <div class="main_list">
    <h1><a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_01.jpg" alt="쌀/잡곡" />쌀/잡곡</a></h1>
    <div class="main_list_ul_bg">
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
    </ul>
    </div>
  </div>
  
  

  <div class="main_list">
    <h1><a href="#"><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_02.jpg" alt="과일/채소" />과일/채소</a></h1>
    <div class="main_list_ul_bg">
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" alt="우렁이쌀(4kg)" class="main_list_img" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_01.jpg" alt="star" width="15px" height="15" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/star_02.jpg" alt="star" width="15px" height="15" />
        </a>
      </li>
    </ul>
    </div>
  </div>
  
<!-- 
  <div class="main_list">
    <h1><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_02.jpg" alt="과일/채소" /></h1>
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li style="border:0px;">
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
  </div>
  
  
  
  
  
  <div class="main_list">
    <h1><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_03.jpg" alt="축산물" /></h1>
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li style="border:0px;">
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v2.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
  </div>
  
  
  
  
  <div class="main_list">
    <h1><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_04.jpg" alt="축산물" /></h1>
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li style="border:0px;">
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
  </div>
  
  
  <div class="main_list">
    <h1><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_05.jpg" alt="축산물" /></h1>
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li style="border:0px;">
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
  </div>
  
  
  
  <div class="main_list">
    <h1><img src="${pageContext.servletContext.contextPath}/assets/images/center_list_06.jpg" alt="축산물" /></h1>
    <ul>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li>
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
      <li style="border:0px;">
        <a href="#">
          <img src="${pageContext.servletContext.contextPath}/assets/images/test_product_v1.jpg" width="120px" height="120px" alt="우렁이쌀(4kg)" /><br />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_best.jpg" alt="best" />
          <img src="${pageContext.servletContext.contextPath}/assets/images/m_l_plus.jpg" alt="plus" /><br />
          8우렁이쌀(4kg)<br />
          <span style="font-weight:bold;">15,000원</span>
        </a>
      </li>
  </div>
  
  
  
</div>
<div id="copy_bg">
  <div id="copy">
    <h1><img src="${pageContext.servletContext.contextPath}/assets/images/logo.jpg" alt="logo" /></h1>
    <div id="copy_c">
      <ul>
        <li><a href="#">이용약관</a></li>
        <li><a href="#" style="color:#e78f04;">개인정보처리방침</a></li>
        <li><a href="#">입점신청</a></li>
        <li><a href="#" style="border:0px;">사이트맵</a></li>
      </ul>
      <p>
        대표자 : 홍길동 주소 : 나는 서울사람이다47 대표전화 : 02-123-4567 
        팩스 : 02-123-4567 이메일 : sarasa5621@naver.com
        사업자등록번호 : 111-156-7489
        copyright(c) 2012 sir all rights reserved.
      </p>
    </div>
    <div id="copy_r"></div>
  </div>
</div>
 -->
</body>
</html>