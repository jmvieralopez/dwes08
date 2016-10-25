<?php include 'cabecera.php';
echo "<br/>";
//* 1. Comentarios de los tres tipos

/*
 * hola
 * jesus
 */

// hola

# hola

// * Sentencias echo con los dos tipos de comillas

$atodos = "a todos";
/*echo "hola " . "a todos";
echo "hola \n a todos";*/
echo "hola $atodos \n";
echo 'hola $atodos \n';

// * Uso de al menos tres operadores de comparación y dos operadores lógicos



// * Uso de un operador de asignación

echo "\n";
$nombre = "jesus";
$apellido = "viera";
$nombre .= " " . $apellido;
echo $nombre + "\n";

// isset()

$v = 7;

if(isset($v)){
	echo "la variable incluye un $v\n";
}
if (isset($v)){
	echo "la variable esta vacia\n";
}

// * Declaración y uso de una variable por referencia

$salario_base=2000;
$salario_juan=&$salario_base;
$salario_juan+=200;
echo "salario base: $salario_base\n";
echo "salario juan: $salario_juan\n";

// * Declaración y uso de dos constantes, una que obligue a respetar las mayúsculas en su uso y otra que no lo haga

define("PROFESOR", "Alberto Ruiz");
echo "<p>El profe es ".PROFESOR."</p>";
echo "<p>El profe es ".Profesor."</p>";

define("AULA", 104, true);
echo "<p>El aula es ".Aula."</p>";

// * Declaración y uso de un variable booleana y otra de tipo double

$bool = true;
$doub = 2.3333333;

// * Uso de is_numeric, is_boolean y is_double con estas variables

echo is_numeric($doub)."<br>";
echo is_bool($bool)."<br>";
echo is_double($doub)."<br>";

// * Declaración de una variable de tipo string. Pruebas con la función *strlen* y con tres de las funciones indicadas en el enlace.

$str = "Toma vitamina para merendar";
$str2 = "Toma vitamina para almorzar";
$len = strlen($str);
$comp = substr_compare($str, $str2);
$trim = trim($str2);
$char = $str{10};
echo "<p>la frase tiene $len caracteres, el caracter en la posicion 10 es $char</p>";

// * Declaración de un array escalar y uno asociativo

$frutas = array("manzana", "pera");
$citricos = array(0=>"naranja", 2=>"limon", 14=>"pomelo");
$verduras[0] = "lechuga";
$verduras[1] = "remolacha";
$verduras[4] = "berenjena";

echo 'tamaño array frutas: '. count($frutas);
echo 'tamaño array citricos: '. sizeof($citricos);
echo "<br/>";

$telefonos = array("fulano"=>111, "mengano"=>222, "zutano"=>333);
$telefonos["fulanita"]=444;

// * Ordenación y volcado de información con *var_dump* siguiendo dos criterios de ordenación en cada uno de los arrays

sort($verduras);
arsort($telefonos);
echo "<p>" . var_dump($verduras) . "</p>";
echo "<p>" . var_dump($telefonos) . "</p>";

// * Una estructura de control de cada tipo (if-elsif-else, switch, while, do-while, for)

if (true) {
	;
}elseif (true) {
	;
}else{
	;
}

// * Un recorrido por cada uno de los dos arrays utilizando foreach, generando por ejemplo una lista ordenada con sus elementos

echo "<ul>";
foreach ($citricos as $citrico){
	echo "<li> $citrico </li>";
}
echo "</ul>";

echo "<ul>";
foreach ($telefonos as $nombre => $tlf){
	echo "<li> $nombre - $tlf </li>";
}
echo "</ul>";

// * Dos pruebas con la variable superglobal _SERVER

echo $_SERVER['PHP_SELF'];
echo "<br/>";
echo $_SERVER['SERVER_NAME'];
echo "<br/>";
echo $_SERVER['HTTP_HOST'];
echo "<br/>";
echo $_SERVER['HTTP_USER_AGENT'];
echo "<br/>";
echo $_SERVER['SCRIPT_NAME'];
echo "<br/>";

// * Dos pruebas de funciones: una devolverá algun tipo, la otra no

$c;
function funcionConTipo($a, $b):string{
	return $a.$b;
}

function funcionSinTipo($a, $b){
	global $c;
	$c = $a.$b;
}

// * Una función que utilice una variable global

function funcionVariableGlobal($a, $b){
	$GLOBALS['c'] = $a.$b;
}

//includes arriba

// * Un formulario que reciba datos y los muestre por pantalla

if(!isset($_POST['enviar'])){
	var_dump($_POST);
?>

<form action="index.php" method="post">
	Introduce tu nombre: <input type="text" name="nombreForm" />
	Introduce tu clase/especie/tipo de personaje: <input type="text" name="claseForm" />
	<input type="submit" name="enviar">
</form>
<?php }
else{
	var_dump($_POST);
	echo "<h2>Yo te saludo, ".$_POST['nombreForm'].", el/la ".$_POST['claseForm'];
}
?>

