<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>

<div class="container">
   	<div class="row">
		<div class="col-md-12">
		
		
			<a href="${pageContext.servletContext.contextPath}/admin/item/write" class="btn btn-primary mt-3">상품추가</a>
		
		
		
		</div>
	</div>
</div>



<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
