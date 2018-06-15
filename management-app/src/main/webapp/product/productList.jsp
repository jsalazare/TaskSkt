<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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
					
						<th scope="col">Id</th>
						<th scope="col">Name</th>
						<th scope="col">Length</th>
						<th scope="col">Width</th>
						<th scope="col">Heigth</th>
						<th scope="col">Weight</th>
					</tr>

					<c:forEach items="${myProducts}" var="product">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.length}</td>
							<td>${product.width}</td>
							<td>${product.heigth}</td>
							<td>${product.weight}</td>
						</tr>
					</c:forEach>
					<!-- close table -->
				</table>
				<div id="browse_app">
					<a class="btn btn-large btn-info" href="newProduct">Add New Product</a>
				</div>
			</div>
		</div>
	</div>
	<!-- provide an html table start tag -->

</body>
</html>