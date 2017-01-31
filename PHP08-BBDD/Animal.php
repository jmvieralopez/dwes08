<?php
class Animal{
	var $chip;
//	var $nombre;
//	var $especie;
	var $tipo;
	var $imagen;
	
	function __toString() {
		return "<p>Chip: $this->chip , nombre: $this->nombre , $this->tipo , $this->imagen </p>";
	}
	
	function getChip(){
		return $this->chip;
	}
	function getNombre(){
		return $this->nombre;
	}
	function getEspecie(){
		return $this->tipo;
	}
	function getImagen(){
		return $this->imagen;
	}
	/*
	function __construct($chip, $nombre, $especie, $imagen){
		$this->chip = $chip;
		$this->especie = $especie;
		$this->imagen = $imagen;
		$this->nombre = $nombre;
	}*/
	/*function __construct($fila){
		$this->chip = $fila['chip'];
		$this->especie = $fila['especie'];
		$this->imagen = $fila['imagen'];
		$this->nombre = $fila['nombre'];
	}*/
}
?>