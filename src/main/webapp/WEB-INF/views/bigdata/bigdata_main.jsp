<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

body {
    font: 10px sans-serif;
 }
li{
  font: 15px sans-serif;
}
    .axis path,
    .axis line {
        fill: none;
        stroke: #000;
        shape-rendering: crispEdges;
    }

    .x.axis path {
        display: none;
    }

    .line {
        fill: none;
        stroke: steelblue;
        stroke-width: 1.5px;
    }
    svg{
        border:1px solid black;
    }
    .grid {
        fill: none;
        shape-rendering: crispEdges;
        stroke: lightgrey;
        opacity: 0.7;
        stroke-width: 1px;
    }
    .segmentText{
        cursor:pointer;
    }
    div.tooltip {
        position: absolute;
        text-align: center;
        width: 120px;
        height: 15px;
        padding: 5px;
        font: 12px sans-serif;
        background: #ddd;
        border: solid 1px #aaa;
        border-radius: 8px;
        pointer-events: none;
    }
</style>
<script>
$(function() {
	//이미지 크기 일정하게
	
	var maxWidth = -1;
	$('.col-sm-4.text-center.sublist').each(function() {
		maxWidth = $(this).width();
		$(this).height(maxWidth*0.6+70)
	});

	$('.img-responsive.sublist').each(function() {
		$(this).width(maxWidth);
		$(this).height(maxWidth*0.6);
	});

});

/* function showGraph(){
	 $.ajax({
		 type:'POST',
		 url:'/main/graph',
		 success:function(response){
			//alert(response);
			 $('#graph').html(response);	 
			 
		 }
	 });
	 
}
$(function(){
	showGraph();
	setInterval("showGraph()", 10000);
	
 });
  */
 
$(function(){
	 (function showGraph(){
		 $.ajax({
			 type:'POST',
			 url:'/main/graph',
			 success:function(response){
				//alert(response);
				 $('#graph').html(response);	 
				 
			 }
		 });
		 setTimeout(showGraph, 10000);
		 
	 })();
	
});

  
	 
	
	 
 
 
</script>

<div class="container">
	${hello }
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>SNS 인기음식<br>
					</strong>
				</h1>
				<hr>
			</div>
			<div id="graph"></div>
	
			<div class="clearfix"></div>
		</div>
	
	</div>
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>${weather}에 추천하는 재료/레시피</strong>
					<br />
					<c:forEach var="vo" items="${weatherIngrlist }" varStatus="rank">
						 <a href="/search/search_total_result?searchParam=전체&searchKeyword=${vo.name }">
							 ${vo.name }
						 </a>&nbsp;&nbsp;
					</c:forEach>
				</h1>
				<hr>
			</div>
			<c:forEach var="vo" items="${RecipeListOnWeahter }">
					<div class="col-sm-4 text-center sublist">
						<a href="/recipe/recipe_detail?id=${vo.id}&page=${page}">
							<img class="img-responsive sublist" src="${vo.img}" alt="">
						</a>
						<h3>
							${vo.title } <br>
							<small>
								by 
								<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
								 	${vo.nickname}
								</a>
							</small>
						</h3>
					</div>
				</c:forEach>
			<div class="clearfix"></div>
		</div>
	</div>
	
	
	<div class="row">
	<!-- start 여기에 붙여넣으세요 -->
	 <div class="row">
       <div class="box">
          <div class="col-lg-12">
             <hr>
				<h1 class="intro-text text-center">
					<strong>7월 21일 대형마트에서 자주 찾는 채소류 재료 " ${todayHitItem} "
					</strong>
				</h1>
				<hr>
			  <div>
		 	      <h3> 롯데마트/홈플러스/이마트 최근 3일 인기순위 </h3> 
			   	  <img id="divChartTrends" style="display:inline;">
			   	  <label style="width:100px;height:300px"></label>
			 	  <canvas id="word_cloud" class="word_cloud" width=300px, height=300px style="display:inline;"></canvas>
     		  </div>
     		  <label style="width:800px;height:30px"></label>
     		  <div>
             	 <c:forEach var="vo" items="${randomMartList}">
					<div class="col-sm-4 text-center sublist">
						<a href="/recipe/recipe_detail?id=${vo.id}&page=${page}">
							<img class="img-responsive sublist" src="${vo.img}" alt="">
						</a>
						<h3>
							${vo.title } <br>
							<small>
								by 
								<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
								 	${vo.nickname}
								</a>
							</small>
						</h3>
					</div>
				</c:forEach> 
			 </div>	
        </div>
	</div>	
	<!-- end 여기에 붙여넣으세요 -->
	<!--  -->

	<!--  -->
	</div>
</div>

