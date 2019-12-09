<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자     	  수정내용
* -------------  ---------  -----------------
* 2019. 11. 7.    박주연           최초작성
* 2019. 11. 28.	   최서희	        알레르기 상세보기 값 가져오기
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 <style>
  h2 {
    margin: 0px 0 20px;
    border-top: 5px solid #2F4F4F;
    display: inline-block;
	}
 #golist{
 	margin-left: 8%;
 }
 
 .blog-post-tag .block p{
 	font-size: 20px;
 }
 </style>
<div id="InnerContainer">
<h2 style="margin-left: 100px;">${allergy.allName }</h2>
<input type="hidden" name="${allergy.allId }">
	<section id="blog">
		<div class="container">
			<div class="row">
				<div style="margin-left: 100px; class="col-md-9 clearfix">
					<ul class="blog-zone">
					    <li>
					        <div class="blog-icon">
					        	<i class="fa  fa-pencil-alt"></i>
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
						            <p>${allergy.allCause }</p>
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
						            <p>${allergy.allSymptom }</p>
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
						            <p>${allergy.allPrevent }</p>
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
						            <p>${allergy.allTherapy }</p>
					            </div>
					        </div>	<!-- End of /.blog-box -->
					    </li>

	<sec:authorize url="/allergy/allergyUpdate.do" access="hasRole('ROLE_ADMIN')">
		<c:url value="/allergy/allergyUpdate.do" var="updateURL">
			<c:param name="what" value="${allergy.allId}"></c:param>
		</c:url>
		<c:url value="/allergy/allergyDelete.do" var="deleteURL">
			<c:param name="what" value="${allergy.allId}"></c:param>
		</c:url>
		<button id="golist" class="btn btnAi2" type="button" onclick="location.href='${cPath}/allergy/allergyList.do';">목록</button>
		<c:if test="${loginId eq 'admin' }">
			<input type="button" class="btn btnAi0" value="수정" onclick="location.href='${updateURL}';" />
			<input type="button" class="btn btnAi1" value="삭제" data-toggle="modal"
			data-target="#exampleModal" />
		</c:if>
	</sec:authorize>
	</ul>	<!-- End of /.blog-zone -->
		</div>	<!-- End of /.container -->
	</div>
	
	</section>	<!-- End of /#Blog -->	


</div>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">삭제확인 창</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="font-size: 20px;">정말 삭제하시겠습니까?</div>
			<form action="${cPath}/uneatable/uneatableDelete.do" method="post">
				<div class="modal-footer">
					<input name="uneatId" type="hidden" value="${uneatAble.uneatId }" />
					<button type="button" class="btn btn btnAi1" data-dismiss="modal">닫기</button>
        			<button  type="button" class="btn btnAi0" id="allDel">삭제</button>
				</div>
			</form>
		</div>
	</div>
</div>


<c:if test="${not empty fail }">
<script>
   $(document).ready(function() {
      Swal.fire({
           icon: 'error',
           title: 'Oops...',
           text: '서버오류'
         })
   });
   </script>
</c:if>

<c:if test="${not empty success }">
   <script>
   $(document).ready(function(){
      
   Swal.fire({
        position: 'center',
        icon: 'success',
        title: '등록되셨습니다',
        showConfirmButton: false,
        timer: 1500
      })
   });
   </script>
</c:if>
 <script>
 var allDel = $("#allDel");
 allDel.on("click",function(){
	$.ajax({
		url : "${cPath}/allergy/allergyDelete.do",
		method : "get",
		data : {
			"what" :"${allergy.allId}"
		},
		dataType : "json",
		success : function(resp) {
			Swal.fire({
//                 title: '삭제',
                text: "삭제되었습니다.",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#90c322',
                confirmButtonText: '확인'
              }).then((result) => {
                  if (result.value) {
					location.href = "${cPath}/allergy/allergyList.do";
                 }
             });
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	})
	 
 })
	
</script>