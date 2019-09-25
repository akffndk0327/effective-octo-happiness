<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		$.ajax({
			url : '<%=request.getContextPath()%>/BuyerServlet',
			type :'get',
			dataType : 'json',
			success :function(result){
				code =""; //이거 왜하는거,,?
				$.each(result,function(i,v){ //하나만 쓰면 result의 v번째가 됨. i:인덱스 v :인덱스번째를 받는 변수
					code+="<p id='"+result[i].id+"'>"+result[i].name+"</p>";
					
				})
				$('#namelist').html(code);
			},
			error:function(xhr){
			alert("상태 : " +xhr.status);
			}
		})	
		
		$('#namelist').on('click','p',function(){
			idvalue = $(this).attr('id');
			$.ajax({
				url : '<%=request.getContextPath()%>/BuyerServlet',
				type :'post',
				data : {"id" : idvalue},
				success : function(result){
					code="<table  class='table table-striped' border='1'>";
					code+="<tr><td>BUYER_ID</td>"
					code+="<td>" + result.id + "</td><tr>"
					
					code+="<tr><td>BUYER_NAME</td>"
					code+="<td>" + result.name + "</td><tr>"
					
					code+="<tr><td>BUYER_BANK</td>"
					code+="<td>" + result.bank + "</td><tr>"
					
					code+="<tr><td>BUYER_BANKNAME</td>"
					code+="<td>" + result.bankname + "</td><tr>"

					code+="<tr><td>BUYER_LGU</td>"
					code+="<td>" + result.lgu + "</td><tr>"
					
					code+="<tr><td>BUYER_BANKNO</td>"
					code+="<td>" + result.bankno + "</td><tr>"
					
					code+="<tr><td>BUYER_MAIL</td>"
					code+="<td>" + result.mail + "</td><tr>"
					
					code+="<tr><td>BUYER_ADD1</td>"
					code+="<td>" + result.add1 + "</td><tr>"
					
					code+="<tr><td>BUYER_comtel</td>"
					code+="<td>" + result.comtel + "</td><tr>"
					
					code+="<tr><td>BUYER_zip</td>"
					code+="<td>" + result.zip + "</td><tr>"
					
					code+="</table>";
					$('#detail').html(code);
					
				},
				error : function(){
					
				},
				dataType:'json'
				
			})
			
		})
	
	})
</script>
<style>
	td{
		width: 50px;
		height: auto;
	}
	p:hover{
		background-color: red;
	}
</style>
</head>
<body>
<div>
	<table class="table table-bordered">
		<tr>
			<td>거래처이름</td>
			<td>거래처 상세정보</td>
		</tr>
		<tr>
			<td id="namelist"></td>
			<td id="detail"></td>
		</tr>
		
	</table>
</div>
</body>
</html>