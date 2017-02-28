<?php
// cierra la sesión y redirige de forma 
// automática a login.php
if ($conexion->connect_errno) {
	$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
}
?>