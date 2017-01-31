<html>
<head>
<title>Conexión a BBDD con PHP</title>
<meta charset="UTF-8"/>
</head>
<body>
<h2>Pruebas con la base de datos de animales</h2>
<?php
include 'Animal.php';
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
?>
<table border="0">
<tr bgcolor="lightblue">
<th>Chip</th>
<th>Nombre</th>
<th>Especie</th>
<th>Imagen</th>
</tr>
<?php
$resultado = $conexion -> query("SELECT * FROM animal ORDER BY nombre");
/*while ($animal = $resultado->fetch_object('Animal')) {
	echo $animal."<br/>";
}*/
while ($animal = $resultado->fetch_object('Animal')) {
	// echo $animal."<br/>"; // primer intento más sencillo
	echo "<tr bgcolor='lightgreen'>";
	echo "<td>".$animal->getChip()."</td>\n";
	echo "<td>".$animal->getNombre()."</td>\n";
	echo "<td>".$animal->getEspecie()."</td>\n";
	echo "<td>".$animal->getImagen()."</td>\n";
	echo "</tr>";
}

?>
</table>
<?php 
echo "<h3>Desconectando...</h3>";
mysqli_close($conexion);
?>
</body>
</html>
