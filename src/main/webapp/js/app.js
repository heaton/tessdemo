(function() {

	// output information
	function msg(msg) {
		$("#message").empty().append(msg);
	}


	// file drag hover
	function FileDragHover(e) {
		e.stopPropagation();
		e.preventDefault();
	}


	// file selection
	function FileSelectHandler(e) {
		FileDragHover(e);
		// fetch FileList object
		var files = e.target.files || e.originalEvent.dataTransfer.files;

		submitFiles(files);

//		for (var i = 0, f; f = files[i]; i++) {
//			ParseFile(f);
//		}

	}

	function submitFiles(files) {
		if(files.length < 1) {
			return;
		}

		var formData = new FormData();
		formData.append("hello", "google");
		formData.append("file", files[0]);
		
		$.ajax({
			url: "upload",
			data: formData,
			type: "POST",
			processData: false,
			contentType: false,
			dataType: "json",
			success: function(data) {
				if (data.result == "success") {
					var rn = Math.floor(10000 * Math.random());
					$("#picture img").attr("src", data.imgPath + "?v=" + rn);
					$("#ocrResult").empty().append(data.ocrResult);
				}
			},
			error: function(xhr, status) {
				msg('errors:' + status);
			}
		});
	}

	// output file information
	function ParseFile(file) {

		msg(
			"<p>File information: <strong>" + file.name +
			"</strong> type: <strong>" + file.type +
			"</strong> size: <strong>" + file.size +
			"</strong> bytes</p>"
		);

	}


	// initialize
	function Init() {

		var $fileselect = $("#file");
		var $filedrag = $("#picture");

		// file select
		$fileselect.change(FileSelectHandler);

		// is XHR2 available?
		var xhr = new XMLHttpRequest();
		if (xhr.upload) {

			// file drop
			$filedrag.on("dragover", FileDragHover);
			$filedrag.on("dragleave", FileDragHover);
			$filedrag.on("drop", FileSelectHandler);

		}

	}

	$(function() {
		if (window.File && window.FileList && window.FileReader) {
			Init();
		}
	});

})();