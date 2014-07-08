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
<script type="text/javascript" src="js/app.js?v=1.1"></script>
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
				<form roll="form" class="form-inline">
				<div id="imgFilters" class="form-group">
				<div class="checkbox"><label>
					<input type="checkbox" id="reduce_noise" value="rn"/> Reduce noise
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="sharpen" value="sp"/> Sharpen
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="gray" value="gs"/> Gray
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="thresholding" value="th"/> Thresholding
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="gamma" value="gm"/> Gamma
				</label></div>
				<br>
				<div class="checkbox"><label>
					<input type="checkbox" id="despeckle" value="dc"/> Delete speckles
					</label>
					<input type="text" id="despeckle_times" placeholder="Repeat times"/>
				</div>
				<br>
				<div class="checkbox"><label>
					<input type="checkbox" id="median" value="md"/> Median
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="unsharp" value="us"/> Sharpen by Unsharp marking
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="hsba" value="ha"/> HSB Adjust+
				</label></div>
				<br>
				<div class="checkbox"><label>
					<input type="checkbox" id="posterize" value="pt"/> Posterize
					</label>
					<input type="text" id="posterize" placeholder="Number Level, Default is 64"/>
				</div>
				<br>
				<div class="checkbox"><label>
					<input type="checkbox" id="edge" value="ed"/> Edge
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="dog" value="dg"/> Edge by Difference of Gaussians
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="laplace" value="lp"/> Edge by Laplace operator
				</label></div>
				<br>
				<div class="checkbox"><label>
					<input type="checkbox" id="invert" value="iv"/> Invert
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="bump" value="bu"/> Bump
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="blur" value="bl"/> Blur
				</label></div>
				<div class="checkbox"><label>
					<input type="checkbox" id="smart_blur" value="sb"/> Smart Blur
				</label></div>
				<br>
					<label for="command">Command:</label>
				    <input type="text" id="command" readonly/>
				</div>
				<div class="form-group">
					<label for="white_list">White list:</label>
					<input type="text" id="white_list"/>
				    <div class="radio"><label>
					   <input type="radio" name="language" value="eng" checked> English
					</label></div>
				    <div class="radio"><label>
					   <input type="radio" name="language" value="chi_sim"> Simplified Chinese
					</label></div>
				</div>
				<div class="form-action">
				    <div class="checkbox"><label>
					   <input type="checkbox" id="ocr" value="yes"/> Ocr
				    </label></div>
				    <button id="req_send" type="button" class="btn btn-default">Check</button>
				</div>
				</form>
				<textarea id="parameters" style="width:100%;height:180px" readonly></textarea>
				<input type="hidden" id="img_name" value="${imgName}"/>
				<h3>Result</h3>
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