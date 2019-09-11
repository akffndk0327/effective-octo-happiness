<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
      <title>Insert title here</title>
   </head>
   <body>
      <pre>
         클라이언트 현재 시간 : <span id="client"></span> 
         서버의 현재 시간 : <span id="server"></span> 
      </pre>
      <script type="text/javascript">
         var client = document.querySelector("#client");
         var server = document.querySelector("#server");
         setInterval(() => {
            var now = new Date();
            client.innerHTML = now;
         $.ajax({
            url : 'getServerTime.jsp',
//             method:"get",
//             data:"param=value&param2=value2",
            dataType : 'json',   //accept:text/plain
                           //json인 경우 accept : application/json ==Content-Type(응답헤더)
            success:function(resp){
               
               server.innerHTML=resp.time;
            },
            error:function(errorResp){
               console.log(errorResp.status);
            }
         });
         }, 1000);
         
      </script>
   </body>
</html>