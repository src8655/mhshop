<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

<script>
$(function(){

	$("#orders_btn").click(function(){
		
		var forms = $("#guest_orders_info_form");
		var ordersNo = forms.find('input[name="ordersNo"]').val();
		var guestPassword = forms.find('input[name="guestPassword"]').val();
		
		
		var exptext = "";
		
		
		if(ordersNo == '') {
			alert("주문번호 형식이 올바르지 않습니다.");
			return false;
		}
		exptext = new RegExp("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
		if(exptext.test(guestPassword) == false) { 
			alert("비밀번호 형식이 올바르지 않습니다.");
			return false;
		}

		forms.submit();
	});
	
	// 입력 후 엔터
	$(".guest_view_input").keydown(function (key) {
		if(key.keyCode == 13) {
			$("#orders_btn").trigger("click");
			return;
		}
	});
	
	
	
});
</script>

<c:if test="${ordersNo ne null}">
<c:if test="${ordersNo ne ''}">
<script>
$(function(){

	
	$("#guest_orders_info_form").submit();
	
	
	
});
</script>
</c:if>
</c:if>

	<div class="container mt-5 mb-5">
		<div class="row">


			<div class="col-lg-4 col-md-3 col-sm-1"></div>
			<div class="col-lg-4 col-md-6 col-sm-10">
				<div class="card" style="margin-top:20px;">
					<article class="card-body"> <a href="${pageContext.servletContext.contextPath}/orders/guest/find/ordersno" class="float-right btn btn-outline-primary">주문번호찾기</a>
					<h4 class="card-title mb-4 mt-1">비회원<br />주문내역</h4>
					<form action="${pageContext.servletContext.contextPath}/orders/guest/view" method="post" id="guest_orders_info_form">
						<div class="form-group">
							<input name="ordersNo" class="form-control guest_view_input" placeholder="주문번호" type="text" value="${ordersNo}" />
						</div>
						<div class="form-group">
							<input name="guestPassword" class="form-control guest_view_input" placeholder="비밀번호" type="password" value="${guestPassword}" />
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="button" class="btn btn-primary btn-block" id="orders_btn">주문상세조회</button>
						</div>
						<!-- form-group// -->
					</form>
					</article>
				</div>
				<!-- card.// -->
			</div>
			<div class="col-lg-4 col-md-3 col-sm-1"></div>


		</div>
	</div>






<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>