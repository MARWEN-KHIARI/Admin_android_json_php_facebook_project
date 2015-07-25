<?php
include  ("../src/dbConx.php");
sleep(1);
$db = new dbConnexion();
session_start();
if ((isset($_POST['user'])) && (!empty($_POST['user']))&&(isset($_POST['pass'])) && (!empty($_POST['pass'])))
{
	if($db->ConnectAdmin($_POST["user"],$_POST["pass"])){		
		header("location: index.php?");
		echo '<script language="Javascript">document.location.replace("index.php");</script>';
	}
	else echo '<h1>wrong password or name!</h1>';
        
}

?>