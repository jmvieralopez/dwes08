<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1>Obras favoritas</h1>
<p>Separar las obras por punto y coma</p>
 <?php //if (!isset($_POST['enviar'])){
 ?>
<form action="respuesta.php" method="post">
<fieldset>
<legend>Películas</legend>
<textarea rows="6" cols="30" name="pelis"></textarea>
</fieldset>
<fieldset>
<legend>Series</legend>
<textarea rows="6" cols="30" name="series"></textarea>
</fieldset>
<fieldset>
<legend>Canciones</legend>
<textarea rows="6" cols="30" name="canciones"></textarea>
</fieldset>
<fieldset>
<legend>Libros</legend>
<textarea rows="6" cols="30" name="libros"></textarea>
</fieldset>
<input type="submit" value="Enviar" name="enviar">
</form>
<?php /*
} else {
	$pelis = $_POST['pelis'];
	$series = $_POST['series'];
	$canciones = $_POST['canciones'];
	$libros = $_POST['libros'];
	$array_pelis = explode(";", $pelis);
	$array_series = explode(";", $series);
	$array_canciones = explode(";", $canciones);
	$array_libros = explode(";", $libros);
	
	if(!isset($_POST['enviar2'])){
		?>
		<form action="examen.php" method="post" name="enviar2">
		<?php 

		echo "Usuario: <input type='text' name='usuario'><br>";
		echo "Película: <select name='pelicula'>";
		foreach ($array_pelis as $peli) {
			echo "<option value='$peli"."'>$peli</option>";
		}
		echo "</select><br>";
		echo "Series: <select multiple='multiple' name='series'>";
		foreach ($array_series as $serie) {
			echo "<option value='$serie"."'>$serie</option>";
		}
		echo "</select><br>";
		echo "Canciones: <br>";
		foreach ($array_canciones as $cancion) {
			echo '<input type="radio" name="cancion" value="'.$cancion.'">'.$cancion.'</br>';
		}
		echo "Libros: <br>";
		foreach ($array_libros as $libro) {
			echo '<input type="checkbox" name="libro" value="'.$libro.'">'.$libro.'</br>';
		}
		echo '<input type="submit" value="Enviar" name="enviar2">';
		
	}else{
		echo "<p>ok</p>";
	}
}
*/

	?>
	
</body>
</html>