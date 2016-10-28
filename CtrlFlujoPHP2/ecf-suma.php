<html>
<body>
<h2>ecf-suma</h2>
<?php
if (! isset ( $_POST ['enviar2'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-suma.php" method="post">
		Introduce X: <input type="text" name="x" />
		<input type="submit" name="enviar2">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	$suma = 0;
	for($j = 1; $j <= $x; $j++) {
		$suma += $j;
	}
	echo "<p>".$suma."</p>";
}
?>
</body>
</html>