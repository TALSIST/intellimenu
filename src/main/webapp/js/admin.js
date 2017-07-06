paceOptions = {
	  ajax: true,
	  document: true,
	  eventLag: false
	};

$(function() {
	
	var current_page = location.pathname;
	$(".side-menu").each(function() {
	     var target = $(this).attr("href");
	     if (current_page.includes(target)) {
	        $(this).parents('li').removeClass('active');
	        $(this).parent('li').addClass('active');
	     }
	});
	
	var status = false;
	$("#chk-head").click(function() {
		status = !status;
		$(".chk-list").each(function() {
			this.checked = status;
		});
	});
	
});
