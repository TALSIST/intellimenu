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
	<div class="row">
		<div class="col-lg-12">
			<hr>
			<h1 class="intro-text text-center">
				<strong> 검색어: ${searchKeyword} 전체검색결과</strong>
			</h1>
			<hr>
		</div>
	<div>
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>제목검색결과 </strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="recipeVO" items="${result.titleSearchResult }">
			<div class="col-sm-4 text-center sublist">
				<a href="/recipe/recipe_detail?id=${recipeVO.id}">
					<img class="img-responsive sublist" src="${recipeVO.img}" alt="">
				</a>
				<h3>
					${recipeVO.title } <br>
					<small>
						by 
						<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
						 	${recipeVO.nickname}
						</a>
					</small>
				</h3>
			</div>
			</c:forEach>
			<c:if test="${result.titleSearchResult.size()==0 }">
				<h2>제목검색결과가 없습니다.</h2>
			</c:if>
			<div class="recipeTag" align="right">
				<li ><a href="/search/search_result?searchParam=제목&searchKeyword=${searchKeyword}">제목검색결과 더보기</a></li>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>재료검색결과 </strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="recipeVO" items="${result.ingrSearchResult }">
			<div class="col-sm-4 text-center sublist">
				<a href="/recipe/recipe_detail?id=${recipeVO.id}">
					<img class="img-responsive sublist" src="${recipeVO.img}" alt="">
				</a>
				<h3>
					${recipeVO.title } <br>
					<small>
						by 
						<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
						 	${recipeVO.nickname}
						</a>
					</small>
				</h3>
			</div>
			</c:forEach>			
			<c:if test="${result.ingrSearchResult.size()==0 }">
				<h2>재료검색결과가 없습니다.</h2>
			</c:if>
			<div class="recipeTag" align="right">
				<li ><a href="/search/search_result?searchParam=재료&searchKeyword=${searchKeyword}">재료검색결과 더보기</a></li>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
		
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>태그검색결과 </strong>
				</h1>
					
				<hr>
			</div>
			<c:forEach var="recipeVO" items="${result.tagSearchResult }">
			<div class="col-sm-4 text-center sublist">
				<a href="/recipe/recipe_detail?id=${recipeVO.id}">
					<img class="img-responsive sublist" src="${recipeVO.img}" alt="">
				</a>
				<h3>
					${recipeVO.title } <br>
					<small>
						by 
						<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
						 	${recipeVO.nickname}
						</a>
					</small>
				</h3>
			</div>
			</c:forEach>
			<c:if test="${result.tagSearchResult.size()==0 }">
				<h2>태그검색결과가 없습니다.</h2>
			</c:if>
			<div class="recipeTag" align="right">
				<li ><a href="/search/search_result?searchParam=태그&searchKeyword=${searchKeyword}">태그검색결과 더보기</a></li>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	

</div>