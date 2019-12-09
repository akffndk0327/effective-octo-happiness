<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
*
* ----------  ---------  -----------------
* 2019. 11. 7.      박주연      최초작성
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<div>
	<h1>1</h1>
	<table>
		<tr>
			<td>
				<a href="${cPath }/news/newsView.do?what"+newsNo">제목입니디ㅏ</a>
			</td>
		</tr>
	</table>
	<button type="button" value="수정"><a href="${cPath }/news/newsUpdate.do">수정</button>
	<button type="button" value="삭제"><a href="${cPath }/news/newsDelete.do">삭제</button>
</div>
