<html>
<head>
<title>Mostrar catálogo por autor</title>
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

if ($conexion->connect_errno) {
	echo "<p>Error al establecer la conexión (" . $conexion->connect_errno . ") " . $conexion->connect_error . "</p>";
}
echo "<p>A continuación mostramos algunos registros:</p>";
?>
<table style="width:50%">

<tr bgcolor="lightblue">
<th>id</th>
<!-- <th>idArtista</th> -->
<th>canción</th>
<th>artista</th>
<th>album</th>
</tr>
<?php
$consulta = "";

if (!isset($_REQUEST["autor"])){
	$consulta = "SELECT * FROM `canciones` ORDER BY `id`";
}else{
	$autor = $_REQUEST["autor"];
	$consulta = "SELECT * FROM canciones WHERE artista LIKE '$autor'";
}
/*
echo print_r($_REQUEST);
echo "<br>";
echo print_r($consulta);*/
$resultado = $conexion -> query($consulta);

while ($cancion = $resultado->fetch_object('Cancion')) {
	// echo $animal."<br/>"; // primer intento más sencillo
	// echo print_r($cancion);
	echo "<tr bgcolor='lightgreen'>";
	echo "<td>".$cancion->getId()."</td>\n";
	/*
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
	*/
	
//	echo "<td>".$cancion->getIdArtista()."</td>\n";
	echo "<td>".$cancion->getCancion()."</td>\n";
//	echo "<a href='./mostrarObra.php?idCancion=".$cancion->getId()."'>".$cancion->getCancion()."</a>\n";
	echo "<td>".$cancion->getArtista()."</td>\n";
	echo "<td>".$cancion->getAlbum()."</td>\n";
	echo "</tr>";
}
echo "<a href='mostrarCatalogo.php'>Eliminar filtros</a>";

mysqli_free_result($resultado);
mysqli_close($conexion);
?>

</table>
</body>
</html>
