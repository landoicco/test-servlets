<html>
<body>
<!--<h2>Lando was here!</h2>
<br>
<br>
<a href="/LandoTest/LandoTest/LoginServlet">Ir a servlet</a>-->

<h1>Acceso a Colaboradores</h1>
	  <fieldset>
	    <legend>Formulario de acceso</legend>
		<form name="acceso" action="/LandoTest/LandoTest/LoginServlet" method="POST">
		  Colaborador <input type="text" name="colaborador">
		  <br><br>
		  Password: <input type="password" name="password">
          <br><br>		  
		  <input type="submit" value="Enviar"/>
		 </form>
      </fieldset>
</body>
</html>
