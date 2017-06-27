$(window).load(function () {

  $('#nav').scrollToFixed();
  $('.res-nav_click').click(function () {
    $('.main-nav').slideToggle();
    return false

  });
  $('#header').css("z-index:1001;");

  $('.main-nav li a, .servicelink').bind('click', function (event) {
    var $anchor = $(this);

    $('html, body').stop().animate({
      scrollTop: $($anchor.attr('href')).offset().top - 102
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

  $('.search-panel .dropdown-menu').find('a').click(function(e) {
		e.preventDefault();
		var param = $(this).attr("href").replace("#","");
		var concept = $(this).text();
		$('.search-panel span#search_concept').text(concept);
		$('.input-group #search_param').val(param);
	});

});