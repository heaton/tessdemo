<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tess Demo</title>
<link type="text/css" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bower_components/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
<div id="message" class="alert alert-warning"></div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6" id="picture">
				<img src="${imgPath}" style="max-width: 100%"/>
			</div>
			<div class="col-md-6">
				<h3>Result</h3>
				<pre>${result}</pre>
				<form action="upload" method="POST" enctype="multipart/form-data">
					<input type="file" id="file" name="file" />
					<!--
					<div class="progress">
						<div id="progressbar" class="progress-bar"></div>
					</div>
					 -->
				</form>
			</div>
		</div>
	</div>
</body>
</html>