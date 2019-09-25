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
<script src="../js/board.js"></script>
<script>
	$(function () {
		readServer(1);
		
		//페이지번호를 클릭하면 이벤트 만들기 -delegate로 주기 
		$('#btngroup').on('click','.paging',function(){
			currentPage=$(this).text();
			readServer(currentPage);
		})
		
		//다음 버튼 클릭하면 
		$('#btngroup').on('click','.next a',function(){
			currentPage  = parseInt($('.paging:last').text())+1;
			readServer(currentPage);
		})
		//이전버튼 클릭하면 
		$('#btngroup').on('click','.previous a',function(){
			currentPage  = parseInt($('.paging:first').text())-1;
			readServer(currentPage);
		})
		//0924
		//확인버튼 클릭하면 
		$('#wok').bind('click',function(){
			writeServer();
			$('#writeModal').modal('hide');
		})
		
		//모달 창이 닫힐때 입력 내용을 지운다 .
		$('#writeModal').on('hide.bs.modal',function(){
			$('.txt').val(""); // 공통속성은 class로 처리.. 
		});
		
		//수정,삭제 등록 버튼 클릭할때 
		$('#accordionList').on('click','.action',function(){
			name = $(this).attr('name');
			idx = $(this).attr('idx');
			if(name == "modify"){
				alert(idx+"번호의 글을 수정합니다 .");
			}else if(name =="delete"){
				alert(idx+"번호의 글을 삭제합니다 .");
			}else if(name =='reply'){
// 				alert(idx+"번호의 글의 댓글을 등록합니다.");	
				
				//입력한 내용 가녀오기
				rtext=$(this).parent().find('.area').val();
				//이름 작성 
				rname = "qwer1234";
				
				replyServer(this); //댓글 저장 
				bonum=$(this).attr('idx'); //replyListServer 로 갈때는 bonum이 필요함.
				console.log(bonum);
				//댓글 목록 
// 				replyListServer(this);
				
			}else if(name=="list");
				//댓글 목록을 가져오기위해서 - 글번호 가져옴 
				bonum=$(this).attr('idx');
				
				replyListServer(this);
			
			
		})
		
	})
</script>
<style>
	#btngroup{
		text-align: center ;
		margin-left: 35%;
	}
	.pager{
		float: left;
		margin-left: 20px;
		
	}
	.next , .previous{
	   margin-left : 20px;
	}
	
	#divwriter{
		text-align: right;
		margin-right: 50px;
	}
	
	.rep{
		background-color: yellow;
		margin : 3px;
		padding:5px;
	}
	
</style>
</head>
<body>
	<!-- 댓글 수정을 위한 form -->



	 <h2>Accodion Example</h2>
	 
	 <br>
	 <div id="divwriter">
	 	<button  data-toggle="modal" data-target="#writeModal" type="button" name="write" 
	 	         class ="action btn btn-success"> 글쓰기 </button> <br><br>
	 </div>
	 
	 <div id="accordionList"> </div>
	<!-- 0923 -->
	<br>
	<div id ="btngroup"></div>
	
	<!-- 글쓰기  Modal -->
<div id="writeModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">새글 작성 창입니다. </h4>
      </div>
      <div class="modal-body">
      <form id="wform" name = "wform">
	        제목 : <input class="txt" type="text" name="subject" id="subject"> <br><br>
	        작성자 : <input class="txt" type="text" name="writer" id="writer"> <br><br>
	        비밀번호 : <input class="txt" type="text" name="password" id ="password"> <br><br>
	        메일 : <input class="txt" type="text" name="mail" id="mail"> <br><br>
	        내용 : <br>
	        <textarea class="txt" rows="10" cols="30" name="content" id="content"> </textarea>
	        <br><br>
        </form>
      </div>
      <div class="modal-footer">
	        <button type="button" class="action btn btn-warning" name="wok" id="wok">확인</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	

</body>
</html>