<!-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> -->
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container pt-5" style="padding-left: 200px; padding-right: 200px;">
		<h2 style="text-align: center;padding: 10px;background-color: #72bac3;"> User Login </h2>
		<br>
		<div >
			<form action="login" method="post">
				<div class="form-group">
				  <label for="username">Username </label>
				  <input type="text" name="uname" id="uname"
									class="form-control" minlength="5" maxlength="50"
									pattern="[A-Za-z0-9]{5,}"
									title="No Spaces Allowed Minimum 5 characters" required>
				</div>
				
				<div class="form-group">
				  <label for="exampleInputPassword1">Password </label>
				  <input type="password" class="form-control" name="pass" minlength="5" maxlength="50" id="exampleInputPassword1" title="No Spaces Allowed and Minimum 5 characters" placeholder="Password" required>
				</div>

				<div class="form-check">
				  <input type="checkbox" class="form-check-input" id="exampleCheck1" required>
				  <label class="form-check-label" for="exampleCheck1">Check me out</label>
				</div>

				<br>
				<div >
					<button type="submit" class="btn btn-outline-success">Login</button>
					<input type="reset" name="Clear" id="reset" style="margin: 10px;" class="btn btn-outline-info">
				</div>
			  </form>
		</div>
	</div>
</body>
</html>