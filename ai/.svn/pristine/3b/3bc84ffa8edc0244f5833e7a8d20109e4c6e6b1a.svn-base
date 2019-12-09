<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 4.      이진희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="food">
		<input type="text" id="search" />
		<button type="sumbit" id="srchBtn">검색</button>
	</div>
	<script type="text/javascript">
		var foodTag = $("#food");
		var prdNm = $("#search");
		// 	function crawling(paramObj){
		// 		$.ajax({
		// 			url: "sample.jsp",
		// 			data : {
		// 				page:paramObj.page
		// 			},
		// 			dataType : "html",
		// 			success:function(resp){
		// 				var keywords = $(resp).find(paramObj.element);
		// 				paramObj.tag.html(keywords);
		// 			},
		// 			error : function(err){
		// 				console.log(err.status);
		// 			}

		// 		});
		// 	}

		// 	crawling({
		// 		page:"food",
		// 		element:".list-container",
		// 		tag:foodTag
		// 	});

		$("#srchBtn").on("click", function() {
			var value = prdNm.val();
			$.ajax({
				url : "${cPath}/food",
				data : {
					"SPRDNM" : value
				},
				dataType: "JSON",
				success : function(resp) {
					console.log("이부분"+resp.prdList);
					$(resp.prdList).each(function(i,v){
						console.log(v.PRDT_SIL_SRV);
						console.log(v.PRDLST_NM);
					})
					
				},
				error : function(err) {
					console.log(err.stauts);
				}

			})
		})
	</script>
</body>
</html>