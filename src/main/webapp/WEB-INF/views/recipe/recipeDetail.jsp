<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	
</script>
<body >
	<div class="container" style="background-color: white">
		<br>
		<form class="form-horizontal">
			<div class="panel panel-default" style="background-color: white">

				<div class="panel-heading">
					<h4>레시피제목</h4>
				</div>
				<div class="panel-body">
					<div class="form-group " style="background-color: white">
						<label class="col-sm-2 control-label">레시피제목</label>
						<div class="col-sm-6">
							<input name="recipe_title" class="form-control" type="text"
								style="background-color: lightgray" placeholder="레시피를 입력해주세요">
							<div id="" class="col-sm-4"
								style="position: absolute; left: 560px; top: 0px">



								<a id="" href="javascript:fnUpload();"> <img id="recipe_img"
									src="http://recipe.ezmember.co.kr/img/pic_none4.gif"
									class="img-thumbnail" width="200px" height="100px" /></a> <input
									type="file" id="fileUpload" style="display: none"
									onchange="imgChange(this)" />

							</div>
							<!-- 

						<img name="recipe_img" src="http://recipe.ezmember.co.kr/img/pic_none4.gif"  class="img-thumbnail" width="200px" height="100px"></div>
			      	-->
						</div>


					</div>
					<div class="form-group" style="background-color: white">
						<label for="inputPassword" class="col-sm-2 control-label">요리소개</label>
						
					</div>
					
				</div>
			</div>
			
			
			<div class="panel panel-default">


				<div class="panel-body">

					<div class="form-group" style="background-color: white">
						<label class="col-sm-2 control-label">재료</label>

						

					</div>
					<div class="col-sm-5"></div>

					<div id="sorts" class="col-sm-4">
						<!-- 재료 -->
					</div>



				</div>
			</div>


			<div class="panel panel-default">
				<div class="col-sm-12"
					style="background-color: white; margin-top: 10px">

					<h4 style="">요리순서</h4>


				</div>
				<div class="col-sm-12">
					<p style="color: gray">요리의 맛이 좌우될 수 있는 중요한 부분은 빠짐없이 적어주세요.</p>


					<div class="col-sm-12" style="margin-left: 10px">
						<p style="color: gray">예) 10분간 익혀주세요 ▷ 10분간 약한불로 익혀주세요.</p>
					</div>
				</div>



				<div id class="panel-body">

					<div id=steps></div>

					<div align=center>
						<button id="addStepBtn" type="button"
							class="btn btn-default btn-lg">추가</button>
						<button id="removeStepBtn" type="button"
							class="btn btn-default btn-lg">제거</button>
					</div>
					<br>


					<div class="form-group " style="background-color: white">
						<label class="col-sm-2 control-label">요리완성사진</label>
						<div class="col-sm-2">
							<img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"
								class="img-thumbnail" width="150px" height="100px">
						</div>
						<div class="col-sm-2">
							<img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"
								class="img-thumbnail" width="150px" height="100px">

						</div>
						<div class="col-sm-2">
							<img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"
								class="img-thumbnail" width="150px" height="100px">
						</div>
						<div class="col-sm-2">
							<img src="http://recipe.ezmember.co.kr/img/pic_none3.gif"
								class="img-thumbnail" width="150px" height="100px">
						</div>
					</div>


				</div>
			</div>


			<div class="panel panel-default">


				<div class="panel-body">
					<!--
						<div class="form-group" style="background-color:white">
				      		<label for="inputPassword" class="col-sm-2 control-label">요리팁</label>
				      		<div class="col-sm-10" >
				        	<textarea class="form-control" rows="5"  placeholder="요리를 소개해주세요" style="background-color: lightgray"></textarea>
				      		</div>
				    	</div>
				 </div>
			</div>
-->

					<div class="panel panel-default">



						<div class="panel-body">
							<div class="form-group " style="background-color: white">
								<label class="col-sm-2 control-label">태그</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" name="tag"
										style="background-color: lightgray" placeholder="태그를 입력해주세요">
								</div>
							</div>
							<div class="col-sm-2"></div>
							<div class="cols-sm-10">
								<h5>
									주재료, 목적, 효능, 대상 등을 태그로 남겨주세요. <small>예) 돼지고기, 다이어트, 비만,
										칼슘, 감기예방, 이유식, 초간단</small>
								</h5>
							</div>

						</div>
					</div>



					<div class="panel">
						<center>
							<button type="button" class="btn btn-default btn-lg">저장</button>
							<button type="submit" class="btn btn-default btn-lg">등록완료</button>
							<button type="reset" class="btn btn-default btn-lg">취소</button>
						</center>
					</div>
		</form>
		<!-- 레시피등록 완료 -->
	</div>



	<!-- https://silviomoreto.github.io/bootstrap-select/examples/ -->
</body>
</html>