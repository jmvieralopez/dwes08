<html>
<head>
<title>Mostrar catálogo</title>
<meta charset="utf-8">
</head>
<body>
<?php
include 'Cancion.php';
$servidor = "localhost";
$usuario = "alumno";
$clave = "alumno";

$conexion = new mysqli($servidor,$usuario,$clave,"catalogo");
$conexion->query("SET NAMES 'UTF8'");
//si quisiéramos hacerlo en dos pasos:
// $conexion = new mysqli($servidor,$usuario,$clave);
// $conexion->select_db("animales");

if ($conexion->connect_errno) {
	echo "<p>Error al establecer la conexión (" . $conexion->connect_errno . ") " . $conexion->connect_error . "</p>";
}
echo "<p>A continuación mostramos algunos registros:</p>";
?>
<table style="width:50%">

<tr bgcolor="lightblue">
<th>id</th>
<th>idArtista</th>
<th>canción</th>
<th>artista</th>
<th>album</th>
</tr>
<?php
$resultado = $conexion -> query("SELECT * FROM canciones ORDER BY cancion");
/*while ($animal = $resultado->fetch_object('Animal')) {
	echo $animal."<br/>";
}*/

while ($cancion = $resultado->fetch_object('Cancion')) {
	// echo $animal."<br/>"; // primer intento más sencillo
	// echo print_r($cancion);
	echo "<tr bgcolor='lightgreen'>";
	echo "<td>".$cancion->getId()."</td>\n";
	
	// ENLACE CON LA TABLA ARTISTA
	$resultado2 = $conexion -> query("SELECT * FROM artista");
	$fila=$resultado2->fetch_array(MYSQLI_ASSOC);
	while ($fila!=null){
		if($fila['id'] == $cancion->getIdArtista() ){
			echo "<td>".$fila['nombre']."</td>\n";
		}
		$fila=$resultado2->fetch_array(MYSQLI_ASSOC);
	}
	mysqli_free_result($resultado2);
	
	
//	echo "<td>".$cancion->getIdArtista()."</td>\n";
	echo "<td>".$cancion->getCancion()."</td>\n";
//	echo "<a href='./mostrarObra.php?idCancion=".$cancion->getId()."'>".$cancion->getCancion()."</a>\n";
//	echo "<td>".$cancion->getArtista()."</td>\n";
	echo "<td>".$cancion->getAlbum()."</td>\n";
	echo "</tr>";
}

// mysqli_close($conexion);
?>
</table>
</body>
</html>
