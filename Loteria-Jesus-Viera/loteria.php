<html>
<head>
<title>Comprobar Lotería</title>
<meta charset="utf-8">
</head>
<body>
<?php 
if(!isset($_POST['enviar'])){
?>
<h2>Compruebe su lotería</h2>
<form action="loteria.php" method="post">
Numero: <input type="number" name="numero"></br>
Boletos comprados:<input type="number" name="numboletos"></br>
<input type="submit" name="enviar" value="Comprobar"/>
</form>
<?php 
}else{
	$numero = $_POST['numero'];
	$numBoletos = $_POST['numboletos'];
	if(is_numeric($numero) && is_numeric($numBoletos) && strlen($numero) == 5){
		$rutaArchivo = "files/loteria.txt";
		$tienePremio = false;
		$numeroPremiado = 0;
		$premio = 0;
		$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
		while(!feof($archivo)) {
			$linea = fgets($archivo);
			$bothnum = explode(' ', $linea);
			$resultado = strcmp($bothnum[0], $numero);
			//echo $resultado;
			if($resultado == 0){
				$tienePremio = true;
				$premio = $bothnum[1] * $numBoletos;
			}
		}
		fclose($archivo);
		if($tienePremio){
			echo "<p>Tiene premio!!! Con su número $numero, le han tocado $premio €!";
		}else{
			echo "<p>Lo sentimos mucho, pero no le ha tocado nada.";
		}
		reload_form("¿Tiene más boletos?");
	}else{
		echo "Compruebe que haya escrito bien los números.";
		reload_form("Vuelva a introducir los datos:");
	}
	
}

function reload_form($titulo) {
	echo '<h3>'.$titulo.'</h3><form action="loteria.php" method="post">
		Numero: <input type="number" name="numero"></br>
		Boletos comprados:<input type="number" name="numboletos"></br>
		<input type="submit" name="enviar" value="Comprobar"/>
		</form>';
	echo '<h3>actualizar índice</h3><form action="subir.php" method="post" enctype="multipart/form-data">
<label>Escoger archivo:</label>
<input type="file" name="archivo">
<input type="submit" value="Subir archivo" name="enviar">
</form>';
}

?>
<h3>Actualizar índice</h3>
<form action="subir.php" method="post" enctype="multipart/form-data">
<label>Escoger archivo:</label>
<input type="file" name="archivo">
<input type="submit" value="Subir archivo" name="enviararchivo">
</form>

</body>
</html>
