<%-- 
    Document   : newlogin
    Created on : Mar 2, 2020, 6:59:08 PM
    Author     : Eva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
	<title>Egerton University</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<link rel="icon" type="image/jpg" href="images/egerlogo.jpg"/>
	
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
    </head>

    <body >
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
	<div class="limiter">
            <div class="container-login100" style="background-image: url('images/gate1.jpg');">
		<div class="wrap-login100">
                    <form class="login100-form validate-form" method="post" action="/registerUser" name="login_form" id="login_form">
                       
                        <span class="login100-form-title p-b-34 p-t-27">
                            Register Student 
						</span>
					<p>${message}</p>
					<div class="login100-form">
                            <input type="hidden" id="user_role" name="user_role" value="student">
                      </div>
                      <div class="login100-form">
                          <label for="staffId">User Number:</label>
                          <input type="text" class="input100" id="reg_no" name="user_number" placeholder="Registration Number" pattern="[A-Z][0-9]+/[0-9]+\/[0-9]{2}$" title="Use this format A12/1234567/17" required>
                        </div>
                        <div class="login100-form">
                          <label for="lname">First Name:</label>
                          <input type="text" class="input100" id="lname" name="user_firstname" placeholder="Last Name" pattern="[A-Za-z']+" title="alphabets and ' only" required>
                        </div>
                        <div class="login100-form">
                          <label for="fname">Last Name:</label>
                          <input type="text" class="input100" id="fname" name="user_lastname" placeholder="First Name" pattern="[A-Za-z']+" title="alphabets and ' only" required>
                        </div>
                     
                        <div class="login100-form">
                          <label for="email">Email Address:</label>
                          <input type="email" class="input100" id="email" name="user_email" placeholder="xyz@abc.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="Use the correct format -----@-----.---" required>
                        </div>
                      
                        <div class="login100-form">
                          <label for="username">Username:</label>
                          <input type="text" class="input100" name="username" id="username" placeholder="Username" required="">
                        </div>

                        <div class="login100-form">
                          <label for="staffId">Hostel</label>
                          <select class="input100" id="hostel_select" name="hostel" required>
                          <option value="">Select Hostel</option>
                          <option>Nairobi</option>
	                        <option>Mombasa</option>
	                        <option>Eldoret</option>
	                        <option>Victoria</option>
	                        <option>Maringo</option>
	                        <option>Elementaita</option>
	                        <option>Thornton</option>
                          </select>
                        </div>
                        <div class="login100-form">
                          <label for="lname">Block</label>
                          <select class="input100" id="block_select" name="block" required>
                          		<option value="0" hostel="Nairobi" class="hide">Select Block</option>
                                <option value="1" hostel="Nairobi" class="hide">1</option>
                                <option value="2" hostel="Nairobi" class="hide">2</option>
                                <option value="3" hostel="Nairobi"class="hide">3</option>
                                <option value="4" hostel="Nairobi" class="hide">4</option>
                                <option value="5" hostel="Nairobi" class="hide">5</option>
                                
                                <option value="0" hostel="Thornton" class="hide">Select Block</option>
                                <option value="Thornton" hostel="Thornton" class="hide">Thornton</option>
                                
                                <option value="0" hostel="Victoria" class="hide">Select Block</option>
                                <option value="Victoria" hostel="Victoria" class="hide">Victoria</option>
                                
                                <option value="0" hostel="Elementaita" class="hide">Select Block</option>
                                <option value="Elementaita" hostel="Elementaita" class="hide">Elementaita</option>
                                
                                <option value="0" hostel="Mombasa" class="hide">Select Block</option>
                                <option value="1" hostel="Mombasa" class="hide">1</option>
                                <option value="2" hostel="Mombasa" class="hide">2</option>
                                <option value="3" hostel="Mombasa" class="hide">3 </option>
                                <option value="4" hostel="Mombasa" class="hide">4</option>
                                <option value="5" hostel="Mombasa" class="hide">5 </option>
                                
                                <option value="0" hostel="Eldoret" class="hide">Select Block</option>
                                <option value="1" hostel="Eldoret" class="hide">1</option>
                                <option value="2" hostel="Eldoret" class="hide">2</option>
                                <option value="3" hostel="Eldoret" class="hide">3 </option>
                                <option value="4" hostel="Eldoret" class="hide">4</option>
                                <option value="5" hostel="Eldoret" class="hide">5 </option>
                                
                                <option value="0" hostel="Maringo" class="hide">Select Block</option>
                                <option value="1" hostel="Maringo" class="hide">1</option>
                                <option value="2" hostel="Maringo" class="hide">2</option>
                                <option value="3" hostel="Maringo" class="hide">3 </option>
                                <option value="4" hostel="Maringo" class="hide">4 </option>
                                <option value="5" hostel="Maringo" class="hide">5</option>
                                
                         		
                                </select>
                        </div>
                      
                        <div class="login100-form">
                          <label for="room">Room Number:</label>
                          <select class="input100" id="room" name="user_room_number" required>
                        	<option value="">Select Room </option>
                        	</select>
                        </div>
                       <input type="hidden" class="input100" id="bed" name="bedNo" value="" required>

            
           
                         <br>
                         
                        <center>
                              <button type="submit" class="login100-form-btn">Register</button>
                        </center>
                    </form>
                    <br>
                        <center>
                            <a href="adminUI.jsp">
                                <button type="submit" class="login100-form-btn">Cancel</button>
                            </a>
                        </center>
                </div>
        
            </div>
        </div>
