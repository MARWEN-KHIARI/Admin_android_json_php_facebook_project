<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */


require_once __DIR__ . '/init_config.php';


// include db connect class
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

// connecting to db
$db->connect();

// check for post data
if (isset($_GET[Tag_Pid])) {
	$pid = $_GET[Tag_Pid];

	// get a product from products table
	$result = mysql_query("SELECT * FROM $c_table WHERE $c_pid = $pid");

	if (!empty($result)) {
		// check for empty result
		if (mysql_num_rows($result) > 0) {
			$response = array();
			$result = mysql_fetch_array($result);

			$product = array();
			$product[Tag_Pid] = $result[$c_pid];
			$product[Tag_Name] = $result[$c_name];
			$product[Tag_Price] = $result[$c_price];
			$product[Tag_Description] = $result[$c_description];
			$product[Tag_Url] = $result[$c_url];
			$product[Tag_Created_at] = $result[$c_created_at];

			$product[Tag_ref] = $result[$c_ref];
			$product[Tag_tax] = $result[$c_tax];
			$product[Tag_size] = $result[$c_size];
			$product[Tag_categories] = $result[$c_categories];
			$product[Tag_availability] = $result[$c_availability];
			
			// success
			$response[Tag_success] = 1;

			// user node
			$response["product"] = array();

			array_push($response["product"], $product);

			// echoing JSON response
			echo json_encode($response);
		} else {
			echo json_encode(response(0,"No product found"));
		}
	} else {
		echo json_encode(response(0,"No product found"));
	}
} else {
	echo json_encode(response(0,"Required field(s) is missing"));
}
?>