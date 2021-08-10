function jokeOne(){

 $.getJSON("/LandoTest/web/jokes?joke=jokeone", function(jokeResponse) {
                    $("#answer").text(jokeResponse.Answer);
                });
                 $.getJSON("/LandoTest/web/jokes?joke=jokeone", function(jokeResponse) {
                                                $("#question").text(jokeResponse.Question);
                                            });

}