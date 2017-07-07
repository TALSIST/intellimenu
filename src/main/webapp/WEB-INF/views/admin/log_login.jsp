<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row text-center">
	<h1>로그인 로그</h1>
</div>

<!-- 본문 -->
<div class="bootstrap-table">
	<table class="table table-hover">
		<tr class="active">
			<th>번호</th>
			<th>email</th>
			<th>닉네임</th>
			<th>ip</th>
			<th>접속시도일</th>
			<th>상태</th>
		</tr>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td>${vo.user_id}</td>
			<td>${vo.user.email}</td>
			<td>${vo.user.nickname}</td>
			<td>${vo.ip}</td>
			<td><fmt:formatDate value="${vo.reqdate}" pattern="yyyy.MM.dd HH:mm:ss" /></td>
			<td>${vo.status}</td>
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
			<li><a href="?page=${pmgr.prevBtn}">&laquo;</a></li>
			<c:forEach var="i" begin="${pmgr.startBlock}" end="${pmgr.endBlock}">
				<li <c:if test="${pmgr.page==i}">class="active"</c:if>>
				<a href="?page=${i}">${i}</a></li>
			</c:forEach>
			<li><a href="?page=${pmgr.nextBtn}">&raquo;</a></li>
		</ul>
	</div>
</div>

