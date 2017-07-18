<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script> -->
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

	/* //div높이 일정하게   
	var maxHeight = -1; 
	$('.col-sm-4.text-center.sublist').each(function() {
		maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
	}); 초기값이 -1이라 위쿼리 적용하면 -1이 되어버린다.

	$('.col-sm-4.text-center.sublist').each(function() {
		console.log(maxHeight)
		$(this).height(maxHeight);
	}); */
});


</script>
<script>


//버튼 누르면 목록으로 이동
$(function(){
	$('#move').click(function(){
		location.href="/recipe/recipe_main";
		
	});
});
var flag=true;
$(function(){
	
	$('#checkAll').click(function(){
				
		$("input[name=check]").prop("checked", flag);
		flag=!flag;
	});
	
	
});


function deleteCheck(){
	var check="";
 	$("input[name='check']:checked").each (function(){
		check=check+$(this).attr('id')+",";
	}); 
	if(check==''){
		alert("삭제할 대상을 선택해주세요");
		return false;	
	}
	check = check.substring(0,check.lastIndexOf( ","));
	
	var list=new Array();
	list=check.split(",");
	
	for (var i = 0; i < list.length; i++) {
		var ch=list.get[1];
	}
	
	console.log("##### 체크로우배열은"+ch);
	if(confirm("스크랩을 삭제하시겠습니까?")){
		

			$.ajax({
			url:'${path}/favorite/favorite_delete',
			type:'post',
			headers: {
                "Content-Type" : "application/json"
            },
			dataType:"text",
			data:JSON.stringify({check:check}),
			
			success:function(data){
				
				
				  location.reload();
		

			},
			error:function(jqXHR, textStatus, errorThrown){
	            alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
	            //self.close();
			}				
		});
		
	}
	
	
	
}

</script>
<div class="container">
	<div class="row">
		<div class="box">
			<div class="col-lg-12">
				<hr>
				<div class="row">
				<div class="col-lg-8">
				<h1 class="intro-text text-left">
					<strong>스크랩한 레시피 </strong>
				</h1>
				</div>
				<div class="col-lg-2" style="margin-top: 25px">
				<!-- <button type="button" class="btn btn-default">전체선택</button>
				<button type="button" class="btn btn-default">전체선택</button> -->
				</div>
				<div class="col-lg-2" style="margin-top: 25px;letter-spacing:10px">
					<button type="button" class="btn btn-default" id="checkAll" >전체선택</button>
					<button type="button" class="btn btn-default" onclick="deleteCheck()">삭제</button>
				</div>
				</div>
				<hr>
			</div>
			<input type="hidden" value="${map.count }">
		<c:choose>
		<c:when test="${map.count==0}">
			<center>
			
			<img src="../img/scrap.png">
			<h1 >장바구니가 비워져 있습니다</h1>
			<p>마음에 드는 레시피를 레시피 하단 <스크랩>을 통해 야무지게 보관하세요.</p>
			<br>
			<button type="button" class="btn btn-info" id="move" >레시피 목록으로 이동</button>
			<br>
			<br>
			<br>
			<br>
			
			</center>
			
			</c:when>
			 
			
			<c:otherwise>
			
			<c:forEach var="v" items="${map.favoriteList }">
				
				<div class="col-sm-4 text-center sublist" style="text-align:left;" id="test">
				<div class="checkbox checkbox-warning">
                        <input id="${v.favorite_id }" name="check" class="styled" type="checkbox" unchecked>
                        <label for="checkbox" id="check${v.favorite_id }">
                    	  </label>
                 </div>
              
					<a href="/recipe/recipe_detail?id=${v.id}&page=${map.page}">
						<img class="img-responsive sublist" src="${v.img_ori}" alt="">
					</a>
 					
					<h3>
						${v.title } <br> <small>by VEGE O'CLOKC</small>
					</h3>
				</div>
			</c:forEach>
			
			</c:otherwise>
			</c:choose>
			<!-- ******************************* 페이징 ****************************************-->
			
			<div class="col-sm-offset-4 col-lg-offset-4 col-sm-4 col-lg-4">
				<ul class="pager">
					<li class="previous">
					
					<a href="/favorite/favorite_test?user_id=${map.user_id }&page=${map.page>1?map.page-1:map.page}">이전글</a>
	
					</li> ${map.page } / ${map.totalpage } page
					</li>
					<li class="next">
					 <a href="/favorite/favorite_test?user_id=${map.user_id }&page=${map.page<10?map.page+1:map.page}">다음글</a>
					</li>
				</ul>
			</div>
	
			<div class="clearfix"></div>
		</div>
	</div>
</div>