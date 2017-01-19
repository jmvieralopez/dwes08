<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/></head>
<body>

<?php


if(isset($_POST["enviararchivo"])) {

	$directorio = "files/";
	$archivo = $directorio . basename($_FILES["archivo"]["name"]);
	$error = false;

	// Comprobar extensiones
	$extension = pathinfo($archivo,PATHINFO_EXTENSION);
	$nombre = pathinfo($archivo,PATHINFO_FILENAME);
	echo "<h4>La extensión es $extension</h4>";
	if(!$extension == "txt") {
		echo "<h3>Error, el archivo es una imagen</h3>";
		$error = true;
	}
	// Subir el archivo
	if (!$error){
		if (move_uploaded_file($_FILES["archivo"]["tmp_name"], $archivo)) {
			echo "<h3>El archivo ". basename( $_FILES["archivo"]["name"]). " ha sido subido al servidor.</h3>";
		} else {
			echo "<h3>Ocurrió un error al intentar subir el archivo.</h3>";
		}
	}
}
?>
<!-- añadir ir atrás -->
