<%@ include file="/admin/includes/admin_header.jsp" %>
<%@ include file="/admin/includes/admin_navigation.jsp" %>
	
<div class="container-fluid bg">
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

			<table class="table table-bordered table-hover">

			<tr>
				<td>Report ID</td>
				<td>Report Author ID</td>
				<td>Report Title</td>
				<td>Report Content</td>
				<td>Report Student ID</td>
				<td>Delete</td>
    		</tr>


			<%
			try{
			connection = DriverManager.getConnection(connectionUrl, userId, password);
			statement=connection.createStatement();
			String sql ="SELECT * FROM reports ORDER BY report_id DESC";

			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
			%>
			<tr>
				<td><%out.println(resultSet.getString("report_id")); %></td>
				<td><%out.println(resultSet.getString("report_author_id")); %></td>
		    	<td><%out.println(resultSet.getString("report_title")); %></td>
		    	<td><%out.println(resultSet.getString("report_content")); %></td>
		    	<td><%out.println(resultSet.getString("student_id")); %></td>
		    	<td><a href='/reports.jsp/admindelete/<%out.println(resultSet.getString("report_id")); %>'>Delete</a></td>
			</tr>

			<%
		    }

		    } catch (Exception e) {
		    e.printStackTrace();
		    }
			%>
			</table>
</div>


<%@ include file="/admin/includes/admin_footer.jsp" %>