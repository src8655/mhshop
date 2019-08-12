<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 


<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>

<style type="text/css">

.panel-heading div {
	margin-top: -18px;
	font-size: 15px;
}
.panel-heading div span{
	margin-left:5px;
}

@media screen and (max-width:990px) {
	.member_table_bg {
		width:100%;
		overflow-x:scroll;
		overflow-y:hidden;
	}
	.member_table {
		width:750px;
	}
}
</style>





<script>
$(function(){


	
	
});
</script>





<div class="container mt-5 mb-5">
   	<div class="row">
		<div class="col-md-12">
		
		
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">주문목록</h3>
				</div>
				<div class="member_table_bg">
				<table class="table table-hover member_table">
					<thead>
						<tr>
							<th class="text-center align-middle">주문일자</th>
							<th class="text-center align-middle" colspan="2">주문 상품 정보</th>
							<th class="text-center align-middle">금액<br />(수량)</th>
							<th class="text-center align-middle">상태</th>
							<th class="text-center align-middle">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ordersList}" var="odata">
						<c:forEach items="${odata.ordersItemList}" var="oidata" varStatus="oistatus">
						<tr>
							<c:if test="${oistatus.index eq 0}">
								<td class="text-center align-middle" rowspan="${fn:length(odata.ordersItemList)}">
									${odata.regDate}<br />
									(${odata.ordersNo})<br />
									<a href="${pageContext.request.contextPath}/admin/orders/view/${odata.ordersNo}" class="btn btn-secondary">상세보기</a>
								</td>
							</c:if>
							<td>
								<img src="${pageContext.request.contextPath}${oidata.itemThumbnail}" style="width:50px;" alt="img" />
							</td>
							<td>
								${oidata.itemName}<br />
								${oidata.itemOptionDetail1} ${oidata.itemOptionDetail2}
							</td>
							<td class="text-center" style="width:120px;">
								<fmt:formatNumber value="${oidata.money}" pattern="#,###" /> 원<br />
								(${oidata.cnt}개)
							</td>
							<c:if test="${oistatus.index eq 0}">
								<td class="text-center align-middle" style="width:100px;" rowspan="${fn:length(odata.ordersItemList)}">
									${odata.status}
								</td>
								<td class="text-center align-middle" style="width:100px;" rowspan="${fn:length(odata.ordersItemList)}">
									<%-- <c:if test="${odata.status eq '입금대기'}">
										<form action="${pageContext.request.contextPath}/orders/member/cancel" method="post">
											<input type="hidden" name="ordersNo" value="${odata.ordersNo}" />
											<button type="submit" class="btn btn-primary w-100 pl-0 pr-0">주문취소</button>
										</form>
									</c:if> --%>
								</td>
							</c:if>
						</tr>
						</c:forEach>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>







<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>