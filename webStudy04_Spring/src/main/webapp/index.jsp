<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<form name="logoutForm" method="post"
		action="${pageContext.request.contextPath}/logout"></form>
	<c:choose>
		<c:when test="${not empty authMember }">
			<a href="${cPath }/mypage">
				${authMember.mem_name}님,${authMember.mem_role }</a>
			<a href="#" onclick="document.logoutForm.submit();">로그아웃</a>
			<!-- a태그이지만 post방식 임  -->
			<div>
				<h4>접속자 리스트</h4>
				<ul id="userListArea">

				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<a href="${cPath}/login"> 로그인</a>
			<a href="${cPath}/member/memberInsert.do">가입하기</a>
		</c:otherwise>
	</c:choose>
	 <button id="connectBtn">connect</button>
   <button id="closeBtn">close</button>
   <input type="text" id="msgInput" />
   <button id="sendBtn">send</button> <br />
   <div id="messageArea">
   
   </div>
   <script type="text/javascript">
// ws://demos.kaazing.com/echo
   var webSocket = null;
   var connectBtn = $("#connectBtn");
   var closeBtn = $("#closeBtn").prop("disabled",true);
   var sendBtn = $("#sendBtn").prop("disabled", true);
   var msgInput = $("#msgInput");
   var messageArea = $("#messageArea");
   
   sendBtn.on("click", function(){
     let msg = msgInput.val();
     messageArea.append(
       $("<p>").text("me : " + msg)     
     );
     webSocket.send(msg);
   });
   closeBtn.on("click",function(){
      webSocket.close();
   });
   
   connectBtn.on("click",function(){
         webSocket = new WebSocket("ws://localhost${cPath}/echo");
         webSocket.onopen=function(event){
            console.log("연결수립");
            connectBtn.prop("disabled", true);
            closeBtn.prop("disabled", false);
            sendBtn.prop("disabled", false);
         }
         
         webSocket.onclose=function(event){
            console.log("연결종료");
            connectBtn.prop("disabled", false);
            closeBtn.prop("disabled", true);
            sendBtn.prop("disabled", true);
         }
         
         webSocket.onmessage=function(event){
            console.log(event);
            let msg = event.data;
            messageArea.append(
                  $("<p>").text("echo : " + msg).css("color", "red")     
              );
         }
         
         webSocket.onerror=function(event){
            console.log(event);
         }
   })

</script>

</body>
</html>















