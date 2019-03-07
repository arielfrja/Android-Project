 
<?php 
	try {
		require 'DB_Manage.php';  
  
	
 
   
		if (isset($_REQUEST["_id"])) 
			$id = $_REQUEST["_id"];
		else
			$id = 'NULL';   
		$name = $_REQUEST["name"];
		$address = $_REQUEST["address"];  
		$phone = $_REQUEST["phone"];
		$email = $_REQUEST["email"];
		$website = $_REQUEST["website"];
  
		$sql = "INSERT INTO `Agencies`(  'name', 'address', 'phone', 'email', 'website', 'id') VALUES ('$name', '$address', '$phone', '$email', '$website', "_id")"; 
	
		if ($conn->query($sql) === TRUE) {
			$last_id = $conn->insert_id;
			echo $last_id;
		}
		else { 
			echo "Error: " . $sql . "\n" . $conn->error;
		}      
  
 
 
}  
	catch(Exception $e) {
		echo "Exception Error See Log....";
		error_log($e->getMessage() , 0);
		}
	$conn->close(); ?>  