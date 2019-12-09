<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 22.      이유진      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<script type="text/javascript" src="<c:url value="/js/sockjs.js"/>"></script>
<c:url value="/note/noteRecieve.do" var="recieveURL">
	<c:param name="memId" value="${loginId }" />
</c:url>
<c:url value="/note/noteSend.do" var="SendURL">
	<c:param name="memId" value="${loginId }" />
</c:url>

<div id="InnerContainer">
	<h2 class="titleTopBar">쪽지함</h2>
	<div class="titleLeftBar">${name }(${loginId })님의쪽지함</div>
	<input type="button" value="목록으로" class="btn btnAi0"
		onclick="location.href='${cPath}/note/noteRecieve.do?memId=${loginId }'">
	<input type="button" value="쪽지쓰기" class="btn btnAi2" id="newNote"
		data-toggle="modal" data-target="#noteModal" send-to=""
		style="float: right;">
	<table>
		<tr>
            <td style="vertical-align: top;">
                <div onclick="location.href='${recieveURL}';" style="width: 150px;">
                <img src="${cPath }/images/recieve.png" style="width: 50px"><br>
                <c:if test="${empty re }">
                <span style="font-size: 1.3em; font-weight: bold; padding: 5px" id="re">받은쪽지함</span>
                </c:if>
                <c:if test="${not empty re }">
                <span style="font-size: 1.3em; font-weight: bold; padding: 10px;background-color: #2f4f4f; color:white; border-radius: 20px;" id="re">받은쪽지함</span>
                </c:if>
                </div><br>
                <div onclick="location.href='${SendURL}';" style="width: 150px;">
                <img src="${cPath }/images/send.png" style="width: 50px"><br>
                <c:if test="${empty se }">
                <span style="font-size: 1.3em; font-weight: bold; padding:5px;" id="se">보낸쪽지함</span>
                </c:if>
                <c:if test="${not empty se }">
                <span style="font-size: 1.3em; font-weight: bold; padding: 10px;background-color: #2f4f4f; color:white; border-radius: 20px;" id="se">보낸쪽지함</span>
                </c:if>
                </div>
            </td>
			<td>
				<div class="table-responsive-vertical shadow-z-1"
					style="width: 1500px; margin: 40px;">
					<table class="table" style="width: 50%;">
						<!--                    수신 쪽지 -->
						<c:if test="${note.recieveId eq loginId }">
							<tbody>
								<tr>
									<td style="width: 20%">보낸 사람</td>
									<td>${note.sendId }</td>
								</tr>
								<tr>
									<td>받은 시간</td>
									<td>${note.sendDate }</td>
								</tr>
								<tr>
									<td colspan="2" style="height: 200px">${note.sendContent }</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2"><input type="button" value="답장"
										class="btn btnAi0" id="reply" name="${note.sendId }">
										<input type="button" value="삭제" class="btn btnAi0" id="delete"
										name="${note.noteNo }"></td>
								</tr>
							</tfoot>
						</c:if>

						<!--                        발신쪽지 -->
						<c:if test="${note.sendId eq loginId }">
							<tbody>
								<tr>
									<td style="width: 20%">받은 사람</td>
									<td>${note.recieveId }</td>
								</tr>
								<tr>
									<td>보낸 시간</td>
									<td>${note.sendDate }</td>
								</tr>
								<tr>
									<td colspan="2" style="height: 200px">${note.sendContent }</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2"><input type="button" value="삭제"
										class="btn btnAi0" id="delete" name="${note.noteNo }">
									</td>
								</tr>
							</tfoot>
						</c:if>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<br> <br> <br> <br>
</div>

<!-- 쪽지쓰는모달 -->
<form id="sendForm">
	<div class="modal fade" id="noteModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">쪽지 쓰기</h4>
				</div>
				<div class="modal-body" style="font-size: 25px;">
					<div class="form-group">
						<label for="recipient-name" class="control-label">받는 사람:</label><br>
						<select class="form-control"
							style="width: 20%; font-size: 17px;
							 display: inline-block;" id="sendWho">
							<option value="self">직접입력</option>
							<c:if
								test="${authorId eq 'ROLE_ADMIN' || authMember eq 'ROLE_EMP' }">
								<option value="giup">기업회원</option>
								<option value="member">일반회원</option>
								<c:if test="${authorId eq 'ROLE_ADMIN'}">
									<option value="all">전체회원</option>
								</c:if>
							</c:if>
						</select> <input type="text" class="form-control" id="recipient-name"
							style="width: 70%; display: inline-block; height: 20px; font-size: 20px;" required
							placeholder="여러명은 쉼표(,)로 구분">
					</div>
					<div class="form-group">
						<label for="message-text" class="control-label">내용:</label>
						<textarea class="form-control" id="message-text"
							style="width: 506px; height: 100px; font-size: 20px;" required placeholder="쪽지 내용을 입력해주세요"></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btnAi1" id="closeModal">닫기</button>
					<button type="submit" class="btn btnAi0" id="sendNote">보내기</button>
				</div>
			</div>
		</div>
	</div>
