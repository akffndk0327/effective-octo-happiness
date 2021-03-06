<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 13.      박슬기      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
  
 
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
	#tform {
/* 	  	width:1000px;   */
	margin: auto;
	align-content: center;
	width: 1500px;
	font-family: 'Eoe_Zno_L';
	}
	
	form{
		text-align: center;
		font-size: 18px;
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
	    	  font-size:22px
	}

	
	#tHead{
	height: 300px;
	font-size: 30px;
/* 	text-align: center; */
	}
	
	#calendar{
	display: inline-block;
    width: 140px;
    height: 30px;
	}
	
	[type="radio"]:checked,
	[type="radio"]:not(:checked) {
		  position: absolute;
		  left: -9999px;
		}
	[type="radio"]:checked + label,
	[type="radio"]:not(:checked) + label
		{
		  position: relative;
		  padding-left: 28px;
		  cursor: pointer;
		  line-height: 20px;
		  display: inline-block;
		  color: #666;
		}
	[type="radio"]:checked + label:before,
	[type="radio"]:not(:checked) + label:before {
		  content: '';
		  position: absolute;
		  left: 0;
		  top: 0;
		  width: 15px;
		  height: 15px;
		  border:2px solid #ddd;
		  border-radius: 100%;
		  background: #fff;
		}
	[type="radio"]:checked + label:before {
		  border:2px solid #2F4F4F;
		  border-radius: 100%;
		}
	[type="radio"]:checked + label:after,
	[type="radio"]:not(:checked) + label:after {
		  content: '';
		  width:9px;
		  height:9px;
		  background: #2F4F4F;
		  position: absolute;
		  top: 5px;
	      left: 5px;
		  border-radius: 100%;
		  -webkit-transition: all 0.2s ease;
		  transition: all 0.2s ease;
		}
	[type="radio"]:not(:checked) + label:after {
		  opacity: 0;
		  -webkit-transform: scale(0);
		  transform: scale(0);
		}
	[type="radio"]:checked + label:after {
		  opacity: 1;
 		  -webkit-transform: scale(1);
		  transform: scale(1);
		}
		
	#aller{
			color:red;
			width: 220px;
			font-size: 18px;
			
	}
	
 	.btn-outline-success{ 
	 	float: inherit; 
	 	background-color: #2F4F4F; 
	 	color:white; 
	 	text-align: center;
	 } 
	 


	 
	 .date{
	 	text-align : center;
	 }
	 
	 .recommendDesc{
	    position: absolute;
	    top: 474px;
    	left: 623px;
	    color: #E65C44;
	    font-size: 20px;
	  }
	 .desc{
	    position: absolute;
	       top: 605px;
    	left: 714px;
	    color: #E65C44;
	    font-size: 20px;
	  }
	 .menuTop{
	    position: absolute;
	    top: 400px;
    	left: 215px;
	    font-size: 20px;
	  }
/* 	  .swal2-title{ */
/* 	  	font-size: 22px; */
/* 	  } */
/* 	  .swal2-html-container{ */
/* 	  	font-size: 18px; */
/* 	  } */
/* 	 .swal2-styled.swal2-confirm{ */
/* 	 	font-weight:bold; */
/* 	  	font-size: 18px; */
/* 	  	width: 106px; */
/* 	  	height: 42px; */
/* 	  	font-family: 'Eoe_Zno_L'; */
/* 	  } */
		
</style>

