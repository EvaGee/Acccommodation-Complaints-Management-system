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
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="studentUI.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-home"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Home</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="studentUI.jsp">
          <i class="fas fa-list"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="status.jsp">
          <i class="fas fa-fw fa-cog"></i>
          <span>Complaints</span>
        </a>
        
      </li>

      
      <!-- Divider -->
      <hr class="sidebar-divider">

      <li class="nav-item">
        <a class="nav-link" href="submitComplaint.jsp">
          <i class="fas fa-folder"></i>
          <span>Submit A complain</span></a>
      </li>
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
          <p class="lead">Reg No</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_NUMBER")%></p>
        </div>
      </div>
        <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Hostel</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_HOSTEL")%> </p>
        </div>
      </div>
        <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Block</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_BLOCK")%></p>
        </div>
      </div>
        <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Room No</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_ROOM_NUMBER")%></p>
        </div>
      </div>
        <div class="row">
        <div class="col-lg-4 ml-auto">
          <p class="lead">Email</p>
        </div>
        <div class="col-lg-4 mr-auto">
          <p class="lead"><%=session.getAttribute("USER_EMAIL")%></p>
        </div>
      </div>
        

     
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


		        	</select>
                  </div>
                  <div class="form-group">
                    <label for="complaint_content">Complaint Content:</label>
                    <textarea rows="5" cols="50" class="form-control" id="complaint_content" name="complaint_content" placeholder="Complaint Content"></textarea>
                  </div>
                  
                  
  		          <input type="hidden" id="complaint_author_id" name="complaint_author_id" value="<%=session.getAttribute("USER_ID")%>">
               
                  <button type="submit" class="btn btn-success btn-block">Submit</button>
            </form>
            <!-- Form End-->
		</div>
	</div>
	<script type="text/javascript">
	 function formValidate()                                    
     {               
         var complaint_content = document.forms["complaintForm"]["complaint_content"];

         if (complaint_content.value == "")                                  
         { 
             window.alert("Please enter the Complaint Description."); 
             complaint_content.focus(); 
             return false; 
         }

         return true; 
     }	
	</script>
<%@ include file="/includes/footer.jsp" %>