<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">


  <title>Egerton University</title>

  <link rel="icon" type="image/jpg" href="images/egerlogo.jpgg"/>
  <link href="css/freelancer.css" rel="stylesheet">
     <link href="css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
  <script>
  function showAlert(){
	  alert("Kindly update your password");
  }
  </script> 

  
       
</head>

<body id="page-top" onload="showAlert()">

  <!-- Navigation -->
  <ul class="navbar-nav ml-auto">
          <!-- Navigation -->
  <nav class="navbar navbar-expand-lg text-uppercase bg-success" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Welcome</a>
      <a class="navbar-brand js-scroll-trigger" href="#page-top"><%=session.getAttribute("USER_FIRSTNAME")%> <%=session.getAttribute("USER_LASTNAME")%> </a>
     
      <a class="navbar-brand js-scroll-trigger" href="#page-top"><%=session.getAttribute("USER_NUMBER")%></a>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          
          <li class="nav-item mx-0 mx-lg-1">
              <a href="#"  data-toggle="dropdown"><img src="images/user.png"><span class="dropdown-toggle"></span>                       
              <div class="dropdown-menu dropdown-menu-right user-dd animated">
                            <a class="dropdown-item" href="#ViewProfile"><span class="fa fa-user"></span> My Profile</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout"><i class="fa fa-power-off m-r-5 m-l-5"></i> Logout</a>
              </div>
              </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
      
      </ul>
     
      
  <div id="wrapper">

   
  <ul class="navbar-nav bg-gradient-light sidebar  sidebar-brand" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="profile.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-home"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Home</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
       <li class="nav-item active">
        <a class="nav-link" href="profile.jsp">
          <i class="fas fa-list"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

     
        </ul> 

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">
    
            <section class="page-section" id="about">

            <div class="container">

      <!-- About Section Heading -->
      <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Your Profile</h2>

      <!-- Icon Divider -->
      <div class="divider-custom ">
        <div class="divider-custom-line"></div>
        <div class="divider-custom-icon">
          <i class="fas fa-star"></i>
        </div>
        <div class="divider-custom-line"></div>
      </div>
<%
					String driverName = "com.mysql.cj.jdbc.Driver";
					String connectionUrl = "jdbc:mysql://localhost:3306/accommodation_complaints_feedback_system";
					String userId = "root";
					String password = "";
					
					try {
					Class.forName(driverName);
					}catch (ClassNotFoundException e) {
					e.printStackTrace();
					}
					
					Connection connection = null;
					Statement statement = null;
					ResultSet resultSet = null;
					
					try{
						connection = DriverManager.getConnection(connectionUrl, userId, password);
						statement=connection.createStatement();
						String sql ="SELECT * FROM users WHERE user_id = "+session.getAttribute("USER_ID");
					
						resultSet = statement.executeQuery(sql);
						while(resultSet.next()){
				%>
       <!-- About Section Content -->
      <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Name</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_FIRSTNAME")%> <%=session.getAttribute("USER_LASTNAME")%></p>
        </div>
      </div>
        <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Staff No</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_NUMBER")%> </p>
        </div>
      </div>
        
        <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Email</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_EMAIL")%> </p>
        </div>
      </div>
      <form action="/update_user" method="post">
      <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Username</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <div class="form-group">
                    <input type="text" class="form-control" name="username"  minlength="5" value="<%out.println(resultSet.getString("username")); %>">
                </div>
        </div>
      </div>
       <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Password</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <div class="form-group">
                    <input type="text" class="form-control" name="password" placeholder="Password" minlength="6" value="<%out.println(resultSet.getString("password")); %>">
                </div>
        </div>
      </div>
      <div class="row">
      <div class="col-lg-4 ml-auto">
          <p class="lead"></p>
        </div>
        <div class="col-lg-4 mr-auto">
        <p class="lead"><button type="submit" class="btn btn-success btn-block">Update</button></p>
        </div>
      </div>
      <input type="hidden" name="user_id" value="<%out.println(resultSet.getString("user_id")); %>">
      <input type="hidden" name="user_hostel" value="<%out.println(resultSet.getString("user_hostel")); %>">
       <input type="hidden" name="user_block" value="<%out.println(resultSet.getString("user_block")); %>">
       <input type="hidden"  name="user_room_number" value="<%out.println(resultSet.getString("user_room_number")); %>">
       <input type="hidden" name="user_firstname" value="<%out.println(resultSet.getString("user_firstname")); %>">
       <input type="hidden" name="user_lastname" value="<%out.println(resultSet.getString("user_lastname")); %>">
       <input type="hidden"  name="user_number" value="<%out.println(resultSet.getString("user_number")); %>">
       <input type="hidden" name="user_role" value="<%out.println(resultSet.getString("user_role")); %>">
       <input type="hidden"  name="user_email" value="<%out.println(resultSet.getString("user_email")); %>">
      </form>
        
<%
   }

   } catch (Exception e) {
   e.printStackTrace();
   }
%>
     
            </div>
    </section>
          
      </div>
   </div>

  </div>
  <!-- Copyright Section -->
  <section class="bg-success py-4 text-center text-white fixed-bottom">
    <div class="container">
      <big>Copyright &copy; Egerton University 2021</big>
    </div>
  </section>

  

  <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
  <div class="scroll-to-top d-lg-none position-fixed ">
    <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
      <i class="fa fa-chevron-up"></i>
  </div>

 
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Plugin JavaScript -->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="js/jqBootstrapValidation.js"></script>
  <script src="js/contact_me.js"></script>
  <!-- Custom scripts for this template -->    </a>

  <script src="js/freelancer.min.js"></script>

</body>

</html>

