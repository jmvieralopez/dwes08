<style>	p{ color : red; }</style>
<?php
if (!isset($_POST['enviar']) || isset($_POST['vaciar'])){
	?>
<h2>Rellena los datos q(^_^)p!</h2>
<form action="Index.php" method="post">
Nombre:<input type="text" name="nombre"><br/>
Edad :<input type="text" name="edad"><br/>
FechaN:<input type="date" name="fecha"><br/>
Sexo : Mujer<input type="radio" value="mujer" name="sexo" checked>
	   Hombre<input type="radio" value="hombre" name="sexo"><br/>
Correo :<input type="text" name="correo"><br/>
Contraseña :<input type="password" name="contrasena"><br/>
<input type="submit" name="enviar" value="enviar"><input type="submit" name="vaciar" value="vaciar">
</form>
<?php
}else{
	?><p><?php 
$nombre=$_POST["nombre"];
$edad=$_POST["edad"];
$fecha=$_POST["fecha"];
$correo=$_POST["correo"];
$contrasena=$_POST["contrasena"];
$letras="zxcvbnmasdfghjklñpoiuytrewqáéíóú ";
$cifras="0123456789";

//nombre
$valido= true;
if (!empty($nombre)){	
	for ($i=0;$i<strlen($nombre) && $valido;$i++){
		$encontrado=true;
		for ($k=0;$k<strlen($letras) && $encontrado; $k++) {			
			if (strtolower($nombre[$i])==$letras[$k]){
				$encontrado=false;
			}
		}
		if($encontrado){
			$valido = false;
		}
	}
	if(!$valido){
		echo "El nombre solo puede contener letras.";
	}
}else{
	echo "El nombre no está escrito.";
}
echo "</br>";

$edadValida=false;
$edadLetra=false;
if(!empty($edad)){
for($p=0;$p<strlen($edad);$p++){
	for($o=0;$o<strlen($letras) && !$edadLetra;$o++){
		if($edad[$p]==$letras[$o]){$edadLetra=true;}
	}
}
if(!$edadLetra){
	$edad=(int)$edad;
		if(is_int($edad)){
			   if ($edad>=13 && $edad<=120){
			   	$edadValida=true;
			   }else {
			   	echo "Edad no permitida (entre 13 y 120).";
			   }
		}
	}else{
		echo "La edad debe ser un número.";
	}
}else{echo "La edad está vacía";}
echo "</br>";

$fechaValida=false;
//fecha nacimiento
if(!empty($fecha)){
	//comprobar fecha nacimiento
	$fechaValida=true;
}else{
	echo "La fecha está vacía.";
}
echo "</br>";
//correo

$correoValido = false;
if(!empty($correo)){
	$encontradoarroba = false;
	$encontradopunto = false;
	for($j = 0; $j<strlen($correo); $j++){
		if($correo[$j]=='@'){
			$encontradoarroba=true;
		}
		if($encontradoarroba==true && $correo[$j]=='.'){
			$correoValido = true;
		}
	}
	if(!$correoValido){
		echo "Correo no válido.";
	}
}else{
	echo "El correo está vacío.";
}
echo "<br/>";

//password

$hayNum=false;
$hayLetra=false;
$contrasenaValida=false;
if(!empty($contrasena)){
	if(strlen($contrasena)>=8){//comprobar que tiene mas de 8 caracteres
		for($l=0;$l<strlen($contrasena);$l++){//recorren la cadena para comprobar caracter a caracter
			for($m=0;$m<strlen($cifras) && !$hayNum;$m++){
				if($contrasena[$l]==$cifras[$m]){$hayNum=true;}
			}
			for($n=0;$n<strlen($letras) && !$hayLetra;$n++){
				if($contrasena[$l]==$letras[$n]){$hayLetra=true;}
			}
		}
		if($hayNum && $hayLetra){
			$contrasenaValida=true;
		}
		else{echo "La contraseña debe contener al menos 1 número y 1 letra.";}
	}else{echo "La contraseña debe tener al menos 8 caracteres.";}
}else{echo "La contraseña está vacía.";}

if($valido && $edadValida && $fechaValida && $correoValido && $contrasenaValida){
	echo "<h1>Trump está contento con tus datos</h1>";
	echo "<img src='trumpbien.jpg' width=50% height=50%/>";
}else{
	?>
	</p>
	<img src='trumpmal.jpg' width=50% height=50%/>
	<form action="Index.php" method="post">
	<?php 
	if($valido){echo "Nombre:<input type='text' value='$nombre' name='nombre'></br>";}
	else{echo "Nombre:<input type='text'name='nombre'></br>";}
	if($edadValida){echo "Edad :<input type='text' value='$edad' name='edad'></br>";}
	else{echo "Edad :<input type='text' name='edad'></br>";}
	if($fechaValida){echo "FechaN:<input type='date' value='$fecha' name='fecha'></br>";}
	else{echo "FechaN:<input type='date' name='fecha'></br>";}
	echo 'Sexo : Mujer<input type="radio" value="mujer" name="sexo" checked>';
	echo '	   Hombre<input type="radio" value="hombre" name="sexo"></br>';
	if($correoValido){echo "Correo :<input type='text' value='$correo' name='correo'></br>";}
	else{echo "Correo :<input type='text' name='correo'></br>";}
	if($contrasenaValida){echo "Contraseña :<input type='password' value='$contrasena' name='contrasena'></br>";}
	else{echo "Contraseña :<input type='password' name='contrasena'></br>";}
	?>
	<input type="submit" name="enviar" value="enviar"><input type="submit" name="vaciar" value="vaciar">
	</form>
	
	<?php
}
}

?>


