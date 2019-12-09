<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.   허민지      최초작성
* 2019. 11. 7.   허민지      로그인 상태 제거
* 2019. 11. 11	 박주연	css 수정 
* 2019. 11. 12	박주연	메인슬라이드 구현
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
	#one,#two,#three,#four,#five{
		float:left;
	}
	
	#tab{
		width:100%
	}
	
/* 	#canvas { */
/*   		 background-image: url("${cPath}/images/test.png"); */
/*   		 background-size:100%; */
/* 	} */
	
</style>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="path/to/chartjs/dist/Chart.js"></script>
<link rel="stylesheet" type="text/css" href="path/to/chartjs/dist/Chart.min.css">
<link rel="stylesheet" type="text/css" href="${cPath }/css/index.css">


<!-- 슬라이드 -->
<!-- <div id="mainback"> -->
	<section id="slider-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div id="slider" class="nivoSlider" style="width: 1850px; height: 550px; ">
						<img src="${cPath }/images2/open.jpg" alt="" style="width: 1850px; height: 550px; "/> 
						<img src="${cPath }/images2/sale.jpg" alt="" style="width: 1850px; height: 550px; "/> 
						<img src="${cPath }/images2/education.jpg" alt="" style="width: 1850px; height: 550px; "/>
					</div>
					<!-- End of /.nivoslider -->
				</div>
				<!-- End of /.col-md-12 -->
			</div>
			<!-- End of /.row -->
		</div>
		<!-- End of /.container -->
	</section>
<!-- </div> -->
<!-- End of Section -->

<!-- ranking -->
<!-- <table id="topChartTable"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 			<table id="keyword"> -->
<!-- 				<tr> -->
<!-- 				<td style="font-size:25px;" colspan="4">실시간 검색어 순위</td> -->
<!-- 				</tr> -->
<%-- 				<c:forEach varStatus="vs" items="${result }" begin="0" end="9" var="r"> --%>
<!-- 					<tr> -->
<%-- 						<td>${vs.count }</td> --%>
<!-- 						<td>&nbsp;&nbsp;&nbsp;</td> -->
<%-- 						<td>${r.searchName }</td> --%>
<!-- 						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> --> 
<%-- 						<td><img src="${cPath }/images/a${vs.count }.png" /></td> --%>
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</table> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- PRODUCTS Start
    ================================================== -->

