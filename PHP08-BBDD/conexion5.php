<html>
<head>
<title>Conexión a BBDD con PHP</title>
<meta charset="UTF-8"/>
</head>
<body>
<h2>Pruebas con la base de datos de animales</h2>
<?php
$servidor = "localhost";
$usuario = "alumno_rw";
$clave = "dwes";
?>
<p> Vamos a utilizar las siguientes variables:</p>
<ul>
<?php
echo "<li>Nombre del servidor al que nos vamos a conectar a MySQL: $servidor</li>";
echo "<li>Nombre de usuario con el que nos vamos a conectar a MySQL: $usuario</li>";
echo "<li>Contraseña del usuario en MySQL: $clave</li>";
$conexion = new mysqli($servidor,$usuario,$clave,"animales");
$conexion->query("SET NAMES 'UTF8'");
//si quisiéramos hacerlo en dos pasos:
// $conexion = new mysqli($servidor,$usuario,$clave);
// $conexion->select_db("animales");

if ($conexion->connect_errno) {
	echo "<p>Error al establecer la conexión (" . $conexion->connect_errno . ") " . $conexion->connect_error . "</p>";
}

?>
<?php
echo "<h2>Listado de cuidadores</h2>";
echo "<h3>Pulsa en cada cuidador para ver los animales de los que se ocupa</h3>";

$resultado = $conexion-> query("SELECT * FROM cuidador");
echo "<ul>\n";
$fila=$resultado->fetch_array(MYSQLI_ASSOC);
while($fila!=null) {
	echo "<li><a href='cuidador.php?idCuidador=$fila[idCuidador]'>$fila[Nombre]</a></li>\n";
	// Ejemplo: <li><a href='cuidador.php?idCuidador=12345'>Alberto</a></li>
	$fila=$resultado->fetch_array(MYSQLI_ASSOC);
}
echo "</ul>";
?>
<?php 
echo "<h3>Desconectando...</h3>";
mysqli_close($conexion);
?>
<a href="index.html">Indice</a>

</body>
</html>