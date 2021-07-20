<html>
<body>  
 <% 
   String message = "";
   // Get wrongData from string and convert to boolean
   boolean wrongData = Boolean.parseBoolean((String)request.getAttribute("wrongData"));
   if(wrongData)
   {
      message = "*Datos no validos*";
   }
 %>

<h1>Acceso a Colaboradores</h1>
	  <fieldset>
	    <legend>Formulario de acceso</legend>
		<form name="acceso" action="/LandoTest/LandoTest/login.html" method="POST">
		  Colaborador <input type="text" name="colaborador">
		  <br><br>
		  Password: <input type="password" name="password">
          <br>
          <p style="color:red"><%= message %></p>
          <br> 		  
		  <input type="submit" value="Enviar"/>
		 </form>
      </fieldset>
</body>
</html>
