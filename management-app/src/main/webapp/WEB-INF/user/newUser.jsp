<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
	<title>Registration Form</title>
	<link rel="stylesheet" type="text/css" th:href="@{/css/registration.css}" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<form autocomplete="off" action="#" th:action="@{/registration}"
					th:object="${user}" method="post" class="form-horizontal"
					role="form">
					<h2>Add a User</h2>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text"
								placeholder="name" class="form-control" /> 
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-9">
							<input type="number"
								placeholder="age" class="form-control" /> 
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" placeholder="Email"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" 
								placeholder="Weight" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<input type="text" 
								placeholder="Height" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-9">
							<button type="submit" class="btn btn-primary btn-block">Add</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>