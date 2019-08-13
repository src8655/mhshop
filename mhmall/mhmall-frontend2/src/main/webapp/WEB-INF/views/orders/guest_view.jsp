<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
$(function(){


	
	
});
</script>

<div class="container mt-5 mb-5">

	<div class="row">
	
	
		<div class="col-lg-2 col-md-2 col-sm-1"></div>
		<div class="col-lg-8 col-md-8 col-sm-10">
			<table class="table table-hover member_table">
				<thead>
					<tr>
						<th colspan="2" class="text-center">비회원 주문상세</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th class="text-center">주문번호</td>
						<td>${ordersVo.ordersNo}</td>
					</tr>
					<tr>
						<th class="text-center">주문일</td>
						<td>${ordersVo.regDate}</td>
					</tr>
					<tr>
						<th class="text-center">상태</td>
						<td>
							${ordersVo.status}
							<c:if test="${ordersVo.status eq '입금대기'}">
							<form action="${pageContext.request.contextPath}/orders/guest/cancel" method="post">
								<input type="hidden" name="ordersNo" value="${ordersVo.ordersNo}" />
								<input type="hidden" name="guestPassword" value="${guestPassword}" />
								<button type="submit" class="btn btn-primary">주문취소</button>
							</form>
							</c:if>
						</td>
					</tr>
					<tr>
						<th class="text-center">입금은행</td>
						<td>${ordersVo.bankName}</td>
					</tr>
					<tr>
						<th class="text-center">계좌번호</td>
						<td>${ordersVo.bankNum}</td>
					</tr>
					<tr>
						<th class="text-center">금액</td>
						<td><fmt:formatNumber value="${ordersVo.money}" pattern="#,###" /> 원</td>
					</tr>
					<tr>
						<th class="text-center">결제일</td>
						<td>${ordersVo.payDate}</td>
					</tr>
					<tr>
						<th class="text-center">운송장번호</td>
						<td>${ordersVo.trackingNum}</td>
					</tr>
					<tr>
						<th class="text-center">받는사람 이름</td>
						<td>${ordersVo.toName}</td>
					</tr>
					<tr>
						<th class="text-center">받는사람 연락처</td>
						<td>${ordersVo.toPhone}</td>
					</tr>
					<tr>
						<th class="text-center">받는사람 우편번호</td>
						<td>${ordersVo.toZipcode}</td>
					</tr>
					<tr>
						<th class="text-center">받는사람 주소</td>
						<td>${ordersVo.toAddr}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-1"></div>
	
	
	</div>


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
						<div class="col-lg-2 col-4 text-center">${oidata.cnt}개</div>
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




</div>






<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>