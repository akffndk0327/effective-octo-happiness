<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자       수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.    허민지      최초작성
* 2019. 11. 11.   최서희	   총 매출현황 차트
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<style>
/*  #cxt, #cxt2, #cxt3 {  */
/*  	float: left;  */
/*  }  */
#circle1, #circle2{
	float:left;
}

.titleLeftBar{
	color : #322C5C;
	border-left: 8px solid #322C5C;
/* 	background-color: #322C5C; */
}
</style>

<div id="InnerContainer">
	<div class="container">
		<div class="row" id="chart" >
		<div class="titleLeftBar">&nbsp; 매출 현황</div>
			<div style="width: 100%;">
				<canvas id="canvas" height="100"></canvas>
			</div>
<!-- 			<div style="width: 40%;"> -->
<!-- 			</div> -->
			<br><br><br><br><br><br><br>
		<div class="titleLeftBar">&nbsp; 사용자 환경 현황</div>
			<div id="circle1" style="width:50%;">
				<canvas id="cxt" height="150"></canvas>
			</div>
			<div id="circle2" style="width:50%;">
				<canvas id="cxt2" height="150"></canvas>
			</div>
			<input name="jan" type="hidden" value="${jan}" /> 
			<input name="feb" type="hidden" value="${feb}" /> 
			<input name="mar" type="hidden"	value="${mar}" /> 
			<input name="apr" type="hidden" value="${apr}" />
			<input name="may" type="hidden" value="${may}" /> 
			<input name="jun" type="hidden" value="${jun}" /> 
			<input name="jul" type="hidden"	value="${jul}" /> 
			<input name="aug" type="hidden" value="${aug}" />
			<input name="sep" type="hidden" value="${sep}" /> 
			<input name="oct" type="hidden" value="${oct}" /> 
			<input name="nov" type="hidden"	value="${nov}" /> 
			<input name="dec" type="hidden" value="${dec}" />

			<input name="Chrome" type="hidden" value="${Chrome}" />
			<input name="FireFox" type="hidden" value="${FireFox}" />
			<input name="Ex" type="hidden" value="${Ex}" />
			<input name="Safari" type="hidden" value="${Safari}" />
			<input name="Others" type="hidden" value="${Others}" />
			
			<input name="Windows" type="hidden" value="${Windows}" />
			<input name="Linux" type="hidden" value="${Linux}" />
			<input name="Android" type="hidden" value="${Android}" />
			<input name="IOS" type="hidden" value="${IOS}" />
			<input name="Etc" type="hidden" value="${Etc}" />
		</div>

	</div>
		<br><br><br><br>
		
		
	
</div>
<!-- InnerContiner 끝  -->
<script type="text/javascript">
			var jan = $("[name='jan']").val();
			var feb = $("[name='feb']").val();
			var mar = $("[name='mar']").val();
			var apr = $("[name='apr']").val();
			var may = $("[name='may']").val();
			var jun = $("[name='jun']").val();
			var jul = $("[name='jul']").val();
			var aug = $("[name='aug']").val();
			var sep = $("[name='sep']").val();
			var oct = $("[name='oct']").val();
			var nov = $("[name='nov']").val();
			var dec = $("[name='dec']").val();
			

			new Chart(document.getElementById("canvas"), {
				type : 'line',
				data : {
					labels : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월",
							"9월", "10월", "11월", "12월" ],
					datasets : [
							{
								label : '2019년도',
								data : [ jan, feb, mar, apr, may, jun, jul,
										aug, sep, oct, nov, dec ],
								borderColor : "rgb(191,160,237)",
								fill : false
							}, ]
				},
				options : {
					responsive : true,
					title : {
						text : '2019년 월별 매출 현황',
						display : true,
						fontSize:20
					},
					scales : {
						xAxes : [ {
							display : true,
							scaleLabel : {
								display : true,
								labelString : 'Month',
								fontSize:20
							},
							ticks : {
								autoSkip : false,
								fontSize:20
							}
						} ],
						yAxes : [ {
							display : true,
							scaleLabel : {
								display : true,
								labelString : '원',
								fontSize:20
							},
							ticks : {
								suggestedMin : 0,
								fontSize:20,
								beginAtZero:true,
								userCallback:function(value,index,values){
									value=value.toString();
									value=value.split(/(?=(?:...)*$)/);
									value=value.join(',');
									return value;
								},
							}
						} ]
					},
					legend:{
						labels:{
//	 						fontSize:20
							fontStyle:'Eoe_Zno_L'
						}
					}
				}
			});
			
		var Chrome = $("[name='Chrome']").val();
		var FireFox = $("[name='FireFox']").val();
		var Ex = $("[name='Ex']").val();
		var Safari = $("[name='Safari']").val();
		var Others = $("[name='Others']").val();
		var Windows = $("[name='Windows']").val();
		var Linux = $("[name='Linux']").val();
		var Android = $("[name='Android']").val();
		var IOS = $("[name='IOS']").val();
		var Etc = $("[name='Etc']").val();
		
		
		var data = {
				labels:[
					"Chrome",
					"Firefox",
					"IE",
					"Safari",
					"etc"
				],
				datasets:[{
					data:[Chrome,FireFox,Ex,Safari,Others],
					backgroundColor:[
						"#111c2b",
						"#e48d7f",
						"#435676",
						"#b07e7a",
						"#DF3542"
					]
				
				}]
			};

			 new Chart(document.getElementById("cxt"),{
				type : 'pie',
				data : data,
				options : {
					title: {
			        	text: 'Browser',
			            display: true,
			            fontSize: 18
			        },
					legend:{
						labels:{
//	 						fontSize:20
							fontStyle:'Eoe_Zno_L'
						}
					}
				}
			});
			 
		var data2 = {
				labels:[
					"Window",
					"Linux",
					"Android",
					"IOS",
					"etc"
				],
				datasets:[{
					data:[Windows,Linux,Android,IOS,Etc],
					backgroundColor:[
						"#f7cac9",
						"#91a8d0",
						"#f3e7db",
						"#90D0AB",
						"#F7C974"
					]
				
				}]
			};

			 new Chart(document.getElementById("cxt2"),{
				type : 'pie',
				data : data2,
				options : {
					title: {
			        	text: 'Operating System',
			            display: true,
			            fontSize: 16
			        },
					legend:{
						labels:{
//	 						fontSize:20
							fontStyle:'Eoe_Zno_L'
						}
					}
				}
			});
			 
		
			
		</script>