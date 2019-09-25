/**
 * 
 */
var currentPage = 1; 
readServer = function(cpage) {
	$.ajax({
		url: '/jqpro/BoardList',
		data:{'page' : cpage},
		dataType:"json",
		success : function(res) {
			code = '<div class="panel-group" id="accordion">';
//			$.each(res,function(i, v) {
				$.each(res.data,function(i, v) { //listpage의 data의 배열이 들어옴  
				code += '<div class="panel panel-default">';
				code += '<div class="panel-heading">';
				code += '<h4 class="panel-title">';
				code += '<a data-toggle="collapse" idx ="'+v.seq+'" name="list" class="action" data-parent="#accordion" href="#collapse'+v.seq+'">'+v.subject+'</a>';
				code += '</h4>';
				code += '  </div>                                                                   ';
				code += '  <div id="collapse'+v.seq+'" class="panel-collapse collapse">              ';
				code += '     <div class="panel-body pbody">                                      ';
				code += '      <p style="width: 80%; float: left;">작성자 :                         ';
				code += '     작성자 :'+v.writer;                         
				code += '          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      ';
				code += '	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '			이메일 :'+v.email;                                                            
				code += '			  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '	          작성일 :'+v.date;                                                            
				code += '	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '	          조회수 :'+v.hit;                                                            
				code += '	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '	          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				code += '	       </p>                                                                   ';
				code += '		   <p style="text-align : right; ">                                       ';
				code += '		   	 <button idx="'+v.seq+'" type="button" name="modify" class="action">수정</button>     ';
				code += '		   	 <button idx="'+v.seq+'" type="button" name="delete" class="action">삭제</button>     ';
				code += '		   </p>                                                                   ';
				code += '		   <hr>                                                                   ';
				code += '		   <p style="width:80%; float="left">';
				code += 			v.content;
				code += '		   </p>                                                                   ';
				code += '		   <textarea class="area" cols="80"> </textarea>          ';
				code += '  <button idx="'+v.seq+'" style="width:40px; vertical-align:top " type="button" name="reply" class="action"> 등록 </button>';
				code += '        </div>';
				code += '      </div>';
				code += '    </div>';
			})
			code+='</div>';
			$('#accordionList').empty().append(code);
			
			//0923 페이지 이전 출력 
			$('#btngroup').empty();
			pager ="";
			if(res.spage > 1){
				pager +="<ul class='pager'>";
				pager +="<li class = 'previous'><a href='#'>Previous</a></li>";
				pager +="</ul>";
				$('#btngroup').append(pager);
			}
			
			//0923 페이지 번호 출력 
			pager="<ul class='pagination pager'>";
			for(i=res.spage; i<=res.epage; i++){
				if(currentPage ==i ){
					pager += "<li class = 'active'>";
					pager += "<a class ='paging' href = '#'>"+ i +"</a></li>";
				}else{
					pager += "<li>";
					pager += "<a class ='paging' href = '#'>"+ i +"</a></li>";
				}
			}
			pager +="</ul>";
			$('#btngroup').append(pager); //.empty() 중복안되게 
			
			//다음 
			if(res.epage <res.tpage){
				pager ="<ul class='pager'>";
				pager +="<li class ='next'><a href='#'>Next</a></li>";
				pager +="</ul>";
				$('#btngroup').append(pager);
			}
			
		},
		error : function(xhr){
			alert("상태 : " +xhr.status);
		}
	})
}
//0924
writeServer = function() {
	//입력한 모든값을 가져온다.
	indatas = $('#wform').serializeArray();
	console.log(indatas);
	$.ajax({
		url : "/jqpro/BoardWriter",
		method :"post",
		data : indatas,
		dataType :"json",
		success : function(res){
			console.log(res.sw); //저장 성공 뜨면 밑에 readServer주석 풀기
//			readServer(1); //방금 입력햇으니 성공햇으면 1페이지로 가라 
		},
		error :function(xhr){
			alert("상태 : "+xhr.status);
		}
	})
}

replyServer = function(bb) {
	$.ajax({
		url:'/jqpro/ReplySave',
		type : "post",
		data : {
			'name':rname,
			'text':rtext,
			'idx':idx
		},
		dataType : "json",
		success : function(res) {
			console.log("댓글 : "+res.sw);
			//댓글 출력 
		replyListServer(bb);
		},
		error : function(xhr) {
			alert("상태 : "+xhr.status)
		}
		
	})
}

replyListServer=function(a){ //클릭한 a 태그 
	$(a).parents('.panel').find('.pbody').find('.rep').remove();
	$.ajax({
		url:"/jqpro/ReplyList",
		method:"post",
		data:{'bonum' : bonum},
		dataType:"json",
		success :function(res){
			repl="";
			
			$.each(res,function(i,v){
				repl += '  <div class="panel-body rep">                                      ';
				repl += '      <p style="width: 80%; float: left;">작성자 :                         ';
				repl += '		<span>'+ v.name;                         
				repl += '        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;      ';
				repl += 			v.rdate;                                                            
				repl += '	     &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;             ';
				repl += '	    </span><br>        ';
				repl += '		<span class="cont">'+v.cont+"</span>";
				repl += '	  </p>                                                                   ';
				repl += '	  <p style="text-align : right; ">                                       ';
				repl += '	  	 <button idx="'+v.renum+'" type="button" name="r_modify" class="action">댓글 수정</button>     ';
				repl += '	  	 <button idx="'+v.renum+'" type="button" name="r_delete" class="action">댓글 삭제</button>     ';
				repl += '    </p>                                                                   ';
				repl += ' </div>                                                                   ';
			})
			$(a).parents('.panel').find('.pbody').append(repl);
		},
		error : function(xhr) {
			alert("상태 : "+xhr.status);
		}
		
	})
	
}




























