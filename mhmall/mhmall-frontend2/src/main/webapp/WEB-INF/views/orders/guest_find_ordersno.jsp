<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>







<div class="container mt-5 mb-5">

	<div class="row">
	
	
		<div class="col-lg-2 col-md-1 col-sm-1"></div>
		<div class="col-lg-8 col-md-10 col-sm-10 text-center">
			<div class="panel-heading mb-2">
				<h4 class="panel-title text-left">비회원주문목록</h4>
			</div>
			<table class="table table-hover member_table">
				<thead>
					<tr>
						<th class="text-center">주문번호</th>
						<th class="text-center">주문일</th>
						<th class="text-center">상태</th>
						<th class="text-center">상세</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${ordersList}" var="odata">
					<tr>
						<td class="align-middle text-center pr-0 pl-0">${odata.ordersNo}</td>
						<td class="align-middle text-center pr-0 pl-0">${odata.regDate}</td>
						<td class="align-middle text-center pr-0 pl-0" style="width:80px;">${odata.status}</td>
						<td class="align-middle text-center pr-0 pl-0" style="width:110px;">
							<form action="${pageContext.servletContext.contextPath}/orders/guest/view" method="post">
								<input type="hidden" name="ordersNo" value="${odata.ordersNo}" />
								<input type="hidden" name="guestPassword" value="${guestPassword}" />
								<button type="submit" class="btn btn-secondary">상세보기</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-lg-2 col-md-1 col-sm-1"></div>
	
	
	</div>



	</div>




</div>






<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>