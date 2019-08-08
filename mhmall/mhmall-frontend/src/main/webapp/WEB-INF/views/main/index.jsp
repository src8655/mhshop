<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>



<!-- Page Content -->
  <div class="container">

    <div class="row">
	  
	  <c:import url="/WEB-INF/views/include/nav.jsp"></c:import>









      <div class="col-lg-9">
        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
       		
          <c:forEach items="${mainImgList}" var="midata" varStatus="nowst">
          	<div style="border-radius:5px;background-image:url(${pageContext.servletContext.contextPath}${midata.itemImg}); background-size:cover;"
          	class="carousel-item <c:if test="${nowst.index eq 0}">active</c:if>">
          		<div class="mt-4 mb-4" style="overflow:hidden;">
          		<div class="mt-5 mb-5 p-5" style="background-color: rgba( 0, 0, 0, 0.5 );font-size:18px;font-weight:bold;color:#ffffff;">
          			${midata.name}
          		</div>
          		</div>
            </div>
          </c:forEach>
            
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>

        <div class="row">


		<c:forEach items="${itemList}" var="idata">
          <div class="col-xl-3 col-lg-6 col-md-6 mb-4">
            <div class="card h-100">
              <a href="${pageContext.servletContext.contextPath}/item/view/${idata.no}"><img class="card-img-top" src="${pageContext.servletContext.contextPath}${idata.thumbnail}" alt=""></a>
              <div>
                <h5 class="text-center mr-2 ml-2" style="height:20px;line-height:20px;overflow:hidden;">
                  <a href="${pageContext.servletContext.contextPath}/item/view/${idata.no}" style="text-decoration:none;color:#000000;font-size:14px;">${idata.name}</a>
                </h5>
                <h6 class="m-2 text-center">
                  <fmt:formatNumber value="${idata.money}" pattern="#,###" />원
                </h6>
              </div>
            </div>
          </div>
        </c:forEach>
          

        </div>
        <!-- /.row -->
      </div>
      
      
      
      
    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->



<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
