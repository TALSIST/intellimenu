<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(function() {
	//이미지 크기 일정하게
	var maxWidth = -1;
	$('.col-sm-4.text-center.sublist').each(function() {
		maxWidth = $(this).width();
		$(this).height(maxWidth*0.6+70)
	});

	$('.img-responsive.sublist').each(function() {
		$(this).width(maxWidth);
		$(this).height(maxWidth*0.6);
	});

});
</script>
<div class="container">
<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>인기검색어<br>
						<c:forEach var="vo" items="${result.logSearchRankList }" varStatus="rank">
							 <a href="/search/search_total_result?searchParam=전체&searchKeyword=${vo.keyword }">
								 ${rank.count }위 ${vo.keyword }
							 </a>&nbsp;&nbsp;
						</c:forEach>
					</strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="vo" items="${result.logSearchRankRecipeList }">
				<div class="col-sm-4 text-center sublist">
					<a href="/recipe/recipe_detail?id=${vo.id}&page=${page}">
						<img class="img-responsive sublist" src="${vo.img}" alt="">
					</a>
					<h3>
						${vo.title } <br>
						<small>
							by 
							<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
							 	${vo.nickname}
							</a>
						</small>
					</h3>
				</div>
			</c:forEach>
			
	
			<div class="clearfix"></div>
		</div>
	</div>
	
	<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>건강을 찾아주는 ${result.nowMonth}월 제철재료가 포함된 레시피
						<br />
						<c:forEach var="vo" items="${result.randomIngrListOnNowMonth }" varStatus="rank">
								 <a href="/recipe/recipe_ingr_list?ingrName=${vo.name }">${vo.name }</a>&nbsp;&nbsp;
						</c:forEach>
					</strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="vo" items="${result.randomRecipeListOnNowMonth }">
				<div class="col-sm-4 text-center sublist">
					<a href="/recipe/recipe_detail?id=${vo.id}&page=${page}">
						<img class="img-responsive sublist" src="${vo.img}" alt="">
					</a>
					<h3>
						${vo.title } <br>
						<small>
							by 
							<a href="/recipe/recipe_user_list?nickname=${vo.nickname}">
							 	${vo.nickname}
							</a>
						</small>
					</h3>
				</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>
	</div>


        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center" >
                        <strong>당신을 위한 음식들</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="img/P_1.JPG" width="750px" alt="">
                    <h3>채소디톡스주스
                    	<br>
                     <small>by VEGE O'CLOKC</small>
                    </h3>
                </div>
                          <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="img/P_2.JPG" width="750px" alt="">
                    <h3>채소디톡스주스
                    	<br>
                     <small>by VEGE O'CLOKC</small>
                    </h3>
                </div>
                   <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="img/P_3.JPG" width="750px" alt="">
                    <h3>채소디톡스주스
                    	<br>
                     <small>by VEGE O'CLOKC</small>
                    </h3>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>

		<div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center" >
                        <strong>인기있는 레시피</strong>
                    </h2>
                    <hr>
                </div>
                <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="img/P_7.JPG" width="750px" alt="">
                    <h3>채소디톡스주스
                    	<br>
                     <small>by VEGE O'CLOKC</small>
                    </h3>
                </div>
                          <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="img/P_8.JPG" width="750px" alt="">
                    <h3>채소디톡스주스
                    	<br>
                     <small>by VEGE O'CLOKC</small>
                    </h3>
                </div>
                          <div class="col-sm-4 text-center">
                    <img class="img-responsive" src="img/P_9.JPG" width="750px" alt="">
                    <h3>채소디톡스주스
                    	<br>
                     <small>by VEGE O'CLOKC</small>
                    </h3>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
</div>
