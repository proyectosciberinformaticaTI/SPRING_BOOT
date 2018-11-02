 <%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %> 
	

<html lang="en">
  <head>
	    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Menú Principal Administrador</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	
	

<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="resources/css/bootstrap.css"/>
<link rel="stylesheet" href="resources/css/dataTables.bootstrap.min.css"/>

<link rel="stylesheet" href="resources/css/bootstrapValidator.css"/>
<script type="text/javascript" src="resources/js/bootstrapValidator.js"></script>

	
	<script type="text/javascript" src="http://www.phpzag.com/demo/delete-records-with-bootstrap-confirm-modal-using-php-mysql/script/bootbox.min.js"></script>


	
	
	<script type="text/javascript">

function solonumeros(e)
{
var key = window.event ? e.which : e.keyCode;
    if(key < 48 || key > 57)
        e.preventDefault();
}
</script>
	<link rel="stylesheet" href="resources/css/custom.css">
  </head>
  <body class="wide comments example">
		<nav class="navbar navbar-default ">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="menuAdministrador">Proyecto PIT 2</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
       
       
       		<li class=""><a href="mantenimientoModerador"><i class='glyphicon glyphicon-user'></i> Mantenimiento de moderadores</a></li>
		<li class=""><a href="mantenimientoAgraviados"><i  class='glyphicon glyphicon-user'></i>Consulta de agraviados</a></li>
       </ul>
      <ul class="nav navbar-nav navbar-right">
       		<li><a href="cerrarSesionModerador"><i class='glyphicon glyphicon-off'></i> Salir</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
	  







</br></br></br></br></br></br>


	<div class="panel panel-info">
		<div class="panel-heading">
		    <div class="btn-group pull-right">
				<button type="button" class="btn btn-info" data-toggle="modal" data-target="#nuevoProducto"><span class="glyphicon glyphicon-plus"></span> Nuevo Moderador</button>
			</div>
			<h4><i class="glyphicon glyphicon-search"></i>Mantenimiento Moderadores</h4>
		</div>
		<div class="panel-body">
			
				    
				  
			
			 <c:if test="${requestScope.mensaje != null}">
               		<div class="alert alert-success fade in">
				      
				<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>       ${requestScope.mensaje}
				    </div>
				    </c:if>
                        
		

				<form class="form-horizontal" method="GET"  id="form1">
			
			
						<div class="form-group row">
							<label for="q" class="col-md-2 control-label">DNI :</label>
							<div class="col-md-5">
								<input type="text" onkeypress="solonumeros(event)" class="form-control" name="dni" id="dni" placeholder="Inserte el dni del moderador">
							</div>
							<div class="col-md-3">
								<button type="button" id="btnConsultar" class="btn btn-default"  >
									<span class="glyphicon glyphicon-search"></span> Buscar</button>
								<span id="loader"></span>
							</div>
							
						</div>
				
				
	
				
			<table id="example" data-form="deleteForm" class="table table-striped table-bordered con" class="display">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Nombre y Apellido</th>
							<th>Telefono</th>
							<th>Correo</th>
							<th>Usuario</th>
							<th>Clave</th>
							<th>Dni</th>
							
						</tr>
					</thead>
					<tbody id="tablaDatos">
				
				
									</tbody>
				
				</table>


				

				</form>
		
			
  </div>
</div>
	
	
	
	
	
	
	
	
	
	
	
	<div class="modal fade" id="nuevoProducto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	
	
	<form class="form-horizontal" accept-charset="UTF-8" action="registrarModerador"  method="post">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
			<h4 class="modal-title" id="myModalLabel"><i class="glyphicon glyphicon-edit"></i> Agregar nuevo Moderador</h4>
		  </div>
		  <div class="modal-body">
						<div id="resultados_ajax2"></div>
			  <div class="form-group">
				<label for="mod_codigo" class="col-sm-3 control-label">Nombre y Apellido :</label>
				<div class="col-sm-8">
				  <input type="text" class="form-control" id="NOMBRE_APELLIDO" name="NOMBRE_APELLIDO" placeholder="Ingrese el nombre y apellido">
					
				</div>
			  </div>
			   <div class="form-group">
				<label for="mod_nombre" class="col-sm-3 control-label">Entidad :</label>
				<div class="col-sm-8">
				 <input type="text" onkeypress="solonumeros(event)" maxlength="9" class="form-control" id="ENTIDAD" name="ENTIDAD" placeholder="Ingrese la entidad" >
							</div>
			  </div>
			  
		
		
		
			 
			  <div class="form-group">
				<label for="mod_precio" class="col-sm-3 control-label">Usuario :</label>
				<div class="col-sm-8">
				  <input type="text" class="form-control" id="USUARIO" name="USUARIO" placeholder="Ingrese el nombre de usuario"  >
				</div>
			  </div>
			 
			 
			 
			 <div class="form-group">
				<label for="mod_precio" class="col-sm-3 control-label">Clave :</label>
				<div class="col-sm-8">
				  <input type="text" class="form-control" id="CLAVE" name="CLAVE" placeholder="ingrese la contraseña">
				</div>
			  </div>
			
			<div class="form-group">
				<label for="mod_precio" class="col-sm-3 control-label">Correo :</label>
				<div class="col-sm-8">
				  <input type="text" class="form-control" id="CORREO" name="CORREO" placeholder="Ingrese el correo" required="">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="mod_precio" class="col-sm-3 control-label">DNI :</label>
				<div class="col-sm-8">
				  <input type="text" class="form-control" maxlength="8" onkeypress="solonumeros(event)" id="DNI" name="DNI" placeholder="ingrese el dni">
				</div>
			  </div>
			
			
			  
			  
			   <div class="form-group">
				<label for="mod_precio" class="col-sm-3 control-label">Telefono :</label>
				<div class="col-sm-8">
				  <input type="text" class="form-control" onkeypress="solonumeros(event)" maxlength="9" id="TELEFONO" name="TELEFONO" placeholder="ingrese el dni">
				</div>
			  </div>
			  
			  
			
			
		 </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			<button type="submit" class="btn btn-primary" id="guardar_datos">Guardar datos</button>
		  </div>
		  
		  
		</div></form>
	  </div>
	</div>
<div class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
      <p class="navbar-text pull-left">&copy 2018 Joel Anthony Arauzo
           <a href="http://obedalvarado.pw/" target="_blank" style="color: #ecf0f1">Sitios Web y Sistemas</a>
      </p>
   </div>
</div>

		
<script type="text/javascript">



$(document).ready(function() {
    $('#example').DataTable();

} );



</script>
<script type="text/javascript">
	
		$("#btnConsultar").click(function(){
			
			var data = $("#form1").serialize();
			$("#tablaDatos").empty();
			
			$.get("traerModeradores", data, function(arreglo){
						
				if( arreglo.length == 0 ){
					return false;
				}
				
				var datos = "";
				
				$.each( arreglo, function( index, rec ){
					console.log(datos);
					datos += "<tr>";
					datos += "<td>" + rec.idtb_MODERADOR + "</td>";
					datos += "<td>" + rec.nombre_APELLIDO + "</td>";
					datos += "<td>" + rec.telefono + "</td>";
					datos += "<td>" + rec.correo + "</td>";
					datos += "<td>" + rec.usuario + "</td>";
					datos += "<td>" + rec.clave + "</td>";
					datos += "<td>" + rec.dni + "</td>";
					datos += "</tr>";
				});
				
				console.log(datos);
				$("#tablaDatos").html(datos);
				
			});
			
		});
	
	</script>
	


    
    
    
    
    
	
  </body>
</html>