<html>
<body>
<h2>Problema 1</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="Problema1-1.php" method="post">
		Introduce numero: <input type="number" name="x" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	echo "<pre>"; //no vale code, no da espacios :(
	echo "/";
	for($k = 0; $k < $x; $k++){
		echo "-";
	}
	echo '\\</br>';
	//relleno
	for($i = 1; $i <= $x; $i++){
		echo "|";
		for ($j = 1; $j <= $i; $j++) {
			echo $j;
		}
		for ($l = $j; $l <= $x; $l++){
			echo " ";
		}
		echo "|</br>";
	}
	//final
	echo "\\";
	for($k = 0; $k < $x; $k++){
		echo "-";
	}
	echo '/';
	
	echo "</pre>";
}
?>
</body>
</html>