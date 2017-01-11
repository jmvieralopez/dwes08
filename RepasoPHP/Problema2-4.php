<html>
<body>
<h2>Problema 3</h2>
<?php
$cnt = 0;
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="Problema2-4.php" method="post">
		Introduce numero: <input type="number" name="x" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	if($x > 0 && $x <= 50){
		$existe = false;
		$rutaArchivo = "files/numeros.txt";
		$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
		while(!feof($archivo)) {
			//posible error, vigilar
			if(fgets($archivo) === $x){
				$existe = true;
			}
		}
		fclose($archivo);
		
		if($existe){
			echo "<p>¡Ya habías introducido el número $x antes! Has logrado escribir $cnt números sin repetirte:</p>";
			$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
			while(!feof($archivo)) {
				echo " " . fgets($archivo);
			}
			fclose($archivo);
		}else{
			//probar con a+ si sobreescribe
			$archivo = fopen($rutaArchivo, "a+") or die("Imposible abrir el archivo para escritura");
			fwrite($archivo,"$x\n");
			fclose($archivo);
			$cnt++;
			echo "<p>Has introducido $cnt números.</p>";
		}
	}
	echo '<form action="Problema2-4.php" method="post">
		Introduce numero: <input type="number" name="x" />
		<input type="submit" name="enviar"></form>';
}
?>
</body>
</html>