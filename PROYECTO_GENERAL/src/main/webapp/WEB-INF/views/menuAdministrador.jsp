<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<html lang="en">
  <head>
	    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Menú Principal Administrador</title>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	
	<link rel="stylesheet" href="resources/css/custom.css">
	<link rel=icon href='img/logo-icon.png' sizes="32x32" type="image/png">
  </head>
  <body>
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
	  






<center>
</br></br></br></br></br></br>
<h1> BIENVENIDO :
<c:if test="${sessionScope.UsuarioLogons != null }">

	${sessionScope.UsuarioLogons}
	</c:if>
	       </h1>

<img width="250px" height="250px" alt=""  src="http://www.nubebiomedica.com/log2.png">


</center>

<div class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
      <p class="navbar-text pull-left">&copy 2018  Joel Anthony Arauzo Masgo
           <a href="http://obedalvarado.pw/" target="_blank" style="color: #ecf0f1">Sitios Web y Sistemas</a>
      </p>
   </div>
</div>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>	<script type="text/javascript" src="js/VentanaCentrada.js"></script>
	
  </body>
</html>