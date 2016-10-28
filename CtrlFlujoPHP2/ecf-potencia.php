<html>
<body><h2>ecf-potencia</h2>
<?php
if (! isset ( $_POST ['enviar3'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-potencia.php" method="post">
		Introduce A: <input type="number" name="a3" /> 
		Introduce B: <input type="number" name="b3" /> 
		<input type="submit" name="enviar3">
	</form>
	<?php
} else {
	$a3 = $_POST['a3'];
	$b3 = $_POST['b3'];
	$resultado = 1;
	for($k = 1; $k <= $b3; $k++) {
		$resultado *= $a3;
	}
	echo "<p>".$resultado."</p>";
}
?>
</body>
</html>