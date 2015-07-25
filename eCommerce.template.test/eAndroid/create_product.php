<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */
require_once __DIR__ . '/init_config.php';

// check for required fields
if (isset($_POST[Tag_Name]) && isset($_POST[Tag_Price]) && isset($_POST[Tag_Description])) {

	require_once $URL_db_connect;
	$db = new DB_CONNECT();


	if((isset($_POST[Tag_Hach]))||(isset($_GET[Tag_Hach]))){
		if(isset($_GET[Tag_Hach])){	$h1=$_GET[Tag_Hach];	}
		else{	$h1=$_POST[Tag_Hach];	}
		$access=$db->testHach($h1);
		if(!$access){
			echo json_encode(response(0,"No access"));
			exit();
		}
	} else {
		echo json_encode(response(0,"No access"));
		exit();
	}


	
	$name = $_POST[Tag_Name];
	$price = $_POST[Tag_Price];
	$description = $_POST[Tag_Description];
	$url = $_POST[Tag_Url];
	//$dateP = date("Y-m-d H:i:s" );
	$dateP = date("Y-m-d");
	$ref=uniqid("R_");	
	if(isset($_POST[Tag_tax]))$tax = $_POST[Tag_tax]; else $tax=0.00;
	if(isset($_POST[Tag_size]))$size = $_POST[Tag_size]; else $size=0;
	if(isset($_POST[Tag_categories]))$categories = $_POST[Tag_categories]; else $categories="Other";	
	$availability = $_POST[Tag_availability]; if($availability == "") $availability=0; 


	// connecting to db
	$db->connect();
	 
	// mysql inserting a new row
	
	$result = mysql_query("INSERT INTO $c_table($c_ref, $c_name, $c_price, $c_tax, $c_size, $c_categories, $c_availability, $c_description, $c_url, $c_created_at) VALUES('$ref', '$name', '$price', '$tax', '$size', '$categories', '$availability', '$description', '$url', '$dateP')");


	// check if row inserted or not
	if ($result) {
		// successfully inserted into database
		echo json_encode(response(1,"Product successfully created."));
	} else {
		// failed to insert row
		echo json_encode(response(0,"Oops! An error occurred."));
	}
} else {
	// required field is missing
	echo json_encode(response(0,"Required field(s) is missing"));
}
?>