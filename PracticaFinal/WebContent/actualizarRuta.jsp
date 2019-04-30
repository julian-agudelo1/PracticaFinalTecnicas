<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/table.css">
	</head>
	<body>
		<h1 style="text-align: center">Actualizar registro ${RutaActualizar.origenDestino} ${RutaActualizar.empresa}</h1>
        <form name="form1" method="get" action="ControladorRuta">
        	<input type="hidden" name="instruccion" value="actualizarBBDD">
        	<input type="hidden" name="origenDestino" value="${RutaActualizar.origenDestino}">
        	<input type="hidden" name="empresa" value="${RutaActualizar.empresa}">
        	<table class="container">
        		<tbody>
                    <tr>
                        <th>Precio Básico</th>
                        <th><input type="number" name="precioB" value="${RutaActualizar.precioB}" required/></th>
                    </tr>
                    <tr>
                        <th>Precio Premium</th>
                        <th><input type="number" name="precioPr" value="${RutaActualizar.precioPr}" required/></th>
                    </tr>
                    <tr>
                        <th>Precio Platino</th>
                        <th><input type="number" name="precioPl" value="${RutaActualizar.precioPl}" required/></th>
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