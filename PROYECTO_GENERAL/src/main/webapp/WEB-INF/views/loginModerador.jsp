
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
   <link href="<c:url value="/resources/css/login.css" />" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
 <div class="container">
        <div class="card card-container">
            <img id="profile-img" class="profile-img-card" src="<c:url value="/resources/img/avatar_2x.png"   />" />
            <p id="profile-name" class="profile-name-card"></p>
            <form method="post" accept-charset="utf-8" action="login1" name="loginform" autocomplete="off" role="form" class="form-signin">
			               
			                
                          <c:if test="${requestScope.mensaje7 != null}">
               		<div class="alert alert-danger fade in" id="success-alert">
				      
				        <strong>${requestScope.mensaje7}</strong>
				    </div>
				    </c:if>

			                <span id="reauth-email" class="reauth-email"></span>
                <input class="form-control"  placeholder="Usuario" name="loginuser" type="text" id=""  required>
                <input class="form-control" placeholder="Contraseña" name="loginpassword" id="" type="password"  required>
              <p style="backface-visibility: hidden;"><br style="backface-visibility: hidden;"><a href="#" id="flip-btn" class="signup signup_link" style="backface-visibility: hidden;">Olvidaste tu contraseña</a></p>
                    
                <button type="submit" class="btn btn-lg btn-success btn-block btn-signin" name="login" id="submit">Iniciar Sesión</button>
          
           
            </form><!-- /form -->
            
        </div><!-- /card-container -->
    </div><!-- /container -->
  </body>
</html>

