<?php
// elimina el usuario de la base de datos y 
// redirige a logout.php para cerrar la sesión
$mensajeError="";
session_start();
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
				//echo "conectado!";
				if ($conexion->connect_errno) {
					$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
					//echo $mensajeError;
				}else{
					$consulta = "SELECT * FROM usuario WHERE login LIKE '$user'";
					$resultado = $conexion -> query($consulta);
					if (mysqli_num_rows($resultado) >= 1){
						$fila=$resultado->fetch_array(MYSQLI_ASSOC);
						//while($fila!=null) {
							$userDetect = $fila['login'];
							$passDetect = $fila['password'];
							//echo $userDetect." ".$passDetect;
						//}
						if(strcmp($user, $userDetect) == 0 && strcmp($pass, $passDetect) == 0){
							$delete = "DELETE FROM usuario WHERE login LIKE '$user'";
							$conexion->query($delete);
							if (empty($conexion->error)){
								echo "<h2>Cuenta eliminada. Redirigiendo...</h2>";
								//header('Location: logout.php');
								header("refresh:2; url=logout.php");
							}else{
								$mensajeError = $conexion->error;
							}
						}else{
							$mensajeError = "Ha escrito mal la contraseña, o ha habido un error interno en la sesión.";
						}
					}else{
						$mensajeError = "El usuario no existe";
					}
				}
			}else{
				$mensajeError = "Campo pass vacío";
			}
	}else{
		$mensajeError = "Campo vacío";
	}
	// echo "$servidor, $usuario, $clave";
	echo $mensajeError;
	mysqli_close($conexion);
}

?>
<html>
<h2>Dar de baja a su usuario</h2>
<form action="baja.php" method="post">
Vuelve a introducir la contraseña <input type="password" name="pass">
<input type="submit" name="baja" value="Dar de baja">
</form>
</html>