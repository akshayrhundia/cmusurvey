<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<!-- <script src="<c:url value='/static/js/writesurvey.js' />"></script> -->

<link href="<c:url value='../../static/css/style.css' />" rel="stylesheet"></link>
<link href="" rel="stylesheet"></link>



<script src="<c:url value='../../static/dist/recorder.js' />"></script>




</head>
<body>
<div class="loading" id="loading">Loading&#8230;</div>
<div class="container-fluid">
		<div id="main-body">

			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="height: 100px;">
						<h3>
							<span id="qid">
							
							<c:choose>
								<c:when test = "${titletype == 'audiotext'}">
									<audio controls autoplay class="audio" ><source src="../../getQuestionAudioForTextFile/${question.id}" type="audio/wav" /></audio>
								</c:when>
								<c:when test = "${titletype == 'audioaudio'}">
									<audio controls autoplay class="audio" ><source src="../../getQuestionAudioForAudioFile/${question.id}" type="audio/wav" /></audio>
								</c:when>
      							<c:otherwise>
      								${question.title}
    							</c:otherwise>
      						</c:choose>
      						</span>
      						
						</h3>
					</div>
					<!--<form:form method="POST"  action="../savespeakans" enctype="multipart/form-data" class="form-horizontal">-->
					<div class="panel panel-default">
						<div class="panel-heading clearfix">
							<i class="icon-calendar"></i>
							<h3 class="panel-title">Please record "yes" or "no" below.</h3>
						</div>
						<div class="panel-body">
							<div class="input-group input-group-lg">
								<input required
									type="hidden" id="qId" name="qId" class="form-control input-lg" value="${question.id}">
								<input required type="hidden" id="qIndex" name="qIndex" class="form-control input-lg" value="${qId}">

								<button onclick="startRecording(this);" style="font-weight: bold;color: red;">Record Answer</button>
								<button onclick="stopRecording(this);" disabled>stop</button>
	  							<!-- <button  onclick="reset(this);">reset</button> -->
	  							<h4>Recorded Answer</h4>
	  							<ul id="recordingslist"></ul>
									
							</div>
						</div>
					</div>
					<div class="modal-footer text-muted">
						<input type="submit" value="Next" class="btn btn-default" onclick="saveMyAudio('${username}')" />
					</div>
					<!--</form:form>-->
				</div>

</div>
</div>
</div>

		
</body>

<script>

$(document).ready(function(){
	$('#loading').hide();
	});
	

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
  function reset(button) {
	  while (recordingslist.hasChildNodes()) {
    	  recordingslist.removeChild(recordingslist.lastChild);
    	}
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
      //var li = document.createElement('li');
      var au = document.createElement('audio');
      var hf = document.createElement('a');
      
      au.controls = true;
      au.src = url;
      au.setAttribute("id", "reply");
      au.setAttribute("name", "reply");
      hf.href = url;
      hf.download = new Date().toISOString() + '.wav';
      hf.innerHTML = hf.download;
      //li.appendChild(au);
      //li.appendChild(hf);
      while (recordingslist.hasChildNodes()) {
    	  recordingslist.removeChild(recordingslist.lastChild);
    	}
      recordingslist.appendChild(au);
      //recordingslist.appendChild(hf);
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
  
  function saveMyAudio(user){
	  if (!recordingslist.hasChildNodes()) {
		  alert("Please record your answer");
		  return;
	  }
	  $('#loading').show();
	  var url = URL.createObjectURL(gBlob);
      var fd = new FormData();
      
      var qId = document.getElementById('qId');
      var qIndex = document.getElementById('qIndex');

	  //var titleByte=toUTF8Array(title.value);
	  var opts=[];
	  opts.push(qId.value);
	     
	  fd.append('qId', qId.value);
	  fd.append('qIndex', qIndex.value);
	  fd.append('reply', gBlob);
	  fd.append('user', user);
      $.ajax({
          type: 'POST',
          url: '../savespeakans',
          data: fd,
          contentType: false,
          processData: false
      }).done(function(data) {
    	  //alert(data);
    	  window.location.replace(data);
    	  //alert("done");
      }).fail(function() { alert("error"); 
      $('#loading').hide();});
     
   
}
  </script>
</html>