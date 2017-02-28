<?php
/*  El formulario será tratado en esta 
 * misma página, y si las credenciales 
 * son correctas se iniciará sesión y 
 * se redirigirá automáticamente a index.php
 */
$mensajeError = "";
if(isset($_REQUEST['login'])){
	$servidor = "localhost";
	$usuario = "alumno";
	$clave = "alumno";
	if(isset($_REQUEST['user']) && isset($_REQUEST['pass'])){
		$user = $_REQUEST['user'];
		$pass = $_REQUEST['pass'];
	}
	// echo "$servidor, $usuario, $clave";
	
	$conexion = new mysqli($servidor,$usuario,$clave,"catalogo");
	$conexion->query("SET NAMES 'UTF8'");
	
	
	if ($conexion->connect_errno) {
		$mensajeError = "Error al establecer la conexión: " . $conexion->connect_errno . "-" . $conexion->connect_error;
		echo $mensajeError;
	}else{
		$consulta = "SELECT * FROM usuarios WHERE nombre LIKE '$user'";
		$resultado = $conexion -> query($consulta);
		if (mysqli_num_rows($resultado) = 1){
			$fila=$resultado->fetch_array(MYSQLI_ASSOC);
			while($fila!=null) {
				$userDetect = $fila['login'];
				$passDetect = $fila['password'];
			}
			if(strcmp($user, $userDetect) = 0 && strcmp($pass, $passDetect) = 0){
				$_SESSION['login'] = 1;
				$_SESSION['usuario'] = $user;
				header('Location: index.php');
			}
		}else{
			$mensajeError = "El usuario no existe o está erróneamente duplicado.";
			echo $mensajeError;
		}
		
		mysqli_close($conexion);
		
	}
}
?>
<html>
<h2>Darse de alta</h2>
<form action="login.php">
Usuario: <input type="text" name="user"><br>
Contraseña <input type="password" name="pass"><br>
<input type="submit" name="login" value="Dar de baja">
</form>

<a href="alta.php">¿aún no tienes cuenta? Haz clic aquí para crear una.</a>
</html>