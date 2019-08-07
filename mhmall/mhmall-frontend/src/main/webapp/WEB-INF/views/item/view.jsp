<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="/WEB-INF/views/include/header.jsp"></c:import>



<!-- Page Content -->
  <div class="container">

    <div class="row">
	  
	  <c:import url="/WEB-INF/views/include/nav.jsp"></c:import>









      <div class="col-lg-9">
      
      
      
      
      
        <div class="mt-4 row">
          <div class="col-md-6">
            <img class="card-img-top img-fluid" src="http://localhost:8080/mhmall-frontend/images/20197785633683.jpg" width="100%" style="border:2px solid #eeeeee;" alt="">
          </div>
          <div class="col-md-6 card-body">
            <h4 class="card-title">Product Name</h4>
            <h4>25,000원</h4>
            
            
            <div class="row">
    				  <div class="col-sm-12 mb-2">
    				  	<label for="option1">1차 옵션</label>
    				    <select name="optionDetailNo1" class="form-control" id="option1">
      						<option value="-1">선택</option>
      						<option value="-1">선택</option>
      						<option value="-1">선택</option>
      					</select>
    				  </div>
    				  <div class="col-sm-12 mb-2">
    				  	<label for="option2">2차 옵션</label>
    				    <select name="optionDetailNo2" class="form-control" id="option2">
      						<option value="-1">선택</option>
      						<option value="-1">선택</option>
      						<option value="-1">선택</option>
      					</select>
    				  </div>
    				  <div class="col-sm-6 mb-2">
    				    <button type="button" class="btn btn-danger" style="width:100%;">장바구니추가</button>
    				  </div>
    				  <div class="col-sm-6 mb-2">
    				    <button type="button" class="btn btn-primary" style="width:100%;">바로구매</button>
    				  </div>
				    </div>
            
            
            
          </div>
        </div>
        <!-- /.card -->

        <div class="card card-outline-secondary my-4">
          <div class="card-header">
            Product Reviews
          </div>
          <div class="card-body">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <a href="#" class="btn btn-success">Leave a Review</a>
          </div>
        </div>
      
      
      
      
      
      
      
      
      </div>
      
      
      
      
    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->



<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
