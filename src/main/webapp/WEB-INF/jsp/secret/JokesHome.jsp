<html>
<%@ page import="database.user.UserDTO" %>
<%
// Get Colab name
String colabName = session.getAttribute("user") != null ? ((UserDTO)session.getAttribute("user")).getFirstName() : null;
%>
<%@include file="/WEB-INF/html/header.html"%>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>

        $(document).ready(function(){
         $(".jokelinks").click(function(){
           var jokeRequested = $(this).attr("id");
           getJoke(jokeRequested);
          });
         });

        function getJoke(joke){

          if(joke == "home"){
             $("#question").text("Bienvenido, <%= colabName %>");
             $("#answer").text("Sigue los link del header!!");
             return;
          }

          $.getJSON("/LandoTest/web/jokes?joke=" + joke, function(jokeResponse){
                $("#question").text(jokeResponse.Question);
                $("#answer").text(jokeResponse.Answer);
          });

        }

        </script>
<body>
<h2 style="text-align:center;" id="question">Bienvenido, <%= colabName %></h2>
          <br>
          <p id="answer" style="text-align:center;">Sigue los link del header!!</p>
          <br>
<%@include file="/WEB-INF/html/footer.html"%>
</body>
</html>