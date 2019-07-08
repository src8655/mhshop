<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%pageContext.setAttribute( "newLine", "\n" );%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/lightbox.css" rel="stylesheet" type="text/css">
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/lightbox.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(function(){

	$("#upload-image").click(function(){
		$("#hidden-no").val($(this).attr("data-no"));

		$('.dialog-bk').css("display", "block");
		$('#dialog-upload-form').css("display", "block");
	});
	
	$('.dialog-cancel').click(function(){
		$('.dialog-bk').css("display", "none");
		$('#dialog-upload-form').css("display", "none");
	});
});	
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			
			
						<div id="gallery">
				<div class="gallery_header">
					<h1>갤러리</h1>
					<a href="#100" id="upload-image">이미지 올리기</a>
				</div>
				<ul>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im1.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im1.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im2.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im2.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im3.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im3.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im4.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im4.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im5.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im5.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im6.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im6.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im7.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im7.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im8.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im8.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im9.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im9.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im10.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im10.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im11.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im11.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im12.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im12.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im13.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im13.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
						
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im14.jpg'"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im14.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/2"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						<li>
							<a	href="${pageContext.request.contextPath}/assets/gallery-examples/im15.jpg"
								data-lightbox="gallery"
								class="image"
								style="background-image:url('${pageContext.request.contextPath}/assets/gallery-examples/im15.jpg')">&nbsp;</a>
								
							<a	href="./gallery/delete/1"
								class="del-button"
								title="삭제">삭제</a>
						</li>
						
																																									
				</ul>	
			</div>

			<div class="dialog-bk" style="display:none;"></div>
			<div id="dialog-upload-form" title="이미지 업로드" style="display:none;">
				<div class="dialog-h"><h5>이미지 업로드</h5><a href="#100" class="dialog-cancel">X</a></div>
  				<p class="validateTips normal">이미지와 간단한 코멘트를 입력해 주세요.</p>
  				<form action="${pageContext.request.contextPath}/gallery/upload" 
  					  method="post"
  					  enctype="multipart/form-data">
					<label>코멘트</label>
					<input type="text" id="input-comments" name="comments" value="" />
					<label>이미지</label>
					<input type="file" id="input-file" name="file" />
					<div>
						<input type="button" value="취소" class="dialog-cancel" />
						<input type="submit" value="업로드" />
					</div>
  				</form>
			</div>
			
			
			
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>