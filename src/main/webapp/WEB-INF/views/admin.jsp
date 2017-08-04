<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html lang="en">
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








<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/editor.css' />" rel="stylesheet"></link>
<script src="<c:url value='/static/js/editor.js' />"></script>

<script>
			$(document).ready(function() {
				$("#speakfirstPage").Editor();
				$("#writefirstPage").Editor();
				$("#speakInsPage").Editor();
				$("#lastPage").Editor();
				$("#writeInsPage").Editor();
				var speakfirstpage = document.getElementById("speakfirst");
				var writefirst = document.getElementById("writefirst");
				var speakIns = document.getElementById("speakIns");
				var lastpage = document.getElementById("lastpage");
				var writeIns = document.getElementById("writeIns");
				
				$.get("getAdmin", function(data, rstatus) {
					jdata = JSON.parse(data);
					speakfirstpage.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.speakfirstpage;
					writefirst.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.writefirstpage;
					speakIns.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.speakIns;
					lastpage.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.lastpage;
					writeIns.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.writeIns;
				});
				
				//'<span style="font-weight: bold;">istruction</span>';
				
			});
		</script>

</head>
<body>
	<div class="container">
		<div id="table" class="table-editable">
			<table class="table">
				<tr>
					<th>Name</th>
					<th>Option1</th>
					<th>Option2</th>
					<th>Option3</th>
					<th>Save</th>
				</tr>
				<tbody id="main-body">
				
				</tbody>
			</table>
		</div>
		<div class="container-fluid">
			<div class="row">
				<h2 class="demo-text">speakfirstpage</h2>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 nopadding" id="speakfirst">
							<textarea id="speakfirstPage"></textarea> 
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<h2 class="demo-text">Instruction Page(type)</h2>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 nopadding" id="speakIns">
							<textarea id="speakInsPage"></textarea> 
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<h2 class="demo-text">Instruction Page(speak)</h2>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 nopadding" id="writeIns">
							<textarea id="writeInsPage"></textarea> 
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<h2 class="demo-text">writefirstpage</h2>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 nopadding" id="writefirst">
							<textarea id="writefirstPage"></textarea> 
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<h2 class="demo-text">Last Page</h2>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 nopadding" id="lastpage">
							<textarea id="lastPage"></textarea> 
						</div>
					</div>
				</div>
			</div>
		</div>
		<button  onclick="saveMainPage()" class="btn btn-primary">Save All three Pages</button></td>'
		
		
	</div>
	
<script>

$(function() {

	
	
	var count = 0;
	var body = "";
	$.get("survey/getallquestion", function(quests, rstatus) {
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

	});

});

function save(id, op1, op2, op3) {
	var q = document.getElementById("q" + id).innerText;
	var option1 = document.getElementById("o" + op1).innerText;
	var option2 = document.getElementById("o" + op2).innerText;
	var option3 = document.getElementById("o" + op3).innerText;
	console.log(option1);
	console.log(option2);
	console.log(option3);
	$.post("saveQuestion",
		    {
				code:parseInt(id),
				quest: q,
		        sop1: option1,
		        sop2: option2,
		        sop3: option3
		    },
		    function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		    });
}
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
	var mainv = document.getElementById("main");
	var lastv1 = document.getElementById("last1");
	var inst1 = document.getElementById("ins1");
	var lastv2 = document.getElementById("last2");
	var inst2 = document.getElementById("ins2");
	mainv = mainv.getElementsByClassName("Editor-editor")[0].innerHTML;
	lastv1 = lastv1.getElementsByClassName("Editor-editor")[0].innerHTML;
	inst1 = inst1.getElementsByClassName("Editor-editor")[0].innerHTML;
	lastv2 = lastv2.getElementsByClassName("Editor-editor")[0].innerHTML;
	inst2 = inst2.getElementsByClassName("Editor-editor")[0].innerHTML;
	//console.log(mainv);
	console.log(lastv1);
	console.log(lastv2);
	$.post("survey/addAdmin",
		    {
				ins1: inst1,
				last1: lastv1,
				ins2: inst2,
				last2: lastv2,
		        main: mainv
		    },
		    function(data, status){
		        alert("Data: " + data + "\nStatus: " + status);
		    });
}

</script>
</body>
</html>