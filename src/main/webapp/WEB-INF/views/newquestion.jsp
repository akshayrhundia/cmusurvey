<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Form</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

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
	
	<style type='text/css'>
    ul { list-style: none; }
    #recordingslist audio { display: block; margin-bottom: 10px; }
  </style>
  
   <script src="~/../static/dist/recorder.js"></script>
  
</head>

<body>

	<div class="container">
		<h2>Registration Form</h2>


		<div class="form-group">
			<label for="titletype" onchange="getComboA(this)"
				class="col-sm-3 control-label">Type</label>
			<div class="col-sm-9">
				<select id="titletype" class="form-control">
					<option value="Text">Text</option>
					<option value="Video">Video</option>
					<option value="Audio">Audio</option>

				</select>
			</div>
		</div>

		<div class="form-group" id="typeUpdate">
			<label for="title" class="col-sm-3 control-label">Question</label>
			<div class="col-sm-9" id="que">
				<input type="text" id="title" placeholder="Question"
					class="form-control" autofocus>
			</div>
			<div class="col-sm-12"></div>
			<label for="option1" class="col-sm-3 control-label">Option 1</label>

			<div class="col-sm-9">
				<input type="text" id="option1" placeholder="Option 1"
					class="form-control" autofocus>
			</div>
			<div class="col-sm-12"></div>
			<label for="option2" class="col-sm-3 control-label">Option 2</label>

			<div class="col-sm-9">
				<input type="text" id="option2" placeholder="Option 2"
					class="form-control" autofocus>
			</div>
			<div class="col-sm-12"></div>
			<div class="col-sm-3 col-sm-offset-3">
				<button type="submit" onclick="myFunction()"
					class="btn btn-primary btn-block">Add Question</button>
			</div>
		</div>
	</div>
 


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

 
  
	<script>
	function toUTF8Array(str) {
	    var utf8 = [];
	    for (var i=0; i < str.length; i++) {
	        var charcode = str.charCodeAt(i);
	        if (charcode < 0x80) utf8.push(charcode);
	        else if (charcode < 0x800) {
	            utf8.push(0xc0 | (charcode >> 6), 
	                      0x80 | (charcode & 0x3f));
	        }
	        else if (charcode < 0xd800 || charcode >= 0xe000) {
	            utf8.push(0xe0 | (charcode >> 12), 
	                      0x80 | ((charcode>>6) & 0x3f), 
	                      0x80 | (charcode & 0x3f));
	        }
	        // surrogate pair
	        else {
	            i++;
	            // UTF-16 encodes 0x10000-0x10FFFF by
	            // subtracting 0x10000 and splitting the
	            // 20 bits of 0x0-0xFFFFF into two halves
	            charcode = 0x10000 + (((charcode & 0x3ff)<<10)
	                      | (str.charCodeAt(i) & 0x3ff));
	            utf8.push(0xf0 | (charcode >>18), 
	                      0x80 | ((charcode>>12) & 0x3f), 
	                      0x80 | ((charcode>>6) & 0x3f), 
	                      0x80 | (charcode & 0x3f));
	        }
	    }
	    return utf8;
	}
	
$('#titletype').change(function() {
	  var type=$(this).val();
	  var audioHTML="<button onclick=\"startRecording(this);\">record</button>"
	  +"<button onclick=\"stopRecording(this);\" disabled>stop</button>"
	  +"<button onclick=\"reset(this);\" disabled>reset</button>"
	  +"<h4>Recorded Question</h4>"
	  +"<ul id=\"recordingslist\"></ul>";
	  if(type==="Audio"){
		  document.getElementById("que").innerHTML = audioHTML;
	  }
	  var txtHTML=""
	});
	
	function saveMyAudio(){
			  var url = URL.createObjectURL(gBlob);
		      var fd = new FormData();
		      
		      var option1 = document.getElementById('option1');
			  var option2 = document.getElementById('option2');
			  //var titleByte=toUTF8Array(title.value);
			  var opts=[];
			  opts.push(option1.value);
			  opts.push(option2.value);
			     
			  fd.append('type', 'audio');
			  fd.append('options', opts);
		      fd.append('fname', 'question.wav');
		      fd.append('data', gBlob);
		      $.ajax({
		          type: 'POST',
		          url: 'newquestionasaudio',
		          data: fd,
		          contentType: false,
		          processData: false
		      }).done(function(data) {
		    	  window.location.replace("newquestion");
		      }).fail(function() { alert("error"); });
		   
	}
	function myFunction(){
		if($('#titletype').val()==="Audio")
			saveMyAudio();
		if($('#titletype').val()==="Text")
			saveMyText();
		
		 
	}
	function saveMyText(){
		var type="text";
		var titles = document.getElementById('title');
		 var option1 = document.getElementById('option1');
	     var option2 = document.getElementById('option2');
	     //var titleByte=toUTF8Array(title.value);
	     var opts=[];
	     opts.push(option1.value);
	     opts.push(option2.value);
	     var body={'title':title.value,'options':opts};
		 
	     $.ajax({
	    	 		
	    	    data:{options:opts, title:titles.value},
	    	    url: 'newquestionastext',
	            type: 'post',
	            traditional: true
	        }).done(function(data) {
	        	
	        	window.location.replace("newquestion");
	        })
	        .fail(function() { alert("error"); });
	}
</script>

</body>

</html>