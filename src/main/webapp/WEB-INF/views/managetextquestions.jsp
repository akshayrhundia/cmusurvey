<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload/Download/Delete Documents</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link href="<c:url value='static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='static/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='static/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='static/css/style.css' />" rel="stylesheet"></link>
<link href="<c:url value='static/css/editor.css' />" rel="stylesheet"></link>
<script src="<c:url value='static/js/editor.js' />"></script>
</head>

<body>
<div class="loading" id="loading">Loading&#8230;</div>
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
					<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Question
        		<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="managetextquestions">Manage
							Text Questions</a></li>
				<li><a href="manageaudiofortextquestions">Manage Audio-Text Questions</a></li>
				<li><a href="manageaudioforaudioquestions">Manage Audio-Audio Questions</a></li>
				
				</ul></li>
				<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">Results
        		<span class="caret"></span></a>
							<ul class="dropdown-menu">
							<li><a href="text/getallanswers">Text Results</a></li>
				<li><a href="audiotext/getallanswers">Audio-Text Results</a></li>
				<li><a href="audioaudio/getallanswers">Audio-Audio Results</a></li>
							</ul>
				</li>

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
					<span class="lead">List of Text Questions </span>
				</div>
				<div class="tablecontainer">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>No.</th>
								<th>Question type</th>
								<th>Question</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${managequestions}" var="doc"
								varStatus="counter">
								<tr>
									<td>${doc.id}</td>
									<td>Text</td>
									<td contenteditable='true' id='${doc.id}'>${doc.title}</td>
									<td><button onclick="saveQuestion(${doc.id})" class="btn btn-primary">
					Save</button></td>
									<td><button onclick="deleteQuestion(${doc.id})" class="btn btn-primary">
					Delete</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
	$('#loading').hide();
	function deleteQuestion(id){
		$('#loading').show();
		$.get("text/delete/"+id, function(data, rstatus) {
		   		    //alert("Data: " + data + "\nStatus: " + status);
			        $('#loading').hide();
			        location.reload();
			    }
		);
	}
	function saveQuestion(id){
		dtitle=document.getElementById ( id ).innerText
		//alert(dtitle);
		$('#loading').show();
		
		$.post("text/updatequestiontitle/"+id, {
			 title:dtitle
			},
			    function(data, status){
			        //alert("Data: " + data + "\nStatus: " + status);
			        $('#loading').hide();
			        location.reload();
			});
	}

	</script>

</body>
</html>