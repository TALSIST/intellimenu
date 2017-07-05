<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <header id="header" class="header">
   <!--header-start-->
   <div class="container">
     <div class="row">
       <!-- logo -->
       <div class="logo col-sm-4">
		<h3>Intelli Menu(가칭)</h3>
       </div>

       <!-- search form -->
       <div class="logo col-sm-6">
	    <div class="input-group">
               <div class="input-group-btn search-panel">
                   <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                   	<span id="search_concept">조건</span> <span class="caret"></span>
                   </button>
                   <ul class="dropdown-menu" role="menu" id="search_selector">
                     <li><a>제목</a></li>
                     <li><a>재료</a></li>
                     <li><a>태그</a></li>
                     <li class="divider"></li>
                     <li><a>전체</a></li>
                   </ul>
               </div>
               <form id="search_form" method="post" action="/search/search_result">
               	<input type="hidden" name="searchParam" value="전체" id="searchParam">         
               	<input type="text" class="form-control" name="searchKeyword" placeholder="검색어를 입력해주세요">
               </form>
               <span class="input-group-btn">
                   <button class="btn btn-default" type="button" id="searchSend"><span class="glyphicon glyphicon-search"></span></button>
               </span>
           </div>
       </div>


 	<!-- 로그인(로그인버튼, 로그인 드랍다운폼) / 회원정보 (기본정보, mypage), 로그아웃 -->
	<c:choose>
	
		<c:when test="${sessionScope.user.email!=null && sessionScope.user.nickname!=null}">
		  <div class="logo col-sm-2">
	         <div class="dropdown keep-open">
	           <a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
	             <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></button></a>
				
	           <div class="dropdown-menu dropdown-menu-right" id="login-form">
	           
	           
		   <div class="container">
		            <div class="well well-sm">
		                <div class="row">
		                    <div class="col-sm-6 col-md-4">
		                        <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" />
		                    </div>
		                    <div class="col-sm-6 col-md-8">
		                        <h4>${sessionScope.user.nickname}</h4>
		                       
		                        <p>
		                            <i class="glyphicon glyphicon-envelope"></i>${sessionScope.user.email}
		                            <br>
		                        </p>
		
		                            <button type="button" id="logout-btn" class="btn btn-primary">로그아웃</button>
		                     
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		
			        </div>
		    </div>
		</div>
		
		</c:when>
		
		<c:when test="${sessionScope.user.email==null || sessionScope.user.nickname==null}">
		
	       <div class="logo col-sm-2">
	         <div class="dropdown keep-open">
	           <a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
	             <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></button></a>
				
	           <div class="dropdown-menu dropdown-menu-right" id="login-form">
	             <div class="container" style="margin-top:30px">
	               <div class="col-xs-12">
	                 <div class="panel panel-default">
	                   <div class="panel-heading">
	                     <h3 class="panel-title"><strong>로그인</strong></h3>
	                     <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">비밀번호 찾기</a></div>
	                   </div>
						
	                   <div class="panel-body">
	                       <div id="login-alert" class="alert alert-danger">
	                         회원정보가 일치하지 않습니다
	                       </div>
	                       <div style="margin-bottom: 12px" class="input-group">
	                         <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	                         <input id="login-email" type="text" class="form-control" value="" placeholder="email">
	                       </div>
	
	                       <div style="margin-bottom: 12px" class="input-group">
	                         <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	                         <input id="login-password" type="password" class="form-control" placeholder="password">
	                       </div>
	
	                       <div class="input-group">
	                         <div class="checkbox" style="margin-top: 0px;">
	                           <label>
	                         <input id="login-remember" type="checkbox" name="remember" value="1"> 로그인 상태 유지
	                       </label>
	                         </div>
	                       </div>
	
	                       <button type="button" id="login-btn" class="btn btn-success">로그인</button>
	
	                       <hr style="margin-top:10px;margin-bottom:10px;">
	
	                       <div class="form-group">
	                         <div style="font-size:85%">
	                           회원이 아니시라면<a href="/signup">여기를 눌러 가입하세요</a>
	                         </div>
	                       </div>
	                       
	                   </div>
	                 </div>
	               </div>
	             </div>
	           </div>
	         </div>
	       </div>
		
		</c:when>
	
	</c:choose>
      
     </div>
   </div>
   
	 <nav class="main-nav-outer" id="nav">
	   <!--main-nav-start-->
	   <div class="container">
	    <ul class="main-nav">
	        <li class="small-logo"><a href="/"><img src="/img/small-logo.png" alt="intellimenu" width="55px"></a></li>
	        <li><a href="/">Home</a></li>
	        <li><a href="/recipe/recipe_main">레시피</a></li>
	        <li><a href="/recipe/recipe_insert">레시피등록</a></li>
	        <li><a href="#">음식점</a></li>
	        <li><a href="#">요리교실</a></li>
	        <li><a href="#">순위</a></li>
	        <li><a href="/admin/main">관리자</a></li>
	    </ul>
	     <a class="res-nav_click" href="#"><i class="fa-bars"></i></a>
	   </div>
	 </nav>
	 <!--main-nav-end-->
 
 </header>
 <!--header-end-->
