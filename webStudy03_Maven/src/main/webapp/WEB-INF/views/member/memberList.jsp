<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"   href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<!-- <script type="text/javascript"   src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script type="text/javascript"   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"   src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>주소</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id ="listBody">
	</tbody>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">상세조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	$('#exampleModal').on('hidden.bs.modal',function(){
		$(this).find("form")[0].reset();
		});
	$('#deleteBtn').on('click',function(){
		$('#exampleModal').modal('show');
		
	})

</script>
<form id="listForm" action="${pageContext.request.contextPath }/member/memberView.do">
	<input type="hidden" name="who"/> 
</form>
<%-- ui는 동기방식, 실제 데이터는 비동기 방식으로  --%>
<script type="text/javascript">
	 var listBody = $("#listBody");
	 var listForm = $("#listForm");
	 var exampleModal = $("#exampleModal");
	 exampleModal.on("hidden.bs.modal",function(){
		 $(this).find(".modal-body").html("");
	 })
	 listForm.on("submit",function(){
		 let action =$(this).attr("action");
		 let method =$(this).attr("method");
		 let queryString = $(this).serialize(); // data : input 태그가 몇개이고 그런거에 대해 달라짐 - form안에있는거에 따라 달ㄹ ㅏ
		 $.ajax({
			url : action,
			method : method?method:"get",
			data : queryString, //요청을 어디로 어떻게 ? -form 태그에 따라 달라진짜 ! 
			dataType : "html", //비동기 요청안에 일부분의 ui 포함됨.
			success : function(resp) {
				exampleModal.find(".modal-body").html(resp);
				exampleModal.modal("show");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		})
		 return false;
	 })
	 
	 
	$.ajax({
// 		data : "",
		dataType : "json",
		success : function(resp) { //응답데이터로 돌아온 json을 언마샬링하고 복원한거 
			let trTags = [];
			$(resp).each(function(index, member) {
				let trTag=$("<tr>").append(
					$('<td>').text(member.mem_id),	
					$('<td>').text(member.mem_name),	
					$('<td>').text(member.mem_hp),	
					$('<td>').text(member.mem_mail),
					$('<td>').text(member.mem_add1),
					$('<td>').text(member.mem_mileage)
				).prop("id", member.mem_id);
				trTags.push(trTag);
			})
			listBody.html(trTags);
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}

	})
	listBody.on("click","tr",function(){ //한명의 id 클릭하면 콘솔에 정보가 찍혀 
		let who = $(this).prop("id");
		listForm.find("[name=who]").val(who);
		listForm.submit(); //테이터 넘어감 
	})
</script>
</body>
</html>








