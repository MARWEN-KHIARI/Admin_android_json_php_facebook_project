<?php
//ini_set("display_errors",0); 
error_reporting(0);
include('config.php');

class dbConnexion extends config{
	public $link;
	//$date = date("Y-m-d");
	//$heure = date("H:i");

	function __construct(){
		}

	function dbConnect(){
		$this->link = mysql_connect(parent::Get_server(), parent::Get_username(), parent::Get_password());
		mysql_select_db(parent::Get_dbname(), $this->link)or die ("erreur bd");;
	}

	function ConnectAdmin($n,$p){
		if(($this->safe($n)==parent::Get_AdminName())&&($this->safe($p)==parent::Get_AdminPass0()))
		{				
			$_SESSION['current']=parent::Get_AdminUID();			
			return true;
		}else return false;
	}

	function GetPic(){
		if ((isset($_SESSION['pic'])) && (!empty($_SESSION['pic'])))
		return $_SESSION['pic'];
		else return null;

	}
	function isSessionAdmin(){
		
		if ((isset($_SESSION['current'])) && (!empty($_SESSION['current'])))
		{
			if($_SESSION['current']==parent::Get_AdminUID())
			return true;
			else return false;
		}else return false;
	}

	function DisconnectAdmin(){
		//session_unregister("current");
		session_destroy();
		header("location: login.php?");
	}


	function dbConnectAdmin(){
		$this->link = mysql_connect(parent::Get_server(), parent::Get_AdminName(), parent::Get_AdminPass());
		mysql_select_db(parent::Get_dbname(), $this->link)or die ("erreur bd");;
	}

	function dbSelect($sql){
		$req = mysql_query($sql,$this->link);
		$i=0;
		if(mysql_num_rows($req)!=0){
			while($data = mysql_fetch_array($req))
			{

				$t[$i]['id']=$data['id'];
				$t[$i]['ref']=$data['ref'];
				$t[$i]['name']=$data['name'];				
				$t[$i]['price']=$data['price'];
				$t[$i]['tax']=$data['tax'];
				$t[$i]['size']=$data['size'];
				$t[$i]['categories']=$data['categories'];
				$t[$i]['availability']=$data['availability'];
				$t[$i]['description']=$data['description'];
				$t[$i]['url']=$data['url'];
				$t[$i]['date']=$data['date'];				
				$i++;
			}
			mysql_close();
			return $t;
		}
		mysql_close();
		return null;
	}
	
	function dbNbreTotal($table){
		$sql="SELECT COUNT(*) AS total FROM ".$table; 
		$req = mysql_query($sql,$this->link);
		$i=0;
		if(mysql_num_rows($req)!=0){
			while($data = mysql_fetch_array($req))
			{
				$t=$data['total'];							
			}
			mysql_close();
			return $t;
		}
		mysql_close();
		return 0;
	}
	function dbExec($sql){
		$req = mysql_query($sql,$this->link);
		//mysql_close();
	}

	function safe($variable)
	{
		$variable = addslashes(trim($variable));
		//$variable  = stripslashes($variable);
		//$variable  = mysql_real_escape_string(stripslashes($variable));
		return $variable;
	}
	function preg_match1($motDeTeste,$variable1){
		if(preg_match($motDeTeste,$variable1))echo '<hr>test vrai<hr><hr>';
		//$db ->preg_match1("/mar/","mar");
	}

}
?>
