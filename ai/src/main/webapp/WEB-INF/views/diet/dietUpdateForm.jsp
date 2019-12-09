	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%--
	* [[개정이력(Modification Information)]]
	* 수정일                 수정자      수정내용
	* 2019.11.11	박슬기		상세기능구현
	* ----------  ---------  -----------------
	* 2019. 11. 6.      박슬기      최초작성
	* Copyright (c) 2019 by DDIT All right reserved
	 --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<style>
	table {
		/*  	width:600px;  */
		margin: auto;
		align-content: center;
		width: 1500px;
		font-family: 'Eoe_Zno_L';
	}
	
	.t {
		 border: 2px solid #548687;
		 width: 210px;
		 height:225px;
		 border-top: 2px solid #548687;
		 border-bottom: 2px solid #548687;
		 text-align: center;
		 font-size:20px;
	}
	
	.t:hover{
	    	  color:#2F4F4F;
	    	  font-size:22px;
	}
	thead{
		height: 300px;
		font-size: 30px;
		text-align: center;
	}
	.btnLo {
		text-align: right;
	}
	
	#aller{
		color:#E65C44;
		width: 200px;
		font-size: 18px;
		padding-top: 27px;
	}
	
	#pass{
		display: none;
	}
	
	</style>
	<div id="InnerContainer" style="width: 1500px">
	    <h1 class="titleTopBar" style="display: inline; float: left">SMART식단</h1>
		<c:set var="diet" value="${o_list}" />
		<br>
		<div style="position: absolute; top: 391px; left: 217px; font-size:29px;">
			작성자 : ${writer}<br>
			제목 : ${diet[0].monthlyTitle}<br>
					<input type="hidden" value="${diet[0].monthlyId}" id="monid"/>
		</div>
			<br><br><br><br><br><br><br><br>
		<table>
		<tbody>
	
			<c:forEach items="${diet}" var="d" begin="0" varStatus="i" step="1">
				<c:if test="${(i.index%35) eq 0}">
					<tr class="view">
				</c:if>
				<c:if test="${(i.index%5) eq 0}">
					<td class="t diet" id="${d.onedayId }">
				</c:if><p id="${d.menuId}"}>${d.menuName}</p>
				<c:if test="${(i.index%5) eq 4}">
					</td>
				</c:if>
				<c:if test="${(i.index%35) eq 34}">
					</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
		<br><br>
			<input type="button" class="btn btn-outline-success btnAi0" value="확인" style="float: right" id="insert"/>
			<input type="button" class="btn btnAi1" id="back" value="뒤로가기" style="float: left"/>
			<br><br>
	</div>
	
	<!-- 하루치 식단 수정 모달-->
	<div class="modal" tabindex="-1" role="dialog" id="myModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header" style="height: 100px">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" style="text-align: center">식단 수정</h4><br>
					<h6 style="color:#E65C44; text-align: center; margin: 0">확인버튼 클릭시 반영됩니다.</h6>
				</div>
				<div class="modal-body">
					<div>
					<table style="width:550px;">
						<tr>
							<th><br>
								밥 : 
							</th>
							<td>
							<!-- 밥 리스트-->
							&nbsp;<select class="form-control dt-tb" id="rice" style="width: 250px; font-size: 20px;">
								<c:forEach items="${r_list}" var="rlist">
									<c:set var="option" value="${option}" />
										<c:if test='${rlist.check eq "true"}'>
											<option id="${rlist.menuId }" class="allergy" value="${rlist.menuId }">${rlist.menuName}</option>
										</c:if>
										<c:if test='${rlist.check ne "true"}'>
											<option id="${rlist.menuId }" value="${rlist.menuId }">${rlist.menuName}</option>
										</c:if>
								</c:forEach>
							</select>
							</td>
							<td class="archeck" id="aller">
								<br><br>
							</td>
						</tr>
						<!-- 김치 리스트-->
						<tr>
							<th><br>
								김치 : 
							</th>
							<td>
							&nbsp;<select class="form-control dt-tb" id="kim" style="width: 250px; font-size: 20px;">
								<c:forEach items="${k_list}" var="klist">
									<c:set var="option" value="${option}" />
										<c:if test='${klist.check eq "true"}'>
											<option id="${klist.menuId }" class="allergy" value=${klist.menuId }">${klist.menuName}</option>
										</c:if>
										<c:if test='${klist.check ne "true"}'>
											<option id="${klist.menuId }" value="${klist.menuId }">${klist.menuName}</option>
										</c:if>
								</c:forEach>
							</select>
							</td>
							<td>
							<br>
								<div class="akcheck" id="aller"></div>
							</td>
						</tr>
						<!-- 국 리스트-->
						<tr>
							<th><br>
								국 : 
							</th>
							<td>
								&nbsp;<select class="form-control dt-tb" id="gook" style="width: 250px; font-size: 20px;">
								
									<c:forEach items="${g_list}" var="glist">
										<c:set var="option" value="${option}" />
											<c:if test='${glist.check eq "true"}'>
												<option id="${glist.menuId }" class="allergy" value="${glist.menuId }">${glist.menuName}</option>
											</c:if>
											<c:if test='${glist.check ne "true"}'>
												<option id="${glist.menuId }" value="${glist.menuId }">${glist.menuName}</option>
											</c:if>
									</c:forEach>
								</select>
							</td>
							<td>
								<div class="agcheck" id="aller"></div>
							</td>
						</tr>
						<!-- 반찬 리스트-->
						<tr >
							<th><br>
								반찬 : 
							</th>
							<td>
								&nbsp;<select multiple="multiple" class="form-control dt-tb bl" id="ban" style="width: 250px; font-size: 20px;">
									<c:forEach items="${b_list}" var="blist">
										<c:set var="option" value="${option}" />
											<c:if test='${blist.check eq "true"}'>
												<option id="${blist.menuId }" class="allergy" value="${blist.menuId}">${blist.menuName}</option>
											</c:if>
											<c:if test='${blist.check ne "true"}'>
												<option id="${blist.menuId }" value="${blist.menuId }">${blist.menuName}</option>
											</c:if>
									</c:forEach>
								</select>
							</td>
							<td>
								<br>
								<div class="abcheck" id="aller"> </div>
							</td>
						</tr>
					</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btnAi0" id="btn-ok">확인</button>
					<button type="button" class="btn btnAi1 btn-secondary " data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- 수정 완료 후 제목, 공개비공개 수정모달-->
	<div class="modal" tabindex="-1" role="dialog" id="checkModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">저장하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> 
				</div>
				<div class="modal-body">
					<div style="font-size: 20px">
						제목 : <input type="text" value="${diet[0].monthlyTitle}" id="title"/><br><br>
						공개여부 :&nbsp;<input type="radio" name="authorId" value="Y" id="open" checked /> 공개 &nbsp;
	                            	  <input type="radio" name="authorId" value="N" id="open"/> 비공개
	                            	  <br> <br>
	                    <div id ="pass">  비밀번호 : <input type="password" id="monpass"/> </div>
	                    
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btnAi0 btn-primary" style="background-color: #2F4F4F">확인</button>
					<button type="button" class="btn btnAi1" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$(function(){
		
	
	var fMenu; 
	 var SMenu;
	 var TMenu; 
	 var FoMenu;
	 var FiMenu
		$(".view").on("click",function(){
			$("#myModal").modal("show");
			$("#rice").val(fMenu).prop("selected","selected");
			$("#kim").val(SMenu).prop("selected","selected");
			$("#gook").val(TMenu).prop("selected","selected");
			$("#ban option").each(function(i,element){
				if ($(element).val() == FiMenu || $(element).val() == FoMenu) {
					$(element).attr("selected",1);
				}
			});
	
		})
		
		$("#insert").on("click",function(){
			$("#checkModal").modal("show");
		})
		
		$("#rice").change(function(){
			var test = $("#rice option:selected").attr("class");
			if (test == "allergy") {
				$(".archeck").html("<br>&nbsp;알러지 유발 메뉴입니다.");
			}else{
				$(".archeck").html("");
			}
		})
	
		$("#gook").change(function(){
			var test = $("#gook option:selected").attr("class");
			if (test == "allergy") {
				$(".agcheck").html("<br>&nbsp;알러지 유발 메뉴입니다.");
			}else{
				$(".agcheck").html("");
			}
		})
		
		$("#kim").change(function(){
			var test = $("#kim option:selected").attr("class");
			if (test == "allergy") {
				$(".akcheck").html("<br>&nbsp;알러지 유발 메뉴입니다.");
			}else{
				$(".akcheck").html("");
			}
		})
		
		$("#ban").change(function(){
		var cnt = 0;
			$("#ban option:selected").each(function(){
				var ch = $(this).attr("class");	
				if (ch == "allergy") {
					cnt++;
				}
			})
				if (cnt>0) {
					$(".abcheck").html("<br>&nbsp;알러지 유발 메뉴입니다.");
				}else{
					$(".abcheck").html("");
				}
		})
		
		$(".btn-primary").on("click",function(){
			var title = $("#title").val();
			var use = $(':radio[name="authorId"]:checked').val();
			var id = $("#monid").val();
			var pass = $("#monpass").val();
			if (title=="") {
				Swal.fire({
	                title: '경고',
	                text: "제목을 입력해주세요.",
	                icon: 'error',
	                showCancelButton: false,
	                confirmButtonColor: '#90c322',
	                confirmButtonText: '확인'
	              }).then((result) => {
	                  if (result.value) {
	                 }
	             });
				return;
			}
			
			if (use=='N' && pass=="") {
				Swal.fire({
	                title: '경고',
	                text: "비밀번호를 입력해주세요.",
	                icon: 'error',
	                showCancelButton: false,
	                confirmButtonColor: '#90c322',
	                confirmButtonText: '확인'
	              }).then((result) => {
	                  if (result.value) {
	                 }
	             });
				return;
			}
			$.ajax({
				url : "${cPath}/diet/dietMonthlyUpdate.do",
				method : "post",
				data : {
					"use" : use,
					"title" : title,
					"id" : id,
					"pass" : pass
				},
				dataType : "json",
				success : function(resp) {
					window.location.href = "${cPath}/diet/dietList.do";
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
			$("#checkModal").modal('hide');
		})
	
		$('input[name="authorId"]').change(function() {
		        var value = $(this).val();       
		        if(value=="N"){
		        	$("#pass").show();
		        }else{
		        	$("#pass").hide();
		        }
		});
			
	
		
		$(".diet").on("click",function(){
			//onedayId를 가져옴
			var id = $(this).attr("id");
			//선택한 날짜의 식단 목록을 가져옴
			fMenu = $(this).children().eq(0).prop("id");
			SMenu = $(this).children().eq(1).prop("id");
	// 		TMenu = $(this).children().eq(2).prop("id");
			FoMenu = $(this).children().eq(3).prop("id");
			FiMenu = $(this).children().eq(4).prop("id");
			
			//배열을 ajax로 보내기 위해 설정하는 코드
			jQuery.ajaxSettings.traditional = true;
			
			//td태그 내의 p태그를 가져옴
			var td = $(this).children("p");
			
			var beforeValue = new Array(); // 초기 메뉴아이디 배열
			td.each(function(i){
				 beforeValue.push(td.eq(i).attr("id"));
			 });		   
			
			
			$("#btn-ok").on("click",function(){
				var rice = $("#rice option:selected").attr("id");
				var kim = $("#kim option:selected").attr("id");
				var gook = $("#gook option:selected").attr("id");
				var ban = $(".bl").val();
				$.ajax({
					url :  "${cPath}/diet/dietOnedayUpdate.do",
					method : "post",
					data : {
						"oneId" : id,
						"beforeRice" : beforeValue[0],
						"beforeKim" : beforeValue[1],
						"beforeGook" : beforeValue[2],
						"beforeBan" : beforeValue[3],
						"beforeBan1" : beforeValue[4],
						"afterRice" : rice,
	                    "afterKim" : kim,
					    "afterGook" :gook,
					    "afterBan" : ban
					},
					dataType : "json",
					success : function(resp) {
						window.location.reload();
					},
					error : function(xhr) {
						console.log(xhr.status);
					}
				})
				$("#myModal").modal('hide');
	
			})	
				
		});
		 $("#back").on("click",function(){
			 history.back();
		 })
		 
	})
	
	
	</script>