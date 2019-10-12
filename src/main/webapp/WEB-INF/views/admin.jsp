<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html lang="en">
<head>
<title>Admin page for survey</title>

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








<link href="<c:url value='static/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='static/css/style.css' />" rel="stylesheet"></link>
<link href="<c:url value='static/css/editor.css' />" rel="stylesheet"></link>
<script src="<c:url value='static/js/editor.js' />"></script>

<script>
$('#loading').show();
			$(document).ready(function() {
				
				$("#speakfirstPage").Editor();
				$("#writefirstPage").Editor();
				$("#speakInsPage").Editor();
				$("#lastPage").Editor();
				$("#writeInsPage").Editor();
				$("#secondlastPage").Editor();
				$("#speakAudioInsPage").Editor();
				$("#writeAudioInsPage").Editor();
				
				var speakfirstpage = document.getElementById("speakfirst");
				var writefirst = document.getElementById("writefirst");
				var speakIns = document.getElementById("speakIns");
				var lastpage = document.getElementById("lastpage");
				var writeIns = document.getElementById("writeIns");
				var secondlastPage = document.getElementById("secondlast");
				var speakAudioIns = document.getElementById("speakAudioIns");
				var writeAudioIns = document.getElementById("writeAudioIns");
				
				$.get("getAdmin", function(data, rstatus) {
					$('#loading').hide();
					jdata = JSON.parse(data);
					speakfirstpage.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.speakfirstpage;
					writefirst.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.writefirstpage;
					speakIns.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.speakIns;
					lastpage.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.lastpage;
					writeIns.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.writeIns;
					secondlastPage.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.secondlastpage;
					speakAudioIns.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.speakAudioIns;
					writeAudioIns.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.writeAudioIns;
				});
				
				//'<span style="font-weight: bold;">istruction</span>';
				
			});
		</script>

</head>
<body>
	<!-- Second navbar for profile settings -->
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
				<li class="active"><a href="admin">Home</a></li>
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
	<!-- /.container --> </nav>
	<!-- /.navbar -->
	</div>
	<!-- /.container-fluid -->
	<div class="container">
	<div class="loading" id="loading">Loading&#8230;</div>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">New descriptions form </span>
			</div>
			<div class="tablecontainer">
				<table>
					<tr>
						<td>
							<h2 class="demo-text">Speaking First Page</h2>
							<div style="width: 90%;" id="speakfirst">
								<textarea id="speakfirstPage"></textarea>
							</div>

						</td>
					</tr>

					<tr>
						<td>
							<h2 class="demo-text">Writing First Page</h2>
							<div style="width: 90%;" id="writefirst">
								<textarea id="writefirstPage"></textarea>
							</div>

						</td>
					</tr>


					<tr>
						<td>

							<h2 class="demo-text">Instruction Page(Text-Speak)</h2>
							<div style="width: 90%;" id="speakIns">
								<textarea id="speakInsPage"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<h2 class="demo-text">Instruction Page(Text-Write)</h2>
							<div style="width: 90%;" id="writeIns">
								<textarea id="writeInsPage"></textarea>
							</div>
						</td>
					</tr>
					
					<tr>
						<td>

							<h2 class="demo-text">Instruction Page(Audio-Speak)</h2>
							<div style="width: 90%;" id="speakAudioIns">
								<textarea id="speakAudioInsPage"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<h2 class="demo-text">Instruction Page(Audio-Write)</h2>
							<div style="width: 90%;" id="writeAudioIns">
								<textarea id="writeAudioInsPage"></textarea>
							</div>
						</td>
					</tr>
					
					
					

					<tr>
						<td>
							<h2 class="demo-text">Second Last Page</h2>
							<div style="width: 90%;" id="secondlast">
								<textarea id="secondlastPage"></textarea>
							</div>

						</td>
					</tr>
					<tr>
						<td>
							<h2 class="demo-text">Last Page</h2>

							<div id="lastpage" style="width: 90%;">
								<textarea id="lastPage"></textarea>
							</div>

						</td>
					</tr>

				</table>
				<button onclick="saveMainPage()" class="btn btn-primary">Save
					All three Pages</button>
			</div>
		</div>

	</div>
	</div>
	<script>

$(function() {

	
	
	var count = 0;
	var body = "";
	/*$.get("survey/getallquestion", function(quests, rstatus) {
		jquests = JSON.parse(quests);
		//console.log(jquests);
		jquests.forEach(function(item) {
			var op1 = "";
			var op2 = "";
			var op3 = "";
			body += '<tr>' + '<td contenteditable="true" id="q' + item.code
					+ '">' + item.quest + '</td>';

			//item.options
			//.forEach(function(opt) {
			//console.log(opt);
			body += '<td contenteditable="true" id="o' + item.options[0].id
					+ '">' + item.options[0].option + '</td>';
			body += '<td contenteditable="true" id="o' + item.options[1].id
					+ '">' + item.options[1].option + '</td>';
			body += '<td contenteditable="true" id="o' + item.options[2].id
					+ '">' + item.options[2].option + '</td>';
			if (op1 == "")
				op1 = item.options[0].id;
			if (op2 == "")
				op2 = item.options[1].id;
			if (op3 == "")
				op3 = item.options[2].id;
			//});

			body += '<td><button onclick="save(' + item.code + ',' + op1 + ','
					+ op2 + ',' + op3
					+ ')" class="btn btn-primary">Save Data</button></td>'
					+ '</tr>';

		});
		document.getElementById("main-body").innerHTML = body;

	});*/

});


function getParameterByName(name, url) {
	if (!url) {
		url = window.location.href;
	}
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function saveMainPage(){
	$('#loading').show();
	var speakfirstpages = document.getElementById("speakfirst");
	var writefirstpages = document.getElementById("writefirst");
	var speakInspage = document.getElementById("speakIns");
	var lastpages = document.getElementById("lastpage");
	var writeInspage = document.getElementById("writeIns");
	var secondlastpages = document.getElementById("secondlast");
	var speakAudioInspage = document.getElementById("speakAudioIns");
	var writeAudioInspage = document.getElementById("writeAudioIns");
	
	
	speakfirstpages = speakfirstpages.getElementsByClassName("Editor-editor")[0].innerHTML;
	writefirstpages = writefirstpages.getElementsByClassName("Editor-editor")[0].innerHTML;
	speakInspage = speakInspage.getElementsByClassName("Editor-editor")[0].innerHTML;
	lastpages = lastpage.getElementsByClassName("Editor-editor")[0].innerHTML;
	writeInspage = writeInspage.getElementsByClassName("Editor-editor")[0].innerHTML;
	secondlastpages = secondlastpages.getElementsByClassName("Editor-editor")[0].innerHTML;
	speakAudioInspage = speakAudioInspage.getElementsByClassName("Editor-editor")[0].innerHTML;
	writeAudioInspage = writeAudioInspage.getElementsByClassName("Editor-editor")[0].innerHTML;
	
	
	//console.log(mainv);
	//console.log(lastv1);
	//console.log(lastv2);
	
	$.post("addAdmin",
		    {
		
		 speakfirstpage:speakfirstpages,
		 writefirstpage:writefirstpages,
		 speakIns:speakInspage,
		 writeIns:writeInspage,
		 speakAudioIns:speakAudioInspage,
		 writeAudioIns:writeAudioInspage,
		 lastpage:lastpages,
		 secondlastpage:secondlastpages
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