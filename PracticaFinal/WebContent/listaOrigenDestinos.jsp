<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="css/table.css">
	</head>
	<body>
		<h1><span class="blue">&lt;</span>Rutas<span class="blue">&gt;</span> <span class="yellow">en TDT</pan></h1>
		<h2>Listado</h2>
		<table class="container">
			<thead>
				<tr>
					<th><h1>Origen-Destino</h1></th>
					<th><h1>Acción</h1></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="temp" items="${listaOrigenDestinos}">
					<!-- Link para cada ruta con su campo clave -->
					<c:url var="linkTemp" value="ControladorRuta">
						<c:param name="instruccion" value="buscarRutaOd"></c:param>
						<c:param name="origenDestino" value="${temp}"></c:param>
					</c:url>
					<tr>
						<td>${temp}</td>
						<td><a href="${linkTemp}">Búsqueda</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>