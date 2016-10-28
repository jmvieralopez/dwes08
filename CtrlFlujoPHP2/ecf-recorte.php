<html>
<body>
<h2>ecf-recorte</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-recorte.php" method="post">
		Introduce cadena: <input type="text" name="txt" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$txt = $_POST['txt'];
	for ($i = strlen($txt); $i > 0; $i--){
		for($j = 0; $j < $i; $j++){
			echo $txt[$j];
		}
		echo "<br/>";
	}
}
?>
</body>
</html>