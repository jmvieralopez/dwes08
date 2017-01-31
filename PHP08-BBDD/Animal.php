<?php
class Animal{
	var $chip;
	var $nombre;
	var $especie;
	var $imagen;
	
	function __toString() {
		return "<p>Chip: $this->chip , nombre: $this->nombre , $this->especie , $this->imagen </p>";
	}
	
	function getChip(){
		return $this->chip;
	}
	function getNombre(){
		return $this->nombre;
	}
	function getEspecie(){
		return $this->especie;
	}
	function getImagen(){
		return $this->imagen;
	}
	
	function __construct(){
		
	}
}
?>