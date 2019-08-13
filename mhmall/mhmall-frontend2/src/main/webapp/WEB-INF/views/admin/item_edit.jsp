<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>

<script src="${pageContext.request.contextPath}/assets/ckeditor/ckeditor.js"></script>
<style type="text/css">
.optiondetail-scroll {
background:#ffffff;
border:2px solid #eeeeee;
height:300px;
display:none;
position:absolute;
z-index:1000;
padding-top:15px;
overflow-y:scroll;
overflow-x:hidden;
}

.optiondetail {
background:#ffffff;
border:2px solid #eeeeee;
display:none;
position:absolute;
z-index:1000;
padding-top:15px;
overflow:hidden;
}

.itemimglist_img {
width:100%;
height:200px;
background-size:cover;
overflow:hidden;
}

@media screen and (min-width:1500px) {
	.item_write_right {
		float:left;
		margin-top:30px;
		margin-left:20px;
		width:630px;
	}
}

@media screen and (max-width:1500px) {
	.item_write_right {
		float:left;
		margin-top:30px;
		margin-left:20px;
		width:400px;
	}
}

@media screen and (max-width:1050px) {
	.item_write_right {
		float:none;
		margin-top:0px;
		margin-left:0px;
		width:100%;
		overflow:hidden;
	}
}
</style>


<script>
$(function(){

	// 썸네일 첨부 버튼
	$("#thumbnail_file_btn").click(function(){
		$(this).next().trigger("click");
		
		function change_img(imgs, files) {
		
		}
	});
	$("#thumbnail_file").change(function(){
		var reader = new FileReader();
		reader.onload = function (e) {
			$("#thumbnail_file_img").attr("src",e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	});
	
	
	// 상세옵션관리버튼
	$(".optiondetaillist").click(function(){
		$(this).parent().next().slideToggle(1);
	});
	// 상세옵션관리닫기
	$(".optiondetaillist2").click(function(){
		$(this).parent().slideToggle(1);
	});
	
	
	
	// 옵션1을 선택했을 때 해당하는 옵션2를 요청
	$("#option1").change(function(){
		$("#left_cnt").val("");
		$("#option_delete_no").val("");
		
		var optionDetailNo1 = $(this).val();
		if(optionDetailNo1 == -1) {
			var htmls = '<option value="-1">선택</option>';
			$("#option2").html(htmls);
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath}/admin/item/option/${itemVo.no}/"+optionDetailNo1,
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
				
				$("#option2").html(htmls);
				
			},
			error: function(xhr, error){
			}
		});
	});
	
	
	// 옵션2을 선택했을 때 해당하는 남은 수량을 요청
	$("#option2").change(function(){
		$("#left_cnt").val("");
		$("#option_delete_no").val("");
		var selected = $(this).find('option:selected');
	    var cnt = selected.data('cnt');
	    var no = selected.data('no');
	    $("#left_cnt").val(cnt);
		$("#option_delete_no").val(no);
	});
	
});
</script>








