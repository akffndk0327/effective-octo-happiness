/**
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2019. 11. 9.      최서희      최초작성
* Copyright (c) 2019 by DDIT All right reserved
*/

$.fn.selectLprodA = function(cPath){
      let prod_lguTag = this; 
      $.ajax({
         url : cPath+"/prod/getLprodAList.do",
         dataType : "json",
         success : function(resp) {
            let options =[];
            $(resp).each(function(index,lprod){
               options.push(
                  $("<option>").text(lprod.lprodNm)
                            .attr({value:lprod.lprodId})
                  );
            });
            $(prod_lguTag).append(options);
            $(prod_lguTag).trigger("change");
         },
         error : function(err) {
            console.log(err.status);
         }

      });
   }

$.fn.selectLprodB=function(param){
   let lgu = param.lgu;
   let plgu = param.plgu;
   let cPath = param.cPath;
   let prod_ParentTag = this;
   $.ajax({
      url : cPath+"/prod/getLprodBList.do",
      data : {
    	  lprodParent:lgu?lgu:""
      },
      dataType : "json",
      success : function(resp) {
         let options =[];
         $(resp).each(function(index,lprod){
            options.push(
               $("<option>").text(lprod.lprodNm)
                         .attr({"value":lprod.lprodId,
                               "class":lprod.lprodParent
                           })
                         .prop("selected", lprod.buyer_id == plgu)
               );
         });
         var delOptions = $(prod_ParentTag).find("option:gt(0)");
         $(prod_ParentTag).remove("option:gt(0)"); //첫번째 이후의 것을 리무브
         $(delOptions).remove();
         $(prod_ParentTag).append(options);
      },
      error : function(err) {
         console.log(err.status);
      }

   });
}
