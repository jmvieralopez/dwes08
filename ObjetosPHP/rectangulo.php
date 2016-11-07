<html>
<body>
<h2>calcular area</h2>
<?php
class Rectangulo{
	public $base;
	private $altura;
	
	function get_altura() {
		return $this->altura;
	}
	function set_altura($alt){
		$this->altura = $alt;
	}
	function calcular_area() {
		return ($this->base * $this->altura);
	}
	function __construct($base, $altura) {
		$this->base = $base;
		$this->altura = $altura;
	}
	
	function iterateVisible() {
		echo "Rectangulo::iterateVisible:\n";
		foreach ($this as $clave => $valor) {
			print "$clave => $valor\n";
		}
	}
}

if (! isset ( $_POST ['enviar'] )) {
	// var_dump($_POST);
	?>
<form action="rectangulo.php" method="post">
		Introduce base: <input type="text" name="base" />
		Introduce altura: <input type="text" name="altura" />
		<input type="submit" name="enviar">
	</form>
	<?php
} else {
	$base = $_POST['base'];
	$altura = $_POST['altura'];
	$tri = new Rectangulo($base, $altura);
	$area = $tri->calcular_area();
	echo "<p>Area: $area</p>";
	$tri->iterateVisible();
}
?>

</body>
</html>