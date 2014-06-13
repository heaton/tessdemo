(function() {
  function successHandler(data) {
    if(data.result == "success") {
      var rn = Math.floor(10000 * Math.random());
      $("#picture img").attr("src", data.imgPath + "?v=" + rn);
      $("#ocrResult").text(data.ocrResult);
      $("#img_name").val(data.imgName);
    } else {
      $("#message").text('errors:' + data.message);
    }
  }

  function getFormData() {
    var filters = [];
    $("#imgFilters :checkbox:checked").each(function() {
      var name = this.value;
      var params = [ name ];
      $(this).parent().nextAll("input").each(function() {
        if(this.value.trim() == "") {
          return;
        }
        params.push(this.value);
      });
      filters.push(params.join(":"));
    });
    return {
      ocr_areas : $("#parameters").val(),
      file_name : $("#img_name").val(),
      white_list : $("#white_list").val(),
      command : filters.join("|"),
      language : $("[name=language]:checked").val()
    };
  }

  function sendOcrRequest() {
        $.get("upload", getFormData(), successHandler, "json");
  }

  $(function() {
    $("#picture").on("upload", function(e, files) {
      if(files.length < 1) {
        return;
      }
      var formData = new FormData();
      formData.append("file", files[0]);
      var params = getFormData();
      for(name in params) {
        formData.append(name, params[name]);
      }
      $.ajax({
        url : "upload",
        data : formData,
        type : "POST",
        processData : false,
        contentType : false,
        dataType : "json",
        success : successHandler,
        error : function(xhr, status) {
          $("#message").text('errors:' + status);
        }
      });
    });
    $("#picture img").drawer({
      change : sendOcrRequest
    });
    $("#req_send").click(sendOcrRequest);
  });

})();
