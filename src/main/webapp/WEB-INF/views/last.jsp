<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Thank you</title>
	<link href="<c:url value='../../static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='../../static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="container">
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="tablecontainer">
				 <c:out value="${lastpage}" escapeXml="false"/>
		    </div>
		</div>
	</div>
		
   	</div>
</body>
</html>