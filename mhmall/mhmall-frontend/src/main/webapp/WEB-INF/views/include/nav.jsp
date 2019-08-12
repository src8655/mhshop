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
        	<c:if test="${categoryNoPath ne null}">
				<c:if test="${categoryNoPath eq -1}">
					<a href="${pageContext.servletContext.contextPath}/item/list/-1" class="list-group-item active">전체</a>
				</c:if>
				<c:if test="${categoryNoPath ne -1}">
					<a href="${pageContext.servletContext.contextPath}/item/list/-1" class="list-group-item">전체</a>
				</c:if>
			</c:if>
			<c:if test="${categoryNoPath eq null}">
				<a href="${pageContext.servletContext.contextPath}/item/list/-1" class="list-group-item">전체</a>
			</c:if>
			<c:forEach items="${categoryList}" var="cdata">
				<c:if test="${categoryNoPath ne null}">
					<c:if test="${categoryNoPath eq cdata.no}">
						<a href="${pageContext.servletContext.contextPath}/item/list/${cdata.no}" class="list-group-item active">${cdata.name}</a>
					</c:if>
					<c:if test="${categoryNoPath ne cdata.no}">
						<a href="${pageContext.servletContext.contextPath}/item/list/${cdata.no}" class="list-group-item">${cdata.name}</a>
					</c:if>
				</c:if>
				<c:if test="${categoryNoPath eq null}">
          			<a href="${pageContext.servletContext.contextPath}/item/list/${cdata.no}" class="list-group-item">${cdata.name}</a>
          		</c:if>
			</c:forEach>
        </div>
        <button type="button" class="d-lg-none btn btn-primary mb-1 category-btn" style="width:100%;">카테고리</button>
        <div class="list-group d-lg-none" style="display:none;">
        	<c:if test="${categoryNoPath ne null}">
				<c:if test="${categoryNoPath eq -1}">
					<a href="${pageContext.servletContext.contextPath}/item/list/-1" class="list-group-item active">전체</a>
				</c:if>
				<c:if test="${categoryNoPath ne -1}">
					<a href="${pageContext.servletContext.contextPath}/item/list/-1" class="list-group-item">전체</a>
				</c:if>
			</c:if>
			<c:if test="${categoryNoPath eq null}">
				<a href="${pageContext.servletContext.contextPath}/item/list/-1" class="list-group-item">전체</a>
			</c:if>
			<c:forEach items="${categoryList}" var="cdata">
				<c:if test="${categoryNoPath ne null}">
					<c:if test="${categoryNoPath eq cdata.no}">
						<a href="${pageContext.servletContext.contextPath}/item/list/${cdata.no}" class="list-group-item active">${cdata.name}</a>
					</c:if>
					<c:if test="${categoryNoPath ne cdata.no}">
						<a href="${pageContext.servletContext.contextPath}/item/list/${cdata.no}" class="list-group-item">${cdata.name}</a>
					</c:if>
				</c:if>
				<c:if test="${categoryNoPath eq null}">
          			<a href="${pageContext.servletContext.contextPath}/item/list/${cdata.no}" class="list-group-item">${cdata.name}</a>
          		</c:if>
			</c:forEach>
        </div>
      </div>

  