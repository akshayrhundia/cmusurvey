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
				<li ><a href="../../admin">Home</a></li>
				<li><a href="../../newquestion">Add Question</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Manage Question <span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="../../managetextquestions">Manage Text
									Questions</a></li>
							<li><a href="../../manageaudiofortextquestions">Manage
									Audio-Text Questions</a></li>
							<li><a href="../../manageaudioforaudioquestions">Manage
									Audio-Audio Questions</a></li>

						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Results <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<c:choose>
								<c:when test="${type == 'text'}">
									<li><a href="">Text Results</a></li>
									<li><a href="../audiotext/getallanswers">Audio-Text Results</a></li>
									<li><a href="../audioaudio/getallanswers">Audio-Audio Results</a></li>
								</c:when>
								<c:when test="${type == 'audiotext'}">
									<li><a href="../text/getallanswers">Text Results</a></li>
									<li><a href="">Audio-Text Results</a></li>
									<li><a href="../audioaudio/getallanswers">Audio-Audio Results</a></li>
								</c:when>

								<c:otherwise>
									<li><a href="../text/getallanswers">Text Results</a></li>
									<li><a href="../audiotext/getallanswers">Audio-Text Results</a></li>
									<li><a href="">Audio-Audio Results</a></li>
								</c:otherwise>
							</c:choose>
						</ul></li>
			
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	<!-- /.container-fluid -->
	<div class="container">
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Answers </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
				<thead>
				<tr>
					<th>Id</th>
								<th>Name</th>
								<th>Age</th>
								<th>Occupation</th>
								<th>Gender</th>
					<th>Question</th>
					<th>Answer</th>
				</tr>
				</thead>
				<tbody id="main-body">
					<c:forEach items="${answers}" var="res" varStatus="counter">
						<tr>
							<td>${res.user.id}</td>
							<td>${res.user.userId}</td>
							<td>${res.user.age}</td>
							<td>${res.user.gender}</td>
							<td>${res.user.occupation}</td>
						
							<c:choose>
								<c:when test = "${type == 'audiotext'}">
									<td><audio controls><source src="../../getQuestionAudioForTextFile/AUDIOTEXT_${res.ans.qId}" type="audio/wav" /></td>
      							</c:when>
      							<c:when test = "${type == 'audiotext'}">
									<td><audio controls><source src="../../getQuestionAudioForAudioFile/AUDIOAUDIO_${res.ans.qId}" type="audio/wav" /></td>
      							</c:when>
      							<c:otherwise>
      								<td>${res.ans.qtype}</td>
    							</c:otherwise>
      						</c:choose>
      						
      						<c:choose>
								<c:when test = "${res.ans.type == 'Audio'}">
									<td><audio controls><source src="../getAnswerFile/${res.ans.qId}?userId=${res.ans.userId}" type="audio/wav" /></audio></td>
      							</c:when>
      							<c:otherwise>
      								<td>${res.ans.type}</td>
    							</c:otherwise>
      						</c:choose>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		
		</div>
		</div>
		
	</div>
	</div>
<script>

</script>
</body>
</html>