<html>
<%@include file="/WEB-INF/html/header.html"%>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            $(document).on("click", "#somebutton", function() {
                $.getJSON("/LandoTest/web/jokes?joke=jokeone", function(jokeResponse) {
                    $("#answer").text(jokeResponse.Answer);
                });
            });
        </script>
<body>
<h3 style="text-align:center;">Whats the object-oriented way to become wealthy</h3>
          <br>
          <p id="answer" style="text-align:center;"> </p>
          <br>
          <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                      <a>
                          <button  id="somebutton" type="button" class="btn btn-primary btn-lg px-4 gap-3">Get answer!</button>
                      </a>
          </div>
<%@include file="/WEB-INF/html/footer.html"%>
</body>
</html>