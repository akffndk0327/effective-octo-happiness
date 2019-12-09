<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 7.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
th {
	width: 20%;
	background: #e6e6e6;
}

.adtable {
	width: 70%;
	margin-left: 15%;
	font-size: 21px;
}
</style>
<div class="InnerContainer">
	<h3>${advertise.memId}님의광고신청</h3>
<form action="${cPath }/dailysupply/dsList.do" id="imageForm" method="get" enctype="multipart/form-data">
	<table class="table adtable table-condensed">
		<thead>
		</thead>
		<tbody>
			<tr>
				<td>번호</td>
				<td class="adId">${advertise.adId }</td>
				<input type="hidden" name="adId" value="${advertise.adId}"/>
			</tr>
			<tr>
				<th>제목</th>
				<td><span class="Bold">${advertise.adTitle }<span></td>
			</tr>
			<tr>
				<th>성 명</th>
				<td>${advertise.memId }</td>
			</tr>
			<tr>
				<th>승인상태</th>
				<td>${advertise.adApprove }</td>
			</tr>
			<tr>
				<th>광고 시작 날짜</th>
				<td>${advertise.adIndate }</td>
			</tr>
			<tr>
				<th>광고 종료 날짜</th>
				<td>${advertise.adTerm}</td>
			</tr>
			<tr>
				<th>이동 URL</th>
				<td class="url">${advertise.adLink }</td>
				<input type="hidden" name="adLink" value="${advertise.adLink }"/>
			</tr>
			<tr>
				<th>게시판</th>
				<td id="board" value="${advertise.adhit.adposition.resource.resName }">${advertise.adhit.adposition.resource.resName }</td>
				<input type="hidden" name="resId" value="${advertise.adhit.resId }"/>
			</tr>
			<tr>
				<th>게시 위치</th>
				<td id="position" value="${advertise.adhit.adposition.adpoName}">${advertise.adhit.adposition.adpoName }</td>
				<input type="hidden" name="adpoId" value="${advertise.adhit.adpoId }"/>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td style="min-width: 100px; min-height: 200px" class="att"><c:if
						test="${not empty advertise.adattatchList }">
						<c:forEach items="${advertise.adattatchList}" var="attatch">
							<img src="${cPath }/advertise/addownload.do?what=${attatch.adattId}" style="max-width:120px; max-height:400px">
						<input type="hidden" name="adattSavename" value="${attatch.adattSavename}"/>
						</c:forEach>
					</c:if>
				</td>
				
			</tr>
			<tr>
				<th>반려사유</th>
				<td>
				<c:if test="${advertise.adApprove eq '대기' }" >
				<select id="adUnapprove">
						<option value="0">선택하세요</option>
						<option value="1" ${"1" eq advertise.adRep ? "selected":"" }>부적절한 광고 내용입니다.</option>
						<option value="2">우리 페이지의 컨텐츠와 맞지않습니다.</option>
						<option value="3" >유효하지 않은 url주소입니다.</option>
						<option value="4">이미지의 크기가 적절하지 않습니다.</option>
						<option value="5">부적절한 게시 위치 입니다.</option>
						
				</select>
				</c:if>
				<c:if test="${advertise.adApprove eq '승인' }" >
				<select id="adUnapprove" disabled>
						<option value="0">선택하세요</option>
						<option value="1" ${"1" eq advertise.adRep ? "selected":"" }>부적절한 광고 내용입니다.</option>
						<option value="2">우리 페이지의 컨텐츠와 맞지않습니다.</option>
						<option value="3" >유효하지 않은 url주소입니다.</option>
						<option value="4">이미지의 크기가 적절하지 않습니다.</option>
						<option value="5">부적절한 게시 위치 입니다.</option>
						
				</select>
				</c:if>
				<c:if test="${advertise.adApprove eq '반려' }" >
					${advertise.adRep }
				</c:if>
				</td>
				
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
				<c:if test="${advertise.adApprove eq '대기'}" >
					<input type="submit" class="btn btnAi0"	value="승인" id="approve"> 
					<input type="button" class="btn btnAi1" value="반려" id="cancle">
				</c:if>
				<c:if test="${advertise.adApprove ne '대기'}" >
					<input type="button" class="btn btnAi0"	value="수정" id="adupdate"> 
				</c:if>
				
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</div>
<script>
// 승인
	var approve = $("#approve");
	var cancle = $("#cancle"); //반려 버튼   
	var adreply = $("#adreply");
	var box = $("#adUnapprove option:selected").val();
	var adId = "${advertise.adId }";
	//반려사유 안쓰고 반려 눌럿을때  선택하게 하고 
	//선택하고 submit하기
	cancle.on("click",function(){
		//셀렉트박스 선택됏나 확인
		if($("#adUnapprove option:selected").val() != 0) {
			$.ajax({
				url : "${cPath}/advertise/adUpdate.do",
				method : 'post',
				data : 
					{
					"selected" : $("#adUnapprove option:selected").val(),
					"adId" : adId,
					"adRep" : $("#adUnapprove option:selected").text()
					},
				dataType : "text",
				success : function(resp) {
					if(resp=="OK"){
						Swal.fire({
		                       title: '반려  완료',
		                       text: "광고신청이 반려 되었습니다",
		                       icon: 'success',
		                       showCancelButton: false,
		                       confirmButtonColor: '#90c322',
		                       confirmButtonText: '확인'
		                     }).then((result) => {
		                        if (result.value) {
		                           window.location.reload();
		                         }
		                    });
						
					}
				},
				 error:function(request,status,error){
			        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			})
		}else{
	        alert("반려사유를 선택하세요");
		}
		box.change()
	});
	
	//승인 버튼 눌럿을때 
	approve.on("click",function(){
		event.preventDefault();
		//셀렉트박스 선택되었나 확인 
		var url = $(".url").text();
		var position = $("#position").val();
		var board = $("#board").val();
		var adimg = $("#adimg").attr("src");
		var adId = $(".adId").text();
		if($("#adUnapprove option:selected").val() != 0){
			alert("반려사유를 선택하지 마세요");
		}
		else{
			$.ajax({
				url : "${cPath}/advertise/adUpdate.do",
				method : 'post',
				data : 
					{
					"selected" : $("#adUnapprove option:selected").val(),
					"adId" : adId
					},
				dataType : "text",
				success : function(resp) {
			       	var queryString = $("#imageForm").serialize();
			       $("#imageForm").submit();
		// 				$.ajax({
		// 					url : "${cPath}/dailysupply/dsTest.do",
		// 					method : "post",
		// 					data : {
								
		// 					},
		// 					success : function(resp) {
		// 						alert("성공");
		// 					},
		// 					error : function(xhr) {
		// 						console.log(xhr.status);
		// 					}
		// 				});
			  
		                
// 					alert(box.val().remove());
				},
				 error:function(request,status,error){
			        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			})
		}
		
	})
</script>