<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>







<div class="container mt-5 mb-5">

	<div class="row">
	
	
		<div class="col-lg-2 col-md-2 col-sm-1"></div>
		<div class="col-lg-8 col-md-8 col-sm-10 text-center">
			<table class="table table-hover member_table">
				<thead>
					<tr>
						<th colspan="2" class="text-center">회원 주문이 완료되었습니다.</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th class="text-center">주문번호</td>
						<td class="text-left">${ordersVo.ordersNo}</td>
					</tr>
					<tr>
						<th class="text-center">입금계좌</td>
						<td class="text-left">(${ordersVo.bankName}) ${ordersVo.bankNum}</td>
					</tr>
					<tr>
						<th class="text-center">입금금액</td>
						<td class="text-left"><fmt:formatNumber value="${ordersVo.money}" pattern="#,###" /> 원</td>
					</tr>
				</tbody>
			</table>
			<a href="${pageContext.servletContext.contextPath}/orders/member/list" class="btn btn-primary">주문내역조회</a>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-1"></div>
	
	
	</div>



	</div>




</div>






<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>