<!-- div 밑에 안붙이면 그래프라 안뜸. 왜일까? -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.5/d3.min.js"></script>
<script>
   var Data=${lineData};
      function fnDrawMultiLineChart(Data, DivID, RevenueName) {
          var margin = { top: 20, right: 80, bottom: 30, left: 50 },
           width = 600 - margin.left - margin.right,
           height = 300 - margin.top - margin.bottom;
          var parseDate = d3.time.format("%d-%b");
          var x = d3.scale.ordinal()
                  .rangeRoundBands([0, width]);
          var y = d3.scale.linear()
                  .range([height, 0]);
          var color = d3.scale.category10();
          var xAxis = d3.svg.axis()
              .scale(x)
              .orient("bottom");
          var yAxis = d3.svg.axis()
              .scale(y)
              .orient("left")
              .ticks(10);
          // xData gives an array of distinct 'Weeks' for which trends chart is going to be made.
          var xData = Data[0].WeeklyData.map(function (d) { return parseDate(new Date(d.week)); });
          //console.log(xData);
          var line = d3.svg.line()
              //.interpolate("basis")
              .x(function (d) { return x(parseDate(new Date(d.week))) + x.rangeBand() / 2; })
              .y(function (d) { return y(d.value); });
          var svg = d3.select("#" + DivID).append("svg")
              .attr("width", width + margin.left + margin.right)
              .attr("height", height + margin.top + margin.bottom)
              .append("g")
              .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
          color.domain(Data.map(function (d) { return d.name; }));
          x.domain(xData);
          var valueMax = d3.max(Data, function (r) { return d3.max(r.WeeklyData, function (d) { return d.value; }) });
          var valueMin = d3.min(Data, function (r) { return d3.min(r.WeeklyData, function (d) { return d.value; }) });
          y.domain([valueMin, valueMax]);
          //Drawing X Axis
          svg.append("g")
                  .attr("class", "x axis")
                  .attr("transform", "translate(0," + height + ")")
                  .call(xAxis);
          // Drawing Horizontal grid lines.
          svg.append("g")
              .attr("class", "GridX")
            .selectAll("line.grid").data(y.ticks()).enter()
              .append("line")
              .attr(
              {
                  "class": "grid",
                  "x1": x(xData[0]),
                  "x2": x(xData[xData.length - 1]) + x.rangeBand() / 2,
                  "y1": function (d) { return y(d); },
                  "y2": function (d) { return y(d); }
              });
          // Drawing Y Axis
          svg.append("g")
              .attr("class", "y axis")
              .call(yAxis)
              .append("text")
                  .attr("transform", "rotate(-90)")
                  .attr("y", 6)
                  .attr("dy", ".71em")
                  .style("text-anchor", "end")
                  .text(RevenueName);
          // Drawing Lines for each segments
          var segment = svg.selectAll(".segment")
                          .data(Data)
                          .enter().append("g")
                          .attr("class", "segment");
          segment.append("path")
                  .attr("class", "line")
                  .attr("id", function (d) { return d.name; })
                  .attr("visible",1)
                  .attr("d", function (d) { return line(d.WeeklyData); })
                  .style("stroke", function (d) { return color(d.name); });
                      // Creating Dots on line
          segment.selectAll("dot")
                  .data(function (d) { return d.WeeklyData; })
                  .enter().append("circle")
                  .attr("r", 5)
                  .attr("cx", function (d) { return x(parseDate(new Date(d.week))) + x.rangeBand() / 2; })
                  .attr("cy", function (d) { return y(d.value); })
                  .style("stroke", "white")
                  .style("fill", function (d) { return color(this.parentNode.__data__.name); })
                  .on("mouseover", mouseover)
                  .on("mousemove", function (d) {
                      divToolTip
                      .text(this.parentNode.__data__.name +" : "+ d.value)
                      .style("left", (d3.event.pageX + 15) + "px")
                      .style("top", (d3.event.pageY - 10) + "px");
                  })
                  .on("mouseout", mouseout);
        
          segment.append("text")
                  .datum(function (d) { return { name: d.name, RevData: d.WeeklyData[d.WeeklyData.length - 1] }; })
                  .attr("transform", function (d) {
                      var xpos = x(parseDate(new Date(d.RevData.week))) + x.rangeBand() / 2;
                      return "translate(" + xpos + "," + y(d.RevData.value) + ")";
                  })
                  .attr("x", 3)
                  .attr("dy", ".35em")
                  .attr("class", "segmentText")
                  .attr("Segid", function (d) { return d.name; })
                  .text(function (d) { return d.name; });
                             
          d3.selectAll(".segmentText").on("click", function (d) {
              var tempId = d3.select(this).attr("Segid");
              var flgVisible = d3.select("#" + tempId).attr("visible");

              var newOpacity = flgVisible == 1 ? 0 : 1;
              flgVisible = flgVisible == 1 ? 0 : 1;

              // Hide or show the elements
              d3.select("#" + tempId).style("opacity", newOpacity)
                  .attr("visible", flgVisible);

          });
           // Adding Tooltip
          var divToolTip = d3.select("body").append("div")
                      .attr("class", "tooltip")
                      .style("opacity", 1e-6);

          function mouseover() {
              divToolTip.transition()
                  .duration(500)
                  .style("opacity", 1);
          }
          function mouseout() {
              divToolTip.transition()
                  .duration(500)
                  .style("opacity", 1e-6);
          }
      }
//Calling function
fnDrawMultiLineChart(Data, "divChartTrends", "Revenue Data");
</script>
<!-- wordCloud용 -->
<script src="https://pulipulichen.github.io/blogger/posts/2016/11/r-text-mining/wordcloud2.js"></script>
<script>
var db = ${wordData};
list = [];
for (var i in db) {
  list.push([db[i]["word"], db[i]["freq"]])
}
WordCloud.minFontSize = "15px"
WordCloud(document.getElementById('word_cloud'), { list: list} );
</script>