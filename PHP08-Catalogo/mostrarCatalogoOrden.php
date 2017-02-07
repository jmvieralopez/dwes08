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
<!-- <th>id</th> -->
<!-- <th>idArtista</th> -->
<th>canción <a href="mostrarCatalogoOrden.php?campo=cancion&orden=a">▲</a> <a href="mostrarCatalogoOrden.php?campo=cancion&orden=d">▼</a></th>
<th>artista <a href="mostrarCatalogoOrden.php?campo=artista&orden=a">▲</a> <a href="mostrarCatalogoOrden.php?campo=artista&orden=d">▼</a></th>
<th>album</th>
<th>carátula</th>
</tr>
<?php
/*while ($animal = $resultado->fetch_object('Animal')) {
	echo $animal."<br/>";
}*/
/*
if ((!isset($_REQUEST["campo"])) && (!isset($_REQUEST["orden"]))){
	$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `id`");
}else{
	$campo = $_REQUEST["campo"];
	$orden = $_REQUEST["orden"];
	if(strcmp($campo, "cancion")){
		if(strcmp($orden, "a")){
			$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `cancion` ASC");
		}else{
			if(strcmp($orden, "d")){
				$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `cancion` DESC");
			}else{
				echo "ERROR: cabecera incorrecta";
			}
		}
			
	} else if(strcmp($campo, "artista")){
		if(strcmp($orden, "a")){
			$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `artista` ASC");
		}else if(strcmp($orden, "d")){
			$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `artista` DESC");
		}else{
			echo "ERROR: cabecera incorrecta";
		}
	}else{
		echo "ERROR: cabecera incorrecta";
	}
}
*/

/*
if ((!isset($_REQUEST["campo"])) && (!isset($_REQUEST["orden"]))){
	$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `id`");
}else{
	$campo = $_REQUEST["campo"];
	$orden = $_REQUEST["orden"];
	if(strcmp($campo, "cancion") && strcmp($orden, "a")){
		$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `cancion` ASC");
	}
	if(strcmp($campo, "cancion") && strcmp($orden, "d")){
		$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `cancion` DESC");
	}
	if(strcmp($campo, "artista") && strcmp($orden, "a")){
		$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `canciones`.`artista` ASC");
	}
	if(strcmp($campo, "artista") && strcmp($orden, "d")){
		$resultado = $conexion -> query("SELECT * FROM `canciones` ORDER BY `canciones`.`artista` DESC");
	}
}
*/
$consulta = "";

if ((!isset($_REQUEST["campo"])) && (!isset($_REQUEST["orden"]))){
	$consulta = "SELECT * FROM `canciones` ORDER BY `id`";
}else{
	$campo = $_REQUEST["campo"];
	$orden = $_REQUEST["orden"];
	/*
	if(strcmp($campo, "cancion")==0) echo "<p>El campo es cancion</p>";
	if(strcmp($campo, "artista")==0) echo "<p>El campo es artista</p>";
	*/
	if(strcmp($campo, "cancion")==0 && strcmp($orden, "a")==0){
		echo "<p>Entra en 1</p>";
		$consulta = "SELECT * FROM canciones ORDER BY cancion ASC";
	}
	if(strcmp($campo, "cancion")==0 && strcmp($orden, "d")==0){
		echo "<p>Entra en 2</p>";
		$consulta = "SELECT * FROM canciones ORDER BY cancion DESC";
	}
	if(strcmp($campo, "artista")==0 && strcmp($orden, "a")==0){
		$consulta = "SELECT * FROM canciones ORDER BY artista ASC";
		echo "<p>Entra en 3</p>";
		}
	if(strcmp($campo, "artista")==0 && strcmp($orden, "d")==0){
		$consulta = "SELECT * FROM canciones ORDER BY artista DESC";
		echo "<p>Entra en 4</p>";
		}
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
//	echo "<td>".$cancion->getId()."</td>\n";
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
	echo "<td><img width='100' height='100' src='./img/".$cancion->getCaratula()."'</img></td>\n";
	echo "</tr>";
}
mysqli_free_result($resultado);
mysqli_close($conexion);
?>
</table>
</body>
</html>
