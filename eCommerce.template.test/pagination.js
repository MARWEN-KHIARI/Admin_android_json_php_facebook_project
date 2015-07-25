$(document).ready(function() {
	
	$('#pg').live("click", function(event) {
		event.preventDefault();		
		var href1=$(this).attr('href')
		$("#prod").load("selectAllproduct.php", {
			p : href1
		});
		$("#pagination").load("pagination.php", {
			p : href1
		});
		
	});
	/*
	$("#p2").live("click", function() {
		$("#loading").html("zzzzzzzzzzzzzzzzzzzzz");
	});*/
	
});


$(document).ready(function() {
	
	$("#prod").load("selectAllproduct.php", {
		p : 1
	});
	$("#pagination").load("pagination.php", {
		p : 1
	});
	
	$("#loading").css({
		"background":"url(images/loader.gif) no-repeat center transparent",		
		"float":"left",
		"height":"30px",
		"width":"30px",
		"padding:":"0",
		"margin:":"0"		
		
	}).hide();	

	$("#prod").bind("ajaxStart",function() {		
		$(this).empty();
		$("#loading").fadeIn();
	});
		
	$("#prod").bind("ajaxComplete",function() {		
		$("#loading").fadeOut();
	});
	
});