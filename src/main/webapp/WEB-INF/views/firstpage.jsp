
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>Survey-Welcome</title>

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

<!-- <script src="/resources/js/recorderWorker.js;jsessionid=DE7F829B064E471DDC0E6B26866AF7D0"></script>
<script src="/resources/js/recorderWorker.js;jsessionid=DE7F829B064E471DDC0E6B26866AF7D0"></script>
<script src="/resources/js/recorder.js;jsessionid=DE7F829B064E471DDC0E6B26866AF7D0"></script>-->
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

</head>
<body style="height: 100%;">
	<div class="container">
		<div class="generic-container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">Consent form</h3>

						</div>
						<div class="panel-body">
							<div class="tab-content">
								<div class="tab-pane active" id="test">
									<c:if test="${not empty speakfirstpage}">
    							${speakfirstpage}
							</c:if>
									<c:if test="${not empty writefirstpage}">
    							${writefirstpage}
							</c:if>

								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">


						<div class="container-fluid">
							<div id="main-body">

								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<div class="row">

												<div class="col-md-12 col-sm-12 col-xs-12">
													<div class="panel panel-default">
														<div class="panel-heading clearfix">
															<i class="icon-calendar"></i>
															<h3 class="panel-title">Enter Your Details</h3>
														</div>

														<div class="panel-body">

															<form:form method="POST" modelAttribute="user"
																class="form-horizontal">
																<div class="row">
																	<div class="form-group col-md-12">
																		<label class="col-md-3 control-lable" for="userId">User
																			ID</label>
																		<div class="col-md-7">
																			<form:input type="text" path="userId" id="userId"
																				class="form-control input-sm" required="true" />
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="form-group col-md-12">
																		<label class="col-md-3 control-lable" for="gender">Gender</label>
																		<div class="col-md-7">
																			<form:input type="text" path="gender" id="gender"
																				class="form-control input-sm" />
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="form-group col-md-12">
																		<label class="col-md-3 control-lable" for="age">Age</label>
																		<div class="col-md-7">
																			<form:input type="text" path="age" id="age"
																				class="form-control input-sm" />
																		</div>
																	</div>
																</div>



																<div class="row">
																	<div class="form-group col-md-12">
																		<label class="col-md-3 control-lable" for="occupation">Occupation</label>
																		<div class="col-md-7">
																			<form:input type="text" path="occupation"
																				id="occupation" class="form-control input-sm" />
																		</div>
																	</div>
																</div>
														</div>
													</div>
													<div class="row">
														<div class="form-actions floatRight">
															<input type="submit" value="Register"
																class="btn btn-primary btn-block" />
														</div>
													</div>


													</form:form>


												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <script>
		var mainv = document.getElementById("test");
		$.get("survey/getAdmin", function(data, rstatus) {
			jdata = JSON.parse(data);
			console.log(jdata.main);
			mainv.innerHTML = jdata.main;
			//lastv.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.last;
			//inst.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.inst;
		});
	</script> -->
</body>
</html>