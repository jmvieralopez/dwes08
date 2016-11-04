<html>
<body>
<h2>ecf-cuadrado</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-cuadrado.php" method="post">
		Introduce numero: <input type="number" name="x" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$x = $_POST['x'];
	echo "<table style='border-width:30px;'>";
	for($i = 1; $i <= $x; $i++){
		if($i % 2 == 0){
			echo "<tr style='background-color:lightblue;'>";
		}else{
			echo "<tr>";
		}
		for($j = 1; $j <= $x; $j++){
			$mul = $i * $j;
			echo "<td style='padding:3px;'> $mul </td>";
		}
		echo "</tr>";
	}
}
?>
</body>
</html>