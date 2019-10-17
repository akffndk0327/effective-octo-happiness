<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
<%
	Calendar cal = getInstance();
	int month = cal.get(Calendar.MONTH);
	Locale currentLocale = request.getLocale();
	DateFormatSymbols dfs = new DateFormatSymbols(currentLocale);
	String[] months = dfs.getMonths();
%>
</head>
<body>
<table class="table">
	<form id="searchForm">
	<thead>
		<tr>
			<th>
				<span>법정코드 검색(주소입력)</span>
				<input type="hidden" name="page"/>
				<input type="hidden" name="searchType" value="name" />
				<input type="text" name="searchWord"/>
				<button type="submit" id="btnSearch">검색</button>
			</th>
		</tr>
	</thead>
	</form>
	<tbody id="resultBody">
	</tbody>
	<table>
		<div id="pagingArea"></div>
	<table>
</table>
<table>
	<tr>
		<td>
			<select id="year">
				<option selected value="2019">2019년</option>
				<option value="2018">2018년</option>
				<option value="2017">2017년</option>
			</select>
		
		</td>
		<td>
			<select id="month">
				<%
					for(int idx=0; idx<months.length-1; idx++){
				%>
					<option <%=idx==month?"selected":"" %> value="<%=idx<10?"0"+idx:idx%>"><%=months[idx] %></option>
				<%
					}
				%>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="3"><button id="btn">Data 가져오기</button></td>
	</tr>
</table>
<!-- 서울지역의 2019년9월의 아파트 거래가 정보 출력  -->
<button id="btn">갸져오기</button>
<div id="resultArea">
	<table>
		<thead>
			<tr id="headerArea"></tr>
		</thead>
		<tbody id="listBody">
		
		</tbody>
	</table>
</div>
<script type="text/javascript">
let headerArea = $("#headerArea")
let listBody = $("#listBody")
	$("#btn").on('click',function(){
		$.ajax({
			data:{
				//동코드, 기간 받아오고
				//프록시에서 잡ㄱ ㅣ
			}
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				let response = resp.response;
				let header = response.header;
				let body = response.body;
				if(header.resultCode!="00"){
					alert(header.resultMsg);
					return;
				}
				let items = body.items.item;
				let trHeaders =[];
// 				let listBodyTags = [];
				let trTags = [];
				$(items).each(function (index,item) { //아이템 안에 거래금액, 건축연도등 들어있ㅇ ㅓ
					let count = 0;
					let trTag=$("<tr>");
					trTags.push(trTag);
					for(let propname in item){
						if(count==0){
							trHeaders.push($("<th>").text(propname)); //th를 채워 넣어야지
						}
						console.log(propname+":"+item[propname])
						//tbody 만들기
						trTag.append($("<td>").text(item[propname]));
					}
					count++;
				});
				listBody.html(trTags);
				headerArea.html(trHeaders);
			},
			error : function(xhr) {
				console.log(xhr.status);
			}
		});
	});
</script>
</body>
</html>