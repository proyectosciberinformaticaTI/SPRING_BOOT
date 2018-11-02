$(document).ready(function(){  
	$('.delete_employee').click(function(e){   
	   e.preventDefault();   
	   var empid = $(this).attr('data-emp-id');
	   var parent = $(this).parent("td").parent("tr");   
	   bootbox.dialog({
			message: "¿Estas seguro que desear eliminar el registro?",
			title: "<i class='glyphicon glyphicon-trash'></i> Eliminar",
			buttons: {
				success: {
					  label: "No",
					  className: "btn-success",
					  callback: function() {
					  $('.bootbox').modal('hide');
				  }
				},
				danger: {
				  label: "Si",
				  id:"delete-more",
				  className: "btn-danger",
				  callback: function() {       
				   $.ajax({        
						type: 'POST',
						url: 'eliminaModerador',
						data: 'idmoderador='+empid,
						 success: function(msg) {
					            window.location.reload(true);
					        
						 
						 
						 }           
				  
				   })
				   .done(function(response){        
						bootbox.alert(response);
						parent.fadeOut('slow');        
				   })
				   .fail(function(){        
						bootbox.alert('Error....');               
				   })          
				  
				  }
				}
			}
	   });   
	   
	   
	   
	
	});  






});