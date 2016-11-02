<html>
<body>
	<h2>ecf-acumulador</h2>
	<form action="ecf-acumulador.php" method="post">
<?php //ASERLO FUNSIONAR
$acumulador = 0;
while ( $acumulador > 50 ) {
	if (! isset ( $_POST ["enviar"] )) {
		// var_dump($_POST);
		echo 'Introduce numero: <input type="number" name="num" /><br>
		<input type="submit" name="enviar">';
	} else {
		echo 'Introduce numero: <input type="number" name="num" /><br>
		<input type="submit" name="enviar">';
		$num = $_POST ['num'];
		$acumulador += $num;
	}
}
?>
	</form>
</body>
</html>