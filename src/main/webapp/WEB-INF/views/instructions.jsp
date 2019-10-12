
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>Survey-Instruction</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>




<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- <link href="/resources/css/style.css" rel="stylesheet" />
<script src="/resources/js/ins.js"></script> -->
<link href="<c:url value='../static/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
	<div class="container">
		<div class="generic-container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Instructions</h3>

					</div>
					<div class="panel-body">
						<div class="tab-content">
							<div class="tab-pane active" id="test">

								<c:if test="${not empty speakIns}">
    									${speakIns}
								</c:if>

								<c:if test="${not empty writeIns}">
    									${writeIns}
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<form action="${survey}/${qId}" method="get">
		<input type="hidden" name="user" id="user"
			class="form-control input-lg" placeholder="Username"
			value="${username}"/>  
			<input class="btn btn-primary btn-block" type="submit" value="Start" />
		</form>
	</div>
	<!-- <script>
		var mainv = document.getElementById("test");
		$.get("getAdmin", function(data, rstatus) {
			jdata = JSON.parse(data);
			//console.log(jdata.main);
			mainv.innerHTML = jdata.inst2;
			//lastv.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.last;
			//inst.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.inst;
		});
	</script> -->

</div>


</body>
</html>