<!DOCTYPE html>
<html><head><meta charset='UTF-8'/></head>
<body>
<h1>Lista</h1>
<?php
//ordenar modulos
$rutaArchivo = "files/personas.txt";
$archivo = fopen($rutaArchivo, 'a');
$lineasArchivo = file($rutaArchivo);
sort($lineasArchivo);
var_dump($lineasArchivo);
foreach ($lineasArchivo as $linea) {
	echo $linea;
}
fclose($archivo);

if(!isset($_POST['enviar']) || !isset($_POST['borrar'])){
?>
<form action="nombres.php" method="post">
<input type="text" name="nombre">
<input type="submit" name="enviar" value="enviar">
<input type="submit" name="borrar" value="borrar">
</form>
<?php 
}else{
	$nombre = $_POST['nombre'];
	if($_POST['enviar']){
		$archivo = fopen($rutaArchivo, 'a+');
		if(!exists($nombre)){
			fwrite($archivo, "$nombre\n");
		}else{
			echo "<p>ya existe</p>";
		}
		fclose($archivo);
	}
	if($_POST['borrar']){
		if(exists($nombre)){
			foreach ($lineasArchivo as $linea) {
				if(strcmp($nombre, $linea)){
					str_replace($nombre, "", $linea);
				}
			}
		}else{
			echo "<p>no existe</p>";
		}
	}
	echo '<form action="nombres.php" method="post">
<input type="text" name="nombre">
<input type="submit" name="enviar" value="enviar">
<input type="submit" name="borrar" value="borrar">
</form>';
	
}

function exists($string){
	$existe = false;
	foreach ($lineasArchivo as $linea) {
		if(strcmp($string, $linea)){
			$existe = true;
		}
	}
	return $existe;
}
?>
</body></html>
