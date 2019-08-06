<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>

<style>
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


<script>
$(function(){

	// 회원검색 버튼
	$("#admin_member_search_btn").click(function(){
		var search = encodeURI($("#admin_member_search").val());
		location.href="${pageContext.servletContext.contextPath}/admin/member/" + search;
	});
	
	
});
</script>

	<div class="container">
    	<div class="row">
			<div class="col-md-12">
			
			
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">회원목록</h3>
						<div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip" title="Toggle table filter" data-container="body">
								<i class="glyphicon glyphicon-filter"></i>
							</span>
						</div>
					</div>
					<div class="panel-body">
						<input type="text" class="form-control float-left mb-3" style="width:68%;" id="admin_member_search" placeholder="이름으로 검색" value="${searchs}" />
						<button class="btn btn-success float-right" style="width:30%;" id="admin_member_search_btn">검색</button>
					</div>
					<div class="">
					<table class="table table-hover" id="dev-table">
						<thead>
							<tr>
								<th>아이디</th>
								<th>이름</th>
								<th>연락처</th>
								<th>이메일</th>
								<th>가입날짜</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${memberList}" var="mdata">
							<tr>
								<td>${mdata.id}</td>
								<td>${mdata.name}</td>
								<td>${mdata.phone}</td>
								<td>${mdata.email}</td>
								<td>${mdata.regDate}</td>
								<td><a href="#" class="btn btn-danger">삭제</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	


<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>