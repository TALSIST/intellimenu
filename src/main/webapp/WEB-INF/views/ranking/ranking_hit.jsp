<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- jQuery CSS -->
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
  <!-- bootstrap -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-sm-9">
			<h4>
				<img src="img/1.png" class="img-circle" alt="Cinque Terre"
					style="width: 30px; height: 30px">좋은 레시피를 많이 등록한 열혈 쉐프
			</h4>
		</div>

		<div class="col-sm-2">
			<select class="form-control" style="width: 130px; margin-left: 70px">
				<option>2017년</option>
				<option>2016년</option>
				<option>2015년</option>
			</select>
		</div>
		<div class="col-sm-1">
			<select class="form-control">
				<option>1월</option>
				<option>2월</option>
			</select>

		</div>
	</div>
	<br>
	<c:forEach var="i" items="${list}" begin="0" varStatus="status" end="9">
	<div class="list-group">
		<a href="#" class="list-group-item"> <span
			style="color: green; font-size: 25pt; position: relative; margin: 100px 20px 2px 5px;">${i+1}</span>
			<img src="img/4.png" class="img-circle" alt="Cinque Terre"
			style="width: 70px; height: 70px; margin-right: 10px"> <span
			style="font-size: 15pt; font-weight: bold;">${list.title}</span><span class="badge">${list.hit}</span>
		</a>
	</div>
	</c:forEach>
</body>
</html>