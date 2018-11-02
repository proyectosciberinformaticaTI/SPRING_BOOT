<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>CONSULTAR CLIENTES</h1>
	<form id="form1" method="post" action="getCliente" >
		<label>CODIGO</label>
		<input type="text" name="codigo" value="${codigo}"/>
		<input type="submit" value="Consultar" />
		<input type="button" value="Consultar con AJAX" id="btnUsaJSON" />
	</form>


	<h1>RESULTADO</h1>
	<table>
	
		<thead>
			<tr>
				<th>CODIGO</th>
				<th>PATERNO</th>
				<th>MATERNO</th>
				<th>NOMBRE</th>
			</tr>
		</thead>
		
		<tbody id="tablaDatos">
			<tr>
				<td>${rec.codigo}</td>
				<td>${rec.paterno}</td>
				<td>${rec.materno}</td>
				<td>${rec.nombre}</td>
			</tr>		
		</tbody>	
	
	</table>
	
	<script type="text/javascript"
  	src="<c:url value='/resources/jquery/jquery.js'></c:url>"></script>
	
	<script type="text/javascript">
	
		$("#btnUsaJSON").click(function(){
			
			var data = $("#form1").serialize();
			$("#tablaDatos").empty();
			
			$.get("traerCliente", data, function(rec){
							
				if (rec.codigo != "0"){
				
					var rowData = "<tr>";	
					rowData += "<td>" + rec.codigo + "</td>";
					rowData += "<td>" + rec.paterno + "</td>";
					rowData += "<td>" + rec.materno + "</td>";
					rowData += "<td>" + rec.nombre + "</td>";
					rowData += "</tr>";
					
					$("#tablaDatos").html(rowData);
					
				}
				
			});
			
		});
	
	</script>
	
</body>
</html>