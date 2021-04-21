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
	<style>
	.hide {
	  display: none
	}
</style>
    </head>

    <body >
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
	<div class="limiter">
            <div class="container-login100" style="background-image: url('images/gate1.jpg');">
		<div class="wrap-login100">
                    <form class="login100-form validate-form" method="post" action="/registerHostel" name="login_form" id="login_form" onsubmit="return validateForm()">
                       <span class="login100-form-logo">
                            <img src='images/egerlogo.jpg' alt='logo' width="200%" height="100%">
                        </span>
                        <span class="login100-form-title p-b-34 p-t-27">
                            Register Hostel
						</span>
						<p>${message}</p>
					<div class="login100-form">
                            <input type="hidden" id="user_role" name="user_role" value="student">
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
                       <div class="login100-form ">
                    <label for="roomNumber">Room Number:</label>
                     <select class="input100" id="room_select" name="room">
                                <option value="0" hostel="Nairobi" class="hide">Select Room</option>
                                <option value="1" hostel="Nairobi" class="hide">1</option>
                                <option value="2" hostel="Nairobi" class="hide">2</option>
                                <option value="3" hostel="Nairobi"class="hide">3</option>
                                
                                <option value="0" hostel="Mombasa" class="hide">Select Room</option>
                                <option value="1" hostel="Mombasa" class="hide">1</option>
                                <option value="2" hostel="Mombasa" class="hide">2</option>
                                <option value="3" hostel="Mombasa" class="hide">3 </option>
                                
                                <option value="0" hostel="Eldoret" class="hide">Select Room</option>
                                <option value="1" hostel="Eldoret" class="hide">1</option>
                                <option value="2" hostel="Eldoret" class="hide">2</option>
                                <option value="3" hostel="Eldoret" class="hide">3 </option>
                                
                                <option value="0" hostel="Maringo" class="hide">Select Room</option>
                                <option value="1" hostel="Maringo" class="hide">1</option>
                                <option value="2" hostel="Maringo" class="hide">2</option>
                                <option value="3" hostel="Maringo" class="hide">3 </option>
                                <option value="4" hostel="Maringo" class="hide">4 </option>
                                
                                <option value="0" hostel="Victoria" class="hide">Select Room</option>
                                <option value="1" hostel="Victoria" class="hide">1</option>
                                <option value="2" hostel="Victoria" class="hide">2</option>
                                <option value="3" hostel="Victoria" class="hide">3</option>
                                <option value="4" hostel="Victoria" class="hide">4</option>
                                <option value="5" hostel="Victoria" class="hide">5</option>
                                <option value="6" hostel="Victoria" class="hide">6</option>
                                <option value="7" hostel="Victoria" class="hide">7</option>
                                <option value="8" hostel="Victoria" class="hide">8</option>
                                <option value="9" hostel="Victoria" class="hide">9</option>
                                <option value="10" hostel="Victoria" class="hide">10</option>
                                
                                <option value="0" hostel="Elementaita" class="hide">Select Room</option>
                                <option value="1" hostel="Elementaita" class="hide">1</option>
                                <option value="2" hostel="Elementaita" class="hide">2</option>
                                <option value="3" hostel="Elementaita" class="hide">3</option>
                                <option value="4" hostel="Elementaita" class="hide">4</option>
                                <option value="5" hostel="Elementaita" class="hide">5</option>
                                <option value="6" hostel="Elementaita" class="hide">6</option>
                                <option value="7" hostel="Elementaita" class="hide">7</option>
                                <option value="8" hostel="Elementaita" class="hide">8</option>
                                <option value="9" hostel="Elementaita" class="hide">9</option>
                                <option value="10" hostel="Elementaita" class="hide">10</option>
                                
                                <option value="0" hostel="Thornton" class="hide">Select Room</option>
                                <option value="1" hostel="Thornton" class="hide">1</option>
                                <option value="2" hostel="Thornton" class="hide">2</option>
                                <option value="3" hostel="Thornton" class="hide">3</option>
                                <option value="4" hostel="Thornton" class="hide">4</option>
                                <option value="5" hostel="Thornton" class="hide">5</option>
                                <option value="6" hostel="Thornton" class="hide">6</option>
                                <option value="7" hostel="Thornton" class="hide">7</option>
                                <option value="8" hostel="Thornton" class="hide">8</option>
                                <option value="9" hostel="Thornton" class="hide">9</option>
                                <option value="10" hostel="Thornton" class="hide">10</option>
                                </select>
                     
                        
                          <input type="hidden" class="input100" id="bedNo" name="bedNo" readonly>
                        
                        <br>
                         
                        <center>
                              <button type="submit" class="login100-form-btn">Register</button>
                        </center>
                    </form>
                    <br>
                       <center>
                          <button class="login100-form-btn">    <a href="adminUI.jsp">Cancel</a></button>
                        </center> 
                </div>
        
            </div>
        </div>
<script>
function validateForm()                                    
{ 
	if (document.getElementById('block_select').value =="0")                      
    { 
        alert("Select block please"); 
        block_select.focus(); 
        return false; 
    } 
    
	if (document.getElementById('room_select').value =="0")                      
    { 
        alert("Select room please"); 
        room_select.focus(); 
        return false; 
    } 
   


    return true; 
}
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
	document.getElementById('hostel_select').addEventListener("change", function() {
	  var val = this.value;
	  var options = document.getElementById('room_select').options;
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
	  document.getElementById('room_select').value = new_val;  

	});
	
	document.getElementById('room_select').addEventListener("change", function() {
		  var room=document.getElementById('room_select').value;
		  var block=document.getElementById('block_select').value;
		  var hostel=document.getElementById('hostel_select').value;
		    
		if(room==="1" && hostel==="Maringo"){
			 document.getElementById('bedNo').value = 1;
		}
		if(room==="2" && hostel==="Maringo"){
			 document.getElementById('bedNo').value = 2;
		}
		
		if(room==="3" && hostel==="Maringo"){
			 document.getElementById('bedNo').value = 4;
		}
		if((room==="4") && hostel==="Maringo"){
			 document.getElementById('bedNo').value = 4;
		}
		
		if(room==="1" && hostel==="Nairobi"){
			 document.getElementById('bedNo').value = 1;
		}
		if(room==="2" && hostel==="Nairobi"){
			 document.getElementById('bedNo').value = 4;
		}
		
		if(room==="3" && hostel==="Nairobi"){
			 document.getElementById('bedNo').value = 4;
		}
		
		
		if(room==="1" && hostel==="Eldoret"){
			 document.getElementById('bedNo').value = 1;
		}
		if(room==="2" && hostel==="Eldoret"){
			 document.getElementById('bedNo').value = 4;
		}
		
		if(room==="3" && hostel==="Eldoret"){
			 document.getElementById('bedNo').value = 4;
		}
		
		if(room==="1" && hostel==="Mombasa"){
			 document.getElementById('bedNo').value = 1;
		}
		if(room==="2" && hostel==="Mombasa"){
			 document.getElementById('bedNo').value = 4;
		}
		
		if(room==="3" && hostel==="Mombasa"){
			 document.getElementById('bedNo').value = 4;
		}
		
		if(hostel==="Elementaita"){
			 document.getElementById('bedNo').value = 6;
		}
		
		if(hostel==="Thornton"){
			 document.getElementById('bedNo').value = 2;
		}
		
		if(hostel==="Victoria"){
			 document.getElementById('bedNo').value = 6;
		}
		});
    </script>      
	
  </body>
</html>
