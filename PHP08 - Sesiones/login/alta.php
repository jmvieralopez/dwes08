<?php
// El formulario será tratado en esta misma página:
// si no hay errores añadirá el nuevo usuario a la
// base de datos y redirigirá a login.php
$mensajeError = "";
if(isset($_REQUEST['alta'])){
	$servidor = "localhost";
	$usuario = "alumno_rw";
	$clave = "dwes";
	if(isset($_REQUEST['user']) && isset($_REQUEST['pass'])){
		if(strlen($_REQUEST['user']) > 0){
			$user = $_REQUEST['user'];
			if(strlen($_REQUEST['pass']) > 0){
				$pass = $_REQUEST['pass'];
				$conexion = new mysqli($servidor,$usuario,$clave,"catalogo");
				$conexion->query("SET NAMES 'UTF8'");
				if ($conexion->connect_errno) {
					$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
					echo $mensajeError;
				}else{
					$consulta = "SELECT * FROM usuario WHERE login LIKE '$user'";
					$resultado = $conexion -> query($consulta);
					if (mysqli_num_rows($resultado) >= 1){
						$fila=$resultado->fetch_array(MYSQLI_ASSOC);
						$mensajeError = "El usuario ya existe.";
					}else{
						$nombre = $_REQUEST['nombre'];
						$admin = $_REQUEST['admin'];
						$descripcion = $_REQUEST['descripcion'];
						$insert = "INSERT INTO `usuario` VALUES ('$user', '$pass', '$nombre', '$admin', '$descripcion'); ";
						$conexion->query($insert);
						if (empty($conexion->error)){
							echo "<h2>Cuenta creada. Redirigiendo a inciar sesión...</h2>";
							header("refresh:2; url=login.php");
						}else{
							$mensajeError = $conexion->error;
						}
					}
				}
				mysqli_close($conexion);
			}else{
				$mensajeError = "Campo pass vacío";
			}
		}else{
			$mensajeError = "Campo user vacío";
		}
	}else{
		$mensajeError = "Campo vacío";
	}
	// echo "$servidor, $usuario, $clave";
	echo $mensajeError;
}
?>
<html>
<h2>Darse de alta</h2>
<form action="alta.php">
Usuario: <input type="text" name="user"><br>
Contraseña <input type="password" name="pass"><br>
Nombre: <input type="text" name="nombre"><br>
Admin?: <input type="radio" name="admin" value="1">Si<input type="radio" name="admin" value="0">No<br>
Descripción: <input type="text" name="descripcion"><br>
<input type="submit" name="alta" value="Darse de alta">
</form>
<br><a href="login.php">¿Ya tienes cuenta? Haz clic aquí para iniciar sesión.</a>
</html>