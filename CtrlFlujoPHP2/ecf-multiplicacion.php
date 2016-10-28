<html>
<body>
<h2>ecf-multiplicacion</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	var_dump($_POST);
	?>
<form action="ecf-multiplicacion.php" method="post">
		Introduce X: <input type="text" name="x" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	$multi = 0;
	for($m = 1; $m <= 10; $m++) {
		$multi = $m * $x;
		echo "<p>".$x." * ".$m." = ".$multi."</p>";
	}
}
?>
</body>
</html>