<?php
session_name('idSesionPHP08');
session_start ();
if (isset($_REQUEST["reiniciarContador"])) {
	unset($_SESSION["contador"]); // elige una
	$_SESSION=array(); // elige una
}
if (session_status () == PHP_SESSION_NONE) {
	$mensaje = "No hay sesión iniciada";
} else {
	if (isset ( $_SESSION ['contador'] )) {
		$_SESSION ['contador'] += 1;
	} else {
		$_SESSION ['contador'] = 1;
	}
	$mensaje = "Has visitado esta página " . $_SESSION ['contador'] . " veces en esta sesión.";
}
if (isset($_REQUEST["cerrarSesion"])) {
	$_SESSION=array();
	session_unset();
	if (ini_get("session.use_cookies")) {
		$params = session_get_cookie_params();
		setcookie(session_name(), '', time() - 42000,
				$params["path"], $params["domain"],
				$params["secure"], $params["httponly"]
				);
	}
	session_destroy();
}

?>
<html>
<head>
<title>Sesiones</title>
<meta charset="UTF-8" />
</head>
<body>
	<h3><?php echo $mensaje;?></h3>
	<p>
		<a href="<?php echo $_SERVER['PHP_SELF']?>">Recargar la página</a>
	</p>
	
	<p><a href="<?php echo $_SERVER['PHP_SELF']."?reiniciarContador=true"?>">Reiniciar contador</a></p>
	<p><a href="<?php echo $_SERVER['PHP_SELF']."?cerrarSesion=true"?>">Cerrar sesión</a></p>
	<form action="contador.php">
		<input type="number" name="cnt">
		<input type="submit" name="modificarcontador" value="añadir">
		<?php 
			if(isset($_REQUEST['modificarcontador'])){
				$_SESSION ['contador'] = $_REQUEST['cnt'];
				header("Location: contador.php");
			}
		?>
	</form>
</body>
</html>
