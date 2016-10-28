<html>
<body>
<?php
if (! isset ( $_POST['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-diferencia.php" method="post">
		Introduce A: <input type="text" name="a" />
		Introduce B: <input type="text" name="b" />
		<input type="submit" name="enviar">
	</form>
<?php
} else {
	// var_dump($_POST);
	$a = $_POST['a'];
	$b = $_POST['b'];
	if($a > $b){
		for($i = 1; $i <= $a - $b; $i ++) {
			echo "#";
		}
		while ( $a > $b ) {
			echo "*";
			$b ++;
		}
	}else if($a < $b){
		for($i = 1; $i <= $b - $a; $i ++) {
			echo "#";
		}
		while ( $a < $b ) {
			echo "*";
			$a ++;
		}
	}else{	
		
	}
}
?>
</body>
</html>
