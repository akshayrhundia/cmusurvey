$(function() {

	
	
	
	var path = document.location.pathname
	var res = path.split("/");
	// alert(res[res.length - 1]);
	var curr = parseInt(res[res.length - 1]);
	//alert(curr);
	var user=getParameterByName('user',window.location.search);
	var age=getParameterByName('age',window.location.search);
	var gender=getParameterByName('gender',window.location.search);
	var occupation=getParameterByName('occupation',window.location.search);
	//alert(user);
	
	if(user!=null){
	$
	.get(
			"../register/" + user.toUpperCase()+"?age="+age+"&gender="+gender+"&occupation="+occupation,
			function(registered, rstatus) {
			//	alert(registered);
	
	$
			.get(
					"getquestion/" + res[res.length - 1],
					function(data, status) {
						// alert("Data: " + data + "\nStatus: " + status);
						
						if(data=="Over"){
							$
							.get(
									"getuser/" + user.toUpperCase(),
									function(userdata, userstatus) {
								var jData=JSON.parse(userdata);
							var body = '<div class="modal-dialog">'
								+ '<div class="modal-content">'
								+ '<div class="modal-header">'
								+ '  <h2> Thanks '
								+ user
								+ ' for taking survey. Have a great day!</h2>'
								+ '  <h4> Your completion code is : '
								+ jData.id
								+ ' </h4>'
								+ '</div>'
								+ '<div class="modal-body">'
								+ '</div>';
								//document.getElementById("main-body").innerHTML = body;
							/*$.get("../last", function(data, rstatus) {
								jdata = JSON.parse(data);
								//console.log(jdata.main);
								body+=jdata.last;
								document.getElementById("main-body").innerHTML = body;
								//lastv.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.last;
								//inst.getElementsByClassName("Editor-editor")[0].innerHTML=jdata.inst;
							});*/
							window.location.replace("last?user="+user.toUpperCase());
								
						});
											
						}else{
						var jData = JSON.parse(data);
						var body = '<div class="modal-dialog">'
								+ '<div class="modal-content">'
								+ '<div class="modal-header" style="height:100px;">'
								+ '  <h3><span id="qid">'
								+ curr
								+ '-</span> '
								+ jData.quest
								+ '</h3>'
								+ '</div>'
								+ '<div class="modal-body">'
								+ '   <div class="col-xs-3 col-xs-offset-5">'
								+ '     <div id="loadbar" style="display: none;">'
								+ '       <div class="blockG" id="rotateG_01"></div>'
								+ '      <div class="blockG" id="rotateG_02"></div>'
								+ '     <div class="blockG" id="rotateG_03"></div>'
								+ '    <div class="blockG" id="rotateG_04"></div>'
								+ '   <div class="blockG" id="rotateG_05"></div>'
								+ '  <div class="blockG" id="rotateG_06"></div>'
								+ ' <div class="blockG" id="rotateG_07"></div>'
								+ ' <div class="blockG" id="rotateG_08"></div>'
								+ '</div>'
								+ '</div>'
								+ '<div class="quiz" id="quiz" data-toggle="buttons">';
						var count = 1;
						var options=jData.options
						options.sort(function(a, b){
							  // ASC  -> a.length - b.length
							  // DESC -> b.length - a.length
							  return a.option.length - b.option.length;
							});
						//alert("hi");
						console.log(options);
						options
								.forEach(function(item) {
									// console.log(item.name + ' ' + item.id);
									if(item.option!=""){
									body += '<div class="alert alert-info" role="alert">'
											+ '<b>'+count+')</b> '+item.option
											+ '</div>'
									count += 1;
									}
								});

						$.get("getansbyuser?user="+user.toUpperCase()+"&code="+curr,
								function(old, oldstatus) {
						//	alert(old);
							if(old!="Failed"){
								var oldjson=JSON.parse(old);
						body += '</div>'
								+ '</div>'
								+ '<div class="panel panel-default">'
								+ '<div class="panel-heading clearfix">'
								+ '<i class="icon-calendar"></i>'
								+ '<h3 class="panel-title">Please type "yes" or "no" below.</h3>'
								+ '</div>'
								+ '<div class="panel-body">'
								+ '<div class="input-group input-group-lg">'
								+ '	<span class="input-group-addon">@</span> <input required type="text"'
								+ '	id="answer"	class="form-control input-lg" value="'+oldjson.ans+'" placeholder="Answer">'
								+ '</div>' + '</div>' + '</div>';
							}
							else{
								body += '</div>'
									+ '</div>'
									+ '<div class="panel panel-default">'
									+ '<div class="panel-heading clearfix">'
									+ '<i class="icon-calendar"></i>'
									+ '<h3 class="panel-title">Please type "yes" or "no" below.</h3>'
									+ '</div>'
									+ '<div class="panel-body">'
									+ '<div class="input-group input-group-lg">'
									+ '	<span class="input-group-addon">Ans:</span> <input required type="text"'
									+ '	id="answer"	class="form-control input-lg" placeholder="Answer">'
									+ '</div>' + '</div>' + '</div>';
							}
						body += '<div class="modal-footer text-muted">'
								+ '<ul class="pager">'
								//+ '<li class="previous"><a href="' + (curr - 1)+'?user='+user.toUpperCase()+"&age="+age+"&gender="+gender+"&occupation="+occupation+'">Previous</a></li>'
								+ '<li class="next"><a id="next"'
								+ '>Next</a></li>' + '</ul>'
								+ ' <span id="answer"></span>' + '</div>';
						
						// alert(body);
						document.getElementById("main-body").innerHTML = body;
						$('#next').click(
								function() {
									var ans = $('#answer').val();
									if(ans=='undefiend' || ans==null || ans==""){
										alert("Please answer the question.")
										return;
								}
									$.get("addans?name="+user.toUpperCase()+"&ans=" + ans.toUpperCase()
											+ "&code=" + curr
											, function(
											ansdata, ansstatus) {
										window.location.replace((curr+1).toString()+"?user="+user.toUpperCase()+"&age="+age+"&gender="+gender+"&occupation="+occupation);
									});
								});
								});

						//$( "main-body" )
						//.append( "Hello");
						}
					});
					
			});
	}

});

function getParameterByName(name, url) {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}