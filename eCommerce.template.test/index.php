<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame. Remove this if you use the .htaccess -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  
	<title>Shopping Website </title>
    
	<link rel="stylesheet" type="text/css" media="all" href="style.css" />
    <link rel="stylesheet" type="text/css" media="all" href="jquery.selectBox.css" />
<!--	<script type="text/javascript" src="js/ajax.js"></script>-->
	<!-- Generate Favicon Using 1.http://tools.dynamicdrive.com/favicon/ OR 2.http://www.favicon.cc/ -->
	<link rel="shortcut icon" href="favicon.ico" />
	
</head> 
<body> 
	<div class="navigation-wrapper">
            <!-- MAIN NAVIGATION -->
		    <ul id="navigation" class="clearfix">
            		<li><a href="index.php" class="asg">E-commerce Admin <img src="images/app_logo.png" width="40px" height="40px"/> </a></li>    
		    </ul><!-- end of #navigation -->
            
  	</div>
    <!-- END of NAVIGATION WRAPPER -->
    
    
    <!-- START of BOTTOM -->
    <div class="bottom-wrapper">
    
		    <div id="bottom" class="clearfix">
            
            		<strong class="welcome-message">Welcome visitor this is a test for android application named ecommerceAdmin.</br>
                        <a href="./eCommerceAdmin.apk">Download the Apk application for android 2.3</a>                       
                        You must get the hach code from the admin <u><a>marwen.khyari@gmail.com</a></u>
                    </strong>
                    
                    <div class="right">
<!--                    
                        <form class="search" method="get" action="#">
                    			<fieldset>
                    	        		<input type="text" id="s" name="s" value="Search" />
                    	                <input class="submit" type="submit" value="Submit" />
                    	        </fieldset>
                    	</form>
                    	-->
                    </div>
            
		    </div><!-- end of #bottom -->
            
    </div>
    <!-- END of BOTTOM -->
    
    <div class="container">
    
    		<!-- START of SLIDER -->
            <div id="slider">
            		<div class="slides" id="slides">
					
                    		<div class="slide">
                            		<a href="product.php?id=1"><img src="images/slider/slide1.jpg" alt="diamonds on the soles of her shoes" /></a>                                    
                                    <div class="caption">
                                    		<a href="product.php?id=1" class="purchase-btn">&nbsp;</a>
                                    		<p>Shoes with Diamonds - Now Available for just <span class="price">$27.00</span></p>                                            
                                    </div><!-- end of .caption -->
                            </div><!-- end of .slide -->
                            
                            <div class="slide">
                            		<a href="product.php?id=2"><img src="images/slider/slide2.jpg" alt="suit for the soles of his body" /></a>                                    
                                    <div class="caption">
                                    		<a href="product.php?id=2" class="purchase-btn">&nbsp;</a>
                                    		<p>Lorem Suit - Now Available for just <span class="price">$85.00</span></p>                                            
                                    </div><!-- end of .caption -->
                            </div><!-- end of .slide -->
                            
                            <div class="slide">
                            		<a href="product.php?id=3"><img src="images/slider/slide3.jpg" alt="cool camera" /></a>                                    
                                    <div class="caption">
                                    		<a href="product.php?id=3" class="purchase-btn">&nbsp;</a>
                                    		<p>Top Selling Camera - Now Available for just <span class="price">$90.00</span></p>                                            
                                    </div><!-- end of .caption -->
                            </div><!-- end of .slide -->
							
							<div class="slide">
                            		<a href="product.php?id=4"><img src="images/slider/slide4.jpg" alt="comfortable sofa" /></a>                                    
                                    <div class="caption">
                                    		<a href="product.php?id=4" class="purchase-btn">&nbsp;</a>
                                    		<p>White Sofa - Now Available for just <span class="price">$120</span></p>                                            
                                    </div><!-- end of .caption -->
                            </div><!-- end of .slide -->
							
                    </div><!-- end of .slides -->
                    
                    <div id="slider-pager"></div>                    
            </div>
            <!-- END of SLIDER -->
            
            
            
            <!-- START of LATEST PRODUCTS -->
            


            <div class="product-listing">
            		<h3><div id="loading"></div><span>Products</span></h3>
                          
                    <ul id="prod" class="clearfix">            
						
					</ul>     
            </div>
            <!-- END of LATEST PRODUCTS -->
    
    
    
    
    
    <!-- START of PAGINATION -->
    
    <p class="pagination" id="pagination">
    </p>
    	<!-- END of PAGINATION -->
    </div><!-- end of .container -->

    
	<!-- START of PAGE-BOTTOM -->
    <div class="page-bottom-wrapper">
    		
            <div id="page-bottom" class="clearfix">
            		
                    <div class="box">
                            <div class="icon">
                            	<img src="images/icon-1.png" alt="" />
                            </div>                                   
							<div class="data">
								 <h3>Application Android</h3>
								 <p>gerer votre site a partir du mobile.</p>
							</div>
                            
                    </div><!-- end of .box -->
                    
                    <div class="box">
							<div class="icon">
                            	<img src="images/icon-2.png" alt="" />
                            </div>                                    
							<div class="data">
								 <h3>Lorem ipsum dolor</h3>
								 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque pretium nisi id sapien cursus eu lobortis libero imperdiet. Aliquam erat volutpat.</p>
							</div>							
                    </div><!-- end of .box -->
                    
                    <div class="box last">
							<div class="icon">
                            	<img src="images/icon-3.png" alt="" />
                            </div>                                    
							<div class="data">
								 <h3>Lorem ipsum dolor</h3>
								 <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque pretium nisi id sapien cursus eu lobortis libero imperdiet. Aliquam erat volutpat.</p>
							</div>                    		
                    </div><!-- end of .box -->
                    
            </div><!-- end of #page-bottom -->
            
    </div>
    <!-- END of PAGE-BOTTOM -->
    
    
    <!-- END of COPYRIGHTS-WRAPPER -->
    
	<!-- jQuery -->
	<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    
    <!-- jQuery Easing -->
	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
	
    <!-- jQuery Selectbox Script to custom style form select boxes -->
    <script type="text/javascript" src="js/jquery.selectBox.js"></script>
    
    <!-- jQuery Cycle Plugin for home page slider-->
    <script type="text/javascript" src="js/jquery.cycle.all.js"></script>
    
    <!-- jQuery Tabs and Accordion Script -->
    <script type="text/javascript" src="js/tabs-accordian.js"></script>
    
    <!-- jQuery Coud Zoom Plugin for Product Page Image Zoom Effect-->
    <script type="text/JavaScript" src="js/cloud-zoom.1.0.2.js"></script>
    
    <!-- jQuery Animate Color Plugin for Hover Color Animation for Links-->
    <script type="text/javascript" src="js/jquery.animate-colors-min.js"></script>           
    
    <!-- jQuery Form and Validation Plugin for Contact form validation and ajax submition -->
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>    	
	
    <!-- script file to add your own JavaScript -->
	<script type="text/javascript" src="js/script.js"></script>
	
	<script src="pagination.js" type="text/javascript"></script>
</body>
</html>