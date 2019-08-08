<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/include/header.jsp"></c:import>









<div style="width:100%;text-align:center;font-size:23px;padding:50px 0 50px 0;">
	<span style="color:blue;">${name}님</span>의 회원가입이 완료되었습니다.<br /><br />
	<a href="${pageContext.servletContext.contextPath}/member/login" class="btn btn-primary">로그인하기</a>
</div>










<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>