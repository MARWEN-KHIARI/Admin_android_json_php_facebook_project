<?php
class config{
	private $server   = "localhost";
	private $username = "touch3d_MARWEN";
	private $password = "";
	private $dbname   = "t3d";
	private $AdminName = "root";
	private $AdminPass = "";
	private $AdminPass0 = "mAbazz";
	private $AdminUID = "josdqaWf8ZKBz6e5tbQU52qsdfqfqsdxVn8OUr8hGPtb";

	function Get_server(){
		return $this->server;
	}

	function Get_username(){
		return $this->username;
	}

	function Get_password(){
		return $this->password;
	}

	function Get_AdminName(){
		return $this->AdminName;
	}

	function Get_AdminPass(){
		return $this->AdminPass;
	}
	function Get_AdminPass0(){
		return $this->AdminPass0;
	}
	function Get_dbname(){
		return $this->dbname;
	}
	function Get_AdminUID(){
		
		return $this->AdminUID;
	}

}
?>
