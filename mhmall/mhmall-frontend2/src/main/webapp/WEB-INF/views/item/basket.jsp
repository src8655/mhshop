<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


<script>
$(function(){
	
	
	
	
	
});
</script>





<!-- Page Content -->
  <div class="container">

    <div class="row">
	  
	  <c:import url="/WEB-INF/views/include/nav.jsp"></c:import>









      <div class="col-lg-9">



      <div class="text-muted mt-4 mb-4" style="border:1px solid #e6e6e6; border-radius:5px;overflow:hidden;">
        <div class=" d-xl-block d-lg-block d-md-block d-md-none d-sm-none d-none">
        <div class="row p-2 pt-3" style="border-bottom:2px solid #e6e6e6;">
          <h6 class="col-md-6" style="font-weight:bold;">상품</h6>
          <h6 class="col-md-2 text-center" style="font-weight:bold;">수량</h6>
          <h6 class="col-md-2 text-center" style="font-weight:bold;">가격</h6>
          <h6 class="col-md-2 text-center" style="font-weight:bold;">삭제</h6>
        </div>
        </div>
        
        <c:forEach items="${basketList}" var="bdata">
        <div class="row p-2" style="border-bottom:1px solid #e6e6e6;">
          <div class="col-lg-6">
            <figure class="media">
            	<div class="img-wrap mr-2" style="width:80px;overflow:hidden;">
            		<a href="${pageContext.servletContext.contextPath}/item/view/${bdata.itemNo}">
            			<img src="${pageContext.servletContext.contextPath}${bdata.thumbnail}" style="width:100%;" class="img-thumbnail img-sm">
            		</a>
            	</div>
            	<figcaption class="media-body">
            		<h6 class="title text-truncate pr-2" style="width:230px;height:20px;line-height:20px;oveflow:hidden;">
            			<a href="${pageContext.servletContext.contextPath}/item/view/${bdata.itemNo}" style="color:#676767;">${bdata.itemName}</a>
            		</h6>
            		<dl class="dlist-inline small">
            		  <dd>${bdata.optionNames}</dd>
            		</dl>
            	</figcaption>
            </figure> 
            
          </div>
          <div class="col-lg-2 col-4 text-center row">
            <div class="col-lg-12 col-sm-6 col-7 p-0 pl-4 pl-lg-0 pl-sm-4">
<form action="${pageContext.servletContext.contextPath}/item/basket/update" method="post">
<input type="hidden" name="no" value="${bdata.no}" />
	          <input type="text" name="cnt" class="form-control" value="${bdata.cnt}" style="width:100%;" />
	        </div>
	        <div class="col-lg-12 col-sm-6 col-5 p-0 pl-2 pl-lg-0 pl-sm-2">
	          <button type="submit" class="btn btn-primary pr-0 pl-0" style="width:100%;">수정</button>
</form>
	        </div>
          </div>
          <div class="col-lg-2 col-4 text-center">
          		<div class="price-wrap"> 
          			<var class="price"><fmt:formatNumber value="${bdata.money}" pattern="#,###" />원</var> 
          		</div>
          </div>
          <div class="col-lg-2 col-4 text-center">
<form action="${pageContext.servletContext.contextPath}/item/basket/delete" method="post">
<input type="hidden" name="no" value="${bdata.no}" />
          <button type="submit" class="btn btn-outline-danger" style="width:100%;">삭제</button>
</form>
          
          </div>
        </div>
        </c:forEach>
        
        
	    <div class="text-right">
		<c:if test="${authUser eq null}">
			<form action="${pageContext.servletContext.contextPath}/orders/guestinfo" method="post">
		</c:if>
		<c:if test="${authUser ne null}">
			<form action="${pageContext.servletContext.contextPath}/orders/member" method="post">
		</c:if>
	  	  <c:forEach items="${basketList}" var="bdata">
	  	  	<input type="hidden" name="optionNos" value="${bdata.optionNo}" />
	  	  	<input type="hidden" name="optionCnts" value="${bdata.cnt}" />
	  	  </c:forEach>
	      <button type="submit" class="btn btn-primary m-2">구매하기</button>
	  	</form>
	    </div>
      
      </div>






		</div>




			</div>
			<!-- /.row -->

  </div>
  <!-- /.container -->



<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
