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
                          <label for="block">Hostel:</label>
                           <select class="input100" id="hostel" name="user_hostel" required>
                        	<option value="">Select Hostel </option>
                        	
                        	</select>
                        </div>
                      
                       
                    <div class="login100-form">
                          <label for="block">Block:</label>
                           <select class="input100" id="block" name="user_block" required>
                        	<option value="">Select Block </option>
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
	            	var filteredData=[];
	            	var unique=[];
	            	data.filter(function (data){
	            		if (unique.indexOf(data.hostel)<0){
	            			unique.push(data.hostel);
	            			filteredData.push(data);
	            		
	            		}
	            	});
	            	
	            	$.each(filteredData, function(i,item){
	            		$("#hostel").append('<option value='+item.hostel+'>' +item.hostel+ '</option>')
	            	});
	               
	            
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
                var filteredData2=[];
            	var unique2=[];
            	data.filter(function (data){
            		if (unique2.indexOf(data.block)<0){
            			unique2.push(data.block);
            			filteredData2.push(data);
            		
            		}
            	});
            	
            	$.each(filteredData2, function(i,item){
            		$("#block").append('<option value='+item.block+'>' +item.block+ '</option>')
            	});
               
            }
        });
      
 }); 


  $("#block").change(function()
		  {
	  var hostel=$("#hostel").val();   
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
                var filteredData1=[];
            	var unique1=[];
            	data.filter(function (data){
            		if (unique1.indexOf(data.roomNumber)<0){
            			unique1.push(data.roomNumber);
            			filteredData1.push(data);
            		
            		}
            	});
            	
            	$.each(filteredData1, function(i,item){
            		$("#room").append('<option value='+item.roomNumber+'>' +item.roomNumber+ '</option>')
            	});
                
            }
        });
      
 }); 
  
  $("#room").change(function()
		  {
	  var hostel=$("#hostel").val();   
   var block=$("#block").val();
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