<!-- Datepicker 한국어로 변환 -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script> -->
<div id="InnerContainer" style="width: 1500px">
      <h1 class="titleTopBar" style="margin-left:10px"">SMART식단</h1>
	<br>
	<div class="menuTop">
	<p style="font-size:25px; text-align: left;">인기 반찬메뉴</p>
      <table>
         <c:forEach varStatus="vs" items="${rec_list }" begin="0" end="4" var="r">
            <tr>
             <td>${vs.count }</td>
               <td>&nbsp;&nbsp;&nbsp;</td>
               <td>${r.menuName }</td>
               <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
               <td><img src="${cPath }/images/b${vs.count }.png" /></td>
            </tr>
         </c:forEach>
      </table>
	</div>
	<br>
		<div style="font-size: 20px">
			<p class="radio-group date">
				<input type="radio" value="self" name="radio" id="self" checked="" ><label for="self"> 직접작성 </label> &nbsp;
		    	<input type="radio" value="recommend" name="radio" id="recommend"> <label for="recommend"> SMART식단 </label>
	    	</p>
	    	<br>
	    	<div class="recommendDesc">
	    		* SMART식단이란? 회원님의 알레르기 정보에 따라 알맞은 메뉴를 추천해드립니다.<br>
	    	</div>
	    	<div class="desc">
				날짜 입력 후 날짜를 선택하시면 식단 입력 창이 출력됩니다.
	    	</div>
		</div>
	    <br><br><br>
			<form>
				<label for="fromDate">시작일</label> 
					<input type="text" class="inputDate" name="fromDate" id="fromDate" size="10" readonly="readonly" /> ~ 
				<label for="toDate">종료일</label>
					<input type="text" class="inputDate" name="toDate" id="toDate" size="10"readonly="readonly" />
				<input type="button" id="test" value="날짜선택" class="btn btn-outline-success"/> 
				
			</form>
			<br>
			<br><br><br>
			<div style="min-height:300px">
				<table id="tform">
					<thead id="tHead">
					</thead>
					<tbody id="dietform" >
					
					</tbody>
				</table>
			</div>
			<br><br>
				<input type="button" id="add" class="btn-outline-success btn btnAi1" value="등록하기" style="float: right"/>
				<input type="button" class="btn btnAi1" id="back" value="뒤로가기" style="float: left"/>
			<br><br>
	</div>

<!-- 하루치 식단 입력 모달-->
<div class="modal" tabindex="-1" role="dialog" id="myModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h2 class="modal-title">식단 입력</h2>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div>
				<table style="margin-top: -60px">
					<tr>
						<th><br>
							밥 : 
						</th>
						<td>
						<!-- 밥 리스트-->
						&nbsp;<select class="form-control dt-tb" style="width: 250px; font-size: 20px;" id="rice">
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
						<br><br>
						<td class="archeck" id="aller">
						</td>
					</tr>
					<!-- 김치 리스트-->
						<th><br>
							김치 : 
						</th>
						<td>
						&nbsp;<select class="form-control dt-tb" style="width: 250px; font-size: 20px;" id="kim">
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
						<td class="akcheck" id="aller">
						<br><br>
						</td>
					</tr>
					<!-- 국 리스트-->
						<th><br>
							국 : 
						</th>
						<td>
							&nbsp;<select class="form-control dt-tb" style="width: 250px; font-size: 20px;" id="gook">
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
						<td class="agcheck" id="aller">
						<br><br>
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
											<option id="${blist.menuId }" class="allergy" name="banName" value="${blist.menuId}">${blist.menuName}</option>
										</c:if>
										<c:if test='${blist.check ne "true"}'>
											<option id="${blist.menuId }"  name="banName" value="${blist.menuId }">${blist.menuName}</option>
										</c:if>
								</c:forEach>
							</select>
						</td>
						<td class="abcheck" id="aller">
							<br><br>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-ok btnAi0">확인</button>
				<button type="button" class="btn btn-secondary btnAi1" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<!-- 수정 완료 후 제목, 공개비공개 수정모달-->
