<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Precio Rutas TDT</title>
		<link rel="stylesheet" href="css/table.css">
	</head>
	<body>
		<h1><span class="blue">&lt;</span>Precio<span class="blue">&gt;</span> <span class="yellow">${origenDestino} ${empresa} para viaje de 40 personas en TDT</pan></h1>
		<table class="container">
			<thead>
				<tr>
					<th><h1>Origen-Destino</h1></th>
					<th><h1>Empresa</h1></th>
					<th><h1>Precio Básico</h1></th>
					<th><h1>Precio Premium</h1></th>
					<th><h1>Precio Platino</h1></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${RutaOdCalc.origenDestino}</td>
					<td>${RutaOdCalc.empresa}</td>
					<td>${RutaOdCalc.precioB}</td>
					<td>${RutaOdCalc.precioPr}</td>
					<td>${RutaOdCalc.precioPl}</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>