<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 1.  박주연      최초작성
* 2019. 11. 1.  이유진      알림을 위한 form 추가
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	#siteMap{
		font-size: 30px;
	}
</style>
<!-- <footer> -->
	<div class="container">
		<div class="row">
        <div class="col-md-4" style="margin-left: -127px;  margin-top: 12px;">
				<div class="block clearfix">
					<a href="#"> <img src="${cPath }/images2/footerlogo.png" alt="">
					</a>
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text ever since the 1500s</p>
				</div>
				<!-- End Of /.block -->
			</div>
			<!-- End Of /.Col-md-4 -->
			<div class="col-md-4" style="margin-left: 20px;">
				<div class="block">
					<h4>GET IN TOUCH</h4>
					<p>
						<i class="fa  fa-map-marker"></i> <span>Allergy Infomation Center</span> 206, youngminBuilding 
						500 DeahungDong zhonggu Daejeon Korea
					</p>
					<p>
						<i class="fa  fa-phone"></i> <span>Phone:</span> 042 330 1234
					</p>

					<p>
						<i class="fa  fa-mobile"></i> <span>Mobile:</span> 010 1234 5789
					</p>

					<p class="mail">
						<i class="fa  fa-envelope"></i> <span>Eamil: </span> aiinfo@aicenter.com
					</p>
				</div>
				<!-- End Of /.block -->
			</div>
			<div class="col-md-4" id="siteMap">
			<br>
				<div style="text-align: center; width: 300px;margin-bottom: 15px;">SITE MAP</div><select>
				<br>
							<option value="https://www.foodsafetykorea.go.kr/main.do">식품안전나라</option>
							<option value="http://koreanfood.rda.go.kr/main">농식품종합정보시스템</option>
							<option value="https://www.data.go.kr">공공데이터 포털</option>
							<option value="https://iaqinfo.nier.go.kr">생활환경정보센터</option>
						 </select>
			</div>
			<!-- End Of Col-md-4 -->
		</div>
		<!-- End Of /.row -->
	</div>
	<!-- End Of /.Container -->

<!-- </footer> -->

<!-- 챗봇 -->
<div id="frogue-container" class="position-right-bottom"
      data-chatbot="8ff0ce63-f814-4417-8639-be740f8104f9"
      data-user="404notfounderr"
      data-init-key="value"
      ></div>
<!-- data-init-식별키=값 으로 셋팅하면 챗플로우에 파라미터와 연동가능. 식별키는 소문자만 가능 -->
<script>
(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "https:\/\/danbee.ai/js/plugins/frogue-embed/frogue-embed.min.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'frogue-embed'));

$(function(){
	$("#siteMap").change(function(){
		var siteMap = $("#siteMap option:selected").val();
		window.location.href = siteMap;
	})
})
</script>