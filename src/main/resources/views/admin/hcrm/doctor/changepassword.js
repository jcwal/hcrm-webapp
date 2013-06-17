$(function() {
	var code = 'change-password';

	var $content = $('#page-' + code);
	var $form = $content.find('form:first');
	$.metadata.setType('attr', 'validate');

	var validator = $form.validate({
		submitHandler : function(form) {
			$(form).ajaxSubmit(
					{
						success : function(data) {
							if (data.success) {
								MessageBox.info('保存成功!', true);
								$(form).trigger('closeDialog');
							} else {
								var errors = {};
								$(data.validateErrors).each(function() {
									errors[this.element] = this.message;
								});
								validator.showErrors(errors);
								data.exceptionMessage
										&& MessageBox.error(
												data.exceptionMessage, true);
							}
						}
					});
		}
	});

	$form.find('.cancel-btn').click(function() {
		$(this).trigger('closeDialog');
	});
});