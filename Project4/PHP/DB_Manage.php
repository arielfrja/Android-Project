<?php
$servername = "localhost";
$username = "faridja";
$password = "!qazXsw3";
$dbname = "faridja_project"; 
$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error)
{ 
    die("Connection failed: " . $conn->connect_error);
	}
$sql = "SELECT * FROM `product_table` WHERE 1"; 	
?>