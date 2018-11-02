
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring UNI</title>

<!-- CSS de Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
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
				<jsp:include page="menu.jsp" />
			</div>
			<div class="col-md-6 col-md-offset-1">
				<div class="col-md-6 col-md-offset-1">
					<form class="form-signin" method="get"  action="loginUsuario">
						<h3 class="form-signin-heading">INGRESAR</h3>
						<label for="usuario" class="sr-only">Usuario</label> 
						<input
							type="text" id="usuario" name="usuario" class="form-control"
							placeholder="Ingrese su email" required="true" autofocus="" /> 
						<label for="password" class="sr-only">Password</label> 
						<input
							type="password" id="clave" name="clave" class="form-control"
							placeholder="Ingrese su contrase�a" required="true" />
						<div style="height: 10px;"></div>
						<button class="btn btn-primary btn-block" type="submit"><b>Acceder</b></button>
					</form>
					<div style="height: 5px;"></div>
					<div class="${tipo_mensaje}" role="alert">${mensaje}</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>






