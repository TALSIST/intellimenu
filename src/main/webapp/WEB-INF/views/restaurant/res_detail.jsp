<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>

</head>
<body>


<img class="center-croping"
   src="${vo.img_ori }"
   alt="${vo.name } 사진 - 서울시 종로구 ${vo.address2}"
   onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'" />


<div class="container">
<section class="main-section contact" id="contact">
		<h2>${vo.name }<span><h6>${vo.score }</h6></span></h2> 
		<h6>파르테 김도형 셰프가 오픈한 Italian Dining & Bistro 점심에는 tasting menu 한가지로 예약제로 운영되며, 저녁에는 다양한 단품과 주류를 판매. **저녁에는 콜키지 불가 </h6>
        <div class="row">
        	<div class="col-lg-6 col-sm-7 wow fadeInLeft">
            	<div class="contact-info-box address clearfix">
                	<h3><i class=" icon-map-marker"></i>주소:</h3>
                	<span>서울시 종로구  ${vo.address2}</span>
                </div>
                <div class="contact-info-box phone clearfix">
                	<h3><i class="fa-phone"></i>전화번호:</h3>
                	<span>${vo.tel }</span>
                </div>
                <div class="contact-info-box email clearfix">
                	<h3><i class="fa-pencil"></i>음식종류:</h3>
                	<span>${vo.category }</span>
                </div>
				<div class="contact-info-box email clearfix">
                	<h3><i class="fa-pencil"></i>주차:</h3>
                	<span>${vo.parking }</span>
                </div>
            	<div class="contact-info-box hours clearfix">
                	<h3><i class="fa-clock-o"></i>영업시간:</h3>
                	<span>${vo.busihour }</span>
                </div>
            	<div class="contact-info-box hours clearfix">
                	<h3><i class="fa-clock-o"></i>쉬는시간:</h3>
                	<span></span>
                </div>
		<div class="contact-info-box hours clearfix">
                	<h3><i class="fa-clock-o"></i>휴일:</h3>
                	<span>${vo.holiday }</span>
                </div>
		<div class="contact-info-box hours clearfix">
                	<h3><i class="fa-clock-o"></i>메뉴:</h3>
                	<span>스파게티</span>
                </div>
                  <tr class="menu_flex">
                    <td class="flex_detail">
                      <div class="list-thumb-photos size-small">
                            <button class="btn-thumb" onclick="common_ga('PG_RESTAURANT', 'CLICK_MENU')" ng-click="open_menu_picture(0)">
                              <img class="center-croping"
                                   src="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/9388_1484709367248107.jpg?fit=around|63:63&amp;crop=63:63;*,*&amp;output-format=jpg&amp;output-quality=80"
                                   alt="서촌김씨 메뉴 사진 - 서울시 종로구 창성동 158-2"
                                   onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'" />

                            </button>
                            <button class="btn-thumb" onclick="common_ga('PG_RESTAURANT', 'CLICK_MENU')" ng-click="open_menu_picture(1)">
                              <img class="center-croping"
                                   src="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/9388_1484709367455060.jpg?fit=around|63:63&amp;crop=63:63;*,*&amp;output-format=jpg&amp;output-quality=80"
                                   alt="서촌김씨 메뉴 사진 - 서울시 종로구 창성동 158-2"
                                   onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'" />

                            </button>
                            <button class="btn-thumb" onclick="common_ga('PG_RESTAURANT', 'CLICK_MENU')" ng-click="open_menu_picture(2)">
                              <img class="center-croping"
                                   src="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/9388_1484709367680089.jpg?fit=around|63:63&amp;crop=63:63;*,*&amp;output-format=jpg&amp;output-quality=80"
                                   alt="서촌김씨 메뉴 사진 - 서울시 종로구 창성동 158-2"
                                   onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'" />

                            </button>
                            <button class="btn-thumb" onclick="common_ga('PG_RESTAURANT', 'CLICK_MENU')" ng-click="open_menu_picture(3)">
                              <img class="center-croping"
                                   src="https://mp-seoul-image-production-s3.mangoplate.com/added_restaurants/9388_1484709367890643.jpg?fit=around|63:63&amp;crop=63:63;*,*&amp;output-format=jpg&amp;output-quality=80"
                                   alt="서촌김씨 메뉴 사진 - 서울시 종로구 창성동 158-2"
                                   onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'" />
                            </button>
                      </div>
                    </td>
                  </tr>
				  </div>
				<!-- 현재는 역지로 링크를 넣어준것이라 지도API와 연동필요-->
				<div class="col-lg-6 col-sm-5 wow fadeInUp delay-05s">
				<table cellpadding="0" cellspacing="0" width="462"> <tr> <td style="border:1px solid #cecece;"><a href="http://map.naver.com/?__pinOnly=false&query=&searchCoord=&menu=location&tab=1&lng=62192909c2b2cf87bf6a56c1b25a7de5&__fromRestorer=true&mapMode=0&pinTitle=7ISc7LSM6rmA7JSoIOumrOyKpO2GoOuegO2FjA%3D%3D&mpx=37.5814765%2C126.9714176%3AZ10%3A0.0437620%2C0.0191649&pinId=37642837&pinType=site&lat=78d5a347b3a1502ec4d010cc1a4341ee&dlevel=10&enc=b64" target="_blank"><img src="http://prt.map.naver.com/mashupmap/print?key=p1497999135277_-1673465461" width="460" height="340" alt="지도 크게 보기" title="지도 크게 보기" border="0" style="vertical-align:top;"/></a></td> </tr> <tr> <td> <table cellpadding="0" cellspacing="0" width="100%"> <tr> <td height="30" bgcolor="#f9f9f9" align="left" style="padding-left:9px; border-left:1px solid #cecece; border-bottom:1px solid #cecece;"> <span style="font-family: tahoma; font-size: 11px; color:#666;">2017.6.20</span>&nbsp;<span style="font-size: 11px; color:#e5e5e5;">|</span>&nbsp;<a style="font-family: dotum,sans-serif; font-size: 11px; color:#666; text-decoration: none; letter-spacing: -1px;" href="http://map.naver.com/?__pinOnly=false&query=&searchCoord=&menu=location&tab=1&lng=62192909c2b2cf87bf6a56c1b25a7de5&__fromRestorer=true&mapMode=0&pinTitle=7ISc7LSM6rmA7JSoIOumrOyKpO2GoOuegO2FjA%3D%3D&mpx=37.5814765%2C126.9714176%3AZ10%3A0.0437620%2C0.0191649&pinId=37642837&pinType=site&lat=78d5a347b3a1502ec4d010cc1a4341ee&dlevel=10&enc=b64" target="_blank">지도 크게 보기</a> </td> <td width="98" bgcolor="#f9f9f9" align="right" style="text-align:right; padding-right:9px; border-right:1px solid #cecece; border-bottom:1px solid #cecece;"> <span style="float:right;"><span style="font-size:9px; font-family:Verdana, sans-serif; color:#444;">&copy;&nbsp;</span>&nbsp;<a style="font-family:tahoma; font-size:9px; font-weight:bold; color:#2db400; text-decoration:none;" href="http://www.nhncorp.com" target="_blank">NAVER Corp.</a></span> </td> </tr> </table> </td> </tr> </table>
				</div>	
            </div>
        </div> <!-- row div-->
        
