<html>
<body>
<h2>ecf-multiplos</h2>
<p>No sabía exactamente qué numeros querías</p>
<h3>Multiplos de 3 y 5</h3>
<?php
for($i = 0; $i <= 1000; ++$i){
	if($i % 3 == 0){
		echo $i." ";
	}elseif ($i % 5 == 0){
		echo $i." ";
	}
}
?>
<h3>20 primeros multiplos de 3 y 5, respectivamente</h3>
<?php 
for($i = 0; $i < 20; ++$i){
	$multres = $i * 3;
	$mulcinco = $i * 5;
	echo "$multres // $mulcinco </br>";
}
?>
</body>
</html>