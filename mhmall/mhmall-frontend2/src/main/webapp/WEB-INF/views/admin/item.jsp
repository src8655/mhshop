<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>
<style type="text/css">
.item_name_box {
position:absolute;
background-color: rgba( 255, 255, 255, 1.0 );
width:100%;
padding:0 10px 0 10px;
line-height:30px;
height:30px;
bottom:0px;
overflow:hidden;
}
</style>
<script>
$(function(){

	// 회원검색 버튼
	$("#item_list_category").change(function(){
		var cdata_no = $(this).val();
		location.href="${pageContext.servletContext.contextPath}/admin/item/"+cdata_no;
	});
	
	
});
</script>



<div class="container">
   	<div class="row">
		<div class="col-md-12">
		
		<div class="panel panel-default ml-3 mr-3">
		  <div class="panel-body">
			  <div class="form-group row">
			  	<div class="col-sm-3 mt-3">
				    <select class="form-control" id="item_list_category">
						<option value="-1">전체</option>
						<c:forEach items="${categoryList}" var="cdata">
							<option value="${cdata.no}" <c:if test="${cdata.no eq categoryNos}">selected</c:if>>${cdata.name}</option>
						</c:forEach>
				    </select>
			    </div>
			  	<div class="col-sm-3 mt-3">
				    <a href="${pageContext.servletContext.contextPath}/admin/item/write" class="btn btn-primary" style="width:100%;">상품추가</a>
			    </div>
			  </div>
		  </div>
		</div>
		
	<div class="container-fluid">
	    <div class="row text-center">
	    
	    
	    <c:forEach items="${itemList}" var="idata">
	      <div class="col-lg-4 col-md-6 mb-4">
	        <div class="card h-100">
	        <c:if test="${idata.display eq 'TRUE'}">
	          <div class="btn btn-default">판매중</div>
	        </c:if>
	        <c:if test="${idata.display eq 'FALSE'}">
	          <div class="btn btn-default" style="color:blue;">판매중지</div>
	        </c:if>
	          <div style="position:relative;">
	          	<img class="card-img-top" src="${pageContext.servletContext.contextPath}${idata.thumbnail}" alt="">
	            <div class="item_name_box">
	            	${idata.name}
	            </div>
	          </div>
	          <div class="card-footer">
	          	<c:if test="${idata.display eq 'TRUE'}">
		            <form action="${pageContext.servletContext.contextPath}/admin/item/display" method="post" style="display:inline;">
	           		<input type="hidden" name="no" value="${idata.no}" />
	           		<input type="hidden" name="display" value="FALSE" />
	           		<input type="hidden" name="categoryNo" value="${categoryNos}" />
		            	<button type="submit" class="btn btn-primary">판매중지</button>
		            </form>
	            </c:if>
	          	<c:if test="${idata.display eq 'FALSE'}">
		            <form action="${pageContext.servletContext.contextPath}/admin/item/display" method="post" style="display:inline;">
	           		<input type="hidden" name="no" value="${idata.no}" />
	           		<input type="hidden" name="display" value="TRUE" />
	           		<input type="hidden" name="categoryNo" value="${categoryNos}" />
		            	<button type="submit" class="btn btn-primary">판매시작</button>
		            </form>
	            </c:if>
	            <form action="${pageContext.servletContext.contextPath}/admin/item/delete" method="post" style="display:inline;">
	            <input type="hidden" name="no" value="${idata.no}" />
	            <input type="hidden" name="categoryNo" value="${categoryNos}" />
	            	<button type="submit" class="btn btn-primary">삭제</button>
	            </form>
	            <a href="${pageContext.servletContext.contextPath}/admin/item/edit/${idata.no}" class="btn btn-primary">수정</a>
	          </div>
	        </div>
	      </div>
	     </c:forEach>
	     
	      
		</div>
	</div>
      
      
		
		</div>
	</div>
</div>



<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
