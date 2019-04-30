<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insertar en BBDD</title>
		<link rel="stylesheet" href="css/table.css">
	</head>
	<body>
		<h1 style="text-align: center">Insertar registro</h1>
        <form name="form1" method="get" action="ControladorRuta">
        	<input type="hidden" name="instruccion" value="insertarBBDD">
        	<table class="container">
        		<tbody>
        			<tr>
                        <th>Origen-Destino</th>
                        <th><input type="text" name="origenDestino" value="" required/></th>
                    </tr>
                    <tr>
                        <th>Empresa</th>
                        <th><input type="text" name="empresa" value="" required/></th>
                    </tr>
                    <tr>
                        <th>Precio Básico</th>
                        <th><input type="number" name="precioB" value="" required/></th>
                    </tr>
                    <tr>
                        <th>Precio Premium</th>
                        <th><input type="number" name="precioPr" value="" required/></th>
                    </tr>
                    <tr>
                        <th>Precio Platino</th>
                        <th><input type="number" name="precioPl" value="" required/></th>
                    </tr>
                    <tr>
                        <td><input type="submit" class="btn btn-blue btn-effect" value="Enviar" name="btnEnviar" /></td>
                        <td><input type="reset" class="btn btn-blue btn-effect" value="Restablecer" name="btnReset" /></td>
                    </tr>
        		</tbody>
        	</table>
        </form>
	</body>
</html>