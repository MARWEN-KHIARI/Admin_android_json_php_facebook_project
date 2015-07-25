<?php

/**
 * A class file to connect to database
 */
class DB_CONNECT {

	// constructor
	function __construct() {
		// import database connection variables
		require_once __DIR__ . '/db_config.php';
	}

	// destructor
	function __destruct() {
		// closing db connection
		$this->close();
	}

	/**
	 * Function to connect with database
	 */
	// connecting to database
	function connect() {

		// Connecting to mysql database
		$con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());

		// Selecing database
		$db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());

		// returing connection cursor
		return $con;
	}

	/**
	 * Function to close db connection
	 */
	function close() {
		// closing db connection
		mysql_close();
	}

	function testHach($hash){
		$real_hash = md5(secretKey);
		if($real_hash == ($hash)){
			return true;
		}
		else{
			return false;
		}
	}
}

?>