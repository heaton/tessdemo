Array.prototype.removeAt = function(i){
    return this.splice(i,1)[0];
};
var Command = function(checkbox) {
  this.name = checkbox.value;

  this.toString = function() {
    var params = [ this.name ];
    $(checkbox).parent().nextAll("input").each(function() {
      if(this.value.trim() == "") {
        return;
      }
      params.push(this.value);
    });
    return params.join(":");
  };
};

var Commands = function(s) {
  var cs = s==""?[]:s.split("|");
  
  function indexOf(command) {
    for(var i = 0; i < cs.length; i++) {
      var c = cs[i].split(":")[0];
      if(command.name == c) {
        return i;
      }
    }
    return -1;
  }

  this.trigger = function(command) {
    var i = indexOf(command);
    if(i > -1) {
      cs.removeAt(i);
    } else {
      cs.push(command.toString());
    }
  };

  this.toString = function() {
    return cs.join("|");
  };
};

$(function() {
  function removeInCommands(commands, c) {
    return false;
  }
  $("#imgFilters :checkbox").click(function() {
    var commands = new Commands($("#command").val());
    var command = new Command(this);
    commands.trigger(command);
    $("#command").val(commands.toString());
  });
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
    return {
      ocr_areas : $("#parameters").val(),
      file_name : $("#img_name").val(),
      command : $("#command").val(),
      white_list : $("#white_list").val(),
      ocr : $("#ocr:checked").val(),
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

});
