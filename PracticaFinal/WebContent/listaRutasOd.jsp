<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Rutas TDT</title>
		<link rel="stylesheet" href="css/table.css">
	</head>
	<body>
		<h1><span class="blue">&lt;</span>Rutas<span class="blue">&gt;</span> <span class="yellow">${origenDestino} en TDT</pan></h1>
		<h2>Listado</h2>
		<h2>Presiona calcular para obtener el precio de un viaje para 40 personas. Cada empresa maneja un descuento para que ahorres :D</h2>
		<table class="container">
			<thead>
				<tr>
					<th><h1>Origen-Destino</h1></th>
					<th><h1>Empresa</h1></th>
					<th><h1>Precio Básico</h1></th>
					<th><h1>Precio Premium</h1></th>
					<th><h1>Precio Platino</h1></th>
					<th><h1>Acción</h1></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="temp" items="${listaRutasOd}">
					<!-- Link para cada ruta con sus campos clave -->
					<c:url var="linkTemp" value="ControladorRuta">
						<c:param name="instruccion" value="calcular"></c:param>
						<c:param name="origenDestino" value="${temp.origenDestino}"></c:param>
						<c:param name="empresa" value="${temp.empresa}"></c:param>
					</c:url>
					<tr>
						<td>${temp.origenDestino}</td>
						<td>${temp.empresa}</td>
						<td>${temp.precioB}</td>
						<td>${temp.precioPr}</td>
						<td>${temp.precioPl}</td>
						<td><a href="${linkTemp}">Calcular</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>