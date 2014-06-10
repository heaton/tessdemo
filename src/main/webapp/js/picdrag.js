(function() {

	function FileDragHover(e) {
		e.stopPropagation();
		e.preventDefault();
	}

	function FileSelectHandler(e) {
		FileDragHover(e);
		var files = e.target.files || e.originalEvent.dataTransfer.files;

		$(this).trigger("upload", [files]);

	}

	function Init() {

		var $fileselect = $("#file");
		var $filedrag = $("#picture");

		$fileselect.change(FileSelectHandler);

		var xhr = new XMLHttpRequest();
		if (xhr.upload) {
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