<div class="container">


	<!-- 상품 상세옵션 -->
	<div class="row">
	
	
		<div class="col-lg-6 col-md-12">
			<div class="form-group row m-2" style="position:relative;">
			  <div class="col-sm-12">
			  	<h5>1차 상세옵션</h5>
			  </div>
			  <div class="col-sm-8 mb-2">
				<form action="${pageContext.request.contextPath}/admin/item/optiondetail" method="post" style="width:100%;">
				<input type="hidden" name="itemNo" value="${itemVo.no}" />
				<input type="hidden" name="level" value="1" />
			    <input class="form-control" name="optionName" type="text" />
			  </div>
			  <div class="col-sm-4 mb-2">
			    <button type="submit" class="btn btn-primary" style="width:100%;">저장</button>
				</form>
			  </div>
			  <div class="col-sm-12 mb-2">
			  	<button type="button" class="btn btn-primary optiondetaillist" style="width:100%;">상세옵션 관리</button>
			  </div>
			  <div class="col-sm-12 mb-2 optiondetail-scroll">
			  	<button type="button" class="btn btn-primary optiondetaillist2" style="width:100%;">닫기</button>
			  	<table class="table table-hover member_table">
					<thead>
						<tr>
							<th>상세옵션명</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${optionDetailList1}" var="oddata">
						<tr>
						<form action="${pageContext.request.contextPath}/admin/item/optiondetail/delete" method="post">
						<input type="hidden" name="itemNo" value="${itemVo.no}" />
						<input type="hidden" name="no" value="${oddata.no}" />
							<td>${oddata.optionName}</td>
							<td><button type="submit" class="btn btn-danger" style="width:100%;">삭제</button></td>
						</form>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			  </div>
			</div>
		</div>
		
		
		<div class="col-lg-6 col-md-12">
			<div class="form-group row m-2" style="position:relative;">
			  <div class="col-sm-12">
			  	<h5>2차 상세옵션</h5>
			  </div>
			  <div class="col-sm-8 mb-2">
			<form action="${pageContext.request.contextPath}/admin/item/optiondetail" method="post">
			<input type="hidden" name="itemNo" value="${itemVo.no}" />
			<input type="hidden" name="level" value="2" />
			    <input class="form-control" name="optionName" type="text" />
			  </div>
			  <div class="col-sm-4 mb-2">
			    <button type="submit" class="btn btn-primary" style="width:100%;">저장</button>
			</form>
			  </div>
			  <div class="col-sm-12 mb-2">
			  	<button type="button" class="btn btn-primary optiondetaillist" style="width:100%;">상세옵션 관리</button>
			  </div>
			  <div class="col-sm-12 mb-2 optiondetail-scroll">
			  	<button type="button" class="btn btn-primary optiondetaillist2" style="width:100%;">닫기</button>
			  	<table class="table table-hover member_table">
					<thead>
						<tr>
							<th>상세옵션명</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${optionDetailList2}" var="oddata">
						<tr>
						<form action="${pageContext.request.contextPath}/admin/item/optiondetail/delete" method="post">
						<input type="hidden" name="itemNo" value="${itemVo.no}" />
						<input type="hidden" name="no" value="${oddata.no}" />
							<td>${oddata.optionName}</td>
							<td><button type="submit" class="btn btn-danger" style="width:100%;">삭제</button></td>
						</form>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			  </div>
			</div>
		</div>
		
		
	</div>
	
	
	<div class="row">
	
		<!-- 상품옵션 -->
		<div class="col-lg-6 col-md-12">
		
		
			<div class="form-group row m-2" style="position:relative;">
			  <div class="col-sm-12">
			  	<h5>옵션추가</h5>
			  </div>
			  <div class="col-sm-4 mb-2">
				<form action="${pageContext.request.contextPath}/admin/item/option" method="post">
				<input type="hidden" name="itemNo" value="${itemVo.no}" />
			  	<label for="optiondetail1">1차 옵션</label>
			    <select name="optionDetailNo1" class="form-control" id="optiondetail1">
					<option value="-1">없음</option>
					<c:forEach items="${optionDetailList1}" var="oddate">
						<option value="${oddate.no}">${oddate.optionName}</option>
					</c:forEach>
				</select>
			  </div>
			  <div class="col-sm-4 mb-2">
			  	<label for="optiondetail2">2차 옵션</label>
			    <select name="optionDetailNo2" class="form-control" id="optiondetail2">
					<option value="-1">없음</option>
					<c:forEach items="${optionDetailList2}" var="oddate">
						<option value="${oddate.no}">${oddate.optionName}</option>
					</c:forEach>
				</select>
			  </div>
			  <div class="col-sm-4 mb-2">
			  	<label for="optionmoney">남은수량</label>
			    <input class="form-control" name="cnt" id="cnt" type="text" />
			  </div>
			  <div class="col-sm-12 mb-2">
			    <button type="submit" class="btn btn-primary" style="width:100%;">추가</button>
				</form>
			  </div>
			  
			  
			  <div class="col-sm-12 mb-2">
			  	<button type="button" class="btn btn-primary optiondetaillist" style="width:100%;">옵션 관리</button>
			  </div>
			  <div class="col-sm-12 mb-2 optiondetail">
			  	<button type="button" class="btn btn-primary optiondetaillist2" style="width:100%;">닫기</button>
			  	
			  	
			<form action="${pageContext.request.contextPath}/admin/item/option/delete" method="post">
			<input type="hidden" name="itemNo" value="${itemVo.no}" />
			<input type="hidden" name="no" value="-1" id="option_delete_no" />
			  	<div class="form-group row m-2">
				  <div class="col-sm-12">
				  	<h5>옵션관리</h5>
				  </div>
				  <div class="col-sm-4 mb-2">
				  	<label for="option1">1차 옵션</label>
				    <select name="optionDetailNo1" class="form-control" id="option1">
						<option value="-1">선택</option>
						<c:forEach items="${optionList}" var="odate">
							<option value="${odate.optionDetailNo1}">${odate.optionDetailName1}</option>
						</c:forEach>
					</select>
				  </div>
				  <div class="col-sm-4 mb-2">
				  	<label for="option2">2차 옵션</label>
				    <select name="optionDetailNo2" class="form-control" id="option2">
						<option value="-1">선택</option>
					</select>
				  </div>
				  <div class="col-sm-4 mb-2">
				  	<label>수량</label>
				    <input class="form-control" id="left_cnt" type="text" disabled />
				  </div>
				  <div class="col-sm-12 mb-2">
				    <button type="submit" class="btn btn-primary" style="width:100%;">삭제</button>
				  </div>
			  </div>
			  </form>
			 </div>
			  
			  
			</div>
		
		
		</div>
		
		
		
		<!-- 상품이미지 -->
		<div class="col-lg-6 col-md-12">
		
		
			<div class="form-group row m-2">
			
			
				<div class="col-sm-12">
					<h5>상품이미지 추가</h5>
				</div>
				<div class="col-sm-12 mb-2">
					<form action="${pageContext.request.contextPath}/admin/item/img/write" method="post" enctype="multipart/form-data">
					<input type="hidden" name="itemNo" value="${itemVo.no}" />
					<div class="input-group">
					  <div class="input-group-prepend">
					    <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
					  </div>
					  <div class="custom-file">
					    <input type="file" name="itemImgFile" class="custom-file-input" id="inputGroupFile01"
					      aria-describedby="inputGroupFileAddon01">
					    <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
					  </div>
					</div>
				</div>
				<div class="col-sm-12 mb-2">
				  <button type="submit" class="btn btn-primary" style="width:100%;">추가</button>
					</form>
				</div>
				
				 <div class="col-sm-12 mb-2">
				 	<button type="button" class="btn btn-primary optiondetaillist" style="width:100%;">상품이미지 관리</button>
				 </div>
				 <div class="col-sm-12 mb-2 optiondetail-scroll" style="height:600px;">
				 	<button type="button" class="btn btn-primary optiondetaillist2" style="width:100%;">닫기</button>
				 	<table class="table table-hover member_table">
				 	<col />
				 	<col width="30%" />
					<thead>
						<tr>
							<th>이미지</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${itemImgList}" var="iidata">
						<tr>
							<form action="${pageContext.request.contextPath}/admin/item/img/delete" method="post">
							<input type="hidden" name="itemNo" value="${itemVo.no}" />
							<input type="hidden" name="no" value="${iidata.no}" />
							<td>
								<a target="_BLANK" href="${pageContext.servletContext.contextPath}${iidata.itemImg}" class="itemimglist_img" style="background-image:url(${pageContext.servletContext.contextPath}${iidata.itemImg});display:block;">
								</a>
							</td>
							<td><button type="submit" class="btn btn-danger" style="width:100%;">삭제</button></td>
							</form>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				
				
			</div>
			
			
		</div>
	
	</div>
	
	
	

	<!-- 상품상세 -->
