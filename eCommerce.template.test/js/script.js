// JavaScript Document
jQuery(document).ready(function(){

	//this function attached focus and blur events with input elements
		var addFocusAndBlur = function($input, $val){
			
			$input.focus(function(){
				if (this.value == $val) {this.value = '';}
			});
			
			$input.blur(function(){
				if (this.value == '') {this.value = $val;}
			});
		}

	// focus and blur event attachment with input elements
		addFocusAndBlur(jQuery('#s'),'Search');
		addFocusAndBlur(jQuery('#coupon-code'),'Coupon Code:');
		addFocusAndBlur(jQuery('#email'),'Email Address');
		addFocusAndBlur(jQuery('#pw'),'Password');
	
	
	// SELECT-BOX
	// follow the link for more detail on it https://github.com/claviska/jquery-selectBox/blob/master/readme.md
		$('#header-select').selectBox();
		$('#options').selectBox();
		$('#country').selectBox();
		$('#state').selectBox();
		
	
	
	// CSS FIXES
		//$('.product-listing ul li:last-child').css('margin-right','0');
		$('#product .product-gallery ul li:last-child ').css('margin-right','0');
	
	
	// CYCLE-PLUGIN 
	// follow the link http://jquery.malsup.com/cycle/options.html to know more about cycle plugin
		$('#slides').cycle({
			fx: 'fade', 			// for more slide-effects follow the link http://jquery.malsup.com/cycle/browser.html
			speed: 800,	  			// slide transition speed in mili-seconds
			pause: true,      		// pasue slide transition on hover
			pager:  '#slider-pager',
			easing: 'easeInOutQuart'
		});
	
	
	// TABS and ACCORDION
	// for more help on tabs and accordion follow the link http://flowplayer.org/tools/tabs/index.html
		$("ul.tabs").tabs("div.panes > .tab-pane", {
				effect: 'fade',		// for more effects visit: http://flowplayer.org/tools/tabs/index.html
		});
		
		$(".accordion").tabs(".accordion div.pane", {
				tabs: 'h4',
				effect: 'slide'		// for more effects visit: http://flowplayer.org/tools/tabs/index.html
		});
		
	
	// ACCORDION: TOGGLE STYLE
		$('#checkout .pane').not('.current').hide();
		
		$('#checkout h4').click(function(){
			$(this).next('.pane').slideToggle(500);	
		});

	
	// HOVER EFFECT on IMAGES		
		// This sets the opacity of the thumbs to fade down to 100% when the page loads
		$('.product-listing a.thumb img, .grid-view a.thumb img, .list-view a.thumb img, #blog .post a.post-feature-image img').fadeTo('slow', 1.0);
		
		
		$('.product-listing a.thumb img, .grid-view a.thumb img, .list-view a.thumb img, #blog .post a.post-feature-image img').hover(
			function(){$(this).stop().fadeTo('slow', 0.6); }, 	// will set the opacity to 60% on hover
		   	function(){$(this).stop().fadeTo('slow', 1.0); 	// will set the opacity back to 100% on mouseout
		});
	
	
	// LIST-GRID TOGGLE EFFECT
		$('#all-product h3 a.list').click(function(){
				$('#all-product ul').removeClass('grid-view');
				$('#all-product ul').addClass('list-view');
			});
		
		$('#all-product h3 a.grid').click(function(){
				$('#all-product ul').removeClass('list-view');
				$('#all-product ul').addClass('grid-view');
			});		
		
	// COLOR-ANIMATIONS
		$('#sidebar ul li a').hover(
				function(){$(this).stop().animate({color: '#545454'})}, // color on hover
				function(){$(this).stop().animate({color: '#49aba8'})}	// color on mouseOut
		);
	
	
	// CLOUD ZOOM
		$('.thumbnail.cloud-zoom-gallery').click(function(e) {
			e.preventDefault();
		});	
		
		$('.cloud-zoom, .cloud-zoom-gallery').CloudZoom();

	
	// AJAX FORM OPTIONS
		var options = { 
	        target:        '#result',   // target element(s) to be updated with server response 
	        beforeSubmit:  function(){
								$('#loader').fadeIn('fast');
								$('#result').fadeOut('fast');							
							},  // pre-submit callback 
	        success:       function(){
								$('#loader').fadeOut('slow');
								$('#result').fadeIn('slow');
								$('#contact-form').resetForm();
							}  // post-submit callback 
	    };	
	
	// FORM VALIDATOR
		$('#contact-form').validate({
		  submitHandler: function(form){
			  	$(form).ajaxSubmit(options);
			  }
		});	  
	  
});