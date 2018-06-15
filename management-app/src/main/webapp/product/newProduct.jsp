<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<title>New Product Form</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="/resources/JS/myJS.js"></script>



</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<form autocomplete="off" action="newProduct" onsubmit="return validate()" method="post"
					class="form-horizontal" role="form">
					<h2>Add a Product</h2>
					<div class="form-group"  >
						<div class="col-sm-9">
							<input type="text" placeholder="name" id="name" name="name"
								class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-9">
							<input type="number" placeholder="length" id="length" name="length"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="number" name="width" id="width" placeholder="width"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" placeholder="height" id="height" name="height"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" placeholder="weight" id="weight"  name="weight"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<button type="submit" class="btn btn-primary btn-block">Add</button>
						</div>
					</div>
				</form>

				<div id="browse_app">
					<a class="btn btn-large btn-info" href="productList">Product List</a>
				</div>

			</div>
		</div>
	</div>

</body>
</html>