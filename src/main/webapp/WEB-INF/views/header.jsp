<%@ page contentType="text/html; charset=UTF-8" %>
<header id="header" style="height: 50px;"><!--header-start-->
	<div class="container" >
    <div class="row" style="height:15px;"></div>
	<div class="row">
		<div class="col-md-12">
            <div class="input-group" id="adv-search">
                <input type="text" class="form-control" placeholder="검색어를 입력하세요" />
                <div class="input-group-btn">
                    <div class="btn-group" role="group">
                        <div class="dropdown dropdown-lg" style="z-index: 1000">
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="caret"></span></button>
                            <div class="dropdown-menu dropdown-menu-right" role="menu">
                                <form class="form-horizontal" role="form" >
                                  <div class="form-group">
                                    <label for="filter">검색조건</label>
                                    <select class="form-control">
                                        <option value="0" selected>모두</option>
                                        <option value="1">레시피</option>
                                        <option value="2">음식점</option>
                                    </select>
                                  </div>
                                  <div class="form-group">
                                    <label for="contain">검색어</label>
                                    <input class="form-control" type="text" />
                                  </div>
                                  <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                </form>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </div>
                </div>
            </div>
          </div>
        </div>
	</div>
</header><!--header-end-->

<nav class="main-nav-outer" id="test"><!--main-nav-start-->
	<div class="container">
        <ul class="main-nav">
            <li class="small-logo"><a href="#header"><img src="/img/small-logo.png" alt=""></a></li>
        	<li><a href="/">Home</a></li>
            <li><a href="/recipe/recipe_list">레시피</a></li>
            <li><a href="/recipe/recipe_insert">레시피등록</a></li>
            <li><a href="#client">Clients</a></li>
            <li><a href="#team">Team</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
        <a class="res-nav_click" href="#"><i class="fa-bars"></i></a>
    </div>
</nav><!--main-nav-end-->