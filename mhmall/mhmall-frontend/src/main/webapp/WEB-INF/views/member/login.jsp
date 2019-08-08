<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

	<div class="container mt-5 mb-5">
		<div class="row">


			<div class="col-lg-4 col-md-3 col-sm-1"></div>
			<div class="col-lg-4 col-md-6 col-sm-10">
				<div class="card">
					<article class="card-body"> <a href="join"
						class="float-right btn btn-outline-primary">회원가입</a>
					<h4 class="card-title mb-4 mt-1">로그인</h4>
					<form action="login" method="post">
						<div class="form-group">
							<label>아이디</label> <input name="id" class="form-control"
								placeholder="아이디" type="text" />
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<a class="float-right" href="#" style="display:none;">Forgot?</a> <label>비밀번호</label>
							<input name="password" class="form-control" placeholder="******"
								type="password" />
						</div>
						<!-- form-group// -->
						<div class="form-group" style="display:none;">
							<div class="checkbox">
								<label> <input type="checkbox" /> Save password </label>
							</div>
							<!-- checkbox .// -->
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								로그인</button>
						</div>
						<!-- form-group// -->
					</form>
					</article>
				</div>
				<!-- card.// -->
			</div>
			<div class="col-lg-4 col-md-3 col-sm-1"></div>


		</div>
	</div>





<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
