<%@ include file="/includes/header.jsp" %>

    <div style="padding: 50px; width:800px;" class="container-fluid bg">
		<div class="container">
				<!-- Form Start -->
            <form class="form-container" method="post" action="/submitComplaint" id="complaintForm" name="complaintForm" onsubmit="return formValidate()">
                <br>
                <button type="button" class="btn btn-success" onClick="window.location.href='studentUI.jsp'">Back</button>
                <br>
                <h1 style="text-align: center;">Welcome <%=session.getAttribute("USER_FIRSTNAME")%></h1>
                <br>
                <h1>Complaint Form</h1>
                  <div class="form-group">
                    <label for="complaint_category">Complaint Category:</label>
                    <select name="complaint_category" id="complaint_category">
			            <!-- Default -->
			            <option value="">Select Options</option>
			            
			            <option value="others">Others</option>
			            <option value="mason">Masonry</option>
			            <option value="plumber">Plumbing</option>
			            <option value="carpenter">Carpentry</option>
			            <option value="security">Security</option>
			            <option value="electrician">Electrical</option>
			            <option value="health">Health</option>
			            <option value="painter">Painting</option>
			            <option value="cleaner">Cleaning</option>
			            <option value="custodian">Custodian Related</option>
		        	</select>
                  </div>
                  <div class="form-group">
                    <label for="complaint_content">Complaint Content:</label>
                    <textarea rows="5" cols="50" class="form-control" id="complaint_content" name="complaint_content" placeholder="Complaint Content"></textarea>
                  </div>
                  
                  
  		          <input type="hidden" id="complaint_author_id" name="complaint_author_id" value="<%=session.getAttribute("USER_NUMBER")%>">
  		          <input type="hidden" id="complaint_author_id" name="complaintHostel" value="<%=session.getAttribute("USER_HOSTEL")%>">
  		          <input type="hidden" id="complaint_author_id" name="complaintBlock" value="<%=session.getAttribute("USER_BLOCK")%>">
  		          <input type="hidden" id="complaint_author_id" name="complaintRoomNumber" value="<%=session.getAttribute("USER_ROOM_NUMBER")%>">
               
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
