<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Insert title here</title>
</head>
<title>만개의 레시피</title>

<link href="/css/business-casual.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>
	
</script>
<body>
	<div class="container">
 		<nav class="navbar navbar-default" role="navigation">
			<div class="container">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
	                <a class="navbar-brand" href="index.html">Business Casual</a>
	            </div>
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav">
	                    <li>
	                     	  <a href="#">종류별 : </a>
	                    </li>
	                    <c:forEach var="vo1" items="${list1 }">
		                    <li>
		                        <a href="/recipe/recipe_sub_list?id=${vo1.id}">${vo1.name}</a>
		                    </li>
	                  	</c:forEach>
	                </ul>
	            </div>
		            <hr width="1000px" >
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav">
	                    <li>
	                       	<a href="#">상황별 : </a> 
	                    </li>
	                    <c:forEach var="vo2" items="${list2 }">
		                    <li>
		                        <a href="/recipe/recipe_sub_list?id=${vo2.id}">${vo2.name}</a>
		                    </li>
	                  	</c:forEach>
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->
	        </div>
  		</nav>

		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<hr>
					<h2 class="intro-text text-center">
						<strong>10분이면 OK! 심심풀이 주전부리 간식</strong>
					</h2>
					<hr>
				</div>
				<div class="col-sm-4 text-center">
					<a href="/recipe/recipe_detail?id=1"><img class="img-responsive" src="/img/P_1.JPG" width="750px" alt=""></a>
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="col-sm-4 text-center">
					<a href="/recipe/recipe_detail?id=2"><img class="img-responsive" src="/img/P_2.JPG" width="750px" alt=""></a>
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="col-sm-4 text-center">
					<a href="/recipe/recipe_detail?id=3"><img class="img-responsive" src="/img/P_3.JPG" width="750px" alt=""></a>
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<hr>
					<h2 class="intro-text text-center">
						<strong>봄철 좋은 비타민 요리 </strong>
					</h2>
					<hr>
				</div>
				<div class="col-sm-4 text-center">
					<img class="img-responsive" src="/img/P_4.JPG" width="750px" alt="">
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="col-sm-4 text-center">
					<img class="img-responsive" src="/img/P_5.JPG" width="750px" alt="">
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="col-sm-4 text-center">
					<img class="img-responsive" src="/img/P_6.JPG" width="750px" alt="">
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<hr>
					<h2 class="intro-text text-center">
						<strong>그린그린그린 </strong>
					</h2>
					<hr>
				</div>
				<div class="col-sm-4 text-center">
					<img class="img-responsive" src="/img/P_7.JPG" width="750px" alt="">
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="col-sm-4 text-center">
					<img class="img-responsive" src="/img/P_8.JPG" width="750px" alt="">
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="col-sm-4 text-center">
					<img class="img-responsive" src="/img/P_9.JPG" width="750px" alt="">
					<h3>
						채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

	</div>
	<!-- /.container -->
</body>
</html>