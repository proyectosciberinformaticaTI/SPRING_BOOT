
//Crear una función recursiva
$(document).ready(function()
{
	$("#enlace").mouseenter(function()
	{
		$("#mensaje").css("display","block");
	});
	
	$("#enlace").mouseleave(function()
	{
		$("#mensaje").css("display","none");	
	});
     

	for(i=0;i<4;i++)
	agregarcontroles();
})
var contador=0;

function agregarcontroles()
{
	
	contador++;
											 //casilla1

	var caja=' Nombres : <input class="form-control" type="file"  disabled  name="fotos"  id="fotos" size="50" />';
		//console.log(casilla);

	$("#controles").append(caja);
	}
function cambio(lugar) {
	console.log(lugar);
	var valor=false;
	var check=document.getElementById('casilla'+lugar+'');
	var caja=document.getElementById('caja'+lugar+'').disabled = false;
if(check.checked){
caja;	
	
}
else{
document.getElementById('caja'+lugar+'').disabled = true;
}
}
function validar(correo)
{

	var patroncorreo=/[a-zA-Z0-9]*$/	

	if(correo==null || !patroncorreo.test(correo))
	{
		alert("Correo Incorrecto");
		return 0;	
	}
	alert("Datos Correctos");
	window.location.href= "bienvenida.html?variable=" + correo;
}
	
	


