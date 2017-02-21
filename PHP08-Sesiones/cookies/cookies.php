<html>
<head>
<title>Cookies y sesiones</title>
<meta charset="UTF-8"/>
</head>
<body>
<?php
setcookie("test", "test", time() + 3600, '/');
if(count($_COOKIE) ==0) echo "<h3>Advertencia: tu navegador tiene las cookies deshabilitadas. Esta aplicación no funcionará</h3>";
if(isset($_POST["enviar"])) {
	//setcookie("visitante", $_POST["nombre"], time() + (86400 * 15), "/"); // 86400 = segundos en 1 día
	//setcookie("visitante", $_POST["nombre"], time() + (15), "/"); // 86400 = segundos en 1 día
	setcookie("visitante", $_POST["nombre"], time() + (15), "/PHP08-Sesiones/"); // 86400 = segundos en 1 día
}
if (isset($_POST["eliminar"])) {
	// 	setcookie("visitante", "nulo", time() - 1, "/");
	setcookie("visitante", "nulo", time() - 1, "/PHP08-Sesiones/");
}
if(isset($_COOKIE["visitante"])) { 
	echo "<h2>Damos la bienvenida a $_COOKIE[visitante]</h2>";
	echo "<form action='".$_SERVER['PHP_SELF']."' method='post'>";
    echo "<input type='submit' value='Eliminar' name='eliminar'>";
	echo "</form>";
}
else
{ // solicitar nombre al usuario
?>
<form action="<?php echo $_SERVER['PHP_SELF']?>" method="post">
    <label>Escribe tu nombre para dirigirnos a ti:</label>
    <input type="text" name="nombre"><br/>
    <input type="submit" value="Enviar" name="enviar">
    <input type="submit" value="Eliminar" name="eliminar">
</form>
<?php
}
?>
<p><a href="<?php echo $_SERVER['PHP_SELF']?>">Enlace a esta misma página</a></p>
</body></html>
