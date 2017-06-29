<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, maximum-scale=1">

<title>Homepage</title>
<link rel="icon" href="favicon.png" type="image/png">
<link rel="shortcut icon" href="favicon.ico" type="img/x-icon">

<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,800italic,700italic,600italic,400italic,300italic,800,700,600'
	rel='stylesheet' type='text/css'>

<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">
<link href="css/animate.css" rel="stylesheet" type="text/css">

<!--[if IE]><style type="text/css">.pie {behavior:url(PIE.htc);}</style><![endif]-->

<script type="text/javascript" src="js/jquery.1.8.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery-scrolltofixed.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/jquery.isotope.js"></script>
<script type="text/javascript" src="js/wow.js"></script>
<script type="text/javascript" src="js/classie.js"></script>
<script src="contactform/contactform.js"></script>

<!-- =======================================================
    Theme Name: Knight
    Theme URL: https://bootstrapmade.com/knight-free-bootstrap-theme/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
======================================================= -->
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eF8Ihby9gJ895hs80gs_"></script>

<!--
참고 사이트 목록 :3 ...아아아악ㄱ ~!!

 http://blog.naver.com/weekamp/220861272054 -->
</head>
<body>

	<center>
		<img class="center-croping" src="${vo.img_ori}" width=450
			alt="${vo.name } - ${vo.sigun } ${vo.address2}"
			onerror="this.src='${vo.img_ori }'" />
	</center>

	<div class="container">
		<section class="main-section contact" id="contact">
			<h2>${vo.name }</h2>
			<h6>${vo.score }</h6>
			<div class="row">
				<div class="col-lg-6 col-sm-7 wow fadeInLeft">
					<div class="contact-info-box address clearfix">
						<h3>
							<i class=" icon-map-marker"></i>주소:
						</h3>
						<span> ${vo.sigun } ${vo.address2 }</span>
					</div>
					<div class="contact-info-box phone clearfix">
						<c:if test="${vo.tel!=null }">
							<h3>
								<i class="fa-phone"></i>전화번호:
							</h3>
							<span>${vo.tel }</span>
						</c:if>
					</div>
					<div class="contact-info-box email clearfix">
						<c:if test="${vo.category!=null }">
							<h3>
								<i class="fa-pencil"></i>음식종류:
							</h3>
							<span>${vo.category }</span>
						</c:if>
					</div>
					<div class="contact-info-box email clearfix">
						<c:if test="${vo.parking!=null }">
							<h3>
								<i class="fa-pencil"></i>주차:
							</h3>
							<span>${vo.parking }</span>
						</c:if>
					</div>
					<div class="contact-info-box hours clearfix">
						<c:if test="${vo.busihour!=null }">
							<h3>
								<i class="fa-clock-o"></i>영업시간:
							</h3>
							<span>${vo.busihour }</span>
						</c:if>
					</div>
					<div class="contact-info-box hours clearfix">
						<c:if test="${vo.holiday!=null }">
							<h3>
								<i class="fa-clock-o"></i>휴일:
							</h3>
							<span>${vo.holiday }</span>
						</c:if>
					</div>
					<!-- 현재는 역지로 링크를 넣어준것이라 지도API와 연동필요-->
					<div id="map" style="width: 400px; height: 300px;"></div>

					<script type="text/javascript">
						var mapOptions = {

							center : new naver.maps.LatLng(37.3595704,
									127.105399),

							zoom : 10

						}

						var map = new naver.maps.Map('map', mapOptions);
					</script>
				</div>
			</div>
			<!-- row div-->
			<div class="container">
				<h2>${vo.name }의리뷰</h2>
				<h6>맛있다 | 별로다 | 추천</h6>
				<div class="team-leader-block clearfix">
					<div class="team-leader-box">
						<div class="team-leader wow fadeInDown delay-03s">
							<div class="team-leader-shadow">
								<a href="#"></a>
							</div>
							<img src="img/team-leader-pic1.jpg" alt="">
							<ul>
								<li><a href="#" class="fa-twitter"></a></li>
								<li><a href="#" class="fa-facebook"></a></li>
								<li><a href="#" class="fa-pinterest"></a></li>
								<li><a href="#" class="fa-google-plus"></a></li>
							</ul>
						</div>
						<h3 class="wow fadeInDown delay-03s">완전 맛있어요</h3>
						<span class="wow fadeInDown delay-03s">김아무개</span>
						<p class="wow fadeInDown delay-03s">와.. 평점이 괜히 높은게 아니네요! 서촌김씨
							전부터 가고싶었던 곳이였는데 망플 예약을 이용해서 갔다왔어요! 일요일 저녁 조용한 분위기에서 코스요리 즐길 수 있어
							너무 좋았네요~ 저녁 코스는 한우등심 카르파치오, 랍스터 라비올리, 트러플 퓨레와 뇨끼가 들어간 감자스프, 안심
							스테이크, 티라미수, 차까지 다채로웠어요. 예약 확정되니 카포나타 생선찜과 한우등심 중 메인 메뉴를 고르라고 전화랑
							문자를 주시더라구요.</p>
					</div>
					<div class="team-leader-box">
						<div class="team-leader  wow fadeInDown delay-06s">
							<div class="team-leader-shadow">
								<a href="#"></a>
							</div>
							<img src="img/team-leader-pic2.jpg" alt="">
							<ul>
								<li><a href="#" class="fa-twitter"></a></li>
								<li><a href="#" class="fa-facebook"></a></li>
								<li><a href="#" class="fa-pinterest"></a></li>
								<li><a href="#" class="fa-google-plus"></a></li>
							</ul>
						</div>
						<h3 class="wow fadeInDown delay-03s">핵꿀맛</h3>
						<span class="wow fadeInDown delay-03s">김아무개</span>
						<p class="wow fadeInDown delay-03s">와.. 평점이 괜히 높은게 아니네요! 서촌김씨
							전부터 가고싶었던 곳이였는데 망플 예약을 이용해서 갔다왔어요! 일요일 저녁 조용한 분위기에서 코스요리 즐길 수 있어
							너무 좋았네요~ 저녁 코스는 한우등심 카르파치오, 랍스터 라비올리, 트러플 퓨레와 뇨끼가 들어간 감자스프, 안심
							스테이크, 티라미수, 차까지 다채로웠어요. 예약 확정되니 카포나타 생선찜과 한우등심 중 메인 메뉴를 고르라고 전화랑
							문자를 주시더라구요.</p>
					</div>
					<div class="team-leader-box">
						<div class="team-leader wow fadeInDown delay-09s">
							<div class="team-leader-shadow">
								<a href="#"></a>
							</div>
							<img src="img/team-leader-pic3.jpg" alt="">
							<ul>
								<li><a href="#" class="fa-twitter"></a></li>
								<li><a href="#" class="fa-facebook"></a></li>
								<li><a href="#" class="fa-pinterest"></a></li>
								<li><a href="#" class="fa-google-plus"></a></li>
							</ul>
						</div>
						<h3 class="wow fadeInDown delay-03s">믿을수 없어요</h3>
						<span class="wow fadeInDown delay-03s">김아무개</span>
						<p class="wow fadeInDown delay-03s">와.. 평점이 괜히 높은게 아니네요! 서촌김씨
							전부터 가고싶었던 곳이였는데 망플 예약을 이용해서 갔다왔어요! 일요일 저녁 조용한 분위기에서 코스요리 즐길 수 있어
							너무 좋았네요~ 저녁 코스는 한우등심 카르파치오, 랍스터 라비올리, 트러플 퓨레와 뇨끼가 들어간 감자스프, 안심
							스테이크, 티라미수, 차까지 다채로웠어요. 예약 확정되니 카포나타 생선찜과 한우등심 중 메인 메뉴를 고르라고 전화랑
							문자를 주시더라구요.</p>
					</div>
				</div>
			</div>
			<ul class="social-link">
				<li class="twitter"><a href="#"><i class="fa-twitter"></i></a></li>
				<li class="facebook"><a href="#"><i class="fa-facebook"></i></a></li>
				<li class="pinterest"><a href="#"><i class="fa-pinterest"></i></a></li>
				<li class="gplus"><a href="#"><i class="fa-google-plus"></i></a></li>
				<li class="dribbble"><a href="#"><i class="fa-dribbble"></i></a></li>
			</ul>

		</section>
	</div>




	<script type="text/javascript">
		$(document).ready(function(e) {
			$('#test').scrollToFixed();
			$('.res-nav_click').click(function() {
				$('.main-nav').slideToggle();
				return false

			});

		});
	</script>

	<script>
		wow = new WOW({
			animateClass : 'animated',
			offset : 100
		});
		wow.init();
	</script>


	<script type="text/javascript">
		$(window).load(function() {

			$('.main-nav li a, .servicelink').bind('click', function(event) {
				var $anchor = $(this);

				$('html, body').stop().animate({
					scrollTop : $($anchor.attr('href')).offset().top - 102
				}, 1500, 'easeInOutExpo');
				/*
				if you don't want to use the easing effects:
				$('html, body').stop().animate({
					scrollTop: $($anchor.attr('href')).offset().top
				}, 1000);
				 */
				if ($(window).width() < 768) {
					$('.main-nav').hide();
				}
				event.preventDefault();
			});
		})
	</script>

	<script type="text/javascript">
		$(window)
				.load(
						function() {

							var $container = $('.portfolioContainer'), $body = $('body'), colW = 375, columns = null;

							$container.isotope({
								// disable window resizing
								resizable : true,
								masonry : {
									columnWidth : colW
								}
							});

							$(window).smartresize(
									function() {
										// check if columns has changed
										var currentColumns = Math.floor(($body
												.width() - 30)
												/ colW);
										if (currentColumns !== columns) {
											// set new column count
											columns = currentColumns;
											// apply width to container manually, then trigger relayout
											$container.width(columns * colW)
													.isotope('reLayout');
										}

									}).smartresize(); // trigger resize to set container width
							$('.portfolioFilter a').click(
									function() {
										$('.portfolioFilter .current')
												.removeClass('current');
										$(this).addClass('current');

										var selector = $(this).attr(
												'data-filter');
										$container.isotope({

											filter : selector,
										});
										return false;
									});

						});
	</script>

</body>
</html>

