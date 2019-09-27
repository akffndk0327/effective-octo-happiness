<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="naver" value="https://www.naver.com" />
<c:set var="daum" value="https://www.daum.net" />
<c:choose>
   <c:when test="${param.page eq 'naver' }">
      <c:import url="${naver }" />
   </c:when>
   <c:when test="${param.page eq 'daum' }">
      <c:import url="${daum }" />
   </c:when>
   <c:otherwise>
      <h4>어디서 뭘???</h4>         
   </c:otherwise>
</c:choose>