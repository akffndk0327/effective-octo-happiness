<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8" /> -->
<!-- <title>Insert title here</title> -->
<!-- <script type="text/javascript" -->
<!-- 	src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
<!-- <link rel="stylesheet" -->
<%-- 	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css"> --%>
<!-- <script type="text/javascript" -->
<!-- 	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script> --%>
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath }/js/generataLprodBuye.js"></script> --%>

<%-- <c:if test="${not empty message }"> --%>
<!-- 	<script type="text/javascript"> -->
<%-- //  		alert("${message}"); --%>
<!--  	</script>  -->
<%-- </c:if> --%>
<!-- </head> -->
<!-- <body> -->
	<form:form commandName="prod" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품코드</th>
				<td><input type="hidden"  class="form-control"
					name="prod_id" value="${prod.prod_id}" />
					<form:errors path="prod_id" element="span" cssClass="error" />
<!-- 					<span ~></span> -->
					</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text"  class="form-control"
					name="prod_name" value="${prod.prod_name}" />
					<form:errors path="prod_name" element="span" cssClass="error" />
					</td>
			</tr>
			<tr>
				<th>분류코드</th>
				<td><select name="prod_lgu">
						<option value>분류선택</option>
				</select> 
				<form:errors path="prod_lgu" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>거래처코드</th>
				<td>
				<select name="prod_buyer">
						<option value>거래처선택</option>
				</select> 
				<form:errors path="prod_buyer" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>구매가</th>
				<td><input type="number"  class="form-control"
					name="prod_cost" value="${prod.prod_cost}" />
					<form:errors path="prod_cost" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td><input type="number"  class="form-control"
					name="prod_price" value="${prod.prod_price}" />
					<form:errors path="prod_price" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>세일가</th>
				<td><input type="number"  class="form-control"
					name="prod_sale" value="${prod.prod_sale}" />
					<form:errors path="prod_sale" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>OUTLINE</th>
				<td><input type="text"  class="form-control"
					name="prod_outline" value="${prod.prod_outline}" />
					<form:errors path="prod_outline" element="span" cssClass="error" />
				</td>
			</tr>
			<tr>
				<th>상세정보</th>
				<td><input type="text" class="form-control" name="prod_detail"
					value="${prod.prod_detail}" />
					<form:errors path="prod_detail" element="span" cssClass="error" />
					</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><input type="file" name="prod_image" accept="image/*">
					<form:errors path="prod_img" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>상품재고</th>
				<td><input type="number"  class="form-control"
					name="prod_totalstock" value="${prod.prod_totalstock}" />
					<form:errors path="prod_totalstock" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>입고일</th>
				<td><input type="date" class="form-control" name="prod_insdate"
					value="${prod.prod_insdate}" />
					<form:errors path="prod_insdate" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>적정재고</th>
				<td><input type="number"  class="form-control"
					name="prod_properstock" value="${prod.prod_properstock}" />
					<form:errors path="prod_properstock" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>크기</th>
				<td><input type="text" class="form-control" name="prod_size"
					value="${prod.prod_size}" />
					<form:errors path="prod_size" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>색상</th>
				<td><input type="text" class="form-control" name="prod_color"
					value="${prod.prod_color}" />
					<form:errors path="prod_color" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>배송방법</th>
				<td><input type="text" class="form-control"
					name="prod_delivery" value="${prod.prod_delivery}" />
					<form:errors path="prod_delivery" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>단위</th>
				<td><input type="text" class="form-control" name="prod_unit"
					value="${prod.prod_unit}" />
					<form:errors path="prod_unit" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>입고량</th>
				<td><input type="number" class="form-control" name="prod_qtyin"
					value="${prod.prod_qtyin}" />
					<form:errors path="prod_qtyin" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>판매량</th>
				<td><input type="number" class="form-control"
					name="prod_qtysale" value="${prod.prod_qtysale}" />
					<form:errors path="prod_qtysale" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" class="form-control"
					name="prod_mileage" value="${prod.prod_mileage}" />
					<form:errors path="prod_mileage" element="span" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn btn-primary" type="submit"
					value="저장" /> <input class="btn btn-warning" type="reset"
					value="취소" /></td>
			</tr>
		</table>
	</form:form>
	<script type="text/javascript">
		var prod_lguTag = $("[name='prod_lgu']");
		prod_lguTag.generateLprod("${pageContext.request.contextPath}", "${prod.prod_lgu}");

		var prod_buyerTag = $("[name='prod_buyer']");
		prod_buyerTag.generateBuyer({
			cPath : "${pageContext.request.contextPath}",
			selectedBuyer : "${prod.prod_buyer}"
		});

		$(prod_lguTag).on('change', function() {
			let lguCode = $(this).val();
			if (!lguCode)
				return;
			let options = $(prod_buyerTag).find("option:gt(0)");
			$(options).hide();
			let lguOptions = $(prod_buyerTag).find("option." + lguCode);
			$(lguOptions).show();
		})
	</script>
<!-- </body> -->
<!-- </html> -->