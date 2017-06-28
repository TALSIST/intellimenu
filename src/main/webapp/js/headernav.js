$(function() {
	
	// 상단부 고정
	$('#nav').scrollToFixed();
	$('.res-nav_click').click(function() {
		$('.main-nav').slideToggle();
		return false
	});
	$('#header').css('z-index:1001;');
	
	// Easing effect
	$('.main-nav li a, .servicelink').bind('click', function(event) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop : $($anchor.attr('href')).offset().top - 102
		}, 1500, 'easeInOutExpo');
		if ($(window).width() < 768) {
			$('.main-nav').hide();
		}
		event.preventDefault();
	});

	// Search-Form
	$('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var param = $(this).attr('href').replace('#', '');
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('.input-group #search_param').val(param);
	});

	// Login
	var login = function(e) {
		e.stopPropagation(e);
		// 유효성 검사

		$.ajax({
			type : 'POST',
			url : '/login',
			data : {
				'email' : $('#login-email').val(),
				'pwd' : $('#login-password').val()
			},
			success : function(resp) {
				$('#login-alert').slideDown(500).delay(2000).slideUp(500);
			}
		});
	};
	$('#login-alert').hide();
	$('#login-btn').click(function(e) {
		login(e);
	});
	$('#login-email, #login-password').keypress(function(e) {
		if (e.keyCode == '13') {
			login(e)
		}
	});

});