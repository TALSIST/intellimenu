<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/css/business-casual.css" rel="stylesheet">
 -->
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
</script>
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
					<c:forEach var="CatSubVO" items="${subList }">
						<li><a
							href="/recipe/recipe_sublist?cat_sub_id=${CatSubVO.id}&name=${CatSubVO.name}">${CatSubVO.name}</a>
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
				<h1 class="intro-text text-center">
					<strong>향긋한 표고버섯 </strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="recipeVO" items="${recipeList1 }">
			<div class="col-sm-4 text-center sublist">
				<a href="/recipe/recipe_detail?id=${recipeVO.id}&page=${page}">
					<img class="img-responsive sublist" src="${recipeVO.img}" alt="">
				</a>
				<h3>
					${recipeVO.title } <br> <small>by VEGE O'CLOKC</small>
				</h3>
			</div>
			</c:forEach>
			<div class="recipeTag" align="right">
				<li ><a href="/recipe/recipe_tag_list?tagName=">더보기</a></li>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>달콤한 딸기 </strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="recipeVO" items="${recipeList2 }">
			<div class="col-sm-4 text-center sublist">
				<a href="/recipe/recipe_detail?id=${recipeVO.id}&page=${page}">
					<img class="img-responsive sublist" src="${recipeVO.img}" alt="">
				</a>
				<h3>
					${recipeVO.title } <br> <small>by VEGE O'CLOKC</small>
				</h3>
			</div>
			</c:forEach>
			<div class="recipeTag" align="right">
				<li ><a href="/recipe/recipe_tag_list?tagId=">더보기</a></li>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
		
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>시원한 아이스크림 </strong>
				</h1>
					
				<hr>
			</div>
			<c:forEach var="recipeVO" items="${recipeList3 }">
			<div class="col-sm-4 text-center sublist">
				<a href="/recipe/recipe_detail?id=${recipeVO.id}&page=${page}">
					<img class="img-responsive sublist" src="${recipeVO.img}" alt="">
				</a>
				<h3>
					${recipeVO.title } <br> <small>by VEGE O'CLOKC</small>
				</h3>
			</div>
			</c:forEach>
			<div class="recipeTag" align="right">
				<li ><a href="/recipe/recipe_tag_list?tagId=">더보기</a></li>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	

</div>