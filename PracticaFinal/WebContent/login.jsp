<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inicia sesión</title>
		<link rel="stylesheet" href="css/logueo.css">
	</head>
	<body>
		<div class="login">
  			<h1>Por favor inicia sesión</h1>
  			<form method="get" action="ControladorLogin">
    			<p><input type="text" name="login" value="" placeholder="Usuario"></p>
   			    <p><input type="password" name="password" value="" placeholder="Contraseña"></p>
    			<p class="submit"><input type="submit" name="commit" value="Login"></p>
  			</form>
		</div>
	</body>
</html>