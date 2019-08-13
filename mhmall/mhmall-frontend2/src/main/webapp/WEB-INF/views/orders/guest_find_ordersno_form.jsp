<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

<script>
$(function(){

	
	
	
});
</script>



	<div class="container mt-5 mb-5">
		<div class="row">


			<div class="col-lg-4 col-md-3 col-sm-1"></div>
			<div class="col-lg-4 col-md-6 col-sm-10">
				<div class="card" style="margin-top:20px;">
					<article class="card-body">
					<h4 class="card-title mb-4 mt-1">비회원 주문번호 찾기</h4>
					<form action="${pageContext.servletContext.contextPath}/orders/guest/find/ordersno" method="post" id="guest_orders_info_form">
						<div class="form-group">
							<input name="guestName" class="form-control" placeholder="이름" type="text" value="" />
						</div>
						<div class="form-group">
							<input name="guestPhone1" class="form-control float-left mb-3"style="width:30%;" placeholder="010" type="text" />
							<input name="guestPhone2" class="form-control float-left mb-3 ml-1"style="width:30%;" placeholder="0000" type="text" />
							<input name="guestPhone3" class="form-control float-left mb-3 ml-1"style="width:30%;" placeholder="0000" type="text" />
						</div>
						<div class="form-group">
							<input name="guestPassword" class="form-control" placeholder="비밀번호" type="password" value="" />
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block" id="orders_btn">주문번호 찾기</button>
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