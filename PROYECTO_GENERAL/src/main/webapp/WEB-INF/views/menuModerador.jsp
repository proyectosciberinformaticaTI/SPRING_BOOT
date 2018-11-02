<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<script type="text/javascript" src="js/fullcalendar.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/es.js" charset="UTF-8"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
	
<link rel="stylesheet" href="css/fullcalendar.min.css"/>
<link rel="stylesheet" href="css/fullcalendar.print.min.css" media='print' />
<link rel="stylesheet" href="css/bootstrap.css"/>



	<!-- bootstrap -->
	<link rel="stylesheet" href="assests/bootstrap/css/bootstrap.min.css">
	<!-- bootstrap theme -->
	<link rel="stylesheet" href="assests/bootstrap/css/bootstrap-theme.min.css">
	<!-- font awesome -->
	<link rel="stylesheet" href="assests/font-awesome/css/font-awesome.min.css">

<style type="text/css">
	.ui-datepicker-calendar {
		display: none;
	}
</style>


<!--   <!-- custom css --> 
  <link rel="stylesheet" href="custom/css/custom.css">

	<!-- DataTables -->
  <link rel="stylesheet" href="assests/plugins/datatables/jquery.dataTables.min.css">

  <!-- file input -->
  <link rel="stylesheet" href="assests/plugins/fileinput/css/fileinput.min.css">

  <!-- jquery -->
<!-- 	<script src="assests/jquery/jquery.min.js"></script> -->
  <!-- jquery ui   -->
  <link rel="stylesheet" href="assests/jquery-ui/jquery-ui.min.css">
   <script src="assests/jquery-ui/jquery-ui.min.js"></script> 

  <!-- bootstrap js -->
	







	<title>Stock Management System</title>


</head>
<body>


	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <!-- <a class="navbar-brand" href="#">Brand</a> -->
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">      

      <ul class="nav navbar-nav navbar-right">        

      	<li id="navDashboard"><a href="menuModerador.jsp"><i class="glyphicon glyphicon-list-alt"></i>Inicio</a></li>        
        
       
        <li class="dropdown" id="navOrder">
                   <ul class="dropdown-menu">            
                        <li id="topNavManageOrder"><a href="orders.php?o=manord"> <i class="glyphicon glyphicon-edit"></i> Manage Orders</a></li>            
          </ul>
        </li> 


        <li id="navReport"><a href="listadoReportes.jsp"> <i class="glyphicon glyphicon-check"></i>Listado de Reportes</a></li>

        <li class="dropdown" id="navSetting">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="glyphicon glyphicon-user"></i> <span class="caret"></span></a>
          <ul class="dropdown-menu">            
                       <li id="topNavLogout"><a href="${pageContext.request.contextPath}/logoutModerador"> <i class="glyphicon glyphicon-log-out"></i> Salir</a></li>            
          </ul>
        </li>        
               
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
	</nav>

	<div class="container">



<div class="row">
	
	<div class="col-md-4">
		<div class="panel panel-success">
			<div class="panel-heading">
				
				<a href="" style="text-decoration:none;color:black;">
					xxxxxxxxxxxxxxxxxxxxxxxxxxx
					<span class="badge pull pull-right">4</span>	
				</a>
				
			</div> <!--/panel-hdeaing-->
		</div> <!--/panel-->
	</div> <!--/col-md-4-->

		<div class="col-md-4">
			<div class="panel panel-info">
			<div class="panel-heading">
				<a href="" style="text-decoration:none;color:black;">
					xxxxxxxxxxxxxxxxxxxxxxxxx
					<span class="badge pull pull-right">xxxxxxxx</span>
				</a>
					
			</div> <!--/panel-hdeaing-->
		</div> <!--/panel-->
		</div> <!--/col-md-4-->

	<div class="col-md-4">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<a href="" style="text-decoration:none;color:black;">
				xxxxxx
					<span class="badge pull pull-right">x</span>	
				</a>
				
			</div> <!--/panel-hdeaing-->
		</div> <!--/panel-->
	</div> <!--/col-md-4-->

	<div class="col-md-4">
		<div class="card">
		  <div class="cardHeader">
		    <h1><img width="250px" height="250px;" src="http://www.dachtechnik-windschuettl.de/wp-content/uploads/2018/03/man.png"/></h1>
		  </div>

		  <div class="cardContainer">
		    <p1>Bienvenido 		</p1>
		  </div>
		</div> 
		<br/>

		<div class="card">
		 

		
		</div> 

	</div>

	<div class="col-md-8">
		<div class="panel panel-default">
				<div class="panel-body">
				<center><h1> SISTEMA DE MODERADOR</h1></br>
				
				
				
					<img width="550px" height="550px" alt="IMAGEN" src="https://topali.com.mx/wp-content/uploads/2017/04/policias-seguridad-topali-1.png" />
	
				
				</center>
				
			</div>	
		</div>
		
	</div>

	
</div> <!--/row-->

<!-- fullCalendar 2.2.5 -->


 </div>

<script>
$(document).ready(function() {
	// top bar active
	
	
	$('#calendar').fullCalendar({
		header: {
			left: '',
			center: 'title',
			
		},
		minTime: '6:00:00',//Hora inicial
		maxTime: '24:00:00',//Hora inicial
		allDaySlot:false, // Deabilitar la opción todo el día
		defaultView: 'agendaWeek', //En modo semanal
		defaultDate: new Date(), // Hora por defecto
		navLinks: false, // Desabilitar el enlace de los días de la semana
		selectable: true, //Desabilitar para que sea seleccionable
		selectHelper: true,
		select: function(start, end, allDay) { //Funcion que se ejecuta al selecciona un rango de fecha
			  
			  //---Para la base de datos---
			  //Fecha de Inicio y fin de seleccion
				  starttime = $.fullCalendar.formatDate(start,'YYYY-MM-DD kk:mm:ss');
			  endtime = $.fullCalendar.formatDate(end,'YYYY-MM-DD kk:mm:ss');
				  
	          $('#formularioCronograma #fechaInicio').val(starttime);
	          $('#formularioCronograma #fechaFin').val(endtime);
	         
	          //---Para las etiquetas---
	          //Fecha de inicio y fin de seleccion para las etiquetas
				  starttime_1 = $.fullCalendar.formatDate(start,'DD MMMM kk:mm:ss');
	          endtime_1 = $.fullCalendar.formatDate(end,'DD MMMM kk:mm:ss');
			  
	          $('#formularioCronograma #labelInicio').text(starttime_1);
	          $('#formularioCronograma #labelFin').text(endtime_1);
	          
	          $('#formularioCronograma').modal('show'); 
	        

			  $('#calendar').fullCalendar('unselect');
		 
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		events: {
			
		}
	});
});

</script>

	<!-- file input -->
	<script src="assests/plugins/fileinput/js/plugins/canvas-to-blob.min.js'); ?>" type="text/javascript"></script>	
	<script src="assests/plugins/fileinput/js/plugins/sortable.min.js" type="text/javascript"></script>	
	<script src="assests/plugins/fileinput/js/plugins/purify.min.js" type="text/javascript"></script>
	<script src="assests/plugins/fileinput/js/fileinput.min.js"></script>	


	<!-- DataTables -->
	<script src="assests/plugins/datatables/jquery.dataTables.min.js"></script>

</body>
</html>