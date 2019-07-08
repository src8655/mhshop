<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

$(function(){

	$(".g_delete").click(function(){
		$("#hidden-no").val($(this).attr("data-no"));

		$('.dialog-bk').css("display", "block");
		$('#dialog-delete-form').css("display", "block");
	});
	
	$('.dialog-cancel').click(function(){
		$('.dialog-bk').css("display", "none");
		$('#dialog-delete-form').css("display", "none");
	});
});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1 class="guestbook_h">방명록</h1>
				<form id="add-form" action="${pageContext.servletContext.contextPath}/guestbook/timeline" method="post">
					<input type="text" id="input-name" placeholder="이름" name="name" />
					<input type="password" id="input-password" placeholder="비밀번호" name="password" />
					<textarea id="tx-content" placeholder="내용을 입력해 주세요." name="contents"></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">

					<c:forEach items="${list}" var="data">
						<li data-no="${data.no}">
							<div class="g_profile">
								<div></div>
								<a href='#100' data-no='${data.no}' class="g_delete">삭제</a> 
							</div>
							<div class="g_content">
								<h4>${data.name}</h4>
								<p>
									${data.contents}
								</p>
							</div>
						</li>
					</c:forEach>
				
				</ul>
			</div>
			<div class="dialog-bk" style="display:none;"></div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none;">
				<div class="dialog-h"><h5>메세지 삭제</h5><a href="#100" class="dialog-cancel">X</a></div>
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form action="${pageContext.servletContext.contextPath}/guestbook/timelinedelete" method="post">
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all" name="password" />
					<input type="hidden" id="hidden-no" value="" name="no" />
					<div>
						<input type="button" value="취소" class="dialog-cancel" />
						<input type="submit" value="삭제" />
					</div>
  				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>