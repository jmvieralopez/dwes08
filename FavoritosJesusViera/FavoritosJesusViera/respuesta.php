<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<h1>Obras favoritas</h1>
<?php
if (isset ( $_POST ['enviar'] )) {
	
	$pelis = $_POST ['pelis'];
	$series = $_POST ['series'];
	$canciones = $_POST ['canciones'];
	$libros = $_POST ['libros'];
	$array_pelis = explode ( ";", $pelis );
	$array_series = explode ( ";", $series );
	$array_canciones = explode ( ";", $canciones );
	$array_libros = explode ( ";", $libros );
}

// Generación del formulario en PHP
if (! isset ( $_POST ['enviar2'] )) {
	?>
	<form action="respuesta.php" method="post" name="enviar2">
	<?php
	echo "<p>Rellenar todos los campos</p>";
	echo "Usuario: <input type='text' name='usuario'><br>";
	echo "Película: <select name='pelicula'>";
	foreach ( $array_pelis as $peli ) {
		echo "<option value='$peli" . "'>$peli</option>";
	}
	echo "</select><br>";
	echo "Series: <select size='".sizeof($array_series)."' name='series'>";
	foreach ( $array_series as $serie ) {
		echo "<option value='$serie" . "'>$serie</option>";
	}
	echo "</select><br>";
	echo "Canciones: <br>";
	foreach ( $array_canciones as $cancion ) {
		echo '<input type="radio" name="cancion" value="' . $cancion . '">' . $cancion . '</br>';
	}
	echo "Libros: <br>";
	foreach ( $array_libros as $libro ) {
		echo '<input type="checkbox" name="libro[]" value="' . $libro . '">' . $libro . '</br>';
	}
	echo '<input type="submit" value="Enviar" name="enviar2">';
} else {
	#una vez llamado a respuesta.php
	if(isset($_POST['usuario'])){
		$usuario = $_POST['usuario'];
	}
	if(isset($_POST['pelicula'])){
		$pelifav = $_POST['pelicula'];		
	}
	if(isset($_POST['series'])){
		$seriefav = $_POST['series'];
	}
	if(isset($_POST['cancion'])){
		$cancionfav = $_POST['cancion'];		
	}
	if(isset($_POST['libro'])){
		$librosfav = $_POST['libro'];
	}
	$vacios = empty($usuario) || empty($pelifav) || empty($seriefav) || empty($cancionfav) || empty($librosfav);
	//var_dump($librosfav);
	//if(!(strlen($pelifav) == 0 && strlen($seriefav) == 0 && strlen($cancionfav) == 0 && strlen($librosfav) == 0) && !(is_null($pelifav)) && !(is_null($seriefav)) && !(is_null($cancionfav)) && !(is_null($librosfav))){
	if(!$vacios){
		echo "<h3>Obras favoritas de $usuario";
		echo "<table>";
		echo "<tr><td>Película</td> <td>$pelifav</td></tr>";
		echo "<tr><td>Serie</td><td>$seriefav</td></tr>";
		echo "<tr><td>Cancion</td> <td>$cancionfav</td></tr>";
		echo "<tr><td>Libro</td><td>";
		foreach ($librosfav as $librofav) {
			echo "$librofav,";
		}		
		echo "</td></tr></table>";
	}else{
		echo "Falta por rellenar algún campo";
	}
}
?>
	</form>
</body>
</html>