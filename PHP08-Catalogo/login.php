<html>
<head>
<title>Login</title>
<meta charset="utf-8">
</head>
<body>
<?php 
include 'constants.php';
if(!isset($_POST['entrar'])){
?>
<form action="login.php" method="post">
<label>Usuario</label><input type="text" name="user"/><br>
<label>Contraseña</label><input type="password" name="pass"/><br>
<input type="submit" name="entrar" value="entrar">
</form>
<?php
}else{
	$servidor = "localhost";
	$usuario = $_POST['user'];
	$clave = $_POST['pass'];
	
	$conexion = new mysqli($servidor,$usuario,$clave,"catalogo");
	$conexion->query("SET NAMES 'UTF8'");
	
	if ($conexion->connect_errno) {
		echo "<p>Error al establecer la conexión (" . $conexion->connect_errno . ") " . $conexion->connect_error . "</p>";
	}else{
		header('Location: mostrarCatalogo.php');
	}
}
?>
</body>
</html>