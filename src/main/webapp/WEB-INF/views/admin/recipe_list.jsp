<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<div class="row text-center">
		<h1>레시피 목록</h1>
	</div>
	
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
					<c:forEach var="vo" items="${subList}">
						<li><a href="/admin/recipe_list?cat=${vo.id}">${vo.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>
	
	<div class="row">
		<table class="table table-hover" width=100%>
			<tr>
				<th width=10%>체크박스</th>
				<th width=10%>일렬번호</th>
				<th width=45%>제목</th>
				<th width=15%>작성자</th>
				<th width=20%>작성일</th>
			</tr>
			<c:forEach var="vo" items="${list}">
				<tr>
					<td width=10% align=center><input type="checkbox"></td>
					<td width=10% align=center>${vo.id}</td>
					<td width=45%><a href="/recipe/recipe_detail?id=${vo.id}">${vo.title}</a></td>
					<td width=15% align=center>${vo.user_id}</td>
					<td width=20% align=center><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="row text-center">
		<ul class="pagination">
		    <li><a href="?cat=${cat}&page=${pmgr.prevBtn}">&laquo;</a></li>
			<c:forEach var="i" begin="${pmgr.startBlock}" end="${pmgr.endBlock}">
		    <li <c:if test="${pmgr.page==i}">class="active"</c:if>><a href="?cat=${cat}&page=${i}">${i}</a></li>
			</c:forEach>
		    <li><a href="?cat=${cat}&page=${pmgr.nextBtn}">&raquo;</a></li>
		</ul>
	</div>
</div>
