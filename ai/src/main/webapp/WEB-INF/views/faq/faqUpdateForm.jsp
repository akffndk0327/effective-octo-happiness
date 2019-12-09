<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자 	      수정내용
* ------------  ---------  -----------------
* 2019. 11. 6.    최서희          최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
 <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>
 
<style>
#mainTable{
	width : 100%;
}
#mainTable td{
	border : 0px solid;
}
#mainTable th{
	border-bottom : 2px solid #ddd;
}
.title{
	margin-bottom : 15px;
}
.note-btn{
    margin: 0;
}
</style>
<div id="InnerContainer">
<h2 class="titleTopBar">FAQ Update</h2>
<form action="${cPath }/faq/faqUpdate.do" method="post">
<input type="hidden" value="${faq.faqNo }" name="faqNo" />
<table id="mainTable" class="table table-hover table-mc-light-blue">
	     <thead>
	        <tr>
	           <th><div class="title">제목 : </div></th>
			   <th>
			   <input type="text" style="width: 500px;" name="faqTitle" value="${faq.faqTitle }"/>
	           </th>
	        </tr>
	      </thead>
	      <tbody id="listBody"> 
			<tr>
				<th>내용 : </th>
				<td>
					<textarea  id="summernote" name="faqContent">
					${faq.faqContent }
					</textarea>
				</td>
			</tr>
	     </tbody>
	     <tfoot>
	     	<tr>
	     		<td colspan="2">
					<input type="button" class="btn btnAi0" id="back" value="취소"></button>
					<input type="submit" class="btn btnAi1" id="add" value="등록"></button>
	     		</td>
	     	</tr>
	     </tfoot>
	  </table>
</form>

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
    

</script>