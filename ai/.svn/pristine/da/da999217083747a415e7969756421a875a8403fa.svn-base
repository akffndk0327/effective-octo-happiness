<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 7.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script> 
<style>
   .note-btn{
      margin: 0;
   }
   
  .name {
    margin: 0px 0 20px;
    border-top: 5px solid #2F4F4F;
    display: inline-block;
	}
  #allhead{
  	margin-left : 17%;
  }
  #alltable{
  	margin-left : 7%;
  	margin-top: 15px;
  }
  .alltd{
  	padding:3px;
  }
</style>
<form method=post>

<c:if test="${not empty allergy.allName }">
	<input type="hidden" name="allId" value="${allergy.allId }" >
	<h2 class="name" id="allhead">${allergy.allName }</h2>
</c:if>
	<section id="blog">
		<div class="container">
			<c:if test="${empty allergy.allName }">
			<table id="alltable">
			<tr>
				<th>* 알레르기명 : </th>
				<td class="alltd"><input type="text" name="allName"></td>
			</tr>
			<tr>
				<th>* 알레르기코드 : </th>
				<td class="alltd"> <input type="text" name="allId"></td>
			</tr>
			<tr>
				<th>* 알레르기타입 : </th>
				<td class="alltd"> <input type="text" name="allType"></td>
			</tr>
			</table>
<!-- 				<h3>알레르기명 : </h3> -->
<!-- 				<h3>알레르기코드 : <input type="text" name="allId"></h3> -->
<!-- 				<h3>알레르기타입 : <input type="text" name="allType"></h3> -->
			</c:if>
			<div class="row">
				<div class="col-md-9 clearfix">
					<ul class="blog-zone">
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil-alt"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2 class="name">원인</h2>
						            </a>
					            	<div class="blog-post-tag">
						            	<div class="block">
						            		<i class="fa fa-clock-o"></i>
						            		<p>2019/11/28</p>
						            	</div>
						            	<div class="block">
						            		<i class="fa fa-user"></i>
						            		<p>Admin</p>
						            	</div>
						            </div>
						            	<textarea class="summernote" name="allCause">
						            	${allergy.allCause }
						            	</textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-allergies"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2 class="name">증상</h2>
						            </a>
					            	<div class="blog-post-tag">
						            	<div class="block">
						            		<i class="fa fa-clock-o"></i>
						            		<p>2019/11/28</p>
						            	</div>
						            	<div class="block">
						            		<i class="fa fa-user"></i>
						            		<p>Admin</p>
						            	</div>
						            </div>
						            <textarea class="summernote" name="allSymptom">
						            ${allergy.allSymptom }
						            </textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-university"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2 class="name">예방법</h2>
						            </a>
					            	<div class="blog-post-tag">
						            	<div class="block">
						            		<i class="fa fa-clock-o"></i>
						            		<p>2019/11/28</p>
						            	</div>
						            	<div class="block">
						            		<i class="fa fa-user"></i>
						            		<p>Admin</p>
						            	</div>
						            </div>
						            <textarea class="summernote" name="allPrevent">
						            ${allergy.allPrevent }
						            </textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa fa-walking"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2 class="name">치료법</h2>
						            </a>
					            	<div class="blog-post-tag">
						            	<div class="block">
						            		<i class="fa fa-clock-o"></i>
						            		<p>2019/11/28</p>
						            	</div>
						            	<div class="block">
						            		<i class="fa fa-user"></i>
						            		<p>Admin</p>
						            	</div>
						            </div>
						            <textarea class="summernote" name="allTherapy">
						            ${allergy.allTherapy }
						            </textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
		<input type="submit" class="btn btnAi0" value="저장" />
	</ul>	<!-- End of /.blog-zone -->
		</div>	<!-- End of /.container -->
	</div>
	
	</section>	<!-- End of /#Blog -->
</form>
<script>
	$('.summernote').summernote({
 	   height: 400,                 // set editor height
 	   minHeight: null,             // set minimum height of editor
 	   maxHeight: null,             // set maximum height of editor
 	   focus: true                  // set focus to editable area after initializing summernote
    
	});

</script>