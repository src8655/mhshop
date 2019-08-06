<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:import url="/WEB-INF/views/member/include/header.jsp"></c:import>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
$(function(){

	// 아이디 중복체크
	// 아이디가 변경되었을 때
	$("#user_ids").change(function(){
		var id = $("#user_ids").val();
		$.ajax({
			url: "http://localhost:8080/mhmall-frontend/member/join/idcheck/"+id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result != "success") {
					$("#id_ch_value").html("잘못된 아이디 형식입니다.");
					return;
				}
				if(response.data == true) {
					$("#id_ch_value").html("중복된 아이디 입니다.");
					$("#id_ch_value").val("");
					return;
				}
				$("#id_ch_value").html("사용 가능한 아이디입니다.");
				
			},
			error: function(xhr, error){
				$("#id_ch_value").html("잘못된 아이디 형식입니다.");
			}
		});
	});
	
	
	// 다음우편번호
	$("#zipcode_btn").click(function(){
		new daum.Postcode({
		      oncomplete: function(data) {
		    	  $("#zipcode").val(data.postcode);
		    	  $("#addr").val(data.address+" "+data.buildingName);
		      }
		  }).open();
	});
	
	
});
</script>

	<div class="container">
		<div class="row">


			<div class="col-lg-4 col-md-3 col-sm-1"></div>
			<div class="col-lg-4 col-md-6 col-sm-10">
				<div class="card" style="margin-top:20px;">
					<article class="card-body">
					<h4 class="card-title mb-4 mt-1">회원가입</h4>
					<form action="join" method="post">
						<div class="form-group">
							<input name="name" class="form-control" placeholder="이름" type="text" />
						</div>
						<div class="form-group">
							<input name="id" class="form-control" placeholder="아이디" type="text" id="user_ids"/>
							<span id="id_ch_value" style="color:blue;">아이디를 입력해 주세요.</span>
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<input name="password" class="form-control" placeholder="비밀번호" type="password" />
						</div>
						<div class="form-group">
							<input name="password2" class="form-control" placeholder="비밀번호 확인" type="password" />
						</div>
						<div class="form-group">
							<input name="email" class="form-control" placeholder="이메일" type="text" />
						</div>
						<div class="form-group">
							<input name="zipcode" class="form-control float-left mb-3" style="width:45%;" placeholder="우편번호" type="text" id="zipcode" />
					    	<a href="#10" class="float-right btn btn-outline-primary" style="width:50%;" id="zipcode_btn">우편번호찾기</a>
						</div>
						<div class="form-group">
							<input name="addr" class="form-control" placeholder="주소" type="text" id="addr" />
						</div>
						<div class="form-group">
							<input name="phone1" class="form-control float-left mb-3"style="width:30%;" placeholder="010" type="text" />
							<input name="phone2" class="form-control float-left mb-3 ml-1"style="width:30%;" placeholder="0000" type="text" />
							<input name="phone3" class="form-control float-left mb-3 ml-1"style="width:30%;" placeholder="0000" type="text" />
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								회원가입</button>
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






<c:import url="/WEB-INF/views/member/include/footer.jsp"></c:import>