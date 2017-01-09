<!DOCTYPE html>
<html><head><meta charset='UTF-8'/></head>
<body>
<?php
$rutaArchivo = "files/modulos.txt";
// Pruebas

echo readfile("files/modulos.txt");

echo "<br>";
// volcar líneas a array
$lineasArchivo = file($rutaArchivo);
print_r($lineasArchivo);

echo "<br>";

//Sin separación de lineas
$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
echo fread($archivo,filesize($rutaArchivo));
fclose($archivo);

echo "<br>";

//Ciclar todas las líneas por línea
$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
while(!feof($archivo)) {
	echo fgets($archivo) . "<br/>";
}
fclose($archivo);

echo "<br>";

//caracter a caracter, con fines de linea
$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
while(!feof($archivo)) {
	$c = fgetc($archivo);
	if (($c == "\n") or ($c == "\r\n")) echo "<br/>";
	else echo $c;
}
fclose($archivo);

echo "<br>";

//----------- PRUEBAS DE ESCRITURA -----------------
$rutaArchivo2 = "files/nuevo.txt";
$archivo = fopen($rutaArchivo2, "w+") or die("Imposible  abrir el archivo para escritura");
fwrite($archivo,"Programación\n");
fwrite($archivo,"Entornos de desarrollo\n");
fclose($archivo);
escribir($rutaArchivo2);

//escribir al principio de un documento
$rutaArchivo2 = "files/modulos.txt";

$lineasArchivo2 = file($rutaArchivo2);

$archivo = fopen($rutaArchivo2, "w+") or die("Imposible  abrir el archivo para escritura");
fwrite($archivo,"Programación\n");
fwrite($archivo,"Entornos de desarrollo\n");
foreach ($lineasArchivo2 as $linea) {
	fwrite($archivo, $linea);
}
fclose($archivo);
escribir($rutaArchivo);

//ordenar modulos

$rutaArchivo3 = "files/modulos.txt";
$lineasArchivo3 = file($rutaArchivo3);
sort($lineasArchivo3);
var_dump($lineasArchivo3);
$archivo = fopen($rutaArchivo3, "w+") or die("Imposible  abrir el archivo para escritura");
foreach ($lineasArchivo3 as $linea) {
	fwrite($archivo, $linea);
}
fclose($archivo);
escribir($rutaArchivo);


function escribir($rutaArchivo){
	echo "<strong>ejecutando</strong><br>";
	$archivo = fopen($rutaArchivo, "r") or die("Imposible abrir el archivo");
	while(!feof($archivo)) {
		echo fgets($archivo) . "<br/>";
	}
	fclose($archivo);
}
?>
</body></html>
