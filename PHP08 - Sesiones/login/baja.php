<?php
// elimina el usuario de la base de datos y 
// redirige a logout.php para cerrar la sesión
if ($conexion->connect_errno) {
	$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
}
?>
<html>
<h2>Dar de baja a este usuario</h2>
<form action="baja.php">
Vuelve a introducir la contraseña <input type="password" name="pass">
<input type="submit" value="Dar de baja">
</form>
</html>