<section id="products">
	<div class="container">
	<div id="tab" class="col-md-9">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active" style="font-size:20px;"><a href="#home" data-toggle="tab">지역별</a></li>
			<li><a href="#profile" style="font-size:20px;" data-toggle="tab">나이별</a></li>
			<li><a href="#age" style="font-size:20px;" data-toggle="tab">성별</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane active" id="home" style="width:100%;" >
				<canvas id="canvas" height="200"></canvas>
			</div>
			<div class="tab-pane" id="profile"> 
				<br><br><br>
				<div id="one" style="width:33%;">
					<canvas id="cxt" height="300"></canvas>	
				</div>
				<div id="two" style="width:33%;">
					<canvas id="cxt2" height="300"></canvas>
				</div>
				<div id="three" style="width:33%;">
					<canvas id="cxt3" height="300"></canvas>
				</div><br><br><br><br><br><br><br><br><br><br>
				<br><br>
				<div style="margin-left:160px;">
					<div id="four" style="width:40%;">
						<canvas id="cxt4" height="310"></canvas>
					</div>
					<div id="five" style="width:40%;">
						<canvas id="cxt5" height="310"></canvas>
					</div>
				</div>
		  </div>
		  <div class="tab-pane age" id="age" style="width:100%;">
				<canvas id="cxt6" height="200"></canvas>
		</div>
	</div>
	<!-- End of /.container -->
	<input name="seoul" type="hidden" value="${seoulAsthma }"/>
	<input name="busan" type="hidden" value="${busanAsthma}"/>
	<input name="daegu" type="hidden" value="${daeguAsthma }"/>
	<input name="gwangju" type="hidden" value="${gwangjuAsthma }"/>
	<input name="daejeon" type="hidden" value="${daejeonAsthma }"/>
	<input name="ulsan" type="hidden" value="${ulsanAsthma }"/>
	<input name="chungbuk" type="hidden" value="${chungbukAsthma }"/>
	<input name="jeonnam" type="hidden" value="${jeonnamAsthma }"/>
	<input name="jeju" type="hidden" value="${jejuAsthma }"/>
	
	<input name="seoulAtopy" type="hidden" value="${seoulAtopy }"/>
	<input name="busanAtopy" type="hidden" value="${busanAtopy}"/>
	<input name="daeguAtopy" type="hidden" value="${daeguAtopy }"/>
	<input name="gwangjuAtopy" type="hidden" value="${gwangjuAtopy }"/>
	<input name="daejeonAtopy" type="hidden" value="${daejeonAtopy }"/>
	<input name="ulsanAtopy" type="hidden" value="${ulsanAtopy }"/>
	<input name="chungbukAtopy" type="hidden" value="${chungbukAtopy }"/>
	<input name="jeonnamAtopy" type="hidden" value="${jeonnamAtopy }"/>
	<input name="jejuAtopy" type="hidden" value="${jejuAtopy }"/>
	
	<input name="seoulRhinitis" type="hidden" value="${seoulRhinitis }"/>
	<input name="busanRhinitis" type="hidden" value="${busanRhinitis}"/>
	<input name="daeguRhinitis" type="hidden" value="${daeguRhinitis }"/>
	<input name="gwangjuRhinitis" type="hidden" value="${gwangjuRhinitis }"/>
	<input name="daejeonRhinitis" type="hidden" value="${daejeonRhinitis }"/>
	<input name="ulsanRhinitis" type="hidden" value="${ulsanRhinitis }"/>
	<input name="chungbukRhinitis" type="hidden" value="${chungbukRhinitis }"/>
	<input name="jeonnamRhinitis" type="hidden" value="${jeonnamRhinitis }"/>
	<input name="jejuRhinitis" type="hidden" value="${jejuRhinitis }"/>
	
	<input name="oneAtopy" type="hidden" value="${oneAtopy }"/>
	<input name="oneAsthma" type="hidden" value="${oneAsthma}"/>
	<input name="oneRhinitis" type="hidden" value="${oneRhinitis }"/>
	
	<input name="twoAtopy" type="hidden" value="${twoAtopy }"/>
	<input name="twoAsthma" type="hidden" value="${twoAsthma}"/>
	<input name="twoRhinitis" type="hidden" value="${twoRhinitis }"/>
	
	<input name="thrAtopy" type="hidden" value="${thrAtopy }"/>
	<input name="thrAsthma" type="hidden" value="${thrAsthma }"/>
	<input name="thrRhinitis" type="hidden" value="${thrRhinitis }"/>
	
	<input name="fourAtopy" type="hidden" value="${fourAtopy }"/>
	<input name="fourAsthma" type="hidden" value="${fourAsthma }"/>
	<input name="fourRhinitis" type="hidden" value="${fourRhinitis }"/>
	
	<input name="fiveAtopy" type="hidden" value="${fiveAtopy }"/>
	<input name="fiveAsthma" type="hidden" value="${fiveAsthma }"/>
	<input name="fiveRhinitis" type="hidden" value="${fiveRhinitis }"/>
	
	<input name="woAtopy" type="hidden" value="${woAtopy }"/>
	<input name="woAsthma" type="hidden" value="${woAsthma }"/>
	<input name="woRhinitis" type="hidden" value="${woRhinitis }"/>
	<input name="woShell" type="hidden" value="${woShell }"/>
	<input name="woEgg" type="hidden" value="${woEgg }"/>
	<input name="woMilk" type="hidden" value="${woMilk }"/>
	<input name="woNut" type="hidden" value="${woNut }"/>
	
	<input name="manAtopy" type="hidden" value="${manAtopy }"/>
	<input name="manAsthma" type="hidden" value="${manAsthma }"/>
	<input name="manRhinitis" type="hidden" value="${manRhinitis }"/>
	<input name="manShell" type="hidden" value="${manShell }"/>
	<input name="manEgg" type="hidden" value="${manEgg }"/>
	<input name="manMilk" type="hidden" value="${manMilk }"/>
	<input name="manNut" type="hidden" value="${manNut }"/>
	
</section>
<!-- End of Section -->

