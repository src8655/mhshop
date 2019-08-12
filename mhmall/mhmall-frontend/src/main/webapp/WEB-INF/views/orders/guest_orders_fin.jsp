<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>







<div style="width:100%;text-align:center;font-size:23px;padding:50px 0 50px 0;">
	주문이 완료되었습니다.<br />
	주문번호 : ${ordersVo.ordersNo}<br />
	입금계좌 : (${ordersVo.bankName}) ${ordersVo.bankNum}<br />
	입금금액 : <fmt:formatNumber value="${ordersVo.money}" pattern="#,###" /> 원
	<br /><br />
	<a href="${pageContext.servletContext.contextPath}/orders/guest/view" class="btn btn-primary">주문내역조회</a>
</div>






<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>