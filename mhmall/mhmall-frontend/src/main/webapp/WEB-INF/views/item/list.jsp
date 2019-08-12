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

        <div class="row mt-4">


		<c:forEach items="${itemList}" var="idata">
          <div class="col-xl-3 col-lg-6 col-md-6 mb-4">
            <div class="card h-100">
              <a style="display:block;overflow:hidden;" href="${pageContext.servletContext.contextPath}/item/view/${idata.no}">
              	<img class="card-img-top" src="${pageContext.servletContext.contextPath}${idata.thumbnail}" alt="">
              </a>
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
        
        
		<!-- pager 추가 -->
        <div class="row">
        	<div class="col-sm-12">
			<ul class="pagination justify-content-center">
				<!-- 1보다 작으면 버튼 비활성화 -->
				<c:if test="${(paging.rangeStart - 1) lt 1}">
					<li class="page-item disabled"><a class="page-link">◀</a></li>
				</c:if>
				<!-- 1보다 같거나 크면 버튼 활성화-->
				<c:if test="${(paging.rangeStart - 1) ge 1}">
					<li class="page-item">
						<a class="page-link" href="${pageContext.servletContext.contextPath}/item/list/${categoryNoPath}/${paging.rangeStart - 1}">◀</a>
					</li>
				</c:if>
				<c:forEach begin="${paging.rangeStart}" end="${paging.rangeStart + paging.pageCnt - 1}" var="i">
					<!-- 최대 페이지를 넘어가면 비활성화 -->
					<c:if test="${i gt paging.lastPage}">
						<li class="page-item disabled"><a class="page-link">${i}</a></li>
					</c:if>
					<c:if test="${i le paging.lastPage}">
						<c:if test="${i eq paging.pages}">
							<li class="page-item active"><a class="page-link">${i}</a></li>
						</c:if>
						<c:if test="${i ne paging.pages}">
							<li class="page-item"><a class="page-link" href="${pageContext.servletContext.contextPath}/item/list/${categoryNoPath}/${i}">${i}</a></li>
						</c:if>
					</c:if>
				</c:forEach>
				<!-- 최대 페이지보다 작거나 같으면 버튼활성화 -->
				<c:if test="${(paging.rangeStart + paging.pageCnt) le paging.lastPage}">
					<li class="page-item"><a class="page-link" href="${pageContext.servletContext.contextPath}/item/list/${categoryNoPath}/${paging.rangeStart + paging.pageCnt}">▶</a></li>
				</c:if>
				<!-- 최대 페이지보다 크면 버튼 비활성화 -->
				<c:if test="${(paging.rangeStart + paging.pageCnt) gt paging.lastPage}">
					<li class="page-item disabled"><a class="page-link">▶</a></li>
				</c:if>
			</ul>
			</div>
		</div>

        
        
        
      </div>
      
      
      
      
    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->



<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
