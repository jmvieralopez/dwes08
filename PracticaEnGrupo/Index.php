<?php

if (!isset($_POST['enviar'])){


?>
<h2>Rellena los malditos datos y no te equivoques JODER!</h2>
<form action="Index.php" method="post">
Nombre:<input type="text" name="nombre"></br>
Edad :<input type="text" name="edad"></br>
FechaN:<input type="date" name="fecha"></br>
Sexo : Mujer<input type="radio" value="mujer" name="sexo" checked>
	   Hombre<input type="radio" value="hombre" name="sexo"></br>
Correo :<input type="text" name="correo"></br>
Contraseña :<input type="password" name="contrasena"></br>
<input type="submit" name="enviar">
</form>
<?php
}else{
	$nombre=$_POST["nombre"];
	$edad=$_POST["edad"];
	$correo=$_POST["correo"];
	$contrasena=$_POST["contrasena"];
	$letras="zxcvbnmasdfghjklñpoiuytrewqáéíóú ";
	
	if (!isset($nombre)){
		$valido = true;
		for ($i=0;$i<strlen($nombre) && $valido;$i++){
			$encontrado=false;
			for ($k=0;$i<strlen($letras) && !$encontrado; $k++) {
				if ($nombre[$i]==$letras[$k]){
						$encontrado=true;
				}				
			}
			if($encontrado){
				$valido = false;	
			}
		}
	}
}

?>


