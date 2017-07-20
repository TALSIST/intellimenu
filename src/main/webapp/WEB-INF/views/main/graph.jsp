<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="chartdiv"></div>
<script>
var chart = AmCharts.makeChart("chartdiv", {
			  	  "type": "serial",
			  	  "startDuration": 2,
			  	  "dataProvider": <%=request.getAttribute("json") %>,
			  	  "valueAxes": [{
			  	    "position": "left",
			  	    "axisAlpha": 0,
			  	    "gridAlpha": 0
			  	  }],
			  	  "graphs": [{
			  	    "balloonText": "[[category]]: <b>[[value]]</b>",
			  	    "colorField": "color",
			  	    "fillAlphas": 0.85,
			  	    "lineAlpha": 0.1,
			  	    "type": "column",
			  	    "topRadius": 1,
			  	    "valueField": "visits"
			  	  }],
			  	  "depth3D": 40,
			  	  "angle": 30,
			  	  "chartCursor": {
			  	    "categoryBalloonEnabled": false,
			  	    "cursorAlpha": 0,
			  	    "zoomable": false
			  	  },
			  	  "categoryField": "country",
			  	  "categoryAxis": {
			  	    "gridPosition": "start",
			  	    "axisAlpha": 0,
			  	    "gridAlpha": 0
			
			  	  },
			  	  "exportConfig": {
			  	    "menuTop": "20px",
			  	    "menuRight": "20px",
			  	    "menuItems": [{
			  	      "icon": '/lib/3/images/export.png',
			  	      "format": 'png'
			  	    }]
			  	  }
			  	}, 0);
			 
			
			  	jQuery('.chart-input').off().on('input change', function() {
			  	  var property = jQuery(this).data('property');
			  	  var target = chart;
			  	  chart.startDuration = 0;
					
			  	  if (property == 'topRadius') {
			  	    target = chart.graphs[0];
			  	  }
			
			  	  target[property] = this.value;
			  	  chart.validateNow();
			  	});
</script>			  	

