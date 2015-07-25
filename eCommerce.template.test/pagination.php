<?php
$messageParPage = 6;
include  ("./src/dbConx.php");
$db = new dbConnexion();
$db->dbConnect();
if(isset($_POST["p"]))$pageActuelle=intval($_POST["p"]);
else $pageActuelle=1;

//$total = count($datas);
$total = $db->dbNbreTotal("`products`");
$nombreDePage = ceil($total/$messageParPage);

    $prev=$pageActuelle-1;if($prev<1)$prev=1;
    $next=$pageActuelle+1;if($next>$nombreDePage)$next=$nombreDePage;
    echo '<a href="'.$prev.'" id="pg">&lt;</a>';
    for($pp=1;$pp<=$nombreDePage;$pp++){
    	echo '<a href="'.$pp.'"';
    	if($pp==($pageActuelle))echo ' class="active"';	
        echo 'id="pg">'.$pp.'</a>';        
    }
    echo '<a href="'.$next.'" id="pg">&gt;</a>';
    ?>    
