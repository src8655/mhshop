<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script>
$(function(){

	// 카테고리 열기 닫기버튼
	$(".category-btn").click(function(){
		$(this).next().slideToggle(100);
	});
	
});
</script>

      <div class="col-lg-3 mt-4">
        <div class="mb-4 list-group d-lg-block d-md-none d-sm-none d-none">
			<c:forEach items="${categoryList}" var="cdata">
          		<a href="${cdata.no}" class="list-group-item">${cdata.name}</a>
			</c:forEach>
        </div>
        <button type="button" class="d-lg-none btn btn-primary mb-1 category-btn" style="width:100%;">카테고리</button>
        <div class="list-group d-lg-none" style="display:none;">
			<c:forEach items="${categoryList}" var="cdata">
          		<a href="${cdata.no}" class="list-group-item">${cdata.name}</a>
			</c:forEach>
        </div>
      </div>

  