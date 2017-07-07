<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
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
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong># ${tagName } </strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="vo" items="${list }">
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
		<div class="col-sm-offset-4 col-lg-offset-4 col-sm-4 col-lg-4">
			<ul class="pager">
				<li class="previous"><a
					href="/recipe/recipe_tag_list?tagName=${tagName }&page=${page>1?page-1:page}">이전글</a>
				</li> ${page } / ${totalPage } page
				</li>
				<li class="next"><a
					href="/recipe/recipe_tag_list?tagName=${tagName }&page=${page<totalPage?page+1:page}">다음글</a>
				</li>
			</ul>
		</div>
	</div>
</div>