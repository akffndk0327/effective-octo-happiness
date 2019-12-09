<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*   2019.11.10      박슬기   공유기능추가
* ----------  ---------  -----------------
* 2019. 11. 6.      박슬기      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
<style type="text/css">
@font-face {
	font-family: 'Eoe_Zno_L';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_eight@1.0/Eoe_Zno_L.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
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
.table-curved{
	width: 1500px;
	font-family: 'Eoe_Zno_L';
	
}
th{
   border: 2px solid #548687;
	border-bottom: 2px solid #548687;
}
td{

   border: 2px solid #548687;
   border-top: 2px solid #548687;
}

#btnLo {
   text-align: left;
}

.share{ 
    width: 50px; 
    height: 50px; 
 } 
 #btn{
 	text-align: right;
 }
 .shareBtn{
 	display: inline;
 	float: left;
 	cursor : pointer;
 }
 .dietBtn{
 	display: inline;
 	float: right;
 }
 

</style>


<!--    <meta property="og:title" content="AI"> -->
<!--    <meta property="og:description" content="공유테스트"> -->
<!--    <meta property="og:url" content="https://localhost/ai/diet/dietView.do?monthlyId=M0009&memId=r001"> -->
   
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>KakaoLink v2 Demo(Default / List) - Kakao JavaScript SDK</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<div id="InnerContainer" style="width: 1500px">
   <c:set var="diet" value="${o_list}" />
   <br><br>
     <div style='display: inline'>
	   <h1 class="titleTopBar" style="display: inline; float: left" >SMART식단</h1>
		   <div class="shareBtn" style="display: inline; float: right">
		       <a id="kakao-link-btn" href="javascript:;">
		         <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" class="share"/>
		       </a>
		         <img src="${pageContext.request.contextPath }/images/facebook.png" class="share facebook" >
		   </div>
	  </div>
	  <br>
	  <br><br>
	  
	<div style="position: absolute; top: 426px; left: 217px; font-size:29px;">
		작성자 : ${writer}<br>
		제목 : ${diet[0].monthlyTitle}<br>
		보유 알레르기 :<c:if test="${fn:length(n_list) eq  0}">
						알레르기 없음
					</c:if>
					<span style="color: red;">
					<c:if test="${fn:length(n_list) ne  0}">
						<c:forEach items="${n_list}" var="n" varStatus="i">
							${n.allName }
							<c:if test="${i.index ne fn:length(n_list)-1}">
							,
							</c:if>
						</c:forEach >
					</c:if>
					</span>
	</div>
	<br><br><br><br><br><br><br><br>
   <table class="table-curved">
   <tbody>
      <c:forEach items="${diet}" var="d" begin="0" varStatus="i" step="1">
         <c:if test="${(i.index%35) eq 0}">
            <tr>
         </c:if>
         <c:if test="${(i.index%5) eq 0}">
            <td class="t diet" id="${d.onedayId }">
         </c:if><p id="${d.menuId}">${d.menuName}</p>
         <c:if test="${(i.index%5) eq 4}">
            </td>
         </c:if>
         <c:if test="${(i.index%35) eq 34}">
            </tr>
         </c:if>
      </c:forEach>
   </tbody>
   </table>
   <br>
   <br>
            <c:if test="${writer eq loginId}">
               <c:url value="/diet/dietUpdate.do" var="updateURL">
                  <c:param name="monthlyId" value="${diet[0].monthlyId}"/>
                  <c:param name="memId" value="${writer}"/>
               </c:url>
               <c:url value="/diet/dietDelete.do" var="deleteURL">
                  <c:param name="monthlyId" value="${diet[0].monthlyId}"/>
               </c:url>
               <button type="button" class="btn btn-outline-success btnAi0" id="del">삭제하기</button>
               <button type="button" class="btn btn-outline-success btnAi0" onclick="location.href='${updateURL}';">수정하기</button>
            </c:if>
               <button type="button" class="btn btn-outline-success btnAi1" id="back" style="float: left">뒤로가기</button>
               <input type="hidden" value="${diet[0].monthlyId}" id="monId"/>

   <br><br>
</div>

 <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="checkModal">
    <div class="modal-dialog modal-sm" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="mySmallModalLabel">삭제 확인</h4>
        </div>
        <div class="modal-body" style="text-align: center">
          <h6 style="margin: 0">
             삭제된 데이터는 복구 불가능합니다.<br>
             삭제하시겠습니까?
          </h6>
          <br>  <br>
         <button type="button" class="btn btn-primary">확인</button>
         <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        </div>

      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

<script type="text/javascript">
   $(function(){
      $("#back").on("click",function(){
         history.back();
      })
      var url = location.href;
      var param = url.substring(url.indexOf('?')+1, url.length);
      $(".facebook").on("click",function(){
         window.open("http://www.facebook.com/sharer/sharer.php?u=https://192.168.206.35/ai/diet/dietView.do?"+ encodeURIComponent(param),
        		 'Share on Facebook','scrollbars=no, width=500, height=500');

      })//facebook end
      
      
      Kakao.init("6c959fda57f7b84bb8fbbbc911356a80");
       // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
       Kakao.Link.createDefaultButton({
            container: '#kakao-link-btn',
            objectType: 'feed',
            content: {
              title: 'AI식단 공유',
              description: '#AI #ai #식단공유 #식단 #알레르기 #검색',
              imageUrl: 'https://miro.medium.com/max/1200/1*LhyCwtrewwc4q4OW6Ucrog.png',
              link: {
                mobileWebUrl: 'https://developers.kakao.com',
                webUrl: "https://localhost/ai/diet/dietView.do?" + param
              }
            },
            buttons: [
              {
                title: '홈페이지가기',
                link: {
                  mobileWebUrl: "https://localhost/ai",
                  webUrl: "https://localhost/ai/"
                }
              },
              {
                title: '게시글보기',
                link: {
                  mobileWebUrl: "https://localhost/ai/diet/dietView.do?" +param,
                  webUrl: "https://localhost/ai/diet/dietView.do?" + param
                }
              }
            ]
          }); //kakao end
   
          
      $("#del").on("click",function(){
         $("#checkModal").modal("show");
         $(".btn-primary").on("click",function(){
            var monId = $("#monId").val();
//             alert(monId);
            window.location.href = "${cPath}/diet/dietDelete.do?monId="+monId;
         })
      })
      
      $("td").hover(function(){
    	  $(this).css({
    	  "color":"#2F4F4F",
    	  "font-size":"22px"})
      },function(){
    	  $(this).css({
    	  	"color":"#555",
    	  	"font-size":"20px"})
      })
    })//function end
//       
</script>