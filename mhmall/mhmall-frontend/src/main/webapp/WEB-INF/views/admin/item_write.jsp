<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>

<script src="${pageContext.request.contextPath}/assets/ckeditor/ckeditor.js"></script>
<style type="text/css">

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
	
	
});
</script>







<form action="${pageContext.request.contextPath}/admin/item/write" method="post" enctype="multipart/form-data">

<div class="container">
   	<div class="row">
		<div class="col-md-12">
		
			
			<div class="form-group mt-3 float-left" style="width:354px;overflow:hidden;">
				<label for="thumbnail_file">상품 썸네일 이미지:</label>
				<p>
				<a href="#100" id="thumbnail_file_btn" style="display:block;border:2px solid #dedede;width:354px;height:354px;overflow:hidden;">
			      <img src="${pageContext.servletContext.contextPath}/assets/images/x350.jpg" width="350px;" alt="이미지" id="thumbnail_file_img" />
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
						<option value="${cdata.no}">${cdata.name}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="form-group">
			  <label for="item_name">상품명:</label>
			  <input type="text" name="name" class="form-control" id="item_name" placeholder="상품명을 입력해 주세요." />
			</div>
			
			<div class="form-group">
			  <label for="item_money">상품가격:</label>
			  <input type="text" name="money" class="form-control" id="item_money" placeholder="상품가격을 입력해 주세요." />
			</div>
			</div>
			
			
		</div>
	</div>
			
   	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
			  <label for="comment">상세설명:</label>
			  <textarea name="description" class="form-control" rows="5" id="description"></textarea>
				<script>
					CKEDITOR.replace('description', {
						width : '100%',
						height : '300px'
					});
				</script>
			</div>
			
			<button type="submit" class="btn btn-success mb-3" style="width:100%;">상품 저장하기</button>
			
			
			
			
		
		</div>
	</div>
</div>

</form>



<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
