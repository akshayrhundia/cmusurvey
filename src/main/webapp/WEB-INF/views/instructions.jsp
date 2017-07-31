


<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC + @JavaConfig</title>

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

<link href="/resources/css/style.css" rel="stylesheet" />
<script src="/resources/js/ins.js"></script>


</head>
<body style="height: 100%; background-color: #d9edf7">
	<div class="container">
		<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Instructions</h3>

				</div>
				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane active" id="test">
							
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	<form action="rsurvey/1" method="get">
		<input type="hidden" name="user" id="user"
			class="form-control input-lg" placeholder="Username"> <input
			type="hidden" name="age" id="age" class="form-control input-lg"
			placeholder="Age"> <input type="hidden" name="gender"
			id="gender" class="form-control input-lg" placeholder="M/F">
		<input type="hidden" name="occupation" id="occupation"
			class="form-control input-lg" placeholder="Occupation"> <input
			class="btn btn-default" type="submit" value="Start" />
	</form>
	

</div>
<script>
var mainv = document.getElementById("test");
$.get("survey/getAdmin", function(data, rstatus) {
	jdata = JSON.parse(data);
	//console.log(jdata.main);
	mainv.innerHTML=jdata.inst2;
	//lastv.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.last;
	//inst.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.inst;
});

</script>



</body>
</html>