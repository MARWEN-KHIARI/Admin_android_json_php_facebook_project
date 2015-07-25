<?php

/*
 * Following code will list all the products
 */

// array for JSON response


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


// get all products from products table
$result = mysql_query("SELECT $c_pid, $c_name, $c_price, $c_created_at FROM $c_table") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
	$response = array();
	// looping through all results
	// products node
	$response["product"] = array();

	while ($row = mysql_fetch_array($result)) {
		// temp user array
		$product = array();
			
		$product[Tag_Pid] = $row[$c_pid];
		$product[Tag_Name] = $row[$c_name];
		$product[Tag_Price] = $row[$c_price];	
		$product[Tag_Created_at] = $row[$c_created_at];		

		// push single product into final response array
		array_push($response["product"], $product);
	}
	// success
	$response["success"] = 1;

	// echoing JSON response
	echo json_encode($response);
} else {
	// echo no users JSON
	echo json_encode(response(0,"No products found"));
}
?>