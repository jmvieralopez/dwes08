<?php
// El formulario será tratado en esta misma página:
// si no hay errores añadirá el nuevo usuario a la
// base de datos y redirigirá a login.php
if ($conexion->connect_errno) {
	$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
}
?>
<html>
<h2>Darse de alta</h2>
<form action="alta.php">
Usuario: <input type="text" name="user"><br>
Contraseña <input type="password" name="pass"><br>
<input type="submit" value="Dar de baja">
</form>
</html>