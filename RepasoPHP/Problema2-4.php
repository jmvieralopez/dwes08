<html>
<body>
<h2>Problema 3</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="Problema2-4.php" method="post">
		Introduce numero: <input type="number" name="x" />
		<input type="hidden" name="cnt" value="0"/>
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	$cnt = $_POST['cnt'];
	echo gettype($x);
	if($x > 0 && $x <= 50){
		$existe = false;
		$rutaArchivo = "files/numeros.txt";
		$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
		while(!feof($archivo)) {
			//posible error, vigilar
			$resultado = strcmp(fgets($archivo), $x."\n");
			echo $resultado;
			if($resultado == 0){
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
			//vaciar archivo
			$archivo = fopen($rutaArchivo, "w+") or die("Imposible abrir el archivo para escritura");
			fwrite($archivo,"");
			fclose($archivo);
			$cnt = 0;
			echo "<h2>¡Juega otra vez!</h2>";
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
		<input type="hidden" name="cnt" value="'.$cnt.'"/>
		<input type="submit" name="enviar"></form>';
}
?>
</body>
</html>