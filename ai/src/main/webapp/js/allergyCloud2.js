        width = 1300,
        height = 650;


    d3.json(cPath+"/memMypage/memAllergyList.do").then(function(data){
		showCloud(data);
	});

    function showCloud (data){
    	 d3.layout.cloud().size([width, height]).words(data)    
         .rotate(0)
         .font("Impact")
         .fontSize(function(d) { 
        	 return d.allergy.allergySymtom.symStrength*60; 
          })
         .padding(3)  
         .on("end", draw)
         .start();

       function draw(words) {
           d3.select("#allergyPosition").append("svg")
             .attr("width", width)
             .attr("height", height)
           .append("g")
             .attr("transform", "translate(" + width/2 + "," + height/2 + ")")
           .selectAll("text")
             .data(words) 
           .enter().append("text")
             .style("font-size", function(d) { 
            	 let weight = d.allergy.allergySymtom.symStrength;
                 
                 return (weight? weight*25:0) + "px"; })   

             .style("font-family", "Impact")
             .attr("class", "allInfo")
             .style("fill", function(d){
            	 return d.allergy.allergySymtom.symColor})
             .attr("text-anchor", "middle")
             .style("padding", "10px 10px 10px 10px")
             .attr("transform", function(d) {
               return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
             })
           .text(function(d) { return d.allergy.allName +'-'+d.allergy.allergySymtom.symName; })
           .on("click", function(d){
        	    let thisOne = $(this);
	    		$.ajax({
	    			url : cPath+"/memMypage/memAllergyDelete.do",
	    			method : "post",
	    			data : {"memId" : d.memId,
	    					"allId" : d.allergy.allId},
	    			dataType : "json",
	    			success : function(resp) {
	    				thisOne.remove();
	    				Swal.fire({
	    					  position: 'center',
	    					  icon: 'success',
	    					  title: '알레르기가 삭제 되었습니다.',
	    					  showConfirmButton: false,
	    					  timer: 1500
	    					})
	    			},
	    			error : function(xhr) {
	    				console.log(xhr.status);
	    			}
	    		});
           });
       }
    }
    