<script>
	$(function(){
		$.ajax({
			url : "${cPath}/static/staticInsert.do",
			method : "post",
		});
		
	})
	var seoul = $("[name='seoul']").val();
	var busan = $("[name='busan']").val();
	var daegu = $("[name='daegu']").val();
	var gwangju = $("[name='gwangju']").val();
	var daejeon = $("[name='daejeon']").val();
	var ulsan = $("[name='ulsan']").val();
	var chungbuk = $("[name='chungbuk']").val();
	var jeonnam = $("[name='jeonnam']").val();
	var jeju = $("[name='jeju']").val();
	
	var seoulAtopy = $("[name='seoulAtopy']").val();
	var busanAtopy = $("[name='busanAtopy']").val();
	var daeguAtopy = $("[name='daeguAtopy']").val();
	var gwangjuAtopy = $("[name='gwangjuAtopy']").val();
	var daejeonAtopy = $("[name='daejeonAtopy']").val();
	var ulsanAtopy = $("[name='ulsanAtopy']").val();
	var chungbukAtopy = $("[name='chungbukAtopy']").val();
	var jeonnamAtopy = $("[name='jeonnamAtopy']").val();
	var jejuAtopy = $("[name='jejuAtopy']").val();
	
	var seoulRhinitis = $("[name='seoulRhinitis']").val();
	var busanRhinitis= $("[name='busanRhinitis']").val();
	var daeguRhinitis = $("[name='daeguRhinitis']").val();
	var gwangjuRhinitis = $("[name='gwangjuRhinitis']").val();
	var daejeonRhinitis = $("[name='daejeonRhinitis']").val();
	var ulsanRhinitis = $("[name='ulsanRhinitis']").val();
	var chungbukRhinitis = $("[name='chungbukRhinitis']").val();
	var jeonnamRhinitis = $("[name='jeonnamRhinitis']").val();
	var jejuRhinitis = $("[name='jejuRhinitis']").val();
	
	var oneAtopy = $("[name='oneAtopy']").val();
	var oneAsthma= $("[name='oneAsthma']").val();
	var oneRhinitis = $("[name='oneRhinitis']").val();

	var twoAtopy = $("[name='twoAtopy']").val();
	var twoAsthma= $("[name='twoAsthma']").val();
	var twoRhinitis = $("[name='twoRhinitis']").val();

	var thrAtopy = $("[name='thrAtopy']").val();
	var thrAsthma= $("[name='thrAsthma']").val();
	var thrRhinitis = $("[name='thrRhinitis']").val();

	var fourAtopy = $("[name='fourAtopy']").val();
	var fourAsthma= $("[name='fourAsthma']").val();
	var fourRhinitis = $("[name='fourRhinitis']").val();

	var fiveAtopy = $("[name='fiveAtopy']").val();
	var fiveAsthma= $("[name='fiveAsthma']").val();
	var fiveRhinitis = $("[name='fiveRhinitis']").val();
	
	var woAtopy = $("[name='woAtopy']").val();
	var woAsthma= $("[name='woAsthma']").val();
	var woRhinitis = $("[name='woRhinitis']").val();
	var woShell = $("[name='woShell']").val();
	var woEgg= $("[name='woEgg']").val();
	var woMilk = $("[name='woMilk']").val();
	var woNut = $("[name='woNut']").val();

	var manAtopy = $("[name='manAtopy']").val();
	var manAsthma= $("[name='manAsthma']").val();
	var manRhinitis = $("[name='manRhinitis']").val();
	var manShell = $("[name='manShell']").val();
	var manEgg= $("[name='manEgg']").val();
	var manMilk = $("[name='manMilk']").val();
	var manNut = $("[name='manNut']").val();

//선그래프
new Chart(document.getElementById("canvas"), {
	    type: 'line',
	    data: {
	        labels: ["서울", "부산", "대구", "광주", "대전","울산", "충북","전남","제주"],
	        datasets: [{
	            label: '천식',
	            data: 
	               [seoul, busan, daegu, gwangju, daejeon, ulsan, chungbuk, jeonnam, jeju]
	            ,
	            borderColor: "rgb(255,126,126)",
	            fill: false
	        },{
	            label: '아토피',
	           
	            data: 
	               [seoulAtopy, busanAtopy, daeguAtopy, gwangjuAtopy, daejeonAtopy, ulsanAtopy, chungbukAtopy, jeonnamAtopy, jejuAtopy]
	            ,
	            borderColor: "rgb(90,174,255)",
	            fill: false
	        },{
	        	label: '비염',
	        	
	            data: 
	               [seoulRhinitis, busanRhinitis, daeguRhinitis, gwangjuRhinitis, daejeonRhinitis, ulsanRhinitis, chungbukRhinitis, jeonnamRhinitis, jejuRhinitis]
	            ,
	            borderColor: "rgb(221,126,255)",
	            fill: false
	        }]
	    },
	    options: {
	    	
	        responsive: true,
	        title: {
	        	text: '지역별알레르기현상',
	            display: true,
	            fontSize: 20
	        },
	        scales: {
	            xAxes: [{
	                display: true,
	                scaleLabel: {
	                    display: true
	                },
	                ticks: {
	                    autoSkip: false,
	                    fontSize: 25
	                }
	            }],
	          
	            yAxes: [{
	            	fontSize: 50,
	                display: true,
	                ticks: {
	                    suggestedMin: 0,
	                    fontSize: 25
	                }
	            }]
	        },
	        legend:{
				labels:{
					fontStyle:'Eoe_Zno_L',
				}
			}
	    }
	});
	
