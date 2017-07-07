<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<head>

<meta charset="UTF-8">


<!-- Latest compiled and minified CSS -->


<!-- jQuery CSS -->
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>
 -->

<link rel="stylesheet"
	href="//cdn.jsdelivr.net/bootstrap.tagsinput/0.4.2/bootstrap-tagsinput.css" />
<script
	src="//cdn.jsdelivr.net/bootstrap.tagsinput/0.4.2/bootstrap-tagsinput.min.js"></script>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<!--[if IE]><style type="text/css">.pie {behavior:url(PIE.htc);}</style><![endif]-->
<style>
li {
	list-style: none;
}

.bootstrap-tagsinput .tag {
	font-size: 15px;
}
</style>
<script type="text/javascript">
	function fnUpload(fileid) {

		$('#' + fileid).click();

	}
	function imgChange(imgsrc, id) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#' + id).attr('src', e.target.result);
		};
		reader.readAsDataURL(imgsrc.files[0]);

	}
</script>

<script src='/js/bootstrapvalidator.js'></script>
<script>
$(function() {
	$('#findbtn').click(function() {
		var address=$('#address').val();
		if(address.trim()=="")
		{
			alert("구를 입력하세요");
			return;
		}
    	$.ajax({
    		type : 'POST',
    		url : "/admin/restaurant/addrfind",
    		data : {'address':address},
    		success : function(resp) {
				$('#view').html(resp);
    		}
    	});
	});
});
</script>
<title>Document</title>
</head>


<body>
	<div style="background-color: white">
		<br>
		<form id="form1" class="form-horizontal" method="post"
			action="/admin/restaurant/insert_ok" enctype="multipart/form-data">
			<div class="panel panel-default" style="background-color: white">

				<div class="panel-heading">
					<h4>음식점 등록</h4>

				</div>
				<div class="panel-body">
					<div style="float: left; width: 85%;">
						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label class="col-xs-2 control-label" style="min-width: 0px;">음식점
								이름</label>
							<div class="col-xs-6">
								<input name="name" class="form-control" type="text"
									style="min-width: 0px; background-color: lightgray"
									placeholder="음식점 이름을 입력해주세요">
							</div>
						</div>


						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">음식점 주소</label>
							<div class="col-sm-1">
								<input id="addr" class="form-control"
									type="text" style="width: 250px; background-color: lightgray"
									placeholder="주소를 검색해주세요" disabled>
									<input type="hidden" name="address1" value="0">
							</div>
							<div class="col-sm-offset-5">
								<button data-toggle="modal" data-target="#ingr-add"
									class="btn btn-default btn-sm" type="button">검색</button>
							</div>

						</div>

						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;"></label>
							<div class="col-xs-6">
								<input name="address2" class="form-control" type="text"
									style="min-width: 0px; background-color: lightgray"
									placeholder="나머지 주소를 입력해주세요">
							</div>
						</div>

						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">음식점 전화번호</label>
							<div class="col-sm-1">
								<select id="tel1" name="tel1" class="selectpicker "
									data-width="fit">
									<option value=1>02</option>
									<option value=2>051</option>
									<option value=3>053</option>
									<option value=4>032</option>
									<option value=5>062</option>
									<option value=6>042</option>
									<option value=7>052</option>
									<option value=8>044</option>
									<option value=9>031</option>
									<option value=10>033</option>
									<option value=11>043</option>
									<option value=12>041</option>
									<option value=13>063</option>
									<option value=14>061</option>
									<option value=15>054</option>
									<option value=16>055</option>
									<option value=17>064</option>
								</select>
							</div>
							<div class="col-sm-1">
								- <input name="tel2" class="form-control" type="text"
									style="width: 50px; background-color: lightgray; display: inline-block;">
							</div>
							<div class="col-sm-1">
								- <input name="tel3" class="form-control" type="text"
									style="width: 50px; background-color: lightgray; display: inline-block;">
							</div>
						</div>


						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">음식점 가격대</label>
							<div class="col-xs-6">
								<input name="price" class="form-control" type="text"
									style="min-width: 0px; background-color: lightgray"
									placeholder="가격대를 입력해주세요">
							</div>
						</div>


						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">음식점 주차정보</label>
							<div class="col-xs-6">
								<input name="parking" class="form-control" type="text"
									style="min-width: 0px; background-color: lightgray"
									placeholder="주차 정보를 입력해주세요">
							</div>
						</div>


						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">음식점 휴무정보</label>
							<div class="col-xs-6">
								<input name="holiday" class="form-control" type="text"
									style="min-width: 0px; background-color: lightgray"
									placeholder="휴무 정보를 입력해주세요">
							</div>
						</div>


						<div class="form-group"
							style="min-width: 0px; background-color: white">
							<label for="inputPassword" class="col-xs-2 control-label"
								style="min-width: 0px;">음식점 영업시간</label>
							<div class="col-xs-6">
								<input name="busihour" class="form-control" type="text"
									style="min-width: 0px; background-color: lightgray"
									placeholder="영업 시간을 입력해주세요">
							</div>
						</div>
					</div>
					<div id="" style="float: left; width: 15%;">
						<a id="" href="javascript:fnUpload('fileUpload');"> <img
							id="recipe_img"
							src="http://recipe.ezmember.co.kr/img/pic_none4.gif"
							class="img-thumbnail" width="200px" height="100px" /></a> <input
							type="file" id="fileUpload" style="display: none; margin: auto;"
							onchange="imgChange(this,'recipe_img')" accept=".gif, .jpg, .png"
							name="img_ori">

					</div>
				</div>
				<div class="panel">
					<center>
						<button type="submit" class="btn btn-default btn-lg">등록</button>
						<button type="reset" class="btn btn-default btn-lg">취소</button>
					</center>
				</div>
			</div>
		</form>
		<!-- 음식점등록 완료 -->
		<!-- 추가입력 modal 시작 -->
		<div class="modal fade" id="ingr-add" tabindex="-1" role="dialog"
			aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">× </span><span class="sr-only">Close</span>
						</button>
						<h3 class="modal-title" id="lineModalLabel">주소 검색</h3>
					</div>

					<div class="modal-body">
						<!-- 데이터처리 -->

							<input type="hidden" name="admin" value="y">
							<fieldset>
								<div class="form-group"
									style="min-width: 0px; background-color: white">
									<div class="col-sm-1">
										<input name="address" id="address" class="form-control" type="text"
											style="width: 150px; background-color: lightgray"
											placeholder="시/구를 검색해주세요">
									</div>
									<div class="col-sm-offset-5">
										<button id="findbtn" class="btn btn-default btn-sm"
											type="button">검색</button>
									</div>
								</div>

								<div class="form-group"
									style="min-width: 0px; background-color: white" id="view">
								</div>
							</fieldset>
					</div>
					<div class="modal-footer">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" role="button">닫기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- modal 종료 -->
	</div>

</body>
</html>