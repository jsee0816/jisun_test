<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>
    	.addpadding{
    		padding-bottom:10px;
    	}
    	#sidebar{
    		float:left;
    	}
    	.clearboth{
    		/* clear:both; */
    	}
    </style>
    
<div id="sidebar" class="col-lg-2">
		<!-- 사이드 바 메뉴-->
		  <!-- 패널 타이틀1 -->
		<div class="panel panel-info ">
		    <div class="panel-heading">
		      <h4 class="panel-title title-color border-bottom addpadding">Product</h4>
		      
		    </div>
		    <!-- 사이드바 메뉴목록1 -->
		    <ul class="list-group">
		      <li class="list-group-item list-group-item-action border-0"><a href="<%=request.getContextPath()%>/partner/addProduct">상품 등록하기</a></li>
		      <li class="list-group-item list-group-item-action border-0"><a href="<%=request.getContextPath()%>/partner/partnerProductList">내 상품 보기</a></li>
		      <li class="list-group-item list-group-item-action border-0"><a href="#">ECMAScript5</a></li>
		    </ul>
		</div>
		  <!-- 패널 타이틀2 -->
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h4 class="panel-title addpadding">Panel Title</h4>
		  <hr>
		  </div>
		    <!-- 사이드바 메뉴목록2 -->
		      <ul class="list-group">
		        <li class="list-group-item border-0"><a href="#">jQuery</a></li>
		        <li class="list-group-item border-0"><a href="#">BootStrap</a></li>
		      </ul>
		</div>      
		  <!-- 패널 타이틀3 -->
		<div class="panel panel-info">
		  <div class="panel-heading">
		    <h4 class="panel-title">Panel Title</h4>
		  </div>
		      <!-- 사이드바 메뉴목록3 -->
		      <ul class="list-group">
		        <li class="list-group-item border-0"><a href="#">About</a></li>
		        <li class="list-group-item border-0"><a href="#">Help</a></li>
		      </ul>
		    </div>
		</div>
		<div class="clearboth"></div>