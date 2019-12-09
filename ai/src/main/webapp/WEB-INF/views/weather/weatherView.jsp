<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 4.      박슬기      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>


<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script> -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<style>
@font-face {
	font-family: 'Eoe_Zno_L';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_eight@1.0/Eoe_Zno_L.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

[class^="1"] {
	border-radius: 40px 80px 90px 80px;
    position: absolute;
    width: 90px;
    height: 154px;
    top: 516px;
    left: 529px;
    font-size: 20px;
    font-family: 'Eoe_Zno_L';
}

[class^="2"] {
	border-radius: 49px 319px 90px 236px;
    position: absolute;
    width: 134px;
    height: 165px;
    top: 501px;
    left: 621px;
    font-size: 20px;
    font-family: 'Eoe_Zno_L';
}

[class^="3"] {
	border-radius: 405px 225px 229px 0px;
    position: absolute;
    width: 130px;
    height: 188px;
    top: 766px;
    left: 500px;
    font-size: 20px;
    font-family: 'Eoe_Zno_L';
}

[class^="4"] {
	border-radius: 20px 3px 23px 73px;
    position: absolute;
    width: 143px;
    height: 96px;
    top: 671px;
    left: 500px;
    font-size: 20px;
    font-family: 'Eoe_Zno_L';
}


[class^="5"] {
	    position: absolute;
    width: 76px;
    height: 36px;
    top: 1053px;
    left: 480px;
    border-radius: 74px 52px 80px 80px;
    font-size: 20px;
    font-family: 'Eoe_Zno_L';
}

[class^="6"] {
	position: absolute;
    width: 150px;
    height: 237px;
    top: 662px;
    left: 652px;
    border-radius: 111px 20px 113px 0px;
    left: 644px;
    font-size: 20px;
    font-family: 'Eoe_Zno_L';
}

.canvas {
	text-align: right;
	width: 500px;
}

#map {
  	position:absolute;
	width: 500px;
    top: 474px;
    left: 339px;
}

.localTotal {
	text-align: left;
	width: 450px;
	height: 1000px;
	display: inline;
	
}

.local, .canvas {
	display: inline;
	float: left;
	width: 40%;
}
.canvas {
    position: absolute;
/*     display: block; */
 	width: 550px;
    height: 550px;
   right: 375px;
    top: 490px;


}
.localDesc{
	position: absolute;
    top: 416px;
    left: 365px;
    color: #E65C44;
    font-size: 20px;
    z-index: 10;
}

#InnerContainer {
	height: 600px;
}

#localTow{
	position: absolute;
    top: 50px;
    left: 20px;
}

#localFour{
	position: absolute;
    top: 30px;
    left: 47px;
}
#localThree{
	position: absolute;
    top: 64px;
    left: 34px;
}
#localFive{
	position: absolute;
    top: 5px;
    left: 87px;
    width: 200px;
}

</style>
<div id="InnerContainer">
	<h2 style="margin: 0 auto;" class="titleTopBar">대기내 중금속 현황</h2>
	<br>
	<br>
	<br>
	<div class="localDesc">
		* 지역을 선택하시면 해당 지역의 상세 날씨 정보를 확인하실 수 있습니다.
	</div>
	<div class="localTotal">
		<div class="local">
			<img src="${pageContext.request.contextPath }/images/map.jpg"
				id="map">
			<!-- 	</div> -->
			<div class="1 div" id="1">
				<br>
				<br>&nbsp;&nbsp;<a>${weather[0]}</a>
			</div>
			<div class="2 div" id="2">
				<br>
				<br><a id="localTow">${weather[1]}</a>
			</div>
			<div class="3 div" id="3">
				<a id="localThree">${weather[2]}</a>
			</div>
			<div class="4 div" id="4">
				<a id="localFour">[중부권, 40.454]</a>
			</div>
			<div class="5 div" id="5">
				<a id="localFive">${weather[4]}</a>
			</div>
			<div class="6 div" id="6">
				<br><br><br>
				<Br> <a>${weather[5]}</a>
			</div>
		</div>
		<div class="canvas">
			<canvas id="canvas" height="250"></canvas>
		</div>
	</div>

</div>
<br><br><br><br><br><br>
<script type="text/javascript">
	$(function() {
		var blue = "blue"
		$(".div").on("click", function() {
			var location = $(this).prop("id");
			let area = $(this).children().text().substring(1, 4);

			$("#modal").modal('toggle');
			$.ajax({
				url : "${cPath}/weather/weatherView.do",
				method : "post",
				data : {
					"location" : location
				},
				dataType : "json",
				success : function(resp) {
// 					if (!resp.Ca) {
// 						alert("현재 제공되지 않는 데이터 입니다. 나중에 다시 시도해주세요.");	
// 						return;
// 					}
					$("#c").html("");
					$("#p").html("");
					new Chart(document.getElementById("canvas"), {
						type : 'bar',
						data : {
							labels : [ '칼슘', '납' ],
							datasets : [ {
								label : '함유량',
								data : [ resp.Ca, resp.Pb ],
								borderColor : resp.color,
								backgroundColor : resp.color,
								fill : false,
							} ]
						},
						options : {
							responsive : true,
							title : {
								text : area + ' 대기 내 중금속 현황',
								display : true,
								fontSize:20
							},
							scales : {
								xAxes : [ {
									display : true,
									scaleLabel : {
										display : true,
										labelString : '중금속 종류',
										fontSize:20,
										fontStyle:'Eoe_Zno_L'
									},
									ticks : {
										autoSkip : false,
										fontSize:20,
										fontStyle:'Eoe_Zno_L'
									}
								} ],
								yAxes : [ {
									display : true,
									ticks : {
										suggestedMin : 0,
										fontSize:20,
										fontStyle:'Eoe_Zno_L'
									},
									scaleLabel : {
										display : true,
										labelString : 'ng/m3',
										fontSize:20,
										fontStyle:'Eoe_Zno_L'
									}
								} ]
							},
							legend:{
								labels:{
									fontSize:20,
									fontStyle:'Eoe_Zno_L'
								}
							}
						}
					});
				},
				error : function(xhr) {
					console.log(xhr.status);
				}
			});
		})
	})
</script>