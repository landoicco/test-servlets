<html>
<body>  
 <% 
   String message;
   String wrongData = request.getParameter("wrongData");
   if(wrongData == null)
   {
	   message = "A";
   }
   else
   {
	   message = "*Datos no validos*";
   }
   
 %>

<h1>Acceso a Colaboradores</h1>
	  <fieldset>
	    <legend>Formulario de acceso</legend>
		<form name="acceso" action="/LandoTest/LandoTest/LoginServlet" method="POST">
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
