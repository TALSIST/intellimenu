<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style.css">
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
    		  </div>
	  				<div>
	  					<img id="highcart-container" style="display:inline;float:left">
	  					<label style="width:100px;height:300px"></label>
	  					<canvas id="word_cloud" class="word_cloud" width=300px height=300px style="display:inline;"></canvas>
					</div>
					</div>
   
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
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/highcharts/4.0.4/highcharts.js'></script>
<script>
 function onSelect (item) {
	  var self = this,
	      elementId = 'highcart',
	      chartId = '#highcart-container',
	      chart = jQuery(chartId).highcharts();
	  
	  function updateLegend(chartItem) {
	    var legend = jQuery('#' + elementId + '-legend-' + chartItem.name),
	        allLegendsActive = true;

	    if (chartItem.visible) {
	      chartItem.hide();
	      legend.addClass('disabled');
	    } else {
	      chartItem.show();
	      legend.removeClass('disabled');
	    }
	    chart.series.forEach(function (item) {
	      if (!item.visible) {
	        allLegendsActive = false;
	      }
	    });
	  }

	  if (item) {
	    var chartItem = jQuery.grep(chart.series, function (s) { return s.name == item; })[0];
	    updateLegend(chartItem);
	  }
	};

	(function () {
	 var categories = ['Jan. 1', 'Jan. 2', 'Jan. 3'];
	 var series = ${series};

	  var options = {
	    //Chart area
	    chart: {
	      type: 'line',
	      plotBackgroundColor: '#F7F7F7'
	    },
	    credits: {
	      enabled: false
	    },
	    title: {
	      text: null
	    },
	    xAxis: {
	      gridLineDashStyle: 'Dot',
	      gridLineWidth: 1.4,
	      gridLineColor: '#999999',
	      labels: {
	        enabled: true,
	        formatter: function () {
	          return categories[this.value];
	        },
	        style: {
	          color: '#CCCCCC',
	          fontWeight: 'normal'
	        }
	      },
	      lineWidth: 1.4,
	      tickInterval: 1,
	      minPadding: 0,
	      maxPadding: 0,
	      startOnTick: true,
	      endOnTick: true
	    },
	    yAxis: {
	      title: {
	        text: null
	      },
	      floor: 0,
	      lineWidth: 1.4,
	      gridLineDashStyle: 'Dot',
	      gridLineWidth: 1.4,
	      gridLineColor: '#999999',
	      labels: {
	        style: {
	          color: '#373737',
	          fontWeight: 'normal'
	        }
	      }
	    },
	    plotOptions: {
	      line: {
	        marker: {
	          enabled: false,
	          radius: 3,
	          symbol: 'circle',
	          states: {
	            hover: {
	              enabled: true
	            },
	            select: {
	              enabled: true
	            }
	          }
	        }
	      }
	    },
	    tooltip: {
	      enabled: true,
	      shared: true,
	      useHTML: true,
	      positioner: function (labelWidth, labelHeight, point) {
	        var chart = this.chart,
	            medianX = (chart.plotSizeX + chart.plotLeft)/2,
	            position = { y: 50 };
	        //Position on the right
	        if (medianX >= point.plotX) {
	          position.x = chart.plotSizeX - (labelWidth + 10) ;
	        }
	        //Position on the left
	        else {
	          position.x = chart.plotLeft + 40;
	        }
	        return position;
	      },
	      formatter: function () {
	        var text = '';
	        text = text.concat(
	          '<strong>',
	          categories[this.x],
	          '</strong></br>'
	        );
	        this.points.forEach(function (point) {
	          var data = point.series;
	          text = text.concat(
	            '<span style="color:',
	            data.color,
	            ';">&#x25CF;</span><span>&nbsp;',
	            data.name,
	            '&#x3a;</span><strong>&nbsp;',
	            point.y,
	            '</strong></br>'
	          );
	        });
	        return text;
	      },
	      style: {
	        fontSize: '12px',
	        padding: '15px'
	      }
	    },
	    legend: {
	      enabled: false
	    },
	    series: series
	  };

	  jQuery('#highcart-container').highcharts(options);
	  
	  jQuery('#highcart-legend-Starts').click(function () { onSelect('Starts'); });
	  jQuery('#highcart-legend-Completes').click(function () { onSelect('Completes'); });
	  jQuery('#highcart-legend-Terminates').click(function () { onSelect('Terminates'); });
	  jQuery('#highcart-legend-Cheaters').click(function () { onSelect('Cheaters'); });
	  jQuery('#highcart-legend-Dropouts').click(function () { onSelect('Dropouts'); });
	})();
 </script>