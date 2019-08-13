<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>


<script>
	location.href="${pageContext.servletContext.contextPath}/admin/item";
</script>


<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
