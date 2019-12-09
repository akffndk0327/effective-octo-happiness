<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                	 수정자     		 수정내용
* ----------  ---------  -----------------
* 2019. 11. 21.      박주연      최초작성 글 등록 폼 
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
th {
	font-size: 19px;
	border-right: 1px solid #ddd;
}

input {
	width: 300px;
}

.dsimg {
	width: 480px;
	height: 400px;
}

.btnSearch {
	width: 64px;
	height: 34px;
	background: #2F4F4F;
	color: white;
	padding: 0;
}
</style>
<form action="?" method="post" enctype="multipart/form-data">
	<input type="text" name="bioCheId" value="${dsprod.bioCheId }" hidden />
	<section id="single-product">
	<div id="InnerContainer">
		<div clsss="container">
			<div class="row">
				<h3 class="titleTopBar">생활용품 등록</h3>
				<table class="table">
					<tbody>
						<tr>
							<th>제품명</th>
							<td><input type="text" name="bioCheName"
								value="${dsprod.bioCheName }" /></td>
						</tr>
						<tr>
							<th>제품분류/용도/중량</th>
							<td><input name="bioCheCont" type="text"
								value="${dsprod.bioCheCont}" /></td>
						</tr>

						<tr>
							<th>제조업체</th>
							<td><input name="bioCheCom" type="text"
								value="${dsprod.bioCheCom}" /></td>
						</tr>
						<tr>
							<th>제조업체 주소</th>
							<td><input name="bioCheComAddr" type="text"
								value="${dsprod.bioCheComAddr}" /></td>
						</tr>
						<tr>
							<th>제품 이미지</th>
							<td>
								<!-- 이미지  --> <!-- <img class="dsimg" name="bioCheImg" --> <%--src="data:image/*;base64,${dsprod.bio_imgBase64 }" alt=""> --%>
								<input type="file" name="bioImg" />
							</td>
						</tr>
						<tr>
							<th>주 의 사 항</th>
							<td><textarea rows="10" cols="100" name="bioChePreca"></textarea>
							</td>
						</tr>
						<tr>
							<th>유해 물질 등록</th>
								<td id="plusTd" colspan="2"> 
								<input type="text" name="biocheList[0].casId" class="casId" />
									<input type="button" class="btn btnSearch btnGreen" value="검색" />
								<img src="${cPath }/images/plus.png" id="btnPlus">
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 				저장 취소버튼   -->
				<div>
					<input id="ok" type="submit" class="btn btnAi0" value="저장" /> 
					<input id="cancle" type="button" class="btn btnAi1" value="취소"
						data-toggle="modal" data-target="#myModal2" />
				</div>
			</div>
		</div>
	</div>
	</section>
</form>


<!-- 검색 모달  -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">화학성분 검색</h4>
			</div>
			<div class="modal-body">
				<input type="text" id="inputTag" /> <input type="button" value="검색"
					class="btn btnAi0" id="modalsearch" />

				<hr color="#ddd">
				<table id="modalTable">
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btnAi0" id="btnClose"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- 취소 모달  -->
<!-- Modal -->
<div class="modal fade" id="myModal2" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">취소하시겠습니까?</h4>
			</div>
			<div class="modal-body">
				<h6>확인을 누르면 작성 내용이 사라지고 목록 화면으로 이동합니다.</h6>
				<h6>취소를 누르면 현재 화면에 남아있습니다.</h6>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btnAi1" data-dismiss="modal"
					value="취소" /> <input type="button" class="btn btnAi0"
					data-dismiss="modal" id="modalOk" value="확인"
					onclick="location.href='<c:url value="/dailysupply/dsList.do"/>';" />
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
var plusTd = $("#plusTd");
var btnPlus = $("#btnPlus");
var btnClose = $("#btnClose");
var btnEnter = $("#btnEnter");
var inputTag = $("#inputTag");

var modalTable = $("#modalTable");
//플러스 버튼 index + 하는거 memForm 알러지 추가 참고 
var index = 1;
$(btnPlus).on("click", function(){
	let tdTag = $(this).closest("td");
	tdTags = "<div style='margin-top: 2px; margin-bottom: 3px;' class='test'>";
	tdTags += "<input type='text' name='biocheList["+(index++)+"].casId' class='casId'/>";
	tdTags += "<input type='button' class='btn btnSearch btnGreen' value='검색' style='margin-left: 6px;' /> ";
	tdTags += "<img src='${cPath }/images/minus.png' class='btnMinus' style='width:32px ; height:32px;'>";
	tdTags += "</div>";
	tdTag.append(tdTags);


// 	//오리지널 세트의 갯수를 알아야함
// 	var index = $(".test").length;
// 	//가져온 html을 넣기전에 그 html의 인덱스를 바꿔줘야함
// 	//바꿀 인덱스를 갯수만큼 바꿔줘야함
// 	var newContent = tdTags.replace(/biocheList\[\d\]/gm,"biocheList["+index+"]");
// 	//바로 위 부모
// 	var allergy = $(parent).parents('tr:first');
// 	console.log(allergy);	
// 	allergy.append(newContent);
})

//마이너스 버튼
$(plusTd).on("click", ".btnMinus", function(){
	$(this).parent("div").remove();
});

//검색 버튼
var temp;
$(plusTd).on("click", ".btnSearch", function(){
	temp = $(this);
    $("#myModal").modal();
    modalTable.empty();
    
})

//검색 모달창에서 다른데 클릭하면
$(inputTag).on("blur", function(){
	var k = $(this).val();
	$.ajax({
		url : "${cPath}/chemical/chemicalList.do", //검색햇을때 화학물질 리스트 출력 
		method :"get",
		data : {
				"name" : k
			},
		dataType : "json",
		success : function(resp) {
			let tags = [];
			$(resp).each(function(i, v){
				let tag = $("<tr>").append(
						$("<td>").text(v.cheNameKo)
				).prop("id", v.casId)
			tags.push(tag);
			});	
			$(inputTag).val("");
			
			modalTable.html(tags);
			
		},
		error : function(xhr) {
			console.log(xhr.status);
		}
	});
})

//모달창 취소버튼 클릭시 내용 비우기
$(btnClose).on("click", function(){
	$(inputTag).val("");
})

//모달창 검색결과 선택
$(modalTable).on("click", "tr", function(){
	//아이디 저장
	let what = $(this).prop("id");
	temp.prev().val(what);
	//이름 표시
	let ko = $(this).children().first().text();
	temp.prev().prev().val();

	$("#myModal").modal('hide');
});


$("#myModal2").on('click',function(){
	show();
})


	
</script>
