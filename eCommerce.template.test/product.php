<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame. Remove this if you use the .htaccess -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  
	<title>Bonfire : HTML Template for Shoping Website</title>
    <link rel="stylesheet" type="text/css" media="all" href="style.css" />
    <link rel="stylesheet" type="text/css" media="all" href="jquery.selectBox.css" />
	
	<!-- Generate Favicon Using 1.http://tools.dynamicdrive.com/favicon/ OR 2.http://www.favicon.cc/ -->
	<link rel="shortcut icon" href="favicon.ico" />
	
</head>
<?php include  ("./selectIdProduct.php"); ?>

<body> 
	
    <div class="navigation-wrapper">
            <!-- MAIN NAVIGATION -->
            <ul id="navigation" class="clearfix">
                    <li><a href="index.php" class="asg">E-commerce Admin </a></li>    
            </ul><!-- end of #navigation -->
            
    </div>
    <!-- END of NAVIGATION WRAPPER -->
    
    <div class="container">
            
            
            <!-- START of BREADCRUMBS -->
            <p id="breadcrumbs">
            		<a href="index.php">Home</a>
                    <a href="index.php">Products</a>
                    <span class="active"><?php echo $data['categories']; ?></span>
            </p>
            <!-- END of BREADCRUMBS -->
            
            
            <!-- START of INNER-CONTAINER -->
            <div class="inner-container clearfix">
<?php //var_dump($data);?>
            		<div id="product" class="clearfix">
                    		<div class="product-gallery">
                    		<?php 
                    		//$pic=trim($data['url'], ".jpg"); $pic.='p'; 
                    		$pic=$data['url'];
                    		$smallPic=explode(".",$data['url']);
                    		$smallPic=$smallPic[0];
                    		$smallPic.='_.jpg';?>
	                            	<div class="large-image">
	                            		<a class="cloud-zoom" id='zoom1' href="products/<?php echo $pic; ?>" rel="adjustX: 10, adjustY:-4, softFocus:true">
	                            			<img src="products/<?php echo $pic; ?>" alt="" />
	                            		</a>
	                            	</div>
                                    <ul class="clearfix">
                                    		<li><a class="thumbnail cloud-zoom-gallery" href='products/<?php echo $smallPic; ?>' title='Thumbnail 1' rel="useZoom: 'zoom1', smallImage: 'products/<?php echo $pic; ?>' ">
                                            	<img src="products/<?php echo $smallPic; ?>" alt="" />
                                                </a>
                                            </li>
                                    </ul>
                            </div><!-- end of .product-gallery -->
							
                            <div class="product-detail">                            
								<h2><a href="#"><?php echo $data['name']; ?></a></h2>
                                    <cite>BY Lorem Ipsum</cite>
                                    <p>Product Code: <?php echo $data['ref']; ?></p>
                                    <p>Reward Points: 9</p>
                                    <p>Availability:<span><?php if($data['availability']==1)echo "In Stock"; else echo "Out Stock"; ?></span></p>
                                    <p class="price">Price: $<?php echo $data['price']; ?></p>
                                    <p class="tax">TAX: $<?php echo $data['tax']; ?></p>
                                    <form class="options-form" method="get" action="#">
			                    			<fieldset>
                                            		<div class="available-options">
                                            		  <label for="options">Size:</label>
                                            		  <select id="options">
                                            		  <?php 
													$ress=explode(",",$data['size']);
                                            		  foreach ($ress as $res1){
                                            		  	echo '<option value="'.$res1.'">'.$res1.'</option>';
														}?>
                                            		  </select>
                                            		</div>
                                                    <p class="qty">
                                                    	<label>Quantity:</label>
                                                    	<input type="text" value="1" />
                                                    </p>
                                                    <input type="button" class="submit-btn" onclick="javaScript:if(confirm('would you like to add this to your cart'))document.location.replace('index.php');" value="ADD TO CART" />
			                    	        </fieldset>
			                    	</form><!-- end of .available-options -->
                                   
                                    <div class="clearfix rat-rev">
                                    	<div class="rating">
                                    		<a href="#" class="yellow">&nbsp;</a>
                                    		<a href="#" class="yellow">&nbsp;</a>
                                    		<a href="#" class="yellow">&nbsp;</a>
                                    		<a href="#" class="gray">&nbsp;</a>
                                    		<a href="#" class="gray">&nbsp;</a>
                                    	</div>
                                     
                                    </div>
                                    <ul class="icons">
                                    	<li><a class="tw" href="#">&nbsp;</a></li>
                                    	<li><a class="fb" href="#">&nbsp;</a></li>
                                    	<li><a class="print" href="#">&nbsp;</a></li>
                                    	<li><a class="email" href="#">&nbsp;</a></li>
                                    	<li><a class="share" href="#">&nbsp;</a></li>
                                    </ul>

                            </div><!-- end of .product-detail -->
                    </div><!-- end of #contents -->
                    
            
            </div>
            <!-- END of INNER-CONTAINER -->
            
            
            <!-- START TABS EXAMPLE -->
            <div class="product-tabs">
	            <ul class="tabs">
	                    <li><a>DESCRIPTION</a></li>
	                    
	            </ul>									
	            <div class="panes">
	                    <div class="tab-pane">
	                        <p>Duis blandit, ante quis elementum lobortis, metus sapien dapibus quam Non pulvinar sapien massa vel ipsum. Fusce non risus urna. Nulla ultricies purus eget augue vulputate in accumsan turpis accumsan. Sed vel faucibus sem. Quisque diam massa, consectetur eu suscipit et, ullamcorper vitae eros. Vestibulum bibendum, lacus vel mattis interdum, mauris libero mollis velit, sit amet semper ligula enim eu neque. Morbi ac mauris nec velit ullamcorper eleifend. Quisque tempor lorem non enim dictum ac commodo risus rutrum.</p>
	                    </div>
	            </div>
            </div>
            <!-- END TABS EXAMPLE -->
            
            
    
    </div><!-- end of .container -->
    
    
    
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
</body>
</html>