(function($) {

	function _printPostions($p, $bg, $div, opts) {
		var pos = $div.position();
		var l = pos.left;
		var t = pos.top;
		var w = $div.width(), h = $div.height();
		if(opts.referToImg) {
			var bw = $bg.width();
			var sw = $bg.get(0).naturalWidth || bw;
			var rate = sw/bw;
			l = l * rate;
			t = t * rate;
			w = w * rate;
			h = h * rate;
		}
		var valueList = $p.val().split("\n");
		var index = $div.data("i");
		valueList[index] = index + "=" + (l<<0) + "," + (t<<0) + "," + (w<<0) + "," + (h<<0);
		$p.val(valueList.join("\n"));
	}

	$.fn.drawer = function(options){
		return this.each(function(){
			var $this = $(this);
			var defaults = {
				paramId: $(this).attr("data-param-id"),
				referToImg: true,
				change: function() {}
			};
			var opts = $.extend({}, defaults, options);
			var start = {};
			var $p = $("#" + opts.paramId);
			var $div = null;
			var index = 0;
			var _afterChange = function($div) {
				_printPostions($p, $this, $div, opts);
				return opts.change($p, $this, $div);
			};
			$(this).mousedown(function(e){
				if(e.which != 1){
					return;
				}
				start.x = e.offsetX;
				start.y = e.offsetY;
				$div = $("<div style='position: absolute;border: 1px solid black;'></div>");
				$(this).parent().append($div);
				$div.css({left:start.x, top:start.y});
			}).mousemove(function(e){
				if($div == null) {
					return;
				}
				$div.width(e.offsetX - start.x).height(e.offsetY - start.y);
			}).mouseup(function(e){
				if($div == null) {
					return;
				}
				var w = e.offsetX - start.x;
				var h = e.offsetY - start.y;
				$div.width(w).height(h).freetrans({rotatable: false, after: _afterChange});
				$div.data({i:index++});
				_printPostions($p, $this, $div, opts);
				opts.change($p, $this, $div);
				$div = null;
			}).on("dragstart", function(e){
				e.stopPropagation();
				e.preventDefault();
			});
		});
	};
	
})(window.jQuery);
