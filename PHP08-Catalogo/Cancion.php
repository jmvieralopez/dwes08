<?php
class Cancion{
	var $id;
	var $idArtista;
	var $cancion;
	var $artista;
	var $album;
	
	function setId($id) { $this->id = $id; }
	function getId() { return $this->id; }
	function setIdArtista($idArtista) { $this->idArtista = $idArtista; }
	function getIdArtista() { return $this->idArtista; }
	function setCancion($cancion) { $this->cancion = $cancion; }
	function getCancion() { return $this->cancion; }
	function setArtista($artista) { $this->artista = $artista; }
	function getArtista() { return $this->artista; }
	function setAlbum($album) { $this->album = $album; }
	function getAlbum() { return $this->album; }
	
}