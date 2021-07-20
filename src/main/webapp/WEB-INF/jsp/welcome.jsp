<html>
<body>
<%
String message = "";
// Get hasAccess from string and convert to Boolean object
boolean hasAccess = Boolean.parseBoolean((String)request.getAttribute("hasAccess"));
if(!hasAccess)
{
  message = "You shall not pass";
}
%>
<h3>Bienvenido</h3>
<%= message %>
</body>
</html>