<%--   	<div id="reply_view">
        <form method="post" action="reply_new_insert.do">
           <div style="float: left;height:45px">
            <input type="hidden" name="bno" value="${vo.no }">
            <input type="hidden" name="page" value="${page }">
            <textarea rows="3" cols="60" name=msg></textarea>
           </div>
           <div style="float: left">
            <input type="submit" value="댓글쓰기" style="height:45px">
           </div>
          </form>
     </div>   --%>
     
    <div id="reply_view">       
      <table id="table_content" width=600>
       <c:forEach var="rvo" items="${rList }">
        <tr>
         <td align=left width=70%>
          <c:if test="${rvo.getGroupTab>0 }">
           <c:forEach var="i" begin="1" end="${rvo.getGroupTab}">
             &nbsp;&nbsp;
           </c:forEach>
           <img src="images/icon_reply.gif">
          </c:if>
     <%--     ${rvo.userId }&nbsp;(${rvo.strDay })<br> --%>
         <c:if test="${rvo.group_tab>0 }">
           <c:forEach var="i" begin="1" end="${rvo.getGroupTab}">
             &nbsp;&nbsp;
           </c:forEach>
          </c:if>
         ${rvo.reply }
         </td>
         <td align=right width=30%>
         └<a href="#" class="modify" value="${rvo.id }">수정</a>&nbsp;
         └<a href="reply_delete.do?no=${rvo.id }&bno=${vo.id}&page=${page}">삭제</a>&nbsp;
         └<a href="#" class="insert" value="${rvo.id }">댓글</a>
         </td>
        </tr>
       </c:forEach>

        <tr>
         <td colspan="2">
          <form method="post" action="reply_new_insert.do">
           <div style="float: left;height:45px">
            <input type="hidden" name="bno" value="${vo.id }">
            <input type="hidden" name="page" value="${page }">
            <textarea rows="3" cols="60" name=msg></textarea>
           </div>
           <div style="float: left">
            <input type="submit" value="댓글쓰기" style="height:45px">
           </div>
          </form>
         </td>
        </tr>

       </table> 
 	</div> 

</section>




<script type="text/javascript">
    $(document).ready(function(e) {
        $('#test').scrollToFixed();
        $('.res-nav_click').click(function(){
            $('.main-nav').slideToggle();
            return false    
            
        });
        
    });
</script>

  <script>
    wow = new WOW(
      {
        animateClass: 'animated',
        offset:       100
      }
    );
    wow.init();
  </script>


<script type="text/javascript">
	$(window).load(function(){
		
		$('.main-nav li a, .servicelink').bind('click',function(event){
			var $anchor = $(this);
			
			$('html, body').stop().animate({
				scrollTop: $($anchor.attr('href')).offset().top - 102
			}, 1500,'easeInOutExpo');
			/*
			if you don't want to use the easing effects:
			$('html, body').stop().animate({
				scrollTop: $($anchor.attr('href')).offset().top
			}, 1000);
			*/
      if ($(window).width() < 768 ) { 
        $('.main-nav').hide(); 
      }
			event.preventDefault();
		});
	})
</script>

<script type="text/javascript">

$(window).load(function(){
  
  
  var $container = $('.portfolioContainer'),
      $body = $('body'),
      colW = 375,
      columns = null;

  
  $container.isotope({
    // disable window resizing
    resizable: true,
    masonry: {
      columnWidth: colW
    }
  });
  
  $(window).smartresize(function(){
    // check if columns has changed
    var currentColumns = Math.floor( ( $body.width() -30 ) / colW );
    if ( currentColumns !== columns ) {
      // set new column count
      columns = currentColumns;
      // apply width to container manually, then trigger relayout
      $container.width( columns * colW )
        .isotope('reLayout');
    }
    
  }).smartresize(); // trigger resize to set container width
  $('.portfolioFilter a').click(function(){
        $('.portfolioFilter .current').removeClass('current');
        $(this).addClass('current');
 
        var selector = $(this).attr('data-filter');
        $container.isotope({
			
            filter: selector,
         });
         return false;
    });
  
});

</script>
	 
</body>
</html>