</form>

<script>
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var pageTag = $("[name='page']")

	pageTag.val("1");

	listBody.on("click", "tr", function() {
		let value = $(this).attr("id");
		location.href = "${cPath}/note/noteView.do?noteId=" + value;
	});

	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1)
			return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});

	//  쪽지 모달창을 닫기버튼 누르면?
	$('#closeModal').on("click", function() {

		Swal.fire({
			title : '쪽지 작성을 취소하시겠습니까?',
			text : "작성 창을 닫으시면 내용은 저장되지 않습니다",
			icon : 'warning',
			showCancelButton : true,
			focusConfirm : false,
			confirmButtonColor : '#90c322',
			cancelButtonColor : '#90c322',
			confirmButtonText : '네',
			cancelButtonText : '아니오'
		}).then(function(result) {
			if (result.value) {
				$('#noteModal').modal('hide');
				$('#recipient-name').val("");
				$('#message-text').val("");
				$('#recipient-name').attr("readonly", false);
			}
		});
	})

	$("#sendWho").on("change", function() {
		let val = $(this).val();
		if (val != 'self') {
			$('#recipient-name').attr("disabled", true)
		} else {
			$('#recipient-name').attr("disabled", false)
		}

	});

	//폼이 submit되면
	$('#sendForm').on(
			"submit",
			function() {
				event.preventDefault();
				let id = $('#recipient-name').val().trim();
				let text = $('#message-text').val().replace(/\n/g, "<br>");
				let selected = $("#sendWho option:selected").val();
				let login = "${loginId}";
				if (id == "")
					id = "noId"

				$.ajax({
					url : "${cPath}/note/sendingNote.do",
					method : "post",
					data : {
						"id" : id,
						"text" : text,
						"selected" : selected,
						"login" : login
					},
					dataType : "json",
					success : function(resp) {
						resp = resp.toString();
						resp = resp.split(",");
						if (resp[0] == 'ok') {
							if (selected != 'self') {
								Swal.fire({
									title : '쪽지 발송 완료',
									text : resp[1] + "명에게 단체 쪽지가 발송 되었습니다",
									icon : 'success',
									showCancelButton : false,
									focusConfirm : false,
									confirmButtonColor : '#90c322',
									confirmButtonText : '확인'
								}).then(function(result) {
									if (result.value) {
										$('#noteModal').modal('hide');
									}
								})
							} else {
								Swal.fire({
									title : '쪽지 발송 완료',
									text : id + "님에게  쪽지가 발송 되었습니다",
									icon : 'success',
									showCancelButton : false,
									focusConfirm : false,
									confirmButtonColor : '#90c322',
									confirmButtonText : '확인'
								}).then(function(result) {
									if (result.value) {
										$('#noteModal').modal('hide');
									}
								})
							}
							resp.splice(0, 2);
							var reciver;
							for (var i = 0; i < resp.length; i++) {
								sock.send(resp[i]);
							}
							$('#recipient-name').val("");
							$('#message-text').val("");
							$('#recipient-name').attr("readonly", false);
						}
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n"
								+ "message:" + request.responseText + "\n"
								+ "error:" + error);
					}
				});//ajax end

				return false;
			})

	//삭제버튼 누르면
	$('#delete').on(
			"click",
			function() {
				var id = $(this).attr("name");
				$.ajax({
					url : "${cPath}/note/noteDelete.do",
					method : "get",
					data : {
						"noteNo" : id
					},
					dataType : "text",
					success : function(resp) {
						if (resp == "OK") {
							Swal.fire({
								title : '쪽지 삭제 완료',
								text : "확인을 누르면 목록으로 돌아갑니다",
								icon : 'success',
								showCancelButton : false,
								focusConfirm : false,
								confirmButtonColor : '#90c322',
								confirmButtonText : '확인'
							}).then(function(result) {
								if (result.value) {
									history.back();
								}
							})
						}
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n"
								+ "message:" + request.responseText + "\n"
								+ "error:" + error);
					}
				});
			})

	$("#reply").on("click", function() {
		var name = $(this).attr('name');
		$('#recipient-name').val(name);
		$('#recipient-name').attr("readonly", true);
		$('#noteModal').modal('show');
	})
</script>