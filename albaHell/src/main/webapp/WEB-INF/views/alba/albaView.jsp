<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<!-- <script type="text/javascript"   src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<table>
		<thead>
			<h4>
				<p>${alba.al_name }</p>
			</h4>
		</thead>
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${alba.al_id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${alba.al_name }</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>${alba.al_age }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${alba.al_address }</td>
			</tr>
			<tr>
				<th>핸드폰</th>
				<td>${alba.al_hp }</td>
			</tr>
			<tr>
				<th>특기사항</th>
				<td>${alba.al_spec }</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>${alba.al_desc }</td>
			</tr>
			<tr>
				<th>학력</th>
				<td>${alba.gr_code }</td>
			</tr>
			<tr>
				<th>경력사항</th>
				<td>${alba.al_career }</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${alba.al_gen }</td>
			</tr>
			<tr>
				<th>혈액형</th>
				<td>${alba.al_btype }</td>
			</tr>
			<tr>
				<th>메일</th>
				<td>${alba.al_mail }</td>
			</tr>
		<c:forEach items="${alba.licAlbaLists}" var="list">
         <c:url value="/alba/licenseImage.do" var="viewURL">
            <c:param name="id" value="${alba.al_id}"/>
         </c:url>
         <c:choose>
         <c:when test="${not empty list.licenseList }">
               <c:forEach items="${list.licenseList}" var="list2">
               <tr>
               <th>자격증코드</th>
               <td>${list2.lic_code }</td>
               </tr>
               <tr>
                  <th>자격증이름</th>
                  <td><a href="${viewURL }">${list2.lic_name}</a></td>
                  
               </tr>
               </c:forEach>
         </c:when>
         <c:otherwise>
            <th>자격증코드</th>
            <td>자격증 없음</td>
         </c:otherwise>
      </c:choose>
   </c:forEach>
		</tbody>
</table>

</body>
</html>