<div class="modal" tabindex="-1" role="dialog" id="checkModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">등록하기</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> 
			</div>
			<div class="modal-body">
				<div style="font-size: 20px;">
					제목 : <input type="text" value="${diet[0].monthlyTitle}" id="title" style="width: 400px;"/><br><br>
                    <p class="radio-group">
					공개여부 :&nbsp;<input type="radio" name="authorId" value="Y" id="Y" checked /><label for="Y"> 공개</label> &nbsp;
                            	  <input type="radio" name="authorId" value="N" id="N"/><label for="N"> 비공개</label> 
                    </p>
                    <br>
                    <div id ="pass">  비밀번호 : <input type="password" id="monpass"/> </div>

				</div>
			</div>
			<br><br>
			<div class="modal-footer">
				<button type="button" class="btn btnAi0" id="ok">확인</button>
				<button type="button" class="btn btn-secondary btnAi1" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function () {
 	//시작일.
	 $('#fromDate').datepicker({
	//      showOn: "both",                     // 달력을 표시할 타이밍 (both: focus or button)
	//     buttonImageOnly : false,             // 버튼 이미지만 표시할지 여부
	//      buttonText: "날짜선택",             // 버튼의 대체 텍스트
	     dateFormat: "yymmdd",             // 날짜의 형식
	     changeMonth: true,                  // 월을 이동하기 위한 선택상자 표시여부
	     minDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
	     onClose: function( selectedDate ) {    
	         // 시작일(fromDate) datepicker가 닫힐때
	         // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
	         $("#toDate").datepicker( "option", "minDate", selectedDate );
	     }
	 });

 	//종료일
	 $('#toDate').datepicker({
	//      showOn: "both", 
	//      buttonImageOnly : false,
	//      buttonText: "날짜선택",
	     dateFormat: "yymmdd",
	     changeMonth: true,
	     minDate: 0, // 오늘 이전 날짜 선택 불가
	     onClose: function( selectedDate ) {
	         // 종료일(toDate) datepicker가 닫힐때
	         // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
	         $("#fromDate").datepicker( "option", "maxDate", selectedDate );
	     }                
	 });
	 
	 function parse(str) {
			var y = str.substr(0,4),
			m = str.substr(4,2) - 1,
			d = str.substr(6,2);
			return new Date(y,m,d);
	}
 
	 var calendarDay;
	 var valendarMonth;
	 var period;
	 $("#test").on("click",function(){
		 $("#dietform").html("");
		 var start =  $("#fromDate").val();
		 var end  = $("#toDate").val();	

		 startday = parse(start);
		 endday = parse(end);
		 period = (endday-startday)/86400000;
		 if(period>=31) {
			 Swal.fire({
                 title: '경고',
                 text: "최대기간은 30일입니다.",
                 icon: 'error',
                 showCancelButton: false,
                 confirmButtonColor: '#90c322',
                 confirmButtonText: '확인'
               }).then((result) => {
                   if (result.value) {
                       window.location.reload();
                  }
              });
			 $("#fromDate").val("");
			 $("#toDate").val("");
			 return;
		 }
		 if(start=="" || end==""){
			 Swal.fire({
                 title: '경고',
                 text: "최소하루 이상 선택해주세요.",
                 icon: 'error',
                 showCancelButton: false,
                 confirmButtonColor: '#90c322',
                 confirmButtonText: '확인'
               }).then((result) => {
                   if (result.value) {
                       window.location.reload();
                  }
              });
			 $("#fromDate").val("");
			 $("#toDate").val("");
			 return;
		 }	 
		 
		 var code = "<tr class='view'>";
		 calendarDay = start.substr(6,2);
		 valendarMonth = start.substr(4,2);
		if (value != "recommend") {
			 for (var i = 1; i <= period+1; i++) {
				if (calendarDay>30) {
					calendarDay = calendarDay - 30;
					valendarMonth = (valendarMonth*1)+1
				}
				if (i%7==0) {
					code+="<td class='t diet'><span id='calendar'>"+valendarMonth+"/"+calendarDay+"</span></td></tr><tr class='view'>"
				}else{
					code+="<td class='t diet'><span id='calendar'>"+valendarMonth+"/"+calendarDay+"</span></td>";
				}
				calendarDay++;
			}
		}
		 code+="</tr>";
		 $("#dietform").append(code);
		 
		 var menuTag="<tr class='view'>";
		 if (value == "recommend") {
			var sendDay = period+1;
			$.ajax({
				url : "${cPath}/diet/recommendInsert.do",
				method : "post",
				data : {
					"period" : sendDay
				},
				dataType : "json",
				success : function(resp) {
					var tableTag = "<tr class='view'>";
					$.each(resp,function(i,v){
							if (calendarDay>30) {
								calendarDay = calendarDay - 30;
								valendarMonth = (valendarMonth*1)+1
							}
						if (i%7==0) {
							tableTag+="<tr class='view'>"
						}
						$.each(v,function(j,k){
							if (j%5==0) {
								tableTag+="<td class='t diet'>"
							}
							tableTag+="<input type='hidden' name='menuId' value='"+k.menuId+"'><p id='"+k.menuId+"'>"+k.menuName+"</p>"
							if (j%5==4) {
								tableTag+="</td>"
							}
						})
						if (i%7==6) {
							tableTag+="</tr>"
						}
							calendarDay++;
					})
					tableTag+="</tr>"
					$("#dietform").append(tableTag);
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
		}
		 
	 })
	var rice;
	var kim;
	var gook;
	var ban;
	var ban2;
	
	var riceValue;
	var kimValue;
	var gookValue;
	var ban1Value;
	var ban2Value;
	
	var index = 1;
	var td;
	var num;
	
	var fMenu; 
	var SMenu; 
	var TMenu; 
	var FoMenu;
	var FiMenu;
	 $("#dietform").on("click","td",function(){
		 td = $(this);
		 fMenu =  $(this).children("p").eq(0).prop("id");
		 SMenu =  $(this).children("p").eq(1).prop("id");
		 TMenu =  $(this).children("p").eq(2).prop("id");
		 FoMenu = $(this).children("p").eq(3).prop("id");
		 FiMenu = $(this).children("p").eq(4).prop("id");
		 $("#myModal").modal("show");
		 $("#rice option:selected").attr("selected",false);
		 $("#kim option:selected").attr("selected",false);
		 $("#gook option:selected").attr("selected",false);
		 
		 $("#ban option").each(function(i,element){
				$(element).attr("selected",false);
		 });
		 
		 $("#rice").val(fMenu).prop("selected","selected");
		 $("#kim").val(SMenu).prop("selected","selected");
		 $("#gook").val(TMenu).prop("selected","selected");
		 $("#ban option").each(function(i,element){
			if ($(element).val() == FiMenu || $(element).val() == FoMenu) {
				$(element).prop("selected","selected");
			}
		 });
		 $(".archeck").html("");
		 $(".agcheck").html("");
		 $(".akcheck").html("");
		 $(".abcheck").html("");
		 
	 })
			$(".btn-ok").on("click",function(){
				if (num<2) {
// 					alert("반찬은 최소 2종류를 선택해주세요.");
					 Swal.fire({
		                 title: '경고',
		                 text: "반찬은 최소 2종류를 선택해주세요.",
		                 icon: 'error',
		                 showCancelButton: false,
		                 confirmButtonColor: '#90c322',
		                 confirmButtonText: '확인'
		               }).then((result) => {
		                   if (result.value) {
		                  }
		              });
					return;
				 }else if(num>2){
// 					  alert("반찬은 최대 2종류 입니다.")
					   Swal.fire({
		                 title: '경고',
		                 text: "반찬은 최대 2종류 입니다.",
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
				 td.val("");
				 td.html("");
				 rice = $("#rice option:selected").attr("id");
				 riceValue =  $("#rice option:selected").text();
				 kim = $("#kim option:selected").attr("id");
				 kimValue =  $("#kim option:selected").text();
				 gook = $("#gook option:selected").attr("id");
				 gookValue =  $("#gook option:selected").text();
				 ban = $(".bl").val();
				 var ban1 = ban.toString().split(',');
// 				 bar1 = ban1[1];
				
// 				 var bannnn = $(".bl option:selected").text();

				var select = $("option[name=banName]");
	            var selectedList = [];

    	        for (var sele = 0; sele < select.length; sele++) {
        	        if (select[sele].selected) {
//         	        	alert(select[sele].text);
            	        selectedList.push(select[sele].text);
                	}
           		}	 
				 //선택한 반찬 1
				 ban = ban1[0];
				 //선택한 반찬2
				 ban2 = ban1[1]
				 td.append(
// 						 valendarMonth+"/"+calendarDay+
						 "<br>"+
						 "<input type='hidden' name='menuId' value ="+rice+"> <p id='"+rice+"'>"+riceValue+"</p>"
						 +"<input type='hidden' name='menuId' value="+kim+"><p id='"+kim+"'>"+kimValue+"</p> "
						 +"<input type='hidden' name='menuId' value="+gook+"><p id='"+gook+"'>"+gookValue+"</p>"
						 +"<input type='hidden' name='menuId' value="+ban+"><p id='"+ban+"'>"+selectedList[0]+"</p>"
						 +"<input type='hidden' name='menuId' value="+ban2+"><p id='"+ban2+"'>"+selectedList[1]+"</p>");
				index++;
				$("#myModal").modal('hide');
			})
	 var value ;
	 $('input[name="radio"]').change(function(){
		 $("#dietform").html("");
		 $("#fromDate").val("");
		 $("#toDate").val("");
		 value = $(this).val();
	 })
	 
	 $("#rice").change(function(){
		var test = $("#rice option:selected").attr("class");
		if (test == "allergy") {
			$(".archeck").html("&nbsp;알레르기 유발 메뉴입니다.");
		}else{
			$(".archeck").html("");
		}
	})

	$("#gook").change(function(){
		var test = $("#gook option:selected").attr("class");
		if (test == "allergy") {
			$(".agcheck").html("&nbsp;알레르기 유발 메뉴입니다.");
		}else{
			$(".agcheck").html("");
		}
	})
	
	$("#kim").change(function(){
		var test = $("#kim option:selected").attr("class");
		if (test == "allergy") {
			$(".akcheck").html("&nbsp;알레르기 유발 메뉴입니다.");
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
				$(".abcheck").html("&nbsp;알레르기 유발 메뉴입니다.");
			}else{
				$(".abcheck").html("");
			}
		num = $(this).children(":selected").length;
		 
	})
	
	$("#add").on("click",function(){
		var size = $("#tform p").length;
		period = (Number(period));
		if (((period+1)*5)!=size) {
			 Swal.fire({
                 title: '경고',
                 text: "비어있는 날짜가있습니다. 확인해주세요.",
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
		$("#checkModal").modal("show");
		$("#pass").hide();
	})
	
	$('input[name="authorId"]').change(function() {
        var value = $(this).val();       
        if(value=="N"){
        	$("#pass").show();
        }else{
        	$("#pass").hide();
        }
	});
	 
	 $("#ok").on("click",function(){
			var title = $("#title").val();
			var use = $(':radio[name="authorId"]:checked').val();
			var pass = $("#monpass").val();
			var td = $(".diet").children("p");
			var MenuArray = new Array(); // 초기 메뉴아이디 배열
			td.each(function(i){
				MenuArray.push(td.eq(i).attr("id"));
				console.log(MenuArray);
			});
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
				url : "${cPath}/diet/dietMonthlyInsert.do",
				method : "post",
				async: false,
				data : {
					"use" : use,
					"title" : title,
					"pass" : pass
				},
				dataType : "json",
				success : function(resp) {
					$.ajax({
						url : "${cPath}/diet/dietOnedayInsert.do",
						method : "post",
						data :{
							"MenuArray" : MenuArray
						},
						dataType : "json",
						traditional : true,
						success : function(resp) {
							location.href = "${cPath}/diet/dietList.do";
						},
						error : function(xhr) {
							console.log(xhr.status);
						}
					});
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
			$("#checkModal").modal('hide');
	 });
	 
	 $("#back").on("click",function(){
		 history.back();
	 });
	 


	 $("#dietform").on("hover",".t",function(){
    	  $(this).css({
    	  "background-color":"transparent",
    	  "color":"#2F4F4F",
    	  "font-size":"20px"})
      },function(){
    	  $(this).css({
    		 "background-color": "transparent",
    	  	"color":"#555",
    	  	"font-size":"18px"})
      })


 
})
</script>