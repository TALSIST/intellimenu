<%@ page contentType="text/html; charset=UTF-8"%>
<script src='/js/bootstrapvalidator.js'></script>
<div class="container">
	<div class="row">
		<!-- form: -->
		<div class="page-header">
			<img src="/img/signup.jpg" class="img-rounded">
		</div>

		<div class="col-lg-8 col-lg-offset-2">
			<form id="signup" method="post" action="/signup/apply" class="form-horizontal">

				<fieldset>
					<legend>환영합니다. 아래의 내용을 입력해주세요</legend>

					<div class="form-group">
						<label class="col-lg-3 control-label">이름</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="name" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">닉네임</label>
						<div class="col-lg-5">
							<input type="text" id="nickname" class="form-control" name="nickname" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">Email주소</label>
						<div class="col-lg-5">
							<input type="text" id="email" class="form-control" name="email" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">비밀번호</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="pwd" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">비밀번호 확인</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="confirmPassword" />
						</div>
					</div>

				</fieldset>

				<div class="form-group">
					<div class="col-lg-9 col-lg-offset-3">
						<button type="submit" class="btn btn-primary">가입하기</button>
					</div>
				</div>
			</form>
		</div>
		<!-- :form -->
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#signup').bootstrapValidator({
			message : '내용을 올바르게 작성해주세요',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				name : {
					message : '올바른 이름이 아닙니다',
					validators : {
						notEmpty : {
							message : '이름을 입력해 주세요'
						},
						stringLength : {
							min : 2,
							max : 25,
							message : '이름은 2글자에서 25글자 사이로 입력가능합니다'
						},
						regexp : {
							regexp : /^[가-힣a-zA-Z]+$/,
							message : '이름은 한글이나 알파벳으로 입력해주세요'
						}
					}
				},
				nickname : {
					message : '올바른 닉네임이 아닙니다',
					validators : {
						notEmpty : {
							message : '닉네임을 입력해 주세요'
						},
						stringLength : {
							min : 2,
							max : 25,
							message : '닉네임은 2글자에서 25글자 사이로 입력가능합니다'
						},
						regexp : {
							regexp : /^[가-힣a-zA-Z0-9]+$/,
							message : '닉네임은 한글이나 알파벳, 숫자로 입력해주세요'
						},
						callback: {
							message: '이미 존재하는 닉네임입니다',
	                        callback: function(value, validator, $field) {
	                        	$.ajax({
	                        		type : "POST",
	                        		url : "/signup/dupchk",
	                        		data : {"field":"nickname", "data":$('#nickname').val()},
	                        		success : function(resp) {
										if(resp.result==="1") {
											validator.updateStatus('nickname', validator.STATUS_INVALID, 'callback');
											return false;
										}
	                        		}
	                        	});
	                        	return true;
	                        }
	                    }
					}
				},
				email : {
					validators : {
						notEmpty : {
							message : '이메일을 입력해주세요'
						},
						emailAddress : {
							message : '올바른 주소 형식이 아닙니다'
						},
						callback: {
								message: '이미 존재하는 회원 email입니다',
		                        callback: function(value, validator, $field) {
		                        	$.ajax({
		                        		type : "POST",
		                        		url : "/signup/dupchk",
		                        		data : {"field":"email", "data":$('#email').val()},
		                        		success : function(resp) {
											if(resp.result==="1") {
												validator.updateStatus('email', validator.STATUS_INVALID, 'callback');
												return false;
											}
		                        		}
		                        	});
		                        	return true;
		                        }
		                    }
		                }
				},
				pwd : {
					validators : {
						notEmpty : {
							message : '비밀번호를 입력해주세요'
						},
						stringLength : {
							min : 6,
							max : 100,
							message : '비밀번호는 6글자 이상 입력해주세요'
						},
						identical : {
							field : 'confirmPassword',
							message : '비밀번호가 일치하지 않습니다'
						}
					}
				},
				confirmPassword : {
					validators : {
						notEmpty : {
							message : '비밀번호를 입력해주세요'
						},
						stringLength : {
							min : 6,
							max : 100,
							message : '비밀번호는 6글자 이상 입력해주세요'
						},
						identical : {
							field : 'pwd',
							message : '비밀번호가 일치하지 않습니다'
						}
					}
				}
			}
		});
	});
</script>
