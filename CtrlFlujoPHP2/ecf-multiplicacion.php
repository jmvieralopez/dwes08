<html>
<body>
<h2>ecf-multiplicacion</h2>
<?php
if (! isset ( $_POST ['enviar5'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-multiplicacion.php" method="post">
		Introduce X: <input type="number" name="x5" />
		<input type="submit" name="enviar5">
	</form>
	<?php
} else {
	$x5 = $_POST['x5'];
	for($m = 1; $m <= 10; $m++) {
		echo "<p>".$x5." * ".$m." = ".$x5*$m."</p>";
	}
}
?>
</body>
</html>