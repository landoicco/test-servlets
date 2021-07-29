<html>
<%
// Get Colab name
String colabName = (String)session.getAttribute("name");
%>
<%@include file="/WEB-INF/html/header.html"%>
<body>
<h3>Bienvenido, <%= colabName %></h3>
<%@include file="/WEB-INF/html/footer.html"%>
</body>
</html>