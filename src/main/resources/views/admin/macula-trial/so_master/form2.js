$(document).ready(function() {
	$.metadata.setType('attr', 'validate');
	var $form = $('#so_master_form2');
	var closeOnSave = false;
	var validator = $form.validate({
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				success : function(data) {
					try {
						if (data.success) {
							MessageBox.info('保存成功!', true);
							if (closeOnSave) {
								$(form).trigger('closeDialog');
								closeOnSave = false;
								return;
							}
							if (data.returnObject) {
								$.getJSON($(form).getContextPath() + '/admin/macula-trial/so_master/read/' + data.returnObject, function(data) {
									ko.mapping.updateFromJS(viewModel, data);
								});
							}
						} else {
							var errors = {};
							$(data.errors).each(function() {
								errors[this.element] = this.message;
							});
							validator.showErrors(errors);
							data.exceptionMessage && MessageBox.error(data.exceptionMessage, true);
						}
					} catch (e) {
						alert(e.message);
					}
				}
			});
		}
	});
	
	$form.find('.cancel-btn').click(function() {
		$(this).trigger('closeDialog');
	});
	
	$form.find('.save-close-btn').click(function() {
		closeOnSave = true;
		$form.submit();
	});

	// initial observable define
	var soDetail = {
		id : '',
		product : {
			code : ''
		},
		price : '',
		qty : '',
		deleted : ''
	}

	var soMaster = {
		id : '',
		soNo : '',
		dealerNo : '',
		soType : {
			code : ''
		},
		soDetails : []
	};

	// 初始化
	var viewModel = ko.mapping.fromJS(soMaster);

	// 删除一行
	viewModel.deleteModel = function(detail) {
		detail.id() ? detail.deleted(true) : this.soDetails.remove(detail);
		this.cancelUpdate();
	};
	// 新增一行
	viewModel.onAddDetails = function() {
		this.soDetails.push(ko.mapping.fromJS(soDetail));
		return false;
	}

	// 数据绑定
	ko.applyBindings(viewModel, $('#so_master_page2')[0]);

	// 获取当前数据
	var currentId = $form.attr('item-id');

	// 如果没有随页面返回数据，则需要异步请求数据
	if (currentId) {
		$.getJSON($form.getContextPath() + '/admin/macula-trial/so_master/read/' + currentId, function(data) {
			ko.mapping.updateFromJS(viewModel, data.returnObject);
		});
	}
});
