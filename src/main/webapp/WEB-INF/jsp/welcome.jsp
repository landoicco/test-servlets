<html>
<%
// Get hasAccess from string and convert to boolean
boolean hasAccess = Boolean.parseBoolean((String)session.getAttribute("hasAccess"));
String colabName = (String)session.getAttribute("name");
if(!hasAccess){
  // Incluir context path para que encuentre el jsp
  response.sendRedirect(request.getContextPath() + "/index.html");
  }
%>
<%@include file="/WEB-INF/html/header.html"%>
<body>
<h3>Bienvenido, <%= colabName %></h3>
<%@include file="/WEB-INF/html/footer.html"%>
</body>
</html>