<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 22.      이유진      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
    

<!-- <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script> -->

<security:authorize access="isAuthenticated()">
<security:authentication property="principal.bNick" var="nick"/>



    <!-- 웹 소켓 사용해서 현재 몇개의 쪽지가 도착했는지 구해오기. --> 

    <script type="text/javascript">
//     var sock= new SockJS("<c:url value="/echo"/>");
    var wsUri = "http://localhost/ai/echo";

    function send_message() {

        websocket = new WebSocket(wsUri);

        websocket.onopen = function(evt) {

            onOpen(evt);

        };

        websocket.onmessage = function(evt) {

            onMessage(evt);

        };

        websocket.onerror = function(evt) {

            onError(evt);

        };

    }

   

    function onOpen(evt) 

    {

       websocket.send("${nick}");

    }

    function onMessage(evt) {

            $('#count').append(evt.data);

    }

    function onError(evt) {

    }

    $(document).ready(function(){

            send_message();

    });

            

        </script>
  </security:authorize>

<div id="InnerContainer">
    <span id="count" class="badge bg-theme">00</span>
</div>    