<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>


<style type="text/css">
.row{
    margin-top:40px;
    padding: 0 10px;
}
.clickable{
    cursor: pointer;   
}

.panel-heading div {
	margin-top: -18px;
	font-size: 15px;
}
.panel-heading div span{
	margin-left:5px;
}

</style>




<div class="container">
    	<div class="row">
			<div class="col-sm-12">
			
			
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">카테고리</h3>
					</div>
					<table class="table table-hover member_table">
						<thead>
							<tr>
								<th>번호</th>
								<th>카테고리명</th>
								<th colspan="2">관리</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<form action="${pageContext.servletContext.contextPath}/admin/category" method="post">
								<td colspan="2"><input type="text" name="categoryName" class="form-control" placeholder="카테고리명" /></td>
								<td colspan="2"><button type="submit" class="btn btn-success" style="width:100%;">추가하기</button></td>
							</form>
							</tr>
							<c:forEach items="${categoryList}" var="cdata">
							<tr>
								<td>${cnt}</td>
								<form action="${pageContext.servletContext.contextPath}/admin/category/edit" method="post">
								<input type="hidden" name="no" value="${cdata.no}" />
									<td>
										<input type="text" name="categoryName" class="form-control" placeholder="카테고리명" value="${cdata.name}" style="width:100%;margin:0px;" />
									</td>
									<td>
										<button type="submit" class="btn btn-primary c_edit_show_btn" style="width:100%;">수정</button>
									</td>
								</form>
								<td>
									<form action="${pageContext.servletContext.contextPath}/admin/category/delete" method="post">
										<input type="hidden" name="no" value="${cdata.no}" />
										<button type="submit" class="btn btn-primary" style="width:100%;">삭제</button>
									</form>
								</td>
							</tr>
							<c:set var="cnt" scope="page" value="${cnt+1}"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
