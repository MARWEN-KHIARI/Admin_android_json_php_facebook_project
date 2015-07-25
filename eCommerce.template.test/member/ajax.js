
$(document).ready(function() {
	$("#checkPass").click(function() {
		
		$.post(
			'conxAdmin.php',
			{
				user: $("#user").val(),
				pass: $("#pass").val()
			},
			function(data) {			
				if(data=='<h1>wrong password or name!</h1>') {
					$("#resultat").html("<h1>wrong password or name!</h1>");
				} else {
					document.location.replace("index.php");
				}
			},
			'text' // type
		);
	
	});

});
/* Gestion globale des événements Ajax sur la page */

$(document).ready(function() {
	//$("<div id=\"loading\"></div>").insertAfter("resultat");	
	$("#loading").css({
		"background":"url(images/loader.gif) no-repeat center transparent",		
		"float":"right",
		"height":"30px",
		"width":"30px",
		"padding:":"0",
		"margin:":"0"		
		
	}).hide();	

	$("#resultat").bind("ajaxStart",function() {
		$("#loading").fadeIn();
		$(this).empty();		
		$(this).append("<p>...</p>");
	});
	
	$("#resultat").bind("ajaxError",function() {
		$(this).append("<p>... !</p>");
	});

	$("#resultat").bind("ajaxSuccess",function() {
		
	});
	
	$("#resultat").bind("ajaxComplete",function() {		
		$("#loading").fadeOut();
	});
	
});

