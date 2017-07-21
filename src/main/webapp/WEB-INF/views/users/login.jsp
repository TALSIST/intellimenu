<%@ page contentType="text/html; charset=UTF-8"%>
<div class="container">
	<div class="row">
		<div class="col-xs-8 col-xs-offset-4" id="login-form">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>로그인</strong>
					</h3>
					<div style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#">비밀번호 찾기</a>
					</div>
				</div>

				<div class="panel-body">
					<div id="login-alert" class="alert alert-danger">회원정보가 일치하지 않습니다</div>
					<div style="margin-bottom: 12px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> <input
							id="login-email" type="text" class="form-control" value="" placeholder="email">
					</div>

					<div style="margin-bottom: 12px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> <input
							id="login-password" type="password" class="form-control" placeholder="password">
					</div>

					<div class="input-group">
						<div class="checkbox" style="margin-top: 0px;">
							<label> <input id="login-remember" type="checkbox" name="remember"
								value="1"> 로그인 상태 유지
							</label>
						</div>
					</div>

					<button type="button" id="login-btn" class="btn btn-success">로그인</button>

					<hr style="margin-top: 10px; margin-bottom: 10px;">

					<div class="form-group">
						<div style="font-size: 85%">
							회원이 아니시라면<a href="/signup">여기를 눌러 가입하세요</a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>