//도넛그래프
var data = {
	labels:[
		"아토피",
		"천식",
		"비염"
	],
	datasets:[{
		data:[oneAtopy,oneAsthma,oneRhinitis],
		backgroundColor:[
			"#BFD0E6",
			"#798F8C",
			"#E9CBD1"
		]
	
	}]
};

 new Chart(document.getElementById("cxt"),{
	type : 'doughnut',
	data : data,
	options : {
		title: {
        	text: '10대 알레르기 발생비율',
            display: true,
            fontSize: 25
        }
	}
});
 
 var data = {
			labels:[
				"아토피",
				"천식",
				"비염"
			],
			datasets:[{
				data:[twoAtopy,twoAsthma,twoRhinitis],
				backgroundColor:[
					"#BFD0E6",
					"#798F8C",
					"#E9CBD1"
				]
			}]
		};
		
		 new Chart(document.getElementById("cxt2"),{
			type : 'doughnut',
			data : data,
			options : {
				title: {
		        	text: '20대 알레르기 발생비율',
		            display: true,
		            fontSize: 25
		        }
			}
		});
		 
 var data = {
			labels:[
				"아토피",
				"천식",
				"비염"
			],
			datasets:[{
				data:[thrAtopy,thrAsthma,thrRhinitis],
				backgroundColor:[
					"#BFD0E6",
					"#798F8C",
					"#E9CBD1"
				]
			}]
		};
		
		 new Chart(document.getElementById("cxt3"),{
			type : 'doughnut',
			data : data,
			options : {
				title: {
		        	text: '30대 알레르기 발생비율',
		            display: true,
		            fontSize: 25
		        }
			}
		});		 

 var data = {
			labels:[
				"아토피",
				"천식",
				"비염"
			],
			datasets:[{
				data:[fourAtopy,fourAsthma,fourRhinitis],
				backgroundColor:[
					"#BFD0E6",
					"#798F8C",
					"#E9CBD1"
				]
			}]
		};
		
		 new Chart(document.getElementById("cxt4"),{
			type : 'doughnut',
			data : data,
			options : {
				title: {
		        	text: '40대 알레르기 발생비율',
		            display: true,
		            fontSize: 25
		        }
			}
		});
		 
 var data = {
			labels:[
				"아토피",
				"천식",
				"비염"
			],
			datasets:[{
				data:[fiveAtopy,fiveAsthma,fiveRhinitis],
				backgroundColor:[
					"#BFD0E6",
					"#798F8C",
					"#E9CBD1"
				]
			}]
		};
		
		 new Chart(document.getElementById("cxt5"),{
			type : 'doughnut',
			data : data,
			options : {
				title: {
		        	text: '50대 알레르기 발생비율',
		            display: true,
		            fontSize: 25
		        }
			}
		});	

		//막대그래프		 
		 var barChartData = {       
			        labels: ["여", "남"],
			        datasets: [{
			            label: '천식',
			            backgroundColor: "#F9D5D3",
			            data: [
			            	woAsthma,
			            	manAsthma
			           ]
			        }, {
			            label: '아토피',
			            backgroundColor: "#ECA4A6",
			            data: [
			            	woAtopy,
			            	manAtopy
			            ]
			        }, {
			        	label: '비염',
			            backgroundColor: "#807F89",
			            data: [
			            	woRhinitis,
			            	manRhinitis
			            ]
			        },{
			        	label: '난류',
			            backgroundColor: "#DB8EB5",
			            data: [
			            	woEgg,
			            	manEgg
			            ]
			        },{
			        	label: '조개류',
			            backgroundColor: "#BFA0ED",
			            data: [
			            	woShell,
			            	manShell
			            ]
			        },
			        {
			        	label: '우유',
			            backgroundColor: "#F29661",
			            data: [
			            	woMilk,
			            	manMilk
			            ]
			        }
			        ]
			    };
		 
		 new Chart(document.getElementById("cxt6"), {
			    type: 'bar',
			    data: barChartData,
			    options : {
					title: {
			        	text: '성별에 따른 알레르기 발생비율',
			            display: true,
			            fontSize: 20
			        },
			        scales: {
			            xAxes: [{
			                ticks: {
			                    fontSize: 25
			                }
			            }],
			            yAxes: [{
			                ticks: {
			                    fontSize: 25
			                }
			            }]
			        },
			        legend:{
						labels:{
							fontSize:20,
							fontStyle:'Eoe_Zno_L'
						}
					}
				}
			});		
		 
	
	 
</script>

