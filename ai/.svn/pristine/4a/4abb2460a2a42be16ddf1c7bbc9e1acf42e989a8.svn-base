<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                    수정자         수정내용
* ----------       ---------  -----------------
* 2019. 11. 15.     박주연         최초작성
* 2019. 11. 16.     허민지      회원정보수정 추가 Modal 추가
* 2019. 11. 19.     최서희      장바구니, 주문내역 링크 추가
* Copyright (c) 2019 by DDIT All right reserved
 --%>
<style>
.input{
	border: 0;
    border-bottom: 1px solid #90c322;
}
</style>
<link rel="stylesheet" href="${cPath }/css/memleft.css">
<div class="titleTopBar">
<li id="MemTitle"><a href="${cPath }/memMypage/member?memId=${loginId }">마이페이지</a></li>
</div>
<br>
   <ul>
      <li class="memleftli" data-toggle="modal"
         data-target="#exampleModal"><a>회원정보수정</a></li> 
      <li class="memleftli"><a href="${cPath }/memMypage/memAllergyView.do">알레르기 정보수정</a></li>
      <li class="memleftli"><a href="${cPath }/memMypage/monthlyView.do">식단공유 게시판 관리</a></li>
      <li class="memleftli"><a href="${cPath }/memMypage/recipeList.do">레시피 게시판 관리</a></li>
      <li class="memleftli"><a href="${cPath }/memMypage/recipeLike.do">찜한 레시피</a></li>
<%--       <li><a href="${cPath }/memMypage/replyList.do">댓글 관리</a></li> --%>
      <li class="memleftli"><a href="${cPath }/cart/cartList.do">장바구니</a></li>
      <li class="memleftli"><a href="${cPath }/order/orderList.do">주문내역</a></li>
   </ul>
   
<!-- Modal -->
<!-- 회원 정보 수정 들어가기 위한 비밀번호 확인 Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
   aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">회원정보 수정</h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body" style="text-align:center">
            <form action="${cPath}/member/checkPassForUpdate.do" method="post">
               <div>비밀번호 확인 : &nbsp;
	               <input name="memPass" class="input" type="password" />           
               </div>
         </div>
            <div class="modal-footer">
               <button type="button" class="btn btnAi1"
                  data-dismiss="modal">취소</button>
               <button type="submit" class="btn btnAi0">확인</button>
            </div>
            </form>
      </div>
   </div>
</div>
