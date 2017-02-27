<?php
session_name('idSesionPHP08');
session_start ();
if (session_status () == PHP_SESSION_NONE) {
	$mensaje = "No hay sesión iniciada";
} else {
	if (isset ( $_SESSION ['contador'] )) {
		$_SESSION ['contador'] += 1;
	} else {
		$_SESSION ['contador'] = 1;
	}
	if(isset($_SESSION ['nombre'])){
		$mensaje = "Bienvenido " . $_SESSION ['nombre'];
	}
}

?>
<html>
<head>
<title>Sesiones</title>
<meta charset="UTF-8" />
</head>
<body>
	<h3>
	<?php 
	if(isset($mensaje)){
		echo $mensaje;
	}
	?></h3>
	<form action="saludo.php">
		<input type="text" name="nombre">
		<input type="submit" name="sesionnombre" value="logar">
		<?php 
			if(isset($_REQUEST['sesionnombre'])){
				$_SESSION ['nombre'] = $_REQUEST['nombre'];
				header("Location: saludo.php");
			}
		?>
	</form>
</body>
</html>
