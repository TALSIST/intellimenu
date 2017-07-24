<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link href="/css/style.css" rel="stylesheet" type="text/css">
<link href="/css/responsive.css" rel="stylesheet" type="text/css">
<link href="/css/animate.css" rel="stylesheet" type="text/css"> -->
<script>
	$(function(){
		$('#foodimg').height($('#foodimg').width()*0.6);
		
	})
</script>
<script>
	/*******************스크랩스크랩스크랩*********************************** */

	var recipe_id=${recipe.id};
	var count=0;

	//화면에서 스크랩으로 이동하는 쿼리
	$(function(){
		$('.favorite').click(function(){

			if(confirm("스크랩을 하시겠습니까?")){
				//응

				$.ajax({
					url:'${path}/favorite/favorite_insert',
					type:'post',
					headers: {
		                "Content-Type" : "application/json"
		            },
					dataType:"text",
					data:JSON.stringify({recipe_id:recipe_id}),

					success:function(count){

						if(count==0){
							alert("스크랩에 성공");

						}else{
							alert("이미 등록된 페이지 입니다");

							if(confirm("스크랩된 페이지로 이동하시겠습니까?")){
								location.href="/favorite/favorite_test";
							}

						}

					},
					error:function(jqXHR, textStatus, errorThrown){
			            alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
			            //self.close();
					}
				});
			}else{
			}
		});

	})
</script>
<div class="container">
	<hr>
	<h2>${recipe.title }</h2>
	<h4 align="right">
		by <a href="/recipe/recipe_user_list?nickname=${recipe.nickname}">${recipe.nickname}</a>
	</h4>
	
	<h5 align="center">${recipe.summary }</h5>
	<hr />
	<div class="row">
		<div class="col-sm-4 wow fadeInLeft delay-05s">
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa fa-file-text-o"></i>
				</div>
				<div class="service-list-col2">
					<h3>카테고리</h3>
					<p>
						<a href="/recipe/recipe_sublist?cat_sub_id=${recipe.cat_sub_id}&name=${recipe.subCategoryName}">
						${recipe.subCategoryName}
						</a>
					</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa fa-users"></i>
				</div>
				<div class="service-list-col2">
					<h3>인원</h3>
					<p>${recipe.reqmember }</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa fa-hand-lizard-o"></i>
				</div>
				<div class="service-list-col2">
					<h3>난이도</h3>
					<p>${recipe.lvl }</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa fa-clock-o"></i>
				</div>
				<div class="service-list-col2">
					<h3>시간</h3>
					<p>${recipe.time }</p>
				</div>
			</div>
			<div class="service-list">
				<div class="service-list-col1">
					<i class="fa fa-plus-square-o"></i>
				</div>
				<div class="service-list-col2">
					<h3>조회수</h3>
					<p>${recipe.hit }</p>
				</div>
			</div>
				<div class="service-list">
				<div class="service-list-col1">
					<p><span class="glyphicon glyphicon-paperclip" style="width:50px"></span></p>
				</div>
				<div class="service-list-col2">
					<h3 class="favorite">스크랩</h3>
					<p >해당 레시피를 스크랩해 주세요</p>
				</div>
			</div>
		</div>
		<figure class="col-sm-8  text-right wow fadeInUp delay-02s">
			<img src="${recipe.img}" id="foodimg" width="100%" alt="">
		</figure>

	</div>

	<hr />

	<div class="portfolioFilter">
		<center>
			<h3>재료</h3><br />
			<table width="200px">				
			<c:forEach var="ingredient" items="${recipe.ingredientList}">
				<tr style="border-bottom: 1px solid lightgrey">
					<th align="left">
						<a href="/recipe/recipe_ingr_list?ingrName=${ingredient.name }">${ingredient.name}</a>
					</th>
					<td align="right">${ingredient.quantity }</td>
				</tr>
			</c:forEach>
			</table>
		</center>
	</div>

	<hr />

	<c:forEach var="vo" items="${recipe.contentList }">
		<div class="container">
			<div class="row">
				<figure class="col-lg-3 col-sm-2 wow fadeInLeft">
					<img src="${vo.img }" alt="">
				</figure>
				<div class="col-lg-9 col-sm-10 featured-work">
					<P class="padding-b">${vo.step+1 }.${vo.content }</P>
				</div>
			</div>
		</div>

		<hr />
	</c:forEach>


	<div class="recipeTag">
		<div class="row">
			<div class="col-lg-12 featured-work">
				<li ><a href="#"style="color:#fff; background:#7cc576">#TAG</a></li>
				<c:forEach var="tag" items="${recipe.tagList}">
					<li><a href="/recipe/recipe_tag_list?tagName=${tag.name }">${tag.name }</a></li>
				</c:forEach>
			</div>
		</div>
		<hr />
	</div>


</div>
