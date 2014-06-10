<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tess Demo</title>
<link type="text/css" href="js/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="css/jquery.freetrans.css" rel="stylesheet" />
<script type="text/javascript" src="js/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="js/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bower_components/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<script type="text/javascript" src="js/Matrix.js"></script>
<script type="text/javascript" src="js/jquery.freetrans.js"></script>
<script type="text/javascript" src="js/drawer.js"></script>
<script type="text/javascript" src="js/picdrag.js"></script>
<script type="text/javascript" src="js/app.js"></script>
</head>
<body>
<div id="message" class="alert alert-warning"></div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6" id="picture">
				<img style="width:100%" src="${imgPath}" data-param-id="parameters"/>
			</div>
			<div class="col-md-6">
				<h3>Parameters</h3>
				<textarea id="parameters" style="width:100%;height:180px" readonly></textarea>
				<input type="hidden" id="img_name" value="${imgName}"/>
				<h3>Result</h3>
				<pre id="areaOcrResult"></pre>
				<pre id="ocrResult">${result}</pre>
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