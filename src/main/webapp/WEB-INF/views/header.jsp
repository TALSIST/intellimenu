<%@ page contentType="text/html; charset=UTF-8" %>
 <header id="header" class="header">
   <!--header-start-->
   <div class="container">
     <div class="row">
       <!-- logo -->
       <div class="col-md-4">

       </div>

       <!-- search form -->
       <div class="col-md-6">
	    <div class="input-group">
               <div class="input-group-btn search-panel">
                   <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                   	<span id="search_concept">조건</span> <span class="caret"></span>
                   </button>
                   <ul class="dropdown-menu" role="menu">
                     <li><a href="#its_equal">제목</a></li>
                     <li><a href="#greather_than">재료</a></li>
                     <li><a href="#less_than">태그</a></li>
                     <li class="divider"></li>
                     <li><a href="#all">전체</a></li>
                   </ul>
               </div>
               <input type="hidden" name="search_param" value="all" id="search_param">         
               <input type="text" class="form-control" name="x" placeholder="검색어를 입력해주세요">
               <span class="input-group-btn">
                   <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
               </span>
           </div>
       </div>

       <!-- 로그인/로그아웃/mypage -->
       <div class="col-md-2">
         <div class="dropdown">
           <a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
             <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></button></a>

           <div class="dropdown-menu dropdown-menu-right" id="login-form">
             <div class="container" style="margin-top:30px">
               <div class="col-md-12">
                 <div class="panel panel-default">
                   <div class="panel-heading">
                     <h3 class="panel-title"><strong>로그인</strong></h3>
                     <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">비밀번호 찾기</a></div>
                   </div>

                   <div class="panel-body">
                     <form role="form">
                       <div class="alert alert-danger" style="display: none;">
                         <a class="close" data-dismiss="alert" href="#">×</a>회원정보가 일치하지 않습니다
                       </div>
                       <div style="margin-bottom: 12px" class="input-group">
                         <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                         <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="email">
                       </div>

                       <div style="margin-bottom: 12px" class="input-group">
                         <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                         <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                       </div>

                       <div class="input-group">
                         <div class="checkbox" style="margin-top: 0px;">
                           <label>
                         <input id="login-remember" type="checkbox" name="remember" value="1"> 로그인 상태 유지
                       </label>
                         </div>
                       </div>

                       <button type="submit" class="btn btn-success">로그인</button>

                       <hr style="margin-top:10px;margin-bottom:10px;">

                       <div class="form-group">

                         <div style="font-size:85%">
                           회원이 아니시라면
                           <a href="#" onclick="$('#loginbox').hide(); $('#signupbox').show()">
                         여기를 눌러 가입하세요
                       </a>
                         </div>

                       </div>
                     </form>
                   </div>
                 </div>
               </div>
             </div>
           </div>
         </div>
       </div>

     </div>
   </div>
 </header>
 <!--header-end-->

 <nav class="main-nav-outer" id="nav">
   <!--main-nav-start-->
   <div class="container">
     <ul class="main-nav">
       <li class="small-logo"><a href="#header"><img src="img/small-logo.png" alt="intellimenu" width="55px"></a></li>
       <li><a href="#header">Home</a></li>
       <li><a href="#service">Services</a></li>
       <li><a href="#Portfolio">Portfolio</a></li>
       <li><a href="#client">Clients</a></li>
       <li><a href="#team">Team</a></li>
       <li><a href="#contact">Contact</a></li>
     </ul>
     <a class="res-nav_click" href="#"><i class="fa-bars"></i></a>
   </div>
 </nav>
 <!--main-nav-end-->