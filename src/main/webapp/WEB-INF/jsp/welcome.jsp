<html>
<%
// Get hasAccess from string and convert to boolean
boolean hasAccess = Boolean.parseBoolean((String)request.getAttribute("hasAccess"));
if(!hasAccess){
  // Incluir context path para que encuentre el jsp
  response.sendRedirect(request.getContextPath() + "/index.html");
}
//RequestDispatcher headerRD = getServletContext().getRequestDispatcher("/html/header.html");
//headerRD.include(request, response);
//pageContext.include("/html/header.html");
%>
<%@include file="/WEB-INF/html/header.html"%>
<body>
<h3>Bienvenido</h3>
<%@include file="/WEB-INF/html/footer.html"%>
<%
//RequestDispatcher footerRD = getServletContext().getRequestDispatcher("/html/footer.html");
//footerRD.include(request, response);
//pageContext.include("/html/footer.html");
%>
</body>
</html>