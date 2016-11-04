<html>
<body>
	<h2>ecf-acumulador</h2>
	<form action="ecf-acumulador.php" method="post">
		<input type="number" name="num"/>
<?php 
	if (! isset ( $_POST ["enviar"] )) {

		// var_dump($_POST);
		echo '<input type="hidden" name="acumulado" value="0"/>';
	} else {
		$acumulador = $_POST["acumulado"] + $_POST["num"];
		if($acumulador < 50){
			?> <input type='hidden' name='acumulado' value="<?php echo $acumulador; ?>"/>
			<?php
		}else{
			echo "<p>Has superado los 50</p>";
		}
	}
?>
		<input type="submit" name="enviar" value="ENv"/>
<?php 
/*
 * Si el acumulador es > 50
 * Poner un mensaje de que nos hemos pasado
 * else
 * Mostrar el formulario
 * >En el formulario hay 3 input:
 * 1. la X
 * 2. un campo hidden
 * 3. el botón de enviar
 * 
 * $acumulador = $_POST["acumulado"] + $_POST["num"];
 */
?>
	</form>
</body>
</html>