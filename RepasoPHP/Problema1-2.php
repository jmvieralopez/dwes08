<html>
<body>
<h2>Problema 2</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="Problema1-2.php" method="post">
		Introduce numero: <input type="number" name="x" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	echo "<pre>"; //no vale code, no da espacios :(
	//arriba
	for($i = 0; $i < $x; $i++){
		echo "|";
		for ($j = 0; $j < $i; $j++) {
			echo " ";
		}
		echo "\</br>";
	}
	//linea central
	echo "|";
	for ($k = 0; $k < $x; $k++) {
		echo " ";
	}
	echo ">Esto es un ejemplo</br>";
	//abajo
	for($i = $x - 1; $i >= 0; $i--){
		echo "|";
		for ($j = $i; $j > 0; $j--) {
			echo " ";
		}
		echo "/</br>";
	}
	echo "</pre>";
}
?>
</body>
</html>