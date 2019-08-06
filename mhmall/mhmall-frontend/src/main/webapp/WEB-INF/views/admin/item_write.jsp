<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:import url="/WEB-INF/views/admin/include/header.jsp"></c:import>



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








<div class="container">
   	<div class="row">
		<div class="col-md-12">
		
		
		
		
		
			<form action="" method="post" enctype="multipart/form-data">
			
			
			<div class="form-group mt-3">
				<label for="thumbnail_file">상품 썸네일 이미지:</label>
				<p>
				<a href="#100" id="thumbnail_file_btn" style="display:block;border:2px solid #dedede;width:354px;height:354px;overflow:hidden;">
			      <img src="${pageContext.servletContext.contextPath}/assets/images/x350.jpg" width="350px;" alt="이미지" id="thumbnail_file_img" />
			    </a>
				<input type="file" name="thumbnail_file" id="thumbnail_file" style="display:none;" />
				</p>
		    </div>
			
			<div class="form-group">
				<label for="category">상품 카테고리 선택</label>
				<select class="form-control" id="category">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
			
			<div class="form-group">
			  <label for="item_name">상품명:</label>
			  <input type="text" class="form-control" id="item_name" placeholder="상품명을 입력해 주세요." />
			</div>
			
			<div class="form-group">
			  <label for="item_money">상품가격:</label>
			  <input type="text" class="form-control" id="item_money" placeholder="상품가격을 입력해 주세요." />
			</div>
			
			<div class="form-group">
			  <label for="comment">상세설명:</label>
			  <textarea class="form-control" rows="5" id="comment"></textarea>
			</div>
			
			<button type="submit" class="btn btn-success" style="width:100%;">상품 저장하기</button>
			</form>
			
			
			
			
		
		</div>
	</div>
</div>



<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
