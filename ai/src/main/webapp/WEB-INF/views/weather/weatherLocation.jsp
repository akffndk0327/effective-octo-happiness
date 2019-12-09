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

#InnerContainer {
	height: auto;
}

#air {
	height: 500px;
	overflow: auto;
	width: 1000px;
}

.air {
	/*  background-color: #dcdcdc; */
	border: 2px solid black;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	margin-left: 50px;
	margin-right: 50px;
}

.h6 {
	margin-block-start: 1.33em;
	margin-block-end: 0.03em;
	font-size: 25px;
}
</style>



<div id="InnerContainer">
	<section id="products">
		<div class="container">
			<div id="tab" class="col-md-9" style="width: 100%">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs">
					<li class="active" style="font-size: 20px;"><a href="#home"
						data-toggle="tab">미세먼지 예보</a></li>
					<li><a href="#profile" style="font-size: 20px;"
						data-toggle="tab">미세먼지 평균 농도</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="home" style="width: 80%;">
						<div class="titleLeftBar">오늘의 미세먼지 예보 관련 안내사항</div>
						<div id="air">
							<c:forEach items="${airguess }" var="air" varStatus="i">
								<div class="air">
								<div class="a" style="margin:20px;">
									<h4>* ${air.dataTime }</h4>
									<h6 class="h6">* 미세먼지예보</h6>
									<br> <span style="font-size: 18px;">${air.informOverall }<br>${air.informCause }</span><br>
									<h6 class="h6">* 전국 미세먼지 현황</h6>
									<c:forEach items="${air.informGrade }" varStatus="v">
										<c:if test="${v.index%5 eq 0 }">
											<br>
										</c:if>
										<span style="font-size: 18px;">${air.informGrade[v.index] }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
									</c:forEach>
								</div>
								</div>
								<br>
								<br>
							</c:forEach>
						</div>

					</div>
					<div class="tab-pane" id="profile">
						<br> <br> <br>
						<div class="titleLeftBar">
							시도별 미세먼지 농도 평균정보 조회<br> <span style="font-size: 17px;">
								* 좋음(0~30) &nbsp;&nbsp; 보통(31~80) &nbsp;&nbsp; 나쁨(81~100)
								&nbsp;&nbsp; 매우나쁨(151~)<br>
							</span>
						</div>
						<div>
							<canvas id="myChart" width="400" height="100"></canvas>
							<div id="weather">
								<c:forEach items="${avgair }" var="avg">
									<input type="hidden" class="dataTime" value="${avg.dataTime }">
									<input type="hidden" class="seoul" value="${avg.seoul }">
									<input type="hidden" class="busan" value="${avg.busan }">
									<input type="hidden" class="daegu" value="${avg.daegu }">
									<input type="hidden" class="incheon" value="${avg.incheon}">
									<input type="hidden" class="gwangju" value="${avg.gwangju}">
									<input type="hidden" class="daejeon" value="${avg.daejeon}">
									<input type="hidden" class="ulsan" value="${avg.ulsan}">
									<input type="hidden" class="gyeonggi" value="${avg.gyeonggi}">
									<input type="hidden" class="gangwon" value="${avg.gangwon}">
									<input type="hidden" class="chungbuk" value="${avg.chungbuk}">
									<input type="hidden" class="chungnam" value="${avg.chungnam}">
									<input type="hidden" class="jeonbuk" value="${avg.jeonbuk}">
									<input type="hidden" class="jeonnam" value="${avg.jeonnam}">
									<input type="hidden" class="gyeongbuk" value="${avg.gyeongbuk}">
									<input type="hidden" class="gyeongnam" value="${avg.gyeongnam}">
									<input type="hidden" class="jeju" value="${avg.jeju}">
									<input type="hidden" class="sejong" value="${avg.sejong}">
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>



