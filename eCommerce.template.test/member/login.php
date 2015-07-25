


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
<head profile="http://gmpg.org/xfn/11">
<title>Touch3D.tn</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="description" content="espace admin Touch3D.tn" />
<link rel="shortcut icon" href="#" />

<link rel="stylesheet" type="text/css" href="style.css"
	media="screen" />
<script src="jquery.min.js" type="text/javascript"></script>
<script src="ajax.js" type="text/javascript"></script>


</head>
<body>
<?php
include_once  ("../src/dbConx.php");
$db = new dbConnexion();
session_start();
if($db->isSessionAdmin()){
header("location: index.php");
echo '<script language="Javascript">document.location.replace("index.php");</script>';
}	
if ((isset($_POST['user'])) && (!empty($_POST['user']))&&(isset($_POST['pass'])) && (!empty($_POST['pass'])))
{
	if($db->ConnectAdmin($_POST["user"],$_POST["pass"])){		
		header("location: index.php?");
		echo '<script language="Javascript">document.location.replace("index.php");</script>';
	}
	else echo '<h1>wrong password or name!</h1>';
        
}

?>
<img class="background-image" src="./images/login-whisp.png"/>
<div id="forms">

<form id="login_form" action="login.php" method="post" target="_self">
<div class="input-req-login"><label for="user">Name&nbsp;</label>
</div>
<div class="input-field-login">
<input name="user" id="user" autofocus="autofocus" value="" 
placeholder="name" class="std_textbox" type="text" tabindex="1" required="" />
<div id="loading"></div>
</div>
<div style="margin-top: 30px;" class="input-req-login">
<label for="pass">Password&nbsp;</label>
</div>
<div class="input-field-login">
<input
	name="pass" id="pass" placeholder="password" class="std_textbox"
	type="password" tabindex="2" required=""/></div>
<div class="controls">
<div class="login-btn">
<!--<button name="login" type="submit" id="login_submit" tabindex="4">C</button>-->
<input type="button" id="checkPass" value="Connectez-vous" tabindex="3"/><a href="index.php">Go</a>
<div id="resultat"></div>
</div>

</div>
<div class="clear" id="push"></div>
</form>


</div>
</body>
</html>

