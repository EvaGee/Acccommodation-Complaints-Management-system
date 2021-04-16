<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

    <div class="container-fluid bg">
<button type="button" class="btn btn-success btn-lg" onClick="window.location.href='/custodianView.jsp'">Back</button>
    <br>
		<div class="container">
				<!-- Form Start -->
            <form class="form-container" method="post" action="/assignToWorker" id="assigningForm" name="assigningForm" onsubmit="return formValidate()">
                <br>
                <h1>Complaint Assigning Form</h1>
               	
                  <div class="form-group">
                	<label for="hostel">Select hostel:</label><br>
                    <select name="hostel" id="hostel">
			            <!-- Default -->
			            <option value="">Select Hostel </option>
			            
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
								String sql ="SELECT * FROM hostel WHERE vacancy = 'vacant' ORDER BY id DESC";

								resultSet = statement.executeQuery(sql);
								while(resultSet.next()){
						%>
						
						<option><%out.println(resultSet.getString("hostel")); %></option>
			           <%
					    }
			
					    } catch (Exception e) {
					    e.printStackTrace();
					    }
						%>
		        	</select>
                </div>
                   <div class="form-group">
                	<label for="hostel">Select block:</label><br>
                  <select name="block" id="block">
                  <option value="">Select Block </option>
                  </select>
                  </div>
                  </div>
                   <div class="form-group">
                	<label for="hostel">Select room:</label><br>
                  <select name="room" id="room">
                  <option value="">Select room </option>
                  </select>
                  </div>
  		          <input type="hidden" id="complaint_assigned_by" name="complaint_assigned_by">
               
                  <button type="submit" class="btn btn-success btn-block">Assign</button>
            </form>
            <!-- Form End-->
		</div>
	</div>
		<script>

  $("#hostel").change(function()
		  {
      
   var hostel=$("#hostel").val();
   console.log(hostel);

    $.ajax(
        {url: "ajaxGetHostel",
            type: 'GET',
            data:{hostel:hostel},
            success: function(data)
            {
            
                console.log(hostel);
                console.log(data);
                var j=data.length;
                for (var i=0; i<j; i++){
                $("#block").append('<option value='+data[i].block+'>' +data[i].block+ '</option>')
            }
            }
        });
      
 }); 


  $("#block").change(function()
		  {
	  var hostel=$("#hostel").val();   
   var block=$("#block").val();
   console.log(block);

    $.ajax(
        {url: "ajaxGetHostelAndBlock",
            type: 'GET',
            data:{block:block, hostel:hostel},
            success: function(data)
            {
            
                console.log(block, hostel);
                console.log(data);
                var j=data.length;
                for (var i=0; i<j; i++){
                $("#room").append('<option value='+data[i].roomNumber+'>' +data[i].roomNumber+ '</option>')
            }
            }
        });
      
 }); 
    </script>
	<script type="text/javascript">
	
	 function formValidate()                                    
     { 	
		 var complaint_id = document.forms["assigningForm"]["complaint_id"];  
		 var complaint_assigned_to = document.forms["assigningForm"]["complaint_assigned_to"];  
        
         if (complaint_id.value == "")                                  
         { 
             window.alert("Please enter the Complaint ID."); 
             complaint_id.focus(); 
             return false; 
         } 
         
         if (complaint_assigned_to.value == "")                                  
         { 
             window.alert("Please Assign a Carpenter."); 
             complaint_assigned_to.focus(); 
             return false; 
         } 

         return true; 
     }	
	</script>
