<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">

	<div class="row">
		<div class="row">
			<a href="#insert">식당 추가</a>
		</div>
		<table class="table" width=100%>
			<tr>
				<th width=10%>번호</th>
				<th width=45%>제목</th>
				<th width=15%>종류</th>
				<th width=20%>작성일</th>
				<th width=10%>조회수</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td width=10% align=center>${vo.id }</td>
					<td width=45%><a href="#detail"> ${vo.name }</a></td>
					<td width=15% align=center>${vo.category }</td>
					<td width=20% align=center><fmt:formatDate value="${vo.regdate }"
							pattern="yyyy-MM-dd" /></td>
					<td width=10% align=center>${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="row">
		<ul class="pagination">
		    <li><a href="#">&laquo;</a></li>
		    <li><a href="#">1</a></li>
		    <li><a href="#">2</a></li>
		    <li><a href="#">3</a></li>
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li><a href="#">&raquo;</a></li>
		</ul>
		</div>
	</div>

</div>
