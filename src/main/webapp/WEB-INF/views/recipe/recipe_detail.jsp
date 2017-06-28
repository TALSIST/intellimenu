<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/css/style.css" rel="stylesheet" type="text/css">
<link href="/css/responsive.css" rel="stylesheet" type="text/css">
<link href="/css/animate.css" rel="stylesheet" type="text/css"> -->
<div class="container">
	<h2>${recipe.title }</h2>
	<h6>${recipe.summary }by글쓴이</h6>
	<div class="row">
		<div class="col-sm-4 wow fadeInLeft delay-05s">
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa-medkit"></i>
				</div>
				<div class="service-list-col2">
					<h3>카테고리</h3>
					<p>${recipe.cat_sub_id}</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa-gear"></i>
				</div>
				<div class="service-list-col2">
					<h3>인원</h3>
					<p>${recipe.reqmember }</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa-apple"></i>
				</div>
				<div class="service-list-col2">
					<h3>난이도</h3>
					<p>${recipe.lvl }</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa-medkit"></i>
				</div>
				<div class="service-list-col2">
					<h3>시간</h3>
					<p>${recipe.time }</p>
				</div>
			</div>
		</div>
		<figure class="col-sm-8  text-right wow fadeInUp delay-02s">
			<img src="${recipe.img}" width="100%" alt="">
		</figure>

	</div>

	<hr />

	<div class="portfolioFilter">
		<ul class="Portfolio-nav wow fadeIn delay-02s">
			<li><a href="#" data-filter="*" class="current">재료</a></li>
			<li><a href="#" data-filter=".branding">양파</a></li>
			<li><a href="#" data-filter=".webdesign">당근</a></li>
			<li><a href="#" data-filter=".printdesign">소금</a></li>
		</ul>
	</div>

	<hr />

	<c:forEach var="vo" items="${contentList }">
		<div class="container">
			<div class="row">
				<figure class="col-lg-3 col-sm-2 wow fadeInLeft">
					<img src="${vo.img }" alt="">
				</figure>
				<div class="col-lg-9 col-sm-10 featured-work">
					<P class="padding-b">${vo.step+1 }.${vo.content }</P>
				</div>
			</div>
		</div>

		<hr />
	</c:forEach>


	<div class="container">
		<div class="row">
			<div class="col-lg-9 col-sm-10 featured-work">
				<P class="padding-b">태그</P>
			</div>
		</div>
	</div>


</div>
