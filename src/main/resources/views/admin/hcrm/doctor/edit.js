$(function() {
	var code = 'edit-doctor';
	var viewModel = ko.mapping.fromJS({
		id : '',
		username : '',
		password : '',
		rePassword : '',
		nickname : '',
		title : '',
		department : '',
		telephone : '',
		mobile : ''
	});
	var $content = $('#page-' + code);
	ko.applyBindings(viewModel, $content[0]);
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
	var currentId = $form.attr('item-id');
	if (currentId) {
		$.getJSON($form.getContextPath() + '/admin/hcrm/doctor/get/'
				+ currentId, function(data) {
			ko.mapping.updateFromJS(viewModel, data.returnObject);
		});
	}
});
