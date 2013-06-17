$(function() {
	$('input[type=text],input[type=password]').focusin(function(e) {
		$(this).addClass('highlight1');
	});

	$('input[type=text],input[type=password]').focusout(function() {
		$(this).removeClass('highlight1');
	});

	$('#password').keypress(function(event) {
		detectCapsLock(event);
	});

	$('#password').blur(function() {
		$('#caps-info').hide();
	});

	// 恢复用户名
	var MaculaUserName = $.cookie("MaculaUserName");
	if (MaculaUserName != null && MaculaUserName != "") {
		$('#userName').val(MaculaUserName);
		$('#password').focus();
	} else {
		$('#userName').select();
	}

	function detectCapsLock(e) {
		var valueCapsLock = e.which;
		var valueShift = e.shiftKey;
		if (((valueCapsLock >= 65 && valueCapsLock <= 90) && !valueShift) || ((valueCapsLock >= 97 && valueCapsLock <= 122) && valueShift)) {
			$('#caps-info').html("Caps Lock键已开启,密码输入是大写状态!").show();
		} else {
			$('#caps-info').hide();
		}
	}

	function updateCaptcha() {
		var updataCaptchaUrl = base + '/ucaptcha?id=' + $('#captchaId').val() + '&_t=' + new Date().getTime();
		$.get(updataCaptchaUrl, function(data) {
			if (data) {
				var captchaUrl = base + '/captcha?id=' + data + '&_t=' + new Date().getTime();
				$('#loginCode').attr('src', captchaUrl);
				$('#captchaId').val(data);
			} else {
				alert('请提供有效的凭据！');
			}
		});
	}

	$('#loginCode,#loginCode_a').click(function() {
		updateCaptcha();
	});

	$.metadata.setType('attr', 'validate');
	var validator = $("#localLoginForm").validate({
		validClass : "loginvalid",
		submitHandler : function(form) {
			$(form).find('input[type=submit]').attr('disabled', 'disabled');
			// 记住用户名
			if ($('#chkRememberUsername').getChecked()) {
				$.cookie("MaculaUserName", $('#userName').val(), {
					expires : 365,
					path : '/'
				});
			} else {
				$.cookie("MaculaUserName", null, {
					path : '/'
				});
			}
			form.submit();
		},
		errorPlacement : function(error, element) {
			var $el = $('#' + $(element).attr("id") + "_error");
			$el.html(error).show().attr("class", "error");
		},
		errorClass : "highlight2"
	});

	if (typeof (validationErrors) != 'undefined') {
		validator.showErrors(validationErrors);
	}

});
