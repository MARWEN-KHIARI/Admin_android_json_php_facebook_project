<?php

/*
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */
require_once __DIR__ . '/init_config.php';

// check for required fields
if (isset($_POST[Tag_Pid])) {
	$pid = $_POST[Tag_Pid];

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

	// mysql update row with matched pid
	$result = mysql_query("DELETE FROM $c_table WHERE $c_pid = $pid Limit 1");

	// check if row deleted or not
	if (mysql_affected_rows() > 0) {
		// successfully updated
		echo json_encode(response(1,"Product successfully deleted"));
	} else {
		// no product found
		echo json_encode(response(0,"No product found"));
	}
} else {
	// required field is missing

	echo json_encode(response(0,"Required field(s) is missing"));
}
?>