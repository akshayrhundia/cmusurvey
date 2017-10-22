<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
	
	


<!--

-->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
<link href="" rel="stylesheet"></link>


</head>
<body>

	<div class="container-fluid">
		<div id="main-body">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="height: 100px;">
						<h3>
							<span id="qid">
							
							<c:choose>
								<c:when test = "${question.titletype == 'Audio'}">
									<audio controls autoplay class="audio"><source src="../getQuestionFile/${question.id}" type="audio/wav" /></audio>
      							</c:when>
      							<c:when test = "${question.titletype == 'Video'}">
      							</c:when>
      							<c:otherwise>
      								${question.titletype}
    							</c:otherwise>
      						</c:choose>
      						</span>
      						
						</h3>
					</div>
					<div class="modal-body">
						<div class="col-xs-3 col-xs-offset-5">
							<div id="loadbar" style="display: none;">
								<div class="blockG" id="rotateG_01"></div>
								<div class="blockG" id="rotateG_02"></div>
								<div class="blockG" id="rotateG_03"></div>
								<div class="blockG" id="rotateG_04"></div>
								<div class="blockG" id="rotateG_05"></div>
								<div class="blockG" id="rotateG_06"></div>
								<div class="blockG" id="rotateG_07"></div>
								<div class="blockG" id="rotateG_08"></div>
							</div>
						</div>
						<div class="quiz" id="quiz" data-toggle="buttons">
						<c:forEach items="${question.options}" var="option" varStatus="counter">
								<div class="alert alert-info" role="alert">
											${option}
								</div>
						</c:forEach>
						</div>
					</div>
					<form:form method="POST"  action="../savewriteans" class="form-horizontal">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<i class="icon-calendar"></i>
							<h3 class="panel-title">Please type "yes" or "no" below.</h3>
						</div>
						<div class="panel-body">
							<div class="input-group input-group-lg">
								<span class="input-group-addon">Ans:</span> 
								
								<input required
									type="hidden" id="qId" name="qId" class="form-control input-lg" value="${question.id}">
															
								<input required
									type="text" id="reply" name="reply" class="form-control input-lg"
									placeholder="Answer">
									
							</div>
						</div>
					</div>
					<div class="modal-footer text-muted">
						<input type="submit" value="Next" class="btn btn-default" />
					</div>
					</form:form>
				</div>



			</div>
</body>
</html>