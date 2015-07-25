/*-----------------------
 * jQuery Plugin: Scroll to Top
 * by Craig Wilson, Ph.Creative (http://www.ph-creative.com)
 * modified by Khiari Marwen for Touch3d.tn
 * Copyright (c) 2009 Ph.Creative Ltd.
 * Description: Adds an unobtrusive "Scroll to Top" link to your page with smooth scrolling.
 * For usage instructions and version updates to go http://blog.ph-creative.com/post/jquery-plugin-scroll-to-top.aspx
 * Do not delete these infomation
 * Version: 1.0, 12/03/2009
-----------------------*/

$(function() {
	$.fn.scrollToRight = function() {
		$(this).hide().removeAttr("href");
		if ($(window).scrollLeft() < "150") {
			$(this).fadeIn("slow")
		}
		var scrollDiv = $(this);
		$(window).scroll(function() {
			if ($(window).scrollLeft() > "200") {
				$(scrollDiv).fadeOut("slow")
			} else {
				$(scrollDiv).fadeIn("slow")
			}
		});
		$(this).click(function() {
			$("html, body").animate( {
				scrollLeft : 1000
			}, "slow")
		})
	}
});

$(function() {
	$.fn.scrollToLeft = function() {
		$(this).hide().removeAttr("href");
		if ($(window).scrollLeft() > "200") {
			$(this).fadeIn("slow")
		}
		var scrollDiv = $(this);
		$(window).scroll(function() {
			if ($(window).scrollLeft() < "100") {
				$(scrollDiv).fadeOut("slow")
			} else {
				$(scrollDiv).fadeIn("slow")
			}
		});
		$(this).click(function() {
			$("html, body").animate( {
				scrollLeft : 0
			}, "slow")
		})
	}
});

$(function() {
	$.fn.scrollToTop = function() {
		$(this).hide().removeAttr("href");
		if ($(window).scrollTop() != "0") {
			$(this).fadeIn("slow")
		}
		var scrollDiv = $(this);
		$(window).scroll(function() {
			if ($(window).scrollTop() == "0") {
				$(scrollDiv).fadeOut("slow")
			} else {
				$(scrollDiv).fadeIn("slow")
			}
		});
		$(this).click(function() {
			$("html, body").animate( {
				scrollTop : 0
			}, "slow")
		})
	}
});

$(function() {
	$.fn.scrollToBottom = function() {
		$(this).hide().removeAttr("href");
		if ($(window).scrollTop() < "100") {
			$(this).fadeIn("slow")
		}
		var scrollDiv = $(this);
		$(window).scroll(function() {
			if ($(window).scrollTop() > "100") {
				$(scrollDiv).fadeOut("slow")
			} else {
				$(scrollDiv).fadeIn("slow")
			}
		});
		$(this).click(function() {
			$("html, body").animate( {
				scrollTop : 3000
			}, "slow")
		})
	}
});

$(function() {
	$("#toRight").scrollToRight();
});

$(function() {
	$("#toTop").scrollToTop();
});
$(function() {
	$("#toLeft").scrollToLeft();
});
$(function() {
	$("#toBottom").scrollToBottom();
});