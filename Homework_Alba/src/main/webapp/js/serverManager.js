/**
 * 
 */
var leftArea = $('#leftArea');
var rightArea = $('#rightArea');
	var leftSrc = $('.leftSrc');
	var rightTarget = $('.rightTarget');
	var serverFileForm = $("#serverFileForm");
	var commandForm = $("#commandForm")
	var srcFile = $("#srcFile");
	// 0919
	$(".managerForm").on("submit",function(event){
		event.preventDefault();
		console.log(this);
		var queryString = $(this).serialize();
		var method = $(this).attr("method")
		console.log(queryString);
		$.ajax({
			url : $.getContextPath()+ "/serverFileManager",
			method : method?method:"get",
			data : queryString,
			dataType : "json", // dataType : 응답 데이터 타입 html,xml, json,
								// script
			success : function(resp) { // 응답데이터를 resp 로 받음.
				console.log(resp);
				if(resp.leftfiles){
					let liTags=[];
					$(resp.leftfiles).each(function(i, element) {
						let liTag = $("<li>").prop("id", element.id)
						    	 .attr("class",element.file?"file":"dir")
						    	 .text(element.displayName);
						liTags.push(liTag);
					});
					leftArea.html(liTags);
					
				}
				if(resp.rightfiles){
					let liTags=[]; // let:틀럭으로 한정된
					$.each(resp.rightfiles,function(){
						let element =this // $ 쓰면 제이쿼리가 됨. 없이 쓰기
						let liTag = $("<li>").prop("id", element.id)
									    	 .attr("class",element.file?"file":"dir")
									    	 .text(element.displayName);
						liTags.push(liTag);
					});
					rightArea.html(liTags);
				}
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		});
		return false;
	})
	
	$("li").css({cursor:"pointer"});
	$(".groupUL").on("dblclick",".dir", function() { // 더블클릭
		if ($(this).parent().prop("id")=='leftArea') {
			$(leftSrc).val($(this).prop("id"));
		} else {
			$(rightTarget).val($(this).prop("id"));
		}
		serverFileForm.submit();
		// 여기에 ajax코드 넣기
	});

	$("#leftArea").on("click", ".file", function() {
		$(this).siblings("li").removeClass("active");
		$(this).toggleClass("active") // 현재 엘리먼트값에 클래스추가할수있다 .
		if ($(this).hasClass("active")) {
			srcFile.val($(this).prop("id")); // 액티브추가됐을때 실행되야함
		}else{
			srcFile.val("");				
		}
	})