<script>
	//막대그래프      

	const arrayOfDiv = [];
	var contentDiv = document.getElementById("weather");
	const contentObject = contentDiv.childNodes;
	var list1, list2, list3, list4, list5, list6, list7;

	for (let i = 0; i < contentObject.length; i++) {
		if (contentObject[i].nodeName == "INPUT") {
			arrayOfDiv.push(contentObject[i].value);
		}
	}

	list1 = arrayOfDiv.slice(0, 18);
	list2 = arrayOfDiv.slice(18, 36);
	list3 = arrayOfDiv.slice(36, 54);
	//                list4=arrayOfDiv.slice(54,72);
	//                list5=arrayOfDiv.slice(72,90);
	//                list6=arrayOfDiv.slice(90,108);
	//                list7=arrayOfDiv.slice(108,126);

	var barChartData = {
		labels : [//list7[0],list6[0],list5[0],list4[0],
		list3[0], list2[0], list1[0] ],
		datasets : [ {
			label : '서울',
			backgroundColor : "#263959",
			data : [
			//                    list7[1],
			//                    list6[1],
			//                    list5[1],
			//                    list4[1],
			list3[1], list2[1], list1[1] ]
		}, {
			label : '부산',
			backgroundColor : "#6d819c",
			data : [
			//                    list7[2],
			//                       list6[2],
			//                       list5[2],
			//                       list4[2],
			list3[2], list2[2], list1[2] ]
		}, {
			label : '대구',
			backgroundColor : "#58C9B9",
			data : [
			//                    list7[3],
			//                       list6[3],
			//                       list5[3],
			//                       list4[3],
			list3[3], list2[3], list1[3] ]
		}, {
			label : '인천',
			backgroundColor : "#fbd14b",
			data : [
			//                       list7[4],
			//                       list6[4],
			//                       list5[4],
			//                       list4[4],
			list3[4], list2[4], list1[4] ]
		}, {
			label : '광주',
			backgroundColor : "#77AF9C",
			data : [
			//                       list7[5],
			//                       list6[5],
			//                       list5[5],
			//                       list4[5],
			list3[5], list2[5], list1[5] ]
		}, {
			label : '대전',
			backgroundColor : "#519D9E",
			data : [
			//                       list7[6],
			//                       list6[6],
			//                       list5[6],
			//                       list4[6],
			list3[6], list2[6], list1[6] ]
		}, {
			label : '울산',
			backgroundColor : "#011638",
			data : [
			//                       list7[7],
			//                       list6[7],
			//                       list5[7],
			//                       list4[7],
			list3[7], list2[7], list1[7] ]
		}, {
			label : '경기',
			backgroundColor : "#9055A2",
			data : [
			//                       list7[8],
			//                       list6[8],
			//                       list5[8],
			//                       list4[8],
			list3[8], list2[8], list1[8] ]
		}, {
			label : '강원',
			backgroundColor : "#77AAAD",
			data : [
			//                       list7[9],
			//                       list6[9],
			//                       list5[9],
			//                       list4[9],
			list3[9], list2[9], list1[9] ]
		}, {
			label : '충북',
			backgroundColor : "#00b9f1",
			data : [
			//                       list7[10],
			//                       list6[10],
			//                       list5[10],
			//                       list4[10],
			list3[10], list2[10], list1[10] ]
		}, {
			label : '충남',
			backgroundColor : "#ef5285",
			data : [
			//                       list7[11],
			//                       list6[11],
			//                       list5[11],
			//                       list4[11],
			list3[11], list2[11], list1[11] ]
		}, {
			label : '전북',
			backgroundColor : "#7200da",
			data : [
			//                       list7[12],
			//                       list6[12],
			//                       list5[12],
			//                       list4[12],
			list3[12], list2[12], list1[12] ]
		}, {
			label : '전남',
			backgroundColor : "#a7a7a2",
			data : [
			//                       list7[13],
			//                       list6[13],
			//                       list5[13],
			//                       list4[13],
			list3[13], list2[13], list1[13] ]
		}, {
			label : '경북',
			backgroundColor : "#e94e77",
			data : [
			//                       list7[14],
			//                       list6[14],
			//                       list5[14],
			//                       list4[14],
			list3[14], list2[14], list1[14] ]
		}, {
			label : '경남',
			backgroundColor : "#fbd14b",
			data : [
			//                       list7[15],
			//                       list6[15],
			//                       list5[15],
			//                       list4[15],
			list3[15], list2[15], list1[15] ]
		}, {
			label : '제주',
			backgroundColor : "#444f59",
			data : [
			//                       list7[16],
			//                       list6[16],
			//                       list5[16],
			//                       list4[16],
			list3[16], list2[16], list1[16] ]
		}, {
			label : '세종',
			backgroundColor : "#fd999a",
			data : [
			//                       list7[17],
			//                       list6[17],
			//                       list5[17],
			//                       list4[17],
			list3[17], list2[17], list1[17] ]
		}

		]

	};

	new Chart(document.getElementById("myChart"), {
		type : 'bar',
		data : barChartData,
		options : {
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true
					}
				} ],
				xAxes : [ {
					gridLines : {
						offsetGridLines : true
					}
				} ]
			},
			  display: false,
            legend:{
                labels:{
                    fontSize:15,
                }
            }
		}
	});
</script>
</div>