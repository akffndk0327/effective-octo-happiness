<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">	
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
	#buyerAdd{
		width:150px;
		height:150px;
	}

</style>
</head>
<body>
<form id="searchForm">
거래처아이디검색	: <input type="text" name="buyer_id"/>
거래처명검색: <input type="text" name="buyer_name"/>
거래처코드검색: <input type="text" name="buyer_lgu"/>
<input type="button" id="searchBtn" value="검색"/>
</form>
<div>
	<table class="table table-bordered" border="1">
		<tr>
		<td>거래처이름</td>
		<td>거래처상세정보</td>
		</tr>
		<tr>
		<td id="nameList"></td>
		<td id="detail">
		</td>
		</tr>
		<tr>
			<td colspan="2" id="pagingArea">
				
			</td>
		</tr>
	</table>
</div>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">추가</button>
<button type="button" class="btn btn-primary" id="delete">삭제</button>
<input type="button" id="modify" class="btn btn-primary" value="수정">


<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">BUYER추가하기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="${pageContext.request.contextPath }/buyer/buyerInsert" method="post">
      <div class="modal-body">
        BUYER_ID : <input type="text" name="buyer_id" /><br>
        BUYER_NAME : <input type="text" name="buyer_name" /><br>
        BUYER_LGU : <select name="buyer_lgu">
					<option value>분류선택</option>
					</select><br>
        BUYER_BANK : <input type="text" name="buyer_bank" /><br>
        BUYER_BANKNO : <input type="text" name="buyer_bankno" /><br>
        BUYER_BANKNAME : <input type="text" name="buyer_bankname" /><br>
        BUYER_ZIP : <input type="text" name="buyer_zip" /><br>
        BUYER_ADD1 : <input type="text" name="buyer_add1" /><br>
        BUYER_ADD2 : <input type="text" name="buyer_add2" /><br>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <Input type="submit" class="btn btn-primary" value="추가하기">
      </div>
        </form>
    </div>
  </div>
</div>

</body>
<script type="text/javascript">
	var nameList = $('#nameList');
	var detail = $('#detail');
	
	var pagingArea = $("#pagingArea");
	pagingArea.on("click","a",function(){
		let page = $(this).data("page");
		paging(page);
	});
	
	function paging(page) {
		if(page<1) return false;
			$.ajax({
				data : "page=" +page,
				dataType : "json",
				success : function(resp){
					let result = resp.dataList;
					let tag="";
					$(result).each(function(i,v){
						tag += "<p id='"+v.buyer_id+"' >" + v.buyer_name + "</p>"
					});
					nameList.html(tag);
					pagingArea.html(resp.pagingHTML);
				},
				error : function(err){
					alert(err.status);
				}
			});
	}	
	
	
	nameList.on("click","p",function(){
		var id = $(this).prop("id");
		
		$.ajax({
			url : "${pageContext.request.contextPath}/buyer/buyerDetail",
			data : {"id" : id},
			dataType : "json",
			success : function(resp){
				code="<form id='modifyForm'>";
				code+="<table>";
				code+="<tr><td>BUYER_ID</td>";
				code+="<td><input name='buyer_id' value='"+resp.buyer_id+"' ></td><tr>";
				
				code+="<tr><td>BUYER_NAME</td>";
				code+="<td><input name='buyer_name' value='"+resp.buyer_name+"' ></td><tr>";
				
				code+="<tr><td>BUYER_LGU</td>";
				code+="<td><input name='buyer_lgu' value='"+resp.buyer_lgu+"' ></td><tr>";
				
				code+="<tr><td>BUYER_BANK</td>";
				code+="<td><input name='buyer_bank' value='"+resp.buyer_bank+"' ></td><tr>";
				
				code+="<tr><td>BUYER_BANKNO</td>";
				code+="<td><input name='buyer_bankno' value='"+resp.buyer_bankno+"' ></td><tr>";
				
				code+="<tr><td>BUYER_BANKNAME</td>";
				code+="<td><input name='buyer_bankname' value='"+resp.buyer_bankname+"' ></td><tr>";
				
				code+="<tr><td>BUYER_ZIP</td>";
				code+="<td><input name='buyer_zip' value='"+resp.buyer_zip+"' ></td><tr>";
				
				code+="<tr><td>BUYER_ADD1</td>";
				code+="<td><input name='buyer_add1' value='"+resp.buyer_add1+"' ></td><tr>";
				
				code+="<tr><td>BUYER_ADD2</td>";
				code+="<td><input name='buyer_add2' value='"+resp.buyer_add2+"' ></td><tr>";
				
				 $(resp.prodList).each(function(i, v){
		               code += "<tr>                                       ";
		               code += "<td>"+ v.prod_id+"</td>         ";
		               code += "<td>"+ v.prod_name+"</td>         ";
		               code += "<td>"+ v.prod_lgu+"</td>        ";
		               code += "<td>"+ v.prod_cost+"</td>      ";
		               code += "<td>"+ v.prod_price+"</td>         ";
		               code += "</tr>                                      ";
		            })
		            
				code+="</table>";
				code+="</form>";
				detail.html(code);
			},
			error : function(err){
				alert(err.status);
			}
		});
		
		
		
		$('#delete').on('click',function(){
	 		$.ajax({
				url : "${pageContext.request.contextPath}/buyer/buyerDelete",
	 			data : {"id" : id},
	 			dataType : "text",
	 			success : function(resp) {
					alert("삭제완료");
				},
	 			error : function(err){
	 				alert(err.status);
	 			}
	 		});
		})
		
	})
	
	$('#modify').on('click',function(){
			modi = $('#modifyForm').serializeArray();
			$.ajax({
				url : "${pageContext.request.contextPath }/buyer/buyerUpdate",
				method : "get",
				data : modi,
				dataType : "text",
				success : function(resp) {
					alert("수정완료");
				},
				error : function(err) {
					console.log(err.status);
				}

			});
		})
		
		var lguResult = $("[name='buyer_lgu']");
	
		$.ajax({
			url : "${pageContext.request.contextPath }/buyer/buyerList",
			dataType : "json",
			success : function(resp) {
				let options =[];
				$(resp).each(function(index,buyer){
					options.push(
						$("<option>").text(buyer.buyer_lgu));
				});
				$(lguResult).append(options);
			},
			error : function(err) {
				console.log(err.status);
			}
		});

		paging(1);

</script>
</html>