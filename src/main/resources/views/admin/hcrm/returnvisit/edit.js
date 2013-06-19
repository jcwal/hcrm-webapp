$(function() {
	var code = 'edit-returnvisit';
	var viewModel = ko.mapping.fromJS({});
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
	var currentId = $form.attr('item-id');
	if (currentId) {
		$.getJSON($form.getContextPath() + '/admin/hcrm/returnvisit/get/'
				+ currentId, function(data) {
			if (data.returnObject.actualVisitDate == null) {
				data.returnObject.actualVisitDate = new Date();
			}
			ko.mapping.updateFromJS(viewModel, data.returnObject);
			ko.applyBindings(viewModel, $content[0]);
		});
	}
	$('#hospitalNumberId').click(function(){
			window.open( $form.getContextPath() + '/admin/hcrm/patient/list/macula-mda/finder/PATIENT_SCHEMA/detail?item=' + viewModel.patient.id());
	});
});
