$(document).ready(function() {
	$.metadata.setType('attr', 'validate');
	var subValidator = $('#sub_so_detail_form').validate();
	var $form = $('#so_master_form');
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
			code : '',
			label : ''
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

	// 获取和界面一起返回的JSON数据
	if (samples_demo_soMaster) {
		ko.mapping.updateFromJS(viewModel, samples_demo_soMaster);
	}

	viewModel.selectedModel = ko.mapping.fromJS(soDetail);
	viewModel.originModel = ko.observable();

	// 选择下拉后需要设置selectedModel.product中的其他属性
	viewModel.onOptionsChange = function(row) {
		if (row != null) {
			if (this.selectedModel.product.code()) {
				this.selectedModel.product.label(row['label']);
			}
		}
	}

	// 明细列表选中，更新selectedModel
	viewModel.onRowSelect = function(detail) {
		this.cancelUpdate();
		ko.mapping.updateFromJS(this.selectedModel, ko.mapping.toJS(detail));
		this.originModel(detail);
	}

	// 取消修改
	viewModel.cancelUpdate = function() {
		// 清空selectedModel
		ko.mapping.updateFromJS(this.selectedModel, soDetail);
		this.originModel(null);
		this.resetDetailValidator();
	};

	// 删除一行
	viewModel.deleteModel = function(detail) {
		detail.id() ? detail.deleted(true) : this.soDetails.remove(detail);
		this.cancelUpdate();
	};

	// 确认修改/新增
	viewModel.updateModel = function() {
		if (!subValidator.form()) {
			return;
		}
		// 替换或者插入一行记录到soDetails，为了和selectedModel脱离关系，需要用from to JS克隆后修改
		if (this.originModel() != null) {
			this.soDetails.replace(this.originModel(), ko.mapping.fromJS(ko.toJS(this.selectedModel)));
		} else {
			this.soDetails.push(ko.mapping.fromJS(ko.toJS(this.selectedModel)));
		}
		this.cancelUpdate();
	};

	// 重置FORM
	viewModel.resetDetailValidator = function() {
		subValidator.resetForm();
	};

	ko.applyBindings(viewModel, $('#so_master_page')[0]);

	var currentId = $("#so_master_form").attr('item-id');

	// 如果没有随页面返回数据，则需要异步请求数据
	if (!samples_demo_soMaster) {
		if (currentId) {
			$.getJSON($("#so_master_form").getContextPath() + '/admin/macula-trial/so_master/read/' + currentId, function(data) {
				ko.mapping.updateFromJS(viewModel, data.returnObject);
			});
		}
	}

	// 清除变量
	delete samples_demo_soMaster;
});
