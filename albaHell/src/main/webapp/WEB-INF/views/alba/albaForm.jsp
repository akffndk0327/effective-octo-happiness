<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/generataLprodBuye.js"></script>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
</head>
<body>
	<div class="container">
		<h2>알바생 상세정보</h2>
		<div style="text-align: right">
			<input type="button" value="수정" id="btn-save" />
		</div>

		<form id="albaForm">
			<table class="table">
				<tr>
					<th>아이디</th>
					<td><span> <c:out value="${alba.al_id}" />
					</span> <input id="al_id" name="al_id" type="hidden" value="${alba.al_id}" />
					</td>
				</tr>

				<tr>
					<th>이름</th>
					<td><input type="text" required class="form-control"
						name="al_name" value="${alba.al_name}" /> <span class="errors">${errors["al_name"]}</span></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="text" required class="form-control"
						name="al_age" value="${alba.al_age}" /> <span class="errors">${errors["al_age"]}</span></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" required class="form-control"
						name="al_add" value="${alba.al_address}" /> <span class="errors">${errors["al_address"]}</span></td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td><input type="text" required class="form-control"
						name="al_hp" value="${alba.al_hp}" /> <span class="errors">${errors["al_hp"]}</span></td>
				</tr>
				<tr>
					<th>특기사항</th>
					<td><input type="text" required class="form-control"
						name="al_spec" value="${alba.al_spec}" /> <span class="errors">${errors["al_spec"]}</span></td>
				</tr>
				<tr>
					<th>비고</th>
					<td><input type="text" required class="form-control"
						name="al_desc" value="${alba.al_desc}" /> <span class="errors">${errors["al_desc"]}</span></td>
				</tr>
				<tr>
					<th>학력</th>
					<td>< <select name="gr_code">
							<option value="G001" ${alba.gr_code eq 'G001' ? 'selected' : ''}>고졸</option>
							<option value="G002" ${alba.gr_code eq 'G002' ? 'selected' : ''}>초대졸</option>
							<option value="G003" ${alba.gr_code eq 'G003' ? 'selected' : ''}>대졸</option>
							<option value="G004" ${alba.gr_code eq 'G004' ? 'selected' : ''}>석사</option>
							<option value="G005" ${alba.gr_code eq 'G005' ? 'selected' : ''}>박사</option>
					</select> <span class="errors">${errors["gr_code"]}</span></td>
				</tr>
				<tr>
					<th>경력사항</th>
					<td><input type="text" required class="form-control"
						name="al_career" value="${alba.al_career}" /> <span
						class="errors">${errors["al_career"]}</span></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><select id="al_btype" name="al_btype">
							<option value="A" ${alba.al_btype eq 'A'  ? 'selected' : ''}>A형</option>
							<option value="B" ${alba.al_btype eq 'B'  ? 'selected' : ''}>B형</option>
							<option value="O" ${alba.al_btype eq 'O'  ? 'selected' : ''}>O형</option>
							<option value="AB" ${alba.al_btype eq 'AB' ? 'selected' : ''}>AB형</option>
					</select></td>
				</tr>
				<tr>
					<th>혈액형</th>
					<td><input type="text" required class="form-control"
						name="al_btype" value="${alba.al_btype}" /> <span class="errors">${errors["al_btype"]}</span></td>
				</tr>
				<tr>
					<th>메일</th>
					<td><input type="text" required class="form-control"
						name="al_mail" value="${alba.al_mail}" /> <span class="errors">${errors["al_mail"]}</span></td>
				</tr>
				<tr>
					<th>자격증코드</th>
					<td><c:choose>
							<c:when test="${not empty alba.licAlbaList}">
								<c:forEach var="lic" items="${alba.licAlbaList}">
									<c:if test="${not empty lic.lic_code}">
										<input type="button" value="X" class=".btn-default deleteDiv"
											lic_code="${lic.lic_code}" al_id="${lic.al_id}">
									</c:if>
									<div lic_code="${lic.lic_code}" al_id="${lic.al_id}"
										class="img-div" style="display: inline-block;">
										<c:out value="${lic.license.lic_name}" />
									</div>
									<br>
								</c:forEach>
							</c:when>
							<c:otherwise>
                             없음 .
                           </c:otherwise>
						</c:choose>
						<div style="padding-top: 20px;">
							<input type="button" value="자격증 추가" id="btn-add-licAlba"
								class="btn btn-primary .btn-sm" data-toggle="modal"
								data-target="#myModal" />
						</div></td>
				</tr>
				<tr>
					<th>&nbsp;자격증명 사진</th>
					<td><img src="#" id="licenseImg"
						style="width: 300px; height: 300px;" /></td>
				</tr>
			</table>
		</form>
		<%-- 자격증에 대한 자격증명 사진이 존재하는 경우 --%>
		<div style="border-top: 1px solid #dee2e6; padding-top: 20px;">
			<form id="imgForm"
				action="${pageContext.request.contextPath}/alba/imageUpload.do"
				enctype="multipart/form-data" method="POST">
				<input type="file" id="lic_image" name="lic_image" /> <input
					type="hidden" id="img_lic_code" name="img_lic_code" /> <input
					type="hidden" id="img_al_id" name="img_al_id" /> <input
					type="submit" id="btn-submit" value="사진 추가" /> <input
					type="button" id="btn-delete" value="사진 삭제" />
			</form>
		</div>
	</div>

	<!-- 모달 -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">자격증 추가</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<select id="License" name="License_type">
						<c:forEach items="${license}" var="license">
							<option value="${license.lic_code }">${license.lic_name}</option>
						</c:forEach>
					</select>

					<div style="border-top: 1px solid #dee2e6; padding-top: 20px;">
						<form id="licForm"
							action="${pageContext.request.contextPath}/alba/imageUpload.do"
							enctype="multipart/form-data" method="POST">
							<input type="file" id="lic_image2" name="lic_image2" /> <input
								type="hidden" id="img_lic_code2" name="img_lic_code2" /> <input
								type="hidden" id="img_al_id2" name="img_al_id2"
								value="${alba.al_id}" />
						</form>
					</div>
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal"
						id="LicBtn">추가</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>




	<script type="text/javascript">
	 //버튼 및 이미지 태그 DISPLAY 설정
    $(document).ready(function(){
        $("#lic_image").hide();
        $("#btn-submit").hide();
        $("#btn-delete").hide();
        $("#licenseImg").hide();
    });

    //알바생 상세정보 저장 이벤트
    $("#btn-save").on("click", function(event){
        $.ajax({
            url : "${pageContext.request.contextPath}/alba/albaUpdate.do",
            method : "POST",
            data : $("#albaForm").serialize(),
            success : function(resp) {
                if(!resp.valid) {
                    alert("저장에 성공하였습니다.");
                    window.location.href = '${pageContext.request.contextPath}/alba/albaView.do?id=${alba.al_id}';
                } else {
                    alert("저장 중 오류가 발생하였습니다.");
                    window.location.reload();
                }
            },
            error : function(err) {
                console.log(err.status);
            }
        })
    });

    //자격증별 첨부된 사진 조회 이벤트
    $(".img-div").on("click", function(event) {
        //모든 DIV 배경색 흰색으로 변경
        $('.img-div').each(function(idx, data){
            $(data).css('background-color', 'white');
        })

        //선택한 DIV 배경색 핑크색으로 변경
        $(this).css('background-color', '#ffdfdf');
        
        //선택한 DIV에 속성으로 존재하는 자격증코드, 알바아이디 가져오기
        var licCode = $(this).attr("lic_code");
        var alId    = $(this).attr("al_id");

        //가져온 값을 MULTIPART FORM INPUT 태그에 값 입력되도록 설정
        $("#img_al_id").val(alId);
        $("#img_lic_code").val(licCode);
        
        //MULTIPART FORM INPUT에 설정된 값 변수에 세팅
        var imgLicCode = $("#img_lic_code").val()
        var imgAlId    = $("#img_al_id").val();
        
        //누락된 값이 없이 설정되었다면 파일첨부 및 전송 버튼 활성화
        if(imgLicCode != null && imgAlId != null) {
            $("#lic_image").show();
            $("#btn-submit").show();
        }
        
        //이미지 조회
        $.ajax({
            url : "${pageContext.request.contextPath}/alba/licenseImage.do",
            method : "POST",
            data : {"lic_code": imgLicCode, "al_id":imgAlId},
            success : function(resp) {
                if(!resp.valid) {
                    var base64 = resp.base64;
                    $("#licenseImg").attr("src", "data:image/jpeg;base64,"+base64);
                    $("#licenseImg").show();
                    $("#btn-delete").show();
                    $("#btn-submit").val("사진 수정");
                    alert("조회에 성공하였습니다.");
                } else {
                    $("#licenseImg").hide();
                    $("#btn-submit").val("사진 추가");
                    alert("이미지 파일이 존재하지 않습니다.");
                }
            },
            error : function(err) {
                console.log(err.status);
            }
        })
    });
    
    //자격증 추가 모달창 띄우기
    $("#btn-submit").on("click", function(event){
        var imgLicCode = $("#img_lic_code").val();
        var imgAlId    = $("#img_al_id").val();
        var file       = $("#lic_image").val();
        
        if(file.length == 0){
            alert("첨부할 이미지를 선택해주세요.");
            return false;
        }
        
        if(imgLicCode.length == 0 && imgAlId.length == 0){
            alert("자격증을 선택해주세요.");
            return false;
        }
    });
    
    //자격증 SELECT BOX에 포함된 OPTION 값 변경 이벤트
    $("#License").change("click", function(event){
        var licenseCode = $("#License option:selected").val();
        $("#img_lic_code2").val(licenseCode);
    });
    
    //자격증 및 사진 추가 버튼
    $("#LicBtn").on("click", function(event){
        $('#licForm').submit();
    });

    //자격증명 사진 삭제 버튼
    $("#btn-delete").on("click", function(event){
        $("#lic_image").hide();
        $('#imgForm').submit();
    });
    
    //자격증 삭제 버튼 
      $(".deleteDiv").on("click", function(event){
        var id=$(this).attr("al_id");
        var code=$(this).attr("lic_code");
        
        $.ajax({
            url : "${pageContext.request.contextPath}/alba/albaLicDelete.do",
            method : "POST",
            data : {"lic_code": code, "al_id":id},
            success : function(resp) {
                 if(!resp.valid) {
                     alert("삭제 성공");
                     window.location.reload();
                 } else {
                     alert("삭제 실패.");
                 }
            },
            error : function(err) {
                console.log(err.status);
            }
        })
        
    });
</script>
</body>
</html>