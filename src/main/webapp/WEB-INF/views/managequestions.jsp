<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Upload/Download/Delete Documents</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Questions </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>No.</th>
					        <th>Question type</th>
					        <th>Question</th>
					 	</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${managequestions}" var="doc" varStatus="counter">
						<tr>
							<td>${doc.id}</td>
							<c:choose>
							<c:when test = "${doc.titletype == 'Audio'}">
							<td>Audio</td>
							
         						<td><audio controls><source src="getQuestionFile/${doc.id}" type="audio/wav" /></td>
      						</c:when>
      						<c:when test = "${doc.titletype == 'Video'}">
      						<td>
      													<td>Video</td>
      								<c:set var="title">${doc.title}</c:set>
      								 <c:out value="${title}" escapeXml="true"/>
      								 <img src="data:image/png;base64, ${doc.title}" alt="Red dot" />
      							</td>
      						</c:when>
      						<c:otherwise>
      													<td>Text</td>
       			<td>${doc.titletype}</td>

    </c:otherwise>
      						</c:choose>
							
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
		</div>
		
   	</div>
</body>
</html>