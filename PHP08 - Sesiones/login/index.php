<?php
/* simulará el contenido real de nuestra 
 * aplicación, sólo disponible para usuarios 
 * autenticados (en esta práctica será 
 * simplemente un saludo al usuario 
 * autenticado). Si un usuario accede a 
 * esta página sin estar autenticado, 
 * será redirigido de forma automática a login.php
 */
$mensajeError="";
$loggedin = $_SESSION['login'];
$user = $_SESSION['usuario'];
if($loggedin != 1){
	header('Location: login.php');
}
?>
<html>
<head>
<title>Mostrar catálogo</title>
<meta charset="utf-8">
</head>
<body>
<?php
include 'Cancion.php';
include 'constants.php';

if(!isset($servidor) && !isset($usuario) && !isset($clave)){
	$servidor = "localhost";
	$usuario = "alumno";
	$clave = "alumno";
}
echo "$servidor, $usuario, $clave";

$conexion = new mysqli($servidor,$usuario,$clave,"catalogo");
$conexion->query("SET NAMES 'UTF8'");
//si quisiéramos hacerlo en dos pasos:
// $conexion = new mysqli($servidor,$usuario,$clave);
// $conexion->select_db("animales");

if ($conexion->connect_errno) {
	echo "<p>Error al establecer la conexión (" . $conexion->connect_errno . ") " . $conexion->connect_error . "</p>";
}

$consulta = "SELECT * FROM usuarios WHERE nombre LIKE '$user'";
$resultado = $conexion -> query($consulta);
if (mysqli_num_rows($resultado) = 1){
	$fila=$resultado->fetch_array(MYSQLI_ASSOC);
	while($fila!=null) {
		$userDetect = $fila['login'];
		$passDetect = $fila['password'];
	}
	if(strcmp($user, $userDetect) = 0 && strcmp($pass, $passDetect) = 0){
		$_SESSION['login'] = 1;
		$_SESSION['usuario'] = $user;
		header('Location: index.php');
	}
}else{
	$mensajeError = "El usuario no existe o está erróneamente duplicado.";
	echo $mensajeError;
}

echo "<h2>Catalogo</h2>";
?>

<form method="get" action="mostrarCatalogo.php">
<h3>Buscar por canción</h3>
<input type="text" name="titulo">
<input type="submit" value="Buscar">
</form>

<table style="width:50%">

<tr bgcolor="lightblue">
<!-- <th>id</th> -->
<!-- <th>idArtista</th> -->
<th>canción</th>
<th>artista</th>
<!-- <th>album</th> -->
<!-- <th>caratula</th> -->
</tr>
<?php
$consulta="";
if(isset($_GET["titulo"])){
	$cancion = $_GET["titulo"];
	$consulta = "SELECT * FROM canciones WHERE cancion LIKE '$cancion'";
}else{
	$consulta = "SELECT * FROM canciones ORDER BY cancion";
}

$resultado = $conexion -> query($consulta);
/*while ($animal = $resultado->fetch_object('Animal')) {
	echo $animal."<br/>";
}*/

while ($cancion = $resultado->fetch_object('Cancion')) {
	// echo $animal."<br/>"; // primer intento más sencillo
	// echo print_r($cancion);
	echo "<tr bgcolor='lightgreen'>";
//	echo "<td>".$cancion->getId()."</td>\n";
	
	// ENLACE CON LA TABLA ARTISTA
	$resultado2 = $conexion -> query("SELECT * FROM artistas");
/*	$fila=$resultado2->fetch_array(MYSQLI_ASSOC);
	while ($fila!=null){
		if($fila['id'] == $cancion->getIdArtista() ){
			echo "<td>".$fila['nombre']."</td>\n";
		}
		$fila=$resultado2->fetch_array(MYSQLI_ASSOC);
	}
	mysqli_free_result($resultado2);
	*/
	
//	echo "<td>".$cancion->getIdArtista()."</td>\n";
	echo "<td><a href='./mostrarObra.php?idCancion=".$cancion->getId()."'>".$cancion->getCancion()."</a></td>\n";
//	echo "<td>".$cancion->getArtista()."</td>\n";
	echo "<td><a href='mostrarCatalogoAutor.php?autor=".$cancion->getArtista()."'>".$cancion->getArtista()."</a></td>\n";
// 	echo "<td>".$cancion->getAlbum()."</td>\n";
// 	echo "<td><img width='100' height='100' src='./img/".$cancion->getCaratula()."'</img></td>\n";
	echo "</tr>";
}

mysqli_close($conexion);

?>


</table>
<?php
if(isset($_GET["titulo"])){
	echo "<a href='mostrarCatalogo.php'>Eliminar filtros</a>";
}
?>
</body>
</html>
