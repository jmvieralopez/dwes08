<html>
<body>
<?php
if (! isset ( $_POST['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-diferencia.php" method="post">
		Introduce A: <input type="text" name="a" /> Introduce B: <input
			type="text" name="b" /> <input type="submit" name="enviar">
	</form>
<?php
} else {
	// var_dump($_POST);
	$a = $_POST['a'];
	$b = $_POST['b'];
	while ( $a > $b ) {
		echo "*";
		if ($a > $b) {
			$a ++;
		}
	}
	for($i = 1; $i <= $a - $b; $i ++) {
		echo "#";
	}
}
?>
</body>
</html>
