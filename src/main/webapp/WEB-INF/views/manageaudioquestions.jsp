<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload/Download/Delete Documents</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse-4">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CMU Survey</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-collapse-4">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="admin">Home</a></li>
					<li><a href="newquestion">Add Question</a></li>

					<li><a href="managetextquestions">Manage Text Questions</a></li>
					<li class="active"><a href="manageaudioquestions">Manage Audio Questions</a></li>
					<li><a href="text/result">Text Results</a></li>
					<li><a href="audio/result">Audio Results</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<div class="container">
		<div class="generic-container">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<span class="lead">List of Audio Questions </span>
				</div>
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
							<c:forEach items="${managequestions}" var="doc"
								varStatus="counter">
								<tr>
									<td>${doc.id}</td>
									<td>Audio</td>

									<td><audio controls>
											<source src="getQuestionFile/${doc.id}" type="audio/wav" />
										</audio></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>