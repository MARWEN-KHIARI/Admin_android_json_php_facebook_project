<?php
 
/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */
 

require_once __DIR__ . '/init_config.php';
 
// check for required fields
if (isset($_POST[Tag_Pid])) {
 
    $pid = $_POST[Tag_Pid];
    if(isset($_POST[Tag_Price])&&!empty($_POST[Tag_Price]))$price = $_POST[Tag_Price];
    else $price="0.0";
    
 
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
       
       $requete="UPDATE $c_table SET $c_price = '$price'";
       
       if(isset($_POST[Tag_ref])&&!empty($_POST[Tag_ref]))$requete .= " , $c_ref = '".$_POST[Tag_ref]."'";
    if(isset($_POST[Tag_Name])&&!empty($_POST[Tag_Name]))$requete .= " , $c_name = '".$_POST[Tag_Name]."'";    
    if(isset($_POST[Tag_Description])&&!empty($_POST[Tag_Description]))$requete .= " , $c_description = '".$_POST[Tag_Description]."'";

    if(isset($_POST[Tag_Url])&&!empty($_POST[Tag_Url]))$requete .= " , $c_url = '".$_POST[Tag_Url]."'";


if(isset($_POST[Tag_tax])&&!empty($_POST[Tag_tax]))$requete .= " , $c_tax = '".$_POST[Tag_tax]."'";
    if(isset($_POST[Tag_size])&&!empty($_POST[Tag_size]))$requete .= " , $c_size = '".$_POST[Tag_size]."'";
   if(isset($_POST[Tag_categories])&&!empty($_POST[Tag_categories]))$requete .= " , $c_categories = '".$_POST[Tag_categories]."'";
    $requete .= " , $c_availability = '".$_POST[Tag_availability]."'";
    if(isset($_POST[Tag_Created_at])&&!empty($_POST[Tag_Created_at]))$requete .= " , $c_created_at = '".$_POST[Tag_Created_at]."'";
   
    $requete .= " WHERE $c_pid = $pid";
    $result = mysql_query($requete);

        


    // check if row inserted or not
    if ($result) {
        // successfully updated
        echo json_encode(response(1,"Product successfully updated."));
    } else {
 		echo json_encode(response(0,"Required field(s) is missing"));
    }
} else {
    // required field is missing
    echo json_encode(response(0,"Required field(s) is missing"));
}
?>