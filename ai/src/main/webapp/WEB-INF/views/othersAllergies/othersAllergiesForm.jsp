<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.      허민지      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script> 

<style>
   .note-btn{
      margin: 0;
   }
</style>
<form method=post>

<section id="topic-header">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h1>${othersAllergies.allName }</h1>
					<p>${othersAllergies.allType }</p>
				</div>	<!-- End of /.col-md-4 -->	
			</div>	<!-- End of /.row -->
		</div>	<!-- End of /.container -->
	</section>	<!-- End of /#Topic-header -->


	<section id="blog">
		<div class="container">
			<div class="row">
				<div class="col-md-9 clearfix">
					<ul class="blog-zone">
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2>원인</h2>
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
						            	${othersAllergies.allCause }
						            	</textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2>증상</h2>
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
						            ${othersAllergies.allSymptom }
						            </textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2>예방법</h2>
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
						            ${othersAllergies.allPrevent }
						            </textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
						            <a href="blog-single.html">
					            		<h2>치료법</h2>
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
						            ${othersAllergies.allTherapy }
						            </textarea>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>

	<sec:authorize url="/othersAllergies/othersAllergiesUpdate.do" access="hasRole('ROLE_ADMIN')">
<%-- 		<c:url value="/othersAllergies/othersAllergiesUpdate.do" var="updateURL"> --%>
<%-- 			<c:param name="allId" value="${othersAllergies.allId}"></c:param> --%>
<%-- 		</c:url> --%>
		<input type="submit" class="btn btnAi0" value="저장" />
	</sec:authorize>
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