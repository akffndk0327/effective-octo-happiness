<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 21.      이유진      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
    

<%-- <script type="text/javascript" src="<c:url value="/static/js/sockjs-0.3.4.js"/>"></script> --%>
<script type="text/javascript" src="<c:url value="/js/sockjs.js"/>"></script>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
 
<div id="InnerContainer">
    <input type="text" id="message"/>
    <input type="button" id="sendBtn" value="전송"/>
    <div id="data"></div>

</div>

<script type="text/javascript">
 
    $(document).ready(function(){
        $("#sendBtn").click(function(){
            sendMessage();
        });
    });
    
    //websocket을 지정한 URL로 연결
//     var sock= new SockJS("<c:url value="/echo"/>");
    //websocket 서버에서 메시지를 보내면 자동으로 실행된다.
    sock.onmessage = onMessage;
    //websocket 과 연결을 끊고 싶을때 실행하는 메소드
    sock.onclose = onClose;
    
    
    function sendMessage(){
            //websocket으로 메시지를 보내겠다.
            sock.send($("#message").val());
        
    }
            
    //evt 파라미터는 websocket이 보내준 데이터다.
    function onMessage(evt){  //변수 안에 function자체를 넣음.
        var data = evt.data;
//         $("#data").append(data+"<br/>");
        console.log(data);
        alert(data);
//         console.log($('#noteCount').html());
//         $('#noteCount').html(data);
        $('#'+data).submit();
        /* sock.close(); */
    }
    
    function onClose(evt){
        $("#data").append("연결 끊김");
    }
    
</script>
 

