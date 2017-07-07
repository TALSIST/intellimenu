<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<script>
	function selectEventY(){
		document.frm.submit();
	}
</script>
<body>
	<div class="row">
		<div class="col-sm-9">
			<h4>
				좋은 레시피를 많이 등록한 열혈 쉐프 TOP 10
			</h4>
		</div>

		<form name="frm" action="/ranking/rank_test" method="post">
		<div class="col-sm-2">
			<select class="form-control" style="width: 130px; margin-left: 70px" name="ydate" onChange="javascript:selectEventY()">
				<option value=2017 selected>2017년</option>
				<option value=2016>2016년</option>
				<option value=2015>2015년</option>
			</select>
		</div>
		<div class="col-sm-1">
			<select class="form-control" name="mdate" onChange="javascript:selectEventY()">
				<%for(int i=1;i<13;i++){ %>
				<option value=<%=i%>> <%=i %>월	</option>
				<%} %>
			</select>
		</div>
		</form>
	</div>
	<br>
	<div class="list-group" id="rank_list">
	
		<c:forEach var="list" items="${list}" begin="0" end="9" step="1" varStatus="status">
			<a href="/recipe/recipe_detail?id=${list.id}" class="list-group-item"> <span
				style="color: green; font-size: 25pt; position: relative; margin: 100px 20px 2px 5px;">${status.count}위</span>
				<img src="${list.img_ori}" class="img-circle" alt="Cinque Terre"
				style="width: 70px; height: 70px; margin-right: 10px"> <span
				style="font-size: 15pt; font-weight: bold;">${list.title}</span><span
				class="badge">${list.hit}</span>
			</a>
		</c:forEach>
	</div>
	
</body>
</html>