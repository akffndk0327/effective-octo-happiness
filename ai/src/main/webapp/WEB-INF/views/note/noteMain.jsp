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
<style type="text/css">
 a:link { color: blue; text-decoration: none;}
/*  a:visited { color: gray; text-decoration: none;} */
 a:hover { text-decoration: underline;}
 
</style>
<script type="text/javascript" src="<c:url value="/js/sockjs.js"/>"></script>
<c:url value="/note/noteRecieve.do" var="recieveURL">
	<c:param name="memId" value="${loginId }" />
</c:url>
<c:url value="/note/noteSend.do" var="SendURL">
	<c:param name="memId" value="${loginId }" />
</c:url>

<div id="InnerContainer">
	<h2 class="titleTopBar">쪽지함</h2>
	<div class="titleLeftBar">${name }(${loginId })님의 쪽지함</div>
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
				<span style="font-size: 1.3em; font-weight: bold; padding: 10px;background-color: #2f4f4f; color:white;border-radius: 20px;" id="se">보낸쪽지함</span>
                </c:if>
				</div>
			</td>
			<td>
				<div class="table-responsive-vertical shadow-z-1"
					style="width: 1300px; margin: 40px;">
					<table class="table table-hover table-mc-light-blue"
						style="width: 70%;">
                        <c:if test="${empty recieveList.dataList[0] && empty sendList.dataList[0]}">
                                <h5 style="margin-left: 30%;">쪽지가 없습니다</h5>
                        </c:if>
						<c:if test="${not empty recieveList.dataList[0] }">
							<thead>
								<th style="width: 15%;">보낸사람</th>
								<th style="width: 60%;">내용</th>
								<th style="width: 20%;">날짜</th>
							</thead>
							<tbody id="listBody">
								<c:forEach items="${recieveList.dataList }" var="recieve">
									<tr id="${recieve.noteNo }">
										<td>${recieve.sendId }</td>
										<td>
										<c:if test="${recieve.readNo eq 'Y' }">
											<a style="color: gray; text-decoration: none;" href="${cPath}/note/noteView.do?noteId=${recieve.noteNo  }">
											  ${recieve.sendContent }
											</a>
										</c:if>
										<c:if test="${recieve.readNo eq 'N' }">
											<a style="color: blue; text-decoration: none;" href="${cPath}/note/noteView.do?noteId=${recieve.noteNo  }">
											  ${recieve.sendContent }
											</a>
										</c:if>
										</td>
										<td>${recieve.sendDate }</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="3">
										<div id="pagingArea" style="text-align: center;">${recieveList.pagingHTML }</div>
									</td>
								</tr>
								<tr>
									<td colspan="3"  style="border-bottom:none;">
										<form action="?" id="searchForm" class="form">
											<input type="hidden" name="page" />
										</form>
									</td>
								</tr>
							</tfoot>
						</c:if>

						<c:if test="${not empty sendList.dataList[0] }">
							<thead>
								<th style="width: 15%;">받는사람</th>
								<th style="width: 60%;">내용</th>
								<th style="width: 20%;">날짜</th>
							</thead>
							<tbody id="listBody">
								<c:forEach items="${sendList.dataList }" var="send">
									<tr id="${send.noteNo }">
										<td>${send.recieveId }</td>
										<td>
	                                        <c:if test="${send.readNo eq 'Y' }">
	                                            <a style="color: gray; text-decoration: none;" href="${cPath}/note/noteView.do?noteId=${send.noteNo  }">
	                                              ${send.sendContent }
	                                            </a>
	                                        </c:if>
	                                        <c:if test="${send.readNo eq 'N' }">
	                                            <a style="color: blue; text-decoration: none;" href="${cPath}/note/noteView.do?noteId=${send.noteNo  }">
	                                              ${send.sendContent }
	                                            </a>
	                                        </c:if>
										</td>
										<td>${send.sendDate }</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="3">
										<div id="pagingArea" style="text-align: center;">${sendList.pagingHTML }</div>
									</td>
								</tr>
								<tr>
									<td colspan="3"  style="border-bottom:none;">
										<form action="?" id="searchForm" class="form">
											<input type="hidden" name="page" />
										</form>
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
				<div class="modal-body" style="font-size: 20px;">
					<div class="form-group">
						<label for="recipient-name" class="control-label">받는 사람:</label><br>
						<select class="form-control"
							style="width: 20%; display: inline-block;" id="sendWho">
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
							style="width: 70%; display: inline-block; height: 20px;" required
							placeholder="여러명은 쉼표(,)로 구분">
					</div>
					<div class="form-group">
						<label for="message-text" class="control-label">내용:</label>
						<textarea class="form-control" id="message-text"
							style="width: 506px; height: 100px; font-size: 20px;" placeholder="내용을 입력해주세요" required></textarea>
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


	pagingArea.on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		if (page < 1)
			return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});

	// 	쪽지 모달창을 닫기버튼 누르면?
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
                $('#recipient-name').attr("readonly",false);
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
				let text=$('#message-text').val().replace(/\n/g, "<br>");
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
							resp.splice(0,2);
							var reciver;
							for (var i = 0; i < resp.length; i++) {
								sock.send(resp[i]);
							}
			                $('#recipient-name').val("");
			                $('#message-text').val("");
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
</script>