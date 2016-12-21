<!DOCTYPE html>
<html><head><meta charset='UTF-8'/></head>
<body>
<h1>Lista</h1>
<?php
/*

------------------------------------

		TERMINAR!!!!!!!!!

------------------------------------

*/
//ordenar modulos
$rutaArchivo = "files/personas.txt";
$archivo = fopen($rutaArchivo, 'a');
fclose($archivo);
$lineasArchivo = file($rutaArchivo);
sort($lineasArchivo);
var_dump($lineasArchivo);
foreach ($lineasArchivo as $linea) {
	echo $linea;
}
//if(!isset($_POST['enviar']) || !isset($_POST['borrar'])){
?>
<form action="nombres.php" method="post">
<input type="text" name="nombre">
<input type="submit" name="enviar" value="enviar">
<input type="submit" name="borrar" value="borrar">
</form>
<?php 
//}else{
	if(isset($_POST['enviar'])){
		$nombre = $_POST['nombre'];
		
		echo "<p>Click en enviar</p>";
		$archivo = fopen($rutaArchivo, 'a+');
		if(!exists($nombre, $lineasArchivo)){
			echo "<p>Enviando</p>";
			fwrite($archivo, "$nombre\n");
		}else{
			echo "<p>ya existe</p>";
		}
		fclose($archivo);
		//echoNewForm();
	}
	if(isset($_POST['borrar'])){
		$nombre = $_POST['nombre'];
		
		echo "<p>Click en borrar</p>";
		if(exists($nombre, $lineasArchivo)){
			echo "<p>Borrando</p>";
			foreach ($lineasArchivo as $linea) {
				if(strcmp($nombre, $linea)){
					str_replace($nombre, "", $linea);
				}
			}
		}else{
			echo "<p>no existe</p>";
		}
		//echoNewForm();
	}
	
	function echoNewForm(){
		echo '<form action="nombres.php" method="post">
<input type="text" name="nombre">
<input type="submit" name="enviar" value="enviar">
<input type="submit" name="borrar" value="borrar">
</form>';
	}
	
//}

// echo 'Enviar está en '.var_dump($_POST['enviar']);
// echo 'Borrar está en '.var_dump($_POST['borrar']);


function exists($string, $lineasArchivo){
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
