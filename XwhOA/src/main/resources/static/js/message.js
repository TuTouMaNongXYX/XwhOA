$.extend({
	message:function(options) {
		let _this = this;
		var settings = {
			type:'default',
			content:'提示内容',
			time:'2000',
			autoClose:true,
			onClose:function(){},
			define:function(){},
		};
		if(typeof options === "string") {
			settings.content = options;
		}
		if(typeof options === "object") {
			 settings = $.extend({},settings, options)
		}
		
		let top = 30;
		if($('.header_tps').length>0) {
				top = 90;
		}
		var msghtml = `<div class="YiJia_message messageFadeInDown" style="top:${top}px">
						<div class="YiJia_message_main YiJia_bg_${settings.type}">
							<i class="YiJia_message_icon YiJia_message_${settings.type}"></i>
							<div class="YiJia_content">${settings.content}</div>
						</div>
					</div>`;
		var body = $("body");
		var msg = $(msghtml);
		var clearTime;
		var msgA,msgB;
		msgA = function(){
			msg.addClass("messageFadeOutUp");
			msg.one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend",
			function() {
				msgB()
			})
		};
		msgB = function() {
			msg.remove();
			settings.onClose(settings);
			clearTimeout(clearTime)
		};
		$(".YiJia_message").remove();
		body.append(msg);
		msg.css({
		    "margin-left": "-" + msg.width() / 2 + "px",
		});
		msg.one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend",function(){
			msg.removeClass("messageFadeInDown");
		})
		if(settings.autoClose){
			
			clearTime = setTimeout(function(){
				msgA();	
				settings.define();		
			},settings.time);	
		}
		
		//function isExitsFunction()
		
	},
	
	confirm:function(content,title,options){
		var title = title ? title : '提示';
		var confirmBody = $('<div class="confirm_box_wrapper"><div class="confirm_model"></div></div>');
		var confirmBox = $('<div class="confirm_box"></div>');
		var confirmHeader = $('<div class="confirm_header"></div>')
		var confirmTitle = $(`<div class="confirm_title"><span>${title}</span></div>`);
		var confirmClose = $('<div class="confirm_close"><i class="icon"></i></div>');
		var confirmContent = $('<div class="confirm_content"></div>');
		var confirmIcon = $(`<div class="confirm_icon confirm_icon_${options.type}"></div>`);
		var confirmMessage = $(`<div class="confirm_message">${content}</div>`);
		var confirmBtns = $('<div class="confirm_btns"></div>')
		var confirmOffButton = $(`<button type="button" class="btns_default"><span>${options.cancelButtonText}</span></button>`);
		var confirmYesButton = $(`<button type="button" class="btns_primary"><span>${options.confirmButtonText}</span></button>`);
		confirmHeader.append(confirmTitle,confirmClose);
		if(options.type!=undefined) {
			confirmContent.append(confirmIcon);
		}
		confirmContent.append(confirmMessage);
		if(options.cancelButtonText!=undefined){
			confirmBtns.append(confirmOffButton);
		}
		confirmBtns.append(confirmYesButton);
		confirmBox.append(confirmHeader,confirmContent,confirmBtns);
		confirmBody.append(confirmBox);
		var body = $('body');
		body.append(confirmBody);
		confirmClose.on('click',function(){
			confirmBody.remove();
		});
		
		confirmOffButton.on('click',function(){
			confirmBody.remove();
			setTimeout(function() {
				options.cancel()
			})
		})
		
		confirmYesButton.on('click',function(){
			confirmBody.remove();
			setTimeout(function() {
				options.define()
			})
		})
	}
	
	
})


