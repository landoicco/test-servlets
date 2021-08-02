<html>
<%
// Get Colab name
String colabName = (String)session.getAttribute("name");
%>
<%@include file="/WEB-INF/html/header.html"%>
<body>
   <h1 style="text-align:center;">Bienvenido, <%= colabName %></h1>
   <p style="text-align:center;">
     Sigue los link del header!!
   </p>
<%@include file="/WEB-INF/html/footer.html"%>
</body>
</html>