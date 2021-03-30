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
    </head>

    <body >

	<div class="limiter">
            <div class="container-login100" style="background-image: url('images/gate1.jpg');">
		<div class="wrap-login100">
                    <form class="login100-form validate-form" method="post" action="/register_user" name="login_form" id="login_form">
                        
                        <span class="login100-form-title p-b-34 p-t-27">
                            Register other users 
						</span>

					<div class="login100-form">
                            <label for="category">User role:</label>
                            <select class="input100" id="user_role" name="user_role">
                            <option value="admin">Admin</option>
	                        <option value="halls_officer">Halls Officer</option>
	                        <option value="mason">Mason</option>
	                        <option value="carpenter">Carpenter</option>
	                        <option value="security">Security</option>
	                        <option value="plumber">Plumber</option>
	                        <option value="electrician">Electrician</option>
	                        <option value="cleaner">Cleaner</option>
	                        <option value="health">Health</option>
	                        <option value="painter">Painter</option>
                            </select>
                      </div>
                      <div class="login100-form">
                          <label for="staffId">User Number:</label>
                          <input type="text" class="input100" name="user_number" id="staffId" pattern="[A-Z]+[A-Z]+[0-9]{5,5}" title="2 or 3 capital letters followed by 5 numbers" minlength="7" maxlength="8" required>
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
						<input type="hidden" name="user_hostel" value="">
                            <input type="hidden" name="user_block" value="">
                            <input type="hidden"  name="user_room_number" value="">
                       
            
           
                         <br>
                         
                        <center>
                              <button type="submit" class="login100-form-btn">Register</button>
                        </center>
                    </form>
                    <br>
                        <center>
                            <a href="home.jsp">
                                <button type="submit" class="login100-form-btn">Cancel</button>
                            </a>
                        </center>
                </div>
        
            </div>
        </div>
      
	
  </body>
</html>
