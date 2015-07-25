<?php

/*
 * All variables
 */
//require_once __DIR__ . '/init_config.php';
//require_once $URL_db_connect;
$URL_db_connect = __DIR__ . '/db_connect.php';
//$url_img_Def='http://localhost/eCommerce.Touch3D.tn/products/';
$url_img_Def='http://eCommerce.Touch3D/products/';
$url_img = '../products/';

$c_table="products";
// `id`, `ref`, `name`, `price`, `tax`, `size`, `categories`, `availability`, `description`, `url`, `date`
$c_pid="id";
$c_name="name";
$c_price="price";
$c_description="description";
$c_url="url";
$c_created_at="date";

$c_ref="ref";
$c_tax="tax";
$c_size="size";
$c_categories="categories";
$c_availability="availability";



//config android application
define('Tag_success', "success");
define('Tag_message', "message");
define('Tag_fichier', "fichier");
define('Tag_address', "address");


define('Tag_Hach', "hach");
define('Tag_Pid', "pid");
define('Tag_Name', "name");
define('Tag_Price', "price");
define('Tag_Description', "description");
define('Tag_Url', "url");
define('Tag_Created_at', "created_at");

define('Tag_ref', "ref");
define('Tag_tax', "tax");
define('Tag_size', "size");
define('Tag_categories', "categories");
define('Tag_availability', "availability");





function response($i,$m){ 
$responseM = array();
$responseM[Tag_success] = $i;
$responseM[Tag_message] = $m;
return $responseM; 
}
?>