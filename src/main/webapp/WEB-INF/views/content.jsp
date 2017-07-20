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

/* function showGraph(){
	 $.ajax({
		 type:'POST',
		 url:'/main/graph',
		 success:function(response){
			//alert(response);
			 $('#graph').html(response);	 
			 
		 }
	 });
	 
}
$(function(){
	showGraph();
	setInterval("showGraph()", 10000);
	
 });
  */
 
$(function(){
	 (function showGraph(){
		 $.ajax({
			 type:'POST',
			 url:'/main/graph',
			 success:function(response){
				//alert(response);
				 $('#graph').html(response);	 
				 
			 }
		 });
		 setTimeout(showGraph, 10000);
		 
	 })();
	
});

  
	 
	
	 
 
 
</script>
<div class="container">
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>Intelli Menu 인기검색어<br>
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
	
		
	<div class="row">
		<div class="box">
				<div class="col-lg-12">
					<hr>
					<h1 class="intro-text text-center">
						<strong>${result.nowMonth}월 제철재료가 포함된 레시피
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
        <hr>
		<div class="col-lg-12 featured-work">
           	<h1 align="center">'${result.randomIngrListOnNowMonth.get(0).name}' 제철 재료 뉴스 검색결과</h1>
        </div>
        
        <hr>
       	
       	<div class="col-lg-6 featured-work">
           	<c:forEach var="vo" items="${result.naverSearchResultList}" varStatus="status">
            	<c:if test="${status.index<3 }">
            	<div class="featured-box">
                	<div class="featured-box-col1 wow fadeInRight delay-02s">
                    </div>	
                	<div class="featured-box-col2 wow fadeInRight delay-02s">
                        <h3><a href="${vo.link }">${vo.title }</a></h3>
                        <p>${vo.description } </p>
                    </div>    
                </div>
                </c:if>
               </c:forEach>
           </div>
           
            <div class="col-lg-6 featured-work">
            	<c:forEach var="vo" items="${result.naverSearchResultList}" varStatus="status">
	            	<c:if test="${status.index>=3&&status.index<6 }">
	            	<div class="featured-box">
	                	<div class="featured-box-col1 wow fadeInRight delay-02s">
	                    </div>	
	                	<div class="featured-box-col2 wow fadeInRight delay-02s">
	                        <h3><a href="${vo.link }">${vo.title }</a></h3>
	                        <p>${vo.description } </p>
	                    </div>    
	                </div>
	                </c:if>
                </c:forEach>
            </div>
        </div>
	
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<h1 class="intro-text text-center">
					<strong>명예의 전당<br>
						<c:forEach var="vo" items="${result.randomUserRankList }">
							 <a href="/recipe/recipe_user_list?nickname=${vo.nickname }">
								 ${vo.nickname }
							 </a>&nbsp;&nbsp;
						</c:forEach>
					</strong>
				</h1>
				<hr>
			</div>
			<c:forEach var="vo" items="${result.randomUserRankRecipeList }">
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
	

</div>
