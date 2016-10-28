<html>
<body>
<h2>ecf-multiplicacion</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	/*
	 * 
	 * 			ARREGLAR
	 * 
	 * 
	 */
	?>
<form action="ecf-multiplicacion.php" method="post">
		Introduce X: <input type="number" name="x" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	for($m = 1; $m <= 10; $m++) {
		echo "<p>".$x." * ".$m." = ".$x*$m."</p>";
	}
}
?>
</body>
</html>