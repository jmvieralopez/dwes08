<?php
class Cancion{
	var $id;
	var $idArtista;
	var $cancion;
	var $artista;
	var $album;
	var $caratula;
	
	function getId() { return $this->id; }
	function getIdArtista() { return $this->idArtista; }
	function getCancion() { return $this->cancion; }
	function getArtista() { return $this->artista; }
	function getAlbum() { return $this->album; }
	function getCaratula() {return $this->caratula; }
}