<html>
<head>
<title>Mostrar catálogo</title>
<meta charset="utf-8">
</head>
<body>
<?php
include 'Cancion.php';
if (!isset($_REQUEST["idCancion"])) die ("<h3>ERROR en la petición. Falta identificador de cancion</h3>");
$idCancion = $_REQUEST["idCancion"];

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
<table>
<tr bgcolor="lightblue">
<!-- <th>id</th> -->
<!-- <th>idArtista</th> -->
<th>canción</th>
<th>artista</th>
<th>album</th>
<th>caratula</th>
</tr>
<?php
$resultado = $conexion -> query("SELECT * FROM canciones WHERE id=$idCancion");
/*while ($animal = $resultado->fetch_object('Animal')) {
	echo $animal."<br/>";
}*/

while ($cancion = $resultado->fetch_object('Cancion')) {
	$resultado2 = $conexion -> query("SELECT * FROM artista");
	// echo print_r($cancion);
	echo "<tr bgcolor='lightgreen'>";
// 	echo "<td>".$cancion->getId()."</td>\n";
/*	$fila=$resultado2->fetch_array(MYSQLI_ASSOC);
	while ($fila!=null){
		if($fila['id'] == $cancion->getIdArtista() ){
			echo "<td>".$fila['nombre']."</td>\n";
		}
		$fila=$resultado2->fetch_array(MYSQLI_ASSOC);
	}
	*/
// 	echo "<td>".$cancion->getIdArtista()."</td>\n";
	echo "<td>".$cancion->getCancion()."</td>\n";
	echo "<td>".$cancion->getArtista()."</td>\n";
	echo "<td>".$cancion->getAlbum()."</td>\n";
	echo "<td><img width='100' height='100' src='./img/".$cancion->getCaratula()."'</img></td>\n";
	
	echo "</tr>";
	//mysqli_free_result($resultado2);
}

mysqli_close($conexion);
?>

</table>

<a href="./mostrarCatalogo.php">volver</a>

</body>
</html>