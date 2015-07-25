<?php
$cnx=false; 
include_once  ("../src/dbConx.php");
$db = new dbConnexion();
session_start();
if($db->isSessionAdmin()){
$db->dbConnectAdmin();
//connected echo htmlentities($dnn['email'], ENT_QUOTES, 'UTF-8');
$cnx=true;
}else {	
	header('Location: login.php');
}
if(isset($_GET["Disconnect"])){
	$db->DisconnectAdmin();
}

if($cnx){
?>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
<head profile="http://gmpg.org/xfn/11">
<title>Touch3D</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="description" content="espace admin Touch3D" />
<link rel="shortcut icon" href="../images/icon.png" />


<link rel="stylesheet" type="text/css" href="style2.css" media="screen" />
<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="../js/scrollDir.js"></script>


<script src="jquery.min.js" type="text/javascript"></script>
<script src="jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="uploadify.css">

</head>
<body>

 

<div class="conn">
<p>You are connected &nbsp;<img width="18px" height="18px" src="images/notice-info.png"/></br><a href="index.php?Disconnect">Disconnect &nbsp;<img width="18px" height="18px" src="images/icon-username.png"/></a></p>
</div></br>
<?php 

	//Add Update Delete
if(isset($_POST["v"])&&!empty($_POST["v"])){
	if(isset($_POST["ref"]) 
	&& isset($_POST["name"]) 
	&& isset($_POST["price"])){
			
switch ($_POST["v"]){
	case "Update":	
	if(!isset($_POST["id00"]))break;		
	$req="UPDATE `products` SET `id`='".$_POST["id"]."',`ref`='".$_POST["ref"]."',`name`='".$_POST["name"]."',`price`='".$_POST["price"]."',`tax`='".$_POST["tax"]."',`size`='".$_POST["size"]."',`categories`='".$_POST["categories"]."',`availability`='".$_POST["availability"]."',`description`='".$_POST["description"]."',`url`='".$_POST["url"]."',`date`='".$_POST["date"]."' WHERE `id`='".$_POST["id00"]."';";	
	$test1 = $db->dbExec($req);
	echo "<h4>Data Updated</h4>";			 
	break;
	case "Delete":
	if(!isset($_POST["id00"]))break;		
	$req="DELETE FROM `products` WHERE `id`='".$_POST["id00"]."';";
	$test1 = $db->dbExec($req);
	echo "<h4>Data Deleted</h4>";			 
	break;		
	case "Add":		
	$dateP = date("Y-m-d" );		
	$req="INSERT INTO `products`(`id`, `ref`, `name`, `price`, `tax`, `size`, `categories`, `availability`, `description`, `url`, `date`) VALUES ('','".$_POST["ref"]."','".$_POST["name"]."','".$_POST["price"]."','".$_POST["tax"]."','".$_POST["size"]."','".$_POST["categories"]."','".$_POST["availability"]."','".$_POST["description"]."','".$_POST["url"]."','".$dateP."');";
	$test1 = $db->dbExec($req);
	echo "<h4>Data Inserted</h4>";			 
	break;		
	
}
}
}

$req="SELECT `id`, `ref`, `name`, `price`, `tax`, `size`, `categories`, `availability`, `description`,`url`, `date` FROM `products`";
$datas = $db->dbSelect($req);

?>
</br>
<div>
<table>
<tr>
<th>id</th>
<th>ref</th>
<th>name</th>
<th>price</th>
<th>tax</th>
<th>size </th>
<th>categories </th>
<th>availability </th>
<th>description</th>
<th>url</th>
<th>date</th>
<th>*Update</th>
<th>*Delete</th>
</tr>
<?php 
if($datas!=NULL)
 foreach($datas as $data)
 {
 
 ?>
<!--  
<td ><input type="text" value="" name="Titre_en"/></td>
-->
<tr>
<form action="index.php" method="post" target="_self">
<td ><input type="text" class="id" placeholder="<?php echo $data['id'];?>" value="<?php echo $data['id']; ?>" name="id"/>
<input type="hidden" value="<?php echo $data['id']; ?>" name="id00"/></td>
<td ><input type="text" class="text1" placeholder="ref" value="<?php echo $data['ref']; ?>" name="ref"/></td>
<td ><input type="text" class="text1" placeholder="name" value="<?php echo $data['name']; ?>" name="name"/></td>
<td ><input type="text" class="text1" placeholder="price" value="<?php echo $data['price']; ?>" name="price"/></td>
<td ><input type="text" class="text1" placeholder="tax" value="<?php echo $data['tax']; ?>" name="tax"/></td>
<td ><input type="text" class="text1" placeholder="size" value="<?php echo $data['size']; ?>" name="size"/></td>
<td ><input type="text" class="categories" placeholder="categories" value="<?php echo $data['categories']; ?>" name="categories"/></td>
<td ><input type="text" class="text1" placeholder="availability" value="<?php echo $data['availability']; ?>" name="availability"/></td>
<td ><textarea name="description" rows="4" cols="35"  ><?php echo $data['description']; ?></textarea></td>
<td ><input type="text" class="url" placeholder="Image" value="<?php echo $data['url']; ?>" name="url"/></td>
<td ><input type="text" class="Date" value="<?php echo $data['date']; ?>" name="date"/></td>
<td><input type="submit" class="bSubmit" name="v" value="Update"/></td>
<td><input type="submit" class="bSubmit" name="v" value="Delete"/></td>
</form>
</tr>
<?php } ?>
<tr>
<form action="index.php" method="post" target="_self">
<td ><input type="text" class="id" placeholder="<?php echo ($data['id']+1); ?>" name="id"/></td>
<td ><input type="text" class="text1" placeholder="ref" value="<?php echo uniqid("R_"); ?>" name="ref"/></td>
<td ><input type="text" class="text1" placeholder="name" value="man woman" name="name"/></td>
<td ><input type="text" class="text1" placeholder="price" value="9.00" name="price"/></td>
<td ><input type="text" class="text1" placeholder="tax" value="1.00" name="tax"/></td>
<td ><input type="text" class="text1" placeholder="size" value="30,32,34,36,38,40,42,44,46" name="size"/></td>
<td ><input type="text" class="categories" placeholder="categories" value="Bags Shoes Hats Lorems Ipsums Dresses Jewellery Furniture" name="categories"/></td>
<td ><input type="text" class="text1" placeholder="availability" value="1" name="availability"/></td>
<td ><textarea name="description" rows="4" cols="35" placeholder="description" >
Duis blandit, ante quis elementum lobortis. Quisque diam massa, consectetur eu suscipit et, ullamcorper vitae eros. Vestibulum bibendum, lacus vel mattis interdum, mauris libero mollis velit, sit amet semper ligula enim eu neque. Morbi ac mauris nec velit ullamcorper eleifend. Quisque tempor lorem non enim dictum ac commodo risus rutrum.
</textarea></td>
<td ><input type="text" class="url" placeholder="Image" value="product-x.jpg" name="url"/></td>
<td ><input type="text" class="Date" placeholder="Date" name="date" value="<?php echo date("Y-m-d" );?>"/></td>

<td colspan="2"><input type="submit" class="bSubmit" name="v" value="Add"/></td>
</form>
</tr>

</table>
<h1>Upload Picture Product</h1>
	<form>
		<div id="queue"><?php $pc=$db->GetPic(); if($pc!=null)echo '<input type="text" id="picId" value="'.$pc.'"/>';?></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">		
	</form>


	<script type="text/javascript">
		<?php $timestamp = time();?>
		$(function() {
			$('#file_upload').uploadify({
				'formData'     : {
					'timestamp' : '<?php echo $timestamp;?>',
					'token'     : '<?php echo md5('unique_salt' . $timestamp);?>'
				},
				'swf'      : 'uploadify.swf',
				'uploader' : 'uploadify.php'				
			});
		});
	</script>
</div>
</br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br></br>
<a href="#" id="toTop">&uarr;</a>
<a href="#" id="toBottom">&darr;</a>
<a href="#" id="toRight">&rarr;</a>
<a href="#" id="toLeft">&larr;</a>
<?php }?>
</body>
</html>