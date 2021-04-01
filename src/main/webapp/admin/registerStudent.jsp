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
                            Register Student 
						</span>

					<div class="login100-form">
                            <input type="hidden" id="user_role" name="user_role" value="student">
                      </div>
                      <div class="login100-form">
                          <label for="staffId">User Number:</label>
                          <input type="text" class="input100" id="reg_no" name="user_number" placeholder="Registration Number" pattern="[A-Z][0-9]+/[0-9]+\/[0-9]{2}$" title="Use this format A12/1234567/17">
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
                       	<label for="hostel">Hostel:</label>
                       <select class="input100" id="hostel_select" name="user_hostel">
                        <option>Nairobi</option>
                        <option>Mombasa</option>
                        <option>Eldoret</option>
                        <option>Argentina</option>
                        <option>Maringo</option>
                        <option>Ruwenzori</option>
                        <option>Hollywood</option>
                        <option>Buruburu</option>
                        <option>Taifa</option>
                        <option>Uganda</option>
                        <option>Barret</option>
                        <option>Thornton</option>
                        <option>Old Hall</option>
                        <option>Amboseli</option>
                        <option>Victoria</option>
                        <option>Elementaita</option>
                        <option>Bogoria</option>
                        <option>Tana</option>
                        <option>Aberdares</option>
                        <option>Turkana</option>
                        <option>Mau</option>
                        <option>Naivasha</option>
                        <option>Tsavo</option>
                        <option>Mama Ngina</option>
                        <option>River View</option>
                    </select>
                    <div class="login100-form">
                          <label for="email">Block:</label>
                          <input type="text" class="input100" name="user_block"  required>
                        </div>
                      
                        <div class="login100-form">
                          <label for="username">Room Number:</label>
                          <input type="number" class="input100" name="user_room_number"  required="">
                        </div>
            </div>
            
           
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
      
	
  </body>
</html>
