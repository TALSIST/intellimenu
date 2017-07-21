<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<div class="container">

	<h1 class="page-header">자료 현황</h1>

    <div class="row">
        <div class="col-xs-6">
            <div id="container" style="min-width: 850px; height: 750px; margin: 0 auto"></div>
        </div>    
        <div class="col-xs-6">
        
        </div>    
    </div>

</div>

<script>
//Create the chart
Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'INTELLIMENU 데이터 현황'
    },
    subtitle: {
        text: '각 그래프를 클릭하시면 상세내용을 확인할 수 있습니다'
    },
    xAxis: {
        type: 'category'
    },
    yAxis: {
        title: {
            text: '게시물 수'
        }

    },
    legend: {
        enabled: false
    },
    plotOptions: {
        series: {
            borderWidth: 0,
            dataLabels: {
                enabled: true,
                format: '{point.y}'
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}건</b> of total<br/>'
    },

    series: [{
        name: 'STAT',
        colorByPoint: true,
        data: [{
            name: '레시피',
            y: ${recipe},
            drilldown: null
        }, {
            name: '태그',
            y: ${tag},
            drilldown: null
        }, {
            name: '재료',
            y: ${ingredient},
            drilldown: null
        }, {
            name: '음식점',
            y: ${restaurant},
            drilldown: null
        }, {
            name: '회원',
            y: ${users},
            drilldown: null
        }]
    }]
});
</script>

