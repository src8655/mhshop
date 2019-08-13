<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

<script>
$(function(){

	$("#orders_btn").click(function(){
		
		var forms = $("#guest_orders_info_form");
		var guestName = forms.find('input[name="guestName"]').val();
		var phone = forms.find('input[name="phone1"]').val()
					+ forms.find('input[name="phone2"]').val()
					+ forms.find('input[name="phone3"]').val();
		var guestPassword = forms.find('input[name="guestPassword"]').val();
		
		
		var exptext = "";
		
		
		if(!(guestName.length >= 2 && guestName.length <=5)) {
			alert("이름 형식이 올바르지 않습니다.");
			return false;
		}
		exptext = new RegExp("^\\d{2,3}\\d{3,4}\\d{4}$");
		if(exptext.test(phone) == false) { 
			alert("연락처 형식이 올바르지 않습니다.");
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
	$(".guest_info_input").keydown(function (key) {
		if(key.keyCode == 13) {
			$("#orders_btn").trigger("click");
			return;
		}
	});
	
	
});
</script>

	<div class="container mt-5 mb-5">
		<div class="row">


			<div class="col-lg-4 col-md-3 col-sm-1"></div>
			<div class="col-lg-4 col-md-6 col-sm-10">
				<div class="card" style="margin-top:20px;">
					<article class="card-body">
					<h4 class="card-title mb-4 mt-1">비회원주문</h4>
					<form action="${pageContext.servletContext.contextPath}/orders/guest" method="post" id="guest_orders_info_form">
				  	  <c:forEach items="${optionNos}" var="optionNo">
				  	  	<input type="hidden" name="optionNos" value="${optionNo}" />
				  	  </c:forEach>
				  	  <c:forEach items="${optionCnts}" var="optionCnt">
				  	  	<input type="hidden" name="optionCnts" value="${optionCnt}" />
				  	  </c:forEach>
						<div class="form-group">
							<input name="guestName" class="guest_info_input form-control" placeholder="이름" type="text" />
						</div>
						<div class="form-group">
							<input name="phone1" class="guest_info_input form-control float-left mb-3"style="width:30%;" placeholder="010" type="text" />
							<input name="phone2" class="guest_info_input form-control float-left mb-3 ml-1"style="width:30%;" placeholder="0000" type="text" />
							<input name="phone3" class="guest_info_input form-control float-left mb-3 ml-1"style="width:30%;" placeholder="0000" type="text" />
						</div>
						<div class="form-group">
							<input name="guestPassword" class="guest_info_input form-control" placeholder="비밀번호" type="password" />
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="button" class="btn btn-primary btn-block" id="orders_btn">주문하기</button>
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