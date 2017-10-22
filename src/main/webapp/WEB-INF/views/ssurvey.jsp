<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script src="<c:url value='/static/js/writesurvey.js' />"></script>

<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
<link href="" rel="stylesheet"></link>

<style type='text/css'>
ul {
	list-style: none;
}

#recordingslist audio {
	display: block;
	margin-bottom: 10px;
}

audio{display:none}
.audioContainer
{
height:50px;width:250px;
border:solid 1px #dedede;
position:relative;
}
.audioProgress
{
height:50px;float:left;background-color:#f2f2f2;z-index:800
}
.audioControl
{
position: absolute;float:left;width:52px;height:48px;;
}
.audioTime
{
position: absolute;width: 45px;height: 20px;margin-left:199px;float:right;
}
.audioBar
{
height: 3px;
background-color: #cc0000;
position: absolute;width: 147px;margin-left: 53px;
}
.audioPlay
{
background:url('../images/play.png') no-repeat
} 
.audioPause
{
background:url('../images/pause.png') no-repeat
}
</style>

<script src="<c:url value='/static/dist/recorder.js' />"></script>




</head>
<body>
<div class="container">
	<div class="container-fluid bg-info">
		<div id="main-body">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="height: 100px;">
						<h3>
							<span id="qid">${question.titletype}
							
							<c:choose>
								<c:when test = "${question.titletype == 'Audio'}">
									<article>
									<audio class="audio" controls="controls"><source src="../getQuestionFile/${question.id}" type="audio/wav" />
									</article>
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
					<form:form method="POST"  action="../savespeakans" class="form-horizontal">
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<i class="icon-calendar"></i>
							<h3 class="panel-title">Please record "yes" or "no" below.</h3>
						</div>
						<div class="panel-body">
							<div class="input-group input-group-lg">
								<input required
									type="hidden" id="qId" name="qId" class="form-control input-lg" value="${question.id}">
															
								<button onclick="startRecording(this);">Record Answer</button>
								<button onclick="stopRecording(this);" disabled>stop</button>
	  							<button  onclick="reset(this);" disabled>reset</button>
	  							<h4>Recorded Answer</h4>
	  							<ul id="recordingslist"></ul>
									
							</div>
						</div>
					</div>
					<div class="modal-footer text-muted">
						<input type="submit" value="Next" class="btn btn-default" />
					</div>
					</form:form>
				</div>

</div>
</div>
</div>

			</div>
</body>

<script>


var gBlob=null;
 
  var audio_context;
  var recorder;

  function startUserMedia(stream) {
    var input = audio_context.createMediaStreamSource(stream);
    
    // Uncomment if you want the audio to feedback directly
    //input.connect(audio_context.destination);
    //__log('Input connected to audio context destination.');
    
    recorder = new Recorder(input);
  }

  function startRecording(button) {
    recorder && recorder.record();
    button.disabled = true;
    button.nextElementSibling.disabled = false;
  }

  function stopRecording(button) {
    recorder && recorder.stop();
    button.disabled = true;
    button.previousElementSibling.disabled = false;
    
    // create WAV download link using audio data blob
    createDownloadLink();
    
    recorder.clear();
  }

  function createDownloadLink() {
    recorder && recorder.exportWAV(function(blob) {
    	gBlob=blob;
      var url = URL.createObjectURL(blob);
      var li = document.createElement('li');
      var au = document.createElement('audio');
      var hf = document.createElement('a');
      
      au.controls = true;
      au.src = url;
      au.setAttribute("id", "reply");
      au.setAttribute("name", "reply");
      hf.href = url;
      hf.download = new Date().toISOString() + '.wav';
      hf.innerHTML = hf.download;
      li.appendChild(au);
      li.appendChild(hf);
      recordingslist.appendChild(li);
    });
  }

  window.onload = function init() {
    try {
      // webkit shim
      window.AudioContext = window.AudioContext || window.webkitAudioContext;
      navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia;
      window.URL = window.URL || window.webkitURL;
      
      audio_context = new AudioContext;
    } catch (e) {
      alert('No web audio support in this browser!');
    }
    
    navigator.getUserMedia({audio: true}, startUserMedia, function(e) {
    });
  };
  </script>
</html>