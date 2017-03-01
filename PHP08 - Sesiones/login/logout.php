<?php
// cierra la sesión y redirige de forma 
// automática a login.php
$_SESSION['login'] = 0;
$user = $_SESSION['usuario'];
if($loggedin != 1){
	header('Location: login.php');
}
?>