<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.nagarro.LibMang.entities.Author"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if (session.getAttribute("username") == null)
		response.sendRedirect("login.jsp");
	%>
<div class="fixed-header">
			<div id="title" style="text-align: right;">

				<form action="Logout" method="post" style="padding: 10px;">
					<b>Welcome ${username} <input type="submit"
						class="btn btn-warning" value="Logout">
				</form>
			</div>

	<div class="row">
		<div id="title" style="text-align: center;">
			<h1 style="background-color: #3cead6;">ADD BOOK DETAILS</h1>

		<div class="main-content col-6" style="
    padding-top: 25px;
">
				<form action="Addbook" method="post">
					<div class="mb-3 row">
						<label for="BookCode" class="col-2 mx-4">Book Code</label> <input
							type="text" class="col-4" id="bookCode"
							name="bookCode" onkeypress="return /[0-9]/i.test(event.key)" required>

					</div>
					<div class="mb-3 row">
						<label for="BookName" class="col-2 mx-4">Book Name</label> <input
							type="text" class="col-4" id="bookName"
							 name="bookName" required>

					</div>
					<div class="mb-3 row">
						<label for="Author" class="col-2 mx-4">Author</label> <select
							class="col-4" name="author" id="author" required>
							<c:forEach items="${Author}" var="Author">
								<option value="${Author.getName()}">${Author.getName()}</option>
							</c:forEach>
						</select>

					</div>
					<div class="mb-3 row">
						<label for="AddedOn" class="col-2 mx-4">Added On</label> <input
							type="text" id="addedOn" class="col-4"
							value="${date}" name="addedOn" readonly>


					</div>


					<div class="col-6 mx-4">
						<button type="submit" class="btn btn-success">SUBMIT</button>
						<form action="BookListing", method="post">
							<button type="submit" class="btn btn-danger">CANCEL</button>
						</form>
						

					</div>
				</form>
			</div>

		</div>
	</div>
</div>

<footer>
    <p style="text-align: center;background-color: #3cead6;position: fixed;bottom: 0;width: 100%;/* padding: 5px; */margin: 0px;">Copyright ©2023 | Nagarro Training</p>
</footer>
</body>

</html>