<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="org.common.model.Product"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product list</title>
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
						<th scope="col">Length</th>
						<th scope="col">Width</th>
						<th scope="col">Heigth</th>
						<th scope="col">Weight</th>
					</tr>
					<%
						List products = (ArrayList) request.getAttribute("myProducts");
					%>

					<%
						for (int i = 0; i < products.size(); i++) {
							Product pro = (Product) products.get(i);
					%>
					<!-- create an html table row -->
					<tr>
						<!-- create cells of row -->
						<td>
							<%
								out.print(pro.getName());
							%>
						</td>
						<td>
							<%
								out.print(pro.getLength());
							%>
						</td>
						<td>
							<%
								out.print(pro.getWidth());
							%>
						</td>
						<td>
							<%
								out.print(pro.getHeigth());
							%>
						</td>
						<td>
							<%
								out.print(pro.getWeight());
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