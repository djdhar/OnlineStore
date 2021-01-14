<!DOCTYPE html>

<%@ page import="User.User,java.io.*,Dress.Dress,java.util.*, java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Online Garment Store</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/shop-homepage.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">

</head>




<body>

  <%
  Vector <Dress> dresslist = (Vector<Dress>) request.getAttribute("dresslist");
  User myuser = (User)request.getAttribute("user");
  String gender = (String)request.getAttribute("A");
  %>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Hi <%out.print(myuser.name);%>! Get your personalized garment! </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/OnlineStore/Login?Username=<%out.print(myuser.username);%>&password=<%out.print(myuser.password);%>">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/OnlineStore/LoginPage">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <br>
  <br>

  <!-- Page Content -->
  <div class="container">

    

    <div class="row">
      <h1 class="my-4">Online Garment Store</h1>

      <!--
      <div class="col-lg-3">

        <h1 class="my-4">Online Garment Store</h1>
        <div class="list-group">
          <a href="#" class="list-group-item">Category 1</a>
          <a href="#" class="list-group-item">Category 2</a>
          <a href="#" class="list-group-item">Category 3</a>
        </div>

      </div>
       /.col-lg-3 -->

      <div class="col-lg-9">
        <form class="form-inline mr-auto mb-4" action="/OnlineStore/SearchBox">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="Search" required="">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="Username" value="<%out.print(myuser.username);%>" required="" hidden>
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="password" value="<%out.print(myuser.password);%>" required="" hidden>
          <button class="btn btn-indigo btn-rounded btn-sm my-0 waves-effect waves-light" type="submit">Search</button>
        </form>

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="http://placehold.it/900x350?text=<%out.print(dresslist.get(0).name);%>"+ alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="http://placehold.it/900x350?text=<%out.print(dresslist.get(1).name);%>" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="http://placehold.it/900x350?text=<%out.print(dresslist.get(2).name);%>" alt="Third slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>

        <div class="row">


          <%
            for(int i=0;i<dresslist.size();i++){

                Dress dress = dresslist.get(i);
            

                  out.println( "<div class=\"col-lg-4 col-md-6 mb-4\">");
                  out.println( " <div class=\"card h-100\">");
                  out.println( "    <a href=\"#\"><img class=\"card-img-top\" src=\"http://placehold.it/700x400?text="+dress.name+"\" alt=\"\"></a>");
                  out.println( "    <div class=\"card-body\">");
                  out.println( "      <h4 class=\"card-title\">");

                  out.println( "       <a href=\"#\">");
                    out.println(dress.name);
                  out.println(  "</a>");

                  out.println( "      </h4>");
                  out.println( "      <h5> \u20b9"+dress.price+"</h5>");

                  DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
                  String output = outputFormatter.format(dress.moment);

                  out.println( "      <p class=\"card-text\"> Arrived At : "+ output +" <br> Arrival Time : "+ dress.time +"<br> Discount : "+dress.discount+"% <br>Final Price : \u20b9"+(dress.price-(int)dress.discount*(dress.price/100.0))+"</p>");
                  out.println( "    </div>");
                  out.println( "    <div class=\"card-footer\">");
                  out.println( "      <small class=\"text-muted\">&#9733; &#9733; &#9733; &#9733; &#9734;</small>");
                  out.println( "    </div>");
                  out.println( " </div>");
                  out.println( "</div>");

            }
          %>


        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Developed by Dibyajyoti Dhar</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
