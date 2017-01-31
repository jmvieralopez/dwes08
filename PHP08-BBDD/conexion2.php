<html>
<head>
<title>Conexión a BBDD con PHP</title>
<meta charset="UTF-8"/>
</head>
<body>
<h2>Pruebas con la base de datos de animales</h2>
<?php
$servidor = "localhost";
$usuario = "alumno";
$clave = "alumno";
?>
<p> Vamos a utilizar las siguientes variables:</p>
<ul>
<?php
echo "<li>Nombre del servidor al que nos vamos a conectar a MySQL: $servidor</li>";
echo "<li>Nombre de usuario con el que nos vamos a conectar a MySQL: $usuario</li>";
echo "<li>Contraseña del usuario en MySQL: $clave</li>";
?>
</ul>
<?php
$conexion = new mysqli($servidor,$usuario,$clave,"animales");
$conexion->query("SET NAMES 'UTF8'");
//si quisiéramos hacerlo en dos pasos:
// $conexion = new mysqli($servidor,$usuario,$clave);
// $conexion->select_db("animales");

if ($conexion->connect_errno) {
	echo "<p>Error al establecer la conexión (" . $conexion->connect_errno . ") " . $conexion->connect_error . "</p>";
}
echo "<p>A continuación mostramos algunos registros:</p>";
$resultado = $conexion -> query("SELECT * FROM animal ORDER BY nombre");
$fila=$resultado->fetch_array(MYSQLI_ASSOC);
while($fila!=null) {
	echo "<hr>";
	echo "Nombre:" . $fila['nombre'];
	echo "<br>Especie: $fila[especie]"; // observa la diferencia en el uso de comillas
	$fila=$resultado->fetch_array(MYSQLI_ASSOC);
}
mysqli_free_result($resultado);
echo "<p>A continuación mostramos los nombres de los cuidadores:</p>";
$resultado = $conexion -> query("SELECT * FROM cuidador ORDER BY nombre");
$fila=$resultado->fetch_array(MYSQLI_ASSOC);
while($fila!=null) {
	echo "<hr>";
	echo "Nombre:" . $fila['Nombre'];
	$fila=$resultado->fetch_array(MYSQLI_ASSOC);
}

echo "<h3>Desconectando...</h3>";
mysqli_close($conexion);
?>
</body>
</html>