<form action="${pageContext.request.contextPath}/admin/item/edit" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="${itemVo.no}" />
   	<div class="row">
		<div class="col-md-12">
		
			
			<div class="form-group mt-3 float-left" style="width:354px;overflow:hidden;">
				<label for="thumbnail_file">상품 썸네일 이미지:</label>
				<p>
				<a href="#100" id="thumbnail_file_btn" style="display:block;border:2px solid #dedede;width:354px;height:354px;overflow:hidden;">
			      <img src="${pageContext.servletContext.contextPath}${itemVo.thumbnail}" width="350px;" alt="이미지" id="thumbnail_file_img" />
			    </a>
				<input type="file" name="thumbnailFile" id="thumbnail_file" style="display:none;" />
				</p>
		    </div>
		    
		    
		    <div class="item_write_right">
			<div class="form-group">
				<label for="category">상품 카테고리 선택</label>
				<select name="categoryNo" class="form-control" id="category">
					<option value="-1">카테고리를 선택해 주세요</option>
					<c:forEach items="${categoryList}" var="cdata">
						<option value="${cdata.no}" <c:if test="${cdata.no eq itemVo.categoryNo}">selected</c:if>>${cdata.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="form-group">
			  <label for="item_name">상품명:</label>
			  <input type="text" name="name" class="form-control" id="item_name" placeholder="상품명을 입력해 주세요." value="${itemVo.name}" />
			</div>
			
			<div class="form-group">
			  <label for="item_money">상품가격:</label>
			  <input type="text" name="money" class="form-control" id="item_money" placeholder="상품가격을 입력해 주세요." value="${itemVo.money}" />
			</div>
			</div>
			
			
		</div>
	</div>
			
   	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
			  <label for="comment">상세설명:</label>
			  <textarea name="description" class="form-control" rows="5" id="description">${itemVo.description}</textarea>
				<script>
					CKEDITOR.replace('description', {
						width : '100%',
						height : '300px'
					});
				</script>
			</div>
			
			<button type="submit" class="btn btn-success mb-3" style="width:100%;">상품 수정하기</button>
			
			
			
			
		
		</div>
	</div>
</form>


</div>




<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
