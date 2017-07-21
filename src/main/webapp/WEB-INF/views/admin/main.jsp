<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>관리자</title>
	
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css" rel="stylesheet">
	<link href="/css/admin-style.css" rel="stylesheet" type="text/css">
  	<link href="/css/pace-theme-corner-indicator.css" rel="stylesheet" />
  	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="/js/admin.js"></script>
	<script type="text/javascript" src='/js/pace.min.js'></script>

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
				<a class="navbar-brand" href="/admin/main">관리자 화면</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/admin/log/search">검색어 로그</a></li>
					<li><a href="/admin/log/login">로그인 로그</a></li>
					<li><a href="/">사용자 페이지로 이동</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
		
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="/admin/main" class="side-menu">+현황</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="/admin/recipe/list" class="side-menu">레시피 목록</a></li>
					<li><a href="/admin/recipe/insert" class="side-menu">레시피 추가</a></li>
					<li><a href="/admin/ingredient/list" class="side-menu">재료관리</a></li>
					<li><a href="/admin/ingredient/category" class="side-menu">재료분류</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="/admin/restaurant/list" class="side-menu">음식점 목록</a></li>
					<li><a href="/admin/restaurant/insert" class="side-menu">음식점 추가</a></li>
					<li><a href="#" class="side-menu">+신고받은 댓글</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="/admin/users/list" class="side-menu">회원 목록</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="/admin/tag/list" class="side-menu">태그 조회</a></li>
				</ul>
			</div>
			
			<div id="admin-main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<tiles:insertAttribute name="content" />

			</div>
			
		</div>
	</div>


</body>
</html>
