<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 6.      이유진      최초작성
* 2019. 11. 11.      이유진      
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<c:set value="${x }" var="x"></c:set>
<c:set value="${y }" var="y"></c:set>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6c959fda57f7b84bb8fbbbc911356a80"></script>
<script>
     var x="${y}";
     var y="${x}";

    $(function() {
     var container = document.getElementById('map');
     var options = {
         center: new kakao.maps.LatLng(x, y),
         level: 8
     };
     
     var map = new kakao.maps.Map(container, options);


     
   //위경도 주소로 바꾸기
     $.ajax({
         url :'//dapi.kakao.com/v2/local/geo/coord2regioncode.json?x='+y+'&y='+x,
         method :"get",
         headers: {
             'Authorization': 'KakaoAK 91e1cb3bf5f287f0013302b4f8d49a8c'
         },
         dataType : "json",
         async:false,
         success : function(resp) {//가져온 좌표 뿌리기
             addr = new Array();
             addr[0]=resp.documents[0].address_name;
             addr[1]=resp.documents[0].region_1depth_name;
             addr[2]=resp.documents[0].region_2depth_name;
             addr[3]=resp.documents[0].region_3depth_name;
             addr[4]=resp.documents[0].x;
             addr[5]=resp.documents[0].y;
             
             
//              $('#addr0').val(addr[0]);
//              $('#addr1').val(addr[1]);
//              $('#addr2').val(addr[2]);
//              $('#addr3').val(addr[3]);
//              $('#addr4').val(addr[4]);
//              $('#addr5').val(addr[5]);
             
         },
         error : function(xhr) {
             console.log(xhr.status);
         }
     });
     
    });
    </script>
<div id="InnerContainer">

	<div id="map" style="width: 500px; height: 400px;"></div>
	<span>${x }</span><br>
	<span>${y }</span>
</div>