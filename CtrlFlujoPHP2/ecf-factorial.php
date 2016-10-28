<html>
<body>
<h2>ecf-factorial</h2>
<?php
if (! isset ( $_POST ['enviar4'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-factorial.php" method="post">
		Introduce X: <input type="number" name="x4" />
		<input type="submit" name="enviar4">
	</form>
	<?php
} else {
	$x4 = $_POST['x4'];
	$fac = 1;
	for($l = 2; $l <= $x4; $l++) {
		$fac *= $l;
	}
	echo "<p>".$fac."</p>";
}
?>
</body>
</html>