
<?php
$messageParPage = 6;
include  ("./src/dbConx.php");
$db = new dbConnexion();
$db->dbConnect();
if(isset($_POST["p"]))$pageActuelle=intval($_POST["p"]);
else $pageActuelle=1;
$premiereEntree = ($pageActuelle-1)*$messageParPage;
$datas = $db->dbSelect("SELECT `id`, `ref`, `name`, `price`, `tax`, `size`, `categories`, `availability`, `description`, `url`, `date` FROM `products` ORDER BY `date` LIMIT ".$premiereEntree.','.$messageParPage.";");




	
foreach ($datas as $data){	
							$smallPic=explode(".",$data['url']);
                    		$smallPic=$smallPic[0];
                    		$smallPic.='_.jpg';
?>

<li class="product">

<a href="product.php?id=<?php echo $data['id'];?>" class="thumb">
<img src="products/<?php echo $smallPic;?>" alt="" /></a> 

<a href="product.php?id=<?php echo $data['id'];?>" class="title">
<?php 
if($data['name']!="") echo $data['name']; 
else echo $data['ref'];
?>
</a>

<div class="clearfix info">
<a href="#" id="add2cart" class="add-to-cart">ADD TO CART</a>
<span class="price-text">$<span><?php echo $data['price'];?></span></span></div>
</li>

<?php }

?>

