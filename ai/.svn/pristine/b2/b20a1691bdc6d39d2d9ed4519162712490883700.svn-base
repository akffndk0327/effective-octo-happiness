/**
 * d3.js 로 word cloud 생성
 * handsontable 로 sheet 생성
 */

    
    //형준쓰
    //경로설정
    d3.json(cp+pmsId+lowerId+"pmsMain",function(data){
       showCloud(data);   
    })

    var width = 1300,
          height = 500
      //태그추가해줌
      var svg = d3.select("#d3").append("svg")
          .attr("width", width)
          .attr("height", height);

    
      //scale.linear: 선형적인 스케일로 표준화를 시킨다. 
      //domain: 데이터의 범위, 입력 크기
      //range: 표시할 범위, 출력 크기 
      //clamp: domain의 범위를 넘어간 값에 대하여 domain의 최대값으로 고정시킨다.
      wordScale = d3.scale.linear().domain([0, 200]).range([0, 200]).clamp(true);
      var svg = d3.select("svg")
                  .append("g")
                  .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")")
      function fillColor(d,i){
          return d.workColor;
    }           
      function showCloud(data) {
          d3.layout.cloud().size([width, height])
              //클라우드 레이아웃에 데이터 전달
              .words(data)
              .rotate(function (d) {
                  return d.workTitle.length > 3 ? 0 : 90;
              })
              //스케일로 각 단어의 크기를 설정
              .fontSize(function (d) {
                  return wordScale(d.workRate);
              })
              //클라우드 레이아웃을 초기화 > end이벤트 발생 > 연결된 함수 작동  
              .on("end", draw)
              .start();

          function draw(words) { 
              var cloud = svg.selectAll("text").data(words)
              
              //Entering words
              cloud.enter()
                  .append("text")
                  .style("font-family", "LotteMartHappy")
                  .style("fill", fillColor)
                  .style("fill-opacity", .5)
                  .attr("text-anchor", "middle") 
                  .attr('font-size', 1)
                  .attr("class", "d3Text" )
                  .attr("data-toggle", "popover")
                  .attr("data-trigger", "hover")
                  .attr("data-imgPath", function(d){
                     return d.memImg})
                  .attr("data-name",function(d){
                     return d.memName})
                  .attr("data-prog", function(d){
                     return d.workProg})
                  .text(function (d) {
                      return d.workTitle;
                  })
                  .on('mouseover', function(d){
                     d3.select('#tooltip').select('#memImg').attr("src","/projectFarm/projectImages/"+d.memImg);
                     d3.select('#tooltip').select('#value').text(d.memName);
                     d3.select('#tooltip').select('#startD').text(d.eworkStime);
                     d3.select('#tooltip').select('#endD').text(d.eworkEtime);
                     d3.select('#tooltip').select("#progress").attr("aria-valuenow", d.workProg).style("width",d.workProg +"%").text(d.workProg+"%");
                     
                     return d3.select('#tooltip').style("position","absolute").style("visibility","visible");
                  }) 
                  .on("mousemove", function(){return d3.select('#tooltip').style("top", (d3.event.pageY-10)+"px").style("left",(d3.event.pageX+10)+"px");})
                  .on('mouseout',function(){ 
                     return d3.select('#tooltip').style("visibility","hidden");   
                  })
              cloud
                  .transition()
                  .duration(600)
                  .style("font-size", function (d) {
                      return d.size + "px";
                  })
                  .attr("transform", function (d) {
                      return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                  })
                  .style("fill-opacity", 1); 
          }
          
      }
      
      
      
      
  