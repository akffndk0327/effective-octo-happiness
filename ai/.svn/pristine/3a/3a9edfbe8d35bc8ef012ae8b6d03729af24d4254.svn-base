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
   
  h2 {
    margin: 0px 0 20px;
    border-top: 5px solid #2F4F4F;
    display: inline-block;
	}
 
  .test{
  	width : 160px;
  }
  
  .blog-post-tag .block p{
	font-size: 20px;
 }
</style> 
 
<section id="blog">
		<div class="container">
		<h2 style="margin-left: 130px;">${othersAllergies.allName }</h2>
			<div class="row">
				<div style="margin-left: 130px;" class="col-md-9 clearfix">
					<ul class="blog-zone">
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil-alt"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            <div class="blog-post-body clearfix">
					            	<a>
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
						            	<p>${othersAllergies.allCause }</p>
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
					            	<a>
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
						            <p>${othersAllergies.allSymptom }</p>
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
						            <a>
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
						            <p>${othersAllergies.allPrevent }</p>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-walking"></i>
					        </div>
					        <div class="blog-box">
					        	<img src="images/blog-1.jpg" alt="">
					            
					            <div class="blog-post-body clearfix">
									<a>
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
						            <p>${othersAllergies.allTherapy }</p>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>
					
	<sec:authorize url="/othersAllergies/othersAllergiesForm.do" access="hasRole('ROLE_ADMIN')">
		<c:url value="/othersAllergies/othersAllergiesForm.do" var="updateURL">
			<c:param name="allId" value="${othersAllergies.allId}"></c:param>
		</c:url>
		<input type="button" class="btn btnAi0" value="수정" onclick="location.href='${updateURL}';"/>
	</sec:authorize>
	</ul>	<!-- End of /.blog-zone -->
		</div>	<!-- End of /.container -->
	</div>
	
	</section>	<!-- End of /#Blog -->
<script>

</script>