<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!-- summernote editor -->
 <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
<style>
	.note-btn{
		margin: 0;
	}
	th{
		width: 50px;
	}
</style>
<div id="demo">
	<div id="InnerContainer">
	<h2 class="titleTopBar">News Writing</h2>
	<br>
	<form method="post">
	  <table id="table" class="table table-hover table-mc-light-blue">
	     <thead>
	        <tr>
	           <th>제목 : </th>
			   <td><input type="text" value="${news.newsTitle }" style="width: 500px;" name="newsTitle"/></td>
	        </tr>
	      </thead>
	      <tbody id="listBody"> 
			<tr>
				<th>내용 : </th>
				<td>
					<textarea  id="summernote" name="newsContent">
							${news.newsContent}
					</textarea>
				</td>
			</tr>
	     </tbody>
	     <tfoot>
	     	<tr>
	     		<td colspan="2">
	     		<c:if test="${news.newsNo ne null }">
					<input type="hidden" name="newsNo" value=${news.newsNo } />
	     		</c:if>
					<input type="submit" class="btn btnAi0" id="add" value="등록"></button>
					<input type="button" class="btn btnAi1" id="back" value="뒤로가기"></button>
	     		</td>
	     	</tr>
	     </tfoot>
	  </table>
	</form>
     </div>
</div>

<script type="text/javascript">

    $('#summernote').summernote({
            height: 400,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: true                  // set focus to editable area after initializing summernote
            
    });
    
    $("#back").on("click",function(){
    	history.back();
    })
    
//     $("#add").on("click",function(){
// 		var title = $("#title").val();
// 		var img = $("#img").val();
// 		var content = $("#content").val();
// 		$.ajax({
// 			url :,
// 			method : "post",
// 			data : {
// 				"title" : title,
// 				"img" : img,
// 				"content" : content
// 			},
// 			dataType : "json",
// 			success : function(resp) {
// 				alert("성공");
// 			},
// 			error : function(xhr) {
// 				console.log(xhr.status);
// 			}
// 		});
//     })  	
    


</script>
