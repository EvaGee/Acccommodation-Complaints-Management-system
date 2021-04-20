<%@ include file="/includes/header.jsp" %>


    <div class="container-fluid bg">
<button type="button" class="btn btn-success btn-lg" onClick="goBack()">Back</button>
    <br>
		<div class="container">
				<!-- Form Start -->
            <form class="form-container" method="post" action="/complaintStatus" id="assigningForm" name="assigningForm" onsubmit="return formValidate()">
                <br>
                <h1>Complaint</h1>
               	<div class="form-group">
                   	<label for="complaint_id">Complaint ID:</label>
                  		<input type="number" class="form-control" name="complaintId" value="${complaint.complaint_id}"  readonly>
                	 </div>
                 <div class="form-group">
                   	<label for="complaint_id">Complaint Hostel:</label>
                  		<input type="text" class="form-control" value="${complaint.complaintHostel}"  readonly>
                	 </div>
                	 <div class="form-group">
                   	<label for="complaint_id">Complaint Block:</label>
                  		<input type="text" class="form-control" value="${complaint.complaintBlock}"  readonly>
                	 </div>
                	 <div class="form-group">
                   	<label for="complaint_id">Complaint Room:</label>
                  		<input type="text" class="form-control" value="${complaint.complaintRoomNumber}"  readonly>
                	 </div>
                	  <div class="form-group">
                   	<label for="complaint_id">Complaint Content:</label>
                  		<input type="text" class="form-control" value="${complaint.complaint_content}"  readonly>
                	 </div>
                	 <div class="form-group">
                   	<label for="complaint_id">Complaint Status:</label>
                  		<select class="form-control"  id="complaint_status" name="complaintStatus" required>
                  		<option value="">Select Status </option>
                  		<option>Done</option>
                  		<option>Incomplete</option>
                  		</select>
                	 </div>
                	 <div class="form-group">
                   	<label for="complaint_id">Complaint Comment:</label>
                  		<input type="text" class="form-control" id="complaintComment" name="complaintComment" required>
                	 </div>
               <input type="text" class="form-control" id="userNumber" name="userNumber" value="${complaint.complaint_assigned_to}">
                  <button type="submit" class="btn btn-success btn-block">Submit</button>
            </form>
            <!-- Form End-->
		</div>
	</div>
	<script type="text/javascript">
	function goBack(){
		window.history.back();
		}
	
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
