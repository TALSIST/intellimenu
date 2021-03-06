<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row text-center">
	<h1>레시피 목록 <small>${catname}</small></h1>
</div>

<!-- 상단툴바 -->
<div class="row">
	<div class="fixed-table-toolbar">
		<div class="bs-bars pull-left">
			<div id="toolbar">
				<button id="remove" class="btn btn-danger">
					<i class="glyphicon glyphicon-remove"></i> 삭제</button>
			</div>
		</div>
		<div class="columns columns-right btn-group pull-right">
			<button id="search-btn" class="btn btn-default">
				<i class="glyphicon glyphicon-search"></i> 검색</button>
			<div class="btn-group">
				<button class="btn btn-default dropdown-toggle" aria-label="export type"
					title="Export data" data-toggle="dropdown" type="button">
					<i class="glyphicon glyphicon-list"></i> 분류 선택<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<c:forEach var="vo" items="${subList}">
						<li><a href="/admin/recipe/list?cat=${vo.id}">${vo.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="pull-right search">
			<input class="form-control" type="text" placeholder="검색어 입력">
		</div>
	</div>
</div>

<!-- 본문 -->
<div class="bootstrap-table">
	<table class="table table-hover">
		<tr class="active">
			<th><input type="checkbox" id="chk-head"></th>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td><input type="checkbox" class="chk-list" value="${vo.id}"></td>
			<td>${vo.id}</td>
			<td><a href="/admin/recipe/list/detail?id=${vo.id}">${vo.title}</a></td>
			<td>${vo.user_id}</td>
			<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd" /></td>
			<td>${vo.hit}</td>
		</tr>
		</c:forEach>
	</table>
</div>

<!-- Pagination -->
<div class="fixed-table-pagination" style="display: block;">
	<div class="pull-left pagination-detail">
		<span class="pagination-info">
		<b>${pmgr.start}번</b> 부터 <b>${pmgr.end}번</b> / 전체 게시물 <b>${pmgr.total}건</b>
		</span>
	</div>
	<div class="pull-right pagination">
		<ul class="pagination">
			<li><a href="?cat=${cat}&page=${pmgr.prevBtn}">&laquo;</a></li>
			<c:forEach var="i" begin="${pmgr.startBlock}" end="${pmgr.endBlock}">
				<li <c:if test="${pmgr.page==i}">class="active"</c:if>>
				<a href="?cat=${cat}&page=${i}">${i}</a></li>
			</c:forEach>
			<li><a href="?cat=${cat}&page=${pmgr.nextBtn}">&raquo;</a></li>
		</ul>
	</div>
</div>
