
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<!DOCTYPE html>
<html lang="es">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
  <title>Simple Invoice | Login</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <!-- CSS  -->
   <link href='<c:url value="/resources/css/login.css"/>' type="text/css" rel="stylesheet" media="screen,projection"/>
<style type="text/css">

body,html{
 height: 100%;
    background-repeat: no-repeat;
 background: url(http://www.utel.edu.mx/blog/administracion-en-linea/wp-content/uploads/2015/06/shutterstock_270635402.jpg) no-repeat center center fixed;
  background-size: 100% 100%;
}


</style>


</head>
<body>
 <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="resources/img/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form method="post" accept-charset="utf-8" action="loginModeradores" name="loginform"  role="form" class="form-signin">
			  
			                       <c:if test="${requestScope.error != null}">
                	<div  style="margin: 16px;" class="alert alert-danger" role="alert">
                		<p ><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                		<strong>${requestScope.error}</strong></p>
                	</div>
                </c:if>
			                <span id="reauth-email" class="reauth-email"></span>
                <input class="form-control" placeholder="Usuario" name="usuCodigo" type="text"  required>
                <input class="form-control" placeholder="Contraseña" name="usuPassword" type="password"   required>
                <button type="submit"  class="btn btn-lg btn-success btn-block btn-signin" name="login" id="submit">Iniciar Sesión</button>
            
            
          
            
            </form><!-- /form -->
            
        </div><!-- /card-container -->
    </div><!-- /container -->
  </body>
</html>

