<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MOVIMEINTOS</title>
</head>
<body>

	<h1>CONSULTAR MOVIMIENTOS</h1>
	<form id="form1" >
		<label>CUENTA: </label>
		<input type="text" name="cuenta"/>
		<input type="button" id="btnConsultar" value="Consultar" />
	</form>
	
	<div id="divResultado">
		<table>
			<thead>
				<tr>
					<th>CUENTA</th>
					<th>NRO. MOV.</th>
					<th>IMPORTE</th>
				</tr>
			</thead>
			<tbody id="tablaDatos">
			</tbody>		
		</table>
	</div>
	
	<script type="text/javascript"
  src="<c:url value='/resources/jquery/jquery.js'></c:url>"></script>
	
	<script type="text/javascript">
	
		$("#btnConsultar").click(function(){
			
			var data = $("#form1").serialize();
			$("#tablaDatos").empty();
			
			$.get("traerMovimientos", data, function(response){
				
				var arreglo = $.parseJSON(response);				
				
				if( arreglo.length == 0 ){
					return false;
				}
				
				var datos = "";
				
				$.each( arreglo, function( index, rec ){
					datos += "<tr>";
					datos += "<td>" + rec.CUENCODIGO + "</td>";
					datos += "<td>" + rec.MOVINUMERO + "</td>";
					datos += "<td>" + rec.MOVIIMPORTE + "</td>";
					datos += "</tr>";
				});
				
				console.log(datos);
				$("#tablaDatos").html(datos);
				
			});
			
		});
	
	</script>
	
	
</body>
</html>