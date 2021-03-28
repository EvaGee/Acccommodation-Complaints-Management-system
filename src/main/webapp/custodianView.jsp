

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">


  <title>Egerton University</title>

  <link rel="icon" type="image/jpg" href="images/egerlogo.jpg"/>  
  <link href="css/freelancer.css" rel="stylesheet">
     <link href="css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template-->
   

  
       
</head>

<body id="page-top">

  <!-- Navigation -->
  <ul class="navbar-nav ml-auto">
          <!-- Navigation -->
  <nav class="navbar navbar-expand-lg text-uppercase bg-success" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="#page-top">Welcome</a>
      <a class="navbar-brand js-scroll-trigger" href="#page-top"><%=session.getAttribute("USER_FIRSTNAME")%> <%=session.getAttribute("USER_LASTNAME")%> </a>
     
      <a class="navbar-brand js-scroll-trigger" href="#page-top"> <%=session.getAttribute("USER_NUMBER")%> </a>
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
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="custodianUI.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-home"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Home</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="custodianUI.jsp">
          <i class="fas fa-list"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>Complaints</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
          	 <a class="collapse-item" href="custodianView.jsp">View Complaints</a>
            <a class="collapse-item" href="assignedComplaints.jsp">Assigned</a>
            <a class="collapse-item" href="custodianWorkspace.jsp">Workspace</a>
            <a class="collapse-item" href="custodianDoneComplaints.jsp">Done Complaints</a>
            
          </div>
        </div>
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
     <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">View complaints</h2>

      <!-- Icon Divider -->
      <div class="divider-custom ">
        <div class="divider-custom-line"></div>
        <div class="divider-custom-icon">
          <i class="fas fa-star"></i>
        </div>
        <div class="divider-custom-line"></div>
      </div>
     <%@page import="java.sql.DriverManager"%>
			<%@page import="java.sql.ResultSet"%>
			<%@page import="java.sql.Statement"%>
			<%@page import="java.sql.Connection"%>

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
			%>

       <table class="table table-secondary container">
                <thead>
                 <tr>
                    
                    <td>Complaint Category</td>
				<td>Complaint Content</td>
				<td>Complaint Author: (ID)</td>
				<td>Complaint Status</td>
				<td>Complaint Approved By: (ID)</td>
				<td>Assign to:</td>
                  </tr>
                  
                </thead>
                <tbody>
                <%
                try{
                	connection = DriverManager.getConnection(connectionUrl, userId, password);
        			statement=connection.createStatement();
        			String sql ="SELECT * FROM complaints WHERE complaint_status = 'approved' AND complaint_assigned_to = 0 ORDER BY complaint_id DESC";

        			resultSet = statement.executeQuery(sql);
        			while(resultSet.next()){
        			%>
        			<tr>
        				<td><%out.println(resultSet.getString("complaint_category")); %></td>
        		    	<td><%out.println(resultSet.getString("complaint_content")); %></td>
        		    	<td><a href='user/<%out.println(resultSet.getString("complaint_author_id")); %>'><%out.println(resultSet.getString("complaint_author_id")); %></a></td>
        		    	<td><%out.println(resultSet.getString("complaint_status")); %></td>
        		    	<td><a href='user/<%out.println(resultSet.getString("complaint_approved_or_rejected_by")); %>'><%out.println(resultSet.getString("complaint_approved_or_rejected_by")); %></a></td>
        		    	<%if(resultSet.getString("complaint_category").equals("plumber")){ %>
        		    	<td>><a href='custodianUI.jsp/plumber/<%out.println(resultSet.getString("complaint_id")); %>'>Plumber</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("mason")){ %>
        		    	<td><a href='custodianUI.jsp/mason/<%out.println(resultSet.getString("complaint_id")); %>'>Mason</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("carpenter")){ %>
        		    	<td><a href='custodianUI.jsp/carpenter/<%out.println(resultSet.getString("complaint_id")); %>'>Carpenter</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("security")){ %>
        		    	<td><a href='custodianUI.jsp/security/<%out.println(resultSet.getString("complaint_id")); %>'>Security</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("electrician")){ %>
        		    	<td><a href='custodianUI.jsp/electrician/<%out.println(resultSet.getString("complaint_id")); %>'>Electrician</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("health")){ %>
        		    	<td><a href='custodianUI.jsp/health/<%out.println(resultSet.getString("complaint_id")); %>'>Health</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("painter")){ %>
        		    	<td><a href='custodianUI.jsp/painter/<%out.println(resultSet.getString("complaint_id")); %>'>Painter</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("cleaner")){ %>
        		    	<td><a href='custodianUI.jsp/cleaner/<%out.println(resultSet.getString("complaint_id")); %>'>Cleaner</a></td>
        		    	<% } else if(resultSet.getString("complaint_category").equals("custodian") || resultSet.getString("complaint_category").equals("others") ){ %>
        		    	<td><a href='custodianUI.jsp/custodian/<%out.println(resultSet.getString("complaint_id")); %>'>Custodian</a></td>
        		    	<% } %>
        			</tr>
			
			<% 
			}
			
			} catch (Exception e) {
			e.printStackTrace();
			}
			%>
			</tbody>
			</table>
        

     
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

