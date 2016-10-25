<?php
if(!isset($_POST['enviar'])){
	var_dump($_POST);
	?>

<form action="form.php" method="post">
	Introduce tu nombre: <input type="text" name="nombre" />
	Introduce tu clase/especie/tipo de personaje: <input type="text" name="clase" />
	<input type="submit" name="enviar">
</form>
<?php }
else{
	var_dump($_POST);
	echo "<h2>Yo te saludo, ".$_POST['nombre'].", el/la ".$_POST['clase'];
}
?>