<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row text-center">
	<h1>회원 목록</h1>
</div>

<!-- 상단툴바 -->
<div class="row">
	<div class="fixed-table-toolbar">
		<div class="bs-bars pull-left">
			<div id="toolbar">
			
				<!-- 추가입력 modal 시작 -->
				<button data-toggle="modal" data-target="#ingr-add" class="btn btn-primary">
				<i class="glyphicon glyphicon-pencil"></i> 추가</button>
				<div class="modal fade" id="ingr-add" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
				  <div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×
								</span><span class="sr-only">Close</span></button>
							<h3 class="modal-title" id="lineModalLabel">회원 추가</h3>
						</div>
						
						<div class="modal-body">
							<!-- 데이터처리 -->
							
							<form id="signup" method="post" action="/signup/apply" class="form-horizontal">
								<input type="hidden" name="admin" value="y">
								<fieldset>
									<div class="form-group">
										<label class="col-lg-4 control-label">이름</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="name" />
										</div>
									</div>
				
									<div class="form-group">
										<label class="col-lg-4 control-label">닉네임</label>
										<div class="col-lg-6">
											<input type="text" id="nickname" class="form-control" name="nickname" />
										</div>
									</div>
				
									<div class="form-group">
										<label class="col-lg-4 control-label">Email주소</label>
										<div class="col-lg-6">
											<input type="text" id="email" class="form-control" name="email" />
										</div>
									</div>
				
									<div class="form-group">
										<label class="col-lg-4 control-label">비밀번호</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" name="pwd" />
										</div>
									</div>
				
									<div class="form-group">
										<label class="col-lg-4 control-label">비밀번호 확인</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" name="confirmPassword" />
										</div>
									</div>
								</fieldset>
				
								<div class="form-group">
									<div class="col-lg-5 col-lg-offset-5">
										<button type="submit" class="btn btn-primary">가입하기</button>
									</div>
								</div>
							</form>
						</div>
						
						
						<div class="modal-footer">
							<div class="btn-group" role="group">
								<button type="button" class="btn btn-default" data-dismiss="modal" role="button">닫기</button>
							</div>
						</div>
					</div>
				  </div>
				</div>
				<!-- modal 종료 -->
			
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
<form id="content-form" method="post" action="/admin/users/delete">
<div class="bootstrap-table">
	<table class="table table-hover">
		<tr class="active">
			<th><input type="checkbox" id="chk-head"></th>
			<th>번호</th>
			<th>EMAIL</th>
			<th>이름</th>
			<th>닉네임</th>
			<th>가입일</th>
			<th>변경일</th>
		</tr>
		<c:forEach var="vo" items="${list}">
		<tr>
			<td><input type="checkbox" class="chk-list" name="chk" value="${vo.id}"></td>
			<td>${vo.id}</td>
			<td>${vo.email}</td>
			<td>${vo.name}</td>
			<td>${vo.nickname}</td>
			<td><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd" /></td>
			<td><fmt:formatDate value="${vo.moddate}" pattern="yyyy-MM-dd" /></td>
		</tr>
		</c:forEach>
	</table>
</div>
</form>

<!-- Pagination -->
<div class="fixed-table-pagination" style="display: block;">
	<div class="pull-left pagination-detail">
		<span class="pagination-info">
		<b>${pmgr.start}번</b> 부터 <b>${pmgr.end}번</b> / 전체 회원 <b>${pmgr.total}명</b>
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

<script src='/js/bootstrapvalidator.js'></script>
<script>
	$(document).ready(function() {
		
		$('#remove').click(function() {
			if (confirm("선택한 회원을 삭제 하시겠습니까?")) {
				$('#content-form').submit();
			}
		});
		
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
