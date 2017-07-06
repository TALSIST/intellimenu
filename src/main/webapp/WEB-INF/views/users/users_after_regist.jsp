<%@ page contentType="text/html; charset=UTF-8" %>

<div class="container">
	<div class="row text-center" style="height: 100px;">
		<h1>${sessionScope.user.name} 회원님 환영합니다</h1>
	</div>
	<div class="row text-center" style="height: 200px;">
		<h2><small>추가정보를 입력하시면 취향에 맞는 레시피를 검색하실 수 있어요</small></h2>
	</div>
	<div class="row text-center" style="height: 200px;">
		<a href="/signup/addinfo">
		<button type="button" class="btn btn-info btn-circle btn-xl"><i class="glyphicon glyphicon-ok"></i></button>
		</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="/">
		<button type="button" class="btn btn-warning btn-circle btn-xl"><i class="glyphicon glyphicon-remove"></i></button>
		</a>
	</div>
</div>