<script>
<!--
$(document).ready(function(){
	 $(function(){
		 var vacancy="vacant";
    $.ajax(
	        {url: "ajaxGetAllHostels",
	            type: 'GET',
	            data:{vacancy:vacancy},
	            success: function(data)
	            {
	            	 console.log(data);
	            	var j=data.length;
	                for (var i=0; i<j; i++){
	                	 $("#hostel").append('<option value='+data[i].hostel+'>' +data[i].hostel+ '</option>')
	               
	            }
	            }
	        });
	   
	 }); 
	});

  $("#hostel").change(function()
		  {
      
   var hostel=$("#hostel").val();
   var vacancy="vacant";
   console.log(hostel);

    $.ajax(
        {url: "ajaxGetHostel",
            type: 'GET',
            data:{hostel:hostel, vacancy:vacancy},
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
-->
document.getElementById('hostel_select').addEventListener("change", function() {
	  var val = this.value;
	  var options = document.getElementById('block_select').options;
	  var new_val = null;
	  for (var i = 0 ; i < options.length; i++) {
	    if (options[i].attributes["hostel"].value === val) {
	      if (!new_val) {
	        new_val = options[i].value;
	      }
	      options[i].classList.remove("hide");
	    } else {
	      options[i].classList.add("hide");
	    }
	  }
	  document.getElementById('block_select').value = new_val;  

	});
	                    </script>
	                    <script>
	

  $("#block_select").change(function()
		  {
	  var hostel=$("#hostel_select").val();   
   var block=$("#block").val();
   var vacancy="vacant";
   console.log(block);

    $.ajax(
        {url: "ajaxGetHostelAndBlock",
            type: 'GET',
            data:{hostel:hostel, block:block, vacancy:vacancy},
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
  
  $("#room").change(function()
		  {
	  var hostel=$("#hostel_select").val();   
   var block=$("#block_select").val();
   var room=$("#room").val();
   var vacancy="vacant";
   console.log(vacancy);

    $.ajax(
        {url: "ajaxGetHostelAndBlockAndRoom",
            type: 'GET',
            data:{block:block, hostel:hostel, room:room, vacancy:vacancy},
            success: function(data)
            {
            
                console.log(hostel, block, room);
                console.log(data);
                var j=data.length;
                for (var i=0; i<j; i++){
                	
                $("#bed").val(data[0].bedNo);
                
            }
            }
        });
      
 }); 
    </script>      
	
  </body>
</html>
