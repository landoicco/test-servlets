<html>
<body>
<%
// Get hasAccess from string and convert to boolean
boolean hasAccess = Boolean.parseBoolean((String)request.getAttribute("hasAccess"));
if(!hasAccess)
{
  // Incluir context path para que encuentre el jsp
  response.sendRedirect(request.getContextPath() + "/index.html");
}
%>
<h3>Bienvenido</h3>
</body>
</html>