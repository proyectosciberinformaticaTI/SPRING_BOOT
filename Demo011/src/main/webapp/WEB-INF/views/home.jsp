<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>SPRING FRAMEWORK</h1>

<c:if test="${sessionScope.empleado == null}">

	<h1>INICIO DE SESION</h1>
	
	<form method="post" action="procesarLogin">
	
		<label>Código del Empleado</label>
		<input type="text" name="empleado" />
		<input type="submit" value="Procesar" />
		
	</form>

</c:if>


<c:if test="${sessionScope.empleado != null }">

	<h1>Empleado: ${sessionScope.empleado}</h1>
	
	<div>
		<a href="cerrarSesion">Cerrar Sesión</a>
	</div>
	
	<div>
		<a href="getCliente">getCliente</a>
	</div>
	
	<div>
		<a href="getMovimientos">getMovimientos</a>
	</div>
	
	<div>
		<a href="procDeposito">procDeposito</a>
	</div>
	
	<div>
		<a href="procRetiro">procRetiro</a>
	</div>


</c:if>
</body>
</html>
