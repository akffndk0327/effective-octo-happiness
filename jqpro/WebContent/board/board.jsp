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
		
		//수정,삭제 등록, 댓글수정, 댓글 삭제 버튼 클릭할때 
		//제목(list)클릭할 떄 - 댓글 가져오기, 조횟수 증가하기 
		$('#accordionList').on('click','.action',function(){
			name = $(this).attr('name');
			idx = $(this).attr('idx');
			if(name == "modify"){
// 				alert(idx+"번호의 글을 수정합니다 .");
				//모달창 띄우기
				$('#updateModal').show();
				//적힌 내용 가져오기 
				title=$(this).parents('.panel').find('#title').text();
				$('#subject2').val(title);
				indata = $(this).parents('.panel').find('.update').text();
				$('txt').val(indata);
				
				
				updateBoard(this);
				
			}else if(name =="delete"){
				alert(idx+"번호의 글을 삭제합니다 .");
//				글번호 가지고 전체 div를 remove();
				seq=idx;
				deleteBoard(this);
				
				
			}else if(name =='reply'){
// 				alert(idx+"번호의 글의 댓글을 등록합니다.");	
				
				//입력한 내용 가녀오기
				rtext=$(this).parent('.panel').find('.area').val();
				//이름 작성 
				rname = "qwer1234";
				
				replyServer(this); //댓글 저장 
				bonum=$(this).attr('idx'); //replyListServer 로 갈때는 bonum이 필요함.
				console.log(bonum);
				//댓글 목록 
// 				replyListServer(this);
				
			}else if(name=="list"){
				//댓글 목록을 가져오기위해서 - 글번호 가져옴 
				bonum=$(this).attr('idx');
				replyListServer(this);
				
				//0926-----조회수증가하기 조회수+1 update,select -----//
				//class 뒤에 "in" 이 써있으면 펼쳐진거, 아니면 닫혀진거 
				//내가 클릭한 this를 기준으로 부모를 찾고 in 써진거 찾는다
				inclass = $(this).parents('.panel').find('.in').attr('class'); //.attr('class') : class 속성에서 in을 찾아라 
				//열려잇을떄 in, 닫혀잇을떄 undefined + 
				//undefined 일때 조회수 증가 시킴. 
				if(typeof inclass =="undefined"){ //typeof : undefined 비교할때 확인하기 ! 
					readHitServer(this);				
				}
				
			}else if(name =="r_modify"){
				//댓글 수정 클릭 시 
				//modifyForm이 열려잇는걸 닫아야 한다 
				//body로 modifyForm을 다시이동 시켜 놓는드ㅏ.
				if($('#modifyForm').css('display') !="none"){
				replyReset();
			}
			
			renum=idx;
			modifyCont=$(this).parents('.rep').find('.cont').html();
			modifyCont = modifyCont.replace(/<br>/g,"\n") ;//replaceAll이 없어서 
			
			$('#modifyForm #test').val(modifyCont);
			$(this).parents('.rep').find('.cont').empty().append($('#modifyForm')); 
			//.append($('#modifyForm') :div 안에 들어가게 하기위해서 $ 씀 
			
			$('#modifyForm').show();
			}else if(name =="r_delete"){
				renum=idx;
				replyDeleteServer(this); //삭제는 번호만 잇으면 돼.this : 삭제버튼 누르면 -> js로 보내기 
// 				$(this).parents('.rep').remove(); //js로 보내
			}
		})
		
// 		modifyCont ="";
		//이미 modifyForm이 열려 상태에서 다시 modifyForm이 열릴때  
		replyReset=function(){
			modispan= $('#modifyForm').parent();	
			$('body').append($('#modifyForm')); //바디로 get으로 옮기고 set으로 받아야해
			$('#modifyForm').hide();
			modispan.html(modifyCont.replace(/\n/g,"<br>"));
		}
		
		//댓글 수정폼에서 확인버튼클릭
		$('#btnOK').on('click',function(){
			//댓글내용 가져오기 \n을 <br>로 바꾼다
			modifyCont=$('#modifyForm #test').val();
			modispan= $('#modifyForm').parent();	
			$('body').append($('#modifyForm')); 
			$('#modifyForm').hide();
			modispan.html(modifyCont.replace(/\n/g,"<br>")); // \n을 <br>로 바꾼다
			
			//db를 수정 =>sql문, dao, service에 메서드 추가 
			//renum, modifyCont가져감. idx값 필요해 
			
			replyUpdateServer();
			
			
		})
		//댓글 수정 폼에서 취소버튼 클릭 
		$('#btnReset').on('click',function(){
			//창만 닫으면 되고 원래 내용갖다 놓면 돼./
			replyReset();
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
	<div id = "modifyForm" style="display:none;">
		<textarea id="test" class="" row="5" cols="30"></textarea>
		<input type="button" value="확인" id="btnOK">
		<input type="button" value="취소" id="btnReset">
	
	</div>



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
	<!-- 글 수정   Modal -->
<div id="updateModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">새글 작성 창입니다. </h4>
      </div>
      <div class="modal-body">
      <form id="uform" name = "uform">
	        제목 : <input class="txt2" type="text" name="subject" id="subject2"> <br><br>
	        작성자 : <input class="txt" type="text" name="writer" id="writer2"> <br><br>
	        비밀번호 : <input class="txt2" type="text" name="password" id ="password2"> <br><br>
	        메일 : <input class="txt2" type="text" name="mail" id="mail2"> <br><br>
	        내용 : <br>
	        <textarea class="txt2" rows="10" cols="30" name="content" id="content2"> </textarea>
	        <br><br>
        </form>
      </div>
      <div class="modal-footer">
	        <button type="button" class="action btn btn-warning" name="wok" id="wok2">확인</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>


	

</body>
</html>