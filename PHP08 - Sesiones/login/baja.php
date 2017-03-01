<?php
// elimina el usuario de la base de datos y 
// redirige a logout.php para cerrar la sesión
$mensajeError="";
$loggedin = $_SESSION['login'];
$user = $_SESSION['usuario'];
if(isset($_REQUEST['baja'])){
	$servidor = "localhost";
	$usuario = "alumno_rw";
	$clave = "dwes";
	if(isset($_REQUEST['pass'])){
			if(strlen($_REQUEST['pass']) > 0 ){
				$pass = $_REQUEST['pass'];
				$conexion = new mysqli($servidor,$usuario,$clave,"catalogo");
				$conexion->query("SET NAMES 'UTF8'");
				if ($conexion->connect_errno) {
					$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
					echo $mensajeError;
				}else{
					$consulta = "SELECT * FROM usuarios WHERE nombre LIKE '$user'";
					$resultado = $conexion -> query($consulta);
					if (mysqli_num_rows($resultado) >= 1){
						$fila=$resultado->fetch_array(MYSQLI_ASSOC);
						while($fila!=null) {
							$userDetect = $fila['login'];
							$passDetect = $fila['password'];
						}
						if(strcmp($user, $userDetect) = 0 && strcmp($pass, $passDetect) = 0){
							$insert = "DELETE FROM `usuario` WHERE `usuario`.`login` = '$user'; ";
							$conexion->query($insert);
							if (empty($conexion->error)){
								header('Location: logout.php');
							}else{
								$mensajeError = $conexion->error;
							}
						}else{
							$mensajeError = "";
						}
					}else{
						$mensajeError = "El usuario no existe";
					}
				}
				mysqli_close($conexion);
			}else{
				$mensajeError = "Campo pass vacío";
			}
	}else{
		$mensajeError = "Campo vacío";
	}
	// echo "$servidor, $usuario, $clave";
	echo $mensajeError;
}

?>
<html>
<h2>Dar de baja a su usuario</h2>
<form action="baja.php">
Vuelve a introducir la contraseña <input type="password" name="pass">
<input type="submit" name="baja" value="Dar de baja">
</form>
</html>