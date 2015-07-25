
<?php
if(isset($_GET["id"])){
include  ("./src/dbConx.php");
$db = new dbConnexion();
$db->dbConnect();
$datas = $db->dbSelect("SELECT `id`, `ref`, `name`, `price`, `tax`, `size`, `categories`, `availability`, `description`, `url`, `date` FROM `products` WHERE id='".$_GET["id"]."';");
$data=$datas[0];
}else header("location: index.php");
?>