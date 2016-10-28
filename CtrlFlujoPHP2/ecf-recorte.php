<html>
<body>
<h2>ecf-multiplicacion</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	/*
	 * 
	 * 			ARREGLAR
	 * 
	 * 
	 */
	?>
<form action="ecf-recorte.php" method="post">
		Introduce X: <input type="text" name="txt" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$txt = $_POST['txt'];
	for ($i = strlen($txt); $i > 0; $i++){
		echo $txt;
	}
}
?>
</body>
</html>