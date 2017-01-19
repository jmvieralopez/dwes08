<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"/></head>
<body>

<form action="subir.php" method="post" enctype="multipart/form-data">
<label>Escoger archivo:</label>
<input type="file" name="archivo">
<input type="submit" value="Subir archivo" name="enviar">
</form>

<?php


if(isset($_POST["enviar"])) {

	$directorio = "upload/";
	$archivo = $directorio . basename($_FILES["archivo"]["name"]);
	$error = false;

	// Comprobar si ya existe
	if (file_exists($archivo)) {
		echo "<h3>Error, ya se ha subido ese archivo</h3>";
		$error = true;
	}

	// Comprobar extensiones
	$extension = pathinfo($archivo,PATHINFO_EXTENSION);
	echo "<h4>La extensión es $extension</h4>";
	if($extension == "jpg" || $extension == "png"
			|| $extension == "jpeg"	|| $extension == "gif" ) {
				echo "<h3>Error, el archivo es una imagen</h3>";
				$error = true;
			}

			// Comprobar tamaño de archivo
			$tamaño =$_FILES["archivo"]["size"];
			echo "<h4>El tamaño del archivo es $tamaño bytes</h4>";
			if ( $tamaño > 1000000) {
				echo "<h3>Error, el archivo es demasiado grande</h3>";
				$error = true;
			}

			// Subir el archivo
			if (!$error)
			{
				if (move_uploaded_file($_FILES["archivo"]["tmp_name"], $archivo)) {
					echo "<h3>El archivo ". basename( $_FILES["archivo"]["name"]). " ha sido subido al servidor.</h3>";
				} else {
					echo "<h3>Ocurrió un error al intentar subir el archivo.</h3>";
				}
			}

}
?>
