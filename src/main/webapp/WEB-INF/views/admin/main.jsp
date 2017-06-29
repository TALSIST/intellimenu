<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Dashboard Template for Bootstrap</title>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
/*
 * Base structure
 */

/* Move down content because we have a fixed navbar that is 50px tall */
body {
	padding-top: 50px;
}

/*
 * Global add-ons
 */
.sub-header {
	padding-bottom: 10px;
	border-bottom: 1px solid #eee;
}

/*
 * Top navigation
 * Hide default border to remove 1px line.
 */
.navbar-fixed-top {
	border: 0;
}

/*
 * Sidebar
 */

/* Hide for mobile, show later */
.sidebar {
	display: none;
}

@media ( min-width : 768px) {
	.sidebar {
		position: fixed;
		top: 51px;
		bottom: 0;
		left: 0;
		z-index: 1000;
		display: block;
		padding: 20px;
		overflow-x: hidden;
		overflow-y: auto;
		/* Scrollable contents if viewport is shorter than content. */
		background-color: #f5f5f5;
		border-right: 1px solid #eee;
	}
}

/* Sidebar navigation */
.nav-sidebar {
	margin-right: -21px; /* 20px padding + 1px border */
	margin-bottom: 20px;
	margin-left: -20px;
}

.nav-sidebar>li>a {
	padding-right: 20px;
	padding-left: 20px;
}

.nav-sidebar>.active>a, .nav-sidebar>.active>a:hover, .nav-sidebar>.active>a:focus
	{
	color: #fff;
	background-color: #428bca;
}

/*
 * Main content
 */
.main {
	padding: 20px;
}

@media ( min-width : 768px) {
	.main {
		padding-right: 40px;
		padding-left: 40px;
	}
}

.main .page-header {
	margin-top: 0;
}

/*
 * Placeholder dashboard ideas
 */
.placeholders {
	margin-bottom: 30px;
	text-align: center;
}

.placeholders h4 {
	margin-bottom: 0;
}

.placeholder {
	margin-bottom: 20px;
}

.placeholder img {
	display: inline-block;
	border-radius: 50%;
}
</style>
<script>
	$(function() {
		
		var current_page = location.pathname;
		$(".side-menu").each(function() {
		     var target = $(this).attr("href");
		     console.log(current_page);
		     console.log(target);
		     if (current_page.includes(target)) {
		        $(this).parents('li').removeClass('active');
		        $(this).parent('li').addClass('active');
		     }
		});
		
	});
</script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">관리자 화면</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="/">MAIN</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="main" class="side-menu">현황</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="recipe_list" class="side-menu">레시피 목록</a></li>
					<li><a href="#" class="side-menu">레시피 추가</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="restaurant_list" class="side-menu">음식점 목록</a></li>
					<li><a href="#" class="side-menu">음식점 댓글 관리</a></li>
					<li><a href="#" class="side-menu">음식점 추가</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="#" class="side-menu">요리 교실 목록</a></li>
					<li><a href="#" class="side-menu">수업 신청 현황</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="#" class="side-menu">회원 목록</a></li>
					<li><a href="#" class="side-menu">회원 등록</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="#" class="side-menu">태그 관리</a></li>
					<li><a href="#" class="side-menu">검색어 로그</a></li>
				</ul>
			</div>
			<div id="admin-main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<tiles:insertAttribute name="content" />

			</div>
		</div>
	</div>


</body>
</html>
