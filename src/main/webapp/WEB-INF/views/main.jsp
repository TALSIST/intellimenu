<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!doctype html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, maximum-scale=1">

  <title>IntelliMenu</title>

  <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
  
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="/css/default-style.css" rel="stylesheet" type="text/css">
  
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/js/scrolltofixed.js"></script>
  <script type="text/javascript" src="/js/headernav.js"></script>
  
</head>

<body>

  <tiles:insertAttribute name="header"/>
  
  <section class="main-section" id="service"><!--main-section-start-->
    <tiles:insertAttribute name="content"/>
  </section><!--main-section-end-->

  <tiles:insertAttribute name="footer"/>

</body>

</html>
