<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


<script>
$(function(){
	
	// 수량 감소
	$(".minus-btn").click(function(){
		
		if($(this).parent().next().attr("disabled") == "disabled")
			return ;
		
		var cnt = $(this).parent().next().val();
		cnt = cnt-1;
		if(cnt <= 0) cnt = 1;
		$(this).parent().next().val(cnt);
	});
	// 수량 증가
	$(".plus-btn").click(function(){
		if($(this).parent().prev().attr("disabled") == "disabled")
			return ;
		
		var cnt = $(this).parent().prev().val();
		cnt = Number(cnt)+1;
		$(this).parent().prev().val(cnt);
	});
	
	
	
	
	
	// 옵션1을 선택했을 때 해당하는 옵션2를 요청
	$("#option1").change(function(){
		// 남은수량 초기화
		$("#left_cnt").html("수량");
		// 선택된 옵션번호 초기화
		$("#option_selected_no").val("-1");
		
		// 수량 초기화
		$("#cnt_input").val("-");
		$("#cnt_input").attr("disabled", true);
		
		var optionDetailNo1 = $(this).val();
		if(optionDetailNo1 == -1) {
			var htmls = '<option value="-1">-</option>';
			// 옵션2 초기화
			$("#option2").html(htmls);
			$("#option2").attr("disabled", true);
			
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath}/item/option/${itemVo.no}/"+optionDetailNo1,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result != "success") {
					return;
				}
				
				var htmls = '<option value="-1">선택</option>';
				
				var i = 0;
				for(i=0;i<response.data.length;i++) {
					var optionVo = response.data[i];
					htmls += '<option value="'+optionVo.optionDetailNo2+'" data-cnt="'+optionVo.cnt+'" data-no="'+optionVo.no+'">';
					htmls += '' + optionVo.optionDetailName2;
					htmls += '</option>';
				}
				
				// 옵션2 사용
				$("#option2").html(htmls);
				$("#option2").attr("disabled", false);
				
				
			},
			error: function(xhr, error){
			}
		});
	});
	
	
	// 옵션2을 선택했을 때 해당하는 남은 수량을 요청
	$("#option2").change(function(){
		// 옵션2가 선택되지 않은 상태면
		var optionDetailNo2 = $(this).val();
		if(optionDetailNo2 == -1) {
			// 남은수량 초기화
			$("#left_cnt").html("수량");
			// 선택된 옵션번호 초기화
			$("#option_selected_no").val("-1");
	    	
			// 수량 초기화
			$("#cnt_input").val("-");
			$("#cnt_input").attr("disabled", true);
			return;
		}
		
		$("#left_cnt").val("");
		$("#option_delete_no").val("");
		var selected = $(this).find('option:selected');
	    var cnt = selected.data('cnt');
	    var no = selected.data('no');
	    if(cnt == -1)
	    	$("#left_cnt").html("제한없음");
	    else
	    	$("#left_cnt").html(cnt + "개 남음");
		$("#option_selected_no").val(no);
		

		// 수량 
		$("#cnt_input").val("1");
		$("#cnt_input").attr("disabled", false);
	});
	
	
	
	
	
	// 장바구니 추가 버튼
	$("#view-basket-btn").click(function(){
		if($("#cnt_input").attr("disabled") == "disabled") {
			return ;
		}
		$("#item_view_form").attr("action", "${pageContext.request.contextPath}/item/basket");
		$("#item_view_form").submit();
	});
	
	// 바로 구매 버튼
	$("#view-buy-btn").click(function(){
		if($("#cnt_input").attr("disabled") == "disabled") {
			return ;
		}
		$("#item_view_form").attr("action", "${pageContext.request.contextPath}/item/buy");
		$("#item_view_form").submit();
	});
	
});
</script>





<!-- Page Content -->
  <div class="container">

    <div class="row">
	  
	  <c:import url="/WEB-INF/views/include/nav.jsp"></c:import>









      <div class="col-lg-9">





		<form action="#" method="post" id="item_view_form">
		<input type="hidden" name="optionNo" value="-1" id="option_selected_no" />
		<input type="hidden" name="itemNo" value="${itemVo.no}" />
			<div class="mt-4 row">
				<div class="col-md-6">
					<img class="card-img-top img-fluid"
						src="${pageContext.servletContext.contextPath}${itemVo.thumbnail}"
						width="100%" style="border: 2px solid #eeeeee;" alt="">
				</div>
				<div class="col-md-6 card-body">
					<h4 class="card-title">${itemVo.name}</h4>
					<h4>
                  		<fmt:formatNumber value="${itemVo.money}" pattern="#,###" />원
					</h4>

					<div class="mb-2">
						<label for="option1">1차 옵션</label> <select name="optionDetailNo1"
							class="form-control" id="option1">
							<option value="-1">선택</option>
							<c:forEach items="${optionList}" var="odate">
								<option value="${odate.optionDetailNo1}">${odate.optionDetailName1}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-2">
						<label for="option2">2차 옵션</label> <select name="optionDetailNo2"
							class="form-control" id="option2" disabled>
							<option value="-1">-</option>
						</select>
					</div>

					<div class="row mb-2">
						<div class="col-6 input-group">
							<div class="btn text-center" id="left_cnt" style="width: 100%;">수량</div>
						</div>
						<div class="col-6 input-group mb-2">
							<div class="input-group" style="width: 130px;">
								<div class="input-group-prepend">
									<button class="btn btn-primary minus-btn" type="button"
										style="font-weight: bold; width: 40px;">-</button>
								</div>
								<input type="text" class="form-control" value="-" name="cnt" id="cnt_input" disabled>
								<div class="input-group-append">
									<button class="btn btn-primary plus-btn" type="button"
										style="font-weight: bold; width: 40px;">+</button>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6 mb-2">
							<button type="button" class="btn btn-danger" style="width: 100%;" id="view-basket-btn">장바구니추가</button>
						</div>
						<div class="col-sm-6 mb-2">
							<button type="button" class="btn btn-primary" style="width: 100%;" id="view-buy-btn">바로구매</button>
						</div>
					</div>

				</div>
			</div>
		</form>
			<!-- /.card -->

			<div class="card card-outline-secondary my-4">
				<div class="card-header">상품 상세정보</div>
				<div class="card-body">
					<p>
						<c:forEach items="${itemImgList}" var="iidata">
							<img src="${pageContext.servletContext.contextPath}${iidata.itemImg}" style="width:100%;" alt="img" />
						</c:forEach>
					</p>
					<hr>
					<p>
						${itemVo.description}
					</p>
				</div>
			</div>







		</div>




			</div>
			<!-- /.row -->

  </div>
  <!-- /.container -->



<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
