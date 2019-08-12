<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
$(function(){

	$("#orders_btn").click(function(){
		
		var forms = $("#guest_orders_form");
		var toName = forms.find('input[name="toName"]').val();
		var toPhone = forms.find('input[name="toPhone1"]').val()
					+ forms.find('input[name="toPhone2"]').val()
					+ forms.find('input[name="toPhone3"]').val();
		var toZipcode = forms.find('input[name="toZipcode"]').val();
		var toAddr = forms.find('input[name="toAddr"]').val();
		
		
		if(!(toName.length >= 2 && toName.length <=5)) {
			alert("이름 형식이 올바르지 않습니다.");
			return false;
		}
		exptext = new RegExp("^\\d{2,3}\\d{3,4}\\d{4}$");
		if(exptext.test(toPhone) == false) { 
			alert("연락처 형식이 올바르지 않습니다.");
			return false;
		}
		if(toZipcode == '') { 
			alert("우편번호 형식이 올바르지 않습니다.");
			return false;
		}
		if(toAddr == '') { 
			alert("주소 형식이 올바르지 않습니다.");
			return false;
		}

		forms.submit();
	});
	
	
	
	// 다음우편번호
	$("#zipcode_btn").click(function(){
		new daum.Postcode({
		      oncomplete: function(data) {
		    	  $("#zipcode").val(data.postcode);
		    	  $("#addr").val(data.address+" "+data.buildingName);
		      }
		  }).open();
	});
	
	
});
</script>

<div class="container mt-5 mb-5">


	<div class="row">




		<div class="col-lg-2 col-md-2 col-sm-1"></div>
		<div class="col-lg-8 col-md-8 col-sm-10">
			<div class="text-muted mt-4 mb-4"
				style="border: 1px solid #e6e6e6; border-radius: 5px; overflow: hidden;">
				<div
					class=" d-xl-block d-lg-block d-md-block d-md-none d-sm-none d-none">
					<div class="row p-2 pt-3" style="border-bottom: 2px solid #e6e6e6;">
						<h6 class="col-md-6" style="font-weight: bold;">상품</h6>
						<h6 class="col-md-2 text-center" style="font-weight: bold;">수량</h6>
						<h6 class="col-md-2 text-center" style="font-weight: bold;">가격</h6>
					</div>
				</div>

				<c:forEach items="${ordersItemList}" var="oidata">
					<div class="row p-2" style="border-bottom: 1px solid #e6e6e6;">
						<div class="col-lg-6">
							<figure class="media">
								<div class="img-wrap mr-2"
									style="width: 80px; overflow: hidden;">
									<a>
										<img
										src="${pageContext.servletContext.contextPath}${oidata.itemThumbnail}"
										style="width: 100%;" class="img-thumbnail img-sm">
									</a>
								</div>
								<figcaption class="media-body">
									<h6 class="title text-truncate pr-2"
										style="width: 230px; height: 20px; line-height: 20px; oveflow: hidden;">
										<a
											style="color: #676767;">${oidata.itemName}</a>
									</h6>
									<dl class="dlist-inline small">
										<dd>${oidata.itemOptionDetail1} ${oidata.itemOptionDetail2}</dd>
									</dl>
								</figcaption>
							</figure>

						</div>
						<div class="col-lg-2 col-4 text-center">${oidata.cnt}</div>
						<div class="col-lg-2 col-4 text-center">
							<div class="price-wrap">
								<var class="price">
									<fmt:formatNumber value="${oidata.money}" pattern="#,###" />
									원
								</var>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-1"></div>




	</div>







	<div class="row">


		<div class="col-lg-2 col-md-2 col-sm-1"></div>
		<div class="col-lg-8 col-md-8 col-sm-10">
			<div class="card" style="margin-top: 20px;">
				<article class="card-body">
					<h4 class="card-title mb-4 mt-1">주문서 작성</h4>
					<form action="${pageContext.servletContext.contextPath}/orders/guest/update" method="post" id="guest_orders_form">
					<input name="ordersNo" type="hidden" value="${ordersNo}" />
					<input name="guestPassword" type="hidden" value="${guestPassword}" />
						<div class="form-group">
							<input name="toName" class="form-control" placeholder="받는사람 이름" type="text" />
						</div>
						<div class="form-group">
							<input name="toPhone1" class="form-control float-left mb-3" style="width: 30%;" placeholder="010" type="text" />
							<input name="toPhone2" class="form-control float-left mb-3 ml-1" style="width: 30%;" placeholder="0000" type="text" />
							<input name="toPhone3" class="form-control float-left mb-3 ml-1" style="width: 30%;" placeholder="0000" type="text" />
						</div>
						<div class="form-group">
							<input name="toZipcode" class="form-control float-left mb-3" style="width: 45%;" placeholder="우편번호" type="text" id="zipcode" />
							<a href="#10" class="float-right btn btn-outline-primary" style="width: 50%;" id="zipcode_btn">우편번호찾기</a>
						</div>
						<div class="form-group">
							<input name="toAddr" class="form-control" placeholder="주소"
								type="text" id="addr" />
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="button" id="orders_btn" class="btn btn-primary btn-block">주문완료</button>
						</div>
						<!-- form-group// -->
					</form>
				</article>
			</div>
			<!-- card.// -->
		</div>
		<div class="col-lg-2 col-md-2 col-sm-1"></div>


	</div>
</div>






<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>