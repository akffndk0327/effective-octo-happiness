<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 6.   허민지      최초작성
* 2019. 11. 10.  최서희 	 판매상품 등록 버튼 생성
* 2019. 11. 11.  최서희	 기업 매출현황 차트
* 2019. 11. 26.  허민지      기업회원 회원정보 수정시 성공 여부 sweetalert추가
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>

<style>
.swal2-title {
   font-size: 25px;
}

.swal2-html-container {
   font-size: 18px;
}

.swal2-styled.swal2-confirm {
   font-weight: bold;
   font-size: 18px;
   width: 106px;
   height: 42px;
   font-family: 'Eoe_Zno_L';
}

.swal2-popup {
   width: 400px;
   height: 250px;
}
</style>
<c:if test="${not empty msg }">
	<script>
	$(document).ready(function() {
		Swal.fire({
		  	icon: 'error',
		  	title: 'Oops...',
		  	text: '비밀번호 오류'
			})
	});
	</script>
</c:if>

<c:if test="${not empty fail }">
	<script>
	$(document).ready(function() {
		Swal.fire({
		  	icon: 'error',
		  	title: 'Oops...',
		  	text: '수정 실패'
			})
	});
	</script>
</c:if>

<div id="InnerContainer">
	<div class="container">
		<h2 class="titleLeftBar">${memName }님 매출 현황 </h2>
		<div class="row">
			<div style="width:auto;">
				<canvas id="canvas" height="100" ></canvas>
			</div >
		</div>
	</div>
	<!-- container 끝 -->

	<input name="jan" type="hidden" value="${jan}" />
	<input name="feb" type="hidden" value="${feb}" />
	<input name="mar" type="hidden" value="${mar}" />
	<input name="apr" type="hidden" value="${apr}" />
	<input name="may" type="hidden" value="${may}" />
	<input name="jun" type="hidden" value="${jun}" />
	<input name="jul" type="hidden" value="${jul}" />
	<input name="aug" type="hidden" value="${aug}" />
	<input name="sep" type="hidden" value="${sep}" />
	<input name="oct" type="hidden" value="${oct}" />
	<input name="nov" type="hidden" value="${nov}" />
	<input name="dec" type="hidden" value="${dec}" />

</div>	
<!-- InnerContainer 끝  -->


<!-- Chart 스크립트  -->
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
    type: 'line',
    data: {
        labels: ["1월", "2월", "3월", "4월", "5월","6월", "7월","8월","9월","10월","11월","12월"],
        datasets: [{
            label: '2019',
            data: 
               [jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec]
            ,
            borderColor: "rgb(218,65,51)",
            fill: false
        },]
    },
    options: {
        responsive: true,
        legend:{
        	fontSize:20
        },
        title: {
        	text: '2019년 월별 매출 현황',
            display: true,
            fontSize: 21
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Month',
                    fontSize:20
                },
                ticks: {
                    autoSkip: false,
                    fontSize:20
                    
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: '원',
                    fontSize:20
                },
                ticks: {
                    suggestedMin: 0,
                    fontSize:20,
                    beginAtZero:true,
					userCallback:function(value,index,values){
						value=value.toString();
						value=value.split(/(?=(?:...)*$)/);
						value=value.join(',');
						return value;
					},
                }
            }]
        },
		legend:{
			labels:{
//					fontSize:20
				fontStyle:'Eoe_Zno_L'
			}
		}
    }
});


</script>