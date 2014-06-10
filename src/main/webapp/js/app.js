(function(){
	
	$(function(){
		$("#picture").on("upload", function(e, files) {
			if(files.length < 1) {
                return;
			}
			var formData = new FormData();
			formData.append("ocr_areas", $("#parameters").val());
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
                        $("#imgName").val(data.imgName);
                    }
                },
                error: function(xhr, status) {
                    $("#message").text('errors:' + status);
                }
			});
		});
		$("#picture img").drawer({
			change : function($p){
			var formData = {ocr_areas:$p.val(), file_name:$("#img_name").val()};
			$.get("upload", formData, function(data){
				if(data.result == "success") {
                    $("#areaOcrResult").empty().append(data.data);
				}
			}, "json");
			
		}});
	});

})();
