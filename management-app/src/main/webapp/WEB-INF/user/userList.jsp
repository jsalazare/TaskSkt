<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="org.common.dto.ExampleUserDTO"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users list</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<table class="table table-hover" style="border: 1px solid;">
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Age</th>
						<th scope="col">Email</th>
						<th scope="col">Height</th>
						<th scope="col">Weight</th>
					</tr>
					<%
						List users = (ArrayList) request.getAttribute("myUsers");
					%>

					<%
						for (int i = 0; i < users.size(); i++) {
							ExampleUserDTO user = (ExampleUserDTO) users.get(i);
					%>
					<!-- create an html table row -->
					<tr>
						<!-- create cells of row -->
						<td>
							<%
								out.print(user.getName());
							%>
						</td>
						<td>
							<%
								out.print(user.getAge());
							%>
						</td>
						<td>
							<%
								out.print(user.getEmail());
							%>
						</td>
						<td>
							<%
								out.print(user.getHeigth());
							%>
						</td>
						<td>
							<%
								out.print(user.getWeight());
							%>
						</td>
						<!-- close row -->
					</tr>
					<!-- close the loop -->
					<%
						}
					%>
					<!-- close table -->
				</table>
			</div>
		</div>
	</div>
	<!-- provide an html table start tag -->

</body>
</html>