<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


<script>
$(function(){
	
	// 수량 감소
	$("#tmp_item").on("click", ".minus-btn", function(){
		if($(this).parent().next().attr("disabled") == "disabled")
			return ;
		
		var cnt = $(this).parent().next().val();
		cnt = cnt-1;
		$(this).parent().next().val(cnt);
		$(this).parent().next().trigger("change");
	});
	// 수량 증가
	$("#tmp_item").on("click", ".plus-btn", function(){
		if($(this).parent().prev().attr("disabled") == "disabled")
			return ;
		
		var cnt = $(this).parent().prev().prev().val();
		cnt = Number(cnt)+1;
		$(this).parent().prev().prev().val(cnt);
		$(this).parent().prev().prev().trigger("change");
	});
	// 수량검사
	$("#tmp_item").on("change", ".opcnt", function(){
		var cnt = Number($(this).val());
		var max_cnt = Number($(this).next().val());
		
		if(cnt <= 0) $(this).val(1);
		if(cnt > max_cnt) $(this).val(max_cnt);
	});
	// 템플릿 닫기 버튼
	$("#tmp_item").on("click", ".closeTemplate", function(){
		$(this).parent().parent().parent().remove();
	});
	
	
	
	
	
	// 옵션1을 선택했을 때 해당하는 옵션2를 요청
	$("#option1").change(function(){
		
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
				
				// 옵션 생성
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
	    	
			return;
		}
		
		$("#left_cnt").val("");
		$("#option_delete_no").val("");
		var selected = $(this).find('option:selected');
	    var cnt = selected.data('cnt');
	    var no = selected.data('no');
	    
	    // 없는 재고이면 오류
	    if(cnt == 0) {
	    	alert("매진된 옵션입니다.");
	    	return;
	    }
		
	    // -1이면 남은수량 제한없음
		var tmp_cnt = cnt;
		if(tmp_cnt == -1) tmp_cnt = "제한없음";
		
		// 템플릿에 넣을 데이터
		var data = [
			{
				"options1":$("#option1 option:selected").html(),
				"options2":$("#option2 option:selected").html(),
				"optionsCnt":tmp_cnt,
				"optionsNo":no
			}
		];
		
		// 옵션이 이미 있는지 확인
		var isExist = false;
		$(".optionNos").each(function(){
			var no_str = no + "";
			if($(this).val() == no_str) isExist = true;
		});
		if(isExist) return;
		
		// 템플릿 생성
		$("#companyTemplate").template("companyTmpl");
		$.tmpl("companyTmpl" , data).appendTo("#tmp_item");
		
	});
	
	
	// 장바구니 추가 버튼
	$("#view-basket-btn").click(function(){
		if($(".optionNos").length == 0) return ;
		$("#item_view_form").attr("action", "${pageContext.request.contextPath}/item/basket");
		$("#item_view_form").submit();
	});
	

	
});
</script>




<!-- 비회원일때 -->
<sec:authorize access="!isAuthenticated()">
<script>
$(function(){

	// 바로 구매 버튼
	$("#view-buy-btn").click(function(){
		if($(".optionNos").length == 0) return ;
		$("#item_view_form").attr("action", "${pageContext.request.contextPath}/orders/guestinfo");
		$("#item_view_form").submit();
	});

});
</script>
</sec:authorize>


<!-- 회원일때 -->
<sec:authorize access="isAuthenticated()">
<script>
$(function(){

	// 바로 구매 버튼
	$("#view-buy-btn").click(function(){
		if($(".optionNos").length == 0) return ;
		$("#item_view_form").attr("action", "${pageContext.request.contextPath}/orders/member");
		$("#item_view_form").submit();
	});

});
</script>
</sec:authorize>






<!-- Page Content -->
  <div class="container">

    <div class="row">
	  
	  <c:import url="/WEB-INF/views/include/nav.jsp"></c:import>









      <div class="col-lg-9">





		<form action="#" method="post" id="item_view_form">
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
						<label for="option1">1차 옵션</label> <select name="optionDetailNo1" class="form-control" id="option1">
							<option value="-1">선택</option>
							<c:forEach items="${optionList}" var="odate">
								<option value="${odate.optionDetailNo1}">${odate.optionDetailName1}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-2">
						<label for="option2">2차 옵션</label> <select name="optionDetailNo2" class="form-control" id="option2" disabled>
							<option value="-1">-</option>
						</select>
					</div>





<script id="companyTemplate" type="text/x-jQuery-tmpl">
<div class="row mb-2 mr-1 ml-1 p-2" style="background:#eeeeee;">
	<input type="hidden" name="optionNos" value="{{= optionsNo}}" class="optionNos" />
	<div class="col-lg-12 col-xl-7">
		<div class="text-left mb-2" style="width: 100%;font-size:14px;">
			<button type="button" class="btn btn-secondary p-0 mr-2 closeTemplate" style="font-size:12px;width:17px;height:17px;line-height:7px;">X</button>
			{{= options1}} / {{= options2}} 수량({{= optionsCnt}})
		</div>
	</div>

	<div class="col-12 col-lg-12 col-xl-5 row p-0">
		<div class="col-6 col-lg-6 col-xl-0"></div>
		<div class="col-6 col-lg-6 col-xl-12">
			<div class="input-group" style="width: 100%;">
				<div class="input-group-prepend">
					<button class="btn btn-primary minus-btn" type="button" style="font-weight: bold;">-</button>
				</div>
				<input type="text" class="form-control opcnt" value="1" name="optionCnts">
				<input type="hidden" class="form-control" value="{{= optionsCnt}}" name="trash_cnt">
				<div class="input-group-append">
					<button class="btn btn-primary plus-btn" type="button" style="font-weight: bold;">+</button>
				</div>
			</div>
		</div>
	</div>
</div>
</script>
<div id="tmp_item">

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
