<html>
<%@ page import="local.user.User" %>
<%
// Get Colab name
String colabName = session.getAttribute("user") != null ? ((User)session.getAttribute("user")).getName() : "null";
%>
<%@include file="/WEB-INF/html/header.html"%>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
        $(document).on("click", "#homebutton", function() {
            goHome();
         });
        $(document).on("click", "#j1button", function() {
            jokeOne();
         });
         $(document).on("click", "#j2button", function() {
             jokeTwo();
          });
         $(document).on("click", "#j3button", function() {
             jokeThree();
          });
         $(document).on("click", "#j4button", function() {
             jokeFour();
          });

            // FUNCTIONS!!!
            function goHome()
            {
            //document.getElementById("answerbutton").style.display = "none";
            $.getJSON("/LandoTest/web/jokes?joke=jokeone", function(jokeResponse) {
              $("#question").text("Bienvenido, <%= colabName %>");
              $("#answer").text("Sigue los link del header!!");
                });
            }
            function jokeOne()
            {
            //document.getElementById("answerbutton").style.display = "block";
              $.getJSON("/LandoTest/web/jokes?joke=jokeone", function(jokeResponse) {
                $("#question").text(jokeResponse.Question);
                $("#answer").text(jokeResponse.Answer);
                  });
            }
            function jokeTwo()
            {
            //document.getElementById("answerbutton").style.display = "block";
              $.getJSON("/LandoTest/web/jokes?joke=joketwo", function(jokeResponse) {
                $("#question").text(jokeResponse.Question);
                $("#answer").text(jokeResponse.Answer);
                  });
            }
           function jokeThree()
           {
           //document.getElementById("answerbutton").style.display = "block";
             $.getJSON("/LandoTest/web/jokes?joke=jokethree", function(jokeResponse) {
               $("#question").text(jokeResponse.Question);
               $("#answer").text(jokeResponse.Answer);
                 });
           }
           function jokeFour()
           {
           //document.getElementById("answerbutton").style.display = "block";
             $.getJSON("/LandoTest/web/jokes?joke=jokefour", function(jokeResponse) {
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