<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Maven + Spring MVC + @JavaConfig</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">





<link href="/resources/css/style.css" rel="stylesheet" />



</head>
<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Users </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
				<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Age</th>
					<th>Occupation</th>
					<th>Gender</th>
				</tr>
				</thead>
				<tbody id="main-body">
					<c:forEach items="${users}" var="user" varStatus="counter">
						<tr>
							<td>${user.id}</td>
							<td><a href="useranswers/${user.userId}">${user.userId}</a></td>
							<td>${user.age}</td>
							<td>${user.occupation}</td>
							<td>${user.gender}</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		
		</div>
		</div>
		
	</div>
	
<script>

</script>
</body>
</html>