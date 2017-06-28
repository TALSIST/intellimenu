<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/css/business-casual.css" rel="stylesheet">
 -->
<div class="container">
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
				<a class="navbar-brand" href="#">종류별 레시피</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:forEach var="vo" items="${list }">
						<li><a
							href="/recipe/recipe_sublist?cat_sub_id=${vo.id}&name=${vo.name}">${vo.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
	관리자선정 레시피 페이지
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
				<a href="/recipe/recipe_detail?id=1"><img class="img-responsive"
					src="/img/P_1.JPG" width="750px" alt=""></a>
				<h3>
					채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
				</h3>
			</div>
			<div class="col-sm-4 text-center">
				<a href="/recipe/recipe_detail?id=2"><img class="img-responsive"
					src="/img/P_2.JPG" width="750px" alt=""></a>
				<h3>
					채소디톡스주스 <br> <small>by VEGE O'CLOKC</small>
				</h3>
			</div>
			<div class="col-sm-4 text-center">
				<a href="/recipe/recipe_detail?id=3"><img class="img-responsive"
					src="/img/P_3.JPG" width="750px" alt=""></a>
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