
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring UNI</title>

<!-- CSS de Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="resources/css/tema.css" rel="stylesheet">
	
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="cabecera.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="menuAdmin.jsp" />
			</div>
			<div class="col-md-7 col-md-offset-1">
				<div class="jumbotron">
					<div class="container">
						<h1>Zona de Administración!</h1>
						<p class="text-justify"><small>En esta página podrás realizar la gestión de todas las canciones para que mantentas siempre actualizado tu repertorio de música!</small></p>
						<p>
							<a class="btn btn-primary btn-lg" href="sobrenosotros.htm" role="button">Leer más »</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	
</body>
</html>