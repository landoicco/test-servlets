<html>
 <%
   String message = "";
   // Get wrongData from string and convert to boolean
   boolean wrongData = Boolean.parseBoolean((String)request.getAttribute("wrongData"));
   if(wrongData){
      message = "*Datos no validos*";
   }
 %>
      <!-- BOOTSTRAP -->

      <head>
          <!-- Bootstrap css -->
          <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
          <meta charset="utf-8" name="theme-color">
          <title>Title</title>

      <style>
            .bd-placeholder-img {
              font-size: 1.125rem;
              text-anchor: middle;
              -webkit-user-select: none;
              -moz-user-select: none;
              user-select: none;
            }

            @media (min-width: 768px) {
              .bd-placeholder-img-lg {
                font-size: 3.5rem;
              }
            }
            html,
      body {
        height: 100%;
      }

      body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        width: 100%;
        max-width: 330px;
        padding: 15px;
        margin: auto;
      }

      .form-signin .checkbox {
        font-weight: 400;
      }

      .form-signin .form-floating:focus-within {
        z-index: 2;
      }

      .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
      }

      .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
      }
          </style>
      </head>
      <body class="text-center">

      <main class="form-signin">
          <form action="/LandoTest/web/update" method="POST">
              <h1 class="h3 mb-3 fw-normal">Actualiza tus datos</h1>
              <br>
              <div class="form-floating">
                  <input type="text" class="form-control" id="floatingInput" placeholder="PrimerNombre" name="firstname">
                  <label for="floatingInput">Primer Nombre</label>
              </div>
              <div class="form-floating">
                  <input type="text" class="form-control" id="floatingInput" placeholder="Apellido" name="lastname">
                  <label for="floatingPassword">Apellido</label>
              </div>
              <div class="form-floating">
                  <input type="text" class="form-control" id="floatingInput" placeholder="Age" name="age">
                  <label for="floatingPassword">Edad</label>
              </div>
              <div class="form-floating">
                  <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                  <label for="floatingPassword">Password</label>
              </div>
              <p style="color:red"> <%= message %> </p>
              <button class="w-100 btn btn-lg btn-primary" type="submit">Actualizar</button>
              <p class="mt-5 mb-3 text-muted">Lando Inc. 2021</p>
          </form>
      </main>

      <!-- Bootstrap js -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>