<html>
<body>
<h2>ecf-meses</h2>
<?php
if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="ecf-meses.php" method="post">
		Introduce numero mes: <input type="number" name="mes" /><br>
		  <input type="radio" name="bisiesto" value="si"> Bisiesto<br>
		  <input type="radio" name="bisiesto" value="no"> No bisiesto<br>
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$mes = $_POST['mes'];
	$bisiesto = $_POST['bisiesto'];
	$diasmes = 0;
	if($mes > 0 && $mes <=12){
		switch($mes){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: $diasmes = 31;break;
			case 4:
			case 6:
			case 9:
			case 11: $diasmes = 30;break;
			case 2:
				# bisiesto == 'si'
				# strcasecamp es como equalsIgnoreCase de Java
				if(strcmp($bisiesto, 'si')){
					$diasmes = 29;
				}else{
					$diasmes = 28;
				};break;
		}
		echo "<p>El mes $mes tiene $diasmes";
	}else{
		echo "<p>Error, ponga un mes numerico</p>";
	}
}
?>
</body>
</html>