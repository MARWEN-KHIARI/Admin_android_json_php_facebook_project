<?php

require_once __DIR__ . '/init_config.php';
$namePic='';
$picture='';
$extension=".jpg";
$result=false;
$message="Wait..";

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

// check for required fields
if((isset($_POST[Tag_fichier]))&&(!empty($_POST[Tag_fichier]))){

	$fichier = $_POST[Tag_fichier];
	$image = addslashes($fichier);
	saveFile($image);
		//$rep=$_SERVER['DOCUMENT_ROOT']."eCommerce.Touch3D.tn/products/";
   $rep = $HTTP_SERVER_VARS['DOCUMENT_ROOT']."/products/";
   $dir = opendir($rep);
   while ($f = readdir($dir)){
      if (is_file($rep.$f) && preg_match("/jpg/",$f)){
       if(substr($f,strlen($f)-5,1)!="_"){
         $size = getimagesize ($rep.$f); 
         $largeur_image = $size[0]; 
         $hauteur_image = $size[1];
         $echelle = 174/$hauteur_image;
         $fileAddr=$rep."/".substr($f,0,strlen($f)-4)."_.jpg";
         if (!file_exists($fileAddr)){
            $picdest = imagecreatetruecolor($largeur_image*$echelle,$hauteur_image*$echelle);
            $picsrc = imagecreatefromjpeg($rep.$f); 
            imagecopyresampled ($picdest,$picsrc,0,0,0,0,$largeur_image*$echelle,$hauteur_image*$echelle,$largeur_image,$hauteur_image );
            imagejpeg($picdest,$fileAddr);
            ImageDestroy($picdest);
         }
      }
      }  
   }
   closedir($dir);	
	// check if row inserted or not
	if ($result) {
		// successfully updated
		$response = array();
		$response[Tag_success] = 1;
		$response[Tag_message] = $message;
		$response[Tag_address] = $namePic;
		// echoing JSON response
		echo json_encode($response);
	} else {

	}
} else {
	// required field is missing
	$response = array();
	$response[Tag_success] = 0;
	$response[Tag_message] = $message;
	$response[Tag_address] = $namePic;
	// echoing JSON response
	echo json_encode($response);
}

function saveFile($imgData){
	global $url_img_Def;
	global $picture;
	global $namePic;	
	global $extension;
	global $url_img;
	global $result;
	global $message;

	if(is_dir ($url_img )){
		//$picture=$folder;
		$picture="product-".uniqid();
		$picture.=$extension;
		$namePic=$picture;
		$url_img_Def=$url_img_Def.$picture;
		$picture=$url_img.$picture;

		touch($picture);
		$binary=base64_decode($imgData);
		$f=fopen($picture, 'w');
		fputs($f,$binary);
		fclose($f);
		if(file_exists($picture))
		{
			$result=true;
			$message="Picture successfully uploaded.";
			
			
			 
		}
	}else { $message="Missing folder";}
}
/*
function miniatureDir(){
	//$rep="/http://ecommerce.touch3d.tn/products/";
	//$rep=$HTTP_SERVER_VARS['DOCUMENT_ROOT']."/products/";
	$rep=$_SERVER['DOCUMENT_ROOT']."eCommerce.Touch3D.tn/products/";

	//global $url_img_Def;	$rep=$url_img_Def;
	$dir = opendir($rep);
	while ($f = readdir($dir)){
		if (is_file($rep.$f) && preg_match("/jpg/",$f)){
			if(substr($f,strlen($f)-5,1)!="_"){
				$size = getimagesize ($rep.$f);
				$largeur_image = $size[0];
				$hauteur_image = $size[1];
				$echelle = 174/$hauteur_image;
				$fileAddr=$rep."/".substr($f,0,strlen($f)-4)."_.jpg";
				if (!file_exists($fileAddr)){
					$picdest = imagecreatetruecolor($largeur_image*$echelle,$hauteur_image*$echelle);
					$picsrc = imagecreatefromjpeg($rep.$f);
					imagecopyresampled ($picdest,$picsrc,0,0,0,0,$largeur_image*$echelle,$hauteur_image*$echelle,$largeur_image,$hauteur_image );
					imagejpeg($picdest,$fileAddr);
					ImageDestroy($picdest);
				}
			}
		}
	}
	closedir($dir);
}
*/
/*$x = 150; 
$y = 105; # hauteur a redimensionner 


$img_new = imagecreatefromjpeg($chemin); 
$size = getimagesize($chemin); 
$img_mini = imagecreatetruecolor ($x, $y); 
imagecopyresampled ($img_mini,$img_new,0,0,0,0,$x,$y,$size[0],$size[1]); 
header('Content-Type:image/jpg'); 
imagejpeg($img_mini); */

?>
